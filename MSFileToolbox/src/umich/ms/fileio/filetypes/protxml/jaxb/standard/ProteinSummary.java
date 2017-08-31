
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
 *       &lt;sequence>
 *         &lt;element name="protein_summary_header">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="program_details">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;any processContents='lax' minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="analysis" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="time" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                           &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="reference_database" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="win-cyg_reference_database" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="residue_substitution_list" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="organism" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="source_files" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="source_files_alt" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="win-cyg_source_files" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="source_file_xtn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="min_peptide_probability" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="min_peptide_weight" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="num_predicted_correct_prots" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="num_input_1_spectra" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="num_input_2_spectra" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="num_input_3_spectra" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="num_input_4_spectra" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="num_input_5_spectra" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="initial_min_peptide_prob" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="total_no_spectrum_ids" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="sample_enzyme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="analysis_summary" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="analysis" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="time" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                 &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="dataset_derivation">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="data_filter">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="number" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="parent_file" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="windows_parent" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="generation_no" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="protein_group" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="protein" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="analysis_result" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;any processContents='lax'/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="analysis" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" default="1" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/protXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                             &lt;element name="annotation" type="{http://regis-web.systemsbiology.net/protXML}protein_annotation" minOccurs="0"/>
 *                             &lt;element name="indistinguishable_protein" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/protXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                                       &lt;element name="annotation" type="{http://regis-web.systemsbiology.net/protXML}protein_annotation" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="protein_name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="peptide" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/protXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                                       &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/protXML}modification_info" maxOccurs="unbounded" minOccurs="0"/>
 *                                       &lt;element name="peptide_parent_protein" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;attribute name="protein_name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="indistinguishable_peptide" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/protXML}modification_info" maxOccurs="unbounded" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                               &lt;attribute name="peptide_sequence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="charge" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *                                               &lt;attribute name="calc_neutral_pep_mass" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="peptide_sequence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="charge" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                                     &lt;attribute name="initial_probability" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                     &lt;attribute name="nsp_adjusted_probability" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                     &lt;attribute name="fpkm_adjusted_probability" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                     &lt;attribute name="ni_adjusted_probability" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                     &lt;attribute name="exp_sibling_ion_instances" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                     &lt;attribute name="exp_sibling_ion_bin" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                     &lt;attribute name="exp_tot_instances" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                     &lt;attribute name="peptide_group_designator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="weight" type="{http://www.w3.org/2001/XMLSchema}double" default="1.0" />
 *                                     &lt;attribute name="is_nondegenerate_evidence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="n_enzymatic_termini" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                                     &lt;attribute name="n_sibling_peptides" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                     &lt;attribute name="n_sibling_peptides_bin" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *                                     &lt;attribute name="max_fpkm" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                     &lt;attribute name="fpkm_bin" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *                                     &lt;attribute name="n_instances" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                                     &lt;attribute name="calc_neutral_pep_mass" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                     &lt;attribute name="is_contributing_evidence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="protein_name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="probability" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="percent_coverage" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="n_indistinguishable_proteins" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="unique_stripped_peptides" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="group_sibling_id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="total_number_peptides" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="total_number_distinct_peptides" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="subsuming_protein_entry" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="pct_spectrum_ids" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="confidence" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="group_number" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="pseudo_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="probability" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="summary_xml" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "proteinSummaryHeader",
    "analysisSummary",
    "datasetDerivation",
    "proteinGroup"
})
@XmlRootElement(name = "protein_summary")
public class ProteinSummary {

    @XmlElement(name = "protein_summary_header", required = true)
    protected ProteinSummaryHeader proteinSummaryHeader;
    @XmlElement(name = "analysis_summary")
    protected List<AnalysisSummary> analysisSummary;
    @XmlElement(name = "dataset_derivation", required = true)
    protected DatasetDerivation datasetDerivation;
    @XmlElement(name = "protein_group", required = true)
    protected List<ProteinGroup> proteinGroup;
    @XmlAttribute(name = "summary_xml")
    protected String summaryXml;

    /**
     * Gets the value of the proteinSummaryHeader property.
     * 
     * @return
     *     possible object is
     *     {@link ProteinSummaryHeader }
     *     
     */
    public ProteinSummaryHeader getProteinSummaryHeader() {
        return proteinSummaryHeader;
    }

    /**
     * Sets the value of the proteinSummaryHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProteinSummaryHeader }
     *     
     */
    public void setProteinSummaryHeader(ProteinSummaryHeader value) {
        this.proteinSummaryHeader = value;
    }

    /**
     * Gets the value of the analysisSummary property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the analysisSummary property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnalysisSummary().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnalysisSummary }
     * 
     * 
     */
    public List<AnalysisSummary> getAnalysisSummary() {
        if (analysisSummary == null) {
            analysisSummary = new ArrayList<AnalysisSummary>(1);
        }
        return this.analysisSummary;
    }

    /**
     * Gets the value of the datasetDerivation property.
     * 
     * @return
     *     possible object is
     *     {@link DatasetDerivation }
     *     
     */
    public DatasetDerivation getDatasetDerivation() {
        return datasetDerivation;
    }

    /**
     * Sets the value of the datasetDerivation property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatasetDerivation }
     *     
     */
    public void setDatasetDerivation(DatasetDerivation value) {
        this.datasetDerivation = value;
    }

    /**
     * Gets the value of the proteinGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the proteinGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProteinGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProteinGroup }
     * 
     * 
     */
    public List<ProteinGroup> getProteinGroup() {
        if (proteinGroup == null) {
            proteinGroup = new ArrayList<ProteinGroup>(1);
        }
        return this.proteinGroup;
    }

    /**
     * Gets the value of the summaryXml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummaryXml() {
        return summaryXml;
    }

    /**
     * Sets the value of the summaryXml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummaryXml(String value) {
        this.summaryXml = value;
    }

}
