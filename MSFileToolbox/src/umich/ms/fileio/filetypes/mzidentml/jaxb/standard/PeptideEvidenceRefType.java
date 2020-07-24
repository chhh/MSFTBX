
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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Reference to the PeptideEvidence element identified. If a specific sequence can be assigned to
 * multiple proteins and or positions in a protein all possible PeptideEvidence elements should be
 * referenced here.
 *
 * <p>Java class for PeptideEvidenceRefType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PeptideEvidenceRefType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="peptideEvidence_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeptideEvidenceRefType")
public class PeptideEvidenceRefType {

  @XmlAttribute(name = "peptideEvidence_ref", required = true)
  protected String peptideEvidenceRef;

  /**
   * Gets the value of the peptideEvidenceRef property.
   *
   * @return possible object is {@link String }
   */
  public String getPeptideEvidenceRef() {
    return peptideEvidenceRef;
  }

  /**
   * Sets the value of the peptideEvidenceRef property.
   *
   * @param value allowed object is {@link String }
   */
  public void setPeptideEvidenceRef(String value) {
    this.peptideEvidenceRef = value;
  }

}
