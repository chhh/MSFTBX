/*
 * Copyright 2016 Dmitry Avtonomov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package umich.ms.fileio.filetypes.mzxml.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ontologyEntryType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ontologyEntryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="category" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ontologyEntryType")
@XmlSeeAlso({
    umich.ms.fileio.filetypes.mzxml.jaxb.MsRun.MsInstrument.MsManufacturer.class,
    umich.ms.fileio.filetypes.mzxml.jaxb.MsRun.MsInstrument.MsMassAnalyzer.class
})
public class OntologyEntryType {

  @XmlAttribute(name = "category", required = true)
  protected String category;
  @XmlAttribute(name = "value", required = true)
  protected String valueOntologyEntryType;

  /**
   * Gets the value of the category property.
   *
   * @return possible object is {@link String }
   */
  public String getCategory() {
    return category;
  }

  /**
   * Sets the value of the category property.
   *
   * @param value allowed object is {@link String }
   */
  public void setCategory(String value) {
    this.category = value;
  }

  /**
   * Gets the value of the valueOntologyEntryType property.
   *
   * @return possible object is {@link String }
   */
  public String getValueOntologyEntryType() {
    return valueOntologyEntryType;
  }

  /**
   * Sets the value of the valueOntologyEntryType property.
   *
   * @param value allowed object is {@link String }
   */
  public void setValueOntologyEntryType(String value) {
    this.valueOntologyEntryType = value;
  }

}
