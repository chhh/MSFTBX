/*
 * Copyright (c) 2019 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.bruker;

import umich.ms.fileio.filetypes.bruker.dao.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.impl.ScanDefault;
import umich.ms.datatypes.scan.props.ActivationInfo;
import umich.ms.datatypes.scan.props.Polarity;
import umich.ms.datatypes.scan.props.PrecursorInfo;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.datatypes.spectrum.impl.SpectrumDefault;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.util.DoubleRange;
import umich.ms.util.StringUtils;

public class BrukerTdfFileBase implements AutoCloseable {
  private static final Logger log = LoggerFactory.getLogger(BrukerTdfFileBase.class);
  private final BrukerTdfFile bruker;
  public enum MS2_REPORTING {
    FULL_2D_SPECTRUM_AS_ONE_SCAN,
    EACH_PASEF_WINDOW_AS_SEPARATE_SCAN,
  }
  private final MS2_REPORTING ms2Reporting;

  public enum MS2_TYPE {
    REGULAR_MS2,
    DIA,
    PASEF,
  }

  public BrukerTdfFileBase(String path, MS2_REPORTING ms2Reporting) {
    if (StringUtils.isNullOrWhitespace(Timsdata.getLoadedLibName())) {
      if (Timsdata.Instance.LIB_LOAD_EXCEPTION != null) {
        throw new UnsupportedOperationException("Bruker native libraries not found", Timsdata.Instance.LIB_LOAD_EXCEPTION);
      }
      throw new UnsupportedOperationException("Bruker native libraries not found");
    }
    this.bruker = new BrukerTdfFile(path);
    this.ms2Reporting = ms2Reporting;
  }

  /**
   * That's how you know which scan numbers (IDs) exist in the file. Scan numbers are not
   * guaranteed to be consecutive integers.
   */
  public List<FrameInfo> readFrameInfos() {
    return bruker.readFrameInfos();
  }

  public double[] convertImScanNumTo1k0(long frameId, double[] scanNums) throws BrukerException {
    return bruker.convertImScanNumTo1k0(frameId, scanNums);
  }

  public double[] convertIm1k0ToScanNum(long frameId, double[] oneOverK0) throws BrukerException {
    return bruker.convertIm1k0ToScanNum(frameId, oneOverK0);
  }

  public Frame readFrameInfoRaw(long frameId) throws BrukerException {
    return bruker.readFrameInfoRaw(frameId);
  }

  public List<IScan> readMs1AsPasef(long ms1FrameId) throws FileParsingException {

    try {
      Frame f = bruker.sql.sqlReadFrame(ms1FrameId);
      FrameInfo fi = FrameInfo.from(f);
      List<PasefMs2Data> pasefMs2Data = bruker.readFramePasef(fi);
      List<IScan> scans = new ArrayList<>();
      for (PasefMs2Data data : pasefMs2Data) {
        Precursor p = bruker.sql.sqlReadPrecursorById(data.precursorId);
        List<PasefFrameMsMsInfo> pmmi = bruker.sql.sqlPasefFrameMsMsInfo(data.precursorId);
        if (pmmi == null || pmmi.isEmpty()) {
          throw new FileParsingException("Got empty PasefFrameMsMsInfo list");
        }
        ScanDefault scan = createScan(f, p, pmmi.get(0));
        SpectrumDefault spec = new SpectrumDefault(data.spectrum.mzs, data.spectrum.abs,
            null);
        scan.setSpectrum(spec, true);
        scans.add(scan);
      }
      return scans;

    } catch (SQLException | BrukerException e) {
      throw new FileParsingException(e);
    }
  }

  /**
   * @param getSpectrum If `true`, will read the spectrum and attach it to the scan.
   * Otherwise only reads the scan meta-data.
   * @return Might return many scans, if MS2-Reporting was set in the constructor to
   * {@link MS2_REPORTING#EACH_PASEF_WINDOW_AS_SEPARATE_SCAN}.
   */
  public List<IScan> readFrame(long frameId, boolean getSpectrum) throws FileParsingException {
    if (frameId > Integer.MAX_VALUE)
      throw new UnsupportedOperationException("Even though the method readFrame() accepts"
          + " Frame Id as a Long, it actually only supports only Integer values.");

    Frame f;
    try {
      f = bruker.sql.sqlReadFrame(frameId);
    } catch (SQLException e) {
      throw new FileParsingException(e);
    }

    List<IScan> scans = new ArrayList<>();

    if (f.getMsMsType() == 0) { // MS1 scan
      ScanDefault scan = createScan(f);
      if (getSpectrum) {
        try {
          Peaklist2D pl2d = bruker.readFrameRaw(frameId);
          scan.setSpectrum(convert(pl2d));
          scans.add(scan);
        } catch (BrukerException e) {
          throw new FileParsingException(e);
        }
      }

    } else { // assuming can only be MS2 scan

      switch (ms2Reporting) {

        case FULL_2D_SPECTRUM_AS_ONE_SCAN: {
          // Just report the scan as is, it doesn't matter what MsMsType the scan is.
          ScanDefault scan = createScan(f);
          if (getSpectrum) {
            Peaklist2D pl2d = null;
            try {
              pl2d = bruker.readFrameRaw(frameId);
              scan.setSpectrum(convert(pl2d));
            } catch (BrukerException e) {
              throw new FileParsingException(e);
            }
          }
          if (f.getMsMsType() == 9) {
            try {
              DiaFrameMsMsInfo mmi = bruker.sql.sqlReadDiaFrameMsMsInfo(frameId);
              List<DiaFrameMsMsWindow> ws = bruker.sql.sqlReadDiaFrameMsMsWindow(mmi.getWindowGroup());
              ws.sort((o1, o2) -> Long.compare(o2.getScanNumEnd(), o1.getScanNumEnd()));
              PrecursorInfo pi = new PrecursorInfo();
              List<PrecursorInfo.IsolationRange2D> ranges = new ArrayList<>();
              for (DiaFrameMsMsWindow w : ws) {
                double mzLo = w.getIsolationMz() - w.getIsolationWidth() / 2.0;
                double mzHi = w.getIsolationMz() + w.getIsolationWidth() / 2.0;
                double[] scanNums = {w.getScanNumBegin(), w.getScanNumEnd()};
                double[] ims = convertImScanNumTo1k0(frameId, scanNums);
                double imLo = Math.min(ims[0], ims[1]);
                double imHi = Math.max(ims[0], ims[1]);
                PrecursorInfo.IsolationRange2D range = new PrecursorInfo.IsolationRange2D(mzLo, mzHi, imLo, imHi);
                ranges.add(range);
              }
              pi.setIsolationRanges(ranges);
              scan.addPrecursor(pi);
            } catch (SQLException | BrukerException e) {
              throw new FileParsingException(e);
            }
          } else {
            log.warn(
                    "Reminder: Precursor infos are not implemented for " +
                            "MS2_REPORTING.FULL_2D_SPECTRUM_AS_ONE_SCAN when MsMsType={}.", f.getMsMsType());
          }

          scans.add(scan);
          break;
        }
        case EACH_PASEF_WINDOW_AS_SEPARATE_SCAN: {
          if (f.getMsMsType() != 8) {
            throw new UnsupportedOperationException("Don't know how to treat frames with MsMsType != 8");
          }
          {
            // I assume this is PASEF scan, don't know what other numbers are.
            // for PASEF scans
            List<PasefFrameMsMsInfo> pis;
            FrameInfo fi = FrameInfo.from(f);
            try {
              pis = bruker.sql.sqlPasefFrameMsMsInfos(fi);
            } catch (SQLException e) {
              throw new FileParsingException("Could not get PASEF MsMs frame infos", e);
            }

            List<PasefMs2Data> ms2Data;
            try {
              ms2Data = bruker.readFramePasef(fi);
            } catch (BrukerException e) {
              throw new FileParsingException("Error reading PASEF ms2 data", e);
            }

            Map<Long, PasefMs2Data> pasefSpectraByPrecId = new HashMap<>();
            for (PasefMs2Data ms2Datum : ms2Data) {
              pasefSpectraByPrecId.put(ms2Datum.precursorId, ms2Datum);
            }
            for (PasefFrameMsMsInfo pi : pis) {
              if (!pasefSpectraByPrecId.containsKey(pi.getPrecursor())) {
                throw new IllegalStateException(
                    "Returned PASEF ms2 data didn't contain spectra for all precursors");
              }
            }

            for (PasefFrameMsMsInfo msmsInfo : pis) {
              ScanDefault scan = createScan(f);

              Precursor p;
              try {
                p = bruker.sql.sqlReadPrecursorById(msmsInfo.getPrecursor());
              } catch (SQLException e) {
                throw new IllegalStateException("Could not read precursor info", e);
              }

              double pScanNum = p.getScanNumber();
              DoubleRange pScanRange = new DoubleRange(
                  (double) msmsInfo.getScanNumBegin(), (double) msmsInfo.getScanNumEnd());
              double[] scanNums = {pScanNum, pScanRange.lo, pScanRange.hi};
              double[] oneOverK0;
              try {
                oneOverK0 = bruker.convertImScanNumTo1k0(frameId, scanNums);
              } catch (BrukerException e) {
                throw new FileParsingException("Error converting to 1/k0", e);
              }
              scan.setIm(oneOverK0[0]);
              scan.setScanRange(pScanRange);

              PrecursorInfo pi = new PrecursorInfo();
              pi.setCharge((int) p.getCharge());
              pi.setMzTargetMono(p.getMonoisotopicMz());
              pi.setMzTarget(p.getLargestPeakMz());
              pi.setMzRangeStart(msmsInfo.getIsolationMz() - msmsInfo.getIsolationWidth() / 2.0);
              pi.setMzRangeEnd(msmsInfo.getIsolationMz() + msmsInfo.getIsolationWidth() / 2.0);
              pi.setIntensity(p.getIntensity());
              pi.setParentScanNum((int) p.getParent());

              ActivationInfo ai = new ActivationInfo();
              ai.setActivationEnergyLo(msmsInfo.getCollisionEnergy());
              ai.setActivationEnergyHi(msmsInfo.getCollisionEnergy());
              ai.setActivationMethod("PASEF CID");
              pi.setActivationInfo(ai);
              scan.addPrecursor(pi);

              if (getSpectrum) {
                PasefMs2Data ms2 = pasefSpectraByPrecId.get(msmsInfo.getPrecursor());
                if (ms2 == null) {
                  throw new IllegalStateException(
                      "Did not get PASEF spectrum for precursor ID: " + msmsInfo.getPrecursor());
                }
                scan.setSpectrum(convert(ms2.spectrum));
              }
              scans.add(scan);
            }
          }
          break;
        }
        default:
          throw new UnsupportedOperationException("Unknown MS2_REPORTING option");
      }
    }

    return scans;
  }

  private static ISpectrum convert(Peaklist2D pl2d) {
    return new SpectrumDefault(pl2d.mzs, pl2d.abs, pl2d.ims);
  }

  private static ISpectrum convert(Peaklist1D pl1d) {
    return new SpectrumDefault(pl1d.mzs, pl1d.abs, null);
  }

  private ScanDefault createScan(Frame f) {
    ScanDefault scan = new ScanDefault((int)f.getId());
    scan.setMsLevel(f.getMsMsType() == 0 ? 1 : 2);
    scan.setCentroided(true); // bruker scans are always centroided, I think (at least in mz)
    scan.setBasePeakIntensity((double)f.getMaxIntensity());
    scan.setRt(f.getTime()/60.0);
    if (f.getPolarity() != null) {
      if ("+".equals(f.getPolarity())) {
        scan.setPolarity(Polarity.POSITIVE);
      } else if ("-".equals(f.getPolarity())) {
        scan.setPolarity(Polarity.NEGATIVE);
      } else {
        log.warn("Unknown polarity value string encountered: '{}'", f.getPolarity());
        scan.setPolarity(Polarity.NEUTRAL);
      }
    }
    return scan;
  }

  private ScanDefault createScan(Frame ms1Frame, Precursor p, PasefFrameMsMsInfo pmmi) throws BrukerException {
    ScanDefault scan = new ScanDefault((int)ms1Frame.getId());
    scan.setMsLevel(2);
    scan.setCentroided(true); // bruker scans are always centroided, I think (at least in mz)
    scan.setBasePeakIntensity((double)p.getIntensity());
    double[] im = bruker
        .convertImScanNumTo1k0(ms1Frame.getId(), new double[]{p.getScanNumber()});
    scan.setIm(im[0]);
    scan.setScanRange(new DoubleRange((double)pmmi.getScanNumBegin(), (double)pmmi.getScanNumEnd()));
    PrecursorInfo pi = new PrecursorInfo();
    scan.addPrecursor(pi);
    pi.setCharge((int)p.getCharge());
    pi.setMzTargetMono(p.getMonoisotopicMz());
    pi.setMzTarget(pmmi.getIsolationMz());
    pi.setMzRangeStart(pmmi.getIsolationMz() - pmmi.getIsolationWidth()/2);
    pi.setMzRangeEnd(pmmi.getIsolationMz() + pmmi.getIsolationWidth()/2);
    pi.setParentScanNum((int)ms1Frame.getId());
    pi.setIntensity(p.getIntensity());
    ActivationInfo ai = new ActivationInfo();
    pi.setActivationInfo(ai);
    ai.setActivationMethod("PASEF");
    ai.setActivationEnergyLo(pmmi.getCollisionEnergy());
    ai.setActivationEnergyHi(pmmi.getCollisionEnergy());

    scan.setRt(ms1Frame.getTime()/60.0);
    if (ms1Frame.getPolarity() != null) {
      if ("+".equals(ms1Frame.getPolarity())) {
        scan.setPolarity(Polarity.POSITIVE);
      } else if ("-".equals(ms1Frame.getPolarity())) {
        scan.setPolarity(Polarity.NEGATIVE);
      } else {
        log.warn("Unknown polarity value string encountered: '{}'", ms1Frame.getPolarity());
        scan.setPolarity(Polarity.NEUTRAL);
      }
    }
    return scan;
  }

  /**
   * This method only reads the bare spectrum. If it's an MS2 PASEF scan and there are IM sub-ranges
   * for each precursor, it still reads and reports the whole 2D spectrum.
   */
  public ISpectrum readRawSpectrum(long frameId) throws FileParsingException {
    try {
      Peaklist2D pl2d = bruker.readFrameRaw(frameId);
      return convert(pl2d);
    } catch (BrukerException e) {
      throw new FileParsingException(e);
    }
  }

  @Override
  public void close() throws Exception {
    if (bruker != null) {
      bruker.close();
    }
  }
}
