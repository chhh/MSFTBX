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

package umich.ms.fileio.filetypes.mzml;

import org.junit.Test;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.StorageStrategy;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.scancollection.impl.ScanCollectionDefault;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.ResourceUtils;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Dmitry Avtonomov
 */
public class MZMLFileTest {
    List<Path> paths;
    private static final String RESOURCE_LOCATION = "mzml";

    @org.junit.Before
    public void setUp() throws Exception {
        paths = ResourceUtils.getResources(this.getClass(), RESOURCE_LOCATION);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        paths.clear();
        paths = null;
    }

    @org.junit.Test
    public void parseRunInfo() throws Exception {
        for (Path p : paths) {
            System.out.printf("\n\nReading run info from file: %s\n", p.getFileName());

            final MZMLFile mzml = new MZMLFile(p.toString());
            final LCMSRunInfo runInfo = mzml.fetchRunInfo();

            System.out.printf("\tRun info: %s\n", runInfo);
        }
    }

    @org.junit.Test
    public void parseWholeFile() throws Exception {
        for (Path p : paths) {
            System.out.printf("\n\nParsing whole file: %s\n", p.getFileName());

            final MZMLFile mzml = new MZMLFile(p.toString());

            mzml.setNumThreadsForParsing(null);
            mzml.setTasksPerCpuPerBatch(1);
            mzml.setParsingTimeout(30 * 1000);

            final MZMLIndex index = mzml.fetchIndex();

            IScanCollection scans = new ScanCollectionDefault(true);
            scans.setDataSource(mzml);
            scans.loadData(LCMSDataSubset.STRUCTURE_ONLY, StorageStrategy.STRONG);
            System.out.printf("\tFound/parsed %d spectra\n", scans.getMapNum2scan().size());
        }
    }

    @Test
    public void buildIndexTestLocation() throws Exception {
        String file = "emptyScan.mzML";
        int expectedCount = 1;
        long expectedOffset = 4224;
        int expectedLen = 4754;

        final Path path = ResourceUtils.getResource(this.getClass(), RESOURCE_LOCATION, file);

        final MZMLFile mzml = new MZMLFile(path.toString());
        final MZMLIndex index = mzml.fetchIndex();
        assertEquals("Wrong number of scans in the build index: " + file ,expectedCount, index.size());
        final MZMLIndexElement elem = index.getMapByNum().firstEntry().getValue();
        assertEquals("Wrong offset: " + file, expectedOffset, elem.getOffsetLength().offset);
        assertEquals("Wrong length: " + file, expectedLen, elem.getOffsetLength().length);
    }

    @Test
    public void buildIndexTestCount() throws Exception {
        LinkedHashMap<String, Integer> file2scanCount = new LinkedHashMap<>();
        //file2scanCount.put("RawCentriodCidWithMsLevelInRefParamGroup.mzML", 102);
        file2scanCount.put("RawCentriodCidWithMsLevelInRefParamGroup_BOM_formatted.mzML", 102);

        for (Map.Entry<String, Integer> e : file2scanCount.entrySet()) {
            int expectedCount = e.getValue();
            String file = e.getKey();
            final Path path = ResourceUtils.getResource(this.getClass(), RESOURCE_LOCATION, file);

            final MZMLFile mzml = new MZMLFile(path.toString());
            final MZMLIndex index = mzml.fetchIndex();
            assertEquals("Wrong number of scans returned after building index: " + file ,expectedCount, index.size());
        }
    }

    //@org.junit.Test
    public void parseWholeFileSpeed() throws Exception {
        //String file = "C:\\data\\andy\\q01507.mzML";
        //String file = "C:\\data\\andy\\no-index\\q01507-broken-index.mzML";
        String file = "C:\\data\\andy\\20161207_200ng_HeLa_DIA_VarTest.mzML";
        //String file = "C:\\projects\\batmass\\MSFTBX\\MSFileToolbox\\test\\resources\\mzml\\tiny.pwiz.idx.mzML";

        System.out.printf("Processing file: %s\n", file);
        final MZMLFile mzml = new MZMLFile(file);
        mzml.setNumThreadsForParsing(null);
        mzml.setTasksPerCpuPerBatch(5);
        mzml.setParsingTimeout(30 * 1000);

        long timeLo = System.nanoTime();
        final MZMLIndex index = mzml.fetchIndex();
        long timeHi = System.nanoTime();
        System.out.printf("Index parsing took %.2fs\n", (timeHi-timeLo)/1e9);

        timeLo = System.nanoTime();
        IScanCollection scans = new ScanCollectionDefault(true);
        scans.setDataSource(mzml);
        //scans.loadData(LCMSDataSubset.WHOLE_RUN, StorageStrategy.STRONG);
        scans.loadData(LCMSDataSubset.STRUCTURE_ONLY, StorageStrategy.STRONG);
        double sum = 0;
        final Set<Map.Entry<Integer, IScan>> entries = scans.getMapNum2scan().entrySet();

        int counter = 0;
        for (Map.Entry<Integer, IScan> entry : entries) {
            final IScan scan = entry.getValue();
            final ISpectrum spec = scan.fetchSpectrum();
            if (spec.getMZs().length > 0) {
                sum += spec.getMZs()[0];
            }
            if (spec.getIntensities().length > 0) {
                sum += spec.getIntensities()[0];
            }
            counter++;
            if (counter % 100 == 0)
                System.out.printf("Done processing %d\n", entry.getKey());
        }
        timeHi = System.nanoTime();
        System.out.printf("Sum is %.4f\n", sum);
        System.out.printf("Parsing %s took %.4fs\n", file, (timeHi-timeLo)/1e9);
    }

}
