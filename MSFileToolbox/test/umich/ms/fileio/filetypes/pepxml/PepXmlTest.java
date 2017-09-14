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

package umich.ms.fileio.filetypes.pepxml;

import org.junit.Assert;
import org.junit.Test;
import umich.ms.fileio.ResourceUtils;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsPipelineAnalysis;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsRunSummary;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.SearchSummary;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.SpectrumQuery;
import umich.ms.fileio.util.jaxb.JaxbUtils;
import umich.ms.util.XmlUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.zip.InflaterInputStream;

/**
 * @author Dmitry Avtonomov
 */
public class PepXmlTest {

    List<Path> paths;

    @org.junit.Before
    public void setUp() throws Exception {
        paths = ResourceUtils.getResources(this.getClass(), "pepxml");
    }

    @org.junit.After
    public void tearDown() throws Exception {
        paths.clear();
        paths = null;
    }

    @Test
    public void testParsing() throws Exception {

        System.out.println("Test parsing whole files.");
        for (Path path : paths) {
            String p = path.toString().toLowerCase();
            if (!p.endsWith(".zip") && !p.endsWith(".gz")) {
                // non-compressed pepxml files, just parse the whole thing at once
                System.out.printf("Parsing whole file: %s\n", path);
                final MsmsPipelineAnalysis msmsPipelineAnalysis = PepXmlParser.parse(path);

                Assert.assertFalse(msmsPipelineAnalysis.getMsmsRunSummary().isEmpty());
                for (MsmsRunSummary msmsRunSummary : msmsPipelineAnalysis.getMsmsRunSummary()) {
                    assertMsMsRunSummaryOk(msmsRunSummary);
                }
            }

            System.out.println("Done.");
        }
    }

    @Test
    public void testIteratorParsing() throws Exception {

        System.out.println("Test parsing files iteratively.");
        for (Path path : paths) {
            String p = path.toString().toLowerCase();
            if (p.endsWith(".zip") || p.endsWith(".gz")) {
                // compressed pepxml files
//                System.out.printf("Compressed file detected, parsing iteratively: %s\n", path);
//                try (InflaterInputStream iis = new InflaterInputStream(new FileInputStream(path.toString()))) {
//                    Iterator<MsmsRunSummary> it = PepXmlParser.parse(iis);
//                    while (it.hasNext()) {
//                        MsmsRunSummary msmsRunSummary = it.next();
//                        assertMsMsRunSummaryOk(msmsRunSummary);
//                    }
//                }

            } else {

                // non-compressed pepxml files, just parse the whole thing at once
                // compressed pepxml files
                System.out.printf("Parsing iteratively: %s\n", path);

                try (FileInputStream fis = new FileInputStream(path.toString())) {
                    Iterator<MsmsRunSummary> it = PepXmlParser.parse(fis);
                    while (it.hasNext()) {
                        MsmsRunSummary msmsRunSummary = it.next();
                        assertMsMsRunSummaryOk(msmsRunSummary);
                    }
                }

            }
            System.out.println("Done.");
        }
    }

    private void assertMsMsRunSummaryOk(MsmsRunSummary msmsRunSummary) {
        final List<SearchSummary> searchSummaries = msmsRunSummary.getSearchSummary();
        Assert.assertFalse("Search summaries should not be empty in test data", searchSummaries.isEmpty());

        final List<SpectrumQuery> spectrumQueries = msmsRunSummary.getSpectrumQuery();
        Assert.assertFalse("Spectrum queries should not be empty in test data", spectrumQueries.isEmpty());
    }
}
