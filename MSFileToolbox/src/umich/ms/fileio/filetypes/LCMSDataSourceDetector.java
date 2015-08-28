package umich.ms.fileio.filetypes;

import java.nio.file.Path;

/**
 * Implementations of this interface should be marked as Service Providers using {@code @ProviderFor} annotation.
 * Those implementations should follow the standard {@code Service Provider} rules:<br/>
 *  - The class must implement the target interface (this interface in this case)<br/>
 *  - The class must provide a no-args constructor<br/>
 *  - The class must be public<br/>
 *
 *
 * @author Dmitry Avtonomov
 */
public interface LCMSDataSourceDetector {
    /**
     *
     * @param path
     */
    void accepts (Path path);
}
