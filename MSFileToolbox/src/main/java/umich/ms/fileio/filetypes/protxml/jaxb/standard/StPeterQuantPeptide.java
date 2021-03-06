
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

package umich.ms.fileio.filetypes.protxml.jaxb.standard;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="sequence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="charge" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="spectralIndex" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class StPeterQuantPeptide {

  @XmlAttribute(name = "sequence", required = true)
  protected String sequence;
  @XmlAttribute(name = "charge", required = true)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name = "nonNegativeInteger")
  protected Integer charge;
  @XmlAttribute(name = "spectralIndex", required = true)
  protected double spectralIndex;

  /**
   * Gets the value of the sequence property.
   *
   * @return possible object is {@link String }
   */
  public String getSequence() {
    return sequence;
  }

  /**
   * Sets the value of the sequence property.
   *
   * @param value allowed object is {@link String }
   */
  public void setSequence(String value) {
    this.sequence = value;
  }

  /**
   * Gets the value of the charge property.
   *
   * @return possible object is {@link String }
   */
  public Integer getCharge() {
    return charge;
  }

  /**
   * Sets the value of the charge property.
   *
   * @param value allowed object is {@link String }
   */
  public void setCharge(Integer value) {
    this.charge = value;
  }

  /**
   * Gets the value of the spectralIndex property.
   */
  public double getSpectralIndex() {
    return spectralIndex;
  }

  /**
   * Sets the value of the spectralIndex property.
   */
  public void setSpectralIndex(double value) {
    this.spectralIndex = value;
  }

}
