package umich.ms.msfiletoolbox;

import umich.ms.datatypes.LCMSData;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.index.IndexElement;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.StorageStrategy;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.scancollection.ScanIndex;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.mzml.MZMLFile;
import umich.ms.fileio.filetypes.mzml.MZMLIndex;

import java.util.*;

/**
 * @author Dmitry Avtonomov
 */
public class Example {
    public static void main(String[] args) throws FileParsingException {

        String pathToFile = "some/path";

        // Create a concrete implementation of LCMSDataSource
        MZMLFile source = new MZMLFile(pathToFile);

        // Get the index (fetchXXX() methods will parse data from the file if it has not yet been parsed) and
        // cache it in the object for reuse.
        // You'll only need the index if you want to convert between internal scan numbers and raw scan numbers
        // in the file. Some files might have non-consecutive scan numbers, for example, but internally they'll be
        // renumbered to start from 1 and increment by one for each next scan.
        MZMLIndex idx = source.fetchIndex();
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
        data.load(LCMSDataSubset.MS1_WITH_SPECTRA);
        // or load the whole structure, but only get m/z-intensity info at MS level 2
        data.load(new LCMSDataSubset(null, null, msLevel, null));
        data.releaseMemory();

        // If you need memory management, you can also pass an instance of an object, which will be considered
        // the owner of prased data. When this object is garbage collected, this will be detected automatically
        // and corresponding spectra released.
        Object dataUser = new Object();
        data.load(LCMSDataSubset.WHOLE_RUN, dataUser);
        System.out.printf("The data is loaded and used by [%s] object.\n", System.identityHashCode(dataUser));
        // at this point dataUser might be garbage collected as it's not referenced anymore, and the data might
        // get unloaded automatically

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
        Integer parentScanNum = scan.getPrecursor().getParentScanNum();
        System.out.printf("Scan #%d (MS%d) is a child scan of #%d\n", scan.getNum(), scan.getMsLevel(), parentScanNum);

        data.releaseMemory();
    }
}
