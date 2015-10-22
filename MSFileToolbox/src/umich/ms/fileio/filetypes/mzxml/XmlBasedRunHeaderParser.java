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
import javax.xml.bind.Marshaller;
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
 * For example look at source of {@link MZXMLRunHeaderParser} and {@link MZMLRunHeaderParser}.
 * @author Dmitry Avtonomov
 */
public abstract class XmlBasedRunHeaderParser {


    /**
     * Be sure to include all the correct tags in this stream. For example look
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
            JAXBContext ctx = JAXBContext.newInstance(packageName, this.getClass().getClassLoader());
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
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
     * @return the offset and length in bytes in the file (file is assumed to be UTF-8 by the standard)
     * @throws umich.ms.fileio.exceptions.RunHeaderParsingException
     */
    protected OffsetLength locateRunHeader(String firstTag) throws RunHeaderParsingException {
        XMLStreamReaderImpl reader = new XMLStreamReaderImpl();
        LogHelper.setJavolutionLogLevelFatal();
        BufferedInputStream bis;
        long headerStartOffset = -1;
        long headerEndOffset = -1;
        final int MAX_OFFSET = 1024 * 1024; // safety net, 1MB should be enough for any header
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
                            headerStartOffset = reader.getLocation().getLastStartTagPos();
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        localName = reader.getLocalName();
                        if (localName.equals(firstTag)) {
                            headerEndOffset = reader.getLocation().getCharacterOffset();
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (reader.isWhiteSpace()) {
                            break;
                        }
                        break;
                }
            } while (reader.hasNext() && eventType != XMLStreamConstants.END_DOCUMENT && (headerStartOffset == -1 || headerEndOffset == -1));
            getAbstractFile().close();
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new RunHeaderParsingException("Error when parsing MS run header info", e);
        } finally {
            getAbstractFile().close();
        }
        if (headerStartOffset == -1 || headerEndOffset == -1) {
            throw new RunHeaderParsingException(String.format("Could not find <%s> opening and closing tags when parsing LCMS run header", firstTag));
        }
        return new OffsetLength(headerStartOffset, (int) (headerEndOffset - headerStartOffset));
    }
}
