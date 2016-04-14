package umich.ms.fileio.filetypes.mzidentml;

import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.mzidentml.jaxb.standard.MzIdentMLType;
import umich.ms.fileio.util.jaxb.JaxbUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.nio.file.Path;

/**
 * A very simple parser for MzIdentML.
 *
 * Created by Dmitry Avtonomov on 2016-04-13.
 */
public class MzIdentMLParser {
    private MzIdentMLParser() {} // no instances

    /**
     * Will parse the whole file. Might be not very efficient memory-wise.
     * @param path path to the file to parse
     * @return auto-generated representation of the file, read the MzIdentML schema for details.
     */
    public static MzIdentMLType parse(Path path) throws FileParsingException {
        try {
            XMLStreamReader xsr = JaxbUtils.createXmlStreamReader(path, false);
            MzIdentMLType mzIdentMLType = JaxbUtils.unmarshall(MzIdentMLType.class, xsr);
            return mzIdentMLType;
        } catch (JAXBException e) {
            throw new FileParsingException(
                    String.format("JAXB parsing of MzIdentML file failed (%s)", path.toAbsolutePath().toString()), e);
        }
    }
}
