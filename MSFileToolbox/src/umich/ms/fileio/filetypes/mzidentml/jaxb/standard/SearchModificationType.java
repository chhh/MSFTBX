
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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Specification of a search modification as parameter for a spectra search. Contains the name of
 * the modification, the mass, the specificity and whether it is a static modification.
 *
 * <p>Java class for SearchModificationType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SearchModificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SpecificityRules" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SpecificityRulesType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cvParam" type="{http://psidev.info/psi/pi/mzIdentML/1.2}CVParamType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="fixedMod" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="massDelta" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="residues" use="required" type="{http://psidev.info/psi/pi/mzIdentML/1.2}listOfCharsOrAny" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchModificationType", propOrder = {
    "specificityRules",
    "cvParam"
})
public class SearchModificationType {

  @XmlElement(name = "SpecificityRules")
  protected List<SpecificityRulesType> specificityRules;
  @XmlElement(required = true)
  protected List<CVParamType> cvParam;
  @XmlAttribute(name = "fixedMod", required = true)
  protected boolean fixedMod;
  @XmlAttribute(name = "massDelta", required = true)
  protected double massDelta;
  @XmlAttribute(name = "residues", required = true)
  protected List<String> residues;

  /**
   * Gets the value of the specificityRules property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the specificityRules property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getSpecificityRules().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link SpecificityRulesType }
   */
  public List<SpecificityRulesType> getSpecificityRules() {
    if (specificityRules == null) {
      specificityRules = new ArrayList<SpecificityRulesType>(1);
    }
    return this.specificityRules;
  }

  /**
   * Gets the value of the cvParam property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the cvParam property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getCvParam().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link CVParamType }
   */
  public List<CVParamType> getCvParam() {
    if (cvParam == null) {
      cvParam = new ArrayList<CVParamType>(1);
    }
    return this.cvParam;
  }

  /**
   * Gets the value of the fixedMod property.
   */
  public boolean isFixedMod() {
    return fixedMod;
  }

  /**
   * Sets the value of the fixedMod property.
   */
  public void setFixedMod(boolean value) {
    this.fixedMod = value;
  }

  /**
   * Gets the value of the massDelta property.
   */
  public double getMassDelta() {
    return massDelta;
  }

  /**
   * Sets the value of the massDelta property.
   */
  public void setMassDelta(double value) {
    this.massDelta = value;
  }

  /**
   * Gets the value of the residues property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the residues property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getResidues().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link String }
   */
  public List<String> getResidues() {
    if (residues == null) {
      residues = new ArrayList<String>(1);
    }
    return this.residues;
  }

}
