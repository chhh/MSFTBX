/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.fileio.exceptions;

/**
 * An indicator, that file parsing had troubles because of not enough memory.
 * @author Dmitry Avtonomov
 */
public class OutOfMemoryException extends Exception {

    public OutOfMemoryException() {
    }

    public OutOfMemoryException(String message) {
        super(message);
    }

    public OutOfMemoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfMemoryException(Throwable cause) {
        super(cause);
    }

    public OutOfMemoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
