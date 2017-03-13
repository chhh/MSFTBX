/* 
 * Copyright 2016 Dmitry Avtonomov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package umich.ms.logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import javolution.context.LogContext;
        
/**
 * @author Dmitry Avtonomov
 */
public class LogHelper {
    private LogHelper() {
    }

    /**
     * Set Javolution log level according to the provided logger.
     * @param log SLF4J wrapper for many logging frameworks
     */
    public static final void setJavolutionLogLevel(org.slf4j.Logger log) {
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
    
    /**
     * Configures JUL (java.util.logging) using the logging.properties file located in this
     * package. Only use this method for testing purposes, clients should configure 
     * logging themselves - that is you need to provide a logging bridge for SLF4J
     * compatible to your logging infrastructure, or use SLF4J no-op logger.
     */
    public static final void configureJavaUtilLogging() {

        try (InputStream is = LogHelper.class.getResourceAsStream("logging.properties")) {
            if (is == null)
                throw new IOException("Can't find/open logging.properties");
            LogManager logMan = LogManager.getLogManager();
            logMan.readConfiguration(is);
        } catch (final IOException e) {
            java.util.logging.Logger.getAnonymousLogger().severe(
                    "Could not load development logging.properties file using "
                    + "LogHelper.class.getResourceAsStream(\"/logging.properties\")");
            java.util.logging.Logger.getAnonymousLogger().severe(e.getMessage());
        }
    }
}
