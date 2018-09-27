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

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.Holder;
import com4j.IID;
import com4j.VTID;

/**
 * IXRawfile Interface
 */
@IID("{11B488A0-69B1-41FC-A660-FE8DF2A31F5B}")
public interface IXRawfile extends Com4jObject {
  // Methods:

  /**
   * <p>
   * method Open
   * </p>
   *
   * @param szFileName Mandatory java.lang.String parameter.
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  void open(
      java.lang.String szFileName);


  /**
   * <p>
   * method Close
   * </p>
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  void close();


  /**
   * <p>
   * method GetFileName
   * </p>
   *
   * @param pbstrFileName Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  void getFileName(
      Holder<java.lang.String> pbstrFileName);


  /**
   * <p>
   * method GetCreatorID
   * </p>
   *
   * @param pbstrCreatorID Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(10)
  void getCreatorID(
      Holder<java.lang.String> pbstrCreatorID);


  /**
   * <p>
   * method GetVersionNumber
   * </p>
   *
   * @param pnVersion Mandatory Holder<Integer> parameter.
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(11)
  void getVersionNumber(
      Holder<Integer> pnVersion);


  /**
   * <p>
   * method IsError
   * </p>
   *
   * @param pbIsError Mandatory Holder<Integer> parameter.
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(13)
  void isError(
      Holder<Integer> pbIsError);


  /**
   * <p>
   * method IsNewFile
   * </p>
   *
   * @param pbIsNewFile Mandatory Holder<Integer> parameter.
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(14)
  void isNewFile(
      Holder<Integer> pbIsNewFile);


  /**
   * <p>
   * method GetErrorCode
   * </p>
   *
   * @param pnErrorCode Mandatory Holder<Integer> parameter.
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(15)
  void getErrorCode(
      Holder<Integer> pnErrorCode);


  /**
   * <p>
   * method GetErrorMessage
   * </p>
   *
   * @param pbstrErrorMessage Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(16)
  void getErrorMessage(
      Holder<java.lang.String> pbstrErrorMessage);


  /**
   * <p>
   * method GetWarningMessage
   * </p>
   *
   * @param pbstrWarningMessage Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(17)
  void getWarningMessage(
      Holder<java.lang.String> pbstrWarningMessage);


  /**
   * <p>
   * method GetSeqRowNumber
   * </p>
   *
   * @param pnSeqRowNumber Mandatory Holder<Integer> parameter.
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(18)
  void getSeqRowNumber(
      Holder<Integer> pnSeqRowNumber);


  /**
   * <p>
   * method GetSeqRowSampleType
   * </p>
   *
   * @param pnSampleType Mandatory Holder<Integer> parameter.
   */

  @DISPID(13) //= 0xd. The runtime will prefer the VTID if present
  @VTID(19)
  void getSeqRowSampleType(
      Holder<Integer> pnSampleType);


  /**
   * <p>
   * method GetSeqRowDataPath
   * </p>
   *
   * @param pbstrDataPath Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(14) //= 0xe. The runtime will prefer the VTID if present
  @VTID(20)
  void getSeqRowDataPath(
      Holder<java.lang.String> pbstrDataPath);


  /**
   * <p>
   * method GetSeqRowRawFileName
   * </p>
   *
   * @param pbstrRawFileName Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(15) //= 0xf. The runtime will prefer the VTID if present
  @VTID(21)
  void getSeqRowRawFileName(
      Holder<java.lang.String> pbstrRawFileName);


  /**
   * <p>
   * method GetSeqRowSampleName
   * </p>
   *
   * @param pbstrSampleName Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(16) //= 0x10. The runtime will prefer the VTID if present
  @VTID(22)
  void getSeqRowSampleName(
      Holder<java.lang.String> pbstrSampleName);


  /**
   * <p>
   * method GetSeqRowSampleID
   * </p>
   *
   * @param pbstrSampleID Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(17) //= 0x11. The runtime will prefer the VTID if present
  @VTID(23)
  void getSeqRowSampleID(
      Holder<java.lang.String> pbstrSampleID);


  /**
   * <p>
   * method GetSeqRowComment
   * </p>
   *
   * @param pbstrComment Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(18) //= 0x12. The runtime will prefer the VTID if present
  @VTID(24)
  void getSeqRowComment(
      Holder<java.lang.String> pbstrComment);


  /**
   * <p>
   * method GetSeqRowLevelName
   * </p>
   *
   * @param pbstrLevelName Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(19) //= 0x13. The runtime will prefer the VTID if present
  @VTID(25)
  void getSeqRowLevelName(
      Holder<java.lang.String> pbstrLevelName);


  /**
   * <p>
   * method GetSeqRowUserText
   * </p>
   *
   * @param nIndex Mandatory int parameter.
   * @param pbstrUserText Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(20) //= 0x14. The runtime will prefer the VTID if present
  @VTID(26)
  void getSeqRowUserText(
      int nIndex,
      Holder<java.lang.String> pbstrUserText);


  /**
   * <p>
   * method GetSeqRowInstrumentMethod
   * </p>
   *
   * @param pbstrInstrumentMethod Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(21) //= 0x15. The runtime will prefer the VTID if present
  @VTID(27)
  void getSeqRowInstrumentMethod(
      Holder<java.lang.String> pbstrInstrumentMethod);


  /**
   * <p>
   * method GetSeqRowProcessingMethod
   * </p>
   *
   * @param pbstrProcessingMethod Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(22) //= 0x16. The runtime will prefer the VTID if present
  @VTID(28)
  void getSeqRowProcessingMethod(
      Holder<java.lang.String> pbstrProcessingMethod);


  /**
   * <p>
   * method GetSeqRowCalibrationFile
   * </p>
   *
   * @param pbstrCalibrationFile Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(23) //= 0x17. The runtime will prefer the VTID if present
  @VTID(29)
  void getSeqRowCalibrationFile(
      Holder<java.lang.String> pbstrCalibrationFile);


  /**
   * <p>
   * method GetSeqRowVial
   * </p>
   *
   * @param pbstrVial Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(24) //= 0x18. The runtime will prefer the VTID if present
  @VTID(30)
  void getSeqRowVial(
      Holder<java.lang.String> pbstrVial);


  /**
   * <p>
   * method GetSeqRowInjectionVolume
   * </p>
   *
   * @param pdInjVol Mandatory Holder<Double> parameter.
   */

  @DISPID(25) //= 0x19. The runtime will prefer the VTID if present
  @VTID(31)
  void getSeqRowInjectionVolume(
      Holder<Double> pdInjVol);


  /**
   * <p>
   * method GetSeqRowSampleWeight
   * </p>
   *
   * @param pdSampleWt Mandatory Holder<Double> parameter.
   */

  @DISPID(26) //= 0x1a. The runtime will prefer the VTID if present
  @VTID(32)
  void getSeqRowSampleWeight(
      Holder<Double> pdSampleWt);


  /**
   * <p>
   * method GetSeqRowSampleVolume
   * </p>
   *
   * @param pdSampleVolume Mandatory Holder<Double> parameter.
   */

  @DISPID(27) //= 0x1b. The runtime will prefer the VTID if present
  @VTID(33)
  void getSeqRowSampleVolume(
      Holder<Double> pdSampleVolume);


  /**
   * <p>
   * method GetSeqRowISTDAmount
   * </p>
   *
   * @param pdISTDAmount Mandatory Holder<Double> parameter.
   */

  @DISPID(28) //= 0x1c. The runtime will prefer the VTID if present
  @VTID(34)
  void getSeqRowISTDAmount(
      Holder<Double> pdISTDAmount);


  /**
   * <p>
   * method GetSeqRowDilutionFactor
   * </p>
   *
   * @param pdDilutionFactor Mandatory Holder<Double> parameter.
   */

  @DISPID(29) //= 0x1d. The runtime will prefer the VTID if present
  @VTID(35)
  void getSeqRowDilutionFactor(
      Holder<Double> pdDilutionFactor);


  /**
   * <p>
   * method GetSeqRowUserLabel
   * </p>
   *
   * @param nIndex Mandatory int parameter.
   * @param pbstrUserLabel Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(30) //= 0x1e. The runtime will prefer the VTID if present
  @VTID(36)
  void getSeqRowUserLabel(
      int nIndex,
      Holder<java.lang.String> pbstrUserLabel);


  /**
   * <p>
   * method InAcquisition
   * </p>
   *
   * @param pbInAcquisition Mandatory Holder<Integer> parameter.
   */

  @DISPID(31) //= 0x1f. The runtime will prefer the VTID if present
  @VTID(37)
  void inAcquisition(
      Holder<Integer> pbInAcquisition);


  /**
   * <p>
   * method GetNumberOfControllers
   * </p>
   *
   * @param pnNumControllers Mandatory Holder<Integer> parameter.
   */

  @DISPID(32) //= 0x20. The runtime will prefer the VTID if present
  @VTID(38)
  void getNumberOfControllers(
      Holder<Integer> pnNumControllers);


  /**
   * <p>
   * method GetControllerType
   * </p>
   *
   * @param nIndex Mandatory int parameter.
   * @param pnControllerType Mandatory Holder<Integer> parameter.
   */

  @DISPID(33) //= 0x21. The runtime will prefer the VTID if present
  @VTID(39)
  void getControllerType(
      int nIndex,
      Holder<Integer> pnControllerType);


  /**
   * <p>
   * method SetCurrentController
   * </p>
   *
   * @param nControllerType Mandatory int parameter.
   * @param nControllerNumber Mandatory int parameter.
   */

  @DISPID(34) //= 0x22. The runtime will prefer the VTID if present
  @VTID(40)
  void setCurrentController(
      int nControllerType,
      int nControllerNumber);


  /**
   * <p>
   * method GetCurrentController
   * </p>
   *
   * @param pnControllerType Mandatory Holder<Integer> parameter.
   * @param pnControllerNumber Mandatory Holder<Integer> parameter.
   */

  @DISPID(35) //= 0x23. The runtime will prefer the VTID if present
  @VTID(41)
  void getCurrentController(
      Holder<Integer> pnControllerType,
      Holder<Integer> pnControllerNumber);


  /**
   * <p>
   * method GetNumSpectra
   * </p>
   *
   * @param pnNumberOfSpectra Mandatory Holder<Integer> parameter.
   */

  @DISPID(36) //= 0x24. The runtime will prefer the VTID if present
  @VTID(42)
  void getNumSpectra(
      Holder<Integer> pnNumberOfSpectra);


  /**
   * <p>
   * method GetNumStatusLog
   * </p>
   *
   * @param pnNumberOfStatusLogEntries Mandatory Holder<Integer> parameter.
   */

  @DISPID(37) //= 0x25. The runtime will prefer the VTID if present
  @VTID(43)
  void getNumStatusLog(
      Holder<Integer> pnNumberOfStatusLogEntries);


  /**
   * <p>
   * method GetNumErrorLog
   * </p>
   *
   * @param pnNumberOfErrorLogEntries Mandatory Holder<Integer> parameter.
   */

  @DISPID(38) //= 0x26. The runtime will prefer the VTID if present
  @VTID(44)
  void getNumErrorLog(
      Holder<Integer> pnNumberOfErrorLogEntries);


  /**
   * <p>
   * method GetNumTuneData
   * </p>
   *
   * @param pnNumTuneData Mandatory Holder<Integer> parameter.
   */

  @DISPID(39) //= 0x27. The runtime will prefer the VTID if present
  @VTID(45)
  void getNumTuneData(
      Holder<Integer> pnNumTuneData);


  /**
   * <p>
   * method GetMassResolution
   * </p>
   *
   * @param pdMassResolution Mandatory Holder<Double> parameter.
   */

  @DISPID(40) //= 0x28. The runtime will prefer the VTID if present
  @VTID(46)
  void getMassResolution(
      Holder<Double> pdMassResolution);


  /**
   * <p>
   * method GetExpectedRunTime
   * </p>
   *
   * @param pdExpectedRunTime Mandatory Holder<Double> parameter.
   */

  @DISPID(41) //= 0x29. The runtime will prefer the VTID if present
  @VTID(47)
  void getExpectedRunTime(
      Holder<Double> pdExpectedRunTime);


  /**
   * <p>
   * method GetNumTrailerExtra
   * </p>
   *
   * @param pnNumberOfTrailerExtraEntries Mandatory Holder<Integer> parameter.
   */

  @DISPID(42) //= 0x2a. The runtime will prefer the VTID if present
  @VTID(48)
  void getNumTrailerExtra(
      Holder<Integer> pnNumberOfTrailerExtraEntries);


  /**
   * <p>
   * method GetLowMass
   * </p>
   *
   * @param pdLowMass Mandatory Holder<Double> parameter.
   */

  @DISPID(43) //= 0x2b. The runtime will prefer the VTID if present
  @VTID(49)
  void getLowMass(
      Holder<Double> pdLowMass);


  /**
   * <p>
   * method GetHighMass
   * </p>
   *
   * @param pdHighMass Mandatory Holder<Double> parameter.
   */

  @DISPID(44) //= 0x2c. The runtime will prefer the VTID if present
  @VTID(50)
  void getHighMass(
      Holder<Double> pdHighMass);


  /**
   * <p>
   * method GetStartTime
   * </p>
   *
   * @param pdStartTime Mandatory Holder<Double> parameter.
   */

  @DISPID(45) //= 0x2d. The runtime will prefer the VTID if present
  @VTID(51)
  void getStartTime(
      Holder<Double> pdStartTime);


  /**
   * <p>
   * method GetEndTime
   * </p>
   *
   * @param pdEndTime Mandatory Holder<Double> parameter.
   */

  @DISPID(46) //= 0x2e. The runtime will prefer the VTID if present
  @VTID(52)
  void getEndTime(
      Holder<Double> pdEndTime);


  /**
   * <p>
   * method GetMaxIntegratedIntensity
   * </p>
   *
   * @param pdMaxIntegIntensity Mandatory Holder<Double> parameter.
   */

  @DISPID(47) //= 0x2f. The runtime will prefer the VTID if present
  @VTID(53)
  void getMaxIntegratedIntensity(
      Holder<Double> pdMaxIntegIntensity);


  /**
   * <p>
   * method GetMaxIntensity
   * </p>
   *
   * @param pnMaxIntensity Mandatory Holder<Integer> parameter.
   */

  @DISPID(48) //= 0x30. The runtime will prefer the VTID if present
  @VTID(54)
  void getMaxIntensity(
      Holder<Integer> pnMaxIntensity);


  /**
   * <p>
   * method GetFirstSpectrumNumber
   * </p>
   *
   * @param pnFirstSpectrum Mandatory Holder<Integer> parameter.
   */

  @DISPID(49) //= 0x31. The runtime will prefer the VTID if present
  @VTID(55)
  void getFirstSpectrumNumber(
      Holder<Integer> pnFirstSpectrum);


  /**
   * <p>
   * method GetLastSpectrumNumber
   * </p>
   *
   * @param pnLastSpectrum Mandatory Holder<Integer> parameter.
   */

  @DISPID(50) //= 0x32. The runtime will prefer the VTID if present
  @VTID(56)
  void getLastSpectrumNumber(
      Holder<Integer> pnLastSpectrum);


  /**
   * <p>
   * method GetInstrumentID
   * </p>
   *
   * @param pnInstrumentID Mandatory Holder<Integer> parameter.
   */

  @DISPID(51) //= 0x33. The runtime will prefer the VTID if present
  @VTID(57)
  void getInstrumentID(
      Holder<Integer> pnInstrumentID);


  /**
   * <p>
   * method GetInletID
   * </p>
   *
   * @param pnInletID Mandatory Holder<Integer> parameter.
   */

  @DISPID(52) //= 0x34. The runtime will prefer the VTID if present
  @VTID(58)
  void getInletID(
      Holder<Integer> pnInletID);


  /**
   * <p>
   * method GetErrorFlag
   * </p>
   *
   * @param pnErrorFlag Mandatory Holder<Integer> parameter.
   */

  @DISPID(53) //= 0x35. The runtime will prefer the VTID if present
  @VTID(59)
  void getErrorFlag(
      Holder<Integer> pnErrorFlag);


  /**
   * <p>
   * method GetSampleVolume
   * </p>
   *
   * @param pdSampleVolume Mandatory Holder<Double> parameter.
   */

  @DISPID(54) //= 0x36. The runtime will prefer the VTID if present
  @VTID(60)
  void getSampleVolume(
      Holder<Double> pdSampleVolume);


  /**
   * <p>
   * method GetSampleWeight
   * </p>
   *
   * @param pdSampleWeight Mandatory Holder<Double> parameter.
   */

  @DISPID(55) //= 0x37. The runtime will prefer the VTID if present
  @VTID(61)
  void getSampleWeight(
      Holder<Double> pdSampleWeight);


  /**
   * <p>
   * method GetVialNumber
   * </p>
   *
   * @param pnVialNumber Mandatory Holder<Integer> parameter.
   */

  @DISPID(56) //= 0x38. The runtime will prefer the VTID if present
  @VTID(62)
  void getVialNumber(
      Holder<Integer> pnVialNumber);


  /**
   * <p>
   * method GetInjectionVolume
   * </p>
   *
   * @param pdInjectionVolume Mandatory Holder<Double> parameter.
   */

  @DISPID(57) //= 0x39. The runtime will prefer the VTID if present
  @VTID(63)
  void getInjectionVolume(
      Holder<Double> pdInjectionVolume);


  /**
   * <p>
   * method GetFlags
   * </p>
   *
   * @param pbstrFlags Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(58) //= 0x3a. The runtime will prefer the VTID if present
  @VTID(64)
  void getFlags(
      Holder<java.lang.String> pbstrFlags);


  /**
   * <p>
   * method GetAcquisitionFileName
   * </p>
   *
   * @param pbstrFileName Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(59) //= 0x3b. The runtime will prefer the VTID if present
  @VTID(65)
  void getAcquisitionFileName(
      Holder<java.lang.String> pbstrFileName);


  /**
   * <p>
   * method GetInstrumentDescription
   * </p>
   *
   * @param pbstrInstrumentDescription Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(60) //= 0x3c. The runtime will prefer the VTID if present
  @VTID(66)
  void getInstrumentDescription(
      Holder<java.lang.String> pbstrInstrumentDescription);


  /**
   * <p>
   * method GetAcquisitionDate
   * </p>
   *
   * @param pbstrAcquisitionDate Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(61) //= 0x3d. The runtime will prefer the VTID if present
  @VTID(67)
  void getAcquisitionDate(
      Holder<java.lang.String> pbstrAcquisitionDate);


  /**
   * <p>
   * method GetOperator
   * </p>
   *
   * @param pbstrOperator Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(62) //= 0x3e. The runtime will prefer the VTID if present
  @VTID(68)
  void getOperator(
      Holder<java.lang.String> pbstrOperator);


  /**
   * <p>
   * method GetComment1
   * </p>
   *
   * @param pbstrComment1 Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(63) //= 0x3f. The runtime will prefer the VTID if present
  @VTID(69)
  void getComment1(
      Holder<java.lang.String> pbstrComment1);


  /**
   * <p>
   * method GetComment2
   * </p>
   *
   * @param pbstrComment2 Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(64) //= 0x40. The runtime will prefer the VTID if present
  @VTID(70)
  void getComment2(
      Holder<java.lang.String> pbstrComment2);


  /**
   * <p>
   * method GetSampleAmountUnits
   * </p>
   *
   * @param pbstrSampleAmountUnits Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(65) //= 0x41. The runtime will prefer the VTID if present
  @VTID(71)
  void getSampleAmountUnits(
      Holder<java.lang.String> pbstrSampleAmountUnits);


  /**
   * <p>
   * method GetInjectionAmountUnits
   * </p>
   *
   * @param pbstrInjectionAmountUnits Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(66) //= 0x42. The runtime will prefer the VTID if present
  @VTID(72)
  void getInjectionAmountUnits(
      Holder<java.lang.String> pbstrInjectionAmountUnits);


  /**
   * <p>
   * method GetSampleVolumeUnits
   * </p>
   *
   * @param pbstrSampleVolumeUnits Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(67) //= 0x43. The runtime will prefer the VTID if present
  @VTID(73)
  void getSampleVolumeUnits(
      Holder<java.lang.String> pbstrSampleVolumeUnits);


  /**
   * <p>
   * method GetFilters
   * </p>
   *
   * @param pvarFilterArray Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(68) //= 0x44. The runtime will prefer the VTID if present
  @VTID(74)
  void getFilters(
      java.lang.Object pvarFilterArray,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method ScanNumFromRT
   * </p>
   *
   * @param dRT Mandatory double parameter.
   * @param pnScanNumber Mandatory Holder<Integer> parameter.
   */

  @DISPID(69) //= 0x45. The runtime will prefer the VTID if present
  @VTID(75)
  void scanNumFromRT(
      double dRT,
      Holder<Integer> pnScanNumber);


  /**
   * <p>
   * method RTFromScanNum
   * </p>
   *
   * @param nScanNumber Mandatory int parameter.
   * @param pdRT Mandatory Holder<Double> parameter.
   */

  @DISPID(70) //= 0x46. The runtime will prefer the VTID if present
  @VTID(76)
  void rtFromScanNum(
      int nScanNumber,
      Holder<Double> pdRT);


  /**
   * <p>
   * method GetFilterForScanNum
   * </p>
   *
   * @param nScanNumber Mandatory int parameter.
   * @param pbstrFilter Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(71) //= 0x47. The runtime will prefer the VTID if present
  @VTID(77)
  void getFilterForScanNum(
      int nScanNumber,
      Holder<java.lang.String> pbstrFilter);


  /**
   * <p>
   * method GetFilterForScanRT
   * </p>
   *
   * @param dRT Mandatory double parameter.
   * @param pbstrFilter Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(72) //= 0x48. The runtime will prefer the VTID if present
  @VTID(78)
  void getFilterForScanRT(
      double dRT,
      Holder<java.lang.String> pbstrFilter);


  /**
   * <p>
   * method GetMassListFromScanNum
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
   */

  @DISPID(73) //= 0x49. The runtime will prefer the VTID if present
  @VTID(79)
  void getMassListFromScanNum(
      Holder<Integer> pnScanNumber,
      java.lang.String bstrFilter,
      int nIntensityCutoffType,
      int nIntensityCutoffValue,
      int nMaxNumberOfPeaks,
      int bCentroidResult,
      Holder<Double> pdCentroidPeakWidth,
      java.lang.Object pvarMassList,
      java.lang.Object pvarPeakFlags,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetMassListFromRT
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
   */

  @DISPID(74) //= 0x4a. The runtime will prefer the VTID if present
  @VTID(80)
  void getMassListFromRT(
      Holder<Double> pdRT,
      java.lang.String bstrFilter,
      int nIntensityCutoffType,
      int nIntensityCutoffValue,
      int nMaxNumberOfPeaks,
      int bCentroidResult,
      Holder<Double> pdCentroidPeakWidth,
      java.lang.Object pvarMassList,
      java.lang.Object pvarPeakFlags,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetNextMassListFromScanNum
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
   */

  @DISPID(75) //= 0x4b. The runtime will prefer the VTID if present
  @VTID(81)
  void getNextMassListFromScanNum(
      Holder<Integer> pnScanNumber,
      java.lang.String bstrFilter,
      int nIntensityCutoffType,
      int nIntensityCutoffValue,
      int nMaxNumberOfPeaks,
      int bCentroidResult,
      Holder<Double> pdCentroidPeakWidth,
      java.lang.Object pvarMassList,
      java.lang.Object pvarPeakFlags,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetPrevMassListFromScanNum
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
   */

  @DISPID(76) //= 0x4c. The runtime will prefer the VTID if present
  @VTID(82)
  void getPrevMassListFromScanNum(
      Holder<Integer> pnScanNumber,
      java.lang.String bstrFilter,
      int nIntensityCutoffType,
      int nIntensityCutoffValue,
      int nMaxNumberOfPeaks,
      int bCentroidResult,
      Holder<Double> pdCentroidPeakWidth,
      java.lang.Object pvarMassList,
      java.lang.Object pvarPeakFlags,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method IsProfileScanForScanNum
   * </p>
   *
   * @param nScanNumber Mandatory int parameter.
   * @param pbIsProfileScan Mandatory Holder<Integer> parameter.
   */

  @DISPID(77) //= 0x4d. The runtime will prefer the VTID if present
  @VTID(83)
  void isProfileScanForScanNum(
      int nScanNumber,
      Holder<Integer> pbIsProfileScan);


  /**
   * <p>
   * method IsCentroidScanForScanNum
   * </p>
   *
   * @param nScanNumber Mandatory int parameter.
   * @param pbIsCentroidScan Mandatory Holder<Integer> parameter.
   */

  @DISPID(78) //= 0x4e. The runtime will prefer the VTID if present
  @VTID(84)
  void isCentroidScanForScanNum(
      int nScanNumber,
      Holder<Integer> pbIsCentroidScan);


  /**
   * <p>
   * method GetScanHeaderInfoForScanNum
   * </p>
   *
   * @param nScanNumber Mandatory int parameter.
   * @param pnNumPackets Mandatory Holder<Integer> parameter.
   * @param pdStartTime Mandatory Holder<Double> parameter.
   * @param pdLowMass Mandatory Holder<Double> parameter.
   * @param pdHighMass Mandatory Holder<Double> parameter.
   * @param pdTIC Mandatory Holder<Double> parameter.
   * @param pdBasePeakMass Mandatory Holder<Double> parameter.
   * @param pdBasePeakIntensity Mandatory Holder<Double> parameter.
   * @param pnNumChannels Mandatory Holder<Integer> parameter.
   * @param pbUniformTime Mandatory Holder<Integer> parameter.
   * @param pdFrequency Mandatory Holder<Double> parameter.
   */

  @DISPID(79) //= 0x4f. The runtime will prefer the VTID if present
  @VTID(85)
  void getScanHeaderInfoForScanNum(
      int nScanNumber,
      Holder<Integer> pnNumPackets,
      Holder<Double> pdStartTime,
      Holder<Double> pdLowMass,
      Holder<Double> pdHighMass,
      Holder<Double> pdTIC,
      Holder<Double> pdBasePeakMass,
      Holder<Double> pdBasePeakIntensity,
      Holder<Integer> pnNumChannels,
      Holder<Integer> pbUniformTime,
      Holder<Double> pdFrequency);


  /**
   * <p>
   * method GetStatusLogForScanNum
   * </p>
   *
   * @param nScanNumber Mandatory int parameter.
   * @param pdStatusLogRT Mandatory Holder<Double> parameter.
   * @param pvarLabels Mandatory java.lang.Object parameter.
   * @param pvarValues Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(80) //= 0x50. The runtime will prefer the VTID if present
  @VTID(86)
  void getStatusLogForScanNum(
      int nScanNumber,
      Holder<Double> pdStatusLogRT,
      java.lang.Object pvarLabels,
      java.lang.Object pvarValues,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetStatusLogForRT
   * </p>
   *
   * @param pdRT Mandatory Holder<Double> parameter.
   * @param pvarLabels Mandatory java.lang.Object parameter.
   * @param pvarValues Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(81) //= 0x51. The runtime will prefer the VTID if present
  @VTID(87)
  void getStatusLogForRT(
      Holder<Double> pdRT,
      java.lang.Object pvarLabels,
      java.lang.Object pvarValues,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetStatusLogLabelsForScanNum
   * </p>
   *
   * @param nScanNumber Mandatory int parameter.
   * @param pdStatusLogRT Mandatory Holder<Double> parameter.
   * @param pvarLabels Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(82) //= 0x52. The runtime will prefer the VTID if present
  @VTID(88)
  void getStatusLogLabelsForScanNum(
      int nScanNumber,
      Holder<Double> pdStatusLogRT,
      java.lang.Object pvarLabels,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetStatusLogLabelsForRT
   * </p>
   *
   * @param pdRT Mandatory Holder<Double> parameter.
   * @param pvarLabels Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(83) //= 0x53. The runtime will prefer the VTID if present
  @VTID(89)
  void getStatusLogLabelsForRT(
      Holder<Double> pdRT,
      java.lang.Object pvarLabels,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetStatusLogValueForScanNum
   * </p>
   *
   * @param nScanNumber Mandatory int parameter.
   * @param bstrLabel Mandatory java.lang.String parameter.
   * @param pdStatusLogRT Mandatory Holder<Double> parameter.
   * @param pvarValue Mandatory java.lang.Object parameter.
   */

  @DISPID(84) //= 0x54. The runtime will prefer the VTID if present
  @VTID(90)
  void getStatusLogValueForScanNum(
      int nScanNumber,
      java.lang.String bstrLabel,
      Holder<Double> pdStatusLogRT,
      java.lang.Object pvarValue);


  /**
   * <p>
   * method GetStatusLogValueForRT
   * </p>
   *
   * @param pdRT Mandatory Holder<Double> parameter.
   * @param bstrLabel Mandatory java.lang.String parameter.
   * @param pvarValue Mandatory java.lang.Object parameter.
   */

  @DISPID(85) //= 0x55. The runtime will prefer the VTID if present
  @VTID(91)
  void getStatusLogValueForRT(
      Holder<Double> pdRT,
      java.lang.String bstrLabel,
      java.lang.Object pvarValue);


  /**
   * <p>
   * method GetTrailerExtraForScanNum
   * </p>
   *
   * @param nScanNumber Mandatory int parameter.
   * @param pvarLabels Mandatory java.lang.Object parameter.
   * @param pvarValues Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(86) //= 0x56. The runtime will prefer the VTID if present
  @VTID(92)
  void getTrailerExtraForScanNum(
      int nScanNumber,
      java.lang.Object pvarLabels,
      java.lang.Object pvarValues,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetTrailerExtraForRT
   * </p>
   *
   * @param pdRT Mandatory Holder<Double> parameter.
   * @param pvarLabels Mandatory java.lang.Object parameter.
   * @param pvarValues Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(87) //= 0x57. The runtime will prefer the VTID if present
  @VTID(93)
  void getTrailerExtraForRT(
      Holder<Double> pdRT,
      java.lang.Object pvarLabels,
      java.lang.Object pvarValues,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetTrailerExtraLabelsForScanNum
   * </p>
   *
   * @param nScanNumber Mandatory int parameter.
   * @param pvarLabels Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(88) //= 0x58. The runtime will prefer the VTID if present
  @VTID(94)
  void getTrailerExtraLabelsForScanNum(
      int nScanNumber,
      java.lang.Object pvarLabels,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetTrailerExtraLabelsForRT
   * </p>
   *
   * @param pdRT Mandatory Holder<Double> parameter.
   * @param pvarLabels Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(89) //= 0x59. The runtime will prefer the VTID if present
  @VTID(95)
  void getTrailerExtraLabelsForRT(
      Holder<Double> pdRT,
      java.lang.Object pvarLabels,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetTrailerExtraValueForScanNum
   * </p>
   *
   * @param nScanNumber Mandatory int parameter.
   * @param bstrLabel Mandatory java.lang.String parameter.
   * @param pvarValue Mandatory java.lang.Object parameter.
   */

  @DISPID(90) //= 0x5a. The runtime will prefer the VTID if present
  @VTID(96)
  void getTrailerExtraValueForScanNum(
      int nScanNumber,
      java.lang.String bstrLabel,
      java.lang.Object pvarValue);


  /**
   * <p>
   * method GetTrailerExtraValueForRT
   * </p>
   *
   * @param pdRT Mandatory Holder<Double> parameter.
   * @param bstrLabel Mandatory java.lang.String parameter.
   * @param pvarValue Mandatory java.lang.Object parameter.
   */

  @DISPID(91) //= 0x5b. The runtime will prefer the VTID if present
  @VTID(97)
  void getTrailerExtraValueForRT(
      Holder<Double> pdRT,
      java.lang.String bstrLabel,
      java.lang.Object pvarValue);


  /**
   * <p>
   * method GetErrorLogItem
   * </p>
   *
   * @param nItemNumber Mandatory int parameter.
   * @param pdRT Mandatory Holder<Double> parameter.
   * @param pbstrErrorMessage Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(92) //= 0x5c. The runtime will prefer the VTID if present
  @VTID(98)
  void getErrorLogItem(
      int nItemNumber,
      Holder<Double> pdRT,
      Holder<java.lang.String> pbstrErrorMessage);


  /**
   * <p>
   * method GetTuneData
   * </p>
   *
   * @param nSegmentNumber Mandatory int parameter.
   * @param pvarLabels Mandatory java.lang.Object parameter.
   * @param pvarValues Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(93) //= 0x5d. The runtime will prefer the VTID if present
  @VTID(99)
  void getTuneData(
      int nSegmentNumber,
      java.lang.Object pvarLabels,
      java.lang.Object pvarValues,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetNumInstMethods
   * </p>
   *
   * @param pnNumInstMethods Mandatory Holder<Integer> parameter.
   */

  @DISPID(94) //= 0x5e. The runtime will prefer the VTID if present
  @VTID(100)
  void getNumInstMethods(
      Holder<Integer> pnNumInstMethods);


  /**
   * <p>
   * method GetInstMethod
   * </p>
   *
   * @param nInstMethodItem Mandatory int parameter.
   * @param pbstrInstMethod Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(95) //= 0x5f. The runtime will prefer the VTID if present
  @VTID(101)
  void getInstMethod(
      int nInstMethodItem,
      Holder<java.lang.String> pbstrInstMethod);


  /**
   * <p>
   * method GetChroData
   * </p>
   *
   * @param nChroType1 Mandatory int parameter.
   * @param nChroOperator Mandatory int parameter.
   * @param nChroType2 Mandatory int parameter.
   * @param bstrFilter Mandatory java.lang.String parameter.
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

  @DISPID(96) //= 0x60. The runtime will prefer the VTID if present
  @VTID(102)
  void getChroData(
      int nChroType1,
      int nChroOperator,
      int nChroType2,
      java.lang.String bstrFilter,
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
   * method RefreshViewOfFile
   * </p>
   */

  @DISPID(97) //= 0x61. The runtime will prefer the VTID if present
  @VTID(103)
  void refreshViewOfFile();


  /**
   * <p>
   * method GetTuneDataValue
   * </p>
   *
   * @param nSegmentNumber Mandatory int parameter.
   * @param bstrLabel Mandatory java.lang.String parameter.
   * @param pvarValue Mandatory java.lang.Object parameter.
   */

  @DISPID(98) //= 0x62. The runtime will prefer the VTID if present
  @VTID(104)
  void getTuneDataValue(
      int nSegmentNumber,
      java.lang.String bstrLabel,
      java.lang.Object pvarValue);


  /**
   * <p>
   * method GetTuneDataLabels
   * </p>
   *
   * @param nSegmentNumber Mandatory int parameter.
   * @param pvarLabels Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(99) //= 0x63. The runtime will prefer the VTID if present
  @VTID(105)
  void getTuneDataLabels(
      int nSegmentNumber,
      java.lang.Object pvarLabels,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetInstName
   * </p>
   *
   * @param pbstrInstName Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(100) //= 0x64. The runtime will prefer the VTID if present
  @VTID(106)
  void getInstName(
      Holder<java.lang.String> pbstrInstName);


  /**
   * <p>
   * method GetInstModel
   * </p>
   *
   * @param pbstrInstModel Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(107)
  void getInstModel(
      Holder<java.lang.String> pbstrInstModel);


  /**
   * <p>
   * method GetInstSerialNumber
   * </p>
   *
   * @param pbstrInstSerialNumber Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(108)
  void getInstSerialNumber(
      Holder<java.lang.String> pbstrInstSerialNumber);


  /**
   * <p>
   * method GetInstSoftwareVersion
   * </p>
   *
   * @param pbstrInstSoftwareVersion Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(109)
  void getInstSoftwareVersion(
      Holder<java.lang.String> pbstrInstSoftwareVersion);


  /**
   * <p>
   * method GetInstHardwareVersion
   * </p>
   *
   * @param pbstrInstHardwareVersion Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(110)
  void getInstHardwareVersion(
      Holder<java.lang.String> pbstrInstHardwareVersion);


  /**
   * <p>
   * method GetInstFlags
   * </p>
   *
   * @param pbstrInstFlags Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(105) //= 0x69. The runtime will prefer the VTID if present
  @VTID(111)
  void getInstFlags(
      Holder<java.lang.String> pbstrInstFlags);


  /**
   * <p>
   * method GetInstNumChannelLabels
   * </p>
   *
   * @param pnInstNumChannelLabels Mandatory Holder<Integer> parameter.
   */

  @DISPID(106) //= 0x6a. The runtime will prefer the VTID if present
  @VTID(112)
  void getInstNumChannelLabels(
      Holder<Integer> pnInstNumChannelLabels);


  /**
   * <p>
   * method GetInstChannelLabel
   * </p>
   *
   * @param nChannelLabelNumber Mandatory int parameter.
   * @param pbstrInstChannelLabel Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(107) //= 0x6b. The runtime will prefer the VTID if present
  @VTID(113)
  void getInstChannelLabel(
      int nChannelLabelNumber,
      Holder<java.lang.String> pbstrInstChannelLabel);


  /**
   * <p>
   * method GetNumberOfControllersOfType
   * </p>
   *
   * @param nControllerType Mandatory int parameter.
   * @param pnNumControllersOfType Mandatory Holder<Integer> parameter.
   */

  @DISPID(108) //= 0x6c. The runtime will prefer the VTID if present
  @VTID(114)
  void getNumberOfControllersOfType(
      int nControllerType,
      Holder<Integer> pnNumControllersOfType);


  /**
   * <p>
   * method GetAverageMassList
   * </p>
   *
   * @param pnFirstAvgScanNumber Mandatory Holder<Integer> parameter.
   * @param pnLastAvgScanNumber Mandatory Holder<Integer> parameter.
   * @param pnFirstBkg1ScanNumber Mandatory Holder<Integer> parameter.
   * @param pnLastBkg1ScanNumber Mandatory Holder<Integer> parameter.
   * @param pnFirstBkg2ScanNumber Mandatory Holder<Integer> parameter.
   * @param pnLastBkg2ScanNumber Mandatory Holder<Integer> parameter.
   * @param bstrFilter Mandatory java.lang.String parameter.
   * @param nIntensityCutoffType Mandatory int parameter.
   * @param nIntensityCutoffValue Mandatory int parameter.
   * @param nMaxNumberOfPeaks Mandatory int parameter.
   * @param bCentroidResult Mandatory int parameter.
   * @param pdCentroidPeakWidth Mandatory Holder<Double> parameter.
   * @param pvarMassList Mandatory java.lang.Object parameter.
   * @param pvarPeakFlags Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(109) //= 0x6d. The runtime will prefer the VTID if present
  @VTID(115)
  void getAverageMassList(
      Holder<Integer> pnFirstAvgScanNumber,
      Holder<Integer> pnLastAvgScanNumber,
      Holder<Integer> pnFirstBkg1ScanNumber,
      Holder<Integer> pnLastBkg1ScanNumber,
      Holder<Integer> pnFirstBkg2ScanNumber,
      Holder<Integer> pnLastBkg2ScanNumber,
      java.lang.String bstrFilter,
      int nIntensityCutoffType,
      int nIntensityCutoffValue,
      int nMaxNumberOfPeaks,
      int bCentroidResult,
      Holder<Double> pdCentroidPeakWidth,
      java.lang.Object pvarMassList,
      java.lang.Object pvarPeakFlags,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method IsThereMSData
   * </p>
   *
   * @param pbMSData Mandatory Holder<Integer> parameter.
   */

  @DISPID(110) //= 0x6e. The runtime will prefer the VTID if present
  @VTID(116)
  void isThereMSData(
      Holder<Integer> pbMSData);


  /**
   * <p>
   * method HasExpMethod
   * </p>
   *
   * @param pbMethod Mandatory Holder<Integer> parameter.
   */

  @DISPID(111) //= 0x6f. The runtime will prefer the VTID if present
  @VTID(117)
  void hasExpMethod(
      Holder<Integer> pbMethod);


  /**
   * <p>
   * method GetFilterMassPrecision
   * </p>
   *
   * @param pnFilterMassPrecision Mandatory Holder<Integer> parameter.
   */

  @DISPID(112) //= 0x70. The runtime will prefer the VTID if present
  @VTID(118)
  void getFilterMassPrecision(
      Holder<Integer> pnFilterMassPrecision);


  /**
   * <p>
   * method GetStatusLogForPos
   * </p>
   *
   * @param nPos Mandatory int parameter.
   * @param pvarRT Mandatory java.lang.Object parameter.
   * @param pvarValue Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(113) //= 0x71. The runtime will prefer the VTID if present
  @VTID(119)
  void getStatusLogForPos(
      int nPos,
      java.lang.Object pvarRT,
      java.lang.Object pvarValue,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetStatusLogAtIndex
   * </p>
   *
   * @param pvarIndex Mandatory java.lang.Object parameter.
   * @param pvarValues Mandatory java.lang.Object parameter.
   * @param pnArraySize Mandatory Holder<Integer> parameter.
   */

  @DISPID(114) //= 0x72. The runtime will prefer the VTID if present
  @VTID(120)
  void getStatusLogPlottableIndex(
      java.lang.Object pvarIndex,
      java.lang.Object pvarValues,
      Holder<Integer> pnArraySize);


  /**
   * <p>
   * method GetInstMethodNames
   * </p>
   *
   * @param pnNumInstMethods Mandatory Holder<Integer> parameter.
   * @param pvarNames Mandatory java.lang.Object parameter.
   */

  @DISPID(115) //= 0x73. The runtime will prefer the VTID if present
  @VTID(121)
  void getInstMethodNames(
      Holder<Integer> pnNumInstMethods,
      java.lang.Object pvarNames);


  /**
   * <p>
   * method SetMassTolerance
   * </p>
   *
   * @param bUseUserDefined Mandatory int parameter.
   * @param dMassTolerance Mandatory double parameter.
   * @param nUnits Mandatory int parameter.
   */

  @DISPID(116) //= 0x74. The runtime will prefer the VTID if present
  @VTID(122)
  void setMassTolerance(
      int bUseUserDefined,
      double dMassTolerance,
      int nUnits);


  /**
   * <p>
   * method GetChros
   * </p>
   *
   * @param nNumChros Mandatory int parameter.
   * @param pdStartTime Mandatory Holder<Double> parameter.
   * @param pdEndTime Mandatory Holder<Double> parameter.
   * @param pvarChroParamsArray Mandatory java.lang.Object parameter.
   * @param pvarChroDataSizeArray Mandatory java.lang.Object parameter.
   * @param pvarChroDataArray Mandatory java.lang.Object parameter.
   * @param pvarPeakFlagsArray Mandatory java.lang.Object parameter.
   */

  @DISPID(117) //= 0x75. The runtime will prefer the VTID if present
  @VTID(123)
  void getChros(
      int nNumChros,
      Holder<Double> pdStartTime,
      Holder<Double> pdEndTime,
      java.lang.Object pvarChroParamsArray,
      java.lang.Object pvarChroDataSizeArray,
      java.lang.Object pvarChroDataArray,
      java.lang.Object pvarPeakFlagsArray);

  // Properties:
}
