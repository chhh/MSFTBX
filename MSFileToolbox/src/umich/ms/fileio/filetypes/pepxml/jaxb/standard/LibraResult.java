
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

package umich.ms.fileio.filetypes.pepxml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intensity" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="channel" use="required" type="{http://regis-web.systemsbiology.net/pepXML}positiveInt" />
 *                 &lt;attribute name="target_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="absolute" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="normalized" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="reject" type="{http://www.w3.org/2001/XMLSchema}boolean" default="0" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="is_rejected" type="{http://www.w3.org/2001/XMLSchema}boolean" default="0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "intensity"
})
@XmlRootElement(name = "libra_result")
public class LibraResult {

  @XmlElement(required = true)
  protected List<Intensity> intensity;
  @XmlAttribute(name = "is_rejected")
  protected Boolean isRejected;

  /**
   * Gets the value of the intensity property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the intensity property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getIntensity().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Intensity }
   */
  public List<Intensity> getIntensity() {
    if (intensity == null) {
      intensity = new ArrayList<Intensity>(1);
    }
    return this.intensity;
  }

  /**
   * Gets the value of the isRejected property.
   *
   * @return possible object is {@link Boolean }
   */
  public boolean isIsRejected() {
    if (isRejected == null) {
      return false;
    } else {
      return isRejected;
    }
  }

  /**
   * Sets the value of the isRejected property.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setIsRejected(Boolean value) {
    this.isRejected = value;
  }

}
