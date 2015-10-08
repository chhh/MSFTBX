package umich.ms.fileio.filetypes.mzxml;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javolution.text.CharArray;
import javolution.xml.internal.stream.XMLInputFactoryImpl;
import javolution.xml.stream.XMLInputFactory;
import javolution.xml.stream.XMLStreamConstants;
import javolution.xml.stream.XMLStreamException;
import javolution.xml.stream.XMLStreamReader;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.index.Index;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.exceptions.IndexBrokenException;
import umich.ms.fileio.exceptions.IndexNotFoundException;
import umich.ms.fileio.filetypes.xmlbased.OffsetLength;
import umich.ms.logging.LogHelper;

/**
 * Parses the index portion of an MZXML file.
 * Created by dmitriya on 2015-02-04.
 */
public class MZXMLIndexParser {
    protected MZXMLFile source;
    protected String FILE_TYPE_NAME = "mzXML";
    protected String TAG_INDEXOFFSET = "indexOffset";
    protected String TAG_INDEX = "index";
    protected String INDEX_NAME = "scan";
    protected String TAG_OFFSET = "offset";
    protected String ATTR_OFFSET_ID = "id";
    protected String TAG_END_OF_RUN = "msRun";
    protected int MAX_BYTES_FROM_END_TO_SEARCH_FOR_INDEX = 1 << 10; // 1kb
    protected int NUM_BYTES_TO_CHECK_INDEX = 1 << 10; // 1kb
    protected static int INDEX_OFFSET_MIN_VALUE = 128;
    protected Pattern RE_INDEX_OFFSET = Pattern.compile("<" + TAG_INDEXOFFSET + ">(\\d+)<");
    /** Not used since started using Javolution instead of regular expressions for parsing index */
    protected Pattern RE_INDEX_ENTRY = Pattern.compile(
            "<" + TAG_OFFSET + "[^>]+?id\\s*=\\s*\"\\s*(\\d+?)\\s*\"\\s*>\\s*(\\d+?)\\s*</" + TAG_OFFSET + ">");
    protected Pattern RE_INDEX_ENTRY_SIMPLE = Pattern.compile(String.format("<%s[^>]*?>\\s*(\\d+?)\\s*</%s>", TAG_OFFSET, TAG_OFFSET));
    protected Pattern RE_END_OF_RUN = Pattern.compile("</" + TAG_END_OF_RUN + ">");

    public MZXMLIndexParser(MZXMLFile source) {
        this.source = source;
        // Turn off non-critical Javolution logging
        LogHelper.setJavolutionLogLevelFatal();
    }

    public MZXMLIndex parse() throws FileParsingException {

        // this is a collection of
        TreeMap<Integer, Long> scanIndex = new TreeMap<>();

        try {
            RandomAccessFile fileHandle = source.getRandomAccessFile();
            long offset = findIndexOffset(fileHandle);
            // we'll find the last byte offset pointing right after the last </scan> element
            //long endOfScansOffset = findEndOfScansOffset(fileHandle, offset);
            scanIndex.put(Integer.MAX_VALUE, Long.MAX_VALUE);

            // now we have the offset of the index, we can start reading line by line,
            // after rewinding the filepointer to the proper position
            fileHandle.seek(offset);

            int lenToEnd = (int) (fileHandle.length() - offset);
            byte[] bytes = new byte[lenToEnd];
            int readResult = fileHandle.read(bytes); // this string is supposed to be in US-ASCII by convention

            this.parseIndexEntries(bytes, scanIndex);
        } catch (FileParsingException | IOException e) {
            throw new FileParsingException(e);
        } catch (IndexNotFoundException | IndexBrokenException e) {
            // we could not find the index, we'll have to scan through the whole file and build one ourselves
            source.close();
            MZXMLIndex index = new MZXMLIndex();
            index = source.buildIndex(index);
            return index;
        } finally {
            source.close();
        }


        // converting this parsed index to a map of convenient {ScanNum => {offset, length}} objects
        MZXMLIndex index = new MZXMLIndex();
        int scanNumRaw     = scanIndex.firstEntry().getKey();
        int scanNumInternal, nextScanNumRaw, length;
        long curScanOffset = scanIndex.firstEntry().getValue();
        long nextOffset;
        for (int i = 0; i < scanIndex.size() - 1; i++) {

            Map.Entry<Integer, Long> nextScanIndex = scanIndex.higherEntry(scanNumRaw);
            nextScanNumRaw = nextScanIndex.getKey();
            nextOffset = nextScanIndex.getValue();

            // calculate the length of the entry
            if (nextScanNumRaw == Integer.MAX_VALUE) {
                // if the next entry in scanIndex is pointing to the beginning of the index
                // we'll try to find out the length ourselves by reading the file
                length = findScanLength(curScanOffset);
            } else {
                length = (int) (nextOffset - curScanOffset);
            }

            OffsetLength offlen = new OffsetLength(curScanOffset, length);

            scanNumInternal = i + 1;

            MZXMLIndexElement indexElem = new MZXMLIndexElement(scanNumInternal, scanNumRaw, offlen);
            index.add(indexElem);

            scanNumRaw = nextScanIndex.getKey();
            curScanOffset = nextScanIndex.getValue();
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
            MZXMLMultiSpectraParser parser = source.getSpectraParser(
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
     * Reads the last few kB of the file, looking for {@link #TAG_INDEXOFFSET} tag.
     * @param raf
     * @return
     * @throws IOException
     * @throws FileParsingException
     */
    protected long findIndexOffset(RandomAccessFile raf) throws IOException, IndexNotFoundException, IndexBrokenException {

        long fileLen = raf.length();
        int bytesToRead = fileLen > MAX_BYTES_FROM_END_TO_SEARCH_FOR_INDEX ? MAX_BYTES_FROM_END_TO_SEARCH_FOR_INDEX : (int)fileLen;
        long offsetFromEOF = fileLen - bytesToRead;
        raf.seek(offsetFromEOF);
        byte[] bytes = new byte[bytesToRead];
        raf.readFully(bytes, 0, bytes.length);
        String fileEndingStr = new String(bytes);
        Matcher matcherIdxOffest = RE_INDEX_OFFSET.matcher(fileEndingStr);
        long indexOffset = -1;
        if (matcherIdxOffest.find()) {
            indexOffset = Long.parseLong(matcherIdxOffest.group(1));
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
    protected void parseIndexEntries(byte[] bytes, TreeMap<Integer, Long> map) throws FileParsingException {
        XMLInputFactory factory = new XMLInputFactoryImpl();
        XMLStreamReader reader = null;
        try {
            reader = factory.createXMLStreamReader(new ByteArrayInputStream(bytes));
            boolean isInsideSpectrumIndex = false;
            boolean stopReading = false;
            int eventType;
            do {
                eventType = reader.next();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (isInsideSpectrumIndex && reader.getLocalName().equals(TAG_OFFSET)) {
                            CharArray offsetId = reader.getAttributeValue(null, ATTR_OFFSET_ID);
                            int scanNum = parseScanNumFromIndex(offsetId);

                            // now read input for all CHARACTERS to get the offset
                            eventType = reader.next();
                            if (eventType != XMLStreamConstants.CHARACTERS) {
                                throw new FileParsingException(String.format(
                                        "Could not find scan offset, specified as CHARACTERS after <%s> tag in %s file", TAG_OFFSET, FILE_TYPE_NAME));
                            }
                            long offset = reader.getText().toLong();
                            map.put(scanNum, offset);

                        } else if (reader.getLocalName().equals(TAG_INDEX)) {
                            // search for the beginning of spectrumRef/scan index
                            // moved to bottom, because this should be less frequent of an event
                            CharArray name = reader.getAttributeValue(null, "name");
                            if (name == null)
                                throw new FileParsingException("mzML file index list did not contain a 'name' attribute for one of its indexes");
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
            if (!e.getMessage().contains("Unexpected end tag")) {
                throw new FileParsingException("Error when parsing index entries", e);
            }
        }
    }

    protected int parseScanNumFromIndex(CharArray scanIdentifier) throws FileParsingException {
        return scanIdentifier.toInt();
    }
}
