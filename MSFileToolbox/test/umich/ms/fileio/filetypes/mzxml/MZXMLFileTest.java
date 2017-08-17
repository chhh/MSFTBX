/*
 * Copyright (c) 2016 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.mzxml;

import org.junit.Test;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.StorageStrategy;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.scancollection.impl.ScanCollectionDefault;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author Dmitry Avtonomov
 */
public class MZXMLFileTest {
    List<Path> paths;

    private Path getResource(String name) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        final URI uri = classLoader.getResource("mzxml").toURI();
        final Path path = Paths.get(uri).toAbsolutePath();
        return Paths.get(path.toString(), name);
    }

    @org.junit.Before
    public void setUp() throws Exception {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            final URI uri = classLoader.getResource("mzxml").toURI();
            final Path path = Paths.get(uri);

            final DirectoryStream<Path> stream = Files.newDirectoryStream(path);
            paths = new ArrayList<>();
            for (Path p : stream) {
                //if (Files.isRegularFile(p) && p.getFileName().toString().equals("DDA-10-scans-no-index.mzXML")) {
                if (Files.isRegularFile(p)) {
                    paths.add(p);
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException("Could not set up test env in MZXMLFileTest");
        }
    }

    @org.junit.After
    public void tearDown() throws Exception {
        paths.clear();
        paths = null;
    }


    @Test
    public void buildIndexTestLocation() throws Exception {
        String file = "DDA-1-scan-broken-index.mzXML";
        int expectedCount = 1;
        long expectedOffset = 1426;
        int expectedLen = 11721;

        final Path path = getResource(file);
        if (!Files.exists(path)) {
            throw new IllegalStateException("Test file " + file + " was probably deleted.");
        }

        final MZXMLFile mzxml = new MZXMLFile(path.toString());
        final MZXMLIndex index = mzxml.fetchIndex();
        assertEquals("Wrong number of scans in the build index: " + file ,expectedCount, index.size());
        final MZXMLIndexElement elem = index.getMapByNum().firstEntry().getValue();
        assertEquals("Wrong offset: " + file, expectedOffset, elem.getOffsetLength().offset);
        assertEquals("Wrong length: " + file, expectedLen, elem.getOffsetLength().length);
    }

    @Test
    public void buildIndexTestCount() throws Exception {
        LinkedHashMap<String, Integer> file2scanCount = new LinkedHashMap<>();
        file2scanCount.put("DDA-10-scans-no-index.mzXML", 10);

        for (Map.Entry<String, Integer> e : file2scanCount.entrySet()) {
            int expectedCount = e.getValue();
            String file = e.getKey();
            final Path path = getResource(file);
            if (!Files.exists(path)) {
                throw new IllegalStateException("Test file " + file + " was probably deleted.");
            }

            final MZXMLFile mzxml = new MZXMLFile(path.toString());
            final MZXMLIndex index = mzxml.fetchIndex();
            assertEquals("Wrong number of scans returned after building index: " + file ,expectedCount, index.size());
        }
    }

    @Test
    public void parseRunInfo() throws Exception {
        for (Path p : paths) {
            System.out.printf("\n\nReading run info from file: %s\n", p.getFileName());

            final MZXMLFile mzxml = new MZXMLFile(p.toString());
            final LCMSRunInfo runInfo = mzxml.fetchRunInfo();

            System.out.printf("\tRun info: %s\n", runInfo);
        }
    }

    @Test
    public void parseWholeFile() throws Exception {
        for (Path p : paths) {
            System.out.printf("\n\nParsing whole file: %s\n", p.getFileName());

            final MZXMLFile mzxml = new MZXMLFile(p.toString());

            mzxml.setNumThreadsForParsing(null);
            mzxml.setTasksPerCpuPerBatch(1);
            mzxml.setParsingTimeout(30 * 1000);

            final MZXMLIndex index = mzxml.fetchIndex();

            IScanCollection scans = new ScanCollectionDefault(true);
            scans.setDataSource(mzxml);
            scans.loadData(LCMSDataSubset.STRUCTURE_ONLY, StorageStrategy.STRONG);
            System.out.printf("\tFound/parsed %d spectra\n", scans.getMapNum2scan().size());
        }
    }
}