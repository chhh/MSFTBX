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
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.List;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.props.Instrument;
import umich.ms.fileio.exceptions.RunHeaderBoundsNotFound;
import umich.ms.fileio.exceptions.RunHeaderParsingException;
import umich.ms.fileio.filetypes.mzxml.jaxb.MsRun;
import umich.ms.fileio.filetypes.mzxml.jaxb.OntologyEntryType;
import umich.ms.fileio.filetypes.util.AbstractFile;
import umich.ms.fileio.filetypes.xmlbased.OffsetLength;
import umich.ms.logging.LogHelper;

/**
 * Parses the header of the file to get isntrument information.
 * @author Dmitry Avtonomov
 */
public class MZXMLRunHeaderParser extends XmlBasedRunHeaderParser {
    protected MZXMLFile source;

    public static final String TAG_MSRUN = "msRun";
    public static final String TAG_SCAN = "scan";


    public MZXMLRunHeaderParser(MZXMLFile source) {
        this.source = source;
        // Turn off non-critical Javolution logging
        LogHelper.setJavolutionLogLevelFatal();
    }

    @Override
    public LCMSRunInfo parse() throws RunHeaderParsingException {
        OffsetLength msRunLocation;
        try {
            msRunLocation = locateRunHeader(TAG_MSRUN, true, true, TAG_SCAN, true, true);
        } catch (RunHeaderParsingException e) {
            if (e instanceof RunHeaderBoundsNotFound) {
                return LCMSRunInfo.getDummyRunInfo();
            }
            throw e;
        }

        MsRun parsedInfo = parseHeaderWithJAXB(MsRun.class, msRunLocation);
        LCMSRunInfo runInfo = new LCMSRunInfo();

        List<MsRun.MsInstrument> msInstruments = parsedInfo.getMsInstrument();
        if (msInstruments.size() > 0) {
            for (MsRun.MsInstrument i : msInstruments) {
                String msInstrumentID;
                if (i.getMsInstrumentID() != null) {
                    msInstrumentID = i.getMsInstrumentID();
                } else if (i.getStringInstrumentID() != null) {
                    msInstrumentID = i.getStringInstrumentID();
                } else if (msInstruments.size() == 1) {
                    // If there was just one instrument, but it had no ID, we'll assign it a fake ID and use that.
                    // If there's more than one isntrument and one of them has no ID, this is unrecoverable.
                    msInstrumentID = Instrument.ID_UNKNOWN;
                } else {
                    throw new RunHeaderParsingException("Could not find instrument ID attribute. " +
                            "Should be <msInstrument msInstrumentID='xxx'> or <msInstrument id='xxx'>");
                }
                Instrument instrument = new Instrument();

                MsRun.MsInstrument.MsManufacturer msManufacturer = i.getMsManufacturer();
                String manufacturer = msManufacturer != null ? msManufacturer.getValueOntologyEntryType() : Instrument.UNKNOWN_MANUFACTURER;
                instrument.setManufacturer(manufacturer);

                OntologyEntryType msModel = i.getMsModel();
                String model = msModel != null ? msModel.getValueOntologyEntryType() : Instrument.UNKNOWN_MODEL;
                instrument.setModel(model);

                MsRun.MsInstrument.MsMassAnalyzer msMassAnalyzer = i.getMsMassAnalyzer();
                String analyzer = msMassAnalyzer != null ? msMassAnalyzer.getValueOntologyEntryType() : Instrument.UNKNOWN_ANALYZER;
                instrument.setAnalyzer(analyzer);

                OntologyEntryType msDetector = i.getMsDetector();
                String detector = msDetector != null ? msDetector.getValueOntologyEntryType() : Instrument.UNKNOWN_DETECTOR;
                instrument.setDetector(detector);

                OntologyEntryType msIonisation = i.getMsIonisation();
                String ionisation = msIonisation != null ? msIonisation.getValueOntologyEntryType() : Instrument.UNKNOWN_IONISATION;
                instrument.setIonisation(ionisation);

                runInfo.addInstrument(instrument, msInstrumentID);
            }
        } else {
            // we didn't find any instrument descriptions in the file, so just set the instrument to some unknown.
            runInfo.addInstrument(Instrument.getDummy(), Instrument.ID_UNKNOWN);
        }

        List<MsRun.DataProcessing> dataProcessings = parsedInfo.getDataProcessing();
        for (MsRun.DataProcessing dataProcessing : dataProcessings) {
            if (dataProcessing.isCentroided() != null) {
                runInfo.setCentroided(dataProcessing.isCentroided());
                break;
            }
        }

        return runInfo;
    }


    @SuppressWarnings("unchecked")
    @Override
    protected <T> T convertJAXBObjectToDomain(Class<T> clazz, Object unmarshalled) throws RunHeaderParsingException {
        if (unmarshalled == null) {
            throw new RunHeaderParsingException("Unmarshalled run header object was null");
        }

        if (!(clazz.isAssignableFrom(unmarshalled.getClass()))) {
            throw new RunHeaderParsingException(String.format("When parsing mzML run header, " +
                            "JAXB object's declared type was wrong. Expected: %s; Found: %s",
                    clazz.getSimpleName(), unmarshalled.getClass().getSimpleName()));
        }
        return (T) unmarshalled;
    }


    /**
     * Reads the whole msRun header portion from the file, appending a closing {@code </msRun>} tag to the end,
     * so that it could be unmarshalled using JAXB without errors.
     * @param msRunLocation
     * @return
     * @throws umich.ms.fileio.exceptions.RunHeaderParsingException
     */
    @Override
    protected InputStream getRunHeaderInputStream(OffsetLength msRunLocation) throws RunHeaderParsingException {
        try {
            RandomAccessFile raf = source.getRandomAccessFile();
            raf.seek(msRunLocation.offset);

            String closingTags = "</" + TAG_MSRUN + ">";
            byte[] msRunCloseBytes = closingTags.getBytes(StandardCharsets.UTF_8);
            byte[] bytes = new byte[msRunLocation.length + msRunCloseBytes.length];

//            byte[] bytes = new byte[msRunLocation.length];

            raf.readFully(bytes, 0, bytes.length);
            System.arraycopy(msRunCloseBytes, 0, bytes, msRunLocation.length, msRunCloseBytes.length);

            return new BufferedInputStream(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            throw new RunHeaderParsingException(e);
        } finally {
            source.close();
        }
    }

    @Override
    public AbstractFile getAbstractFile() {
        return source;
    }

}
