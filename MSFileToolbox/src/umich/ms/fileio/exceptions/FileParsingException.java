/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.fileio.exceptions;

/**
 * Is thrown when anything bad happens during parsing LC/MS files.
 * @author Dmitry Avtonomov <dmitriy.avtonomov@gmail.com>
 */
public class FileParsingException extends Exception {

    public FileParsingException() {
    }

    public FileParsingException(String message) {
        super(message);
    }

    public FileParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileParsingException(Throwable cause) {
        super(cause);
    }
    
}
