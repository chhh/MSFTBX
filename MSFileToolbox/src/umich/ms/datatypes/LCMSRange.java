/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.datatypes;

import com.google.common.collect.Range;
import umich.ms.util.DoubleRange;

/**
 * <b>NOT IN USE YET, SUPPOSED TO REPLACE {@link LCMSDataSubset}</b>
 * Represents a subset of data in an LCMS run.
 * @author Dmitry Avtonomov
 */
public class LCMSRange {
    private final Range<Integer> scanRange;
    private final Integer msLevel;
    private final DoubleRange mzRange;


    /** Represents the whole range of scans by number in any LCMS run. */
    public static final Range<Integer> FULL_SCAN_RANGE = Range.closed(Integer.MIN_VALUE, Integer.MAX_VALUE);

    public static final LCMSRange WHOLE_RUN = new LCMSRange();
    public static final LCMSRange WHOLE_RUN_MS1 = new LCMSRange(FULL_SCAN_RANGE, 1, null);
    public static final LCMSRange WHOLE_RUN_MS2 = new LCMSRange(FULL_SCAN_RANGE, 2, null);
    public static final LCMSRange WHOLE_RUN_STRUCTURE = new LCMSRange(FULL_SCAN_RANGE, -1, null);

    /**
     * Creates a range, that includes the whole run.
     */
    protected LCMSRange() {
        scanRange = FULL_SCAN_RANGE;
        msLevel = null;
        mzRange = null;
    }

    /**
     *
     * @param scanRange null not allowed, use {@link #FULL_SCAN_RANGE} as a marker
     * for all scans in the run
     * @param msLevel null means any level, which means, that in this case you
     * are only allowed to provide null for mzRange.
     * @param mzRange if msLevel is null, then this can't be non-null.
     */
    protected LCMSRange(Range<Integer> scanRange, Integer msLevel, DoubleRange mzRange) {
        if (scanRange == null) {
            throw new IllegalArgumentException("scanRange can't be null, if you want"
                    + " to cover all scans, use LCMSRange.FULL_SCAN_RANGE");
        }
        if (msLevel == null && mzRange != null) {
            throw new IllegalArgumentException("If you provide a non-null mzRange, you must provide"
                    + " an msLevel as well.");
        }
        this.scanRange = scanRange;
        this.msLevel = msLevel;
        this.mzRange = mzRange;
    }

    /**
     * A range that will contain all the scans in an LCMS run, no filters applied.
     * @return
     */
    public static final LCMSRange create() {
        return WHOLE_RUN;
    }

    /**
     * A range that will contain all scans within the specified scan number range.
     * Note that internal scan numbers start at one.
     * @param scanRange null means the whole range of scan numbers in the run
     * @return
     */
    public static final LCMSRange create(Range<Integer> scanRange) {
        return new LCMSRange(scanRange, null, null);
    }

    /**
     * A range that will contain all scans with the scan number range, but only
     * at a specific MS-Level.
     * @param scanRange null means the whole range of scan numbers in the run
     * @param msLevel null means any ms-level
     * @return
     */
    public static final LCMSRange create(Range<Integer> scanRange, Integer msLevel) {
        return new LCMSRange(scanRange, msLevel, null);
    }

    /**
     * A range, containing all scans within the scan number range at a specific MS-Level
     * and a specific precursor range.
     * @param scanRange null means the whole range of scan numbers in the run
     * @param msLevel null means any ms-level
     * @param mzRange null means all ranges. You can't use non-null here, if
     * {@code msLevel} is null
     * @return
     */
    public static final LCMSRange create(Range<Integer> scanRange, Integer msLevel, DoubleRange mzRange) {
        return new LCMSRange(scanRange, msLevel, mzRange);
    }

    public Range<Integer> getScanRange() {
        return scanRange;
    }

    public Integer getMsLevel() {
        return msLevel;
    }

    public DoubleRange getMzRange() {
        return mzRange;
    }

    
}
