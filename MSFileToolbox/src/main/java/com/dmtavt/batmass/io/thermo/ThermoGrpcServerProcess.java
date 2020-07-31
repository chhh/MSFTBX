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

package com.dmtavt.batmass.io.thermo;

import com.github.chhh.OsUtils;
import com.github.chhh.PathUtils;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThermoGrpcServerProcess implements IGrpcServerProcess {
  private static final Logger log = LoggerFactory.getLogger(ThermoGrpcServerProcess.class);

//  private static List<String> BIN_SEARCH_PATHS_ABSOLUTE = Arrays.asList("C:\\code\\massoid\\org\\massoid-io\\massoid-io-csharp\\MassoidIoThermoGrpcServer\\bin\\ext\\thermo");
//  private static List<String> BIN_SEARCH_PATHS_ABSOLUTE = Arrays.asList("C:\\code\\massoid\\org\\massoid-io\\massoid-io-csharp\\MassoidIoThermoGrpcServer\\bin\\Debug");
//  private static List<String> BIN_SEARCH_PATHS_ABSOLUTE = Arrays.asList("C:\\code2\\batmass-io\\batmass-io-thermo\\BatmassIoThermoServer\\bin\\Debug");
  private static List<String> BIN_SEARCH_PATHS_ABSOLUTE = Collections.emptyList();
  private static List<String> BIN_SEARCH_PATHS_RELATIVE = Arrays.asList(".", "..",
        "../../../..",     // running tests from gradle
        "../../../../.."); // debugging from IDEA
//      "../batmass-io/batmass-io-thermo/BatmassIoThermoServer/bin", // dev-env only: only, run tests on gradle build
//      "../../batmass-io/batmass-io-thermo/BatmassIoThermoServer/bin"); // dev-env only: debug from idea
//  private static List<String> BIN_SEARCH_PATHS_RELATIVE = Collections.emptyList();
  private static final String SERVER_DIR = "ext/thermo";
  private static final List<String> SERVER_BIN = Arrays.asList("MassoidIoThermoGrpcServer.exe", "BatmassIoThermoServer.exe");
  public static final String[] JAVA_SYSTEM_PROP_PREFIXES = {"", "batmass.io."};
  public static final String JAVA_SYSTEM_PROP_LIBS_DIR = "libs.thermo.dir";
  public static final String JAVA_SYSTEM_PROP_LIBS_BIN = "libs.thermo.bin";
  public static final String JAVA_SYSTEM_PROP_LIBS_TIMEOUT = "libs.thermo.timeout";

  private volatile Process process;
  private volatile Integer port;

  public ThermoGrpcServerProcess() {
    if (findServerBin() == null) {
      throw new UnsupportedOperationException("Batmass-IO binaries for Thermo support and/or Thermo native libraries not found found");
    }
  }

  @Override
  public String serverName() {
    return "Batmass Thermo gRPC .NET";
  }

  @Override
  public synchronized boolean isRunning() {
    return process != null && process.isAlive();
  }

  @Override
  public synchronized Integer port() {
    return port;
  }

  private static BufferedReader from(InputStream is) {
    return new BufferedReader(new InputStreamReader(new BufferedInputStream(is), StandardCharsets.UTF_8));
  }

  @Override
  public synchronized Process stop() {
    if (process != null) {
      return process.destroyForcibly();
    }
    return process;
  }

  private static Path findExistingBin(Collection<Path> paths, Collection<String> fn) {
    for (Path path : paths) {
      for (String f : fn) {
        try {
          Path p = path.resolve(f);
          if (Files.exists(p)) {
            return p;
          }
        } catch (Exception ignore) {}
      }
    }
    return null;
  }

  public static String getServerTimeoutProp() {
    final HashSet<String> timeouts = new LinkedHashSet<>();
    Arrays.stream(JAVA_SYSTEM_PROP_PREFIXES)
            .map(prefix -> prefix + JAVA_SYSTEM_PROP_LIBS_TIMEOUT)
            .forEach(propName -> {
              String timeout = System.getProperty(propName);
              if (timeout != null) {
                timeouts.add(timeout);
              }
            });
    return timeouts.stream().findFirst().orElse(null);
  }

  public static Path findServerBin() {
    LinkedHashSet<Path> searchPaths = new LinkedHashSet<>();

    // from system properties (user provided)
    List<String> libDirPropNames = Arrays.stream(JAVA_SYSTEM_PROP_PREFIXES).map(prefix -> prefix + JAVA_SYSTEM_PROP_LIBS_DIR).collect(Collectors.toList());
    for (String libDirPropName : libDirPropNames) {
      String dirFromSysProps = System.getProperty(libDirPropName);
      if (dirFromSysProps != null) {
        log.debug("Found system property {}: {}", JAVA_SYSTEM_PROP_LIBS_DIR, dirFromSysProps);
        try {
          searchPaths.add(Paths.get(dirFromSysProps));
        } catch (Exception e) {
          log.error("Could not add search path, is it a valid location? [{}]", dirFromSysProps);
        }
      }
    }

    // from absolute paths list
    searchPaths.addAll(BIN_SEARCH_PATHS_ABSOLUTE.stream().map(Paths::get).collect(Collectors.toList()));

    // from classpath
    Set<String> classpathDirs = PathUtils.getClasspathDirs();
    searchPaths.addAll(classpathDirs.stream()
        .map(s -> Paths.get(s).resolve(SERVER_DIR))
        .collect(Collectors.toList()));

    URI jarUri = PathUtils.getCurrentJarUri();
    log.debug("JAR URI: {}", jarUri);
    if (jarUri != null) {
      String jarMainPath = PathUtils.extractMainFilePath(jarUri);
      log.debug("Main JAR file path: {}", jarMainPath);
      if (jarMainPath != null) {
        try {
          Path jarPath = Paths.get(jarMainPath);
          if (Files.exists(jarPath)) {
            Path jarDir = Files.isDirectory(jarPath) ? jarPath : jarPath.getParent();
            for (String relPath : BIN_SEARCH_PATHS_RELATIVE) {
              searchPaths.add(jarDir.resolve(relPath).resolve(SERVER_DIR));
            }
          }
        } catch (Exception e) {
          log.debug("Exception while fiddling with current jar location", e);
        }

      }

    } else {
      log.debug("No JAR path found.");
      searchPaths.add(Paths.get(SERVER_DIR));
    }

    log.debug("Checking the following locations for BatmassIo Thermo Server:\n  {}",
        searchPaths.stream().map(Path::toString).collect(Collectors.joining("\n  ")));
    HashSet<String> binFns = new HashSet<>();
    Arrays.stream(JAVA_SYSTEM_PROP_PREFIXES).map(prefix -> prefix + JAVA_SYSTEM_PROP_LIBS_BIN).forEach(
            propNameBin -> {
              String binFromSysProps = System.getProperty(propNameBin);
              if (binFromSysProps != null) {
                binFns.add(binFromSysProps);
              }
            }
    );


    binFns.addAll(SERVER_BIN); // add these after system properties' one, this way user provided choice will have precedence.
    Path existingBin = findExistingBin(searchPaths, binFns);
    if (existingBin == null) {
      log.debug("Could not find Batmass Thermo Server binary(s): {}", String.join(", ", binFns));
    }
    return existingBin;
  }

  @Override
  public synchronized ThermoGrpcServerProcess start() {
    List<String> cmd = new ArrayList<>();

    Path serverBin = findServerBin();
    if (serverBin == null) {
      return this;
    }
    log.debug("Using Batmass Thermo Server bin path: {}", serverBin);
    Path binPath = serverBin;

    String serverTimeout = getServerTimeoutProp();
    if (serverTimeout != null)
      log.debug("Found timeout user-property, value: {}", serverTimeout);

    process = null;
    port = null;
    try {
      if (!OsUtils.isWindows()) {
        log.debug("OS is not Windows, assuming Mono is installed");
        cmd.add("mono");
      }
      cmd.add(binPath.toString());
      if (serverTimeout != null) {
        cmd.add("0"); // TODO: this is for old BatmassIo Servers that interpreted the first parameter as a provided port number
        cmd.add("-t"); // For new servers - add a 5 second auto-timeout server stop
        cmd.add(serverTimeout);
      }
      ProcessBuilder pb = new ProcessBuilder(cmd);
      log.debug("Trying to start Batmass Thermo Server: {}", String.join(" ", pb.command()));
      pb.redirectErrorStream(true);

      log.debug("Waiting to get TCP port number from the server");
      ExecutorService exec = Executors.newSingleThreadExecutor();

      process = pb.start();
      final Process procRef = process;
      final InputStream processOutput = process.getInputStream();

      // trying to get port number
      Pattern re = Pattern.compile("running on port:\\s*(\\d+)", Pattern.CASE_INSENSITIVE);
      Callable<Integer> callable = () -> scanStreamForPattern(processOutput, re);
      Future<Integer> portSearchTask = exec.submit(callable);
      exec.shutdown();

      // adding shutdown hook
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try {
          if (procRef != null && procRef.isAlive()) {
            procRef.destroyForcibly().waitFor();
          }
        } catch (InterruptedException e) {
          log.error("Something happened while trying to destory child processes in runtime shutdown hook", e);
        }
      }));

      try {
        int timeout = 5;
        TimeUnit unit = TimeUnit.SECONDS;
        log.debug("Waiting {} {} to get port number from server", timeout, unit);
        port = portSearchTask.get(timeout, unit);
        log.debug("Received TCP port of server: {}", port);
      } catch (TimeoutException e) {
        log.error("Did not get port information from server output within timeout.");
      } catch (InterruptedException | ExecutionException e) {
        log.error("Error occurred while waiting to get port number from server", e);
      } finally {
        if (!portSearchTask.isDone() && !portSearchTask.isCancelled()) {
          portSearchTask.cancel(true);
        }
      }

    } catch (IOException e) {
      log.error("Problmes starting process", e);
    }

    log.debug("Server process is alive: {}", process.isAlive());
    return this;
  }

  private static Integer scanStreamForPattern(InputStream is, Pattern re) {

    StringBuilder sb = new StringBuilder();
    char[] buf = new char[4096];

    try (BufferedReader br = from(is)) {
      while (!Thread.interrupted()) {
        if (br.ready()) {
          int read = br.read(buf);
          log.debug("Read {} chars from process' output", read);
          if (read == -1) {
            log.debug("Reached end of stream from process' output");
            break;
          }
          sb.append(buf, 0, read);
          log.debug("So far got from process:\n" + sb.toString());
          Integer parsedPort = findPortInString(re, sb.toString());
          if (parsedPort != null)
            return parsedPort;
        } else {
          Thread.sleep(200);
        }
      }

      return null;
    } catch (IOException | InterruptedException e) {
      log.error("Error while scanning stream", e);
    } finally {
      if (Thread.interrupted()) {
        log.debug("Search for TCP port was stopped because thread was interrupted [IF: Thread.interrupted()]");
      } else {
        log.debug("Search for TCP port was stopped because thread was interrupted: [ELSE: Thread.interrupted()]");
      }
    }
    return null;
  }

  private static Integer findPortInString(Pattern re, String line) {
    Matcher m = re.matcher(line);
    if (m.find()) {

      log.debug("Got possible port information in server's output: \"" + line + "\"");
      try {
        int parsedPort = Integer.parseInt(m.group(1));
        if (parsedPort >= 1 && parsedPort < 65536) {
          log.debug("Parsed TCP port gRPC server is running on: " + parsedPort);
          return parsedPort;
        } else {
          log.error("Parsed server port numer '{}' is out of valid range [1, 65535]", parsedPort);
        }
      } catch (Exception e) {
        log.error("Error parsing port info from server's output", e);
      }
    }
    return null;
  }
}
