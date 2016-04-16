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
package umich.ms.fileio.filetypes.pepxml.jaxb.standard;

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
 *         &lt;element name="search_database" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="local_path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="URL" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="database_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="orig_database_url" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="database_release_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                 &lt;attribute name="database_release_identifier" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="size_in_db_entries" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="size_of_residues" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="type" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;enumeration value="AA"/>
 *                       &lt;enumeration value="NA"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="enzymatic_search_constraint" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="enzyme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="max_num_internal_cleavages" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="min_number_termini" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="sequence_search_constraint" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="sequence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="aminoacid_modification" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="aminoacid" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="massdiff" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="variable" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="peptide_terminus" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="protein_terminus" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="symbol" type="{http://regis-web.systemsbiology.net/pepXML}aa_symbolType" />
 *                 &lt;attribute name="binary" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="terminal_modification" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="terminus" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="massdiff" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="variable" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="symbol" type="{http://regis-web.systemsbiology.net/pepXML}term_symbolType" />
 *                 &lt;attribute name="protein_terminus" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="base_name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="search_engine" use="required" type="{http://regis-web.systemsbiology.net/pepXML}engineType" />
 *       &lt;attribute name="search_engine_version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="precursor_mass_type" use="required" type="{http://regis-web.systemsbiology.net/pepXML}massType" />
 *       &lt;attribute name="fragment_mass_type" use="required" type="{http://regis-web.systemsbiology.net/pepXML}massType" />
 *       &lt;attribute name="out_data_type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="out_data" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="search_id" use="required" type="{http://regis-web.systemsbiology.net/pepXML}positiveInt" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "searchDatabase",
    "enzymaticSearchConstraint",
    "sequenceSearchConstraint",
    "aminoacidModification",
    "terminalModification",
    "parameter"
})
public class SearchSummary {

    @XmlElement(name = "search_database")
    protected SearchDatabase searchDatabase;
    @XmlElement(name = "enzymatic_search_constraint")
    protected EnzymaticSearchConstraint enzymaticSearchConstraint;
    @XmlElement(name = "sequence_search_constraint")
    protected List<SequenceSearchConstraint> sequenceSearchConstraint;
    @XmlElement(name = "aminoacid_modification")
    protected List<AminoacidModification> aminoacidModification;
    @XmlElement(name = "terminal_modification")
    protected List<TerminalModification> terminalModification;
    protected List<NameValueType> parameter;
    @XmlAttribute(name = "base_name", required = true)
    protected String baseName;
    @XmlAttribute(name = "search_engine", required = true)
    protected EngineType searchEngine;
    @XmlAttribute(name = "search_engine_version")
    protected String searchEngineVersion;
    @XmlAttribute(name = "precursor_mass_type", required = true)
    protected MassType precursorMassType;
    @XmlAttribute(name = "fragment_mass_type", required = true)
    protected MassType fragmentMassType;
    @XmlAttribute(name = "out_data_type")
    protected String outDataType;
    @XmlAttribute(name = "out_data")
    protected String outData;
    @XmlAttribute(name = "search_id", required = true)
    protected long searchId;

    /**
     * Gets the value of the searchDatabase property.
     *
     * @return
     *     possible object is
     *     {@link SearchDatabase }
     *
     */
    public SearchDatabase getSearchDatabase() {
        return searchDatabase;
    }

    /**
     * Sets the value of the searchDatabase property.
     *
     * @param value
     *     allowed object is
     *     {@link SearchDatabase }
     *
     */
    public void setSearchDatabase(SearchDatabase value) {
        this.searchDatabase = value;
    }

    /**
     * Gets the value of the enzymaticSearchConstraint property.
     *
     * @return
     *     possible object is
     *     {@link EnzymaticSearchConstraint }
     *
     */
    public EnzymaticSearchConstraint getEnzymaticSearchConstraint() {
        return enzymaticSearchConstraint;
    }

    /**
     * Sets the value of the enzymaticSearchConstraint property.
     *
     * @param value
     *     allowed object is
     *     {@link EnzymaticSearchConstraint }
     *
     */
    public void setEnzymaticSearchConstraint(EnzymaticSearchConstraint value) {
        this.enzymaticSearchConstraint = value;
    }

    /**
     * Gets the value of the sequenceSearchConstraint property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sequenceSearchConstraint property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSequenceSearchConstraint().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SequenceSearchConstraint }
     *
     *
     */
    public List<SequenceSearchConstraint> getSequenceSearchConstraint() {
        if (sequenceSearchConstraint == null) {
            sequenceSearchConstraint = new ArrayList<SequenceSearchConstraint>(1);
        }
        return this.sequenceSearchConstraint;
    }

    /**
     * Gets the value of the aminoacidModification property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aminoacidModification property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAminoacidModification().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AminoacidModification }
     *
     *
     */
    public List<AminoacidModification> getAminoacidModification() {
        if (aminoacidModification == null) {
            aminoacidModification = new ArrayList<AminoacidModification>(1);
        }
        return this.aminoacidModification;
    }

    /**
     * Gets the value of the terminalModification property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the terminalModification property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTerminalModification().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TerminalModification }
     *
     *
     */
    public List<TerminalModification> getTerminalModification() {
        if (terminalModification == null) {
            terminalModification = new ArrayList<TerminalModification>(1);
        }
        return this.terminalModification;
    }

    /**
     * Gets the value of the parameter property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameter property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameter().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameValueType }
     *
     *
     */
    public List<NameValueType> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<NameValueType>(1);
        }
        return this.parameter;
    }

    /**
     * Gets the value of the baseName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBaseName() {
        return baseName;
    }

    /**
     * Sets the value of the baseName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBaseName(String value) {
        this.baseName = value;
    }

    /**
     * Gets the value of the searchEngine property.
     *
     * @return
     *     possible object is
     *     {@link EngineType }
     *
     */
    public EngineType getSearchEngine() {
        return searchEngine;
    }

    /**
     * Sets the value of the searchEngine property.
     *
     * @param value
     *     allowed object is
     *     {@link EngineType }
     *
     */
    public void setSearchEngine(EngineType value) {
        this.searchEngine = value;
    }

    /**
     * Gets the value of the searchEngineVersion property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSearchEngineVersion() {
        return searchEngineVersion;
    }

    /**
     * Sets the value of the searchEngineVersion property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSearchEngineVersion(String value) {
        this.searchEngineVersion = value;
    }

    /**
     * Gets the value of the precursorMassType property.
     *
     * @return
     *     possible object is
     *     {@link MassType }
     *
     */
    public MassType getPrecursorMassType() {
        return precursorMassType;
    }

    /**
     * Sets the value of the precursorMassType property.
     *
     * @param value
     *     allowed object is
     *     {@link MassType }
     *
     */
    public void setPrecursorMassType(MassType value) {
        this.precursorMassType = value;
    }

    /**
     * Gets the value of the fragmentMassType property.
     *
     * @return
     *     possible object is
     *     {@link MassType }
     *
     */
    public MassType getFragmentMassType() {
        return fragmentMassType;
    }

    /**
     * Sets the value of the fragmentMassType property.
     *
     * @param value
     *     allowed object is
     *     {@link MassType }
     *
     */
    public void setFragmentMassType(MassType value) {
        this.fragmentMassType = value;
    }

    /**
     * Gets the value of the outDataType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOutDataType() {
        return outDataType;
    }

    /**
     * Sets the value of the outDataType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOutDataType(String value) {
        this.outDataType = value;
    }

    /**
     * Gets the value of the outData property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOutData() {
        return outData;
    }

    /**
     * Sets the value of the outData property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOutData(String value) {
        this.outData = value;
    }

    /**
     * Gets the value of the searchId property.
     *
     */
    public long getSearchId() {
        return searchId;
    }

    /**
     * Sets the value of the searchId property.
     *
     */
    public void setSearchId(long value) {
        this.searchId = value;
    }

}
