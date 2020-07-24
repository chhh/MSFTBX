
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
 *       &lt;attribute name="decoy_string" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="decoy_ratio" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="exclude_string" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="use_confidence" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "decoy_analysis_summary")
public class DecoyAnalysisSummary {

  @XmlAttribute(name = "decoy_string", required = true)
  protected String decoyString;
  @XmlAttribute(name = "decoy_ratio", required = true)
  protected double decoyRatio;
  @XmlAttribute(name = "exclude_string")
  protected String excludeString;
  @XmlAttribute(name = "use_confidence")
  protected String useConfidence;

  /**
   * Gets the value of the decoyString property.
   *
   * @return possible object is {@link String }
   */
  public String getDecoyString() {
    return decoyString;
  }

  /**
   * Sets the value of the decoyString property.
   *
   * @param value allowed object is {@link String }
   */
  public void setDecoyString(String value) {
    this.decoyString = value;
  }

  /**
   * Gets the value of the decoyRatio property.
   */
  public double getDecoyRatio() {
    return decoyRatio;
  }

  /**
   * Sets the value of the decoyRatio property.
   */
  public void setDecoyRatio(double value) {
    this.decoyRatio = value;
  }

  /**
   * Gets the value of the excludeString property.
   *
   * @return possible object is {@link String }
   */
  public String getExcludeString() {
    return excludeString;
  }

  /**
   * Sets the value of the excludeString property.
   *
   * @param value allowed object is {@link String }
   */
  public void setExcludeString(String value) {
    this.excludeString = value;
  }

  /**
   * Gets the value of the useConfidence property.
   *
   * @return possible object is {@link String }
   */
  public String getUseConfidence() {
    return useConfidence;
  }

  /**
   * Sets the value of the useConfidence property.
   *
   * @param value allowed object is {@link String }
   */
  public void setUseConfidence(String value) {
    this.useConfidence = value;
  }

}
