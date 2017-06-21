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

import org.biojava.nbio.ontology.Term;
import org.biojava.nbio.ontology.Triple;
import umich.ms.datatypes.lcmsrun.Hash;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.lcmsrun.MsSoftware;
import umich.ms.datatypes.lcmsrun.OriginalFile;
import umich.ms.datatypes.scan.props.Instrument;
import umich.ms.fileio.exceptions.RunHeaderBoundsNotFound;
import umich.ms.fileio.exceptions.RunHeaderParsingException;
import umich.ms.fileio.filetypes.mzml.jaxb.*;
import umich.ms.fileio.filetypes.mzml.util.InstrumentModelCVTerm;
import umich.ms.fileio.filetypes.mzml.util.PSIMSCV;
import umich.ms.fileio.filetypes.mzxml.XmlBasedRunHeaderParser;
import umich.ms.fileio.filetypes.util.AbstractFile;
import umich.ms.fileio.filetypes.xmlbased.OffsetLength;
import umich.ms.logging.LogHelper;
import umich.ms.util.StringUtils;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Parses the run header of an mzML file.
 * @author Dmitry Avtonomov
 */
public class MZMLRunHeaderParser extends XmlBasedRunHeaderParser {

    protected MZMLFile source;

    public static final String TAG_MZML = "mzML";
    public static final String TAG_RUN = "run";
    //public static final String TAG_SPECTRUM_LIST = "spectrumList";
    //public static final String TAG_CHROMATOGRAM_LIST = "chromatogramList";


    public MZMLRunHeaderParser(MZMLFile source) {
        this.source = source;
        // Turn off non-critical Javolution logging
        LogHelper.setJavolutionLogLevelFatal();
    }

    @Override
    public LCMSRunInfo parse() throws RunHeaderParsingException {
        OffsetLength headerLocation;
        try {
            headerLocation = locateRunHeader(TAG_MZML, true, true, TAG_RUN, true, false);
        } catch (RunHeaderParsingException e) {
            if (e instanceof RunHeaderBoundsNotFound) {
                return LCMSRunInfo.getDummyRunInfo();
            }
            throw e;
        }
        MzMLType parsedInfo = parseHeaderWithJAXB(MzMLType.class, headerLocation);
        LCMSRunInfo runInfo = new LCMSRunInfo();


        // original files
        FileDescriptionType fileDescription = parsedInfo.getFileDescription();
        if (fileDescription != null) {
            final SourceFileListType sourceFileList = fileDescription.getSourceFileList();
            if (sourceFileList != null) {
                List<SourceFileType> sourceFiles = sourceFileList.getSourceFile();
                for (SourceFileType source : sourceFiles) {
                    String location = source.getLocation();
                    String name = source.getName();
                    Hash hash = null;
                    for (CVParamType cvParam : source.getCvParam()) {
                        if (PSIMSCV.MS_HASH_SHA1.accession.equals(cvParam.getAccession())) {
                            hash = new Hash(cvParam.getValue(), Hash.TYPE.SHA1);
                        } else if (PSIMSCV.MS_HASH_MD5.accession.equals(cvParam.getAccession())) {
                            hash = new Hash(cvParam.getValue(), Hash.TYPE.MD5);
                        }
                    }
                    runInfo.getOriginalFiles().add(new OriginalFile(location, name, hash));
                }
            }
        }


        // software
        final SoftwareListType softwareList = parsedInfo.getSoftwareList();
        if (softwareList != null) {
            List<SoftwareType> software = softwareList.getSoftware();
            for (SoftwareType soft : software) {
                String name = soft.getId();
                String id = soft.getId();
                String version = soft.getVersion();
                for (CVParamType cv : soft.getCvParam()) {
                    if (cv.getName() != null && !cv.getName().isEmpty())
                        name = cv.getName();
                }
                runInfo.getSoftware().add(new MsSoftware(name, version));
            }
        }


        final InstrumentConfigurationListType instrumentConfigurationList = parsedInfo.getInstrumentConfigurationList();
        if (instrumentConfigurationList != null) {
            final List<InstrumentConfigurationType> instruments = instrumentConfigurationList.getInstrumentConfiguration();
            if (instruments != null && instruments.size() > 0) {
                for (InstrumentConfigurationType i : instruments) {
                    String msInstrumentID;

                    if (i.getId() != null) {
                        msInstrumentID = i.getId();
                    } else {
                        throw new RunHeaderParsingException("Could not find instrument ID attribute. " +
                                "Should be <instrumentConfiguration id=\"xxx\">");
                    }

                    Instrument instrument = new Instrument();

                    boolean isInstrumentCvParamFound = false;
                    boolean isVendorCvParamFound = false;
                    if (!i.getCvParam().isEmpty()) {
                        for (CVParamType cvParam : i.getCvParam()) {
                            if (lookupInstrumentCV(cvParam, instrument)) isInstrumentCvParamFound = true;
                            if (lookupInstrumentVendor(cvParam, instrument)) isVendorCvParamFound = true;
                            if (PSIMSCV.MS_INSTRUMENT_SERIAL_NUMBER.accession.equals(cvParam.getAccession()))
                                instrument.setSerialNumber(cvParam.getValue());
                        }

                    }
                    if (!isInstrumentCvParamFound && !i.getReferenceableParamGroupRef().isEmpty()) {
                        // if there were no cvParams, there still might be a ReferenceableParamGroupType containing a cvParam
                        // with isntrument info
                        List<ReferenceableParamGroupRefType> refGrps = i.getReferenceableParamGroupRef();
                        for (ReferenceableParamGroupRefType refGrpRef : refGrps) {
                            ReferenceableParamGroupType refGrp = (ReferenceableParamGroupType) refGrpRef.getRef();
                            for (CVParamType cvParam : refGrp.getCvParam()) {
                                if (lookupInstrumentCV(cvParam, instrument)) isInstrumentCvParamFound = true;
                                if (lookupInstrumentVendor(cvParam, instrument)) isVendorCvParamFound = true;
                                if (PSIMSCV.MS_INSTRUMENT_SERIAL_NUMBER.accession.equals(cvParam.getAccession())) {
                                    instrument.setSerialNumber(cvParam.getValue());
                                }
                            }
                        }
                    }


                    if (!isInstrumentCvParamFound && !isVendorCvParamFound) {
                        instrument.setManufacturer(Instrument.UNKNOWN_MANUFACTURER);
                        instrument.setModel(Instrument.UNKNOWN_MODEL);
                        //throw new RunHeaderParsingException("Instrument configuration is "
                        //        + "supposed to have at least one CvParam/referenceableParamGroup with instrument name");
                    } else if (!isInstrumentCvParamFound && isVendorCvParamFound) {
                        instrument.setModel(Instrument.UNKNOWN_MODEL);
                    }

                    ComponentListType componentList = i.getComponentList();
                    if (componentList != null) {
                        String msAnalyzer = componentTypeListToString(componentList.getAnalyzer(), PSIMSCV.MAP_ANALYZER_TYPE);
                        instrument.setAnalyzer(msAnalyzer);
                        //String msDetector = componentTypeListToString(componentList.getDetector(), null);
                        instrument.setDetector("");
                        String ionization = componentTypeListToString(componentList.getSource(), PSIMSCV.MAP_IONIZATION_TYPE);
                        instrument.setIonisation(ionization);
                    }

                    runInfo.addInstrument(instrument, msInstrumentID);
                }
            }
        } else {
            // couldn't find a single instrument definition, add a dummy
            runInfo.addInstrument(Instrument.getDummy(), Instrument.ID_UNKNOWN);
        }

        // default instrument ID is a required attribute of <run> tag in mzML
        RunType run = parsedInfo.getRun();
        if (run == null) {
            throw new RunHeaderParsingException("Could not find <run> tag, which is needed to get default instrument ID");
        }
        Object defaultInstrumentConfigurationRef = run.getDefaultInstrumentConfigurationRef();
        if (!(defaultInstrumentConfigurationRef instanceof InstrumentConfigurationType)) {
            throw new RunHeaderParsingException("Could not find \"defaultInstrumentConfigurationRef\" attribute of " +
                    "<run> tag, it is required by mzML standard");
        }
        InstrumentConfigurationType instrument = (InstrumentConfigurationType) defaultInstrumentConfigurationRef;
        runInfo.setDefaultInstrumentID(instrument.getId());

        // get start date
        XMLGregorianCalendar startTimeStamp = run.getStartTimeStamp();
        if (startTimeStamp != null) {
            GregorianCalendar cal = startTimeStamp.toGregorianCalendar();
            Date date = cal.getTime();
            runInfo.setRunStartTime(date);
        }

        // try to get the default isCentroided from DataProcessing section
        List<DataProcessingType> dataProcessings = parsedInfo.getDataProcessingList().getDataProcessing();
        outerLoop:
        for (DataProcessingType dataProcessing : dataProcessings) {
            for (ProcessingMethodType processingMethodType : dataProcessing.getProcessingMethod()) {
                for (CVParamType cvParam : processingMethodType.getCvParam()) {
                    if (PSIMSCV.isPeakPickingTerm(cvParam.getAccession())) {
                        runInfo.setCentroided(true);
                        break outerLoop;
                    }
                }
            }
        }

        return runInfo;
    }

    private boolean lookupInstrumentCV(CVParamType cvParam, Instrument instrument) {
        InstrumentModelCVTerm instrumentModelCVTerm = PSIMSCV.instrumentFromAccession(cvParam.getAccession());
        if (instrumentModelCVTerm != null) {
            instrument.setManufacturer(instrumentModelCVTerm.vendor);
            instrument.setModel(instrumentModelCVTerm.model);
            return true;
        }
        return false;
    }

    private boolean lookupInstrumentVendor(CVParamType cvParam, Instrument instrument) {
        String accession = cvParam.getAccession();
        try {
            Term term = PSIMSCV.ONTOLOGY.getTerm(accession);
            Set<Triple> term_is_a = PSIMSCV.ONTOLOGY.getTriples(term, null, null);
            if (term_is_a.size() == 1) {
                Triple triple = term_is_a.iterator().next();
                Term object = triple.getObject();
                if (object.getName().equalsIgnoreCase(PSIMSCV.MS_INSTRUMENT_MODEL.accession)) {
                    String vendor = term.getDescription();
                    vendor = vendor.replaceAll(PSIMSCV.MS_INSTRUMENT_MODEL.description, "");
                    instrument.setManufacturer(vendor);
                    return true;
                }
            }

        } catch (NoSuchElementException e) {
            // nothing bad will happen if there is no such element
        }
        return false;
    }

    private String componentTypeListToString(List<? extends ComponentType> comps, Map<String, Term> whitelist) {
        List<String> strings = new ArrayList<>();
        for (ComponentType comp : comps) {
            List<CVParamType> cvParams = comp.getCvParam();
            for (CVParamType cvParam : cvParams) {
                String accession = cvParam.getAccession();
                if (accession.equals(PSIMSCV.MS_INSTRUMENT_COMPONENT_SOURCE.accession)
                    || accession.equals(PSIMSCV.MS_INSTRUMENT_COMPONENT_ANALYZER.accession)
                    || accession.equals(PSIMSCV.MS_INSTRUMENT_COMPONENT_DETECTOR.accession)) {
                    continue;
                }
                if (whitelist != null && !whitelist.containsKey(accession))
                        continue;
                strings.add(cvParam.getName());
            }
        }
        return StringUtils.join(strings, ", ");
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected <T> T convertJAXBObjectToDomain(Class<T> clazz, Object unmarshalled) throws RunHeaderParsingException {
        if (unmarshalled == null) {
            throw new RunHeaderParsingException("Unmarshalled run header object (mzML) was null");
        }
        if (!(unmarshalled instanceof JAXBElement)) {
            throw new RunHeaderParsingException("When parsing mzML run header, JAXB unmarshalling produced an unknown object");
        }
        JAXBElement jaxbElement = (JAXBElement) unmarshalled;
        if (jaxbElement.getValue() == null) {
            throw new RunHeaderParsingException("When parsing mzML run header, JAXB unmarshalled value was null");
        }
        Class<?> parsedClass = jaxbElement.getValue().getClass();
        if (!clazz.isAssignableFrom(parsedClass)) {
            throw new RunHeaderParsingException(String.format("When parsing mzML run header, " +
                    "JAXB object's declared type was wrong. Expected: %s; Found: %s",
                    clazz.getSimpleName(), parsedClass.getSimpleName()));
        }

        return (T) jaxbElement.getValue();
    }

    @Override
    protected InputStream getRunHeaderInputStream(OffsetLength msRunLocation) throws RunHeaderParsingException {
        try {
            RandomAccessFile raf = source.getRandomAccessFile();
            raf.seek(msRunLocation.offset);
            String closingTags = "</" + TAG_RUN + "></" + TAG_MZML + ">";
//            String closingTags = "</" + TAG_MZML + ">";
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
