/*
 * Copyright (c) 2016 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.thermo.raw.com4j;

import com4j.DISPID;
import com4j.Holder;
import com4j.IID;
import com4j.VTID;

/**
 * IXRawfile4 Interface
 */
@IID("{E7CF6760-11CD-4260-B5B0-FCE2AD97547B}")
public interface IXRawfile4 extends umich.ms.fileio.filetypes.thermo.raw.com4j.IXRawfile3 {
  // Methods:

  /**
   * <p>
   * method ExtractInstMethodFromRaw
   * </p>
   *
   * @param szInstMethodFileName Mandatory java.lang.String parameter.
   */

  @DISPID(131) //= 0x83. The runtime will prefer the VTID if present
  @VTID(137)
  void extractInstMethodFromRaw(
      java.lang.String szInstMethodFileName);


  /**
   * <p>
   * method GetSegmentAndEventForScanNum
   * </p>
   *
   * @param nScanNumber Mandatory int parameter.
   * @param pnSegment Mandatory Holder<Integer> parameter.
   * @param pnScanEvent Mandatory Holder<Integer> parameter.
   */

  @DISPID(132) //= 0x84. The runtime will prefer the VTID if present
  @VTID(138)
  void getSegmentAndEventForScanNum(
      int nScanNumber,
      Holder<Integer> pnSegment,
      Holder<Integer> pnScanEvent);


  /**
   * <p>
   * method GetMassPrecisionEstimate
   * </p>
   *
   * @param lScanNumber Mandatory int parameter.
   * @param pvarMassList Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(133) //= 0x85. The runtime will prefer the VTID if present
  @VTID(139)
  void getMassPrecisionEstimate(
      int lScanNumber,
      java.lang.Object pvarMassList,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetActivationTypeForScanNum
   * </p>
   *
   * @param nScan Mandatory int parameter.
   * @param lMSOrder Mandatory int parameter.
   * @param pnActivationType Mandatory Holder<Integer> parameter.
   */

  @DISPID(134) //= 0x86. The runtime will prefer the VTID if present
  @VTID(140)
  void getActivationTypeForScanNum(
      int nScan,
      int lMSOrder,
      Holder<Integer> pnActivationType);


  /**
   * <p>
   * method GetDetectorTypeForScanNum
   * </p>
   *
   * @param nScan Mandatory int parameter.
   * @param pnDetectorType Mandatory Holder<Integer> parameter.
   */

  @DISPID(135) //= 0x87. The runtime will prefer the VTID if present
  @VTID(141)
  void getDetectorTypeForScanNum(
      int nScan,
      Holder<Integer> pnDetectorType);


  /**
   * <p>
   * method GetMassAnalyzerTypeForScanNum
   * </p>
   *
   * @param nScan Mandatory int parameter.
   * @param pnMassAnalyzerType Mandatory Holder<Integer> parameter.
   */

  @DISPID(136) //= 0x88. The runtime will prefer the VTID if present
  @VTID(142)
  void getMassAnalyzerTypeForScanNum(
      int nScan,
      Holder<Integer> pnMassAnalyzerType);


  /**
   * <p>
   * method GetMSOrderForScanNum
   * </p>
   *
   * @param nScan Mandatory int parameter.
   * @param pnMSOrder Mandatory Holder<Integer> parameter.
   */

  @DISPID(137) //= 0x89. The runtime will prefer the VTID if present
  @VTID(143)
  void getMSOrderForScanNum(
      int nScan,
      Holder<Integer> pnMSOrder);


  /**
   * <p>
   * method GetPrecursorMassForScanNum
   * </p>
   *
   * @param nScan Mandatory int parameter.
   * @param lMSOrder Mandatory int parameter.
   * @param pdMass Mandatory Holder<Double> parameter.
   */

  @DISPID(138) //= 0x8a. The runtime will prefer the VTID if present
  @VTID(144)
  void getPrecursorMassForScanNum(
      int nScan,
      int lMSOrder,
      Holder<Double> pdMass);


  /**
   * <p>
   * method GetScanTypeForScanNum
   * </p>
   *
   * @param nScan Mandatory int parameter.
   * @param pnScanType Mandatory Holder<Integer> parameter.
   */

  @DISPID(139) //= 0x8b. The runtime will prefer the VTID if present
  @VTID(145)
  void getScanTypeForScanNum(
      int nScan,
      Holder<Integer> pnScanType);


  /**
   * <p>
   * method GetAveragedMassSpectrum
   * </p>
   *
   * @param pnScanNumbers Mandatory Holder<Integer> parameter.
   * @param nScansToAverage Mandatory int parameter.
   * @param bCentroidResult Mandatory boolean parameter.
   * @param pvarMassList Mandatory java.lang.Object parameter.
   * @param pvarPeakFlags Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(140) //= 0x8c. The runtime will prefer the VTID if present
  @VTID(146)
  void getAveragedMassSpectrum(
      Holder<Integer> pnScanNumbers,
      int nScansToAverage,
      boolean bCentroidResult,
      java.lang.Object pvarMassList,
      java.lang.Object pvarPeakFlags,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetSummedMassSpectrum
   * </p>
   *
   * @param pnScanNumbers Mandatory Holder<Integer> parameter.
   * @param nScansToSum Mandatory int parameter.
   * @param bCentroidResult Mandatory boolean parameter.
   * @param pvarMassList Mandatory java.lang.Object parameter.
   * @param pvarPeakFlags Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(141) //= 0x8d. The runtime will prefer the VTID if present
  @VTID(147)
  void getSummedMassSpectrum(
      Holder<Integer> pnScanNumbers,
      int nScansToSum,
      boolean bCentroidResult,
      java.lang.Object pvarMassList,
      java.lang.Object pvarPeakFlags,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetAveragedLabelData
   * </p>
   *
   * @param pnScanNumbers Mandatory Holder<Integer> parameter.
   * @param nScansToAverage Mandatory int parameter.
   * @param pvarMassList Mandatory java.lang.Object parameter.
   * @param pvarPeakFlags Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(142) //= 0x8e. The runtime will prefer the VTID if present
  @VTID(148)
  void getAveragedLabelData(
      Holder<Integer> pnScanNumbers,
      int nScansToAverage,
      java.lang.Object pvarMassList,
      java.lang.Object pvarPeakFlags,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method FindPrecursorMassInFullScan
   * </p>
   *
   * @param nScan Mandatory int parameter.
   * @param pnMasterScan Mandatory Holder<Integer> parameter.
   * @param pdFoundMass Mandatory Holder<Double> parameter.
   * @param pdHeaderMass Mandatory Holder<Double> parameter.
   * @param pnChargeState Mandatory Holder<Integer> parameter.
   */

  @DISPID(143) //= 0x8f. The runtime will prefer the VTID if present
  @VTID(149)
  void findPrecursorMassInFullScan(
      int nScan,
      Holder<Integer> pnMasterScan,
      Holder<Double> pdFoundMass,
      Holder<Double> pdHeaderMass,
      Holder<Integer> pnChargeState);


  /**
   * <p>
   * method Version
   * </p>
   *
   * @param pnMajorVersion Mandatory Holder<Integer> parameter.
   * @param pnMinorVersion Mandatory Holder<Integer> parameter.
   * @param pnSubMinorVersion Mandatory Holder<Integer> parameter.
   * @param nBuildNumber Mandatory Holder<Integer> parameter.
   */

  @DISPID(144) //= 0x90. The runtime will prefer the VTID if present
  @VTID(150)
  void version(
      Holder<Integer> pnMajorVersion,
      Holder<Integer> pnMinorVersion,
      Holder<Integer> pnSubMinorVersion,
      Holder<Integer> nBuildNumber);

  // Properties:
}
