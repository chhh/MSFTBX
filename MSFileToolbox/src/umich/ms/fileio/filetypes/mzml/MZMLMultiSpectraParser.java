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

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.zip.DataFormatException;
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
import umich.ms.datatypes.scan.props.*;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.datatypes.spectrum.impl.SpectrumDefault;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.mzml.util.PSIMSCV;
import umich.ms.fileio.filetypes.mzml.util.UnitsCV;
import umich.ms.fileio.filetypes.util.MultiSpectraParser;
import umich.ms.fileio.filetypes.xmlbased.IndexBuilder;
import umich.ms.fileio.filetypes.xmlbased.OffsetLength;
import umich.ms.logging.LogHelper;
import umich.ms.util.ByteArrayHolder;
import umich.ms.util.base64.Base64;
import umich.ms.util.base64.Base64Context;
import umich.ms.util.base64.Base64ContextPooled;

/**
 * Parses multiple spectra read from mzML file. One chunk of bytes should be given to it.
 * @author Dmitry Avtonomov
 */
@SuppressWarnings("unchecked")
public class MZMLMultiSpectraParser extends MultiSpectraParser {

    protected final MZMLFile source;
    protected LCMSRunInfo runInfo;
    protected MZMLIndex index;

    protected ArrayList<IScan> parsedScans;
    protected VarsHolder vars;
    protected ObjectPool<XMLStreamReaderImpl> readerPool = null;
    private int numOpeningScanTagsFound;

    protected static enum TAG {
        SPECTRUM("spectrum"),
        CV_PARAM("cvParam"),
        SCAN_LIST("scanList"),
        SCAN("scan"),
        PRECURSOR_LIST("precursorList"),
        PRECURSOR("precursor"),
        SELECTED_ION_LIST("selectedIonList"),
        SELECTED_ION("selectedIon"),
        ACTIVATION("activation"),
        BINARY_DATA_LIST("binaryDataArrayList"),
        BINARY_DATA_ARRAY("binaryDataArray"),
        BINARY("binary");

        public final String name;
        public final CharArray charArray;

        TAG(String name) {
            this.name = name;
            this.charArray = new CharArray(name);
        }
    }

    protected static enum ATTR {
        SPECTRUM_INDEX("index", true),
        SPECTRUM_ID("id", true),
        SPECTRUM_INSTRUMENT("instrumentConfigurationRef", false),
        SPECTRUM_DEFAULT_ARRAY_LENGTH("defaultArrayLength", true),
        CV_PARAM_ACCESSION("accession", true),
        CV_PARAM_VALUE("value", true),
        CV_PARAM_UNIT_ACCESSION("unitAccession", true),
        CV_PARAM_UNIT_NAME("unitName", true),
        PRECURSOR_SPEC_REF("spectrumRef", false)
        ;

        public final String name;
        public final boolean isRequired;

        ATTR(String name, boolean isRequired) {
            this.name = name;
            this.isRequired = isRequired;
        }
    }

    public MZMLMultiSpectraParser(InputStream is, LCMSDataSubset subset, MZMLFile source) throws FileParsingException {
        super(is, subset);
        this.source = source;
    }


    @Override
    public MZMLFile getSource() {
        return source;
    }

    public ObjectPool<XMLStreamReaderImpl> getReaderPool() {
        return readerPool;
    }

    public void setReaderPool(ObjectPool<XMLStreamReaderImpl> readerPool) {
        this.readerPool = readerPool;
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
        vars = new VarsHolder();

        XMLStreamReaderImpl reader = (readerPool == null) ? new XMLStreamReaderImpl() : readerPool.borrowObject();
        try {
            reader.setInput(is, StandardCharsets.UTF_8.name());
            LogHelper.setJavolutionLogLevelFatal();

            int eventType = XMLStreamConstants.END_DOCUMENT;
            CharArray localName, attr, val, unitAccession;
            Attributes attrs;
            PSIMSCV cvEntry;

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
                        if (parsedScans.size() == numOpeningScanTagsFound) {
                            if (numScansToProcess != null && parsedScans.size() != numScansToProcess) {
                                throw new FileParsingException("The number of read scans did not match the provided number" +
                                        "of scans to be read at method invocation. End of stream reached earlier.", e);
                            }
                            return parsedScans.isEmpty() ? null : parsedScans;
                        }
                    }
                    throw new FileParsingException(e);
                }


                // process the read event
                switch (eventType) {

                    case XMLStreamConstants.START_ELEMENT:
                        localName = reader.getLocalName();
                        attrs = reader.getAttributes();

                        // TODO: consider changing to .contentEquals(), that method doesn't store any references
                        if (localName.equals(TAG.SPECTRUM.name)) {
                            tagSpectrumStart(attrs);

                        } else if (localName.equals(TAG.CV_PARAM.name)) {
                            tagCvParamStart(attrs, reader);

                        } else if (localName.equals(TAG.SCAN.name)) {
                            tagSpectrumInstarumentStart(attrs);

                        } else if (localName.equals(TAG.BINARY_DATA_LIST.name)) {
                            tagBinaryDataListStart(reader, attrs);

                        } else if (localName.equals(TAG.PRECURSOR.name)) {
                            tagPrecursorStart(reader);
                        }
                        // END: case XMLStreamConstants.START_ELEMENT
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        localName = reader.getLocalName();

                        if (localName.equals(TAG.SPECTRUM.name)) {
                            addCurScanAndFlushVars();
                        }

                        break;
                }



            } while (eventType != XMLStreamConstants.END_DOCUMENT);

        } finally {
            if (readerPool != null && reader != null) {
                readerPool.returnObject(reader);
            }
        }




        return parsedScans;
    }

    private void tagPrecursorStart(XMLStreamReaderImpl reader) throws FileParsingException {
        int eventType;
        Attributes attrs;
        CharArray attr, localName, val;
        PSIMSCV cvEntry;

        if (flushVarsIfNoCurScan()) {
            return;
        }

        // read until the closing </precursor> tag
        PrecursorInfo precursorInfo = new PrecursorInfo();
        attrs = reader.getAttributes();
        attr = attrs.getValue(ATTR.PRECURSOR_SPEC_REF.name);
        if (attr != null) {
            precursorInfo.setParentScanRefRaw(attr.toString());
            try {
                int scanNumInternal = mapIdRefToInternalScanNum(attr);
                precursorInfo.setParentScanNum(scanNumInternal);
            } catch (FileParsingException fpe) {
                // we couldn't find a mapping, means that likely the parent scan is not in the run at all
                // this is not critical, so we just leave it as null and store the precursor scan number / scanRef
                // as a string in the PrecursorInfo#parentScanRefRaw
            }
        }

        eventType = XMLStreamConstants.END_DOCUMENT;
        localName = TAG.PRECURSOR.charArray;

        do {
            try {
                eventType = reader.next();
                if (eventType != XMLStreamConstants.START_ELEMENT && eventType != XMLStreamConstants.END_ELEMENT) {
                    continue;
                }
            } catch (XMLStreamException e) {
                if (e instanceof XMLUnexpectedEndTagException) {
                    continue;
                }
                throw new FileParsingException(e);
            }
            localName = reader.getLocalName();

            if (eventType == XMLStreamConstants.START_ELEMENT) {
                attrs = reader.getAttributes();
                if (localName.equals(TAG.CV_PARAM.name)) {

                    attr = attrs.getValue(ATTR.CV_PARAM_ACCESSION.name);
                    val = attrs.getValue(ATTR.CV_PARAM_VALUE.name);
                    if (attr == null) {
                        throw new FileParsingException("cvParam did not have an 'accession' or 'value' " +
                                "attribute, which are required");
                    }

                    cvEntry = PSIMSCV.fromAccession(attr);
                    if (cvEntry == null) {
                        // check for activation method
                        String activationMethod = PSIMSCV.activationMethodFromAccession(attr);
                        if (activationMethod != null) {
                            precursorInfo.setActivationMethod(activationMethod);
                        }
                        break;
                    }
                    switch (cvEntry) {
                        case MS_PRECURSOR_ISO_WND_TARGET:
                            vars.precursorIsoWndTarget = val.toDouble();
                            break;
                        case MS_PRECURSOR_INTENSITY:
                            vars.precursorIntensity = val.toDouble();
                            precursorInfo.setIntensity(vars.precursorIntensity);
                            break;
                        case MS_PRECURSOR_ISO_WND_LO_OFFSET:
                            vars.precursorIsoWndLoOffset = val.toDouble();
                            break;
                        case MS_PRECURSOR_ISO_WND_HI_OFFSET:
                            vars.precursorIsoWndHiOffset = val.toDouble();
                            break;
                        case MS_PRECURSOR_ISO_WND_LO_OBSOLETE:
                            precursorInfo.setMzRangeStart(val.toDouble());
                            break;
                        case MS_PRECURSOR_ISO_WND_HI_OBSOLETE:
                            precursorInfo.setMzRangeEnd(val.toDouble());
                            break;
                        case MS_PRECURSOR_MZ:
                            precursorInfo.setMzTarget(val.toDouble());
                            break;
                        case MS_PRECURSOR_CHARGE:
                            precursorInfo.setCharge(val.toInt());
                            break;
                        case MS_PRECURSOR_COLLISION_ENERGY:
                            // we don't use those
                            break;
                    }
                }
            }

        } while (!(eventType == XMLStreamConstants.END_ELEMENT && localName.equals(TAG.PRECURSOR.name)));
        // now we've reached the end of <precursor> tag, so we can check if precursor isolation window
        // bounds were found
                            if (precursorInfo.getMzRangeStart() == null) {
                                if (vars.precursorIsoWndLoOffset != null) {
                                    if (vars.precursorIsoWndTarget != null) {
                                        precursorInfo.setMzRangeStart(vars.precursorIsoWndTarget - vars.precursorIsoWndLoOffset);
                                    } else if (precursorInfo.getMzTarget() != null) {
                                        precursorInfo.setMzRangeStart(precursorInfo.getMzTarget() - vars.precursorIsoWndLoOffset);
                                    }
                                } else if (precursorInfo.getMzTarget() != null) {
                                    precursorInfo.setMzRangeStart(precursorInfo.getMzTarget());
                                }
                            }
        if (precursorInfo.getMzRangeEnd() == null) {
                                if (vars.precursorIsoWndHiOffset != null) {
                                    if (vars.precursorIsoWndTarget != null) {
                                        precursorInfo.setMzRangeEnd(vars.precursorIsoWndTarget + vars.precursorIsoWndHiOffset);
                                    } else if (precursorInfo.getMzTarget() != null) {
                                        precursorInfo.setMzRangeEnd(precursorInfo.getMzTarget() + vars.precursorIsoWndHiOffset);
                                    }
                                } else if (precursorInfo.getMzTarget() != null) {
                                    precursorInfo.setMzRangeEnd(precursorInfo.getMzTarget());
                                }
        }
        vars.precursors.add(precursorInfo);
        vars.curScan.setPrecursor(precursorInfo);
    }

    private void tagBinaryDataListStart(XMLStreamReaderImpl reader, Attributes attrs) throws FileParsingException, DataFormatException, IllegalStateException, IOException {
        int eventType;
        CharArray localName, attr;
        PSIMSCV cvEntry;
        if (flushVarsIfNoCurScan() || !doesNeedSpectrumParsing(vars.curScan)) {
            return;
        }

        // read until the closing </binaryDataArrayList> tag
        eventType = XMLStreamConstants.END_DOCUMENT;
        localName = TAG.BINARY_DATA_LIST.charArray;

        do {
            try {
                eventType = reader.next();
                if (eventType != XMLStreamConstants.START_ELEMENT && eventType != XMLStreamConstants.END_ELEMENT) {
                    // we're only interested in data enclosed in tags
                    // we will call .next() to read Base64 encoded values manually later down
                    // this code path
                    continue;
                }
            } catch (XMLStreamException e) {
                if (e instanceof XMLUnexpectedEndTagException)
                    continue;
                throw new FileParsingException(e);
            }
            localName = reader.getLocalName();


            if (eventType == XMLStreamConstants.START_ELEMENT) {
                if (localName.equals(TAG.CV_PARAM.name)) {

                    attr = attrs.getValue(ATTR.CV_PARAM_ACCESSION.name);
                    if (attr == null) {
                        throw new FileParsingException("cvParam did not have an 'accession' or 'value' " +
                                "attribute, which are required");
                    }

                    cvEntry = PSIMSCV.fromAccession(attr);
                    if (cvEntry == null) {
                        break; // we don't care about unknown cv entries
                    }
                    switch (cvEntry) {
                        case MS_PRECISION_32:
                            vars.precision = 32;
                            break;
                        case MS_PRECISION_64:
                            vars.precision = 64;
                            break;
                        case MS_COMPRESSION_ZLIB:
                            vars.getCompressions().add(PeaksCompression.ZLIB);
                            break;
                        case MS_COMPRESSION_NONE:
                            vars.getCompressions().add(PeaksCompression.NONE);
                            break;
                        case MS_COMPRESSION_NUMPRESS_LIN_PRED:
                            vars.getCompressions().add(PeaksCompression.NUMPRESS_LINPRED);
                            break;
                        case MS_COMPRESSION_NUMPRESS_LOG_FLOAT:
                            vars.getCompressions().add(PeaksCompression.NUMPRESS_SHLOGF);
                            break;
                        case MS_COMPRESSION_NUMPRESS_POS_INT:
                            vars.getCompressions().add(PeaksCompression.NUMPRESS_POSINT);
                            break;
                        case MS_DATA_ARRAY_MZ:
                            vars.binDataType = VarsHolder.BIN_DATA_TYPE.MZ;
                            break;
                        case MS_DATA_ARRAY_INTENSITY:
                            vars.binDataType = VarsHolder.BIN_DATA_TYPE.INTENSITY;
                            break;
                    }



                } else if (localName.equals(TAG.BINARY.name)) {
                    try {
                        eventType = reader.next();
                        if (eventType != XMLStreamConstants.CHARACTERS) {

                            if (eventType == XMLStreamConstants.END_ELEMENT) {
                                localName = reader.getLocalName();
                                if (localName.equals(TAG.BINARY.name)) {
                                    // we hit a closing BINARY tag immediately after the opening one - empty array
                                    switch (vars.binDataType) {
                                        case MZ:
                                            vars.mzData = MZMLPeaksDecoder.DecodedData.createEmpty();
                                            break;
                                        case INTENSITY:
                                            vars.intensityData = MZMLPeaksDecoder.DecodedData.createEmpty();
                                            break;
                                        default:
                                            throw new IllegalStateException("Binary data was decoded, but we did not find" +
                                                    "a specification if this was mz or intensity data.");
                                    }
                                }
                            } else {
                                throw new FileParsingException("Binary data tag <binary> wasn't immediately " +
                                        "followed by Base64 encoded string");
                            }

                        } else {
                            //The new way, Base64 decoder is based on Apache Commons implementation,
                            // but reuses internal buffers, taking them from the pool
                            Base64 base64 = new Base64();
                            //Base64Context ctx = new Base64Context();   // don't use regular contexts, underlying buffers will be always re-allocated
                            Base64Context ctx = new Base64ContextPooled(); // this ctx will borrow a ByteArrayHolder from pool
                            CharArray chars = reader.getText();
                            Base64Context decodedB64 = base64.decode(chars.array(), chars.offset(), chars.length(), ctx);
                            ByteArrayHolder bah = decodedB64.readResults();


                            MZMLPeaksDecoder.DecodedData decoded = MZMLPeaksDecoder.decode(
                                    bah.getUnderlyingBytes(), bah.getPosition(), vars.precision,
                                    vars.defaultArrayLength, vars.getCompressions());
                            ctx.close(); // close the context, so it could return it's ByteArrayHolder to the pool
                            switch (vars.binDataType) {
                                case MZ:
                                    vars.mzData = decoded;
                                    break;
                                case INTENSITY:
                                    vars.intensityData = decoded;
                                    break;
                                default:
                                    throw new IllegalStateException("Binary data was decoded, but we did not find" +
                                            "a specification if this was mz or intensity data.");
                            }
                        }
                        vars.flushBinDataDescription();
                    } catch (XMLStreamException e) {
                        throw new FileParsingException(e);
                    }

                }
            }

        } while (!(eventType == XMLStreamConstants.END_ELEMENT && localName.equals(TAG.BINARY_DATA_LIST.name)));

        // now we've reached the end of <binaryDataArrayList> tag
        double basePeakMz = vars.intensityData.valMaxPos < 0 ? 0d : vars.mzData.arr[vars.intensityData.valMaxPos];
        ISpectrum spectrum = new SpectrumDefault(
                vars.mzData.arr, vars.intensityData.arr,
                vars.intensityData.valMin, vars.intensityData.valMinNonZero,
                vars.intensityData.valMax, basePeakMz,
                vars.intensityData.sum);
        vars.curScan.setSpectrum(spectrum, false);
    }

    private void tagSpectrumInstarumentStart(Attributes attrs) throws FileParsingException {
        CharArray attr;
        // now check if there was a specific instrument specified for this spectrum,
        // otherwise use the default instrument configuration ref.
        attr = attrs.getValue(ATTR.SPECTRUM_INSTRUMENT.name);
        Instrument instrument = runInfo.getDefaultInstrument();
        if (attr != null) {
            instrument = runInfo.getInstrument(attr.toString());
            if (instrument == null) {
                throw new FileParsingException(String.format("An instrument ref was not present for " +
                        "scan  index #%d, but run header did not contain that ref.", vars.spectrumIndex));
            }
        }
        vars.curScan.setInstrument(instrument);
    }

    private void tagCvParamStart(Attributes attrs, XMLStreamReaderImpl reader) throws FileParsingException {
        CharArray attr, val;
        PSIMSCV cvEntry;
        if (flushVarsIfNoCurScan()) {
            return;
        }

        // these are some general CVs, that we can safely parse and assign to the scan
        // all other attributes are optional according to mzXML schema
        attr = attrs.getValue(ATTR.CV_PARAM_ACCESSION.name);
        val = attrs.getValue(ATTR.CV_PARAM_VALUE.name);
        if (attr == null) {
            throw new FileParsingException("cvParam did not have an 'accession' attribute, which is required");
        }
        //cvEntry = PSIMSCV.fromAccession(attr.toString());
        cvEntry = PSIMSCV.fromAccession(attr);
        if (cvEntry == null) {
            // we don't care about unknown cv entries
            return;
        }
        switch (cvEntry) {
            case MS_LEVEL:
                vars.curScan.setMsLevel(val.toInt());
                break;
            case MS_POLARITY_POS_OBSOLETE:
            case MS_POLARITY_POS:
                vars.curScan.setPolarity(Polarity.POSITIVE);
                break;
            case MS_POLARITY_NEG_OBSOLETE:
            case MS_POLARITY_NEG:
                vars.curScan.setPolarity(Polarity.NEGATIVE);
                break;
            case MS_CENTROIDED:
                vars.curScan.setCentroided(true);
                break;
            case MS_PROFILE:
                vars.curScan.setCentroided(false);
                break;
            case MS_MZ_OBSERVED_LO:
            case MS_MZ_OBSERVED_LO_INST_SETTING:
                vars.curScan.setScanMzWindowLower(val.toDouble());
                break;
            case MS_MZ_OBSERVED_HI:
            case MS_MZ_OBSERVED_HI_INST_SETTING:
                vars.curScan.setScanMzWindowUpper(val.toDouble());
                break;
            case MS_BASEPEAK_MZ:
                vars.curScan.setBasePeakMz(val.toDouble());
                break;
            case MS_BASEPEAK_INTENSITY:
                vars.curScan.setBasePeakIntensity(val.toDouble());
                break;
            case MS_TIC:
                vars.curScan.setTic(val.toDouble());
                break;
            case MS_SCAN_TYPE_FULL:
                vars.curScan.setScanType(ScanType.FULL);
                break;
            case MS_SCAN_TYPE_CRM:
                vars.curScan.setScanType(ScanType.CRM);
                break;
            case MS_SCAN_TYPE_SRM:
                vars.curScan.setScanType(ScanType.CRM);
                break;
            case MS_SCAN_TYPE_SIM:
                vars.curScan.setScanType(ScanType.SIM);
                break;
            case MS_SCAN_TYPE_ZOOM:
                vars.curScan.setScanType(ScanType.ZOOM);
                break;
            case MS_ION_INJECTION_TIME:
                // cvParams for RT should contain time units
                cvEntry = PSIMSCV.UO_MILLISECONDS; // default assumption
                attr = attrs.getValue(ATTR.CV_PARAM_UNIT_ACCESSION.name);
                if (attr != null) {
                    cvEntry = PSIMSCV.fromAccession(attr.toString());
                }
                if (cvEntry == null) {
                    throw new FileParsingException(String.format(
                            "Unknown ion injection time units accession encountered: '%s', claims to be: '%s'",
                            attr.toString(), attrs.getValue(ATTR.CV_PARAM_UNIT_NAME.name).toString()));
                }
                InjectionInfo injectionInfo = vars.curScan.getInjectionInfo();
                if (injectionInfo == null) {
                    injectionInfo = new InjectionInfo();
                    vars.curScan.setInjectionInfo(injectionInfo);
                }
                switch (cvEntry) {
                    case UO_MILLISECONDS:
                        vars.curScan.setRt(val.toDouble());
                        break;
                    case UO_MICROSECONDS:
                        vars.curScan.setRt(val.toDouble() * 1e6d);
                        break;
                    case UO_NANOSECONDS:
                        vars.curScan.setRt(val.toDouble() * 1e9d);
                        break;
                    case UO_SECONDS:
                        injectionInfo.setDuration(val.toDouble() * 1e3d);
                        break;
                    case UO_MINUTES:
                        vars.curScan.setRt(val.toDouble() * 60d * 1e3d);
                        break;
                    case UO_HOURS:
                        vars.curScan.setRt(val.toDouble() * 60d * 1e6d);
                        break;
                }

            case MS_RT_SCAN_START:
            case MS_RT_RETENTION_TIME:
            case MS_RT_RETENTION_TIME_LOCAL:
            case MS_RT_RETENTION_TIME_NORMALIZED:
                // cvParams for RT should contain time units
                cvEntry = PSIMSCV.UO_SECONDS; // default assumption
                attr = attrs.getValue(ATTR.CV_PARAM_UNIT_ACCESSION.name);
                if (attr != null) {
                    cvEntry = PSIMSCV.fromAccession(attr.toString());
                }
                if (cvEntry == null) {
                    throw new FileParsingException(String.format(
                            "Unknown RT time units accession encountered: '%s', claims to be: '%s'",
                            attr.toString(), attrs.getValue(ATTR.CV_PARAM_UNIT_NAME.name).toString()));
                }
                switch (cvEntry) {
                    case UO_SECONDS:
                        vars.curScan.setRt(val.toDouble() / 60d);
                        break;
                    case UO_MINUTES:
                        vars.curScan.setRt(val.toDouble());
                        break;
                    case UO_HOURS:
                        vars.curScan.setRt(val.toDouble() * 60d);
                        break;
                    case UO_MILLISECONDS:
                        vars.curScan.setRt(val.toDouble() * 1e3d);
                        break;
                    case UO_MICROSECONDS:
                        vars.curScan.setRt(val.toDouble() * 1e6d);
                        break;
                    case UO_NANOSECONDS:
                        vars.curScan.setRt(val.toDouble() * 1e9d);
                        break;
                }
                break;
        }


//        attr = attrs.getValue(ATTR.INSTRUMENT_ID.name);
//
//        if (runInfo != null) {
//            if (attr != null) {
//                vars.curScan.setInstrument(runInfo.getInstrument(attr.toString()));
//            } else {
//                // we didn't find an instrument ID in the <scan>, so we should assume the default one
//                vars.curScan.setInstrument(runInfo.getDefaultInstrument());
//            }
//        }
//        break;

    }

    private void tagSpectrumStart(Attributes attrs) throws FileParsingException {
        numOpeningScanTagsFound += 1;
        if (vars.curScan != null) {
            addCurScanAndFlushVars();
        }

        // these are required attributes, if they're not there, just throw an exception
        try {
            vars.spectrumIndex = attrs.getValue(ATTR.SPECTRUM_INDEX.name).toInt();
            //vars.scanNum = parseScanNumFromId(attrs.getValue(ATTR.SPECTRUM_ID.name));
            vars.defaultArrayLength = attrs.getValue(ATTR.SPECTRUM_DEFAULT_ARRAY_LENGTH.name).toInt();
        } catch (NumberFormatException e) {
            throw new FileParsingException("One of the required attributes for <scan> was missing", e);
        }

        // create a new scan stub for the parsed data
        int scanNumInternal = mapRawNumToInternalScanNum(vars.spectrumIndex);
        vars.curScan = new ScanDefault(scanNumInternal);
        //System.out.printf("Scan #%d, MS%d, has %d peaks\n", vars.scanNum, vars.defaultArrayLength);
        //System.out.flush();
    }

    /**
     * Intended use: find the length of the last scan entry in the file. MzXML
     * might contain chromatograms after scans, and the index only contains the
     * offset of the last scan - there is no easy way to figure out what the
     * length is. If you just consider, that the length is from the offset to the
     * beginning of the index, then you might end up reading several hundred Mb
     * of chromatogram data.<br/>
     * To use this method properly, you should create a File-based stream (buffered),
     * starting at the offset of the last scan and this method will read the file until
     * it finds the corresponding closing tag.
     *
     * @return The length of the first scan entry in this stream, or, more precisely,
     * the offset in the stream of the end of the closing tag of the first 'scan' tag.<br/>
     * Or -1 if no matching pair of 'scan' tags was found.
     * @throws umich.ms.fileio.exceptions.FileParsingException
     */
    public int findThisStreamFirstScanLen() throws FileParsingException {
        int length = -1;
        numOpeningScanTagsFound = 0;
        vars = new VarsHolder();

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


                // process the read event
                switch (eventType) {

                    case XMLStreamConstants.START_ELEMENT:
                        localName = reader.getLocalName();

                        if (localName.equals(TAG.SPECTRUM.name)) {
                            numOpeningScanTagsFound += 1;
                            break;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        localName = reader.getLocalName();

                        if (localName.equals(TAG.SPECTRUM.name)) {
                            if (numOpeningScanTagsFound == 1) {
                                length = reader.getLocation().getCharacterOffset();
                                return length;
                            }
                        }
                        break;

                }
            } while (eventType != XMLStreamConstants.END_DOCUMENT);

        } catch (XMLStreamException | DataFormatException | IOException e) {
            throw new FileParsingException(e);
        } catch (Exception e) {
            throw new FileParsingException(e);
        } finally {
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
     * For use with Executors, consider using  instead of calling this method directly.
     * @param info info about offsets in file and in currently read buffer
     * @return
     * @throws FileParsingException
     */
    public IndexBuilder.Result<MZMLIndexElement> buildIndex(final IndexBuilder.Info info) throws Exception {
        final long offsetInFile = info.offsetInFile;
        final long offsetInBuffer = info.offsetInBuffer;
        IndexBuilder.Result<MZMLIndexElement> result = new IndexBuilder.Result<>(info);

        numOpeningScanTagsFound = 0;
        vars = new VarsHolder();

        XMLStreamReaderImpl reader = (readerPool == null) ? new XMLStreamReaderImpl() : readerPool.borrowObject();
        try {
            reader.setInput(is, StandardCharsets.UTF_8.name());
            LogHelper.setJavolutionLogLevelFatal();

            int eventType = XMLStreamConstants.END_DOCUMENT;
            CharArray localName, attr, val, unitAccession;
            Attributes attrs;
            PSIMSCV cvEntry;

            do {
                // Read the next XML element
                try {
                    eventType = reader.next();
                } catch (XMLStreamException e) {
                    if (e instanceof XMLUnexpectedEndTagException) {
                        eventType = XMLStreamConstants.END_ELEMENT;
                        continue;
                    }
                    if (e instanceof XMLUnexpectedEndOfDocumentException) {
                        // as we're reading arbitrary chunks of file, we will almost always finish parsing by hitting this condition
                        if (vars.offset != null) {
                            addCurIndexElementAndFlushVars(result, offsetInFile, offsetInBuffer);
                        }
                        return result;
                    }
                    throw new FileParsingException(e);
                }


                // process the read event
                switch (eventType) {

                    case XMLStreamConstants.START_ELEMENT:
                        localName = reader.getLocalName();
                        attrs = reader.getAttributes();

                        if (localName.equals(TAG.SPECTRUM.name)) {
                            numOpeningScanTagsFound += 1;
                            if (vars.offset != null) {
                                // this means we've encountered nested Spectrum tags
                                long lastStartTagPos = reader.getLocation().getLastStartTagPos();
                                vars.length = (int)(vars.offset - lastStartTagPos);
                                addCurIndexElementAndFlushVars(result, offsetInFile, offsetInBuffer);
                            }

                            // these are required attributes, if they're not there, just throw an exception
                            try {
                                vars.spectrumIndex = attrs.getValue(ATTR.SPECTRUM_INDEX.name).toInt();
                                if (vars.spectrumIndex >= 90 && vars.spectrumIndex <= 95) {
                                    int a = 1;
                                }
                                vars.spectrumId = attrs.getValue(ATTR.SPECTRUM_ID.name).toString();

                                long lastStartTagPos = reader.getLocation().getLastStartTagPos();
                                vars.offset = lastStartTagPos;

                            } catch (NumberFormatException e) {
                                throw new FileParsingException("Malformed scan number while building index", e);
                            }
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        localName = reader.getLocalName();

                        if (localName.equals(TAG.SPECTRUM.name)) {
                            vars.length = (int)(reader.getLocation().getCharacterOffset() - vars.offset);
                            addCurIndexElementAndFlushVars(result, offsetInFile, offsetInBuffer);
                        }

                        break;

                    case XMLStreamConstants.END_DOCUMENT:
                        break;
                }



            } while (eventType != XMLStreamConstants.END_DOCUMENT);

        } catch (Exception e) {
            int a = 1;
        }
        finally {
            if (readerPool != null && reader != null) {
                readerPool.returnObject(reader);
            }
        }

        return result;
    }

    private void addCurIndexElementAndFlushVars(IndexBuilder.Result<MZMLIndexElement> result, long offsetInFile, long offsetInBuffer) {

        if (vars.spectrumIndex == null || vars.spectrumId == null || vars.offset == null) {
            throw new IllegalStateException("When building index some variables were not set");
        }

        int len = vars.length != null ? vars.length : -1;
        OffsetLength offsetLength = new OffsetLength(offsetInFile + offsetInBuffer + vars.offset, len);
        MZMLIndexElement idxElem = new MZMLIndexElement(vars.spectrumIndex + 1, vars.spectrumIndex, vars.spectrumId, offsetLength);
        if (len != -1) {
            result.addIndexElement(idxElem);
        } else {
            result.addUnfinishedIndexElement(idxElem);
        }

        vars.resetToDefaults();
    }

    protected void addCurScanAndFlushVars() {
        if (vars.curScan != null) {
            if (source.isExcludeEmptyScans() && vars.curScan.getSpectrum() != null && vars.curScan.getSpectrum().getMZs().length == 0) {
                // skip this scan, don't add it
            } else {
                if (vars.precursors.size() > 1) {
                    System.err.printf("Found multiple precursors for scan #%d, this is not really supported", vars.curScan.getNum());
                }
                if (vars.curScan.getInstrument() == null) {
                    // if the instrument was not set, we'll set it to a default one
                    vars.curScan.setInstrument(runInfo.getDefaultInstrument());
                }
                parsedScans.add(vars.curScan);
            }
        }
        vars.resetToDefaults();
    }

    /**
     * If the current {@code vars} variable doesn't contain a scan, that we've started processing, then flushes all
     * the fields in {@code vars} and returns true, otherwise does nothing and returns false.
     * @return true, if vars were flushed (no scan was set). false if the scan is set.
     */
    protected boolean flushVarsIfNoCurScan() {
        if (vars.curScan == null) {
            vars.resetToDefaults();
            return true;
        }
        return false;
    }

    /**
     * Given a scan ID goes to the index and tries to find a mapping.
     * @param id
     * @return
     * @throws umich.ms.fileio.exceptions.FileParsingException in case the mapping can't be done
     */
    protected int mapIdRefToInternalScanNum(CharArray id) throws FileParsingException {
        String idStr = id.toString();
        MZMLIndexElement byId = index.getById(idStr);
        if (byId == null) {
            String msg = String.format("Could not find a mapping from spectrum id"
                    + " ref to an internal scan number for"
                    + "\n\t file: %s"
                    + "\n\t spectrum index of the spectrum in which the error occured: #%d"
                    + "\n\t idRef searched for: %s", source.getPath(), vars.spectrumIndex, idStr);
            throw new FileParsingException(msg);
        }
        return byId.getNumber();
    }

    /**
     * Given a scan internal number (spectrum index) goes to the index and tries to find a mapping.
     * @param spectrumIndex
     * @return
     * @throws umich.ms.fileio.exceptions.FileParsingException
     */
    protected int mapRawNumToInternalScanNum(int spectrumIndex) throws FileParsingException {
        MZMLIndexElement byRawNum = index.getByRawNum(spectrumIndex);
        if (byRawNum == null) {
            String msg = String.format("Could not find a mapping from spectrum index"
                    + " ref to an internal scan number for"
                    + "\n\t file: %s"
                    + "\n\t spectrum index searched for: #%d"
                    + "\n\t spectrum index of the spectrum in which the error occured: #%d"
                    , source.getPath(), spectrumIndex, vars.spectrumIndex);
            throw new FileParsingException(msg);
        }
        return byRawNum.getNumber();
    }

    protected boolean doesNeedSpectrumParsing(IScan scan) {
        return subset.isInSubset(scan);
    }

    private static class VarsHolder {
        ScanDefault curScan;
        Integer defaultArrayLength;
        Integer spectrumIndex;
        String spectrumId;
        Integer precursorSpectrumIndex;
        String activationMethodAbbreviation;
        List<PrecursorInfo> precursors;
        Double precursorIsoWndTarget;
        Double precursorIsoWndLoOffset;
        Double precursorIsoWndHiOffset;
        Double precursorIntensity;

        // vars for Index Building
        Long offset;
        Integer length;

        public enum BIN_DATA_TYPE{MZ, INTENSITY};
        Integer precision;
        EnumSet<PeaksCompression> compressions;
        BIN_DATA_TYPE binDataType;
        MZMLPeaksDecoder.DecodedData mzData;
        MZMLPeaksDecoder.DecodedData intensityData;

        public VarsHolder() {
            resetToDefaults();
        }

        public void flushBinDataDescription() {
            precision = null;
            compressions = null;
            binDataType = null;
        }

        /**
         * Resets all held variables to their default values.
         */
        public final void resetToDefaults() {
            curScan = null;
            defaultArrayLength = null;
            spectrumIndex = null;
            spectrumId = null;
            precursorSpectrumIndex = null;
            activationMethodAbbreviation = null;
            precursors = new ArrayList<>(1);
            precursorIsoWndTarget = null;
            precursorIsoWndLoOffset = null;
            precursorIsoWndHiOffset = null;
            precursorIntensity = null;

            offset = null;
            length = null;

            precision = null;
            compressions = null;
            binDataType = null;
            mzData = null;
            intensityData = null;
        }

        public EnumSet<PeaksCompression> getCompressions() {
            if (compressions == null) {
                compressions = EnumSet.noneOf(PeaksCompression.class);
            }
            return compressions;
        }
    }

    public MZMLIndexBuilder getIndexBuilder(IndexBuilder.Info info) {
        return new MZMLIndexBuilder(info);
    }


    public class MZMLIndexBuilder implements IndexBuilder<MZMLIndexElement>  {
        Info info;

        public MZMLIndexBuilder(Info info) {
            this.info = info;
        }

        @Override
        public Result<MZMLIndexElement> buildIndex(Info info) throws Exception {
            return MZMLMultiSpectraParser.this.buildIndex(info);
        }

        @Override
        public Result<MZMLIndexElement> call() throws Exception {
            return buildIndex(info);
        }
    }
}
