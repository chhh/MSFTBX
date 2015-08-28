package umich.ms.fileio.filetypes.mzml.util;

/**
 * Just the most generic representation of a CV term.
 * Created by dmitriya on 2015-03-30.
 */
public class CVTerm {
    public final String accession;
    public final String name;

    public CVTerm(String accession, String name) {
        this.accession = accession;
        this.name = name;
    }
}
