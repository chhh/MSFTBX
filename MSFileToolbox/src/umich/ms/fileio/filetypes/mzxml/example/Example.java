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
package umich.ms.fileio.filetypes.mzxml.example;

import umich.ms.datatypes.LCMSData;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.index.IndexElement;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.StorageStrategy;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.scancollection.ScanIndex;
import umich.ms.datatypes.scancollection.impl.ScanCollectionDefault;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.mzml.MZMLFile;
import umich.ms.fileio.filetypes.mzml.MZMLIndex;
import umich.ms.fileio.filetypes.mzxml.MZXMLFile;
import umich.ms.fileio.filetypes.mzxml.MZXMLIndex;
import umich.ms.fileio.filetypes.mzxml.MZXMLIndexElement;

import java.util.*;

/**
 * @author Dmitry Avtonomov
 */
public class Example {
    public static void main(String[] args) throws FileParsingException {

//        String path = "E:\\andy\\q01507.mzXML";
        String path = args[0];

        // Create a concrete implementation of LCMSDataSource
        MZXMLFile source = new MZXMLFile(path);
        // This code block is for processing mzXML containing only MS2 scans
        {
            // if a scan has zero peaks in its spectrum it will still be parsed
            source.setExcludeEmptyScans(false);
            // null means use as many cores as reported by Runtime.getRuntime().availableProcessors()
            source.setNumThreadsForParsing(null);
            // 30 sec timeout for worker threads - each worker must parse its chunk of spectra within that time
            source.setParsingTimeout(30L);


            MZXMLIndex mzxmlIndex = source.fetchIndex();


            // this is a data structure used to store scans and to navigate around the run
            ScanCollectionDefault scans = new ScanCollectionDefault();
            // softly reference spectral data, make it reclaimable by GC
            scans.setDefaultStorageStrategy(StorageStrategy.SOFT);
            // set it to automatically re-parse spectra from the file if spectra were not yet parsed or were reclaimed
            // to make auto-loading work you'll need to use IScan#fetchSpectrum() method instead of IScan#getSpectrum()
            scans.isAutoloadSpectra(true);

            // set the MZXML file as the data source for this scan collection
            scans.setDataSource(source);
            // load the whole run, with forced parsing of MS2 spectra, using default StorageStrategy.
            scans.loadData(LCMSDataSubset.MS2_WITH_SPECTRA);

            TreeMap<Integer, IScan> num2scan = scans.getMapNum2scan();
            Set<Map.Entry<Integer, IScan>> scanEntries = num2scan.entrySet();
            // we will use this index to map from internal scan numbers to raw scan numbers
            MZXMLIndex idx = source.fetchIndex();
            for (Map.Entry<Integer, IScan> scanEntry : scanEntries) {
                Integer scanNum = scanEntry.getKey();
                IScan scan = scanEntry.getValue();

                // internal scan number (1 based)
                int scanNumInternal = scan.getNum();
                // an implementation of IndexElement will know how to covert between different numbering schemes
                // it's possible to get a null here, but this should not happen
                IndexElement idxElem = idx.getByNum(scanNumInternal);
                int scanNumRaw = idxElem.getRawNumber();

                // note that we use fetchSpectrum() method here, because we've set the ScanCollection to softly
                // reference spectra
                ISpectrum spectrum = scan.fetchSpectrum();
                // just count the number of points in the spectrum
                int numPoints = spectrum.getMZs().length;
                if (scan.getMsLevel() > 1) {
                    System.out.printf("Scan #%d MS%d[%s] (raw #%d), precursor: #%s(mz: %.3f, z: %d) contained %d data points\n",
                            scanNumInternal, scan.getMsLevel(), scan.getPolarity().toString(), scanNumRaw,
                            scan.getPrecursor().getParentScanRefRaw(), scan.getPrecursor().getMzTarget(), scan.getPrecursor().getCharge(), numPoints);
                } else {
                    System.out.printf("Scan #%d MS%d[%s] (raw #%d) contained %d data points\n",
                            scanNumInternal, scan.getMsLevel(), scan.getPolarity().toString(), scanNumRaw, numPoints);
                }
                // by this point we're no longer holding a strong reference to the spectrum, it can be reclaimed
            }
        }
        System.exit(1);


        // Get the index (fetchXXX() methods will parse data from the file if it has not yet been parsed) and
        // cache it in the object for reuse.
        // You'll only need the index if you want to convert between internal scan numbers and raw scan numbers
        // in the file. Some files might have non-consecutive scan numbers, for example, but internally they'll be
        // renumbered to start from 1 and increment by one for each next scan.
        MZXMLIndex idx = source.fetchIndex();
        // info about the run
        LCMSRunInfo runInfo = source.fetchRunInfo();


        // To parse a single scan from the file (or a range of scans) we first create a predicate matching the
        // scan to be parsed.
        // For example, parse scans from 1 to 3 at MS level 2.
        Set<Integer> msLevel = Collections.singleton(2);
        LCMSDataSubset subset = new LCMSDataSubset(1, 3, msLevel, null);
        List<IScan> parsedScans = source.parse(subset);

        // If you want higher level access to data, create an LCMSData object
        LCMSData data = new LCMSData(source);
        // load the whole structure of the run, and parse all spectra for MS1 scans
        data.load(LCMSDataSubset.WHOLE_RUN);
        data.releaseMemory();

        // or load the whole structure, but only get m/z-intensity info at MS level 2
        data.load(new LCMSDataSubset(null, null, msLevel, null));
        data.releaseMemory();
        // alternatively, use this shortcut
        data.load(LCMSDataSubset.MS2_WITH_SPECTRA);
        data.releaseMemory();

        // If you need memory management, you can also pass an instance of an object, which will be considered
        // the owner of prased data. When this object is garbage collected, this will be detected automatically
        // and corresponding spectra released.
        Object dataUser = new Object();
        data.load(LCMSDataSubset.WHOLE_RUN, dataUser);
        System.out.printf("The data is loaded and used by [%s] object.\n", System.identityHashCode(dataUser));
        // at this point dataUser might be garbage collected as it's not referenced anymore, and the data might
        // get unloaded automatically
        dataUser = null; // just to be sure that we don't have a strong reference

        // If you don't want to fiddle around with memory management at all, but still want it to play nicely
        // there's one more feature - auto-loading of spectra.
        // You can parse the whole structure of the file and keep it in memory (it's rather small), and
        // just magically get the spectra whenever you need them.
        // Also set referenceing type to soft, so that garbage collector could reclaim unused spectra.
        data.load(LCMSDataSubset.STRUCTURE_ONLY);

        IScanCollection scans = data.getScans();
        scans.isAutoloadSpectra(true); // set automatic spectra loading
        scans.setDefaultStorageStrategy(StorageStrategy.SOFT); // mz-intensity data will be softly referenced
        TreeMap<Integer, ScanIndex> msLevel2index = scans.getMapMsLevel2index();
        ScanIndex ms2idx = msLevel2index.get(2); // get the index at MS level 2

        // we'll iterate by scan numbers
        TreeMap<Integer, IScan> num2scan = ms2idx.getNum2scan();
        Set<Map.Entry<Integer, IScan>> scanEntries = num2scan.entrySet();
        for (Map.Entry<Integer, IScan> scanEntry : scanEntries) {
            Integer scanNum = scanEntry.getKey();
            IScan scan = scanEntry.getValue();

            // note that we use fetchXXX() method here, because we've only parsed the structure of the file,
            // which includes scan meta-data, but not the spectra themselves
            ISpectrum spectrum = scan.fetchSpectrum();
            int scanNumInternal = scan.getNum(); // internal scan number (1 based)
            IndexElement idxElem = idx.getByNum(scanNumInternal);
            int scanNumRaw = idxElem.getRawNumber();
            int numPoints = spectrum.getMZs().length;
            System.out.printf("Scan #%d (raw #%d) contained %d data points\n", scanNumInternal, scanNumRaw, numPoints);
        }

        // You can use the ScanCollection API to navigate around the LCMS run.
        // E.g., get the number fo the first scan at ms lelvel 2
        Integer firstMS2ScanNum = scans.getMapMsLevel2index().get(2).getNum2scan().firstKey();
        IScan scan = scans.getScanByNum(firstMS2ScanNum);
        // Now get the next scan at the same MS level
        scan = scans.getNextScanAtSameMsLevel(scan);

        // Because we did parsing of the whole structure, an important method was called automagically for us:
        // ScanCollectionHelper.finalizeScanCollection(scans), which sets up parent child relations between scans
        // even if that information was not in the scan meta-data.
        // You can also call this method yourself if it you only parse a portion of the file
        String parentScanRef = scan.getPrecursor().getParentScanRefRaw();
        System.out.printf("Scan #%d (MS%d) is a child scan of {%s}\n", scan.getNum(), scan.getMsLevel(), parentScanRef);

        data.releaseMemory();
    }
}
