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
package umich.ms.datatypes.scan.props;

import umich.ms.fileio.filetypes.mzml.util.PSIMSCV;
import umich.ms.util.StringUtils;

/**
 * The type of scanning used in MS.
 * @author Dmitry Avtonomov
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
