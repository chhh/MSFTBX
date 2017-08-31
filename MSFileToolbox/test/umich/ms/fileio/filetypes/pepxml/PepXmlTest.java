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

import java.nio.file.Path;
import java.util.List;

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

        for (Path path : paths) {
            System.out.printf("Test parsing file: %s\n", path);
            final MsmsPipelineAnalysis msmsPipelineAnalysis = PepXmlParser.parse(path);

            Assert.assertFalse(msmsPipelineAnalysis.getMsmsRunSummary().isEmpty());
            for (MsmsRunSummary msmsRunSummary : msmsPipelineAnalysis.getMsmsRunSummary()) {
                final List<SearchSummary> searchSummaries = msmsRunSummary.getSearchSummary();
                Assert.assertFalse(searchSummaries.isEmpty());

                final List<SpectrumQuery> spectrumQueries = msmsRunSummary.getSpectrumQuery();
                Assert.assertFalse(spectrumQueries.isEmpty());
            }

            System.out.println("Done.");
        }
    }
}
