
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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="mod_aminoacid_mass" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="position" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="mass" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="mod_nterm_mass" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mod_cterm_mass" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="modified_peptide" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "modAminoacidMass"
})
public class Mod {

  @XmlElement(name = "mod_aminoacid_mass")
  protected List<ModAminoacidMass> modAminoacidMass;
  @XmlAttribute(name = "mod_nterm_mass")
  protected String modNtermMass;
  @XmlAttribute(name = "mod_cterm_mass")
  protected String modCtermMass;
  @XmlAttribute(name = "modified_peptide")
  protected String modifiedPeptide;

  /**
   * Gets the value of the modAminoacidMass property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the modAminoacidMass property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getModAminoacidMass().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link ModAminoacidMass }
   */
  public List<ModAminoacidMass> getModAminoacidMass() {
    if (modAminoacidMass == null) {
      modAminoacidMass = new ArrayList<ModAminoacidMass>(1);
    }
    return this.modAminoacidMass;
  }

  /**
   * Gets the value of the modNtermMass property.
   *
   * @return possible object is {@link String }
   */
  public String getModNtermMass() {
    return modNtermMass;
  }

  /**
   * Sets the value of the modNtermMass property.
   *
   * @param value allowed object is {@link String }
   */
  public void setModNtermMass(String value) {
    this.modNtermMass = value;
  }

  /**
   * Gets the value of the modCtermMass property.
   *
   * @return possible object is {@link String }
   */
  public String getModCtermMass() {
    return modCtermMass;
  }

  /**
   * Sets the value of the modCtermMass property.
   *
   * @param value allowed object is {@link String }
   */
  public void setModCtermMass(String value) {
    this.modCtermMass = value;
  }

  /**
   * Gets the value of the modifiedPeptide property.
   *
   * @return possible object is {@link String }
   */
  public String getModifiedPeptide() {
    return modifiedPeptide;
  }

  /**
   * Sets the value of the modifiedPeptide property.
   *
   * @param value allowed object is {@link String }
   */
  public void setModifiedPeptide(String value) {
    this.modifiedPeptide = value;
  }

}
