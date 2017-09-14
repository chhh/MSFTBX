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
import umich.ms.fileio.util.jaxb.JaxbUtils;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamReader;
import java.nio.file.Path;

/**
 * A very simple parser for PepXML files.
 */
public class PepXmlParser {
    protected static final String TAG_MSMS_RUN_SUMMARY = "msms_run_summary";
    protected static final String TAG_MSMS_PIPELINE_ANALYSIS = "msms_pipeline_analysis";

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

}
