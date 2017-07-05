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
package umich.ms.datatypes.scan;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import umich.ms.datatypes.scan.props.*;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.spectrum.ISpectrum;

/**
 * The most general representation of a Scan.
 *
 * Author: Dmitry Avtonomov (dmitriya)
 */
public abstract class AbstractScan implements IScan {
    /** Serial number of this scan in an LC/MS run */
    protected int num;
    /** If this scan was added to a scan collection, this field should reference it */
    protected IScanCollection scans;
    /** If this is an MS1 scan, this field is left null, indicating there was no precursor */
    protected PrecursorInfo precursor;
    /** If a file contains injection information, it should be put here, otherwise this field might be null. */
    protected InjectionInfo injectionInfo;
    /** A list of MS/MS scans, that originated from this one */
    protected List<Integer> childScans;
    /** Retention time in minutes*/
    protected Double rt;
    protected Integer msLevel;
    protected Polarity polarity;
    /** Should be a reference to one of the instruments listed in the ScanCollection */
    protected Instrument instrument;
    /** Data might come already centroided out of the instrument or post-processing algorithms */
    protected Boolean isCentroided;
    /** Type of scan (Full, Zoom, SIM, etc.). If the file had info about scan type, this field should be assigned a
     * {@link umich.ms.datatypes.scan.props.ScanType} value.
     * Use {@link umich.ms.datatypes.scan.props.ScanType#fromString(String)} to parse a string representation, which
     * will give you ScanType.UNKNOWN, if the representation could not be parsed. If the value is not present in the
     * file, leave this field {@code null}.
     * */
    protected ScanType scanType;
    /** m/z of the most intense peak */
    protected Double basePeakMz;
    /** Intensity of the most intense peak */
    protected Double basePeakIntensity;
    /** Total ion current */
    protected Double tic;
    /** Note that this number might not correspond to the lowest actual m/z in the spectrumRef.
     Instruments often measure spectra a little wider, than requested by acquisition method. */
    protected Double scanMzWindowLower;
    /** Note that this number might not correspond to the highest actual m/z in the spectrumRef.
     If the spectrumRef was centroided, it's highly likely, that there were no data points near
     the upper m/z acquisition limit defined by the instrument acquisition method. */
    protected Double scanMzWindowUpper;
    protected StorageStrategy storageStrategy;


    public static final DecimalFormat FMT_DOUBLE_2_DIGITS_AFTER_DOT = new DecimalFormat("0.00");

    /**
     * Creates a scan with STRONG storage strategy.
     * @param num
     */
    protected AbstractScan(int num) {
        this.num = num;
        this.precursor = null;
        this.childScans = null;
        this.rt = null;
        this.msLevel = null;
        this.polarity = null;
        this.instrument = null;
        this.isCentroided = null;
        this.scanType = null;
        this.basePeakMz = null;
        this.basePeakIntensity = null;
        this.tic = null;
        this.scanMzWindowLower = null;
        this.scanMzWindowUpper = null;
        this.storageStrategy = StorageStrategy.STRONG;
        this.scans = null;
    }

    @Override
    public InjectionInfo getInjectionInfo() {
        return injectionInfo;
    }

    @Override
    public void setInjectionInfo(InjectionInfo injectionInfo) {
        this.injectionInfo = injectionInfo;
    }

    @Override
    public int getNum() {
        return num;
    }

    @Override
    public PrecursorInfo getPrecursor() {
        return precursor;
    }

    @Override
    public void setPrecursor(PrecursorInfo precursor) {
        this.precursor = precursor;
    }

    @Override
    public ScanType getScanType() {
        return scanType;
    }

    @Override
    public void setScanType(ScanType scanType) {
        this.scanType = scanType;
    }

    @Override
    public List<Integer> getChildScans() {
        return childScans;
    }

    @Override
    public void setChildScans(List<Integer> childScans) {
        this.childScans = childScans;
    }

    @Override
    public Double getRt() {
        return rt;
    }

    @Override
    public void setRt(Double rt) {
        this.rt = rt;
    }

    @Override
    public Integer getMsLevel() {
        return msLevel;
    }

    @Override
    public void setMsLevel(Integer msLevel) {
        this.msLevel = msLevel;
    }

    @Override
    public Polarity getPolarity() {
        return polarity;
    }

    @Override
    public void setPolarity(Polarity polarity) {
        this.polarity = polarity;
    }

    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public Boolean getCentroided() {
        return isCentroided;
    }

    @Override
    public void setCentroided(Boolean centroided) {
        isCentroided = centroided;
    }

    public Double getBasePeakMz() {
        return basePeakMz;
    }

    @Override
    public void setBasePeakMz(Double basePeakMz) {
        this.basePeakMz = basePeakMz;
    }

    @Override
    public Double getBasePeakIntensity() {
        return basePeakIntensity;
    }

    @Override
    public void setBasePeakIntensity(Double basePeakIntensity) {
        this.basePeakIntensity = basePeakIntensity;
    }

    @Override
    public Double getTic() {
        return tic;
    }

    @Override
    public void setTic(Double tic) {
        this.tic = tic;
    }

    @Override
    public Double getScanMzWindowLower() {
        return scanMzWindowLower;
    }

    @Override
    public void setScanMzWindowLower(Double scanMzWindowLower) {
        this.scanMzWindowLower = scanMzWindowLower;
    }

    @Override
    public Double getScanMzWindowUpper() {
        return scanMzWindowUpper;
    }

    @Override
    public void setScanMzWindowUpper(Double scanMzWindowUpper) {
        this.scanMzWindowUpper = scanMzWindowUpper;
    }

    @Override
    public Boolean isCentroided() {
        return isCentroided;
    }

    @Override
    public StorageStrategy getStorageStrategy() {
        return storageStrategy;
    }

    @Override
    public IScanCollection getScanCollection() {
        return scans;
    }

    @Override
    public void setScanCollection(IScanCollection scans) {
        this.scans = scans;
    }

    @Override
    public void setStorageStrategy(StorageStrategy storageStrategy) {
        if (storageStrategy == null) {
            throw new NullPointerException("StorageStrategy can't be null");
        }
        if (this.storageStrategy != storageStrategy) {
            this.storageStrategy = storageStrategy;
        }
        setSpectrumImpl(getSpectrum());
    }

    @Override
    public final void setSpectrum(ISpectrum spectrum, boolean forceOverrideMinMaxSumVals) {
        if (spectrum == null && forceOverrideMinMaxSumVals) {
            throw new IllegalArgumentException(String.format(
                    "If you force override min/max values, the spectrumRef must be non-null. Scan MS%d #%d @ %.3f",
                    getMsLevel(), getNum(), getRt()));
        }
        if (forceOverrideMinMaxSumVals) {
            this.basePeakIntensity = spectrum.getMaxInt();
            this.basePeakMz = spectrum.getMaxIntMz();
            // only set these values if they've not been set previously, because these are isntrument level settings,
            // we're only setting it here in case we could not parse scan-ranges from the raw LC/MS file.
            if (this.scanMzWindowLower == null) {
                this.scanMzWindowLower = spectrum.getMinMZ();
            }
            if (this.scanMzWindowUpper == null) {
                this.scanMzWindowUpper = spectrum.getMaxMZ();
            }
            this.tic = spectrum.getSumInt();
        }
        setSpectrumImpl(spectrum);
    };

    /**
     * Method was added for backwards compatibility, consider using the full signature version
     * {@link #setSpectrum(umich.ms.datatypes.spectrum.ISpectrum, boolean)}.
     * @param spectrum
     */
    public void setSpectrum(ISpectrum spectrum) {
        setSpectrum(spectrum, true);
    }

    /**
     * The implementation of {@link IScan#setSpectrum(umich.ms.datatypes.spectrum.ISpectrum, boolean) }
     * in this base class will set all the Scan-level variables for you according to the
     * spectrumRef provided, but you need to define how to store the spectrumRef itself.
     * @param spectrum
     */
    protected abstract void setSpectrumImpl(ISpectrum spectrum);

    @Override
    public String toString() {
        return new StringBuilder(64)
                .append("Scan #").append(getNum()).append(":MS").append(getMsLevel())
                .append("[").append(getPolarity().toString()).append("]")
                .append("@").append(FMT_DOUBLE_2_DIGITS_AFTER_DOT.format(getRt())).append("min")
                .toString();
    }
}
