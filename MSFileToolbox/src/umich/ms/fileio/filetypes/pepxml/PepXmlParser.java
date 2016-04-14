package umich.ms.fileio.filetypes.pepxml;

import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsPipelineAnalysis;
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
 * A very simple parser for PepXML files.
 *
 * Created by Dmitry Avtonomov on 2016-04-13.
 */
public class PepXmlParser {
    private PepXmlParser() {}

    public static MsmsPipelineAnalysis parse(Path path) throws FileParsingException {

        try {
            XMLStreamReader xsr = JaxbUtils.createXmlStreamReader(path, true);
            MsmsPipelineAnalysis msmsPipelineAnalysis = JaxbUtils.unmarshall(MsmsPipelineAnalysis.class, xsr);
            return msmsPipelineAnalysis;
        } catch (JAXBException e) {
            throw new FileParsingException(
                    String.format("JAXB parsing of PepXML file failed (%s)", path.toAbsolutePath().toString()), e);
        }

    }
}
