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
package umich.ms.fileio.filetypes.gpmdb;

import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.gpmdb.jaxb.Bioml;
import umich.ms.fileio.util.jaxb.JaxbUtils;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamReader;
import java.nio.file.Path;

/**
 * A very simple parser for GPMDB XML files.
 *
 * @author Dmitry Avtonomov
 */
public class GPMDBParser {
    private GPMDBParser() {}

    public static Bioml parse(Path path) throws FileParsingException {
        try {
            XMLStreamReader xsr = JaxbUtils.createXmlStreamReader(path, false);
            Bioml bioml = JaxbUtils.unmarshall(Bioml.class, xsr);
            return bioml;
        } catch (JAXBException e) {
            throw new FileParsingException(e);
        }
    }
}
