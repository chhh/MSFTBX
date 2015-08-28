/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.fileio.exceptions;

/**
 * Thrown when mzML/mzXML has no index.
 * @author Dmitry Avtonomov
 */
public class IndexNotFoundException extends Exception {

    public IndexNotFoundException() {
    }

    public IndexNotFoundException(String message) {
        super(message);
    }

    public IndexNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IndexNotFoundException(Throwable cause) {
        super(cause);
    }

    public IndexNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
