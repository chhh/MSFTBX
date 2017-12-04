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
package umich.ms.fileio.filetypes.pepxml.example;

import umich.ms.fileio.filetypes.pepxml.jaxb.standard.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Simple parsing of all MsmsRunSummary entries in a pep.xml file. In this example the whole input pep.xml (the path to
 * which is supplied as the first command line argument) is parsed and some data from it is written to a txt file.
 *
 * @author Dmitry Avtonomov
 */
public class PepXmlExample {
    public static void main(String[] args) throws Exception {

        String pathIn = args[0];
        File fileOut = Paths.get(pathIn + ".txt").toFile();

        try (PrintWriter printWriter = new PrintWriter(fileOut)) {
            Path p = Paths.get(pathIn).toAbsolutePath();
            if (!Files.exists(p))
                throw new IllegalArgumentException("Input file doesn't exist.");
            File f = p.toFile();


            // declaring what to parse

            // run the parser
            JAXBContext ctx = JAXBContext.newInstance(MsmsPipelineAnalysis.class);
            Unmarshaller unmarshaller  = ctx.createUnmarshaller();
            Object unmarshalled = unmarshaller.unmarshal(f);
            MsmsPipelineAnalysis pipelineAnalysis = (MsmsPipelineAnalysis) unmarshalled;


            // use the unmarshalled object
            if (pipelineAnalysis.getMsmsRunSummary().isEmpty()) {
                error("MS/MS run summary was empty!");
            }

            MsmsRunSummary run = pipelineAnalysis.getMsmsRunSummary().get(0);
            List<SpectrumQuery> queries = run.getSpectrumQuery();
            if (queries.isEmpty()) {
                error("Spectrum queries table was empty!");
            }

            for (SpectrumQuery query : queries) {
                List<SearchResult> searchResult = query.getSearchResult();
                if (searchResult.isEmpty()) {
                    error(String.format("Search RESULT was empty for query #%d [spec id: %s]", query.getIndex(), query.getSpectrum()));
                }
                SearchResult result = searchResult.get(0);
                List<SearchHit> searchHit = result.getSearchHit();
                if (searchHit.isEmpty()) {
                    error(String.format("Search HIT was empty for query #%d [spec id: %s]", query.getIndex(), query.getSpectrum()));
                }
                for (SearchHit hit : searchHit) {
                    if (hit.getHitRank() > 10)
                        break;
                    String scoreStr = hit.getSearchScore().get(0).getValueStr();
                    Double scoreVal = Double.parseDouble(scoreStr);

                    printWriter.printf("%s,%d,%s,%s,%d\n",
                            query.getSpectrum(), hit.getHitRank(), hit.getPeptide(),
                            scoreStr, hit.getNumMatchedIons());
                }

            }

            printWriter.flush();
        }

    }

    private static void error(String msg) {
        System.err.println(msg);
        System.exit(1);
    }
}
