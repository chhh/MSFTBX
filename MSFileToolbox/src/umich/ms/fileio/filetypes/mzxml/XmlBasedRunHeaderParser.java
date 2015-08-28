/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.fileio.filetypes.mzxml;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javolution.text.CharArray;
import javolution.xml.internal.stream.XMLStreamReaderImpl;
import javolution.xml.stream.XMLStreamConstants;
import javolution.xml.stream.XMLStreamException;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.fileio.exceptions.RunHeaderParsingException;
import umich.ms.fileio.filetypes.mzml.MZMLRunHeaderParser;
import umich.ms.fileio.filetypes.util.AbstractFile;
import umich.ms.fileio.filetypes.xmlbased.OffsetLength;
import umich.ms.logging.LogHelper;

/**
 * For examples look at source of {@link MZXMLRunHeaderParser} and {@link MZMLRunHeaderParser}.
 * @author Dmitry Avtonomov
 */
public abstract class XmlBasedRunHeaderParser {


    /**
     * Be sure to include all the correct tags in this stream. For examples look
     * at source of {@link MZXMLRunHeaderParser} and {@link MZMLRunHeaderParser}.
     * @param msRunLocation
     * @return
     * @throws RunHeaderParsingException
     */
    protected abstract InputStream getRunHeaderInputStream(OffsetLength msRunLocation) throws RunHeaderParsingException;

    protected abstract <T> T convertJAXBObjectToDomain(Class<T> clazz, Object unmarshalled) throws RunHeaderParsingException;

    public abstract AbstractFile getAbstractFile();

    public abstract LCMSRunInfo parse() throws RunHeaderParsingException;

    protected <T> T parseHeaderWithJAXB(Class<T> clazz, OffsetLength msRunLocation) throws RunHeaderParsingException {
        T parsedInfo;
        ClassLoader classLoaderOrig = Thread.currentThread().getContextClassLoader();
        try {
            InputStream runHeaderInputStream = getRunHeaderInputStream(msRunLocation);
            String packageName = clazz.getPackage().getName();
            Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
            JAXBContext jaxbContext = JAXBContext.newInstance(packageName, this.getClass().getClassLoader());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Object unmarshalled = unmarshaller.unmarshal(runHeaderInputStream);
            parsedInfo = convertJAXBObjectToDomain(clazz, unmarshalled);
        } catch (JAXBException e) {
            throw new RunHeaderParsingException(e);
        } finally {
            Thread.currentThread().setContextClassLoader(classLoaderOrig);
        }
        if (parsedInfo == null) {
            throw new RunHeaderParsingException("Something happened while unmarshalling MsRun header, MsRun was null.");
        }
        return parsedInfo;
    }

    /**
     * Reads the run header from the file, locating positions of {@code <firstTag>} and first {@code <lastTag>} tags.
     * @param firstTag the first tag after which run header starts
     * @param lastTag the tag before which run header ends
     * @return the offset and length in bytes in the file (file is assumed to be UTF-8 by the standard)
     * @throws umich.ms.fileio.exceptions.RunHeaderParsingException
     */
    protected OffsetLength locateRunHeader(String firstTag, String lastTag) throws RunHeaderParsingException {
        XMLStreamReaderImpl reader = new XMLStreamReaderImpl();
        LogHelper.setJavolutionLogLevelFatal();
        BufferedInputStream bis = null;
        long prevElementOffset = 0;
        long msRunOffset = -1;
        long scanOffset = -1;
        try {
            bis = getAbstractFile().getBufferedInputStream();
            reader.setInput(bis, StandardCharsets.UTF_8.name());
            int eventType;
            CharArray localName;
            // we're looking for the first <msRun> and <scan> tag occurrence.
            do {
                eventType = reader.next();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        localName = reader.getLocalName();
                        if (localName.equals(firstTag)) {
                            msRunOffset = prevElementOffset;
                        } else if (localName.equals(lastTag)) {
                            scanOffset = prevElementOffset;
                        }
                        prevElementOffset = reader.getLocation().getCharacterOffset();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        prevElementOffset = reader.getLocation().getCharacterOffset();
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (reader.isWhiteSpace()) {
                            break;
                        }
                        prevElementOffset = reader.getLocation().getCharacterOffset();
                        break;
                }
            } while (reader.hasNext() && eventType != XMLStreamConstants.END_DOCUMENT && (msRunOffset == -1 || scanOffset == -1));
            getAbstractFile().close();
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new RunHeaderParsingException("Error when parsing MS run header info", e);
        } finally {
            getAbstractFile().close();
        }
        if (msRunOffset == -1 || scanOffset == -1) {
            throw new RunHeaderParsingException(String.format("Could not find <%s> and/or <%s> tags when parsing LCMS run header", firstTag, lastTag));
        }
        return new OffsetLength(msRunOffset, (int) (scanOffset - msRunOffset));
    }
}
