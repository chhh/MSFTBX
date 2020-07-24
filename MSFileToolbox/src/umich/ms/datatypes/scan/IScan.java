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
package umich.ms.datatypes.scan;

import java.io.Serializable;
import java.util.List;
import umich.ms.datatypes.scan.props.InjectionInfo;
import umich.ms.datatypes.scan.props.Instrument;
import umich.ms.datatypes.scan.props.Polarity;
import umich.ms.datatypes.scan.props.PrecursorInfo;
import umich.ms.datatypes.scan.props.ScanType;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.exceptions.FileParsingException;

/**
 * If you are ever writing a wrapper for 3rd party Scan implementations you must implement this
 * interface
 *
 * Author: Dmitry Avtonomov (dmitriya) Email: dmitriy.avtonomov@gmail.com
 */
public interface IScan extends Serializable {

  /**
   * Returns the internal scan number, which sometimes coincides with original scan numbers from raw
   * mass-spec files. If you want to get the number from the original raw file though, you should
   * consult the run index.
   *
   * @return internal scan number, starting from 1.
   */
  int getNum();

  IScanCollection getScanCollection();

  void setScanCollection(IScanCollection scans);

  Double getRt();

  void setRt(Double rt);

  Double getIm();

  void setIm(Double im);

  Integer getMsLevel();

  void setMsLevel(Integer msLevel);

  Boolean isCentroided();

  Polarity getPolarity();

  void setPolarity(Polarity polarity);

  ScanType getScanType();

  void setScanType(ScanType scanType);

  PrecursorInfo getPrecursor();

  void setPrecursor(PrecursorInfo precursor);

  List<Integer> getChildScans();

  void setChildScans(List<Integer> childScans);

  Instrument getInstrument();

  void setInstrument(Instrument instrument);

  InjectionInfo getInjectionInfo();

  void setInjectionInfo(InjectionInfo injectionInfo);

  Double getBasePeakIntensity();

  void setBasePeakIntensity(Double basePeakIntensity);

  Double getBasePeakMz();

  void setBasePeakMz(Double basePeakMz);

  Double getTic();

  void setTic(Double tic);

  Double getScanMzWindowLower();

  void setScanMzWindowLower(Double scanMzWindowLower);

  Double getScanMzWindowUpper();

  void setScanMzWindowUpper(Double scanMzWindowUpper);

  /**
   * Get the spectrum, not trying to loead it from disk, if it wasn't parsed.
   *
   * @return if a ScanCollection wasn't set, and the spectrum wasn't explicitly set for this scan,
   * then the spectrum returned might be null. If a scan collection was set, and the scan collection
   * was set to {@link umich.ms.datatypes.scancollection.IScanCollection#isAutoloadSpectra()}
   */
  ISpectrum getSpectrum();

  /**
   * Get the spectrum, possibly preloading it from disk. Must only work if this Scan has been added
   * to a ScanCollection via {@link umich.ms.datatypes.scancollection.IScanCollection#addScan(IScan)}
   * and the scan collection was set to auto-load spectra ({@link umich.ms.datatypes.scancollection.IScanCollection#isAutoloadSpectra(boolean)})
   *
   * @return if a spectrum is available, will return it. null, if this scan is not a part of a scan
   * collection, or if the scan collection was set to not auto-load spectra. Otherwise will try to
   * parse from disk.
   */
  ISpectrum fetchSpectrum() throws FileParsingException;

  StorageStrategy getStorageStrategy();

  /**
   * Implementors of IScan interface must respect the value of reftype in constructors and {@link
   * #setSpectrum(umich.ms.datatypes.spectrum.ISpectrum, boolean)} implementations in order for
   * memory management to work.
   */
  void setStorageStrategy(StorageStrategy strategy);

  void setCentroided(Boolean centroided);

  /**
   * Set the spectrumRef for this scan. In case the scan collection is cache-enabled, might put the
   * spectrumRef into a SoftReference.
   *
   * @param spectrum the new spectrumRef to set. Can be null, to release the memory, in which case
   * {@code forceOverrideMinMaxSumVals} must be false.
   * @param forceOverrideMinMaxSumVals when true, will override any min/max/sum intensity fields
   * previously set on this scan by using values from the spectrumRef. Must be set to false if
   * {@code spectrumRef} is null.
   */
  void setSpectrum(ISpectrum spectrum, boolean forceOverrideMinMaxSumVals);
}
