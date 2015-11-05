package umich.ms.fileio.chunk;

import org.apache.commons.pool2.PoolUtils;
import org.apache.commons.pool2.impl.SoftReferenceObjectPool;
import umich.ms.fileio.filetypes.util.AbstractFile;
import umich.ms.util.ByteArrayHolder;
import umich.ms.util.ByteArrayHolderFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

/**
 * @author Dmitry Avtonomov
 */
public class ChunkedFile {
    private Path path;
    /** in bytes, 8MB default. */
    SoftReferenceObjectPool<ByteArrayHolder> pool;
    private int chunkSize = 1024 * 1024 * 8;
    /** In bytes. */
    private int chunkOverlap = 512;
    LinkedList<FileChunk> chunks;

    public ChunkedFile(Path path) {
        this.path = path;
        ByteArrayHolderFactory factory = new ByteArrayHolderFactory();
        factory.setDefaultSize(chunkSize);
        pool = new SoftReferenceObjectPool<>(factory);

    }

    /**
     * Check if the file is still valid.
     */
    public void validate() throws FileNotFoundException {
        if (!Files.exists(path))
            throw new FileNotFoundException("Could not find a file under path: " + path.toAbsolutePath().toString());
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public int getChunkOverlap() {
        return chunkOverlap;
    }

    public void setChunkOverlap(int chunkOverlap) {
        this.chunkOverlap = chunkOverlap;
    }
}
