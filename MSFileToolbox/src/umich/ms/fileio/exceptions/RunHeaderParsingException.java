package umich.ms.fileio.exceptions;

/**
 * Used in areas, where run header info is parsed.
 * Created by dmitriya on 2015-02-20.
 */
public class RunHeaderParsingException extends FileParsingException {
    public RunHeaderParsingException() {
    }

    public RunHeaderParsingException(String message) {
        super(message);
    }

    public RunHeaderParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RunHeaderParsingException(Throwable cause) {
        super(cause);
    }
}
