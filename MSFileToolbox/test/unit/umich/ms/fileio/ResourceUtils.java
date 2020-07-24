/*
 * Copyright (c) 2017 Dmitry Avtonomov
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

package umich.ms.fileio;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ResourceUtils {

  private ResourceUtils() {
  } // no instances

  /**
   * @param clazz This class' ClassLoader will be used to locate the resource.
   * @param resourceLocation This is the `package` or `directory` where the resource resides. This
   * normally will be a subfolder of `test/resources`.
   * @param resourceName This is the name of the file that you need from that folder.
   * @return Absolute path to the resource.
   * @throws Exception If something is wrong with the URI
   */
  public static Path getResource(Class<?> clazz, String resourceLocation, String resourceName)
      throws Exception {
    ClassLoader cl = clazz.getClassLoader();
    final URI uri = cl.getResource(resourceLocation).toURI();
    final Path path = Paths.get(uri).toAbsolutePath();
    return Paths.get(path.toString(), resourceName).toAbsolutePath();
  }

  public static List<Path> getResources(Class<?> clazz, String resourceLocation) {
    try {
      ClassLoader cl = clazz.getClassLoader();
      final URI uri = cl.getResource(resourceLocation).toURI();
      final Path path = Paths.get(uri);

      final DirectoryStream<Path> stream = Files.newDirectoryStream(path);
      List<Path> paths = new ArrayList<>();
      for (Path p : stream) {
        if (Files.isRegularFile(p)) {
          paths.add(p);
        }
      }
      return paths;
    } catch (IOException | URISyntaxException e) {
      throw new IllegalStateException(String.format(
          "Could not get a list of resources for class `%s`, location `%s`", clazz.getSimpleName(),
          resourceLocation));
    }

  }
}
