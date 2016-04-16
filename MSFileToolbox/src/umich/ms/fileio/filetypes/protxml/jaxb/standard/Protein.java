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
package umich.ms.fileio.filetypes.protxml.jaxb.standard;

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
 *         &lt;element name="analysis_result" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;any processContents='lax'/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="analysis" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" default="1" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/protXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="annotation" type="{http://regis-web.systemsbiology.net/protXML}protein_annotation" minOccurs="0"/>
 *         &lt;element name="indistinguishable_protein" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/protXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="annotation" type="{http://regis-web.systemsbiology.net/protXML}protein_annotation" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="protein_name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="peptide" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/protXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/protXML}modification_info" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="peptide_parent_protein" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="protein_name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="indistinguishable_peptide" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/protXML}modification_info" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="peptide_sequence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="charge" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *                           &lt;attribute name="calc_neutral_pep_mass" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="peptide_sequence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="charge" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="initial_probability" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="nsp_adjusted_probability" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="fpkm_adjusted_probability" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="ni_adjusted_probability" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="exp_sibling_ion_instances" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="exp_sibling_ion_bin" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="exp_tot_instances" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="peptide_group_designator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="weight" type="{http://www.w3.org/2001/XMLSchema}double" default="1.0" />
 *                 &lt;attribute name="is_nondegenerate_evidence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="n_enzymatic_termini" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="n_sibling_peptides" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="n_sibling_peptides_bin" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *                 &lt;attribute name="max_fpkm" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="fpkm_bin" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *                 &lt;attribute name="n_instances" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="calc_neutral_pep_mass" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="is_contributing_evidence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="protein_name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="probability" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="percent_coverage" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="n_indistinguishable_proteins" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="unique_stripped_peptides" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="group_sibling_id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="total_number_peptides" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="total_number_distinct_peptides" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="subsuming_protein_entry" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pct_spectrum_ids" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="confidence" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "analysisResult",
    "parameter",
    "proteinAnnotation",
    "indistinguishableProtein",
    "peptide"
})
public class Protein {

    @XmlElement(name = "analysis_result")
    protected List<AnalysisResult> analysisResult;
    protected List<NameValueType> parameter;
    @XmlElement(name = "annotation")
    protected ProteinAnnotation proteinAnnotation;
    @XmlElement(name = "indistinguishable_protein")
    protected List<IndistinguishableProtein> indistinguishableProtein;
    @XmlElement(required = true)
    protected List<Peptide> peptide;
    @XmlAttribute(name = "protein_name", required = true)
    protected String proteinName;
    @XmlAttribute(name = "probability", required = true)
    protected double probability;
    @XmlAttribute(name = "percent_coverage")
    protected Double percentCoverage;
    @XmlAttribute(name = "n_indistinguishable_proteins", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "integer")
    protected Integer nIndistinguishableProteins;
    @XmlAttribute(name = "unique_stripped_peptides")
    protected String uniqueStrippedPeptides;
    @XmlAttribute(name = "group_sibling_id", required = true)
    protected String groupSiblingId;
    @XmlAttribute(name = "total_number_peptides")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "integer")
    protected Integer totalNumberPeptides;
    @XmlAttribute(name = "total_number_distinct_peptides")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "integer")
    protected Integer totalNumberDistinctPeptides;
    @XmlAttribute(name = "subsuming_protein_entry")
    protected String subsumingProteinEntry;
    @XmlAttribute(name = "pct_spectrum_ids")
    protected String pctSpectrumIds;
    @XmlAttribute(name = "confidence")
    protected Double confidence;

    /**
     * Gets the value of the analysisResult property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the analysisResult property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnalysisResult().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnalysisResult }
     *
     *
     */
    public List<AnalysisResult> getAnalysisResult() {
        if (analysisResult == null) {
            analysisResult = new ArrayList<AnalysisResult>(1);
        }
        return this.analysisResult;
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
     * Gets the value of the proteinAnnotation property.
     *
     * @return
     *     possible object is
     *     {@link ProteinAnnotation }
     *
     */
    public ProteinAnnotation getProteinAnnotation() {
        return proteinAnnotation;
    }

    /**
     * Sets the value of the proteinAnnotation property.
     *
     * @param value
     *     allowed object is
     *     {@link ProteinAnnotation }
     *
     */
    public void setProteinAnnotation(ProteinAnnotation value) {
        this.proteinAnnotation = value;
    }

    /**
     * Gets the value of the indistinguishableProtein property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the indistinguishableProtein property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIndistinguishableProtein().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IndistinguishableProtein }
     *
     *
     */
    public List<IndistinguishableProtein> getIndistinguishableProtein() {
        if (indistinguishableProtein == null) {
            indistinguishableProtein = new ArrayList<IndistinguishableProtein>(1);
        }
        return this.indistinguishableProtein;
    }

    /**
     * Gets the value of the peptide property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the peptide property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeptide().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Peptide }
     *
     *
     */
    public List<Peptide> getPeptide() {
        if (peptide == null) {
            peptide = new ArrayList<Peptide>(1);
        }
        return this.peptide;
    }

    /**
     * Gets the value of the proteinName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProteinName() {
        return proteinName;
    }

    /**
     * Sets the value of the proteinName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProteinName(String value) {
        this.proteinName = value;
    }

    /**
     * Gets the value of the probability property.
     *
     */
    public double getProbability() {
        return probability;
    }

    /**
     * Sets the value of the probability property.
     *
     */
    public void setProbability(double value) {
        this.probability = value;
    }

    /**
     * Gets the value of the percentCoverage property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getPercentCoverage() {
        return percentCoverage;
    }

    /**
     * Sets the value of the percentCoverage property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setPercentCoverage(Double value) {
        this.percentCoverage = value;
    }

    /**
     * Gets the value of the nIndistinguishableProteins property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Integer getNIndistinguishableProteins() {
        return nIndistinguishableProteins;
    }

    /**
     * Sets the value of the nIndistinguishableProteins property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNIndistinguishableProteins(Integer value) {
        this.nIndistinguishableProteins = value;
    }

    /**
     * Gets the value of the uniqueStrippedPeptides property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUniqueStrippedPeptides() {
        return uniqueStrippedPeptides;
    }

    /**
     * Sets the value of the uniqueStrippedPeptides property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUniqueStrippedPeptides(String value) {
        this.uniqueStrippedPeptides = value;
    }

    /**
     * Gets the value of the groupSiblingId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getGroupSiblingId() {
        return groupSiblingId;
    }

    /**
     * Sets the value of the groupSiblingId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setGroupSiblingId(String value) {
        this.groupSiblingId = value;
    }

    /**
     * Gets the value of the totalNumberPeptides property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Integer getTotalNumberPeptides() {
        return totalNumberPeptides;
    }

    /**
     * Sets the value of the totalNumberPeptides property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTotalNumberPeptides(Integer value) {
        this.totalNumberPeptides = value;
    }

    /**
     * Gets the value of the totalNumberDistinctPeptides property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Integer getTotalNumberDistinctPeptides() {
        return totalNumberDistinctPeptides;
    }

    /**
     * Sets the value of the totalNumberDistinctPeptides property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTotalNumberDistinctPeptides(Integer value) {
        this.totalNumberDistinctPeptides = value;
    }

    /**
     * Gets the value of the subsumingProteinEntry property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSubsumingProteinEntry() {
        return subsumingProteinEntry;
    }

    /**
     * Sets the value of the subsumingProteinEntry property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSubsumingProteinEntry(String value) {
        this.subsumingProteinEntry = value;
    }

    /**
     * Gets the value of the pctSpectrumIds property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPctSpectrumIds() {
        return pctSpectrumIds;
    }

    /**
     * Sets the value of the pctSpectrumIds property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPctSpectrumIds(String value) {
        this.pctSpectrumIds = value;
    }

    /**
     * Gets the value of the confidence property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getConfidence() {
        return confidence;
    }

    /**
     * Sets the value of the confidence property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setConfidence(Double value) {
        this.confidence = value;
    }

}
