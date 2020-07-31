/*
 * Copyright (c) 2019 Dmitry Avtonomov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package umich.ms.fileio.filetypes.bruker;

import com.dmtavt.utils.PathUtils;
import com.dmtavt.utils.StringUtils;
import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Two system properties can be used to provide an alternative path and name for Bruker libraries.<br/>
 * Use "-Dbruker.lib.path=..." to provide location (either direct or where ext/bruker is).<br/>
 * Use "-Dbruker.lib.name=..." to change the name of the loaded library, e.g. "timsdata-3-1-5".
 */
public interface Timsdata extends Library {
  static final Logger log = LoggerFactory.getLogger(Timsdata.class);

  long tims_open(String analysis_dir, long use_recalib);
  long tims_close(long handle);
  long tims_get_last_error_string(byte[] error, long len);
  long tims_read_scans_v2(long handle, long frameId, long scanBegin, long scanEnd, byte[] scanBuffer, long len);
  long tims_index_to_mz(long handle, long frameId, double[] index, double[] mz, long len);
  long tims_scannum_to_oneoverk0(long handle, long frameId, double[] scannum, double[] oneOverK0, long len);
  long tims_oneoverk0_to_scannum(long handle, long frameId, double[] oneoverk0, double[] scannum, long len);

  interface msms_spectrum_functor extends Callback {
    void invoke(long precursor_id, long num_peaks, Pointer mz_values, Pointer area_values);
  }

  /**
   * @param frame_id <b>MS1</b> frame id. If MS2 frame is given - no spectra are returned and no
   * error is thrown.
   * @param callback Callback functor accepting the MS/MS spectra.
   */
  long tims_read_pasef_msms_for_frame(long handle, long frame_id, msms_spectrum_functor callback);

  /**
   * @param precursors List of PASEF precursor IDs; the returned spectra may be in different order.
   * @param num_precursors Number of requested spectra, must be >= 1.
   * @param callback Callback accepting the MS/MS spectra.
   */
  long tims_read_pasef_msms(long handle, long[] precursors, long num_precursors, msms_spectrum_functor callback);


  class Instance {
    static Timsdata INSTANCE;
    static String LOADED_LIB_NAME;
    static UnsatisfiedLinkError LIB_LOAD_EXCEPTION;

    static {
      final String propJnaLibPath = "jna.library.path";
      String val = System.getProperty(propJnaLibPath);
      HashSet<String> jnaPaths = new LinkedHashSet<>();
      if (val != null) {
        String[] split = val.split(File.pathSeparator);
        jnaPaths.addAll(Arrays.asList(split));
      }

      // relative locations
      final List<String> addonPaths = new ArrayList<>();
      addonPaths.add(join("ext", "bruker"));
      addonPaths.add(join(".", "ext", "bruker"));
      addonPaths.add(join("..", "ext", "bruker"));
      addonPaths.add(join("ext-bruker"));
      addonPaths.add(join(".", "ext-bruker"));
      addonPaths.add(join("..", "ext-bruker"));

      // user paths + relative locations
      List<String> userSuppliedPaths = Arrays.stream(SYS_PROP_BRUKER_LIB_PATH)
          .map(System::getProperty).filter(Objects::nonNull).collect(Collectors.toList());
      if (userSuppliedPaths.isEmpty()) {
        log.debug("No user supplied paths to Bruker lib found");
      } else {
        log.debug("Found user supplied paths to bruker lib: {}", userSuppliedPaths);
        for (String userPath : userSuppliedPaths) {
          List<String> userPaths = Arrays.stream(userPath.split(File.pathSeparator))
              .filter(p -> !StringUtils.isNullOrWhitespace(p)).collect(Collectors.toList());
          jnaPaths.addAll(userPaths);
          // relative locations of user paths
          List<String> userPathsRel = userPaths.stream().map(Paths::get)
              .flatMap(up -> addonPaths.stream().map(up::resolve))
              .map(Path::toString).collect(Collectors.toList());
          jnaPaths.addAll(userPathsRel);
        }
      }


      try {
        log.debug("Trying to get main jar path");
        String mainJarPath = PathUtils.getMainJarPath();
        if (mainJarPath == null) {
          log.debug("No main jar path found");
        } else {
          log.debug("Got main jar path: {}", mainJarPath);
          Path p = Paths.get(mainJarPath).getParent();
          List<String> relToMainJar = addonPaths.stream()
              .map(ap -> p.resolve(ap).toAbsolutePath().normalize().toString())
              .collect(Collectors.toList());
          jnaPaths.addAll(relToMainJar);
        }
      } catch (Exception e) {
        log.warn("Could not get main jar path", e);
      }

      // relative locations themselves
      jnaPaths.addAll(addonPaths);

      // classpath + relative locations
      List<String> cps = PathUtils.getClasspaths();
      if (cps == null || cps.isEmpty()) {
        log.debug("No classpaths found");
      } else {
        log.debug("Classpaths found: {}", String.join(File.pathSeparator, cps));
        List<String> cpAugmented = cps.stream()
            .filter(s -> !StringUtils.isNullOrWhitespace(s)).distinct()
            .map(cp -> {
              Path p = Paths.get(cp);
              if (Files.isDirectory(p)) {
                return p;
              } else if (Files.isRegularFile(p)) {
                return p.getParent();
              } else {
                Path abs = p.toAbsolutePath();
                return Files.isDirectory(abs) ? abs : abs.getParent();
              }
            })
            .filter(Objects::nonNull).distinct()
            .flatMap(cp -> addonPaths.stream().map(ap -> cp.resolve(ap).normalize().toString()))
            .distinct().collect(Collectors.toList());
        jnaPaths.addAll(cpAugmented);
      }

      String newJnaPath = String.join(File.pathSeparator, jnaPaths);
      System.setProperty(propJnaLibPath, newJnaPath);

      log.debug("Checking JNA paths before loading bruker lib");

      final String libName = Arrays.stream(SYS_PROP_BRUKER_LIB_NAME)
          .map(System::getProperty).filter(Objects::nonNull).findFirst().orElse(BRUKER_LIB_NAME);
      try {
        log.debug("JNA trying to load native library, name: {}", libName);
        INSTANCE = Native.load(libName, Timsdata.class);
        log.debug("JNA successfully loaded native library, name: {}", libName);
        LOADED_LIB_NAME = libName;
      } catch (UnsatisfiedLinkError e) {
        log.debug("JNA paths: {}", PathUtils.getJnaLoadingPaths());
        log.debug("JNA paths one per line:\n{}", String.join("\n", PathUtils.getJnaLoadingPaths().split(File.pathSeparator)));
        log.error("JNA could not load native Bruker library", e);
        INSTANCE = null;
        LOADED_LIB_NAME = "";
        LIB_LOAD_EXCEPTION = e;
      }
    }

    private static String join(String... pathParts) {
      return String.join(File.separator, pathParts);
    }
  }
  static Timsdata getInstance() {
    return Instance.INSTANCE;
  }
  static String getLoadedLibName() {
    return Instance.LOADED_LIB_NAME;
  }
  public String BRUKER_LIB_NAME = "timsdata-2-4-4";
  public static final String[] SYS_PROP_BRUKER_LIB_NAME = {"bruker.lib.name", "libs.bruker.bin"};
  public static final String[] SYS_PROP_BRUKER_LIB_PATH = {"bruker.lib.path", "libs.bruker.dir"};
}
