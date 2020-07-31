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


public class Frame {

  private long id;
  private double time;
  private String polarity;
  private long scanMode;
  private long msMsType;
  private long timsId;
  private long maxIntensity;
  private long summedIntensities;
  private long numScans;
  private long numPeaks;
  private long mzCalibration;
  private double t1;
  private double t2;
  private long timsCalibration;
  private long propertyGroup;
  private double accumulationTime;
  private double rampTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public double getTime() {
    return time;
  }

  public void setTime(double time) {
    this.time = time;
  }


  public String getPolarity() {
    return polarity;
  }

  public void setPolarity(String polarity) {
    this.polarity = polarity;
  }


  public long getScanMode() {
    return scanMode;
  }

  public void setScanMode(long scanMode) {
    this.scanMode = scanMode;
  }


  public long getMsMsType() {
    return msMsType;
  }

  public void setMsMsType(long msMsType) {
    this.msMsType = msMsType;
  }


  public long getTimsId() {
    return timsId;
  }

  public void setTimsId(long timsId) {
    this.timsId = timsId;
  }


  public long getMaxIntensity() {
    return maxIntensity;
  }

  public void setMaxIntensity(long maxIntensity) {
    this.maxIntensity = maxIntensity;
  }


  public long getSummedIntensities() {
    return summedIntensities;
  }

  public void setSummedIntensities(long summedIntensities) {
    this.summedIntensities = summedIntensities;
  }


  public long getNumScans() {
    return numScans;
  }

  public void setNumScans(long numScans) {
    this.numScans = numScans;
  }


  public long getNumPeaks() {
    return numPeaks;
  }

  public void setNumPeaks(long numPeaks) {
    this.numPeaks = numPeaks;
  }


  public long getMzCalibration() {
    return mzCalibration;
  }

  public void setMzCalibration(long mzCalibration) {
    this.mzCalibration = mzCalibration;
  }


  public double getT1() {
    return t1;
  }

  public void setT1(double t1) {
    this.t1 = t1;
  }


  public double getT2() {
    return t2;
  }

  public void setT2(double t2) {
    this.t2 = t2;
  }


  public long getTimsCalibration() {
    return timsCalibration;
  }

  public void setTimsCalibration(long timsCalibration) {
    this.timsCalibration = timsCalibration;
  }


  public long getPropertyGroup() {
    return propertyGroup;
  }

  public void setPropertyGroup(long propertyGroup) {
    this.propertyGroup = propertyGroup;
  }


  public double getAccumulationTime() {
    return accumulationTime;
  }

  public void setAccumulationTime(double accumulationTime) {
    this.accumulationTime = accumulationTime;
  }


  public double getRampTime() {
    return rampTime;
  }

  public void setRampTime(double rampTime) {
    this.rampTime = rampTime;
  }

}
