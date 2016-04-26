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
package umich.ms.fileio.filetypes.protxml.example;

import umich.ms.fileio.filetypes.protxml.jaxb.standard.ProteinSummary;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Dmitry Avtonomov
 */
public class ProtXmlJaxbExample {
    public static void main(String[] args) throws JAXBException {
        String path = args[0];
        Path p = Paths.get(path).toAbsolutePath();
        File f = new File(p.toString());

        // declaring what to parse
        JAXBContext ctx = JAXBContext.newInstance(ProteinSummary.class);

        // run the parser
        Unmarshaller unmarshaller  = ctx.createUnmarshaller();
        Object unmarshalled = unmarshaller.unmarshal(f);


        // use the unmarshalled object
        ProteinSummary ps = (ProteinSummary) unmarshalled;

        System.out.printf("File: %s\nContained %d protein groups.\n", path, ps.getProteinGroup().size());

        int a = 1;
    }
}
