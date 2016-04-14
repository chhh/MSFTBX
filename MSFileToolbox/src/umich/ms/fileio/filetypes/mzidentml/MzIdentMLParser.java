package umich.ms.fileio.filetypes.mzidentml;

import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.mzidentml.jaxb.standard.MzIdentMLType;

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
 * Helper methods for MzIdentML (.mzid) files.
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
            JAXBContext jaxb = JAXBContext.newInstance(umich.ms.fileio.filetypes.mzidentml.jaxb.standard.ObjectFactory.class);
            XMLInputFactory xif = XMLInputFactory.newFactory();
            if (!xif.isPropertySupported(XMLInputFactory.IS_NAMESPACE_AWARE))
                throw new FileParsingException(
                        "The XMLInputFactory on this system does not support non-namespace aware parsing. " +
                        "Look at the source of 'umich.ms.fileio.filetypes.mzidentml.MzIdentMLParser#parse(Path) " +
                        "method as a reference to implement something else :)");
            xif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);
            XMLStreamReader xsr = xif.createXMLStreamReader(new StreamSource(path.toFile()));
            Unmarshaller unmarshaller = jaxb.createUnmarshaller();
            JAXBElement<MzIdentMLType> jaxbElement = unmarshaller.unmarshal(xsr, MzIdentMLType.class);
            return jaxbElement.getValue();

        } catch (JAXBException | XMLStreamException e) {
            throw new FileParsingException(
                    String.format("JAXB parsing of MzIdentML file failed (%s)", path.toAbsolutePath().toString()), e);
        }
    }
}
