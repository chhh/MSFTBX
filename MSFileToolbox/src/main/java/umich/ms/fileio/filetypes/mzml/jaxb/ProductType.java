
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

package umich.ms.fileio.filetypes.mzml.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * The method of product ion selection and activation in a precursor ion scan
 *
 * <p>Java class for ProductType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ProductType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isolationWindow" type="{http://psi.hupo.org/ms/mzml}ParamGroupType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductType", propOrder = {
    "isolationWindow"
})
public class ProductType {

  protected ParamGroupType isolationWindow;

  /**
   * Gets the value of the isolationWindow property.
   *
   * @return possible object is {@link ParamGroupType }
   */
  public ParamGroupType getIsolationWindow() {
    return isolationWindow;
  }

  /**
   * Sets the value of the isolationWindow property.
   *
   * @param value allowed object is {@link ParamGroupType }
   */
  public void setIsolationWindow(ParamGroupType value) {
    this.isolationWindow = value;
  }

}
