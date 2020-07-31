package javolution.xml.stream;

/**
 * Indicates that an XML stream has ended abruptly without proper closing XML tags.
 *
 * @author  <a href="mailto:dmitriy.avtonomov@gmail.com">Dmitry Avtonomov</a>
 * @version 1.0, October 09, 2015
 */
public class XMLUnexpectedEndOfDocumentException extends XMLStreamException {
    public XMLUnexpectedEndOfDocumentException() {
    }

    public XMLUnexpectedEndOfDocumentException(String msg) {
        super(msg);
    }

    public XMLUnexpectedEndOfDocumentException(Throwable nested) {
        super(nested);
    }

    public XMLUnexpectedEndOfDocumentException(String msg, Throwable nested) {
        super(msg, nested);
    }

    public XMLUnexpectedEndOfDocumentException(String msg, Location location, Throwable nested) {
        super(msg, location, nested);
    }

    public XMLUnexpectedEndOfDocumentException(String msg, Location location) {
        super(msg, location);
    }
}
