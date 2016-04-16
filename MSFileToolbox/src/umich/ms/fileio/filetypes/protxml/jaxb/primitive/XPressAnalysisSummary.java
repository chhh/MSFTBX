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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="min_peptide_probability" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="min_peptide_weight" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="min_protein_probability" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="reference_isotope" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "XPress_analysis_summary")
public class XPressAnalysisSummary {

    @XmlAttribute(name = "min_peptide_probability", required = true)
    protected double minPeptideProbability;
    @XmlAttribute(name = "min_peptide_weight", required = true)
    protected double minPeptideWeight;
    @XmlAttribute(name = "min_protein_probability", required = true)
    protected double minProteinProbability;
    @XmlAttribute(name = "reference_isotope")
    protected String referenceIsotope;

    /**
     * Gets the value of the minPeptideProbability property.
     * 
     */
    public double getMinPeptideProbability() {
        return minPeptideProbability;
    }

    /**
     * Sets the value of the minPeptideProbability property.
     * 
     */
    public void setMinPeptideProbability(double value) {
        this.minPeptideProbability = value;
    }

    /**
     * Gets the value of the minPeptideWeight property.
     * 
     */
    public double getMinPeptideWeight() {
        return minPeptideWeight;
    }

    /**
     * Sets the value of the minPeptideWeight property.
     * 
     */
    public void setMinPeptideWeight(double value) {
        this.minPeptideWeight = value;
    }

    /**
     * Gets the value of the minProteinProbability property.
     * 
     */
    public double getMinProteinProbability() {
        return minProteinProbability;
    }

    /**
     * Sets the value of the minProteinProbability property.
     * 
     */
    public void setMinProteinProbability(double value) {
        this.minProteinProbability = value;
    }

    /**
     * Gets the value of the referenceIsotope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceIsotope() {
        return referenceIsotope;
    }

    /**
     * Sets the value of the referenceIsotope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceIsotope(String value) {
        this.referenceIsotope = value;
    }

}
