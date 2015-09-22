package umich.ms.datatypes.index;

/**
 * A mapping from internal scan numbers (<b>which start at one</b>) to scan numbering in the original files.<br/>
 * mzXML files are required to provide a scan number, those scan numbers are not always continuous, e.g.:<br/>
 * <ul>
 *     <li>
 *         In mzXML data converted from AB Sciex raw files one might have the first scan number as 2037,
 *         the second as 4056 and so on, they are incrementing, but not incrementing by one.
 *     </li>
 *     <li>
 *         In mzML data converted from Agilent raw files using proteowizard (msconvert.exe) each scan has a separate
 *         "index" attribute, which starts at zero (index="0") and then and "id" which is a unique textual
 *         representation of the scan's identity of the form id="cycleNumber=1, experimentNumber=1, scanNumber=1".
 *         This scanNumber=1 is not always a unique number, it's some internal vendor specific string.
 *     </li>
 *     <li>
 *         There are many other example
 *     </li>
 * </ul>
 *
 * In order to avoid all the confusion about scan numbering, MSFileToolbox uses a separate numbering and indexing
 * scheme. Internally all scan numbers start at one and then increment by one in retention time order.<br/>
 * Numbering from the original file is maintained, be it a raw vendor file (e.g. Thermo uses normal 1 based numbering,
 * Agilent has an ordinal number stored internally in their files, but also has those 'experiment number' and
 * other stuff), or an XML based file. The ID is also retained
 *
 * @author Dmitry Avtonomov
 */
public interface IndexElement {
    /**
     * Internal scan number, starting from 1, incrementing by one for each consecutive scan.<br/>
     * This is the number you get from {@link umich.ms.datatypes.scan.IScan#getNum()}
     * @return
     */
    int getNumber();

    /**
     * The scan number from the raw file. For mzXML files this will often (not always) be the same as the internal
     * number. For mzML this will typically be 1 less, than the internal number (the value of "index" attribute of
     * a spectrum tag).
     * @return
     */
    int getRawNumber();

    /**
     * The textual representation of an ID of a scan. mzML provides those IDs by default, in mzXML there is no such
     * element, so this will return the same as {@link #getRawNumber()}.
     * @return
     */
    String getId();
}
