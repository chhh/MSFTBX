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
 * IXVirMS Interface
 */
@IID("{55EA38B7-5419-4BE4-9198-3E4D78E64632}")
public interface IXVirMS extends Com4jObject {
  // Methods:
  /**
   * <p>
   * method Create
   * </p>
   * @param szFileName Mandatory java.lang.String parameter.
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  void create(
    @MarshalAs(NativeType.Unicode) java.lang.String szFileName);


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
   * @param pbstrFileName Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  void getFileName(
    Holder<java.lang.String> pbstrFileName);


  /**
   * <p>
   * property IsError
   * </p>
   * <p>
   * Getter method for the COM property "IsError"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(10)
  int isError();


  /**
   * <p>
   * property ErrorCode
   * </p>
   * <p>
   * Getter method for the COM property "ErrorCode"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(11)
  int errorCode();


  /**
   * <p>
   * property IsValid
   * </p>
   * <p>
   * Getter method for the COM property "IsValid"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(12)
  int isValid();


  /**
   * <p>
   * method WriteInstID
   * </p>
   * @param szName Mandatory java.lang.String parameter.
   * @param szModel Mandatory java.lang.String parameter.
   * @param szSerialNumber Mandatory java.lang.String parameter.
   * @param szSoftwareRev Mandatory java.lang.String parameter.
   * @param expType Mandatory java.lang.String parameter.
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(13)
  void writeInstID(
    @MarshalAs(NativeType.Unicode) java.lang.String szName,
    @MarshalAs(NativeType.Unicode) java.lang.String szModel,
    @MarshalAs(NativeType.Unicode) java.lang.String szSerialNumber,
    @MarshalAs(NativeType.Unicode) java.lang.String szSoftwareRev,
    @MarshalAs(NativeType.Unicode) java.lang.String expType);


  /**
   * <p>
   * method WriteRunHeaderInfo
   * </p>
   * @param dExpectedRunTime Mandatory double parameter.
   * @param dMassResolution Mandatory double parameter.
   * @param szComment1 Mandatory java.lang.String parameter.
   * @param szComment2 Mandatory java.lang.String parameter.
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(14)
  void writeRunHeaderInfo(
    double dExpectedRunTime,
    double dMassResolution,
    @MarshalAs(NativeType.Unicode) java.lang.String szComment1,
    @MarshalAs(NativeType.Unicode) java.lang.String szComment2);


  /**
   * <p>
   * method WriteInstData
   * </p>
   * @param pcData Mandatory Holder<Byte> parameter.
   * @param nDataSize Mandatory int parameter.
   * @param eType Mandatory umich.ms.thermo.MS_PacketTypes parameter.
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(15)
  void writeInstData(
    Holder<Byte> pcData,
    int nDataSize,
    umich.ms.fileio.filetypes.thermo.raw.com4j.MS_PacketTypes eType);


  /**
   * <p>
   * method SetTrailerHeaderNumFields
   * </p>
   * @param nFields Mandatory int parameter.
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(16)
  void setTrailerHeaderNumFields(
    int nFields);


  /**
   * <p>
   * method SetTrailerHeaderField
   * </p>
   * @param nIdx Mandatory int parameter.
   * @param szLabel Mandatory java.lang.String parameter.
   * @param eDataType Mandatory umich.ms.thermo.MS_DataTypes parameter.
   * @param nPrecision Mandatory int parameter.
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(17)
  void setTrailerHeaderField(
    int nIdx,
    @MarshalAs(NativeType.Unicode) java.lang.String szLabel,
    umich.ms.fileio.filetypes.thermo.raw.com4j.MS_DataTypes eDataType,
    int nPrecision);


  /**
   * <p>
   * method WriteTrailerHeader
   * </p>
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(18)
  void writeTrailerHeader();


  /**
   * <p>
   * method SetStatusLogHeaderNumFields
   * </p>
   * @param nFields Mandatory int parameter.
   */

  @DISPID(13) //= 0xd. The runtime will prefer the VTID if present
  @VTID(19)
  void setStatusLogHeaderNumFields(
    int nFields);


  /**
   * <p>
   * method SetStatusLogHeaderField
   * </p>
   * @param nIdx Mandatory int parameter.
   * @param szLabel Mandatory java.lang.String parameter.
   * @param eDataType Mandatory umich.ms.thermo.MS_DataTypes parameter.
   * @param nPrecision Mandatory int parameter.
   */

  @DISPID(14) //= 0xe. The runtime will prefer the VTID if present
  @VTID(20)
  void setStatusLogHeaderField(
    int nIdx,
    @MarshalAs(NativeType.Unicode) java.lang.String szLabel,
    umich.ms.fileio.filetypes.thermo.raw.com4j.MS_DataTypes eDataType,
    int nPrecision);


  /**
   * <p>
   * method WriteStatusLogHeader
   * </p>
   */

  @DISPID(15) //= 0xf. The runtime will prefer the VTID if present
  @VTID(21)
  void writeStatusLogHeader();


  /**
   * <p>
   * method SetTuneDataHeaderNumFields
   * </p>
   * @param nFields Mandatory int parameter.
   */

  @DISPID(16) //= 0x10. The runtime will prefer the VTID if present
  @VTID(22)
  void setTuneDataHeaderNumFields(
    int nFields);


  /**
   * <p>
   * method SetTuneDataHeaderField
   * </p>
   * @param nIdx Mandatory int parameter.
   * @param szLabel Mandatory java.lang.String parameter.
   * @param eDataType Mandatory umich.ms.thermo.MS_DataTypes parameter.
   * @param nPrecision Mandatory int parameter.
   */

  @DISPID(17) //= 0x11. The runtime will prefer the VTID if present
  @VTID(23)
  void setTuneDataHeaderField(
    int nIdx,
    @MarshalAs(NativeType.Unicode) java.lang.String szLabel,
    umich.ms.fileio.filetypes.thermo.raw.com4j.MS_DataTypes eDataType,
    int nPrecision);


  /**
   * <p>
   * method WriteTuneDataHeader
   * </p>
   */

  @DISPID(18) //= 0x12. The runtime will prefer the VTID if present
  @VTID(24)
  void writeTuneDataHeader();


  /**
   * <p>
   * method WriteTuneData
   * </p>
   * @param pcData Mandatory Holder<Byte> parameter.
   */

  @DISPID(19) //= 0x13. The runtime will prefer the VTID if present
  @VTID(25)
  void writeTuneData(
    Holder<Byte> pcData);


  /**
   * <p>
   * method WriteStatusLog
   * </p>
   * @param fTime Mandatory float parameter.
   * @param pcData Mandatory Holder<Byte> parameter.
   */

  @DISPID(20) //= 0x14. The runtime will prefer the VTID if present
  @VTID(26)
  void writeStatusLog(
    float fTime,
    Holder<Byte> pcData);


  /**
   * <p>
   * method WriteTrailer
   * </p>
   * @param pcData Mandatory Holder<Byte> parameter.
   */

  @DISPID(21) //= 0x15. The runtime will prefer the VTID if present
  @VTID(27)
  void writeTrailer(
    Holder<Byte> pcData);


    /**
     * <p>
     * method InitMethodScanEvents
     * </p>
     */

    @DISPID(23) //= 0x17. The runtime will prefer the VTID if present
    @VTID(29)
    void initMethodScanEvents();


      /**
       * <p>
       * method WriteMethodScanEvents
       * </p>
       */

      @DISPID(25) //= 0x19. The runtime will prefer the VTID if present
      @VTID(31)
      void writeMethodScanEvents();


          /**
           * <p>
           * method InitializeScanIndex
           * </p>
           * @param nScanIndexPosition Mandatory int parameter.
           * @param eType Mandatory umich.ms.thermo.MS_PacketTypes parameter.
           */

          @DISPID(28) //= 0x1c. The runtime will prefer the VTID if present
          @VTID(34)
          void initializeScanIndex(
            int nScanIndexPosition,
            umich.ms.fileio.filetypes.thermo.raw.com4j.MS_PacketTypes eType);


            // Properties:
          }
