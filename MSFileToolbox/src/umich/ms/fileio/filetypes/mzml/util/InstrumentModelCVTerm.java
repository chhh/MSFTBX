package umich.ms.fileio.filetypes.mzml.util;

/**
 * Created by dmitriya on 2015-03-30.
 */
public class InstrumentModelCVTerm extends CVTerm {
    public final String vendor;
    public final String model;

    public InstrumentModelCVTerm(String accession, String name, String vendor, String model) {
        super(accession, name);
        this.vendor = vendor;
        this.model = model;
    }

    @Override
    public String toString() {
        return vendor + ": " + model;
    }
}
