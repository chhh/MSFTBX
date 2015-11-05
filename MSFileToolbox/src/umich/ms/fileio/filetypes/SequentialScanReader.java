package umich.ms.fileio.filetypes;

import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.scan.IScan;

/**
 * A universal interface that can be used if you want to traverse all scans in a run
 * and don't care about higher level stuff that {@link umich.ms.datatypes.scancollection.IScanCollection} provides.
 * @author Dmitry Avtonomov
 */
public interface SequentialScanReader {

    /**
     * Gets the next scan.
     * @param subset filter that determines which scans.
     * @param removeNonMatching if true, the reader won't return non-matching scans at all.
     *                          Otherwise will return them, but without spectra parsed.
     * @return null if the end of LC/MS run has been reached.
     */
    IScan next(LCMSDataSubset subset, boolean removeNonMatching);
}
