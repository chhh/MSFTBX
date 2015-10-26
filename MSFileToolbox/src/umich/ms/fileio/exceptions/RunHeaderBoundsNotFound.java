package umich.ms.fileio.exceptions;

/**
 * Created by Dmitry Avtonomov on 2015-10-26.
 */
public class RunHeaderBoundsNotFound extends RunHeaderParsingException {
    public RunHeaderBoundsNotFound() {
    }

    public RunHeaderBoundsNotFound(String message) {
        super(message);
    }

    public RunHeaderBoundsNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public RunHeaderBoundsNotFound(Throwable cause) {
        super(cause);
    }
}
