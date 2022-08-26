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
package umich.ms.datatypes.scan.impl;

import java.lang.ref.Reference;
import umich.ms.datatypes.scan.AbstractScan;
import umich.ms.datatypes.scan.StorageStrategy;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.exceptions.FileParsingException;

/**
 * All the constructors produce Scans that have {@link umich.ms.datatypes.scan.StorageStrategy} set
 * to {@link umich.ms.datatypes.scan.StorageStrategy#STRONG}, it's up to the client to reset this to
 * anything else.
 *
 * Author: Dmitry Avtonomov (dmitriya)
 */
public class ScanDefault extends AbstractScan {

  protected Reference<ISpectrum> spectrumRef;


  public ScanDefault(int num) {
    super(num);
    init();
    this.spectrumRef = storageStrategy.getRef(null);
  }

  public ScanDefault(IScanCollection scans, int num) {
    this(num);
    this.scans = scans;
  }

  public ScanDefault(int num, double rt, int msLevel, boolean isCentroided) {
    this(num);
    this.rt = rt;
    this.msLevel = msLevel;
    this.isCentroided = isCentroided;
  }

  public ScanDefault(IScanCollection scans, int num, double rt, int msLevel, boolean isCentroided) {
    this(num, rt, msLevel, isCentroided);
    this.scans = scans;
  }

  private void init() {
    this.storageStrategy = StorageStrategy.STRONG;
  }

  @Override
  public Double getRt() {
    return rt;
  }


  public IScanCollection getScans() {
    return scans;
  }

  public void setScans(IScanCollection scans) {
    this.scans = scans;
  }

  public Reference<ISpectrum> getSpectrumRef() {
    return spectrumRef;
  }

  @Override
  public ISpectrum getSpectrum() {
    return spectrumRef.get();
  }

  @Override
  public ISpectrum fetchSpectrum() throws FileParsingException {
    ISpectrum spec = spectrumRef.get();
    if (spec == null) {
      synchronized (this) {
        spec = spectrumRef.get();
        if (spec == null && scans != null && scans.isAutoloadSpectra()) {
          spec = scans.getDataSource().parseSpectrum(this.num);
          if (spec
              != null) { // the spectrum can still be null, if this scan had no spectrum recorded.
            setSpectrum(spec, true);
          }
        }
      }
    }
    return spec;
  }

  @Override
  protected void setSpectrumImpl(ISpectrum spectrum) {
    this.spectrumRef = storageStrategy.getRef(spectrum);
  }

  @Override
  public Double getFaimsVoltage() {
    return faimsVoltage;
  }

  @Override
  public void setFaimsVoltage(Double faimsVoltage) {
    this.faimsVoltage = faimsVoltage;
  }


}
