package umich.ms.fileio.filetypes.protxml;

import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.protxml.jaxb.standard.ProteinSummary;
import umich.ms.fileio.util.jaxb.JaxbUtils;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamReader;
import java.nio.file.Path;

/**
 * A very simple parser for ProtXML files.
 *
 * @author Dmitry Avtonomov
 */
public class ProtXmlParser {
    private ProtXmlParser() {}

    public static ProteinSummary parse(Path path) throws FileParsingException {
        XMLStreamReader xsr = null;
        try {
            xsr = JaxbUtils.createXmlStreamReader(path, true);
            ProteinSummary proteinSummary = JaxbUtils.unmarshall(ProteinSummary.class, xsr);
            return proteinSummary;
        } catch (JAXBException e) {
            throw new FileParsingException(e);
        }
    }
}
