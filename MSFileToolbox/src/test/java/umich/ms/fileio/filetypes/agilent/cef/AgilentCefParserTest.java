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

package umich.ms.fileio.filetypes.agilent.cef;

import java.nio.file.Path;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import umich.ms.fileio.ResourceUtils;
import umich.ms.fileio.filetypes.agilent.cef.jaxb.CEF;

public class AgilentCefParserTest {

  List<Path> paths;

  @org.junit.Before
  public void setUp() throws Exception {
    paths = ResourceUtils.getResources(this.getClass(), "agilent/cef");
  }

  @org.junit.After
  public void tearDown() throws Exception {
    paths.clear();
    paths = null;
  }

  @Test
  public void parse() throws Exception {
    for (Path path : paths) {
      CEF cef = AgilentCefParser.parse(path);
      Assert.assertFalse(cef.getCompoundList().getCompound().isEmpty());
    }
  }
}
