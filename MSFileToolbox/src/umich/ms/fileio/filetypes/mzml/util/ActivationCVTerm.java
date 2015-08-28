package umich.ms.fileio.filetypes.mzml.util;

/**
 * Created by dmitriya on 2015-03-13.
 */
public class ActivationCVTerm extends CVTerm {
    public final String description;
    public final String shortName;

    public static final String UNKNOWN_METHOD = "Unknown";

    public ActivationCVTerm(String accession, String name, String description, String shortName) {
        super(accession, name);
        this.description = description;
        this.shortName = shortName;
    }

    public ActivationCVTerm(String accession, String name, String description) {
        super(accession, name);
        this.description = description;
        this.shortName = null;
    }

    public String getShortName() {
        return shortName != null ? shortName : name;
    }
}
