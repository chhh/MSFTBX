/*
 * Copyright 2016 Dmitry Avtonomov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package umich.ms.fileio.filetypes.mzxml;

import java.io.BufferedInputStream;
import java.io.IOException;
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
import umich.ms.fileio.exceptions.RunHeaderBoundsNotFound;
import umich.ms.fileio.exceptions.RunHeaderParsingException;
import umich.ms.fileio.filetypes.mzml.MZMLRunHeaderParser;
import umich.ms.fileio.util.AbstractFile;
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
            InputStream is = getRunHeaderInputStream(msRunLocation);
            String packageName = clazz.getPackage().getName();
            Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
            JAXBContext ctx = JAXBContext.newInstance(packageName, this.getClass().getClassLoader());
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            Object unmarshalled = unmarshaller.unmarshal(is);
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
     *
     * @param firstTag the first tag after which run header starts
     * @param firstTagIsStart if the opening tag is a closing tag or an opening tag
     * @param firstTagGetStartLoc if the location of the beginning of the tag must be taken
     * @param lastTag the tag up to which we need to parse.
     * @param lastTagIsStart if the last tag is an opening tag or a closing tag
     * @param lastTagGetStartLoc if the location of the beginning or end of the tag entry should be taken
     * @return the offset and length in bytes in the file (file is assumed to be UTF-8 by the standard)
     * @throws umich.ms.fileio.exceptions.RunHeaderParsingException
     */
    protected OffsetLength locateRunHeader(String firstTag, boolean firstTagIsStart, boolean firstTagGetStartLoc,
                                           String lastTag, boolean lastTagIsStart, boolean lastTagGetStartLoc) throws RunHeaderParsingException {
        LogHelper.setJavolutionLogLevelFatal();
        XMLStreamReaderImpl xsr = new XMLStreamReaderImpl();
        long headerStartOffset = -1;
        long headerEndOffset = -1;
        final int MAX_OFFSET = 1024 * 1024; // safety net, 1MB should be enough for any header
        try (BufferedInputStream bis = getAbstractFile().getBufferedInputStream()) {
            xsr.setInput(bis, StandardCharsets.UTF_8.name());
            int eventType;
            CharArray localName;
            // we're looking for the first <msRun> and <scan> tag occurrence.
            do {
                eventType = xsr.next();
                final XMLStreamReaderImpl.LocationImpl loc = xsr.getLocation();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        localName = xsr.getLocalName();
                        if (firstTagIsStart && localName.equals(firstTag)) {
                            if (firstTagGetStartLoc) {
                                headerStartOffset = calcByteOffsetTag(loc);
                            } else {
                                headerStartOffset = calcByteOffsetChars(loc);
                            }

                        } else if (lastTagIsStart && localName.equals(lastTag)) {
                            if (lastTagGetStartLoc) {
                                headerEndOffset = calcByteOffsetTag(loc);
                            } else {
                                headerEndOffset = calcByteOffsetChars(loc);
                            }
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        localName = xsr.getLocalName();
                        if (!firstTagIsStart && localName.equals(firstTag)) {
                            if (firstTagGetStartLoc) {
                                headerStartOffset = calcByteOffsetTag(loc);
                            } else {
                                headerStartOffset = calcByteOffsetChars(loc);
                            }

                        } else if (!lastTagIsStart && localName.equals(lastTag)){
                            if (lastTagGetStartLoc) {
                                headerEndOffset = calcByteOffsetTag(loc);
                            } else {
                                headerEndOffset = calcByteOffsetChars(loc);
                            }
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (xsr.isWhiteSpace()) {
                            break;
                        }
                        break;
                }
                if (calcByteOffsetChars(loc) > MAX_OFFSET)
                    throw new RunHeaderBoundsNotFound(String.format(
                            "Could not locate the header of the file using <%s> starting " +
                                    "tag and <%s> closing tag withing the first %d characters", firstTag, lastTag, MAX_OFFSET));
            } while (eventType != XMLStreamConstants.END_DOCUMENT
                    && (headerStartOffset == -1 || headerEndOffset == -1));
            getAbstractFile().close();
        } catch (XMLStreamException | IOException e) {
            throw new RunHeaderParsingException("Error when parsing MS run header info", e);
        } finally {
            getAbstractFile().close();
        }
        if (headerStartOffset == -1 || headerEndOffset == -1) {
            throw new RunHeaderBoundsNotFound(String.format(
                    "Could not find <%s> opening and <%s> closing tags when parsing LCMS run header", firstTag, lastTag));
        }
        return new OffsetLength(headerStartOffset, (int) (headerEndOffset - headerStartOffset));
    }

    private long calcByteOffsetChars(XMLStreamReaderImpl.LocationImpl l) {
        return l.getCharacterOffset() + l.getBomLength();
    }

    private long calcByteOffsetTag(XMLStreamReaderImpl.LocationImpl l) {
        return l.getLastStartTagPos() + l.getBomLength();
    }
}
