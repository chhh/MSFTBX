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
import umich.ms.fileio.Opts;
import umich.ms.fileio.ResourceUtils;
import umich.ms.util.IntervalST;
import umich.ms.util.file.FileListing;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static umich.ms.logging.LogHelper.configureJavaUtilLogging;

/**
 * @author Dmitry Avtonomov
 */
public class MZMLFileTest {
    private static final String RESOURCE_LOCATION = "mzml";
    List<Path> paths;

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
        assertEquals("Wrong number of scans in the build index: " + file, expectedCount, index.size());
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
            assertEquals("Wrong number of scans returned after building index: " + file, expectedCount, index.size());
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
        System.out.printf("Index parsing took %.2fs\n", (timeHi - timeLo) / 1e9);

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
        System.out.printf("Parsing %s took %.4fs\n", file, (timeHi - timeLo) / 1e9);
    }

    //@Test
    public void mzmlFileOldMainMethod() throws Exception {
        configureJavaUtilLogging();

        String[] filenames = {"E:\\andy\\broken-venky-mzml\\HELA_1µg_onColumn_OT120K.mzML"};
//        String[] filenames = {"C:\\tmp\\_garbage\\HELA_1µg_onColumn_OT120K.mzML_h"};
        Integer numThreads = null;
        Integer numSpectraPerThread = 50;
        List<Path> paths = new ArrayList<>();
        for (int i = 0; i < filenames.length; i++) {
            String filename = filenames[i];
            if (i == 0) {
                try {
                    int numThreadsParsed = Integer.parseInt(filename);
                    numThreads = numThreadsParsed;
                    System.out.printf("Setting number of threads to: %d\n", numThreadsParsed);
                    continue;
                } catch (NumberFormatException e) {
                    // no worries, it's ok
                }
            }
            if (i == 1) {
                try {
                    int numSpectraPerThreadParsed = Integer.parseInt(filename);
                    numSpectraPerThread = numSpectraPerThreadParsed;
                    System.out.printf("Setting number of spectra per thread to: %d\n", numSpectraPerThreadParsed);
                    continue;
                } catch (NumberFormatException e) {
                    // it's ok
                }
            }

            Path path = Paths.get(filename).toAbsolutePath();
            if (!Files.exists(path)) {
                System.err.println("File does not exist: " + path.toString());
                System.exit(1);
            }
            if (Files.isRegularFile(path)) {
                paths.add(path);
            } else if (Files.isDirectory(path)) {
                FileListing fileListing = new FileListing(path, ".*\\.mzML");
                fileListing.setFollowLinks(false);
                fileListing.setRecursive(false);
                paths.addAll(fileListing.findFiles());
            }
        }

        IScanCollection scans;
        for (Path path : paths) {
            if (Opts.DEBUG || true) {
                double fileSize = path.toFile().length() / (1024 * 1024);
                System.out.printf("File: %s (%.2fMb)\n", path.toString(), fileSize);
            }

            MZMLFile mzml = new MZMLFile(path.toString());
            LCMSRunInfo lcmsRunInfo = mzml.fetchRunInfo();
            System.out.println(lcmsRunInfo.toString());


            mzml.setNumThreadsForParsing(numThreads);
            mzml.setTasksPerCpuPerBatch(numSpectraPerThread);
            mzml.setParsingTimeout(30 * 1000);
            long startTime = System.nanoTime();


            MZMLIndex index = mzml.fetchIndex();
            if (index.size() > 0) {
                MZMLIndexElement byNum = index.getByNum(1);
                MZMLIndexElement byRawNum = index.getByRawNum(byNum.getRawNumber());
                MZMLIndexElement byId = index.getById(byNum.getId());

            } else {
                System.err.println("Parsed index was empty!");
            }
            System.out.println("It took: " + (System.nanoTime() - startTime) / 1e9
                                       + " seconds to parse index (" + index.size() + " spectra)");


            startTime = System.nanoTime();
            scans = new ScanCollectionDefault(true);
            scans.setDataSource(mzml);
            scans.loadData(LCMSDataSubset.WHOLE_RUN, StorageStrategy.STRONG);
            System.out.println("It took: " + (System.nanoTime() - startTime) / 1e9
                                       + " seconds to parse all scans (" + scans.getScanCount() + " spectra)");

            TreeMap<Integer, IScan> num2scanMap = scans.getMapNum2scan();
            Set<Map.Entry<Integer, IScan>> num2scanEntries = num2scanMap.entrySet();
            int counterMzIntPairs = 0;
            for (Map.Entry<Integer, IScan> next : num2scanEntries) {
                IScan scan = next.getValue();
                if (scan.getSpectrum() != null) {
                    counterMzIntPairs += scan.getSpectrum().getMZs().length;
                }
            }

            IScan scan = scans.getMapNum2scan().firstEntry().getValue();
            ISpectrum spectrum = scan.fetchSpectrum();
            double[] mzs = spectrum.getMZs();
            double[] intensities = spectrum.getIntensities();
            System.out.print("First ten valus of m/z and intensity arrays of the 1st scan:\n\t");
            for (int i = 0; i < mzs.length && i < 10; i++) {
                System.out.printf("%.3f=%.1f; ", mzs[i], intensities[i]);
            }
            System.out.println();


            System.out.printf("Total number of mz-intensity pairs: %d\n", counterMzIntPairs);
            DecimalFormat formatter = new DecimalFormat("0.##E0");
            double doubleArraysSizeMb = ((double) counterMzIntPairs * 2 * 8) / (double) (1024 * 1024);
            System.out.printf("Thas is: %s (%.2fMB)\n", formatter.format(counterMzIntPairs), doubleArraysSizeMb);

            TreeMap<Integer, IntervalST<Double, TreeMap<Integer, IScan>>> mapMsLevel2rangeGroups = scans.getMapMsLevel2rangeGroups();
            Set<Map.Entry<Integer, IntervalST<Double, TreeMap<Integer, IScan>>>> entries = mapMsLevel2rangeGroups.entrySet();
            for (Map.Entry<Integer, IntervalST<Double, TreeMap<Integer, IScan>>> next : entries) {
                Integer msLevel = next.getKey();
                IntervalST<Double, TreeMap<Integer, IScan>> mzRangesAtMsLevel = next.getValue();
                System.out.printf("Tree at MS level: %d\n", msLevel);
                Iterator<IntervalST.Node<Double, TreeMap<Integer, IScan>>> iterIntervalTree = mzRangesAtMsLevel.iterator();
                while (iterIntervalTree.hasNext()) {
                    IntervalST.Node<Double, TreeMap<Integer, IScan>> range = iterIntervalTree.next();
                    System.out.printf("Interval: %s contains %d scans\n", range.getInterval().toString(), range.getValue().size());
                }

                System.out.println("=================");
                System.out.println();
            }

            TreeMap<Integer, IScan> mapNum2scan = scans.getMapNum2scan();
            int counter = 0;


            if (false) {
                ArrayList<Integer> scanNums = new ArrayList<>();
                for (Map.Entry<Integer, IScan> num2scan : mapNum2scan.entrySet()) {
                    counter++;
                    if (counter % 2 == 0) {
                        scanNums.add(num2scan.getKey());
                    }
                }
                mzml.parse(scanNums);
            }
        }
    }
}
