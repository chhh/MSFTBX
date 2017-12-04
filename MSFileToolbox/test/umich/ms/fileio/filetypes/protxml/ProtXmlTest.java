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

package umich.ms.fileio.filetypes.protxml;

import org.junit.Assert;
import org.junit.Test;
import umich.ms.fileio.ResourceUtils;
import umich.ms.fileio.filetypes.protxml.jaxb.standard.ProteinGroup;
import umich.ms.fileio.filetypes.protxml.jaxb.standard.ProteinSummary;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Dmitry Avtonomov
 */
public class ProtXmlTest {

    List<Path> paths;

    @org.junit.Before
    public void setUp() throws Exception {
        paths = ResourceUtils.getResources(this.getClass(), "protxml");
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
                final ProteinSummary proteinSummary = ProtXmlParser.parse(Paths.get(p));

                Assert.assertFalse("Empty protein groups list", proteinSummary.getProteinGroup().isEmpty());
                for (ProteinGroup proteinGroup : proteinSummary.getProteinGroup()) {
                    Assert.assertFalse("Empty protein group encountered", proteinGroup.getProtein().isEmpty());
                }
            }

            System.out.println("Done.");
        }
    }

}
