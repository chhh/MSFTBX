
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
 *       &lt;sequence>
 *         &lt;element name="search_result" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="search_hit" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="alternative_protein" type="{http://regis-web.systemsbiology.net/pepXML}altProteinDataType" maxOccurs="unbounded" minOccurs="0"/>
 *                             &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/pepXML}modInfoDataType" minOccurs="0"/>
 *                             &lt;element name="xlink" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="linked_peptide" maxOccurs="2" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="alternative_protein" type="{http://regis-web.systemsbiology.net/pepXML}altProteinDataType" maxOccurs="unbounded" minOccurs="0"/>
 *                                                 &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/pepXML}modInfoDataType" minOccurs="0"/>
 *                                                 &lt;element name="xlink_score" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                               &lt;attribute name="peptide" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="peptide_prev_aa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="peptide_next_aa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="protein" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="num_tot_proteins" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *                                               &lt;attribute name="calc_neutral_pep_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                               &lt;attribute name="complement_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                               &lt;attribute name="designation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="xlink_score" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="identifier" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="search_score" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                             &lt;element name="analysis_result" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;any processContents='lax' maxOccurs="unbounded"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="analysis" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="id" type="{http://regis-web.systemsbiology.net/pepXML}positiveInt" default="1" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="hit_rank" use="required" type="{http://regis-web.systemsbiology.net/pepXML}positiveInt" />
 *                           &lt;attribute name="peptide" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="peptide_prev_aa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="peptide_next_aa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="protein" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="num_tot_proteins" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *                           &lt;attribute name="num_matched_ions" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                           &lt;attribute name="tot_num_ions" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                           &lt;attribute name="calc_neutral_pep_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                           &lt;attribute name="massdiff" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                           &lt;attribute name="num_tol_term" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                           &lt;attribute name="num_missed_cleavages" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="num_matched_peptides" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="xlink_type">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;enumeration value="na"/>
 *                                 &lt;enumeration value="xl"/>
 *                                 &lt;enumeration value="loop"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="is_rejected" default="0">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger">
 *                                 &lt;enumeration value="0"/>
 *                                 &lt;enumeration value="1"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="protein_descr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="calc_pI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="protein_mw" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="search_id" type="{http://regis-web.systemsbiology.net/pepXML}positiveInt" default="1" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="spectrum" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="spectrumNativeID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="start_scan" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="end_scan" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="retention_time_sec" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="collision_energy" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="compensation_voltage" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="precursor_intensity" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="activation_method" type="{http://regis-web.systemsbiology.net/pepXML}activationMethodType" />
 *       &lt;attribute name="precursor_neutral_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="assumed_charge" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="search_specification" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="index" use="required" type="{http://regis-web.systemsbiology.net/pepXML}positiveInt" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "searchResult"
})
public class SpectrumQuery {

    @XmlElement(name = "search_result")
    protected List<SearchResult> searchResult;
    @XmlAttribute(name = "spectrum", required = true)
    protected String spectrum;
    @XmlAttribute(name = "spectrumNativeID")
    protected String spectrumNativeID;
    @XmlAttribute(name = "start_scan", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long startScan;
    @XmlAttribute(name = "end_scan", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long endScan;
    @XmlAttribute(name = "retention_time_sec")
    protected float retentionTimeSec;
    @XmlAttribute(name = "collision_energy")
    protected float collisionEnergy;
    @XmlAttribute(name = "compensation_voltage")
    protected float compensationVoltage;
    @XmlAttribute(name = "precursor_intensity")
    protected float precursorIntensity;
    @XmlAttribute(name = "activation_method")
    protected ActivationMethodType activationMethod;
    @XmlAttribute(name = "precursor_neutral_mass", required = true)
    protected float precursorNeutralMass;
    @XmlAttribute(name = "assumed_charge", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer assumedCharge;
    @XmlAttribute(name = "search_specification")
    protected String searchSpecification;
    @XmlAttribute(name = "index", required = true)
    protected long index;

    /**
     * Gets the value of the searchResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchResult }
     * 
     * 
     */
    public List<SearchResult> getSearchResult() {
        if (searchResult == null) {
            searchResult = new ArrayList<SearchResult>(1);
        }
        return this.searchResult;
    }

    /**
     * Gets the value of the spectrum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpectrum() {
        return spectrum;
    }

    /**
     * Sets the value of the spectrum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpectrum(String value) {
        this.spectrum = value;
    }

    /**
     * Gets the value of the spectrumNativeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpectrumNativeID() {
        return spectrumNativeID;
    }

    /**
     * Sets the value of the spectrumNativeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpectrumNativeID(String value) {
        this.spectrumNativeID = value;
    }

    /**
     * Gets the value of the startScan property.
     * 
     */
    public long getStartScan() {
        return startScan;
    }

    /**
     * Sets the value of the startScan property.
     * 
     */
    public void setStartScan(long value) {
        this.startScan = value;
    }

    /**
     * Gets the value of the endScan property.
     * 
     */
    public long getEndScan() {
        return endScan;
    }

    /**
     * Sets the value of the endScan property.
     * 
     */
    public void setEndScan(long value) {
        this.endScan = value;
    }

    /**
     * Gets the value of the retentionTimeSec property.
     * 
     */
    public float getRetentionTimeSec() {
        return retentionTimeSec;
    }

    /**
     * Sets the value of the retentionTimeSec property.
     * 
     */
    public void setRetentionTimeSec(float value) {
        this.retentionTimeSec = value;
    }

    /**
     * Gets the value of the collisionEnergy property.
     * 
     */
    public float getCollisionEnergy() {
        return collisionEnergy;
    }

    /**
     * Sets the value of the collisionEnergy property.
     * 
     */
    public void setCollisionEnergy(float value) {
        this.collisionEnergy = value;
    }

    /**
     * Gets the value of the compensationVoltage property.
     * 
     */
    public float getCompensationVoltage() {
        return compensationVoltage;
    }

    /**
     * Sets the value of the compensationVoltage property.
     * 
     */
    public void setCompensationVoltage(float value) {
        this.compensationVoltage = value;
    }

    /**
     * Gets the value of the precursorIntensity property.
     * 
     */
    public float getPrecursorIntensity() {
        return precursorIntensity;
    }

    /**
     * Sets the value of the precursorIntensity property.
     * 
     */
    public void setPrecursorIntensity(float value) {
        this.precursorIntensity = value;
    }

    /**
     * Gets the value of the activationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link ActivationMethodType }
     *     
     */
    public ActivationMethodType getActivationMethod() {
        return activationMethod;
    }

    /**
     * Sets the value of the activationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivationMethodType }
     *     
     */
    public void setActivationMethod(ActivationMethodType value) {
        this.activationMethod = value;
    }

    /**
     * Gets the value of the precursorNeutralMass property.
     * 
     */
    public float getPrecursorNeutralMass() {
        return precursorNeutralMass;
    }

    /**
     * Sets the value of the precursorNeutralMass property.
     * 
     */
    public void setPrecursorNeutralMass(float value) {
        this.precursorNeutralMass = value;
    }

    /**
     * Gets the value of the assumedCharge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getAssumedCharge() {
        return assumedCharge;
    }

    /**
     * Sets the value of the assumedCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssumedCharge(Integer value) {
        this.assumedCharge = value;
    }

    /**
     * Gets the value of the searchSpecification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchSpecification() {
        return searchSpecification;
    }

    /**
     * Sets the value of the searchSpecification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchSpecification(String value) {
        this.searchSpecification = value;
    }

    /**
     * Gets the value of the index property.
     * 
     */
    public long getIndex() {
        return index;
    }

    /**
     * Sets the value of the index property.
     * 
     */
    public void setIndex(long value) {
        this.index = value;
    }

}
