package umich.ms.fileio.exceptions;

/**
 *
 *
 * @author Dmitry Avtonomov.
 */
public class IndexBrokenException extends Exception {
    public IndexBrokenException() {
    }

    public IndexBrokenException(String message) {
        super(message);
    }

    public IndexBrokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public IndexBrokenException(Throwable cause) {
        super(cause);
    }

    public IndexBrokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
