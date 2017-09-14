/* 
 * Copyright 2017 Dmitry Avtonomov.
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
package umich.ms.fileio.filetypes.pepxml;

import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsPipelineAnalysis;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsRunSummary;
import umich.ms.fileio.util.jaxb.JaxbUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A very simple parser for PepXML files.
 */
public class PepXmlParser implements Iterable<MsmsPipelineAnalysis> {


    private PepXmlParser() {}

    /**
     * The simplest method to parse the whole MsmsPipelineAnalysis from a file.
     * @param path Path to the file
     * @return  MsmsPipelineAnalysis
     * @throws FileParsingException
     */
    public static MsmsPipelineAnalysis parse(Path path) throws FileParsingException {

        try {
            XMLStreamReader xsr = JaxbUtils.createXmlStreamReader(path, false);
            MsmsPipelineAnalysis msmsPipelineAnalysis = JaxbUtils.unmarshall(MsmsPipelineAnalysis.class, xsr);
            return msmsPipelineAnalysis;
        } catch (JAXBException e) {
            throw new FileParsingException(
                    String.format("JAXB parsing of PepXML file failed (%s)", path.toAbsolutePath().toString()), e);
        }
    }

//    public static Iterable<MsmsPipelineAnalysis> iterable(Path path) throws FileParsingException {
//
//        try (FileInputStream fis = new FileInputStream(path.toFile())) {
//            // we'll manually iterate over msmsRunSummaries - won't need so much memory
//            // at once for processing large files.
//            JAXBContext ctx = JAXBContext.newInstance(MsmsRunSummary.class);
//            Unmarshaller unmarshaller = ctx.createUnmarshaller();
//
//            XMLInputFactory xif = XMLInputFactory.newFactory();
//
//            StreamSource ss = new StreamSource(fis);
//            XMLStreamReader xsr = xif.createXMLStreamReader(ss);
//
//
//            while (advanceReaderToNextRunSummary(xsr)) {
//                // we've advanced to the next MsmsRunSummary in the file
//                //long timeLo = System.nanoTime();
//                JAXBElement<MsmsRunSummary> unmarshalled = unmarshaller.unmarshal(xsr, MsmsRunSummary.class);
//                //long timeHi = System.nanoTime();
//                //System.out.printf("Unmarshalling took %.4fms (%.2fs)\n", (timeHi-timeLo)/1e6, (timeHi-timeLo)/1e9);
//                MsmsRunSummary runSummary = unmarshalled.getValue();
//                if (runSummary.getSpectrumQuery().isEmpty()) {
//                    String msg = String.format("Parsed msms_run_summary was empty for " +
//                                    "'%s', summary base_name '%s'",
//                            path.toUri().toString(), runSummary.getBaseName());
//                    //System.out.println(msg);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            throw new FileParsingException("Input file not found", e);
//        } catch (IOException | JAXBException | XMLStreamException e) {
//            throw new FileParsingException("Something happened while iterating over MsmsPipelineAnalysis");
//        }
//
//    }

    private static boolean advanceReaderToNextRunSummary(XMLStreamReader xsr) throws XMLStreamException {
        do {
            if (xsr.next() == XMLStreamConstants.END_DOCUMENT)
                return false;
        } while (!(xsr.isStartElement() && xsr.getLocalName().equals("msms_run_summary")));

        return true;
    }

    @Override
    public Iterator<MsmsPipelineAnalysis> iterator() {
        return null;
    }
}
