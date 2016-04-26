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

import com4j.*;

/**
 * IXRawfile5 Interface
 */
@IID("{06F53853-E43C-4F30-9E5F-D1B3668F0C3C}")
public interface IXRawfile5 extends umich.ms.fileio.filetypes.thermo.raw.com4j.IXRawfile4 {
  // Methods:
  /**
   * <p>
   * method GetNumberOfMassRangesFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pnNumMassRanges Mandatory Holder<Integer> parameter.
   */

  @DISPID(145) //= 0x91. The runtime will prefer the VTID if present
  @VTID(151)
  void getNumberOfMassRangesFromScanNum(
    int nScanNumber,
    Holder<Integer> pnNumMassRanges);


  /**
   * <p>
   * method GetFullMSOrderPrecursorDataFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param nMSOrder Mandatory int parameter.
   * @param pvarFullMSOrderPrecursorInfo Mandatory java.lang.Object parameter.
   */

  @DISPID(146) //= 0x92. The runtime will prefer the VTID if present
  @VTID(152)
  void getFullMSOrderPrecursorDataFromScanNum(
    int nScanNumber,
    int nMSOrder,
    java.lang.Object pvarFullMSOrderPrecursorInfo);


  /**
   * <p>
   * method GetMassRangeFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param nMassRangeIndex Mandatory int parameter.
   * @param pdMassRangeLowValue Mandatory Holder<Double> parameter.
   * @param pdMassRangeHighValue Mandatory Holder<Double> parameter.
   */

  @DISPID(147) //= 0x93. The runtime will prefer the VTID if present
  @VTID(153)
  void getMassRangeFromScanNum(
    int nScanNumber,
    int nMassRangeIndex,
    Holder<Double> pdMassRangeLowValue,
    Holder<Double> pdMassRangeHighValue);


  /**
   * <p>
   * method GetCycleNumberFromScanNumber
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pnCycleNumber Mandatory Holder<Integer> parameter.
   */

  @DISPID(148) //= 0x94. The runtime will prefer the VTID if present
  @VTID(154)
  void getCycleNumberFromScanNumber(
    int nScanNumber,
    Holder<Integer> pnCycleNumber);


  /**
   * <p>
   * method GetCompoundNameFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pbstrCompoundName Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(149) //= 0x95. The runtime will prefer the VTID if present
  @VTID(155)
  void getCompoundNameFromScanNum(
    int nScanNumber,
    Holder<java.lang.String> pbstrCompoundName);


  /**
   * <p>
   * method GetUniqueCompoundNames
   * </p>
   * @param pvarCompoundNamesArray Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(150) //= 0x96. The runtime will prefer the VTID if present
  @VTID(156)
  void getUniqueCompoundNames(
    java.lang.Object pvarCompoundNamesArray,
    Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetChroByCompoundName
   * </p>
   * @param nChroType1 Mandatory int parameter.
   * @param nChroOperator Mandatory int parameter.
   * @param nChroType2 Mandatory int parameter.
   * @param pCompoundNames Mandatory java.lang.Object parameter.
   * @param bstrMassRanges1 Mandatory java.lang.String parameter.
   * @param bstrMassRanges2 Mandatory java.lang.String parameter.
   * @param dDelay Mandatory double parameter.
   * @param pdStartTime Mandatory Holder<Double> parameter.
   * @param pdEndTime Mandatory Holder<Double> parameter.
   * @param nSmoothingType Mandatory int parameter.
   * @param nSmoothingValue Mandatory int parameter.
   * @param pvarChroData Mandatory java.lang.Object parameter.
   * @param pvarPeakFlags Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(151) //= 0x97. The runtime will prefer the VTID if present
  @VTID(157)
  void getChroByCompoundName(
    int nChroType1,
    int nChroOperator,
    int nChroType2,
    java.lang.Object pCompoundNames,
    java.lang.String bstrMassRanges1,
    java.lang.String bstrMassRanges2,
    double dDelay,
    Holder<Double> pdStartTime,
    Holder<Double> pdEndTime,
    int nSmoothingType,
    int nSmoothingValue,
    java.lang.Object pvarChroData,
    java.lang.Object pvarPeakFlags,
    Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetMSXMultiplexValueFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pnMSXValue Mandatory Holder<Integer> parameter.
   */

  @DISPID(152) //= 0x98. The runtime will prefer the VTID if present
  @VTID(158)
  void getMSXMultiplexValueFromScanNum(
    int nScanNumber,
    Holder<Integer> pnMSXValue);


  /**
   * <p>
   * method GetAValueFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pnAValue Mandatory Holder<Integer> parameter.
   */

  @DISPID(153) //= 0x99. The runtime will prefer the VTID if present
  @VTID(159)
  void getAValueFromScanNum(
    int nScanNumber,
    Holder<Integer> pnAValue);


  /**
   * <p>
   * method GetBValueFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pnBValue Mandatory Holder<Integer> parameter.
   */

  @DISPID(154) //= 0x9a. The runtime will prefer the VTID if present
  @VTID(160)
  void getBValueFromScanNum(
    int nScanNumber,
    Holder<Integer> pnBValue);


  /**
   * <p>
   * method GetFValueFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pnFValue Mandatory Holder<Integer> parameter.
   */

  @DISPID(155) //= 0x9b. The runtime will prefer the VTID if present
  @VTID(161)
  void getFValueFromScanNum(
    int nScanNumber,
    Holder<Integer> pnFValue);


  /**
   * <p>
   * method GetKValueFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pnKValue Mandatory Holder<Integer> parameter.
   */

  @DISPID(156) //= 0x9c. The runtime will prefer the VTID if present
  @VTID(162)
  void getKValueFromScanNum(
    int nScanNumber,
    Holder<Integer> pnKValue);


  /**
   * <p>
   * method GetRValueFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pnRValue Mandatory Holder<Integer> parameter.
   */

  @DISPID(157) //= 0x9d. The runtime will prefer the VTID if present
  @VTID(163)
  void getRValueFromScanNum(
    int nScanNumber,
    Holder<Integer> pnRValue);


  /**
   * <p>
   * method GetVValueFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pnVValue Mandatory Holder<Integer> parameter.
   */

  @DISPID(158) //= 0x9e. The runtime will prefer the VTID if present
  @VTID(164)
  void getVValueFromScanNum(
    int nScanNumber,
    Holder<Integer> pnVValue);


  /**
   * <p>
   * method GetNumberOfMassCalibratorsFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pnNumMassCalibrators Mandatory Holder<Integer> parameter.
   */

  @DISPID(159) //= 0x9f. The runtime will prefer the VTID if present
  @VTID(165)
  void getNumberOfMassCalibratorsFromScanNum(
    int nScanNumber,
    Holder<Integer> pnNumMassCalibrators);


  /**
   * <p>
   * method GetMassCalibrationValueFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param nMassCalibrationndex Mandatory int parameter.
   * @param pnMassCalibrationValue Mandatory Holder<Double> parameter.
   */

  @DISPID(160) //= 0xa0. The runtime will prefer the VTID if present
  @VTID(166)
  void getMassCalibrationValueFromScanNum(
    int nScanNumber,
    int nMassCalibrationndex,
    Holder<Double> pnMassCalibrationValue);


  /**
   * <p>
   * method GetMassTolerance
   * </p>
   * @param bUserDefined Mandatory Holder<Integer> parameter.
   * @param dMassTolerance Mandatory Holder<Double> parameter.
   * @param nUnits Mandatory Holder<Integer> parameter.
   */

  @DISPID(161) //= 0xa1. The runtime will prefer the VTID if present
  @VTID(167)
  void getMassTolerance(
    Holder<Integer> bUserDefined,
    Holder<Double> dMassTolerance,
    Holder<Integer> nUnits);


  /**
   * <p>
   * method GetNumberOfSourceFragmentsFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pnNumSourceFragments Mandatory Holder<Integer> parameter.
   */

  @DISPID(162) //= 0xa2. The runtime will prefer the VTID if present
  @VTID(168)
  void getNumberOfSourceFragmentsFromScanNum(
    int nScanNumber,
    Holder<Integer> pnNumSourceFragments);


  /**
   * <p>
   * method GetSourceFragmentValueFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param nSourceFragmentIndex Mandatory int parameter.
   * @param pdSourceFragmentValue Mandatory Holder<Double> parameter.
   */

  @DISPID(163) //= 0xa3. The runtime will prefer the VTID if present
  @VTID(169)
  void getSourceFragmentValueFromScanNum(
    int nScanNumber,
    int nSourceFragmentIndex,
    Holder<Double> pdSourceFragmentValue);


  /**
   * <p>
   * method GetNumberOfSourceFragmentationMassRangesFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pnNumSourceFragmentationMassRanges Mandatory Holder<Integer> parameter.
   */

  @DISPID(164) //= 0xa4. The runtime will prefer the VTID if present
  @VTID(170)
  void getNumberOfSourceFragmentationMassRangesFromScanNum(
    int nScanNumber,
    Holder<Integer> pnNumSourceFragmentationMassRanges);


  /**
   * <p>
   * method GetSourceFragmentationMassRangeFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param nSourceFragmentMassRangeIndex Mandatory int parameter.
   * @param pdSourceFragmentRangeLowValue Mandatory Holder<Double> parameter.
   * @param pdSourceFragmentRangeHighValue Mandatory Holder<Double> parameter.
   */

  @DISPID(165) //= 0xa5. The runtime will prefer the VTID if present
  @VTID(171)
  void getSourceFragmentationMassRangeFromScanNum(
    int nScanNumber,
    int nSourceFragmentMassRangeIndex,
    Holder<Double> pdSourceFragmentRangeLowValue,
    Holder<Double> pdSourceFragmentRangeHighValue);


  /**
   * <p>
   * method GetNumberOfMSOrdersFromScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pnNumMSOrders Mandatory Holder<Integer> parameter.
   */

  @DISPID(166) //= 0xa6. The runtime will prefer the VTID if present
  @VTID(172)
  void getNumberOfMSOrdersFromScanNum(
    int nScanNumber,
    Holder<Integer> pnNumMSOrders);


  /**
   * <p>
   * method GetIsolationWidthForScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param nMSOrder Mandatory int parameter.
   * @param pdIsolationWidth Mandatory Holder<Double> parameter.
   */

  @DISPID(167) //= 0xa7. The runtime will prefer the VTID if present
  @VTID(173)
  void getIsolationWidthForScanNum(
    int nScanNumber,
    int nMSOrder,
    Holder<Double> pdIsolationWidth);


  /**
   * <p>
   * method GetCollisionEnergyForScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param nMSOrder Mandatory int parameter.
   * @param pdCollisionEnergy Mandatory Holder<Double> parameter.
   */

  @DISPID(168) //= 0xa8. The runtime will prefer the VTID if present
  @VTID(174)
  void getCollisionEnergyForScanNum(
    int nScanNumber,
    int nMSOrder,
    Holder<Double> pdCollisionEnergy);


  /**
   * <p>
   * method GetPrecursorRangeForScanNum
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param nMSOrder Mandatory int parameter.
   * @param pdFirstPrecursorMass Mandatory Holder<Double> parameter.
   * @param pdLastPrecursorMass Mandatory Holder<Double> parameter.
   * @param pbIsValid Mandatory Holder<Integer> parameter.
   */

  @DISPID(169) //= 0xa9. The runtime will prefer the VTID if present
  @VTID(175)
  void getPrecursorRangeForScanNum(
    int nScanNumber,
    int nMSOrder,
    Holder<Double> pdFirstPrecursorMass,
    Holder<Double> pdLastPrecursorMass,
    Holder<Integer> pbIsValid);


  /**
   * <p>
   * method GetAllMSOrderData
   * </p>
   * @param nScanNumber Mandatory int parameter.
   * @param pvarDoubleData Mandatory java.lang.Object parameter.
   * @param pvarFlagsData Mandatory java.lang.Object parameter.
   * @param pnNumberOfMSOrders Mandatory Holder<Integer> parameter.
   */

  @DISPID(170) //= 0xaa. The runtime will prefer the VTID if present
  @VTID(176)
  void getAllMSOrderData(
    int nScanNumber,
    java.lang.Object pvarDoubleData,
    java.lang.Object pvarFlagsData,
    Holder<Integer> pnNumberOfMSOrders);


  /**
   * <p>
   * method IsQExactive
   * </p>
   * @param pVal Mandatory Holder<Integer> parameter.
   */

  @DISPID(171) //= 0xab. The runtime will prefer the VTID if present
  @VTID(177)
  void isQExactive(
    Holder<Integer> pVal);


  /**
   * <p>
   * method IncludeReferenceAndExceptionData
   * </p>
   * @param value Mandatory int parameter.
   */

  @DISPID(172) //= 0xac. The runtime will prefer the VTID if present
  @VTID(178)
  void includeReferenceAndExceptionData(
    int value);


  // Properties:
}
