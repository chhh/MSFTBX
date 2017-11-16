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

import javolution.text.CharArray;
import javolution.xml.internal.stream.XMLStreamReaderImpl;
import javolution.xml.sax.Attributes;
import javolution.xml.stream.XMLStreamConstants;
import javolution.xml.stream.XMLStreamException;
import javolution.xml.stream.XMLUnexpectedEndOfDocumentException;
import javolution.xml.stream.XMLUnexpectedEndTagException;
import org.apache.commons.pool2.ObjectPool;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.PeaksCompression;
import umich.ms.datatypes.scan.impl.ScanDefault;
import umich.ms.datatypes.scan.props.Polarity;
import umich.ms.datatypes.scan.props.PrecursorInfo;
import umich.ms.datatypes.scan.props.ScanType;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.datatypes.spectrum.impl.SpectrumDefault;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.xmlbased.MultiSpectraParser;
import umich.ms.logging.LogHelper;
import umich.ms.util.ByteArrayHolder;
import umich.ms.util.base64.Base64;
import umich.ms.util.base64.Base64Context;
import umich.ms.util.base64.Base64ContextPooled;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Parses several spectra using Javolution XML streaming parser.
 * @author Dmitry Avtonomov
 */

// this SuppressWarnings was the only way I've found to get rid of unchecked cast
// warning from getSource().
// was complaining like:
//  warning: [unchecked] getSource() in MZXMLMultiSpectraParser overrides <T>getSource() in MultiSpectraParser
//  public MZXMLFile getSource() {
//  return type requires unchecked conversion from MZXMLFile to T
//  where T is a type-variable:
//  T extends LCMSDataSource<?> declared in method <T>getSource()
@SuppressWarnings("unchecked")
public class MZXMLMultiSpectraParser extends MultiSpectraParser {

    protected final MZXMLFile source;
    protected LCMSRunInfo runInfo;
    protected MZXMLIndex index;

    private ArrayList<IScan> parsedScans;
    private MzxmlVars vars;
    private ObjectPool<XMLStreamReaderImpl> readerPool = null;

    /** This var is used to count the number of times {@code <scan>} tags were encountered during one parsing run.
     *  If this number is greater than the number of Scans in {@code #parsedScans} field by the time an
     *  "Unexpected End Of Document" is thrown, it means something really went wrong.</br>
     *  That's only for cases of old mzXML files, that had nested scan tags. E.g. if you had 1 MS1 scan, followed
     *  by 5 MS2 scans, and the parser was given the first 3 scans only, then the parser would never see the closing
     *  tag for the MS1 scan (3 more MS2 scans are between the end of the stream and the closing tag), and that is
     *  considered an error by the parser.
     **/
    private int numOpeningScanTagsFound;

    protected enum TAG {
        SCAN("scan"),
        PRECURSOR("precursorMz"),
        PEAKS("peaks");

        public final String name;

        TAG(String named) {
            this.name = named;
        }
    }

    protected enum ATTR {
        SCAN_NUM("num", true),
        SCAN_TYPE("scanType", false),
        MS_LEVEL("msLevel", true),
        PEAKS_COUNT("peaksCount", true),
        RT("retentionTime", false),
        POLARITY("polarity", false),
        MZ_LO_OBSERVED("lowMz", false),
        MZ_HI_OBSERVED("highMz", false),
        MZ_LO_INSTRUMENT("startMz", false),
        MZ_HI_INSTRUMENT("endMz", false),
        BASEPEAK_INTENSITY("basePeakIntensity", false),
        INSTRUMENT_ID("msInstrumentID", false),
        TIC("totIonCurrent", false),
        CENTROIDED("centroided", false),
        PRECURSOR_SCAN_NUM("precursorScanNum", false),
        PRECURSOR_INTENSITY("precursorIntensity", true),
        PRECURSOR_CHARGE("precursorCharge", false),
        PRECURSOR_ISOLATION_WINDOW("windowWideness", false),
        FRAGMENTATION_METHOD("activationMethod", false),
        ACTIVATION_ENERGY("collisionEnergy", false),
        COMPRESSION_TYPE("compressionType", true),
        COMPRESSED_LEN("compressedLen", true),
        PRECISION("precision", false);

        public final String name;
        public final boolean isRequired;

        ATTR(String named, boolean isRequired) {
            this.name = named;
            this.isRequired = isRequired;
        }
    }



    /**
     * Set the pool of readers. If you'll be parsing lots of scans, it is more efficient to reuse the readers,
     * as their internal buffers will not be reinitialised, they will already be grown to some reasonable size
     * which was enough for previous parsing to complete.
     */
    void setReaderPool(ObjectPool<XMLStreamReaderImpl> readerPool) {
        this.readerPool = readerPool;
    }

    /**
     *
     * @param is
     * @param subset the subset will only be used to determine if the spectrum
     * should be parsed or not.
     * @param source
     * @throws FileParsingException
     */
    MZXMLMultiSpectraParser(InputStream is, LCMSDataSubset subset, MZXMLFile source) throws FileParsingException {
        super(is, subset);
        this.source = source;
    }

    @Override
    public MZXMLFile getSource() {
        return source;
    }

    @Override
    public List<IScan> call() throws Exception {
        runInfo = source.fetchRunInfo();
        index = source.fetchIndex();

        if (numScansToProcess != null) {
            parsedScans = new ArrayList<>(numScansToProcess);
        } else {
            parsedScans = new ArrayList<>();
        }
        numOpeningScanTagsFound = 0;
        vars = new MzxmlVars();

        XMLStreamReaderImpl reader = (readerPool == null) ? new XMLStreamReaderImpl() : readerPool.borrowObject();
        try {
            reader.setInput(is, StandardCharsets.UTF_8.name());
            LogHelper.setJavolutionLogLevelFatal();

            int eventType = XMLStreamConstants.END_DOCUMENT;
            CharArray localName;
            do {
                // if we've read enough spectra already, then stop processing the stream
                if (numScansToProcess != null && parsedScans.size() == numScansToProcess) {
                    addCurScanAndFlushVars();
                    break;
                }

                // Read the next XML element
                try {
                    eventType = reader.next();
                } catch (XMLStreamException e) {
                    if (e instanceof XMLUnexpectedEndTagException) {
                        continue;
                    }
                    if (e instanceof XMLUnexpectedEndOfDocumentException) {
                        // this happens when we have nested <scan> tags and parsing not the whole MS1/MS2 child pairs
                        if (parsedScans.size() == numOpeningScanTagsFound ||
                                (vars.curScan != null
                                        && numScansToProcess != null
                                        && parsedScans.size() == numScansToProcess - 1)) {
                            // this happens when mzXML was converted with ReAdW and SCAN tags for MS2 scans
                            // are enclosed into MS1 SCAN tags. In that case, if the last scan to process happens
                            // to be an MS1 scan, then it doesn't have a closing SCAN tag, instead there is another
                            // opening SCAN tag in its place.
                            if (parsedScans.size() == numScansToProcess - 1) {
                                addCurScanAndFlushVars();
                            }
                            return parsedScans.isEmpty() ? null : parsedScans;
                        } else {
                            throw new FileParsingException("The number of read scans did not match the provided number" +
                                    "of scans to be read at method invocation. End of stream reached earlier.", e);
                        }
                    }
                    throw new FileParsingException(e);
                }


                // Process the read event
                switch (eventType) {

                    case XMLStreamConstants.START_ELEMENT:
                        localName = reader.getLocalName();

                        if (localName.contentEquals(TAG.SCAN.name)) {
                            tagScanStart(reader);
                        } else if (localName.contentEquals(TAG.PRECURSOR.name)) {
                            eventType = tagPrecursorStart(reader);
                        } else if (localName.contentEquals(TAG.PEAKS.name)) {
                            eventType = tagPeaksStart(reader, eventType);
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        localName = reader.getLocalName();

                        if (localName.contentEquals(TAG.SCAN.name)) {
                            addCurScanAndFlushVars();
                            break;
                        }

                        break;
                }
            } while (eventType != XMLStreamConstants.END_DOCUMENT);

            return parsedScans.isEmpty() ? null : parsedScans;

        } finally {
            // we need to return the reaer to the pool, if we borrowed it from there
            if (readerPool != null) {
                readerPool.returnObject(reader);
            }
        }

    }




    private int tagPeaksStart(XMLStreamReaderImpl reader, int eventType) throws XMLStreamException, FileParsingException, DataFormatException, IOException {
        Attributes attrs;
        CharArray attr;
        vars.isPeaksTagReached = true;
        if (vars.curScan == null || !doesNeedSpectrumParsing(vars.curScan)) {
            // we will only use this tag, if parsing spectrum for this MS-Level was requested
            // so if it doesn't need to have a spectrum parsed, then we can safely add this scan
            // to the result
            addCurScanAndFlushVars();
            return eventType;
        }
        attrs = reader.getAttributes();

        // not all mzXML files adhere to the standard, some don't provide compressionType attribute
        // we'll have to assume no compression in this case
        //attr = fetchAttribute(attrs, ATTR.COMPRESSION_TYPE);
        //vars.compressionType = attr.toString();
        attr = attrs.getValue(ATTR.COMPRESSION_TYPE.name);
        if (attr != null) {
            vars.compressionType = attr.toString();
        }

        // not all mzXML files adhere to the standard, some don't provide compressedLen attribute
        //attr = fetchAttribute(attrs, ATTR.COMPRESSED_LEN);
        //vars.compressedLen = attr.toInt();
        attr = attrs.getValue(ATTR.COMPRESSED_LEN.name);
        if (attr != null) {
            vars.compressedLen = attr.toInt();
        }

        attr = attrs.getValue(ATTR.PRECISION.name);
        if (attr != null) {
            vars.precisionMz = attr.toInt();
            vars.precisionInt = attr.toInt();
        }

        // the next event must be CHARACTERS, that's the Base64 encoded string of mz/intensity values
        // but only if there was at least a single peak (i.e. the spectrumRef was not empty)
        if (vars.peaksCount > 0) {
            eventType = reader.next();
            if (eventType != XMLStreamConstants.CHARACTERS) {
                throw new FileParsingException(String.format(
                        "Base64 encoded string was not found immediately after <%s> tag for scan #%d", TAG.PEAKS.name, vars.curScan.getNum()));
            }


            // This is the implementation of Base64 decoding from Stephen Ostermiller
            // in my tests it was much slower, than Apache Commons implementation
            //byte[] bytes = Base64Slow.decodeToBytes(reader.getText().toString());

            // this works using Cryptacular's base64 decoding of CharSequence
            // does pretty good
            //byte[] bytes = MZXMLPeaksDecoder.decodeBase64(reader.getText());
            //MZXMLPeaksDecoder.DecodedData decoded =
            //        MZXMLPeaksDecoder.decode(
            //                bytes, vars.precisionMz, vars.compressionType);


            // The new way, Base64 decoder is based on Apache Commons implementation,
            // but reuses internal buffers, taking them from the pool
            Base64 base64 = new Base64();
            //Base64Context ctx = new Base64Context();   // don't use regular contexts, underlying buffers will be always re-allocated
            Base64Context ctx = new Base64ContextPooled(); // this ctx will borrow a ByteArrayHolder from pool
            CharArray chars = reader.getText();
            Base64Context decodedB64 = base64.decode(chars.array(), chars.offset(), chars.length(), ctx);
            ByteArrayHolder bah = decodedB64.readResults();
            PeaksCompression compression = PeaksCompression.NONE;
            if (vars.compressionType != null) {
                if ("zlib".equalsIgnoreCase(vars.compressionType)) {
                    compression = PeaksCompression.ZLIB;
                }
            }
            MZXMLPeaksDecoder.DecodedData decoded = MZXMLPeaksDecoder.decode(
                    bah.getUnderlyingBytes(), bah.getPosition(),
                    vars.precisionMz, compression);
            ctx.close(); // close the context, so it could return it's ByteArrayHolder to the pool


            ISpectrum spectrum = new SpectrumDefault(
                    decoded.mzs, decoded.intensities,
                    decoded.minIntensity, decoded.minIntensityNonZero,
                    decoded.maxIntensity, decoded.maxIntensityMz, decoded.intensitySum);
            vars.curScan.setSpectrum(spectrum, false);
        }

        return eventType;
    }

    private int tagPrecursorStart(XMLStreamReaderImpl reader) throws FileParsingException, XMLStreamException {
        Attributes attrs;
        CharArray attr;
        int eventType;
        if (vars.curScan == null) {
            vars.reset();
        }
        attrs = reader.getAttributes();
        PrecursorInfo precursorInfo = vars.curScan.getPrecursor();
        if (precursorInfo == null) {
            precursorInfo = new PrecursorInfo();
            vars.curScan.setPrecursor(precursorInfo);
        }

        attr = attrs.getValue(ATTR.PRECURSOR_SCAN_NUM.name);
        if (attr != null) {
            precursorInfo.setParentScanRefRaw(attr.toString());
            try {
                int precursorScanNumInternal = mapRawNumToInternalScanNum(attr.toInt());
                precursorInfo.setParentScanNum(precursorScanNumInternal);
            } catch (FileParsingException fpe) {
                // We could not find a mapping, which means the file does NOT contain
                // the scan we're looking for - happens when MS1 scans are removed during conversion, for example.
                // In this case just leave it as null.
            }
        }
        attr = attrs.getValue(ATTR.FRAGMENTATION_METHOD.name);
        if (attr != null) {
            precursorInfo.getActivationInfo().setActivationMethod(attr.toString());
        }
        attr = attrs.getValue(ATTR.PRECURSOR_ISOLATION_WINDOW.name);
        Double isolationWindowWidth = null;
        if (attr != null) {
            isolationWindowWidth = attr.toDouble();
        }
        attr = attrs.getValue(ATTR.PRECURSOR_CHARGE.name);
        Integer charge = null;
        if (attr != null) {
            charge = attr.toInt();
        }
        // the next XML element must be just CHARACTERS, representing precursor mass
        eventType = reader.next();
        if (eventType != XMLStreamConstants.CHARACTERS) {
            throw new FileParsingException("PrecursorMz tag was not immediately followed by characters, representing precursor mass.");
        }
        attr = reader.getText();
        double precursorMz = attr.toDouble();
        precursorInfo.setMzTarget(precursorMz);
        precursorInfo.setCharge(charge);
        if (isolationWindowWidth == null) {
            isolationWindowWidth = 0d;
        }
        precursorInfo.setMzRangeStart(precursorMz - isolationWindowWidth/2d);
        precursorInfo.setMzRangeEnd(precursorMz + isolationWindowWidth/2d);

        return eventType;
    }

    private void tagScanStart(XMLStreamReaderImpl reader) throws FileParsingException {
        Attributes attrs;
        CharArray attr;
        numOpeningScanTagsFound += 1;
        if (vars.curScan != null) {
            addCurScanAndFlushVars();
        }
        attrs = reader.getAttributes();

        // these are required attributes, if they're not there, just throw an exception
        try {
            vars.scanNumRaw = attrs.getValue(ATTR.SCAN_NUM.name).toInt();
            vars.msLevel = attrs.getValue(ATTR.MS_LEVEL.name).toInt();
            vars.peaksCount = attrs.getValue(ATTR.PEAKS_COUNT.name).toInt();
        } catch (NumberFormatException e) {
            throw new FileParsingException("One of the required attributes for <scan> was missing", e);
        }

        // create a new scan stub for the parsed data
        int scanNumInternal = mapRawNumToInternalScanNum(vars.scanNumRaw);
        vars.curScan = new ScanDefault(scanNumInternal);
        vars.curScan.setMsLevel(vars.msLevel);
        //System.out.printf("Scan #%d, MS%d, has %d peaks\n", vars.scanNum, vars.msLevel, vars.peaksCount);
        //System.out.flush();

        // all other attributes are optional according to mzXML schema
        attr = attrs.getValue(ATTR.SCAN_TYPE.name);
        if (attr != null) {
            // if scanType was present, we'll try to parse it. If we don't have a corresponding value
            // in our enum, then it will be set to ScanType.UNKNOWN.
            // If the attribute was not present at all, this field remains null
            ScanType scanType = ScanType.fromString(attr.toString());
            vars.curScan.setScanType(scanType);
        }

        attr = attrs.getValue(ATTR.CENTROIDED.name);
        if (attr != null) {
            // msConvert incorrectly uses integers for representation of boolean
            // variables in xml. So we first try to parse it as text.
            if (attr.equals("1")) {
                vars.curScan.setCentroided(true);
            } else if (attr.equals("0")) {
                vars.curScan.setCentroided(false);
            } else {
                // in the end we try to use official parsing facilities
                vars.curScan.setCentroided(attr.toBoolean());
            }
        }
        attr = attrs.getValue(ATTR.POLARITY.name);
        if (attr != null) {
            if (attr.equals(Polarity.POSITIVE.toString())) {
                vars.curScan.setPolarity(Polarity.POSITIVE);
            } else if (attr.equals(Polarity.NEGATIVE.toString())) {
                vars.curScan.setPolarity(Polarity.NEGATIVE);
            }
        }

        attr = attrs.getValue(ATTR.ACTIVATION_ENERGY.name);
        if (attr != null) {
            PrecursorInfo precursorInfo = vars.curScan.getPrecursor();
            if (precursorInfo == null) {
                precursorInfo = new PrecursorInfo();
                vars.curScan.setPrecursor(precursorInfo);
            }
            precursorInfo.getActivationInfo().setActivationEnergyLo(attr.toDouble());
            precursorInfo.getActivationInfo().setActivationEnergyHi(attr.toDouble());
        }

        attr = attrs.getValue(ATTR.RT.name);
        if (attr != null) {
            vars.curScan.setRt(DATA_FACTORY.newDuration(attr.toString()).getTimeInMillis(new Date()) / 1000d / 60d);
        }
        // first check if we have instrument-level settings for the m/z range (i.e. what was in acquisition method)
        attr = attrs.getValue(ATTR.MZ_LO_INSTRUMENT.name);
        if (attr != null) {
            vars.curScan.setScanMzWindowLower(attr.toDouble());
        } else {
            // otherwise try to get the observed m/z values
            attr = attrs.getValue(ATTR.MZ_LO_OBSERVED.name);
            if (attr != null) {
                vars.curScan.setScanMzWindowLower(attr.toDouble());
            }
        }
        // same for high m/z value
        attr = attrs.getValue(ATTR.MZ_HI_INSTRUMENT.name);
        if (attr != null) {
            vars.curScan.setScanMzWindowUpper(attr.toDouble());
        } else {
            // otherwise try to get the observed m/z values
            attr = attrs.getValue(ATTR.MZ_HI_OBSERVED.name);
            if (attr != null) {
                vars.curScan.setScanMzWindowUpper(attr.toDouble());
            }
        }
        attr = attrs.getValue(ATTR.BASEPEAK_INTENSITY.name);
        if (attr != null) {
            vars.curScan.setBasePeakIntensity(attr.toDouble());
        }
        attr = attrs.getValue(ATTR.TIC.name);
        if (attr != null) {
            vars.curScan.setTic(attr.toDouble());
        }
        attr = attrs.getValue(ATTR.INSTRUMENT_ID.name);

        if (runInfo != null) {
            if (attr != null) {
                vars.curScan.setInstrument(runInfo.getInstrument(attr.toString()));
            } else {
                // we didn't find an instrument ID in the <scan>, so we should assume the default one
                vars.curScan.setInstrument(runInfo.getDefaultInstrument());
            }
        }
    }

    private void addCurScanAndFlushVars() {
        if (vars.curScan != null && vars.isPeaksTagReached) {
            if (source.isExcludeEmptyScans() && vars.peaksCount == 0) {
                // Don't add this Scan
            } else {
                // should this scan have a spectrumRef parsed? If so, then check if all important values were parsed
                // from scan info, otherwise calc them from the spectrum.
                ISpectrum spectrum = vars.curScan.getSpectrumRef().get();
                // the scan must have been created with StorageStrategy set to STRONG, so if we don't find the spectrum
                // here, it means, that spectrum parsing was not needed for this scan in the first place
                if (spectrum != null) {
                    if (vars.curScan.getTic() == null)               vars.curScan.setTic(spectrum.getSumInt());
                    if (vars.curScan.getScanMzWindowLower() == null) vars.curScan.setScanMzWindowLower(spectrum.getMinMZ());
                    if (vars.curScan.getScanMzWindowUpper() == null) vars.curScan.setScanMzWindowUpper(spectrum.getMaxMZ());
                    if (vars.curScan.getBasePeakIntensity() == null) vars.curScan.setBasePeakIntensity(spectrum.getMaxInt());
                }
                if (vars.curScan.isCentroided() == null) {
                    // if we could not find "centroided" attribute in the scan itself, we can resort to check the run header
                    if (runInfo != null && runInfo.isCentroided() != null) {
                        vars.curScan.setCentroided(runInfo.isCentroided());
                    }
                }
                parsedScans.add(vars.curScan);
            }
        }
        vars.reset();
    }

    protected CharArray fetchAttribute(Attributes attrs, ATTR attr) throws FileParsingException {
        CharArray value = attrs.getValue(attr.name);
        if (attr.isRequired && value == null) {
            throw new FileParsingException(String.format("%s attribute was not specified, but is required by mzXML schema.", attr.name));
        }
        return value;
    }

    private boolean doesNeedSpectrumParsing(IScan scan) {
        return subset.isInSubset(scan);
    }

    /**
     * Intended use: find the length of the last scan entry in the file. MzXML
     * might contain chromatograms after scans, and the index only contains the
     * offset of the last scan - there is no easy way to figure out what the
     * length is. If you just consider, that the length is from the offset to the
     * beginning of the index, then you might end up reading several hundred Mb
     * of chromatogram data.
     * To use this method properly, you should create a File-based stream (buffered),
     * starting at the offset of the last scan and this method will read the file
     * until it finds the corresponding closing tag.
     *
     * @return The length of the first scan entry in this stream, or, more precisely,
     * the offset in the stream of the end of the closing tag of the first 'scan' tag.
     * Or -1 if no matching pair of 'scan' tags was found.
     * @throws FileParsingException
     */
    int findThisStreamFirstScanLen() throws FileParsingException {
        int length = -1;
        numOpeningScanTagsFound = 0;
        XMLStreamReaderImpl reader = null;
        try {
            reader = (readerPool == null) ? new XMLStreamReaderImpl() : readerPool.borrowObject();
            reader.setInput(is, StandardCharsets.UTF_8.name());
            LogHelper.setJavolutionLogLevelFatal();

            int eventType = XMLStreamConstants.END_DOCUMENT;
            CharArray localName;
            do {
                // Read the next XML element
                try {
                    eventType = reader.next();
                } catch (XMLStreamException e) {
                    if (e instanceof XMLUnexpectedEndTagException) {
                        continue;
                    }
                    if (e instanceof XMLUnexpectedEndOfDocumentException) {
                        // this happens when we have nested <scan> tags and parsing not the whole MS1/MS2 child pairs
                        return length;
                    }
                    throw new FileParsingException(e);
                }


                // Process the read event
                switch (eventType) {

                    case XMLStreamConstants.START_ELEMENT:
                        localName = reader.getLocalName();

                        if (localName.contentEquals(TAG.SCAN.name)) {
                            numOpeningScanTagsFound += 1;
                            break;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        localName = reader.getLocalName();

                        if (localName.contentEquals(TAG.SCAN.name)) {
                            if (numOpeningScanTagsFound == 1) {
                                final XMLStreamReaderImpl.LocationImpl loc = reader.getLocation();
                                length = loc.getCharacterOffset() + loc.getBomLength();
                                return length;
                            }
                        }
                        break;
                }
            } while (eventType != XMLStreamConstants.END_DOCUMENT);


        } catch (Exception e) {
            throw new FileParsingException(e);
        } finally {
            // we need to return the reaer to the pool, if we borrowed it from there
            if (readerPool != null && reader != null) {
                try {
                    readerPool.returnObject(reader);
                } catch (Exception e) {
                    throw new FileParsingException(e);
                }
            }
        }


        return length;
    }

    /**
     * Given a scan internal number (spectrum index) goes to the index and tries to find a mapping.
     * @param rawScanNum the number as is in the scan tag
     * @return
     * @throws umich.ms.fileio.exceptions.FileParsingException
     */
    private int mapRawNumToInternalScanNum(int rawScanNum) throws FileParsingException {
        MZXMLIndexElement byRawNum = index.getByRawNum(rawScanNum);
        if (byRawNum == null) {
            String msg = String.format("Could not find a mapping from spectrum index"
                    + " ref to an internal scan number for"
                    + "\n\t file: %s"
                    + "\n\t raw scan num searched for: #%d"
                    + "\n\t raw scan num of the spectrum in which the error occured: #%d"
                    , source.getPath(), rawScanNum, vars.scanNumRaw);
            throw new FileParsingException(msg);
        }
        return byRawNum.getNumber();
    }


}
