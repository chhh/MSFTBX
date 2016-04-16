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

import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.pepxml.PepXmlParser;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsPipelineAnalysis;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsRunSummary;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.SpectrumQuery;
import umich.ms.fileio.filetypes.protxml.ProtXmlParser;
import umich.ms.fileio.filetypes.protxml.jaxb.standard.ProteinGroup;
import umich.ms.fileio.filetypes.protxml.jaxb.standard.ProteinSummary;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Dmitry Avtonomov
 */
public class ProtXmlSimpleExample {
    public static void main(String[] args) throws FileParsingException {
        if (args.length < 1)
            throw new IllegalArgumentException("You must specify the filepath using command line arguments");
        Path path = Paths.get(args[0]);


        // a single call to parse the whole file
        ProteinSummary proteinSummary = ProtXmlParser.parse(path);


        List<ProteinGroup> proteinGroups = proteinSummary.getProteinGroup();
        System.out.printf("Processing ProtXML: [%s]\n", path.toString());
        for (ProteinGroup pg : proteinGroups) {
            System.out.printf("Protein group: [%s] contains %d proteins and has %.4f probability\n",
                    pg.getGroupNumber(), pg.getProtein().size(), pg.getProbability());
        }

        System.out.println();
    }
}
