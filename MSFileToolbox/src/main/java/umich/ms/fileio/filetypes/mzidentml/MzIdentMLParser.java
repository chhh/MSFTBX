/*
 * Copyright 2016 Dmitry Avtonomov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package umich.ms.fileio.filetypes.mzidentml;

import java.nio.file.Path;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamReader;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.util.jaxb.JaxbUtils;

/**
 * A parser for MzIdentML (mzid) files.
 */
public class MzIdentMLParser {

  private MzIdentMLParser() {
  } // no instances

  /**
   * Will parse the whole file. Might be not very efficient memory-wise.
   *
   * @param path path to the file to parse
   * @return auto-generated representation of the file, read the MzIdentML schema for details.
   */
  public static umich.ms.fileio.filetypes.mzidentml.jaxb.standard.MzIdentMLType parse(Path path)
      throws FileParsingException {
    try {
      XMLStreamReader xsr = JaxbUtils.createXmlStreamReader(path, false);
      umich.ms.fileio.filetypes.mzidentml.jaxb.standard.MzIdentMLType mzIdentMLType = JaxbUtils
          .unmarshal(umich.ms.fileio.filetypes.mzidentml.jaxb.standard.MzIdentMLType.class, xsr);
      return mzIdentMLType;
    } catch (JAXBException e) {
      throw new FileParsingException(
          String.format("JAXB parsing of MzIdentML file failed (%s)",
              path.toAbsolutePath().toString()), e);
    }
  }

}
