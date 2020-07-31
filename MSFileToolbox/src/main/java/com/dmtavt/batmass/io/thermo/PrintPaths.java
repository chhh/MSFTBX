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

import com.github.chhh.PathUtils;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;

public class PrintPaths {

  public static void main(String[] args) {

    System.out.println("Searching for class paths");

    List<String> cps = PathUtils.getClasspaths();

    LinkedHashSet<String> classpaths = new LinkedHashSet<>();
    for (String cp : cps) {
      System.out.println("Processing classpath: " + cp);
      Path path;
      try {
        path = Paths.get(cp);
      } catch (InvalidPathException e) {
        System.err.println("Invalid path: " + cp);
        continue;
      }

      boolean exists;
      try {
        exists = Files.exists(path);
      } catch (Exception e) {
        System.err.println("Testing if file exists failed: " + path.toString());
        continue;
      }
      boolean isDir;
      try {
        isDir = Files.isDirectory(path);
      } catch (Exception e) {
        System.err.println("Testing if file is directory failed: " + path.toString());
        continue;
      }

      if (isDir) {
        classpaths.add(path.toString());
      } else {
        Path parent = path.getParent();
        classpaths.add(parent != null ? parent.toString() : ".");
      }
    }

    System.out.println("Classpath locations:\n  " + String.join("\n  ", classpaths));


    System.out.println();
    System.out.println("====================");
    System.out.println();

    System.out.println("Trying to locate JAR");

    URI jarUri = PathUtils.getCurrentJarUri();
    if (jarUri == null) {
      System.out.println("Could not determine jar URI");
    } else {
      System.out.println("Found jar URI: " + jarUri.toString());
    }

    String mainJarPath = extractMainFilePath(jarUri);
    if (mainJarPath == null) {
      System.err.println("Could not extract main JAR file path.");
    } else {
      System.out.println("Main JAR file path: " + mainJarPath.toString());
    }
  }

  public static String extractMainFilePath(URI uri) {
    String scheme = uri.getScheme();
    System.out.println("JAR URI scheme: " + scheme);
    URL uriUrl;
    try {
      uriUrl = uri.toURL();
    } catch (MalformedURLException e) {
      System.err.println("Could not convert URI to URL: " + uri.toString());
      return null;
    }

    switch (scheme) {
      case "jar":
      case "jar:file":
        final JarURLConnection conJar;
        try {
          conJar = (JarURLConnection) uriUrl.openConnection();
        } catch (IOException e) {
          System.err.println("Could not create JarUrlConnection object for URL: " + uriUrl.toString());
          return null;
        }
        final URL url = conJar.getJarFileURL();
        return url.toString();

      case "file":
        return Paths.get(uri).toString();

      default:
        System.err.println("Don't know how to process URI scheme: " + scheme);
        return null;
    }
  }

}
