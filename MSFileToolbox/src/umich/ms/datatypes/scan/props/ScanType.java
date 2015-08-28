package umich.ms.datatypes.scan.props;

import umich.ms.fileio.filetypes.mzml.util.PSIMSCV;
import umich.ms.util.StringUtils;

/**
 * The type of scanning used in MS.
 * @author Dmitry Avtonomov <dmitriy.avtonomov@gmail.com>
 */
public enum ScanType {
    UNKNOWN("Unknown"),
    FULL("Full"),
    SIM("SIM"),
    SRM("SRM"),
    CRM("CRM"),
    MRM("MRM"),
    ZOOM("Zoom"),
    Q1("Q1"),
    Q3("Q3");

    public String name;

    ScanType(String name) {
        this.name = name;
    }

    /**
     * Case-insensitive conversion from a string representation to values of this Enum.
     * @param name string representation (will be upper-cased and trimemd)
     * @return {@link umich.ms.datatypes.scan.props.ScanType#UNKNOWN} if there is no corresponding value in this enum
     */
    public static ScanType fromString(String name) {
        if (name == null) {
            return null;
        }
        ScanType type = StringUtils.getEnumFromString(ScanType.class, name);
        return type != null ? type : UNKNOWN;
    }

    /**
     * Get enum value from PSI-MS ontology terms.
     * @param psiMsTermNumber in the full form, e.g. "MS:1000579"
     * @return {@link umich.ms.datatypes.scan.props.ScanType#UNKNOWN} if no match could be found, null
     * if passed null in.
     */
    public static ScanType fromPSIMSOntology(String psiMsTermNumber) {
        if (psiMsTermNumber == null) {
            return null;
        }
        if (psiMsTermNumber.equalsIgnoreCase(PSIMSCV.MS_SCAN_TYPE_FULL.accession)) {
            return FULL;
        } else if (psiMsTermNumber.equalsIgnoreCase(PSIMSCV.MS_SCAN_TYPE_SIM.accession)) {
            return SIM;
        } else if (psiMsTermNumber.equalsIgnoreCase(PSIMSCV.MS_SCAN_TYPE_SRM.accession)) {
            return SRM;
        } else if (psiMsTermNumber.equalsIgnoreCase(PSIMSCV.MS_SCAN_TYPE_ZOOM.accession)) {
            return ZOOM;
        } else if (psiMsTermNumber.equalsIgnoreCase(PSIMSCV.MS_SCAN_TYPE_CRM.accession)) {
            return CRM;
        }
        return UNKNOWN;
    }
}
