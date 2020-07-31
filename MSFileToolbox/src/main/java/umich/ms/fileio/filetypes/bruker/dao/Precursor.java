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

package umich.ms.fileio.filetypes.bruker.dao;


public class Precursor {

  private long id;
  private double largestPeakMz;
  private double averageMz;
  private double monoisotopicMz;
  private long charge;
  private double scanNumber;
  private double intensity;
  private long parent;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public double getLargestPeakMz() {
    return largestPeakMz;
  }

  public void setLargestPeakMz(double largestPeakMz) {
    this.largestPeakMz = largestPeakMz;
  }


  public double getAverageMz() {
    return averageMz;
  }

  public void setAverageMz(double averageMz) {
    this.averageMz = averageMz;
  }


  public double getMonoisotopicMz() {
    return monoisotopicMz;
  }

  public void setMonoisotopicMz(double monoisotopicMz) {
    this.monoisotopicMz = monoisotopicMz;
  }


  public long getCharge() {
    return charge;
  }

  public void setCharge(long charge) {
    this.charge = charge;
  }


  public double getScanNumber() {
    return scanNumber;
  }

  public void setScanNumber(double scanNumber) {
    this.scanNumber = scanNumber;
  }


  public double getIntensity() {
    return intensity;
  }

  public void setIntensity(double intensity) {
    this.intensity = intensity;
  }


  public long getParent() {
    return parent;
  }

  public void setParent(long parent) {
    this.parent = parent;
  }

}
