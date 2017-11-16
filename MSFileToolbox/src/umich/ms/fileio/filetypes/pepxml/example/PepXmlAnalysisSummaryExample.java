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

package umich.ms.fileio.filetypes.pepxml.example;

import umich.ms.fileio.filetypes.pepxml.jaxb.standard.*;
import umich.ms.util.jaxb.JaxbUtils;
import umich.ms.util.StringUtils;

import javax.xml.stream.XMLStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Unfortunately, the declarations of different "analyses" are say nothing about the elements in the XML they might
 * be contained in. Hence, the only way to parse those out of a file is
 *
 * @author Dmitry Avtonomov
 */
public class PepXmlAnalysisSummaryExample {
    public static void main(String[] args) throws Exception {

        // input file
        String pathIn = args[0];
        Path p = Paths.get(pathIn).toAbsolutePath();
        if (!Files.exists(p))
            throw new IllegalArgumentException("File doesn't exist: " + p.toString());

        //////////////////////////////////
        //
        //      Relevant part start
        //
        //////////////////////////////////

        // prepare the input stream
        final XMLStreamReader xsr = JaxbUtils.createXmlStreamReader(p, false);
        // advance the input stream to the beginning of <peptideprophet_summary>
        final boolean foundPepProphSummary = umich.ms.util.xml.XmlUtils.advanceReaderToNext(xsr, "peptideprophet_summary");
        if (!foundPepProphSummary)
            throw new IllegalStateException("Could not advance the reader to the beginning of a peptideprophet_summary tag.");

        // unmarshal
        final PeptideprophetSummary ps = JaxbUtils.unmarshal(PeptideprophetSummary.class, xsr);

        //////////////////////////////////
        //
        //      Relevant part end
        //
        //////////////////////////////////

        // use the unmarshalled object
        StringBuilder sb = new StringBuilder();
        sb.append("Input files:");
        for (InputFileType inputFile : ps.getInputfile()) {
            sb.append("\n\t").append(inputFile.getName());
            if (!StringUtils.isNullOrWhitespace(inputFile.getDirectory()))
                sb.append(" @ ").append(inputFile.getDirectory());
        }
        for (RocErrorDataType rocErrorData : ps.getRocErrorData()) {
            sb.append("\n");
            sb.append(String.format("ROC Error data (charge '%s'): \n", rocErrorData.getCharge()));
            // roc_data_points
            for (RocDataPoint rocDataPoint : rocErrorData.getRocDataPoint()) {
                sb.append(String.format("ROC min_prob=\"%.3f\" sensitivity=\"%.3f\" error=\"%.3f\" " +
                                "num_corr=\"%d\" num_incorr=\"%d\"\n",
                        rocDataPoint.getMinProb(), rocDataPoint.getSensitivity(), rocDataPoint.getError(),
                        rocDataPoint.getNumCorr(), rocDataPoint.getNumIncorr()));
            }
            // error_points
            for (ErrorPoint errroPoint : rocErrorData.getErrorPoint()) {
                sb.append(String.format("ERR error=\"%.3f\" min_prob=\"%.3f\" num_corr=\"%d\" num_incorr=\"%d\"\n",
                        errroPoint.getError(), errroPoint.getMinProb(), errroPoint.getNumCorr(), errroPoint.getNumIncorr()));
            }
        }

        System.out.println(sb.toString());
    }


    private static void error(String msg) {
        System.err.println(msg);
        System.exit(1);
    }
}
