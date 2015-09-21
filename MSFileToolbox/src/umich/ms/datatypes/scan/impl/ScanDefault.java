package umich.ms.datatypes.scan.impl;

import java.lang.ref.Reference;
import umich.ms.datatypes.scan.AbstractScan;
import umich.ms.datatypes.scan.StorageStrategy;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.exceptions.FileParsingException;

/**
 * All the constructors produce Scans that have {@link umich.ms.datatypes.scan.StorageStrategy} set to
 * {@link umich.ms.datatypes.scan.StorageStrategy#STRONG}, it's up to the client to reset this to anything else.
 *
 * Author: Dmitry Avtonomov (dmitriya)
 * Email: dmitriy.avtonomov@gmail.com
 */
public class ScanDefault extends AbstractScan {
    protected Reference<ISpectrum> spectrumRef;


    public ScanDefault(int num) {
        super(num);
        init();
        this.spectrumRef = storageStrategy.getRef(null);
    }

    public ScanDefault(IScanCollection scans, int num) {
        this(num);
        this.scans = scans;
    }

    public ScanDefault(int num, double rt, int msLevel, boolean isCentroided) {
        this(num);
        this.rt = rt;
        this.msLevel = msLevel;
        this.isCentroided = isCentroided;
    }

    public ScanDefault(IScanCollection scans, int num, double rt, int msLevel, boolean isCentroided) {
        this(num, rt, msLevel, isCentroided);
        this.scans = scans;
    }

    private void init() {
        this.storageStrategy = StorageStrategy.STRONG;
    }

    @Override
    public Double getRt() {
//        return (double)getNum(); // TODO: WARNING: ACHTUNG: This was here to test Waters IMS data in MAP 2D
        return rt;
    }


    public IScanCollection getScans() {
        return scans;
    }

    public void setScans(IScanCollection scans) {
        this.scans = scans;
    }

    public Reference<ISpectrum> getSpectrumRef() {
        return spectrumRef;
    }

    @Override
    public ISpectrum getSpectrum() {
        return spectrumRef.get();
    }

    @Override
    public ISpectrum fetchSpectrum() throws FileParsingException {
        ISpectrum spec = spectrumRef.get();
        if (spec == null) {
            synchronized (this) {
                spec = spectrumRef.get();
                if (spec == null && scans!= null && scans.isAutoloadSpectra()) {
                    spec = scans.getDataSource().parseSpectrum(this.num);
                    if (spec != null) { // the spectrum can still be null, if this scan had no spectrum recorded.
                        setSpectrum(spec, true);
                    }
                }
            }
        }
        return spec;
    }

    @Override
    protected void setSpectrumImpl(ISpectrum spectrum) {
        this.spectrumRef = storageStrategy.getRef(spectrum);
    }


}
