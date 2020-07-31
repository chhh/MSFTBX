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

package umich.ms.msfiletoolbox;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsftbxInfoTest {
  private static final Logger log = LoggerFactory.getLogger(MsftbxInfoTest.class);

  @Test
  @Order(Integer.MAX_VALUE)
  public void getVersion() {
    String loc = getCurrentLocation();
    Assumptions.assumeTrue(loc != null, "Could not determine classes location");
    Path path = Paths.get(loc);

    try {
      List<String> relLocs = new ArrayList<>();
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 5; i++) {
        sb.append("../");
        relLocs.add(sb.toString());
      }
      Path gradle = null;
      for (String relLoc : relLocs) {
        Path p = path.resolve(relLoc).resolve("build.gradle");
        log.info("Looking for build.gradle in: {}", p.normalize().toAbsolutePath());
        if (Files.exists(p)) {
          gradle = p;
          break;
        }
      }

      Assertions.assertNotNull(gradle);

      log.info("Found build script: " + gradle.toString());
      List<String> lines = Files.readAllLines(gradle, StandardCharsets.UTF_8);
      String buildScript = String.join("\n", lines);
//      log.info("Read buildscript:\n{}", buildScript);
      Pattern re = Pattern.compile("version\\s*=\\s*['\"]([^'\"]+)");
      Matcher m = re.matcher(buildScript);
      if (m.find()) {
        String verInBuildScript = m.group(1);
        log.info("Found version in build script: " + verInBuildScript);
        String verInCode = MsftbxInfo.getVersion();
        log.info("Found version in code: " + verInCode);
        Assertions.assertEquals(verInBuildScript, verInCode, "Versions in build.gradle and " + MsftbxInfo.class.getCanonicalName() + " don't match");
      }

    } catch (Exception e) {
      log.error("Version test", e);
      return;
    }

    System.out.println();
  }

  private String getCurrentLocation() {
    try {
      CodeSource codeSource = MsftbxInfoTest.class.getProtectionDomain().getCodeSource();
      URL location = codeSource.getLocation();
      return extractMainFilePath(location.toURI());
    } catch (URISyntaxException e) {
      log.error("Can't get current location", e);
    }
    return null;
  }

  public static String extractMainFilePath(URI uri) {
    String scheme = uri.getScheme();
    URL uriUrl;
    try {
      uriUrl = uri.toURL();
    } catch (MalformedURLException e) {
      return null;
    }

    switch (scheme) {
      case "jar":
      case "jar:file":
        final JarURLConnection conJar;
        try {
          conJar = (JarURLConnection) uriUrl.openConnection();
        } catch (IOException e) {
          return null;
        }
        final URL url = conJar.getJarFileURL();
        try {
          URI uri1 = url.toURI();
          Path path = Paths.get(uri1);
          return path.toAbsolutePath().toString();
        } catch (Exception e) {
          return null;
        }


      case "file":
        return Paths.get(uri).toAbsolutePath().toString();

      default:
        return null;
    }
  }
}
