package umich.ms.logging;

import javolution.context.LogContext;
import org.slf4j.Logger;

/**
 * Created by dmitriya on 2015-02-09.
 */
public class LogHelper {
    private LogHelper() {
    }

    /**
     * Set Javolution log level according to the provided logger.
     * @param log SLF4J wrapper for many logging frameworks
     */
    public static final void setJavolutionLogLevel(Logger log) {
        // disable Javolution info-level logging
        if (log.isTraceEnabled()) {
            javolution.context.LogContext.enter().setLevel(LogContext.Level.DEBUG);
        } else if (log.isDebugEnabled()) {
            javolution.context.LogContext.enter().setLevel(LogContext.Level.DEBUG);
        } else if (log.isInfoEnabled()) {
            javolution.context.LogContext.enter().setLevel(LogContext.Level.INFO);
        } else if (log.isWarnEnabled()) {
            javolution.context.LogContext.enter().setLevel(LogContext.Level.WARNING);
        } else if (log.isErrorEnabled()) {
            javolution.context.LogContext.enter().setLevel(LogContext.Level.ERROR);
        } else {
            javolution.context.LogContext.enter().setLevel(LogContext.Level.FATAL);
        }
    }

    public static final void setJavolutionLogLevel(LogContext.Level level) {
        javolution.context.LogContext.enter().setLevel(level);
    }

    public static final void setJavolutionLogLevelFatal() {
        javolution.context.LogContext.enter().setLevel(LogContext.Level.FATAL);
    }
}
