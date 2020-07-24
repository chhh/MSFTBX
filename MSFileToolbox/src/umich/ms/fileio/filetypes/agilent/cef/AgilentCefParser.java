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
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamReader;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.agilent.cef.jaxb.CEF;
import umich.ms.util.jaxb.JaxbUtils;

public class AgilentCefParser {

  public static CEF parse(Path path) throws FileParsingException {
    XMLStreamReader xsr = null;
    try {
      xsr = JaxbUtils.createXmlStreamReader(path, false);
    } catch (JAXBException e) {
      throw new FileParsingException("Could not create XML Stream Reader for Agilent CEF file", e);
    }
    CEF cef = null;
    try {
      cef = JaxbUtils.unmarshal(CEF.class, xsr);
    } catch (JAXBException e) {
      throw new FileParsingException("Could not unmarshall CEF.class from XML", e);
    }
    return cef;
  }
}
