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
package umich.ms.fileio.filetypes.protxml;

import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.protxml.jaxb.standard.ProteinSummary;
import umich.ms.fileio.util.jaxb.JaxbUtils;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamReader;
import java.nio.file.Path;

/**
 * A very simple parser for ProtXML files.
 *
 * @author Dmitry Avtonomov
 */
public class ProtXmlParser {
    private ProtXmlParser() {}

    public static ProteinSummary parse(Path path) throws FileParsingException {
        XMLStreamReader xsr = null;
        try {
            xsr = JaxbUtils.createXmlStreamReader(path, true);
            ProteinSummary proteinSummary = JaxbUtils.unmarshall(ProteinSummary.class, xsr);
            return proteinSummary;
        } catch (JAXBException e) {
            throw new FileParsingException(e);
        }
    }
}
