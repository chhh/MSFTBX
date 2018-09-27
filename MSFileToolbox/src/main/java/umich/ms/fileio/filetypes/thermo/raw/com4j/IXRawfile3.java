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
import com4j.MarshalAs;
import com4j.NativeType;
import com4j.VTID;

/**
 * IXRawfile3 Interface
 */
@IID("{19A00B1E-1559-42B1-9A46-08A5E599EDEE}")
public interface IXRawfile3 extends umich.ms.fileio.filetypes.thermo.raw.com4j.IXRawfile2 {
  // Methods:

  /**
   * <p>
   * method GetMassListRangeFromScanNum
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
   * @param szMassRange1 Mandatory java.lang.String parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(126) //= 0x7e. The runtime will prefer the VTID if present
  @VTID(132)
  void getMassListRangeFromScanNum(
      Holder<Integer> pnScanNumber,
      java.lang.String bstrFilter,
      int nIntensityCutoffType,
      int nIntensityCutoffValue,
      int nMaxNumberOfPeaks,
      int bCentroidResult,
      Holder<Double> pdCentroidPeakWidth,
      java.lang.Object pvarMassList,
      java.lang.Object pvarPeakFlags,
      @MarshalAs(NativeType.Unicode) java.lang.String szMassRange1,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetMassListRangeFromRT
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
   * @param szMassRange1 Mandatory java.lang.String parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(127) //= 0x7f. The runtime will prefer the VTID if present
  @VTID(133)
  void getMassListRangeFromRT(
      Holder<Double> pdRT,
      java.lang.String bstrFilter,
      int nIntensityCutoffType,
      int nIntensityCutoffValue,
      int nMaxNumberOfPeaks,
      int bCentroidResult,
      Holder<Double> pdCentroidPeakWidth,
      java.lang.Object pvarMassList,
      java.lang.Object pvarPeakFlags,
      @MarshalAs(NativeType.Unicode) java.lang.String szMassRange1,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetNextMassListRangeFromScanNum
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
   * @param szMassRange1 Mandatory java.lang.String parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(128) //= 0x80. The runtime will prefer the VTID if present
  @VTID(134)
  void getNextMassListRangeFromScanNum(
      Holder<Integer> pnScanNumber,
      java.lang.String bstrFilter,
      int nIntensityCutoffType,
      int nIntensityCutoffValue,
      int nMaxNumberOfPeaks,
      int bCentroidResult,
      Holder<Double> pdCentroidPeakWidth,
      java.lang.Object pvarMassList,
      java.lang.Object pvarPeakFlags,
      @MarshalAs(NativeType.Unicode) java.lang.String szMassRange1,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetPrevMassListRangeFromScanNum
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
   * @param szMassRange1 Mandatory java.lang.String parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(129) //= 0x81. The runtime will prefer the VTID if present
  @VTID(135)
  void getPrevMassListRangeFromScanNum(
      Holder<Integer> pnScanNumber,
      java.lang.String bstrFilter,
      int nIntensityCutoffType,
      int nIntensityCutoffValue,
      int nMaxNumberOfPeaks,
      int bCentroidResult,
      Holder<Double> pdCentroidPeakWidth,
      java.lang.Object pvarMassList,
      java.lang.Object pvarPeakFlags,
      @MarshalAs(NativeType.Unicode) java.lang.String szMassRange1,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetPrecursorInfoFromScanNum
   * </p>
   *
   * @param nScanNumber Mandatory int parameter.
   * @param pvarPrecursorInfos Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(130) //= 0x82. The runtime will prefer the VTID if present
  @VTID(136)
  void getPrecursorInfoFromScanNum(
      int nScanNumber,
      java.lang.Object pvarPrecursorInfos,
      Holder<Integer> pnArraySize);

  // Properties:
}
