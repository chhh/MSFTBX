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
package umich.ms.datatypes.spectrum;

import java.io.Serializable;

/**
 * Author: Dmitry Avtonomov (dmitriya)
 */
public interface ISpectrum extends Serializable {

  /**
   * The lowest m/z value in the spectrumRef.
   */
  double getMinMZ();

  /**
   * The highest m/z value in the spectrumRef.
   */
  double getMaxMZ();

  /**
   * The minimum intensity in the spectrumRef. Might be zero in some cases.
   */
  double getMinInt();

  /**
   * The minimum intensity in the spectrumRef, but greater than zero. It can still be zero, if there
   * are only zero intensity peaks in the spectrumRef.
   *
   * @return might be zero only if the spectrumRef is of zero length, or all intensities in the
   * spectrumRef are zero.
   */
  double getMinIntNonZero();

  /**
   * The highest intensity in the spectrumRef. Also called basepeak intensity.
   */
  double getMaxInt();

  /**
   * The m/z value of the basepeak (i.e. the most intense peak in the spectrumRef).
   */
  double getMaxIntMz();

  /**
   * The sum of all intensities. Also known as TIC (Total Ion Current).
   */
  double getSumInt();

  /**
   * Array of m/z values. Might be of zero length, if the spectrumRef had no peaks in it.
   */
  double[] getMZs();

  /**
   * Array of intensities. Might be of zero length, if the spectrumRef had no peaks in it.
   */
  double[] getIntensities();

  /**
   * Array of mean inverse reduced ion mobility. Might be of zero length, if the spectrumRef had no peaks in it.
   */
  double[] getIMs();

  /**
   * Finds the idx of MZ that is equal or less than the provided one.
   *
   * @return null, if there is no value equal or less
   */
  Integer findMzIdxFloor(double mz);

  /**
   * Finds the idx of MZ that is equal or greater than the provided one.
   *
   * @return null, if there is no value equal or greater
   */
  Integer findMzIdxCeiling(double mz);

  /**
   * @return null if the array was empty
   */
  Integer findClosestMzIdx(double mz);

  /**
   * Finds indexes of MZs that are within a tolerance window (inclusive)
   *
   * @param mzLo low mass
   * @param mzHi high mass
   * @return array of 2 elements: {start_idx, end_idx}
   */
  int[] findMzIdxs(double mzLo, double mzHi);

  /**
   * Finds indexes of MZs that are within a PPM tolerance window (inclusive)
   *
   * @param ppm in PPM
   * @return array of 2 elements: {start_idx, end_idx}
   */
  int[] findMzIdxsWithinPpm(double mz, double ppm);
}
