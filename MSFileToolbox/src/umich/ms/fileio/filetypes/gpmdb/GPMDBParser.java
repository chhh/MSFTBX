package umich.ms.fileio.filetypes.gpmdb;

import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.gpmdb.jaxb.Bioml;
import umich.ms.fileio.util.jaxb.JaxbUtils;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamReader;
import java.nio.file.Path;

/**
 * A very simple parser for GPMDB XML files.
 *
 * @author Dmitry Avtonomov
 */
public class GPMDBParser {
    private GPMDBParser() {}

    public static Bioml parse(Path path) throws FileParsingException {
        try {
            XMLStreamReader xsr = JaxbUtils.createXmlStreamReader(path, false);
            Bioml bioml = JaxbUtils.unmarshall(Bioml.class, xsr);
            return bioml;
        } catch (JAXBException e) {
            throw new FileParsingException(e);
        }
    }
}
