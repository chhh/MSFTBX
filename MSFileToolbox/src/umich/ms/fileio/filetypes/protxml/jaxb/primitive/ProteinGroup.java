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
package umich.ms.fileio.filetypes.protxml.jaxb.primitive;

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
 *         &lt;element name="protein" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="analysis_result" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;any processContents='lax'/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="analysis" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" default="1" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/protXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="annotation" type="{http://regis-web.systemsbiology.net/protXML}protein_annotation" minOccurs="0"/>
 *                   &lt;element name="indistinguishable_protein" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/protXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                             &lt;element name="annotation" type="{http://regis-web.systemsbiology.net/protXML}protein_annotation" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="protein_name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="peptide" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/protXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                             &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/protXML}modification_info" maxOccurs="unbounded" minOccurs="0"/>
 *                             &lt;element name="peptide_parent_protein" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="protein_name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="indistinguishable_peptide" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/protXML}modification_info" maxOccurs="unbounded" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="peptide_sequence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="charge" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *                                     &lt;attribute name="calc_neutral_pep_mass" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="peptide_sequence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="charge" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                           &lt;attribute name="initial_probability" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="nsp_adjusted_probability" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="fpkm_adjusted_probability" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="ni_adjusted_probability" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="exp_sibling_ion_instances" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="exp_sibling_ion_bin" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="exp_tot_instances" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="peptide_group_designator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="weight" type="{http://www.w3.org/2001/XMLSchema}double" default="1.0" />
 *                           &lt;attribute name="is_nondegenerate_evidence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="n_enzymatic_termini" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                           &lt;attribute name="n_sibling_peptides" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="n_sibling_peptides_bin" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *                           &lt;attribute name="max_fpkm" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="fpkm_bin" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *                           &lt;attribute name="n_instances" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="calc_neutral_pep_mass" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="is_contributing_evidence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="protein_name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="probability" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="percent_coverage" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="n_indistinguishable_proteins" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="unique_stripped_peptides" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="group_sibling_id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="total_number_peptides" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="total_number_distinct_peptides" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="subsuming_protein_entry" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="pct_spectrum_ids" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="confidence" type="{http://www.w3.org/2001/XMLSchema}double" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="group_number" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pseudo_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="probability" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "protein"
})
public class ProteinGroup {

    @XmlElement(required = true)
    protected List<Protein> protein;
    @XmlAttribute(name = "group_number", required = true)
    protected String groupNumber;
    @XmlAttribute(name = "pseudo_name")
    protected String pseudoName;
    @XmlAttribute(name = "probability", required = true)
    protected double probability;

    /**
     * Gets the value of the protein property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the protein property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProtein().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Protein }
     *
     *
     */
    public List<Protein> getProtein() {
        if (protein == null) {
            protein = new ArrayList<Protein>(1);
        }
        return this.protein;
    }

    /**
     * Gets the value of the groupNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getGroupNumber() {
        return groupNumber;
    }

    /**
     * Sets the value of the groupNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setGroupNumber(String value) {
        this.groupNumber = value;
    }

    /**
     * Gets the value of the pseudoName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPseudoName() {
        return pseudoName;
    }

    /**
     * Sets the value of the pseudoName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPseudoName(String value) {
        this.pseudoName = value;
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

}
