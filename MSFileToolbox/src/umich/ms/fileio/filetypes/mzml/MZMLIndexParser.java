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
package umich.ms.fileio.filetypes.mzml;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javolution.text.CharArray;
import javolution.xml.internal.stream.XMLInputFactoryImpl;
import javolution.xml.stream.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.exceptions.IndexBrokenException;
import umich.ms.fileio.exceptions.IndexNotFoundException;
import umich.ms.fileio.filetypes.xmlbased.OffsetLength;
import umich.ms.logging.LogHelper;

/**
 * @author Dmitry Avtonomov
 */
public class MZMLIndexParser {
    private static final Logger log = LoggerFactory.getLogger(MZMLIndexParser.class);

    protected MZMLFile source;
    protected String FILE_TYPE_NAME = "mzML";
    protected String TAG_INDEXOFFSET = "indexListOffset";
    protected String TAG_INDEX = "index";
    protected String INDEX_NAME = "spectrum";
    protected String TAG_OFFSET = "offset";
    protected String ATTR_OFFSET_ID = "idRef";
    protected String TAG_END_OF_RUN = "spectrumList";
    protected Pattern RE_INDEX_OFFSET = Pattern.compile("<" + TAG_INDEXOFFSET + ">(\\d+)<");
    protected int MAX_BYTES_FROM_END_TO_SEARCH_FOR_INDEX = 1 << 10; // 1kb
    protected int NUM_BYTES_TO_CHECK_INDEX = 1 << 10; // 1kb
    protected static int INDEX_OFFSET_MIN_VALUE = 128;
    protected Pattern RE_INDEX_ENTRY_SIMPLE = Pattern.compile(String.format("<%s[^>]*?>\\s*(\\d+?)\\s*</%s>", TAG_OFFSET, TAG_OFFSET));
    protected Pattern RE_END_OF_RUN = Pattern.compile("</" + TAG_END_OF_RUN + ">");

    public MZMLIndexParser(MZMLFile source) {
        this.source = source;
        // Turn off non-critical Javolution logging
        LogHelper.setJavolutionLogLevelFatal();
    }

    public MZMLIndex parse() throws FileParsingException {

        TreeMap<Integer, OffsetId> scanIndex = new TreeMap<>();

        try {
            RandomAccessFile fileHandle = source.getRandomAccessFile();
            long offset = findIndexOffset(fileHandle);
            if (offset < INDEX_OFFSET_MIN_VALUE) {
                throw new FileParsingException(String.format(
                        "When parsing index, the offset of the beginning of the index was small " +
                                "(less than %d bytes). " +
                                "Likely a broken file, this happens with larger files converted with " +
                                "some versions of ProteoWizard.",
                        INDEX_OFFSET_MIN_VALUE
                ));
            }
            // we'll find the last byte offset pointing right after the last </scan> element
            //long endOfScansOffset = findEndOfScansOffset(fileHandle, offset);
            scanIndex.put(Integer.MAX_VALUE, new OffsetId(Long.MAX_VALUE, "END_OF_SCANS"));

            // now we have the offset of the index, we can start reading line by line,
            // after rewinding the filepointer to the proper position
            fileHandle.seek(offset);

            int lenToEnd = (int) (fileHandle.length() - offset);
            byte[] bytes = new byte[lenToEnd];
            int readResult = fileHandle.read(bytes); // this string is supposed to be in US-ASCII by convention

            this.parseIndexEntries(bytes, scanIndex);
            source.close();
        } catch (IOException e) {
            throw new FileParsingException(e);
        } catch (IndexNotFoundException | IndexBrokenException ex) {
            // we could not find the index, we'll have to scan through the whole file and build one ourselves
            source.close();
            MZMLIndex index = new MZMLIndex();
            index = source.buildIndex(index);
            return index;

        } finally {
            source.close();
        }


        // converting this parsed index to a map of convenient {ScanNum => {offset, length}} objects
        MZMLIndex index = new MZMLIndex();

        int curScanNumRaw  = scanIndex.firstEntry().getKey();
        long curScanOffset = scanIndex.firstEntry().getValue().offset;
        int scanNumInternal = 1, nextScanNumRaw, length;
        long nextScanOffset;
        String curScanId = scanIndex.firstEntry().getValue().id, nextScanId;

        if (curScanNumRaw == Integer.MAX_VALUE) {

            // there was just one scan in the run
            length = findScanLength(curScanOffset);
            OffsetLength offlen = new OffsetLength(curScanOffset, length);
            MZMLIndexElement indexElem = new MZMLIndexElement(scanNumInternal++, curScanNumRaw, curScanId,  offlen);
            index.add(indexElem);
        } else {

            // there were multiple scans in the run
            Set<Map.Entry<Integer, OffsetId>> entries = scanIndex.entrySet();
            Iterator<Map.Entry<Integer, OffsetId>> iterator = entries.iterator();
            Map.Entry<Integer, OffsetId> firstEntry = iterator.next();// skip the first entry
            curScanId = firstEntry.getValue().id;
            curScanNumRaw = firstEntry.getKey();
            curScanOffset = firstEntry.getValue().offset;

            while (iterator.hasNext()) {
                Map.Entry<Integer, OffsetId> nextScanEntry = iterator.next();
                nextScanNumRaw = nextScanEntry.getKey();
                nextScanOffset = nextScanEntry.getValue().offset;
                nextScanId = nextScanEntry.getValue().id;
                if (nextScanOffset < curScanOffset) {
                    log.warn("Found mzML index entry with offset smaller than the previous entry in the same index." +
                                     " Entry #{}, found offset: {}, previous entry #{}, previous offset: {}", nextScanNumRaw, nextScanOffset, curScanNumRaw, curScanOffset);
                } else {

                    // calculate the length of the entry
                    if (nextScanNumRaw == Integer.MAX_VALUE) {
                        // if the next entry in scanIndex is pointing to the beginning of the index
                        // we'll try to find out the length ourselves by reading the file
                        length = findScanLength(curScanOffset);
                    } else {
                        length = (int) (nextScanOffset - curScanOffset);
                    }

                    OffsetLength offlen = new OffsetLength(curScanOffset, length);
                    MZMLIndexElement indexElem = new MZMLIndexElement(scanNumInternal++, curScanNumRaw, curScanId, offlen);
                    index.add(indexElem);

                    curScanNumRaw = nextScanNumRaw;
                    curScanOffset = nextScanOffset;
                    curScanId = nextScanId;
                }
            }
        }

        return index;
    }

    /**
     * Finds the length of a scan entry in the original file by reading it,
     * starting at the provided offset and looking for an "end of scan" tag.
     * <b>Note that the {@code} source must be closed prior to calling this method.</b>
     * @param offset offset in the file, where to start reading
     * @return -1 if no end of scan was found
     * @throws umich.ms.fileio.exceptions.FileParsingException
     */
    protected int findScanLength(long offset) throws FileParsingException {
        int length = -1;
        try {
            RandomAccessFile raf = source.getRandomAccessFile();
            raf.seek(offset);
            InputStream is = Channels.newInputStream(raf.getChannel());
            BufferedInputStream bis = new BufferedInputStream(is);
            MZMLMultiSpectraParser parser = source.getSpectraParser(
                    bis, LCMSDataSubset.STRUCTURE_ONLY, source.getReaderPool(), 1);

            length = parser.findThisStreamFirstScanLen();
            is.close();
        } catch (IOException e) {
            throw new FileParsingException(e);
        } finally {
            source.close();
        }
        return length;
    }


    /**
     * Reads the last several kB of the file, looking for {@link #TAG_INDEXOFFSET} tag.
     * @param raf
     * @return
     * @throws IOException
     * @throws IndexNotFoundException in case "indexListOffset" tag could not be found within the last few kB of the file
     */
    protected long findIndexOffset(RandomAccessFile raf) throws IOException, IndexNotFoundException, IndexBrokenException {
        long fileLen = raf.length();
        int bytesToRead = fileLen > MAX_BYTES_FROM_END_TO_SEARCH_FOR_INDEX ? MAX_BYTES_FROM_END_TO_SEARCH_FOR_INDEX : (int)fileLen;
        long offsetFromEOF = fileLen - bytesToRead;
        raf.seek(offsetFromEOF);
        byte[] bytes = new byte[bytesToRead];
        raf.readFully(bytes, 0, bytes.length);
        String fileEndingStr = new String(bytes);
        Matcher matcher = RE_INDEX_OFFSET.matcher(fileEndingStr);
        long indexOffset = -1;
        if (matcher.find()) {
            indexOffset = Long.parseLong(matcher.group(1));
        }
        if (indexOffset == -1) {
            throw new IndexNotFoundException(String.format(
                    "%s <%s> section was not found within the last %d bytes in the file! (%s)",
                    FILE_TYPE_NAME, TAG_INDEXOFFSET, MAX_BYTES_FROM_END_TO_SEARCH_FOR_INDEX, source.getPath()));
        }
        if (indexOffset < INDEX_OFFSET_MIN_VALUE) {
            throw new IndexBrokenException(String.format(
                    "Index offset was less than %d, actual value: [%d] - not allowed", INDEX_OFFSET_MIN_VALUE, indexOffset));
        }
        if (indexOffset > fileLen) {
            throw new IndexBrokenException(String.format(
                    "Index offset was larger than the length of the file, actual value: [%d]", indexOffset));
        }

        // now check the first few values of the entries in the index
        long lenFromIndexStartToEOF = fileLen - indexOffset;
        int indexBeginLength = lenFromIndexStartToEOF >= NUM_BYTES_TO_CHECK_INDEX ? NUM_BYTES_TO_CHECK_INDEX : (int)lenFromIndexStartToEOF;
        byte[] indexBeginBytes = new byte[indexBeginLength];
        raf.seek(indexOffset);
        raf.readFully(indexBeginBytes, 0, indexBeginBytes.length);
        String indexBeginStr = new String(indexBeginBytes);

        Matcher matcherIdxEntry = RE_INDEX_ENTRY_SIMPLE.matcher(indexBeginStr);
        long offsetPrev = -2, offsetCur;
        while(matcherIdxEntry.find()) {
            offsetCur = Long.parseLong(matcherIdxEntry.group(1));
            if (offsetCur < 0) {
                throw new IndexBrokenException(String.format(
                        "The index contained an element less than zero: '%s'", matcherIdxEntry.group(0)));
            }
            if (offsetCur <= offsetPrev) {
                throw new IndexBrokenException(String.format(
                        "The index contained an element less or equal to a previous one. The match was: '%s'", matcherIdxEntry.group(0)));
            }
            if (offsetCur >= indexOffset) {
                throw new IndexBrokenException(String.format(
                        "The index contained an element that was further in the file than the '%s'.", TAG_INDEXOFFSET));
            }
            offsetPrev = offsetCur;
        }

        return indexOffset;
    }

    /**
     *
     * @param bytes the ending of the file, containing the index
     * @param map ScanMap with simple mapping from Integer scan numbers to Long offsets, just as written in the "index".
     *            This map should already contain the offset of the beginning of the "index" section in mzML file associated
     *            with key Integer.MAX_VALUE.
     * @throws FileParsingException
     */
    protected void parseIndexEntries(byte[] bytes, TreeMap<Integer, OffsetId> map) throws FileParsingException {
        XMLInputFactory factory = new XMLInputFactoryImpl();
        XMLStreamReader reader;
        try {
            reader = factory.createXMLStreamReader(new ByteArrayInputStream(bytes));
            boolean isInsideSpectrumIndex = false;
            boolean stopReading = false;
            int eventType, scanNumOrdinal = 0;
            long offset, offsetPrev = -1;
            do {
                eventType = reader.next();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (isInsideSpectrumIndex && reader.getLocalName().equals(TAG_OFFSET)) {
                            CharArray offsetId = reader.getAttributeValue(null, ATTR_OFFSET_ID);
                            //int scanNum = parseScanNumFromIndex(offsetId);
                            int scanNum = scanNumOrdinal;
                            scanNumOrdinal++;

                            // now read input for all CHARACTERS to get the offset
                            eventType = reader.next();
                            if (eventType != XMLStreamConstants.CHARACTERS) {
                                throw new FileParsingException(String.format(
                                        "Could not find scan offset, specified as CHARACTERS "
                                                + "after <%s> tag in %s file", TAG_OFFSET, FILE_TYPE_NAME));
                            }
                            offset = reader.getText().toLong();
                            if (offset == offsetPrev) {
                                throw new FileParsingException("When parsing index, encountered same offsets for" +
                                        " different spectra. Likely a broken file, this happens when converting large" +
                                        " files with specific versions of ProteoWizard.");
                            }
                            map.put(scanNum, new OffsetId(offset, offsetId.toString()));
                            offsetPrev = offset;

                        } else if (reader.getLocalName().equals(TAG_INDEX)) {
                            // search for the beginning of spectrumRef/scan index
                            // moved to bottom, because this should be less frequent of an event
                            CharArray name = reader.getAttributeValue(null, "name");
                            if (name == null)
                                throw new FileParsingException("mzML file index list did not contain "
                                        + "a 'name' attribute for one of its indexes");
                            if (name.equals(INDEX_NAME)) {
                                isInsideSpectrumIndex = true;
                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        if (isInsideSpectrumIndex && reader.getLocalName().equals(TAG_INDEX)) {
                            stopReading = true;
                            break;
                        }
                }
                if (stopReading)
                    break;
            } while (eventType != XMLStreamConstants.END_DOCUMENT);

        } catch (XMLStreamException e) {
            // Javolution throws an Exception, which can only be identified by its text message
            if (e instanceof XMLUnexpectedEndTagException) {
                throw new FileParsingException("Error when parsing index entries", e);
            }
        }
    }


    protected class OffsetId {
        public final long offset;
        public final String id;

        public OffsetId(long offset, String id) {
            this.offset = offset;
            this.id = id;
        }
    }
}
