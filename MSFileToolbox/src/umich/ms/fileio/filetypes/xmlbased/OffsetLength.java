package umich.ms.fileio.filetypes.xmlbased;

/**
 * Simple holder for a byte offset in file and length of an entry.
 * Used when string index to do effective reads.
 * @author Dmitry Avtonomov
 */
public class OffsetLength {
    /** Offset in bytes in a file (for use with RandomAccessFiles) */
    public final long offset;
    /** The length of data entry in bytes */
    public final int length;

    public OffsetLength(long offset, int length) {
        this.offset = offset;
        this.length = length;
    }

    @Override
    public String toString() {
        return "[offset: " + offset + ", len: " + length + "]";
    }
}
