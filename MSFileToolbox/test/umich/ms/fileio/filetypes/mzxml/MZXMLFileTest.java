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
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.StorageStrategy;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.scancollection.impl.ScanCollectionDefault;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.ResourceUtils;
import umich.ms.util.DoubleRange;
import umich.ms.util.Interval1D;
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
public class MZXMLFileTest {
    private static final String RESOURCE_LOCATION = "mzxml";
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


    @Test
    public void buildIndexTestLocation() throws Exception {
        String file = "DDA-1-scan-broken-index.mzXML";
        int expectedCount = 1;
        long expectedOffset = 1426;
        int expectedLen = 11721;

        final Path path = ResourceUtils.getResource(this.getClass(), RESOURCE_LOCATION, file);
        if (!Files.exists(path)) {
            throw new IllegalStateException("Test file " + file + " was probably deleted.");
        }

        final MZXMLFile mzxml = new MZXMLFile(path.toString());
        final MZXMLIndex index = mzxml.fetchIndex();
        assertEquals("Wrong number of scans in the build index: " + file, expectedCount, index.size());
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
            final Path path = ResourceUtils.getResource(this.getClass(), RESOURCE_LOCATION, file);
            if (!Files.exists(path)) {
                throw new IllegalStateException("Test file " + file + " was probably deleted.");
            }

            final MZXMLFile mzxml = new MZXMLFile(path.toString());
            final MZXMLIndex index = mzxml.fetchIndex();
            assertEquals("Wrong number of scans returned after building index: " + file, expectedCount, index.size());
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

    //@Test
    public void oldMzxmlFileMain() throws Exception {
        configureJavaUtilLogging();

        // aggregate paths found on the command line
        String[] filenames = {};
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
                FileListing fileListing = new FileListing(path, ".*\\.mzXML");
                fileListing.setFollowLinks(false);
                fileListing.setRecursive(false);
                paths.addAll(fileListing.findFiles());
            }
        }
        // iterate over the files, parsing them
        IScanCollection scanCollection;
        for (Path path : paths) {
            double fileSize = path.toFile().length() / (1024 * 1024);
            System.out.printf("File: %s (%.2fMb)\n", path.toString(), fileSize);
            MZXMLFile mzxml = new MZXMLFile(path.toString(), false);
            mzxml.setNumThreadsForParsing(numThreads);
            mzxml.setTasksPerCpuPerBatch(numSpectraPerThread);
            mzxml.setParsingTimeout(30 * 1000);
            long startTime = System.nanoTime();
            // parse index
            MZXMLIndex idx = mzxml.fetchIndex();
            if (idx.size() > 0) {
                MZXMLIndexElement byNum = idx.getByNum(1);
                MZXMLIndexElement byRawNum = idx.getByRawNum(byNum.getRawNumber());
                MZXMLIndexElement byId = idx.getById(byNum.getId());
            } else {
                System.err.println("Parsed index was empty!");
            }

            NavigableMap<Integer, MZXMLIndexElement> index = mzxml.fetchIndex().getMapByNum();
            double timeSpentParsingIndex = (System.nanoTime() - startTime) / 1e9;
            System.out.printf("It took: %.2f seconds to parse index (%d spectra)\n", timeSpentParsingIndex, index.size());
            LCMSRunInfo lcmsRunInfo = mzxml.fetchRunInfo();
            scanCollection = null;
            boolean parseList = false;
            if (parseList) {
                // parse structure
                startTime = System.nanoTime();
                //                List<Integer> integers = Arrays.asList(index.subMap(0, 100).keySet().toArray(new Integer[0]));
                List<Integer> integers = Arrays.asList(index.keySet().toArray(new Integer[0]));
                List<IScan> parsedScans = mzxml.parse(integers);
                scanCollection = new ScanCollectionDefault(false);
                for (IScan parsedScan : parsedScans) {
                    scanCollection.addScan(parsedScan);
                }
                double timeSpentParsingStructure = (System.nanoTime() - startTime) / 1e9;
                int numSpectraStructure = scanCollection.getScanCount();
                System.out.printf("It took: %.2f seconds to parse structure of all scans (%d spectra, %.2f spec/s, %.2f Mb/s)\n",
                                  timeSpentParsingStructure, numSpectraStructure,
                                  numSpectraStructure / timeSpentParsingStructure, fileSize / timeSpentParsingStructure);
            } else {
                // parse the scans
                startTime = System.nanoTime();
                LCMSDataSubset subset = LCMSDataSubset.WHOLE_RUN;
                scanCollection = new ScanCollectionDefault(false);
                scanCollection.setDataSource(mzxml);
                scanCollection.loadData(subset, StorageStrategy.STRONG);
                boolean loadMs2 = false;
                if (loadMs2) {
                    HashSet<Integer> msLvl2 = new HashSet<>();
                    msLvl2.add(2);
                    IntervalST<Double, TreeMap<Integer, IScan>> ms2ranges = scanCollection.getMapMsLevel2rangeGroups().get(2);
                    if (ms2ranges != null) {
                        Iterator<IntervalST.Node<Double, TreeMap<Integer, IScan>>> ms2RangesIter = ms2ranges.iterator();
                        IntervalST.Node<Double, TreeMap<Integer, IScan>> firstNode = ms2RangesIter.next();
                        Interval1D<Double> interval = firstNode.getInterval();
                        DoubleRange doubleRange = DoubleRange.fromInterval1D(interval);
                        ArrayList<DoubleRange> doubleRanges = new ArrayList<>(1);
                        doubleRanges.add(doubleRange);
                        List<IScan> listMS2ScansFirstGroup = mzxml.parse(new LCMSDataSubset(null, null, msLvl2, doubleRanges));
                        int a = 1;
                        IntervalST.Node<Double, TreeMap<Integer, IScan>> secondGroup = ms2RangesIter.next();
                        List<Integer> scanNumsInSecondGroup = Arrays.asList(secondGroup.getValue().keySet().toArray(new Integer[0]));
                        List<IScan> listMS2ScansSecondGroup = mzxml.parse(scanNumsInSecondGroup);
                        a = 2;
                    }
                }
                double timeSpentParsingSpectra = (System.nanoTime() - startTime) / 1e9;
                int numSpectra = scanCollection.getScanCount();
                System.out.printf("It took: %.2f seconds to parse all scans (%d spectra, %.2f spec/s, %.2f Mb/s)\n",
                                  timeSpentParsingSpectra, numSpectra,
                                  numSpectra / timeSpentParsingSpectra, fileSize / timeSpentParsingSpectra);
            }
            // print a part of the first spectrumRef
            if (!scanCollection.getMapNum2scan().isEmpty()) {
                IScan scan = scanCollection.getMapNum2scan().firstEntry().getValue();
                ISpectrum spectrum = scan.getSpectrum();
                if (spectrum != null) {
                    double[] mzs = spectrum.getMZs();
                    System.out.print("First ten valus of m/z array of the 1st scan:\n\t");
                    for (int i = 0; i < mzs.length && i < 10; i++) {
                        System.out.printf("%.3f ", mzs[i]);
                    }
                    System.out.println();
                } else {
                    System.out.println("The first scan is there, but the spectrum has not been parsed.");
                }
            }
            // do count the total number of peaks in the file
            TreeMap<Integer, IScan> num2scanMap = scanCollection.getMapNum2scan();
            Set<Map.Entry<Integer, IScan>> num2scanEntries = num2scanMap.entrySet();
            int counterMzIntPairs = 0;
            for (Map.Entry<Integer, IScan> next : num2scanEntries) {
                IScan scan = next.getValue();
                if (scan.getSpectrum() != null) {
                    counterMzIntPairs += scan.getSpectrum().getMZs().length;
                }
            }
            System.out.printf("Total number of mz-intensity pairs: %d\n", counterMzIntPairs);
            DecimalFormat formatter = new DecimalFormat("0.##E0");
            double doubleArraysSizeMb = ((double) counterMzIntPairs * 2 * 8) / (double) (1024 * 1024);
            System.out.printf("Thas is: %s (%.2fMB in doubles)\n", formatter.format(counterMzIntPairs), doubleArraysSizeMb);
            // iterate over precursor windows, printing MS1 ranges and MSn precursor window groups
            TreeMap<Integer, IntervalST<Double, TreeMap<Integer, IScan>>> mapMsLevel2rangeGroups = scanCollection.getMapMsLevel2rangeGroups();
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
        }
    }
}
