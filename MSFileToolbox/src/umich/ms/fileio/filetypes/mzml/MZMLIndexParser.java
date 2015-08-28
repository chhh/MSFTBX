package umich.ms.fileio.filetypes.mzml;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.Map;
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
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.exceptions.IndexNotFoundException;
import umich.ms.fileio.filetypes.xmlbased.OffsetLength;
import umich.ms.logging.LogHelper;

/**
 * Created by dmitriya on 2015-02-04.
 */
public class MZMLIndexParser {
    protected MZMLFile source;
    protected String FILE_TYPE_NAME = "mzML";
    protected String TAG_INDEXOFFSET = "indexListOffset";
    protected String TAG_INDEX = "index";
    protected String INDEX_NAME = "spectrum";
    protected String TAG_OFFSET = "offset";
    protected String ATTR_OFFSET_ID = "idRef";
    protected String TAG_END_OF_RUN = "spectrumList";
    protected Pattern RE_INDEX_OFFSET = Pattern.compile("<" + TAG_INDEXOFFSET + ">(\\d+)<");
    protected int MAX_LINES_FROM_END_TO_SEARCH_FOR_INDEX = 100;
    protected int MAX_BYTES_FROM_END_TO_SEARCH_FOR_INDEX = 1 << 10; // 1kb
    protected int MAX_BYTES_FROM_INDEX_TO_END_OF_RUN = 1024;
    protected static int INDEX_OFFSET_MIN_VALUE = 128;
    //RE_INDEX_ENTRY = Pattern.compile(
    //        "<" + TAG_OFFSET + "[^>]+?idRef\\s*=\\s*\"[^>]*?scan=(\\d+?)\\s*\"\\s*>\\s*(\\d+?)\\s*</" + TAG_OFFSET + ">");
    protected Pattern RE_END_OF_RUN = Pattern.compile("</" + TAG_END_OF_RUN + ">");
    /**
     * @deprecated We now only use the scan id ('idRef' attribute in the index) and never
     * try to guess/parse the scan number from just the index.
     */
    @Deprecated
    public static final Pattern RE_SCAN_NUM_FROM_INDEX_REF = Pattern.compile("(?:scan|cycle)=(\\d+)");
    public static final Pattern RE_SCAN_ID_FROM_INDEX_REF = Pattern.compile("(?:scan|cycle)=(\\d+)");

    public MZMLIndexParser(MZMLFile source) {
        this.source = source;
        // Turn off non-critical Javolution logging
        LogHelper.setJavolutionLogLevelFatal();
    }

    /**
     * @deprecated We now only use the scan id ('idRef' attribute in the index) and never
     * try to guess/parse the scan number from just the index.
     * @param scanIdentifier
     * @return
     * @throws FileParsingException
     */
    @Deprecated
    protected int parseScanNumFromIndex(CharArray scanIdentifier) throws FileParsingException {
        Matcher matcher = RE_SCAN_NUM_FROM_INDEX_REF.matcher(scanIdentifier);
        if (!matcher.find()) {
            throw new FileParsingException(String.format(
                    "Could not parse scan number from an index entry of %s file: %s", FILE_TYPE_NAME, scanIdentifier.toString()));
        }
        return Integer.parseInt(matcher.group(1));
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
        } catch (IndexNotFoundException ex) {
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
        int scanNumRaw, nextScanNumRaw, scanNumInternal, length;
        long curScanOffset, nextScanOffset;
        String curScanId, nextScanId;

        curScanOffset = scanIndex.firstEntry().getValue().offset;
        curScanId = scanIndex.firstEntry().getValue().id;
        
        for (int i = 0; i < scanIndex.size() - 1; i++) {
            scanNumRaw = i;
            
            Map.Entry<Integer, OffsetId> nexScanIndex = scanIndex.higherEntry(scanNumRaw);
            nextScanNumRaw = nexScanIndex.getKey();
            nextScanOffset = nexScanIndex.getValue().offset;
            nextScanId = nexScanIndex.getValue().id;

            if (nextScanNumRaw == Integer.MAX_VALUE) {
                length = findScanLength(curScanOffset);
            } else {
                length = (int) (nextScanOffset - curScanOffset);
            }
            OffsetLength offlen = new OffsetLength(curScanOffset, length);

            scanNumInternal = i + 1;
            
            MZMLIndexElement indexElem = new MZMLIndexElement(scanNumInternal, scanNumRaw, curScanId, offlen);
            index.add(indexElem);

            curScanOffset = nextScanOffset;
            curScanId = nextScanId;
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
     * Reads the file backwards, looking for {@link #TAG_INDEXOFFSET} tag.
     * TODO: it's better to just read the last 1kb of the file and parse it normally,
     * this way it won't depend on line breaks in the file
     * @param raf
     * @return
     * @throws IOException
     * @throws IndexNotFoundException in case "indexListOffset" tag could not be found within 100 lines from the end of file
     */
    protected long findIndexOffset(RandomAccessFile raf) throws IOException, IndexNotFoundException {
        long fileLen = raf.length();
        int bytesToRead = fileLen > MAX_BYTES_FROM_END_TO_SEARCH_FOR_INDEX ? MAX_BYTES_FROM_END_TO_SEARCH_FOR_INDEX : (int)fileLen;
        long offsetFromEOF = fileLen - bytesToRead;
        raf.seek(offsetFromEOF);
        byte[] bytes = new byte[bytesToRead];
        raf.readFully(bytes, 0, bytes.length);
        String fileEndingStr = new String(bytes);
        Matcher matcher = RE_INDEX_OFFSET.matcher(fileEndingStr);
        long offset = -1;
        if (matcher.find()) {
            offset = Long.parseLong(matcher.group(1));
        }

        // Old version reading in reverse line by line
        // notice that the file is read in reverse line-by-line using ReverseLineInputStream
//        ReverseLineInputStream reverseLineInputStream = new ReverseLineInputStream(raf);
//        BufferedReader in = new BufferedReader(new InputStreamReader(reverseLineInputStream));
//
//        long offset = -1;
//        String line;
//        Matcher indexOffsetReMatcher;
//        int linesRead = 0;
//        // reading in reverse, looking for <indexOffset>
//        while ((line = in.readLine()) != null) {
//            if (line.contains(TAG_INDEXOFFSET)) {
//                indexOffsetReMatcher = RE_INDEX_OFFSET.matcher(line);
//                if (indexOffsetReMatcher.find()) {
//                    offset = Long.parseLong(indexOffsetReMatcher.group(1));
//                    break;
//                }
//            }
//
//            linesRead++;
//            if (linesRead > MAX_LINES_FROM_END_TO_SEARCH_FOR_INDEX) {
//                break;
//            }
//        }

        if (offset == -1) {
            throw new IndexNotFoundException(String.format(
                    "%s <%s> section was not found within the last %d lines in the file! (%s)",
                    FILE_TYPE_NAME, TAG_INDEXOFFSET, MAX_LINES_FROM_END_TO_SEARCH_FOR_INDEX, source.getPath()));
        }
        return offset;
    }

    /**
     * Search for byte offset of {@code </msRun>} or comparable tag, which should go right after the last scan.
     * This is actually only needed for the case, when we use a validating XML parser to parseIndexEntries scans
     * from the XML file, because it will stumble upon seeing a closing {@code </msRun>} tag after the last
     * {@code </scan>} (that's because the last scan's ending offset is set to be the {@code <index>} offset).
     * What a mess...
     * @deprecated not is use anymore, we now just run the parser on the last scan in the index to find out its
     * actual length
     * @param fileHandle
     * @param indexOffset offset of the beginning of the {@code <index>}
     * @return
     * @throws IOException
     * @throws umich.ms.fileio.exceptions.FileParsingException
     */
    @Deprecated
    protected long findEndOfScansOffset(RandomAccessFile fileHandle, long indexOffset) throws IOException, FileParsingException {
        StringBuilder sb = new StringBuilder();
        int toRead = (int)Math.min(MAX_BYTES_FROM_INDEX_TO_END_OF_RUN, indexOffset);
        long pos = indexOffset - toRead;
        fileHandle.seek(pos);
        byte[] bytes = new byte[toRead];
        fileHandle.readFully(bytes);
        String beforeIndex = new String(bytes, StandardCharsets.UTF_8);
        Matcher matcher = RE_END_OF_RUN.matcher(beforeIndex);
        long endOfRunOffset;
        if (matcher.find()) {
            endOfRunOffset = pos + matcher.start();
        } else {
            return indexOffset;
//            throw new FileParsingException(String.format("Couldn't find </%s> tag within %d bytes of <%s> tag when parsing index",
//                    TAG_END_OF_RUN, MAX_BYTES_FROM_INDEX_TO_END_OF_RUN, TAG_INDEX));
        }

        return endOfRunOffset;
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
            if (!e.getMessage().contains("Unexpected end tag")) {
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
