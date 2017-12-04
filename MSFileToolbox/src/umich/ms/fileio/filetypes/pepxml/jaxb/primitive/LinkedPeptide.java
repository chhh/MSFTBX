
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

package umich.ms.fileio.filetypes.pepxml.jaxb.primitive;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="alternative_protein" type="{http://regis-web.systemsbiology.net/pepXML}altProteinDataType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/pepXML}modInfoDataType" minOccurs="0"/>
 *         &lt;element name="xlink_score" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="peptide" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="peptide_prev_aa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="peptide_next_aa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="protein" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="num_tot_proteins" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="calc_neutral_pep_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="complement_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="designation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "alternativeProtein",
    "modificationInfo",
    "xlinkScore"
})
public class LinkedPeptide {

    @XmlElement(name = "alternative_protein")
    protected List<AltProteinDataType> alternativeProtein;
    @XmlElement(name = "modification_info")
    protected ModificationInfo modificationInfo;
    @XmlElement(name = "xlink_score")
    protected List<NameValueType> xlinkScore;
    @XmlAttribute(name = "peptide", required = true)
    protected String peptide;
    @XmlAttribute(name = "peptide_prev_aa")
    protected String peptidePrevAa;
    @XmlAttribute(name = "peptide_next_aa")
    protected String peptideNextAa;
    @XmlAttribute(name = "protein", required = true)
    protected String protein;
    @XmlAttribute(name = "num_tot_proteins", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long numTotProteins;
    @XmlAttribute(name = "calc_neutral_pep_mass", required = true)
    protected double calcNeutralPepMass;
    @XmlAttribute(name = "complement_mass", required = true)
    protected double complementMass;
    @XmlAttribute(name = "designation")
    protected String designation;

    /**
     * Gets the value of the alternativeProtein property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alternativeProtein property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlternativeProtein().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AltProteinDataType }
     * 
     * 
     */
    public List<AltProteinDataType> getAlternativeProtein() {
        if (alternativeProtein == null) {
            alternativeProtein = new ArrayList<AltProteinDataType>(1);
        }
        return this.alternativeProtein;
    }

    /**
     * Gets the value of the modificationInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ModificationInfo }
     *     
     */
    public ModificationInfo getModificationInfo() {
        return modificationInfo;
    }

    /**
     * Sets the value of the modificationInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModificationInfo }
     *     
     */
    public void setModificationInfo(ModificationInfo value) {
        this.modificationInfo = value;
    }

    /**
     * Gets the value of the xlinkScore property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the xlinkScore property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getXlinkScore().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameValueType }
     * 
     * 
     */
    public List<NameValueType> getXlinkScore() {
        if (xlinkScore == null) {
            xlinkScore = new ArrayList<NameValueType>(1);
        }
        return this.xlinkScore;
    }

    /**
     * Gets the value of the peptide property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeptide() {
        return peptide;
    }

    /**
     * Sets the value of the peptide property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeptide(String value) {
        this.peptide = value;
    }

    /**
     * Gets the value of the peptidePrevAa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeptidePrevAa() {
        return peptidePrevAa;
    }

    /**
     * Sets the value of the peptidePrevAa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeptidePrevAa(String value) {
        this.peptidePrevAa = value;
    }

    /**
     * Gets the value of the peptideNextAa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeptideNextAa() {
        return peptideNextAa;
    }

    /**
     * Sets the value of the peptideNextAa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeptideNextAa(String value) {
        this.peptideNextAa = value;
    }

    /**
     * Gets the value of the protein property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtein() {
        return protein;
    }

    /**
     * Sets the value of the protein property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtein(String value) {
        this.protein = value;
    }

    /**
     * Gets the value of the numTotProteins property.
     * 
     */
    public long getNumTotProteins() {
        return numTotProteins;
    }

    /**
     * Sets the value of the numTotProteins property.
     * 
     */
    public void setNumTotProteins(long value) {
        this.numTotProteins = value;
    }

    /**
     * Gets the value of the calcNeutralPepMass property.
     * 
     */
    public double getCalcNeutralPepMass() {
        return calcNeutralPepMass;
    }

    /**
     * Sets the value of the calcNeutralPepMass property.
     * 
     */
    public void setCalcNeutralPepMass(double value) {
        this.calcNeutralPepMass = value;
    }

    /**
     * Gets the value of the complementMass property.
     * 
     */
    public double getComplementMass() {
        return complementMass;
    }

    /**
     * Sets the value of the complementMass property.
     * 
     */
    public void setComplementMass(double value) {
        this.complementMass = value;
    }

    /**
     * Gets the value of the designation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets the value of the designation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesignation(String value) {
        this.designation = value;
    }

}
