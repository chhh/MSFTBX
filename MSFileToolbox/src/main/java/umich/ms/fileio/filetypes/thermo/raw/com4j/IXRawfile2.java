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
 * IXRawfile2 Interface
 */
@IID("{55A25FF7-F437-471F-909A-D7F2B5930805}")
public interface IXRawfile2 extends umich.ms.fileio.filetypes.thermo.raw.com4j.IXRawfile {
  // Methods:

  /**
   * <p>
   * method GetLabelData
   * </p>
   *
   * @param pvarLabels Mandatory java.lang.Object parameter.
   * @param pvarFlags Mandatory java.lang.Object parameter.
   * @param pnScanNumber Mandatory Holder<Integer> parameter.
   */

  @DISPID(118) //= 0x76. The runtime will prefer the VTID if present
  @VTID(124)
  void getLabelData(
      java.lang.Object pvarLabels,
      java.lang.Object pvarFlags,
      Holder<Integer> pnScanNumber);


  /**
   * <p>
   * method GetNoiseData
   * </p>
   *
   * @param pvarNoisePacket Mandatory java.lang.Object parameter.
   * @param pnScanNumber Mandatory Holder<Integer> parameter.
   */

  @DISPID(119) //= 0x77. The runtime will prefer the VTID if present
  @VTID(125)
  void getNoiseData(
      java.lang.Object pvarNoisePacket,
      Holder<Integer> pnScanNumber);


  /**
   * <p>
   * method GetSegmentedMassListFromRT
   * </p>
   *
   * @param pdRT Mandatory Holder<Double> parameter.
   * @param bstrFilter Mandatory java.lang.String parameter.
   * @param nIntensityCutoffType Mandatory int parameter.
   * @param nIntensityCutoffValue Mandatory int parameter.
   * @param nMaxNumberOfPeaks Mandatory int parameter.
   * @param bCentroidResult Mandatory int parameter.
   * @param pdCentroidPeakWidth Mandatory Holder<Double> parameter.
   * @param pvarMassList Mandatory java.lang.Object parameter.
   * @param pvarPeakFlags Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   * @param pvarSegments Mandatory java.lang.Object parameter.
   * @param pnNumSegments Mandatory Holder<Integer> parameter.
   * @param pvarLowHighMassRange Mandatory java.lang.Object parameter.
   */

  @DISPID(120) //= 0x78. The runtime will prefer the VTID if present
  @VTID(126)
  void getSegmentedMassListFromRT(
      Holder<Double> pdRT,
      java.lang.String bstrFilter,
      int nIntensityCutoffType,
      int nIntensityCutoffValue,
      int nMaxNumberOfPeaks,
      int bCentroidResult,
      Holder<Double> pdCentroidPeakWidth,
      java.lang.Object pvarMassList,
      java.lang.Object pvarPeakFlags,
      Holder<Integer> pnArraySize,
      java.lang.Object pvarSegments,
      Holder<Integer> pnNumSegments,
      java.lang.Object pvarLowHighMassRange);


  /**
   * <p>
   * method GetSegmentedMassListFromScanNum
   * </p>
   *
   * @param pnScanNumber Mandatory Holder<Integer> parameter.
   * @param bstrFilter Mandatory java.lang.String parameter.
   * @param nIntensityCutoffType Mandatory int parameter.
   * @param nIntensityCutoffValue Mandatory int parameter.
   * @param nMaxNumberOfPeaks Mandatory int parameter.
   * @param bCentroidResult Mandatory int parameter.
   * @param pdCentroidPeakWidth Mandatory Holder<Double> parameter.
   * @param pvarMassList Mandatory java.lang.Object parameter.
   * @param pvarPeakFlags Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   * @param pvarSegments Mandatory java.lang.Object parameter.
   * @param pnNumSegments Mandatory Holder<Integer> parameter.
   * @param pvarLowHighMassRange Mandatory java.lang.Object parameter.
   */

  @DISPID(121) //= 0x79. The runtime will prefer the VTID if present
  @VTID(127)
  void getSegmentedMassListFromScanNum(
      Holder<Integer> pnScanNumber,
      java.lang.String bstrFilter,
      int nIntensityCutoffType,
      int nIntensityCutoffValue,
      int nMaxNumberOfPeaks,
      int bCentroidResult,
      Holder<Double> pdCentroidPeakWidth,
      java.lang.Object pvarMassList,
      java.lang.Object pvarPeakFlags,
      Holder<Integer> pnArraySize,
      java.lang.Object pvarSegments,
      Holder<Integer> pnNumSegments,
      java.lang.Object pvarLowHighMassRange);


  /**
   * <p>
   * method GetScanEventForScanNum
   * </p>
   *
   * @param nScanNumber Mandatory int parameter.
   * @param pbstrScanEvent Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(122) //= 0x7a. The runtime will prefer the VTID if present
  @VTID(128)
  void getScanEventForScanNum(
      int nScanNumber,
      Holder<java.lang.String> pbstrScanEvent);


  /**
   * <p>
   * method GetSeqRowUserTextEx
   * </p>
   *
   * @param nIndex Mandatory int parameter.
   * @param pbstrUserText Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(123) //= 0x7b. The runtime will prefer the VTID if present
  @VTID(129)
  void getSeqRowUserTextEx(
      int nIndex,
      Holder<java.lang.String> pbstrUserText);


  /**
   * <p>
   * method GetSeqRowBarcode
   * </p>
   *
   * @param pbstrBarcode Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(124) //= 0x7c. The runtime will prefer the VTID if present
  @VTID(130)
  void getSeqRowBarcode(
      Holder<java.lang.String> pbstrBarcode);


  /**
   * <p>
   * method GetSeqRowBarcodeStatus
   * </p>
   *
   * @param pnBarcodeStatus Mandatory Holder<Integer> parameter.
   */

  @DISPID(125) //= 0x7d. The runtime will prefer the VTID if present
  @VTID(131)
  void getSeqRowBarcodeStatus(
      Holder<Integer> pnBarcodeStatus);

  // Properties:
}
