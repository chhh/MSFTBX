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
 *         &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/protXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/protXML}modification_info" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="peptide_parent_protein" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="protein_name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="indistinguishable_peptide" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/protXML}modification_info" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="peptide_sequence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="charge" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *                 &lt;attribute name="calc_neutral_pep_mass" type="{http://www.w3.org/2001/XMLSchema}double" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="peptide_sequence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="charge" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="initial_probability" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="nsp_adjusted_probability" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="fpkm_adjusted_probability" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="ni_adjusted_probability" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="exp_sibling_ion_instances" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="exp_sibling_ion_bin" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="exp_tot_instances" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="peptide_group_designator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="weight" type="{http://www.w3.org/2001/XMLSchema}double" default="1.0" />
 *       &lt;attribute name="is_nondegenerate_evidence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="n_enzymatic_termini" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="n_sibling_peptides" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="n_sibling_peptides_bin" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="max_fpkm" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="fpkm_bin" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="n_instances" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="calc_neutral_pep_mass" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="is_contributing_evidence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "parameter",
    "modificationInfo",
    "peptideParentProtein",
    "indistinguishablePeptide"
})
public class Peptide {

    protected List<NameValueType> parameter;
    @XmlElement(name = "modification_info")
    protected List<ModificationInfo> modificationInfo;
    @XmlElement(name = "peptide_parent_protein")
    protected List<PeptideParentProtein> peptideParentProtein;
    @XmlElement(name = "indistinguishable_peptide")
    protected List<IndistinguishablePeptide> indistinguishablePeptide;
    @XmlAttribute(name = "peptide_sequence", required = true)
    protected String peptideSequence;
    @XmlAttribute(name = "charge", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer charge;
    @XmlAttribute(name = "initial_probability", required = true)
    protected double initialProbability;
    @XmlAttribute(name = "nsp_adjusted_probability")
    protected Double nspAdjustedProbability;
    @XmlAttribute(name = "fpkm_adjusted_probability")
    protected Double fpkmAdjustedProbability;
    @XmlAttribute(name = "ni_adjusted_probability")
    protected Double niAdjustedProbability;
    @XmlAttribute(name = "exp_sibling_ion_instances")
    protected Double expSiblingIonInstances;
    @XmlAttribute(name = "exp_sibling_ion_bin")
    protected Double expSiblingIonBin;
    @XmlAttribute(name = "exp_tot_instances")
    protected Double expTotInstances;
    @XmlAttribute(name = "peptide_group_designator")
    protected String peptideGroupDesignator;
    @XmlAttribute(name = "weight")
    protected Double weight;
    @XmlAttribute(name = "is_nondegenerate_evidence", required = true)
    protected String isNondegenerateEvidence;
    @XmlAttribute(name = "n_enzymatic_termini", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer nEnzymaticTermini;
    @XmlAttribute(name = "n_sibling_peptides")
    protected Double nSiblingPeptides;
    @XmlAttribute(name = "n_sibling_peptides_bin")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "integer")
    protected Integer nSiblingPeptidesBin;
    @XmlAttribute(name = "max_fpkm")
    protected Double maxFpkm;
    @XmlAttribute(name = "fpkm_bin")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "integer")
    protected Integer fpkmBin;
    @XmlAttribute(name = "n_instances", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "integer")
    protected Integer nInstances;
    @XmlAttribute(name = "calc_neutral_pep_mass")
    protected Double calcNeutralPepMass;
    @XmlAttribute(name = "is_contributing_evidence", required = true)
    protected String isContributingEvidence;

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
     * Gets the value of the modificationInfo property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the modificationInfo property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModificationInfo().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ModificationInfo }
     *
     *
     */
    public List<ModificationInfo> getModificationInfo() {
        if (modificationInfo == null) {
            modificationInfo = new ArrayList<ModificationInfo>(1);
        }
        return this.modificationInfo;
    }

    /**
     * Gets the value of the peptideParentProtein property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the peptideParentProtein property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeptideParentProtein().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeptideParentProtein }
     *
     *
     */
    public List<PeptideParentProtein> getPeptideParentProtein() {
        if (peptideParentProtein == null) {
            peptideParentProtein = new ArrayList<PeptideParentProtein>(1);
        }
        return this.peptideParentProtein;
    }

    /**
     * Gets the value of the indistinguishablePeptide property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the indistinguishablePeptide property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIndistinguishablePeptide().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IndistinguishablePeptide }
     *
     *
     */
    public List<IndistinguishablePeptide> getIndistinguishablePeptide() {
        if (indistinguishablePeptide == null) {
            indistinguishablePeptide = new ArrayList<IndistinguishablePeptide>(1);
        }
        return this.indistinguishablePeptide;
    }

    /**
     * Gets the value of the peptideSequence property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPeptideSequence() {
        return peptideSequence;
    }

    /**
     * Sets the value of the peptideSequence property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPeptideSequence(String value) {
        this.peptideSequence = value;
    }

    /**
     * Gets the value of the charge property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Integer getCharge() {
        return charge;
    }

    /**
     * Sets the value of the charge property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCharge(Integer value) {
        this.charge = value;
    }

    /**
     * Gets the value of the initialProbability property.
     *
     */
    public double getInitialProbability() {
        return initialProbability;
    }

    /**
     * Sets the value of the initialProbability property.
     *
     */
    public void setInitialProbability(double value) {
        this.initialProbability = value;
    }

    /**
     * Gets the value of the nspAdjustedProbability property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getNspAdjustedProbability() {
        return nspAdjustedProbability;
    }

    /**
     * Sets the value of the nspAdjustedProbability property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setNspAdjustedProbability(Double value) {
        this.nspAdjustedProbability = value;
    }

    /**
     * Gets the value of the fpkmAdjustedProbability property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getFpkmAdjustedProbability() {
        return fpkmAdjustedProbability;
    }

    /**
     * Sets the value of the fpkmAdjustedProbability property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setFpkmAdjustedProbability(Double value) {
        this.fpkmAdjustedProbability = value;
    }

    /**
     * Gets the value of the niAdjustedProbability property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getNiAdjustedProbability() {
        return niAdjustedProbability;
    }

    /**
     * Sets the value of the niAdjustedProbability property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setNiAdjustedProbability(Double value) {
        this.niAdjustedProbability = value;
    }

    /**
     * Gets the value of the expSiblingIonInstances property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getExpSiblingIonInstances() {
        return expSiblingIonInstances;
    }

    /**
     * Sets the value of the expSiblingIonInstances property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setExpSiblingIonInstances(Double value) {
        this.expSiblingIonInstances = value;
    }

    /**
     * Gets the value of the expSiblingIonBin property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getExpSiblingIonBin() {
        return expSiblingIonBin;
    }

    /**
     * Sets the value of the expSiblingIonBin property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setExpSiblingIonBin(Double value) {
        this.expSiblingIonBin = value;
    }

    /**
     * Gets the value of the expTotInstances property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getExpTotInstances() {
        return expTotInstances;
    }

    /**
     * Sets the value of the expTotInstances property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setExpTotInstances(Double value) {
        this.expTotInstances = value;
    }

    /**
     * Gets the value of the peptideGroupDesignator property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPeptideGroupDesignator() {
        return peptideGroupDesignator;
    }

    /**
     * Sets the value of the peptideGroupDesignator property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPeptideGroupDesignator(String value) {
        this.peptideGroupDesignator = value;
    }

    /**
     * Gets the value of the weight property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public double getWeight() {
        if (weight == null) {
            return  1.0D;
        } else {
            return weight;
        }
    }

    /**
     * Sets the value of the weight property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setWeight(Double value) {
        this.weight = value;
    }

    /**
     * Gets the value of the isNondegenerateEvidence property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIsNondegenerateEvidence() {
        return isNondegenerateEvidence;
    }

    /**
     * Sets the value of the isNondegenerateEvidence property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIsNondegenerateEvidence(String value) {
        this.isNondegenerateEvidence = value;
    }

    /**
     * Gets the value of the nEnzymaticTermini property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Integer getNEnzymaticTermini() {
        return nEnzymaticTermini;
    }

    /**
     * Sets the value of the nEnzymaticTermini property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNEnzymaticTermini(Integer value) {
        this.nEnzymaticTermini = value;
    }

    /**
     * Gets the value of the nSiblingPeptides property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getNSiblingPeptides() {
        return nSiblingPeptides;
    }

    /**
     * Sets the value of the nSiblingPeptides property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setNSiblingPeptides(Double value) {
        this.nSiblingPeptides = value;
    }

    /**
     * Gets the value of the nSiblingPeptidesBin property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public int getNSiblingPeptidesBin() {
        if (nSiblingPeptidesBin == null) {
            return new Adapter1().unmarshal("0");
        } else {
            return nSiblingPeptidesBin;
        }
    }

    /**
     * Sets the value of the nSiblingPeptidesBin property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNSiblingPeptidesBin(Integer value) {
        this.nSiblingPeptidesBin = value;
    }

    /**
     * Gets the value of the maxFpkm property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getMaxFpkm() {
        return maxFpkm;
    }

    /**
     * Sets the value of the maxFpkm property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setMaxFpkm(Double value) {
        this.maxFpkm = value;
    }

    /**
     * Gets the value of the fpkmBin property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public int getFpkmBin() {
        if (fpkmBin == null) {
            return new Adapter1().unmarshal("0");
        } else {
            return fpkmBin;
        }
    }

    /**
     * Sets the value of the fpkmBin property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFpkmBin(Integer value) {
        this.fpkmBin = value;
    }

    /**
     * Gets the value of the nInstances property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Integer getNInstances() {
        return nInstances;
    }

    /**
     * Sets the value of the nInstances property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNInstances(Integer value) {
        this.nInstances = value;
    }

    /**
     * Gets the value of the calcNeutralPepMass property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getCalcNeutralPepMass() {
        return calcNeutralPepMass;
    }

    /**
     * Sets the value of the calcNeutralPepMass property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setCalcNeutralPepMass(Double value) {
        this.calcNeutralPepMass = value;
    }

    /**
     * Gets the value of the isContributingEvidence property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIsContributingEvidence() {
        return isContributingEvidence;
    }

    /**
     * Sets the value of the isContributingEvidence property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIsContributingEvidence(String value) {
        this.isContributingEvidence = value;
    }

}
