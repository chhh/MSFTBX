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

package umich.ms.util.xml;

import javolution.text.CharArray;
import javolution.xml.internal.stream.XMLStreamReaderImpl;
import javolution.xml.stream.XMLStreamConstants;
import javolution.xml.stream.XMLStreamException;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.exceptions.RunHeaderParsingException;
import umich.ms.util.OffsetLength;
import umich.ms.logging.LogHelper;

import javax.xml.stream.XMLStreamReader;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Dmitry Avtonomov
 */
public class XmlUtils {
    private XmlUtils() {}

    /**
     * Locates specific sequences of bytes in the input stream.
     * @param targets Sequences of bytes to be searched for. The returned list will be of the same size if all
     *                are found.
     * @param is The stream to search in, it won't be buffered, the stream will be left open
     *           and at the position of the last occurrence of the target.
     * @param maxOffset How far down the stream to search? if maxOffset <= 0, then search
     *                  indefinitely up to Long.MAX_VALUE bytes.
     * @return Empty list if the exact sub-sequence was not found in the stream.
     *         An incomplete list of matches (possibly empty) if the 'maxOffset' has been reached.
     *         Otherwise byte offsets in the input stream will be returned.
     * @throws IOException In case IO errors occur.
     */
    public static List<Long> locate(List<byte[]> targets, List<POSITION> locations,
                                    InputStream is, long maxOffset) throws IOException {
        if (targets.isEmpty())
            throw new IllegalArgumentException("Targets argument can't be empty");
        if (locations.size() != targets.size())
            throw new IllegalArgumentException("Targets and Locations arguments must be of equal length");
        for (byte[] target : targets) {
            if (target.length == 0)
                throw new IllegalArgumentException("Input Targets must be non-zero length");
        }
        if (maxOffset <= 0)
            maxOffset = Long.MAX_VALUE;

        long posSource = -1;
        int iRead;
        byte bRead;
        List<Long> result = new ArrayList<>(targets.size());

        for (int i = 0; i < targets.size(); i++) {
            byte[] target = targets.get(i);
            int posTarget = 0;
            byte bTarget = target[posTarget];
            while ((iRead = is.read()) >= 0) {
                posSource++;
                if (posSource > maxOffset) {
                    // reached max allowed offset, returning what we have so far
                    return result;
                }

                bRead = (byte)iRead;
                if (bRead != bTarget) {
                    if (posTarget > 0) {
                        posTarget = 0;
                        bTarget = target[posTarget];
                    }
                    continue;
                } else {
                    posTarget++;
                    if (posTarget == target.length) {
                        // the end of target has been reached, add it to result
                        switch (locations.get(i)) {
                            case START:
                                result.add(posSource - target.length + 1);
                                break;

                            case END:
                                result.add(posSource);
                                break;

                            default:
                                throw new IllegalArgumentException("Unsupported ELEMENT_LOCATION");
                        }
                        // move to next target
                        break; // break out of while(is.read())
                    }
                    bTarget = target[posTarget];
                    continue;
                }
            }
            if (iRead < 0 && result.size() != targets.size()) {
                // reached EOF without finding all the targets in the input stream
                return Collections.emptyList();
            }
        }
        return result;
    }

    /**
     * Reads the run header from the file, locating <b>character (!)</b> positions of {@code <firstTag>}
     * and first {@code <lastTag>} tags. <b></b>
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
     * @return The CHARACTER offset and length in CHARACTERS in the file.
     * @throws umich.ms.fileio.exceptions.FileParsingException if the tags could not be found or for any IOException
     *
     * @deprecated Try using {@link #locate(List, List, InputStream, long)}.
     *             This method uses an XML parser internally, so it's 1) slower than the other method
     *             2) it's reporting of locations is in Characters, not Bytes!
     */
    @Deprecated
    public static OffsetLength locate(String tag1, TAG_TYPE tag1Type, POSITION tag1Loc,
                                           String tag2, TAG_TYPE tag2Type, POSITION tag2Loc,
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

    private static long calcOffset(XMLStreamReaderImpl.LocationImpl loc, POSITION type) {
        switch (type) {
            case START:
                return loc.getLastStartTagPos() + loc.getBomLength();
            case END:
                return loc.getTotalCharsRead() + loc.getBomLength();
            default:
                throw new IllegalStateException("Provided POSITION is not supported.");
        }
    }

    /**
     * Advances the Stream Reader to the next occurrence of a user-specified tag.
     * @param xsr The reader to advance.
     * @param tag The tag to advance to. No brackets, just the name.
     * @return True if advanced successfully, false when the end of document was successfully reached.
     * @throws javax.xml.stream.XMLStreamException In all cases other than described by 'return'.
     */
    public static boolean advanceReaderToNext(XMLStreamReader xsr, String tag) throws javax.xml.stream.XMLStreamException {
        if (tag == null)
            throw new IllegalArgumentException("Tag name can't be null");
        if (xsr == null)
            throw new IllegalArgumentException("Stream Reader can't be null");
        do {
            if (xsr.next() == javax.xml.stream.XMLStreamConstants.END_DOCUMENT)
                return false;
        } while (!(xsr.isStartElement() && xsr.getLocalName().equals(tag)));

        return true;
    }
}
