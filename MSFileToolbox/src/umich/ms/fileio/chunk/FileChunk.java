package umich.ms.fileio.chunk;

import umich.ms.util.ByteArrayHolder;

/**
 * @author Dmitry Avtonomov
 */
public class FileChunk {
    int chunkNum;
    long offset;
    int length;
    ByteArrayHolder bah;
    boolean isUsed;

    public FileChunk(int chunkNum, long offset, int length, ByteArrayHolder bah) {
        this.chunkNum = chunkNum;
        this.offset = offset;
        this.length = length;
        this.bah = bah;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public int getChunkNum() {
        return chunkNum;
    }

    public long getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }

    public ByteArrayHolder getBah() {
        return bah;
    }
}
