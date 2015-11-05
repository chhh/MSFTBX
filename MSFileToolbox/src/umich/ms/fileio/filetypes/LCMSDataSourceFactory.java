package umich.ms.fileio.filetypes;

import umich.ms.fileio.filetypes.mzml.MZMLFile;
import umich.ms.fileio.filetypes.mzxml.MZXMLFile;

import java.nio.file.Path;

/**
 * Factory for automatic detection of supported filetypes and creating corresponding {@link LCMSDataSource}s.
 *
 * @author Dmitry Avtonomov
 */
public class LCMSDataSourceFactory {
    private LCMSDataSourceFactory() { throw new IllegalStateException("No instances.");}

    /**
     * Try and create a data source from a given file path.
     * @param path
     * @return null if the provided path was not recognized
     */
    public LCMSDataSource<?> create(Path path) {
        path = path.toAbsolutePath();
        String lowerCaseName = path.getFileName().toString().toLowerCase();
        if (lowerCaseName.endsWith(".mzxml")) {
            return new MZXMLFile(path.toString());
        } else if (lowerCaseName.endsWith(".mzml")) {
            return new MZMLFile(path.toString());
        }
        return null;
     }
}
