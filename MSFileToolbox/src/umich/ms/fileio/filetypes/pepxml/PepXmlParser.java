package umich.ms.fileio.filetypes.pepxml;

import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsPipelineAnalysis;

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
 * Created by Dmitry Avtonomov on 2016-04-13.
 */
public class PepXmlParser {
    private PepXmlParser() {}

    public static MsmsPipelineAnalysis parse(Path path) throws FileParsingException {

        try {
            JAXBContext jaxb = JAXBContext.newInstance(umich.ms.fileio.filetypes.pepxml.jaxb.standard.ObjectFactory.class);


            XMLInputFactory xif = XMLInputFactory.newFactory();
            if (!xif.isPropertySupported(XMLInputFactory.IS_NAMESPACE_AWARE))
                throw new FileParsingException(
                        "The XMLInputFactory on this system does not support non-namespace aware parsing. " +
                                "Look at the source of 'umich.ms.fileio.filetypes.pepxml.PepXmlParser#parse(Path) " +
                                "method as a reference to implement something else :)");

            xif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);
            XMLStreamReader xsr = xif.createXMLStreamReader(new StreamSource(path.toFile()));
            Unmarshaller unmarshaller = jaxb.createUnmarshaller();
            JAXBElement<MsmsPipelineAnalysis> jaxbElement = unmarshaller.unmarshal(xsr, MsmsPipelineAnalysis.class);
            return jaxbElement.getValue();

        } catch (JAXBException | XMLStreamException e) {
            throw new FileParsingException(
                    String.format("JAXB parsing of PepXML file failed (%s)", path.toAbsolutePath().toString()), e);
        }

    }
}
