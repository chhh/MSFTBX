/*
 * Copyright 2016 Dmitry Avtonomov.
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
package umich.ms.fileio.filetypes.mzml;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javolution.xml.internal.stream.XMLStreamReaderImpl;
import org.apache.commons.pool2.ObjectPool;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.StorageStrategy;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.scancollection.impl.ScanCollectionDefault;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.Opts;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.xmlbased.AbstractXMLBasedDataSource;
import umich.ms.fileio.filetypes.xmlbased.IndexBuilder;

import static umich.ms.logging.LogHelper.configureJavaUtilLogging;

import umich.ms.fileio.util.FileListing;
import umich.ms.util.IntervalST;

/**
 *
 * @author Dmitry Avtonomov <dmitriy.avtonomov@gmail.com>
 */
public class MZMLFile extends AbstractXMLBasedDataSource<MZMLIndexElement, MZMLIndex> {
    private MZMLIndex index;

    public MZMLFile(String path) {
        super(path);
    }

    public ObjectPool<XMLStreamReaderImpl> getReaderPool() {
        return readerPool;
    }

    @Override
    public MZMLIndex getIndex() {
        return index;
    }

    @Override
    public MZMLIndex fetchIndex() throws FileParsingException {
        MZMLIndex tmp = index;
        if (tmp == null) {
            synchronized (this) {
                tmp = getIndex();
                if (tmp == null) {
                    tmp = parseIndex();
                    index = tmp;
                }
            }
        }
        return tmp;
    }

    @Override
    public MZMLIndex parseIndex() throws FileParsingException {
        MZMLIndexParser parser = new MZMLIndexParser(this);
        return parser.parse();
    }

    @Override
    public LCMSRunInfo parseRunInfo() throws FileParsingException {
        MZMLRunHeaderParser parser = new MZMLRunHeaderParser(this);
        return parser.parse();
    }

    @Override
    protected void releaseResources() {
        index = null;
    }

    @Override
    public MZMLMultiSpectraParser getSpectraParser(InputStream inputStream,
            LCMSDataSubset subset, ObjectPool<XMLStreamReaderImpl> readerPool, Integer numSpectra) {
        MZMLMultiSpectraParser parser;
        try {
            parser = new MZMLMultiSpectraParser(inputStream, subset, this);
        } catch (FileParsingException ex) {
            throw new IllegalStateException(ex);
        }
        parser.setNumScansToProcess(numSpectra);
        parser.setReaderPool(readerPool);
        return parser;
    }

    @Override
    public IndexBuilder<MZMLIndexElement> getIndexBuilder(IndexBuilder.Info info) {
        MZMLMultiSpectraParser parser = getSpectraParser(info.is, LCMSDataSubset.STRUCTURE_ONLY, getReaderPool(), null);
        MZMLMultiSpectraParser.MZMLIndexBuilder builder = parser.getIndexBuilder(info);
        return builder;
    }

    public static void main(String[] args) throws FileParsingException {
        configureJavaUtilLogging();
        if (args.length == 0) {
            System.out.println("Give me a dollar. And a list of mzML files. E.g.: "
                    + "\n\tjava -jar MSDataStructures.jar ./*.mzML");
            System.exit(0);
        }

        String[] filenames = args;
        Integer numThreads = null;
        Integer numSpectraPerThread = 50;
        List<Path> paths = new ArrayList<>();
        for (int i=0; i < filenames.length; i++) {
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
                int a = 1;

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

//        String path = "G:\\LTQ2_010_0195_R_040108_NCI20_04_TT.mzML";
//        MZMLFile mzml = new MZMLFile(path);
//        TreeMap<Integer, ScanIndexElement> idx = mzml.fetchIndex();
//        LCMSRunInfo lcmsRunInfo = mzml.parseRunInfo();
//        int a = 1;
    }

}
