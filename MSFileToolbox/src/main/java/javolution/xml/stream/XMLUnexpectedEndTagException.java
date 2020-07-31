package javolution.xml.stream;

/**
 * Indicates that a closing tag was found without a matching opening tag.
 *
 * @author  <a href="mailto:dmitriy.avtonomov@gmail.com">Dmitry Avtonomov</a>
 * @version 1.0, October 09, 2015
 */
public class XMLUnexpectedEndTagException extends XMLStreamException {
    public XMLUnexpectedEndTagException() {
    }

    public XMLUnexpectedEndTagException(String msg) {
        super(msg);
    }

    public XMLUnexpectedEndTagException(Throwable nested) {
        super(nested);
    }

    public XMLUnexpectedEndTagException(String msg, Throwable nested) {
        super(msg, nested);
    }

    public XMLUnexpectedEndTagException(String msg, Location location, Throwable nested) {
        super(msg, location, nested);
    }

    public XMLUnexpectedEndTagException(String msg, Location location) {
        super(msg, location);
    }
}
