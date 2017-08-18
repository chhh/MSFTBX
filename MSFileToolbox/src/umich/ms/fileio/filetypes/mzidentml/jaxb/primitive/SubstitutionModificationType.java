
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

package umich.ms.fileio.filetypes.mzidentml.jaxb.primitive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * A modification where one residue is substituted by another (amino acid change). 
 * 
 * <p>Java class for SubstitutionModificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubstitutionModificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="originalResidue" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="[ABCDEFGHIJKLMNOPQRSTUVWXYZ?\-]{1}"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="replacementResidue" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="[ABCDEFGHIJKLMNOPQRSTUVWXYZ?\-]{1}"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="location" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="avgMassDelta" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="monoisotopicMassDelta" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubstitutionModificationType")
public class SubstitutionModificationType {

    @XmlAttribute(name = "originalResidue", required = true)
    protected String originalResidue;
    @XmlAttribute(name = "replacementResidue", required = true)
    protected String replacementResidue;
    @XmlAttribute(name = "location")
    protected int location;
    @XmlAttribute(name = "avgMassDelta")
    protected double avgMassDelta;
    @XmlAttribute(name = "monoisotopicMassDelta")
    protected double monoisotopicMassDelta;

    /**
     * Gets the value of the originalResidue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalResidue() {
        return originalResidue;
    }

    /**
     * Sets the value of the originalResidue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalResidue(String value) {
        this.originalResidue = value;
    }

    /**
     * Gets the value of the replacementResidue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplacementResidue() {
        return replacementResidue;
    }

    /**
     * Sets the value of the replacementResidue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplacementResidue(String value) {
        this.replacementResidue = value;
    }

    /**
     * Gets the value of the location property.
     * 
     */
    public int getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     */
    public void setLocation(int value) {
        this.location = value;
    }

    /**
     * Gets the value of the avgMassDelta property.
     * 
     */
    public double getAvgMassDelta() {
        return avgMassDelta;
    }

    /**
     * Sets the value of the avgMassDelta property.
     * 
     */
    public void setAvgMassDelta(double value) {
        this.avgMassDelta = value;
    }

    /**
     * Gets the value of the monoisotopicMassDelta property.
     * 
     */
    public double getMonoisotopicMassDelta() {
        return monoisotopicMassDelta;
    }

    /**
     * Sets the value of the monoisotopicMassDelta property.
     * 
     */
    public void setMonoisotopicMassDelta(double value) {
        this.monoisotopicMassDelta = value;
    }

}
