package umich.ms.fileio.chunk;

/**
 * Supplies chunks of raw bytes from a file.
 * @author Dmitry Avtonomov
 */
public interface FileChunkSource {

    FileChunk next();
}
