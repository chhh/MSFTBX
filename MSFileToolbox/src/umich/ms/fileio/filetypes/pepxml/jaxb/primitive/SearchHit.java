
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
 *         &lt;element name="alternative_protein" type="{http://regis-web.systemsbiology.net/pepXML}altProteinDataType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/pepXML}modInfoDataType" minOccurs="0"/>
 *         &lt;element name="xlink" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="linked_peptide" maxOccurs="2" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="alternative_protein" type="{http://regis-web.systemsbiology.net/pepXML}altProteinDataType" maxOccurs="unbounded" minOccurs="0"/>
 *                             &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/pepXML}modInfoDataType" minOccurs="0"/>
 *                             &lt;element name="xlink_score" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="peptide" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="peptide_prev_aa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="peptide_next_aa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="protein" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="num_tot_proteins" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *                           &lt;attribute name="calc_neutral_pep_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                           &lt;attribute name="complement_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                           &lt;attribute name="designation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="xlink_score" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="identifier" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="search_score" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="analysis_result" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;any processContents='lax' maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="analysis" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="id" type="{http://regis-web.systemsbiology.net/pepXML}positiveInt" default="1" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="hit_rank" use="required" type="{http://regis-web.systemsbiology.net/pepXML}positiveInt" />
 *       &lt;attribute name="peptide" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="peptide_prev_aa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="peptide_next_aa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="protein" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="num_tot_proteins" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="num_matched_ions" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="tot_num_ions" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="calc_neutral_pep_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="massdiff" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="num_tol_term" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="num_missed_cleavages" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="num_matched_peptides" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="xlink_type">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="na"/>
 *             &lt;enumeration value="xl"/>
 *             &lt;enumeration value="loop"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="is_rejected" default="0">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger">
 *             &lt;enumeration value="0"/>
 *             &lt;enumeration value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="protein_descr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="calc_pI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="protein_mw" type="{http://www.w3.org/2001/XMLSchema}double" />
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
    "xlink",
    "searchScore",
    "analysisResult",
    "parameter"
})
public class SearchHit {

    @XmlElement(name = "alternative_protein")
    protected List<AltProteinDataType> alternativeProtein;
    @XmlElement(name = "modification_info")
    protected ModificationInfo modificationInfo;
    protected Xlink xlink;
    @XmlElement(name = "search_score")
    protected List<NameValueType> searchScore;
    @XmlElement(name = "analysis_result")
    protected List<AnalysisResult> analysisResult;
    protected List<NameValueType> parameter;
    @XmlAttribute(name = "hit_rank", required = true)
    protected long hitRank;
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
    @XmlAttribute(name = "num_matched_ions")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer numMatchedIons;
    @XmlAttribute(name = "tot_num_ions")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer totNumIons;
    @XmlAttribute(name = "calc_neutral_pep_mass", required = true)
    protected float calcNeutralPepMass;
    @XmlAttribute(name = "massdiff", required = true)
    protected float massdiff;
    @XmlAttribute(name = "num_tol_term")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer numTolTerm;
    @XmlAttribute(name = "num_missed_cleavages")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "integer")
    protected Integer numMissedCleavages;
    @XmlAttribute(name = "num_matched_peptides")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "integer")
    protected Integer numMatchedPeptides;
    @XmlAttribute(name = "xlink_type")
    protected String xlinkType;
    @XmlAttribute(name = "is_rejected")
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Integer isRejected;
    @XmlAttribute(name = "protein_descr")
    protected String proteinDescr;
    @XmlAttribute(name = "calc_pI")
    protected String calcPI;
    @XmlAttribute(name = "protein_mw")
    protected double proteinMw;

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
     * Gets the value of the xlink property.
     * 
     * @return
     *     possible object is
     *     {@link Xlink }
     *     
     */
    public Xlink getXlink() {
        return xlink;
    }

    /**
     * Sets the value of the xlink property.
     * 
     * @param value
     *     allowed object is
     *     {@link Xlink }
     *     
     */
    public void setXlink(Xlink value) {
        this.xlink = value;
    }

    /**
     * Gets the value of the searchScore property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchScore property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchScore().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameValueType }
     * 
     * 
     */
    public List<NameValueType> getSearchScore() {
        if (searchScore == null) {
            searchScore = new ArrayList<NameValueType>(1);
        }
        return this.searchScore;
    }

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
     * Gets the value of the hitRank property.
     * 
     */
    public long getHitRank() {
        return hitRank;
    }

    /**
     * Sets the value of the hitRank property.
     * 
     */
    public void setHitRank(long value) {
        this.hitRank = value;
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
     * Gets the value of the numMatchedIons property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getNumMatchedIons() {
        return numMatchedIons;
    }

    /**
     * Sets the value of the numMatchedIons property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumMatchedIons(Integer value) {
        this.numMatchedIons = value;
    }

    /**
     * Gets the value of the totNumIons property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getTotNumIons() {
        return totNumIons;
    }

    /**
     * Sets the value of the totNumIons property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNumIons(Integer value) {
        this.totNumIons = value;
    }

    /**
     * Gets the value of the calcNeutralPepMass property.
     * 
     */
    public float getCalcNeutralPepMass() {
        return calcNeutralPepMass;
    }

    /**
     * Sets the value of the calcNeutralPepMass property.
     * 
     */
    public void setCalcNeutralPepMass(float value) {
        this.calcNeutralPepMass = value;
    }

    /**
     * Gets the value of the massdiff property.
     * 
     */
    public float getMassdiff() {
        return massdiff;
    }

    /**
     * Sets the value of the massdiff property.
     * 
     */
    public void setMassdiff(float value) {
        this.massdiff = value;
    }

    /**
     * Gets the value of the numTolTerm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getNumTolTerm() {
        return numTolTerm;
    }

    /**
     * Sets the value of the numTolTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumTolTerm(Integer value) {
        this.numTolTerm = value;
    }

    /**
     * Gets the value of the numMissedCleavages property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getNumMissedCleavages() {
        return numMissedCleavages;
    }

    /**
     * Sets the value of the numMissedCleavages property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumMissedCleavages(Integer value) {
        this.numMissedCleavages = value;
    }

    /**
     * Gets the value of the numMatchedPeptides property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getNumMatchedPeptides() {
        return numMatchedPeptides;
    }

    /**
     * Sets the value of the numMatchedPeptides property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumMatchedPeptides(Integer value) {
        this.numMatchedPeptides = value;
    }

    /**
     * Gets the value of the xlinkType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXlinkType() {
        return xlinkType;
    }

    /**
     * Sets the value of the xlinkType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXlinkType(String value) {
        this.xlinkType = value;
    }

    /**
     * Gets the value of the isRejected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public int getIsRejected() {
        if (isRejected == null) {
            return new Adapter1().unmarshal("0");
        } else {
            return isRejected;
        }
    }

    /**
     * Sets the value of the isRejected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRejected(Integer value) {
        this.isRejected = value;
    }

    /**
     * Gets the value of the proteinDescr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProteinDescr() {
        return proteinDescr;
    }

    /**
     * Sets the value of the proteinDescr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProteinDescr(String value) {
        this.proteinDescr = value;
    }

    /**
     * Gets the value of the calcPI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalcPI() {
        return calcPI;
    }

    /**
     * Sets the value of the calcPI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalcPI(String value) {
        this.calcPI = value;
    }

    /**
     * Gets the value of the proteinMw property.
     * 
     */
    public double getProteinMw() {
        return proteinMw;
    }

    /**
     * Sets the value of the proteinMw property.
     * 
     */
    public void setProteinMw(double value) {
        this.proteinMw = value;
    }

}
