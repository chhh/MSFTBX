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
package umich.ms.datatypes.spectrum.impl;

import java.io.Serializable;
import umich.ms.datatypes.spectrum.AbstractSpectrum;

/**
 * This is a straightforward implementation of AbstractSpectrum using Java arrays Author: Dmitry
 * Avtonomov (dmitriya)
 */
public class SpectrumDefault extends AbstractSpectrum implements Serializable {

  private static final long serialVersionUID = -2198926992332773666L;

  protected double[] mz;
  protected double[] intensity;
  protected double[] im;

  /**
   * The provided mz and intensity arrays must be sorted properly
   *
   * @param intLoNonZero minimum non-zero intensity in the spectrumRef. Can be set to zero if
   * spectrumRef contains no peaks
   * @param basePeakIntensity Base-peak intensity
   */
  public SpectrumDefault(double[] mz, double[] intensity, double[] im, double intLo, double intLoNonZero,
      double basePeakIntensity, double basePeakMz, double intSum) {
    if (mz.length != intensity.length) {
      throw new IllegalArgumentException("M/z and Intensity arrays must be of equal length");
    }

    if (im != null && mz.length != im.length) {
      throw new IllegalArgumentException("M/z and Ion Mobility arrays must be of equal length");
    }

    this.mz = mz;
    this.intensity = intensity;
    this.im = im;
    if (mz.length > 0) {
      init(mz[0], mz[mz.length - 1], intLo, intLoNonZero, basePeakIntensity, basePeakMz, intSum);
    } else {
      init(0, 0, 0, 0, 0, 0, 0);
    }
  }

  public SpectrumDefault(double[] mz, double[] intensity, double[] im) {
    if (mz.length != intensity.length) {
      throw new IllegalArgumentException("M/z and Intensity arrays must be of equal length");
    }

    if (im != null && mz.length != im.length) {
      throw new IllegalArgumentException("M/z and Ion Mobility arrays must be of equal length");
    }

    this.mz = mz;
    this.intensity = intensity;
    this.im = im;
    if (mz.length <= 0) {
      init(0, 0, 0, 0, 0, 0, 0);
    } else {
      double intLo = Double.POSITIVE_INFINITY;
      double intLoNonZero = Double.POSITIVE_INFINITY;
      double intHi = Double.NEGATIVE_INFINITY;
      double intHiMz = Double.NEGATIVE_INFINITY;
      double intSum = 0.0;
      for (int i = 0; i < intensity.length; i++) {
        if (intLo > intensity[i]) {
          intLo = intensity[i];
          if (intLo > 0) {
            intLoNonZero = intLo;
          }
        }
        if (intHi < intensity[i]) {
          intHi = intensity[i];
          intHiMz = mz[i];
        }
        intSum += intensity[i];
      }
      init(mz[0], mz[mz.length - 1], intLo, intLoNonZero, intHi, intHiMz, intSum);
    }
  }

  private void init(double mzLo, double mzHi, double intLo, double intLoNonZero, double intHi,
      double intHiMz, double intSum) {
    this.minMZ = mzLo;
    this.maxMZ = mzHi;
    this.minInt = intLo;
    this.minIntNonZero = intLoNonZero;
    this.maxInt = intHi;
    this.maxIntMz = intHiMz;
    this.sumInt = intSum;
  }

  @Override
  public double[] getMZs() {
    return mz;
  }

  @Override
  public double[] getIntensities() {
    return intensity;
  }

  @Override
  public double[] getIMs() {
    return im;
  }

  public void setIntensities(double[] intensity) {
    this.intensity = intensity;
  }

  public void setMzs(double[] mz) {
    this.mz = mz;
  }

  @Override
  public int[] findMzIdxs(double mzLo, double mzHi) {
    Integer mzLoIdx = findMzIdxCeiling(mzLo);
    Integer mzHiIdx = findMzIdxFloor(mzHi);
    if (mzLoIdx == null || mzHiIdx == null || mzLoIdx > mzHiIdx) {
      return null;
    }
    return new int[]{mzLoIdx, mzHiIdx};
  }


}
