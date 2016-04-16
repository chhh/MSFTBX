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
package umich.ms.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.util.DoubleRange;

/**
 * Represents a subset of a ScanCollection.
 * Created by dmitriya on 2015-03-04.
 */
public class LCMSDataSubset implements Serializable {
    private static final long serialVersionUID = 7992060642981830599L;

    Integer scanNumLo;
    Integer scanNumHi;
    Set<Integer> msLvls;
    List<DoubleRange> mzRanges;

    private volatile int hashCode;

    /** Sets up the parsing parameters to parse all the scans and spectra in a run. */
    public static final LCMSDataSubset WHOLE_RUN;
    /** Sets up the parsing parameters to parse all scans, but spectra only for MS1 scans. */
    public static final LCMSDataSubset MS1_WITH_SPECTRA;
    /** Sets up the parsing parameters to parse all scans, but spectra only for MS2 scans. */
    public static final LCMSDataSubset MS2_WITH_SPECTRA;
    /** Sets up the parsing parameters to parse only scans for the whole run, no spectra. */
    public static final LCMSDataSubset STRUCTURE_ONLY;

    static {



        WHOLE_RUN = new LCMSDataSubset();

        Set<Integer> msLvls1;
        msLvls1 = new HashSet<>(1);
        msLvls1.add(1);
        MS1_WITH_SPECTRA = new LCMSDataSubset(null, null, msLvls1, null);

        Set<Integer> msLvls2;
        msLvls2 = new HashSet<>(1);
        msLvls2.add(2);
        MS2_WITH_SPECTRA = new LCMSDataSubset(null, null, msLvls2, null);

        Set<Integer> msLvlsEmpty = Collections.emptySet();
        STRUCTURE_ONLY = new LCMSDataSubset(null, null, msLvlsEmpty, null);
    }

    public LCMSDataSubset() {
        scanNumLo = null;
        scanNumHi = null;
        msLvls = null;
        mzRanges = null;
    }

    /**
     *
     * @param scanNumLo null means from the beginning
     * @param scanNumHi null means to the end
     * @param msLvls null means any MS-level. If you provide an empty set, then
     * no scan can match this expression.
     * @param mzRanges null means any. If you provide an empty set, then
     * no scan can match this expression.
     */
    public LCMSDataSubset(Integer scanNumLo, Integer scanNumHi, Set<Integer> msLvls, List<DoubleRange> mzRanges) {
        this.scanNumLo = scanNumLo;
        this.scanNumHi = scanNumHi;
        this.msLvls = msLvls;
        this.mzRanges = mzRanges;
    }

    public Integer getScanNumLo() {
        return scanNumLo;
    }

    public void setScanNumLo(Integer scanNumLo) {
        this.scanNumLo = scanNumLo;
    }

    public Integer getScanNumHi() {
        return scanNumHi;
    }

    public void setScanNumHi(Integer scanNumHi) {
        this.scanNumHi = scanNumHi;
    }

    public Set<Integer> getMsLvls() {
        return msLvls;
    }

    public void setMsLvls(Set<Integer> msLvls) {
        this.msLvls = msLvls;
    }

    public List<DoubleRange> getMzRanges() {
        return mzRanges;
    }

    public void setMzRanges(List<DoubleRange> mzRanges) {
        this.mzRanges = mzRanges;
    }

    /**
     * In some cases we don't have the lower/upper mz window (for MS1 this
     * requires either this data to be in scan meta info, or the
     * spectrumRef to be parsed). In this case the method returns TRUE.
     * @param scan
     * @return
     */
    public boolean isInSubset(IScan scan) {
        int num = scan.getNum();
        if (scanNumLo != null) {
            if (num < scanNumLo) {
                return false;
            }
        }
        if (scanNumHi != null) {
            if (num > scanNumHi) {
                return false;
            }
        }
        Integer msLevel = scan.getMsLevel();
        if (msLvls != null) {
            if (!msLvls.contains(msLevel)) {
                return false;
            }
        }
        DoubleRange range;
        if (mzRanges != null) {
            if (msLevel == 1) {
                if (scan.getScanMzWindowLower() == null || scan.getScanMzWindowUpper() == null) {
                    throw new IllegalStateException(String.format(
                            "Could not check if scan #%d (MS%d) is in LCMSDataSubset, as lower/upper m/z range was not present in the scan.", num, msLevel));
                }
                range = new DoubleRange(scan.getScanMzWindowLower(), scan.getScanMzWindowUpper());

            } else {
                if (scan.getPrecursor() == null) {
                    throw new IllegalStateException(String.format(
                            "Could not check if scan #%d (MS%d) is in LCMSDataSubset, as precursor info was not present in the scan.", num, msLevel));
                }
                range = new DoubleRange(scan.getPrecursor().getMzRangeStart(), scan.getPrecursor().getMzRangeEnd());
            }
            for (DoubleRange listedRange : mzRanges) {
                if (range.overlapRelative(listedRange) < IScanCollection.MIN_GROUP_OVERLAP) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if data ranges described by this subset fully contain ranges
     * specified in another set.<br/>
     * According to this definition, for example {@link #WHOLE_RUN} contains
     * any other subset, including itself.
     * @param other
     * @return
     */
    public boolean contains(LCMSDataSubset other) {

        // compare ms levels
        Set<Integer> msLvlsThis = getMsLvls();
        Set<Integer> msLvlsThat = other.getMsLvls();
        // if ms levels are null, this definitely matches any other subset
        // so we need to check msLevelsThis frist!
        if (msLvlsThis != null && msLvlsThat != null) {
            if (!msLvlsThis.containsAll(msLvlsThat)) {
                return false;
            }
        }

        // compare mz ranges
        List<DoubleRange> mzRangesThis = getMzRanges();
        List<DoubleRange> mzRangesThat = other.getMzRanges();
        if (mzRangesThis != null && mzRangesThat != null) {
            if (!mzRangesThis.containsAll(mzRangesThat)) {
                return false;
            }
        }

        // compare scan number ranges
        Integer scanNumLoThis = getScanNumLo();
        Integer scanNumLoThat = other.getScanNumLo();
        if (scanNumLoThis != null && scanNumLoThat != null) {
            if (scanNumLoThis > scanNumLoThat) {
                return false;
            }
        }
        Integer scanNumHiThis = getScanNumHi();
        Integer scanNumHiThat = other.getScanNumHi();
        if (scanNumHiThis != null && scanNumHiThat != null) {
            if (scanNumHiThis < scanNumHiThat) {
                return false;
            }
        }
        return true;
    }

    /**
     * Doesn't modify original values, always returns a new one.
     * @param other
     * @return a new instance of LCMSDataSubset, even if this subset
     * {@link #contains(umich.ms.datatypes.LCMSDataSubset) } the other one.
     */
    public LCMSDataSubset merge(LCMSDataSubset other) {
        LCMSDataSubset merged = new LCMSDataSubset();

        Set<Integer> msLvlsThis = getMsLvls();
        Set<Integer> msLvlsThat = other.getMsLvls();
        // only merge if both are not null, otherwise null signifies the whole
        // run, so we can keep it null in the merged version
        if (msLvlsThis != null && msLvlsThat != null) {
            HashSet<Integer> mergedMsLvls = new HashSet<>(msLvlsThis);
            mergedMsLvls.addAll(msLvlsThat);
            merged.setMsLvls(mergedMsLvls);
        }

        // merge mz ranges
        List<DoubleRange> mzRangesThis = getMzRanges();
        List<DoubleRange> mzRangesThat = other.getMzRanges();
        if (mzRangesThis != null && mzRangesThat != null) {
            ArrayList<DoubleRange> mergedMzRanges = new ArrayList<>(mzRangesThis);
            mergedMzRanges.addAll(mzRangesThat);
            merged.setMzRanges(mergedMzRanges);
        }

        // compare scan number ranges
        Integer scanNumLoThis = getScanNumLo();
        Integer scanNumLoThat = other.getScanNumLo();
        if (scanNumLoThis != null && scanNumLoThat != null) {
            merged.setScanNumLo(Math.min(scanNumLoThis, scanNumLoThat));
        }
        Integer scanNumHiThis = getScanNumHi();
        Integer scanNumHiThat = other.getScanNumHi();
        if (scanNumHiThis != null && scanNumHiThat != null) {
            merged.setScanNumHi(Math.max(scanNumHiThis, scanNumHiThat));
        }

        return merged;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LCMSDataSubset)) return false;

        LCMSDataSubset that = (LCMSDataSubset) o;

        if (msLvls != null ? !msLvls.equals(that.msLvls) : that.msLvls != null) return false;
        if (mzRanges != null ? !mzRanges.equals(that.mzRanges) : that.mzRanges != null) return false;
        if (scanNumHi != null ? !scanNumHi.equals(that.scanNumHi) : that.scanNumHi != null) return false;
        if (scanNumLo != null ? !scanNumLo.equals(that.scanNumLo) : that.scanNumLo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hashCode;

        if (hashCode == 0) {
            result = 7;
            result = 41 * result + (scanNumLo != null ? scanNumLo.hashCode() : Integer.MIN_VALUE / 2);
            result = 41 * result + (scanNumHi != null ? scanNumHi.hashCode() : Integer.MIN_VALUE / 3);
            result = 41 * result + (msLvls != null ? msLvls.hashCode() : Integer.MIN_VALUE / 4);
            result = 41 * result + (mzRanges != null ? mzRanges.hashCode() : Integer.MIN_VALUE / 5);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{MS-Lvls: ");
        sb = msLvls == null ? sb.append("any") : sb.append(Arrays.toString(msLvls.toArray()));
        sb.append(", ScanNumRange: ");
        sb = scanNumLo == null ? sb.append("any") : sb.append(scanNumLo);
        sb.append(" - ");
        sb = scanNumHi == null ? sb.append("any") : sb.append(scanNumHi);
        sb.append(", Precursors: ");
        sb = mzRanges == null ? sb.append("any") : sb.append(Arrays.toString(mzRanges.toArray()));
        sb.append("}");
        return sb.toString();
    }


}
