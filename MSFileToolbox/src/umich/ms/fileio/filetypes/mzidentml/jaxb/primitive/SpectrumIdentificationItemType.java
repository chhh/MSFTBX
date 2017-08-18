
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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * An identification of a single (poly)peptide, resulting from querying an input spectra, along with the set of confidence values for that identification.
 * PeptideEvidence elements should be given for all mappings of the corresponding Peptide sequence within protein sequences. 
 * 
 * <p>Java class for SpectrumIdentificationItemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SpectrumIdentificationItemType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType">
 *       &lt;sequence>
 *         &lt;element name="PeptideEvidenceRef" type="{http://psidev.info/psi/pi/mzIdentML/1.2}PeptideEvidenceRefType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Fragmentation" type="{http://psidev.info/psi/pi/mzIdentML/1.2}FragmentationType" minOccurs="0"/>
 *         &lt;group ref="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamGroup" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="chargeState" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="experimentalMassToCharge" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="calculatedMassToCharge" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="calculatedPI" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="peptide_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rank" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="passThreshold" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="massTable_ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sample_ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpectrumIdentificationItemType", propOrder = {
    "peptideEvidenceRef",
    "fragmentation",
    "paramGroup"
})
public class SpectrumIdentificationItemType
    extends IdentifiableType
{

    @XmlElement(name = "PeptideEvidenceRef")
    protected List<PeptideEvidenceRefType> peptideEvidenceRef;
    @XmlElement(name = "Fragmentation")
    protected FragmentationType fragmentation;
    @XmlElements({
        @XmlElement(name = "cvParam", type = CVParamType.class),
        @XmlElement(name = "userParam", type = UserParamType.class)
    })
    protected List<AbstractParamType> paramGroup;
    @XmlAttribute(name = "chargeState", required = true)
    protected int chargeState;
    @XmlAttribute(name = "experimentalMassToCharge", required = true)
    protected double experimentalMassToCharge;
    @XmlAttribute(name = "calculatedMassToCharge")
    protected double calculatedMassToCharge;
    @XmlAttribute(name = "calculatedPI")
    protected float calculatedPI;
    @XmlAttribute(name = "peptide_ref", required = true)
    protected String peptideRef;
    @XmlAttribute(name = "rank", required = true)
    protected int rank;
    @XmlAttribute(name = "passThreshold", required = true)
    protected boolean passThreshold;
    @XmlAttribute(name = "massTable_ref")
    protected String massTableRef;
    @XmlAttribute(name = "sample_ref")
    protected String sampleRef;

    /**
     * Gets the value of the peptideEvidenceRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the peptideEvidenceRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeptideEvidenceRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeptideEvidenceRefType }
     * 
     * 
     */
    public List<PeptideEvidenceRefType> getPeptideEvidenceRef() {
        if (peptideEvidenceRef == null) {
            peptideEvidenceRef = new ArrayList<PeptideEvidenceRefType>(1);
        }
        return this.peptideEvidenceRef;
    }

    /**
     * Gets the value of the fragmentation property.
     * 
     * @return
     *     possible object is
     *     {@link FragmentationType }
     *     
     */
    public FragmentationType getFragmentation() {
        return fragmentation;
    }

    /**
     * Sets the value of the fragmentation property.
     * 
     * @param value
     *     allowed object is
     *     {@link FragmentationType }
     *     
     */
    public void setFragmentation(FragmentationType value) {
        this.fragmentation = value;
    }

    /**
     * Scores or attributes associated with the SpectrumIdentificationItem e.g. e-value, p-value, score.Gets the value of the paramGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paramGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParamGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CVParamType }
     * {@link UserParamType }
     * 
     * 
     */
    public List<AbstractParamType> getParamGroup() {
        if (paramGroup == null) {
            paramGroup = new ArrayList<AbstractParamType>(1);
        }
        return this.paramGroup;
    }

    /**
     * Gets the value of the chargeState property.
     * 
     */
    public int getChargeState() {
        return chargeState;
    }

    /**
     * Sets the value of the chargeState property.
     * 
     */
    public void setChargeState(int value) {
        this.chargeState = value;
    }

    /**
     * Gets the value of the experimentalMassToCharge property.
     * 
     */
    public double getExperimentalMassToCharge() {
        return experimentalMassToCharge;
    }

    /**
     * Sets the value of the experimentalMassToCharge property.
     * 
     */
    public void setExperimentalMassToCharge(double value) {
        this.experimentalMassToCharge = value;
    }

    /**
     * Gets the value of the calculatedMassToCharge property.
     * 
     */
    public double getCalculatedMassToCharge() {
        return calculatedMassToCharge;
    }

    /**
     * Sets the value of the calculatedMassToCharge property.
     * 
     */
    public void setCalculatedMassToCharge(double value) {
        this.calculatedMassToCharge = value;
    }

    /**
     * Gets the value of the calculatedPI property.
     * 
     */
    public float getCalculatedPI() {
        return calculatedPI;
    }

    /**
     * Sets the value of the calculatedPI property.
     * 
     */
    public void setCalculatedPI(float value) {
        this.calculatedPI = value;
    }

    /**
     * Gets the value of the peptideRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeptideRef() {
        return peptideRef;
    }

    /**
     * Sets the value of the peptideRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeptideRef(String value) {
        this.peptideRef = value;
    }

    /**
     * Gets the value of the rank property.
     * 
     */
    public int getRank() {
        return rank;
    }

    /**
     * Sets the value of the rank property.
     * 
     */
    public void setRank(int value) {
        this.rank = value;
    }

    /**
     * Gets the value of the passThreshold property.
     * 
     */
    public boolean isPassThreshold() {
        return passThreshold;
    }

    /**
     * Sets the value of the passThreshold property.
     * 
     */
    public void setPassThreshold(boolean value) {
        this.passThreshold = value;
    }

    /**
     * Gets the value of the massTableRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMassTableRef() {
        return massTableRef;
    }

    /**
     * Sets the value of the massTableRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMassTableRef(String value) {
        this.massTableRef = value;
    }

    /**
     * Gets the value of the sampleRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSampleRef() {
        return sampleRef;
    }

    /**
     * Sets the value of the sampleRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSampleRef(String value) {
        this.sampleRef = value;
    }

}
