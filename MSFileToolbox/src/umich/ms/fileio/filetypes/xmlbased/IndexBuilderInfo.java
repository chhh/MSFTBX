package umich.ms.fileio.filetypes.xmlbased;

import java.io.InputStream;

/**
 * @author Dmitry Avtonomov
 */
public class IndexBuilderInfo {
    /** Global offset of the read-buffer (which is shared by multiple parsers) relative to the start of the file. */
    public final long offsetInFile;
    /**
     * Offset in the read-buffer (which is shared by multiple parsers), where this particular parser started
     * parsing.
     */
    public final long offsetInBuffer;
    //private final IndexBuilder<T> builder;
    public final InputStream is;

//    public IndexBuilderInfo(long offsetInFile, long offsetInBuffer, IndexBuilder<T> builder) {
    public IndexBuilderInfo(long offsetInFile, long offsetInBuffer, InputStream is) {
        this.offsetInFile = offsetInFile;
        this.offsetInBuffer = offsetInBuffer;
        this.is = is;
    }
}
