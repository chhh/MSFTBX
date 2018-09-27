
/*
 * Copyright (c) 2017 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Helper type to allow either a cvParam or a userParam to be provided for an element.
 *
 * <p>Java class for ParamType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ParamType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;group ref="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamGroup"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParamType", propOrder = {
    "cvParam",
    "userParam"
})
public class ParamType {

  protected CVParamType cvParam;
  protected UserParamType userParam;

  /**
   * Gets the value of the cvParam property.
   *
   * @return possible object is {@link CVParamType }
   */
  public CVParamType getCvParam() {
    return cvParam;
  }

  /**
   * Sets the value of the cvParam property.
   *
   * @param value allowed object is {@link CVParamType }
   */
  public void setCvParam(CVParamType value) {
    this.cvParam = value;
  }

  /**
   * Gets the value of the userParam property.
   *
   * @return possible object is {@link UserParamType }
   */
  public UserParamType getUserParam() {
    return userParam;
  }

  /**
   * Sets the value of the userParam property.
   *
   * @param value allowed object is {@link UserParamType }
   */
  public void setUserParam(UserParamType value) {
    this.userParam = value;
  }

}
