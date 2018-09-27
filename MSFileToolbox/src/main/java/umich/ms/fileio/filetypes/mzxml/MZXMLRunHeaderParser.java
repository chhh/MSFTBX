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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamReader;
import umich.ms.datatypes.lcmsrun.Hash;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.lcmsrun.MsSoftware;
import umich.ms.datatypes.lcmsrun.OriginalFile;
import umich.ms.datatypes.scan.props.Instrument;
import umich.ms.fileio.exceptions.RunHeaderParsingException;
import umich.ms.fileio.filetypes.mzml.MZMLRunInfo;
import umich.ms.fileio.filetypes.mzxml.jaxb.MsRun;
import umich.ms.fileio.filetypes.mzxml.jaxb.OntologyEntryType;
import umich.ms.fileio.filetypes.mzxml.jaxb.Software;
import umich.ms.logging.LogHelper;
import umich.ms.util.OffsetLength;
import umich.ms.util.jaxb.JaxbUtils;
import umich.ms.util.xml.POSITION;
import umich.ms.util.xml.XmlUtils;

/**
 * Parses the header of the file to get isntrument information.
 *
 * @author Dmitry Avtonomov
 */
public class MZXMLRunHeaderParser implements XmlBasedRunHeaderParser {

  private static final String TAG_MSRUN = "msRun";
  private static final String TAG_SCAN = "scan";
  protected MZXMLFile source;


  public MZXMLRunHeaderParser(MZXMLFile source) {
    this.source = source;
    // Turn off non-critical Javolution logging
    LogHelper.setJavolutionLogLevelFatal();
  }

  @Override
  public LCMSRunInfo parse() throws RunHeaderParsingException {

    Charset utf8 = Charset.forName("UTF-8");
    final long maxOffset = 10 * 1024 * 1024; // 10MB

    OffsetLength loc;
    try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source.getPath()))) {
      String search1 = "<" + TAG_MSRUN;
      String search2 = "<" + TAG_SCAN;
      List<byte[]> targets = Arrays.asList(search1.getBytes(utf8), search2.getBytes(utf8));
      List<POSITION> locations = Arrays.asList(POSITION.START, POSITION.START);
      List<Long> locate = XmlUtils.locate(targets, locations, bis, maxOffset);
      if (locate == null || locate.size() != targets.size()) {
        throw new RunHeaderParsingException(
            "Could not locate the header within " + maxOffset + " bytes of the file.");
      }
      loc = new OffsetLength(locate.get(0), (int) (locate.get(1) - locate.get(0)));

    } catch (IOException | RunHeaderParsingException e) {
      // if we can't locate the header, use a dummy instead
      return LCMSRunInfo.createDummyInfo();
    }

    String header;
    RandomAccessFile raf = null;
    try {
      raf = source.getRandomAccessFile();
      raf.seek(loc.offset);
      byte[] bytes = new byte[loc.length];
      raf.readFully(bytes);
      String body = new String(bytes, utf8);
      String suffix = "</" + TAG_MSRUN + ">";
      header = body + suffix;
    } catch (IOException e) {
      // if we can't read the header
      return LCMSRunInfo.createDummyInfo();
    } finally {
      if (raf != null) {
        source.close();
      }
    }

    // parsing with JAXB
    MsRun msRun;
    try (InputStream is = new ByteArrayInputStream(header.getBytes(Charset.forName("UTF-8")))) {
      XMLStreamReader xsr = JaxbUtils.createXmlStreamReader(is, false);
      msRun = JaxbUtils.unmarshal(MsRun.class, xsr);
    } catch (IOException | JAXBException e1) {
      final LCMSRunInfo dummyInfo = LCMSRunInfo.createDummyInfo();
      return new MZMLRunInfo(dummyInfo);
    }

    LCMSRunInfo runInfo = new LCMSRunInfo();

    // original files
    List<MsRun.ParentFile> parentFiles = msRun.getParentFile();
    if (parentFiles != null) {
      for (MsRun.ParentFile parentFile : parentFiles) {
        String file = parentFile.getFileName();
        file = file.replaceAll("\\\\", "/");
        String location = "";
        String fileName = file;
        try {
          Path path = Paths.get(file);
          location = path.getParent().toString();
          fileName = path.getFileName().toString();
        } catch (InvalidPathException e) {
          // could not parse path, try URI
          try {
            URI uri = URI.create(file).normalize();
            String uriPath = uri.getPath();
            while (uriPath.startsWith("/")) {
              uriPath = uriPath.substring(1);
            }

            Path path = Paths.get(uriPath);
            fileName = path.getFileName().toString();
            Path parent = path.getParent();
            if (parent != null) {
              location = toString();
            }
          } catch (IllegalArgumentException e2) {
            // URI also didn't work, forget it
          }
        }

        Hash hash = null;
        if (parentFile.getFileSha1() != null && !parentFile.getFileSha1().isEmpty()) {
          hash = new Hash(parentFile.getFileSha1(), Hash.TYPE.SHA1);
        }
        runInfo.getOriginalFiles().add(new OriginalFile(location, fileName, hash));
      }
    }

    List<MsRun.MsInstrument> msInstruments = msRun.getMsInstrument();
    if (msInstruments != null && msInstruments.size() > 0) {
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
        String manufacturer = msManufacturer != null ? msManufacturer.getValueOntologyEntryType()
            : Instrument.UNKNOWN_MANUFACTURER;
        instrument.setManufacturer(manufacturer);

        OntologyEntryType msModel = i.getMsModel();
        String model =
            msModel != null ? msModel.getValueOntologyEntryType() : Instrument.UNKNOWN_MODEL;
        instrument.setModel(model);

        MsRun.MsInstrument.MsMassAnalyzer msMassAnalyzer = i.getMsMassAnalyzer();
        String analyzer = msMassAnalyzer != null ? msMassAnalyzer.getValueOntologyEntryType()
            : Instrument.UNKNOWN_ANALYZER;
        instrument.setAnalyzer(analyzer);

        OntologyEntryType msDetector = i.getMsDetector();
        String detector = msDetector != null ? msDetector.getValueOntologyEntryType()
            : Instrument.UNKNOWN_DETECTOR;
        instrument.setDetector(detector);

        OntologyEntryType msIonisation = i.getMsIonisation();
        String ionisation = msIonisation != null ? msIonisation.getValueOntologyEntryType()
            : Instrument.UNKNOWN_IONISATION;
        instrument.setIonisation(ionisation);

        runInfo.addInstrument(instrument, msInstrumentID);
      }
    } else {
      // we didn't find any instrument descriptions in the file, so just set the instrument to some unknown.
      runInfo.addInstrument(Instrument.getDummy(), Instrument.ID_UNKNOWN);
    }

    List<MsRun.DataProcessing> dataProcessings = msRun.getDataProcessing();
    if (dataProcessings != null) {
      for (MsRun.DataProcessing dataProcessing : dataProcessings) {
        if (dataProcessing.isCentroided() != null) {
          runInfo.setCentroided(dataProcessing.isCentroided());
        }

        Software software = dataProcessing.getSoftware();
        if (software != null) {
          MsSoftware msSoftware = new MsSoftware(software.getName(), software.getVersion());
          runInfo.getSoftware().add(msSoftware);
        }
      }
    }

    return runInfo;
  }

}
