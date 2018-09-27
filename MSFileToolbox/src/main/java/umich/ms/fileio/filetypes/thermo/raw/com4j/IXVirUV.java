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
import com4j.MarshalAs;
import com4j.NativeType;
import com4j.VTID;

/**
 * IXVirUV Interface
 */
@IID("{796CB3FE-C696-4AFE-B719-18246F38A740}")
public interface IXVirUV extends Com4jObject {
  // Methods:

  /**
   * <p>
   * method Create
   * </p>
   *
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
   *
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
   *
   * @return Returns a value of type int
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
   *
   * @return Returns a value of type int
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
   *
   * @return Returns a value of type int
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(12)
  int isValid();


  /**
   * <p>
   * method WriteErrorLog
   * </p>
   *
   * @param fTime Mandatory float parameter.
   * @param szError Mandatory java.lang.String parameter.
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(13)
  void writeErrorLog(
      float fTime,
      @MarshalAs(NativeType.Unicode) java.lang.String szError);


  /**
   * <p>
   * method WriteInstID
   * </p>
   *
   * @param szName Mandatory java.lang.String parameter.
   * @param szModel Mandatory java.lang.String parameter.
   * @param szSerialNumber Mandatory java.lang.String parameter.
   * @param szSoftwareRev Mandatory java.lang.String parameter.
   * @param szLabel1 Mandatory java.lang.String parameter.
   * @param szLabel2 Mandatory java.lang.String parameter.
   * @param szLabel3 Mandatory java.lang.String parameter.
   * @param szLabel4 Mandatory java.lang.String parameter.
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(14)
  void writeInstID(
      @MarshalAs(NativeType.Unicode) java.lang.String szName,
      @MarshalAs(NativeType.Unicode) java.lang.String szModel,
      @MarshalAs(NativeType.Unicode) java.lang.String szSerialNumber,
      @MarshalAs(NativeType.Unicode) java.lang.String szSoftwareRev,
      @MarshalAs(NativeType.Unicode) java.lang.String szLabel1,
      @MarshalAs(NativeType.Unicode) java.lang.String szLabel2,
      @MarshalAs(NativeType.Unicode) java.lang.String szLabel3,
      @MarshalAs(NativeType.Unicode) java.lang.String szLabel4);


  /**
   * <p>
   * method WriteRunHeaderInfo
   * </p>
   *
   * @param dExpectedRunTime Mandatory double parameter.
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(15)
  void writeRunHeaderInfo(
      double dExpectedRunTime);


  /**
   * <p>
   * method WriteInstData
   * </p>
   *
   * @param pcData Mandatory Holder<Byte> parameter.
   * @param nDataSize Mandatory int parameter.
   * @param eType Mandatory umich.ms.thermo.MS_PacketTypes parameter.
   * @param nDataLen Mandatory int parameter.
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(16)
  void writeInstData(
      Holder<Byte> pcData,
      int nDataSize,
      umich.ms.fileio.filetypes.thermo.raw.com4j.MS_PacketTypes eType,
      int nDataLen);

  // Properties:
}
