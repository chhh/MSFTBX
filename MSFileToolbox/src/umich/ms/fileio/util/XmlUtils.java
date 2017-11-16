/*
 * Copyright (c) 2017 Dmitry Avtonomov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package umich.ms.fileio.util;

import javolution.text.CharArray;
import javolution.xml.internal.stream.XMLStreamReaderImpl;
import javolution.xml.stream.XMLStreamConstants;
import javolution.xml.stream.XMLStreamException;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.exceptions.RunHeaderParsingException;
import umich.ms.fileio.filetypes.xmlbased.OffsetLength;
import umich.ms.logging.LogHelper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Dmitry Avtonomov
 */
public class XmlUtils {
    private XmlUtils() {}

    /**
     * XML tag type. Can be an opening tag, closing tag or a self-closing tag.
     */
    public enum TAG_TYPE {OPENING, CLOSING, SELF_CLOSING}

    /**
     * Describes if the beginning or the end of a text element should be reported as location.
     */
    public enum LOCATION_TYPE {ELEMENT_START, ELEMENT_END}

    /**
     * Reads the run header from the file, locating positions of {@code <firstTag>} and first {@code <lastTag>} tags.
     *
     * @param tag1 The first tag after which run header starts
     * @param tag1Type If the opening tag is a closing tag or an opening tag
     * @param tag1Loc If the location of the beginning of the tag must be taken
     * @param tag2 The tag up to which we need to parse.
     * @param tag2Type If the last tag is an opening tag or a closing tag
     * @param tag2Loc If the location of the beginning or end of the tag entry should be taken
     * @param maxOffset How far in the file should we look? Values <= 0 are treated as infinity.
     * @param is A stream to read from. It will be buffered internally
     *
     * @return the offset and length in bytes in the file (file is assumed to be UTF-8 by the standard)
     * @throws umich.ms.fileio.exceptions.FileParsingException if the tags could not be found or for any IOException
     */
    public static OffsetLength locate(String tag1, TAG_TYPE tag1Type, LOCATION_TYPE tag1Loc,
                                           String tag2, TAG_TYPE tag2Type, LOCATION_TYPE tag2Loc,
                                           long maxOffset, InputStream is) throws FileParsingException {
        if (tag1Type == TAG_TYPE.SELF_CLOSING || tag2Type == TAG_TYPE.SELF_CLOSING)
            throw new IllegalArgumentException("Self-closing tags are not supported.");

        LogHelper.setJavolutionLogLevelFatal();
        long offsetLo = -1;
        long offsetHi = -1;
        try (BufferedInputStream bis = new BufferedInputStream(is)) {
            final XMLStreamReaderImpl xsr = new XMLStreamReaderImpl();
            xsr.setInput(bis, StandardCharsets.UTF_8.name());
            final XMLStreamReaderImpl.LocationImpl loc = xsr.getLocation();
            int eventType;
            CharArray localName;
            // we're looking for the first <msRun> and <scan> tag occurrence.
            do {
                eventType = xsr.next();

                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        localName = xsr.getLocalName();
                        switch (tag1Type) {
                            case OPENING:
                                if (localName.equals(tag1))
                                    offsetLo = calcOffset(loc, tag1Loc);
                                break;
                            default:
                                break;
                        }
                        switch (tag2Type) {
                            case OPENING:
                                if (localName.equals(tag2))
                                    offsetHi = calcOffset(loc, tag2Loc);
                                break;
                            default:
                                break;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        localName = xsr.getLocalName();
                        switch (tag1Type) {
                            case OPENING:
                                if (localName.equals(tag1))
                                    offsetLo = calcOffset(loc, tag1Loc);
                                break;
                            default:
                                break;
                        }
                        switch (tag2Type) {
                            case OPENING:
                                if (localName.equals(tag2))
                                    offsetHi = calcOffset(loc, tag2Loc);
                                break;
                            default:
                                break;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        break;
                }
                if (loc.getTotalCharsRead() > maxOffset)
                    throw new FileParsingException(String.format(
                            "Could not locate tags '%s' and '%s' within first %d characters", tag1, tag2, maxOffset));


            } while (eventType != XMLStreamConstants.END_DOCUMENT
                    && (offsetLo == -1 || offsetHi == -1));

        } catch (XMLStreamException | IOException e) {
            throw new RunHeaderParsingException("Error when parsing MS run header info", e);
        }
        if (offsetLo == -1 || offsetHi == -1) {
            throw new FileParsingException(String.format(
                    "Could not locate tags '%s' and '%s'.", tag1, tag2));
        }
        return new OffsetLength(offsetLo, (int) (offsetHi - offsetLo));
    }

    private static long calcOffset(XMLStreamReaderImpl.LocationImpl loc, LOCATION_TYPE type) {
        switch (type) {
            case ELEMENT_START:
                return loc.getLastStartTagPos() + loc.getBomLength();
            case ELEMENT_END:
                return loc.getTotalCharsRead() + loc.getBomLength();
            default:
                throw new IllegalStateException("Provided LOCATION_TYPE is not supported.");
        }
    }

}
