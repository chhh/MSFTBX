
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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="fdr_pp" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="fdr_pp_decoy" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="num_corr_pp" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="num_corr_pp_decoy" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="pp_decoy_uncert" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="pp_uncert" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="prob_cutoff" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Point {

    @XmlAttribute(name = "fdr_pp")
    protected Double fdrPp;
    @XmlAttribute(name = "fdr_pp_decoy")
    protected Double fdrPpDecoy;
    @XmlAttribute(name = "num_corr_pp")
    protected Double numCorrPp;
    @XmlAttribute(name = "num_corr_pp_decoy")
    protected Double numCorrPpDecoy;
    @XmlAttribute(name = "pp_decoy_uncert")
    protected Double ppDecoyUncert;
    @XmlAttribute(name = "pp_uncert")
    protected Double ppUncert;
    @XmlAttribute(name = "prob_cutoff")
    protected Double probCutoff;

    /**
     * Gets the value of the fdrPp property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFdrPp() {
        return fdrPp;
    }

    /**
     * Sets the value of the fdrPp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFdrPp(Double value) {
        this.fdrPp = value;
    }

    /**
     * Gets the value of the fdrPpDecoy property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFdrPpDecoy() {
        return fdrPpDecoy;
    }

    /**
     * Sets the value of the fdrPpDecoy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFdrPpDecoy(Double value) {
        this.fdrPpDecoy = value;
    }

    /**
     * Gets the value of the numCorrPp property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getNumCorrPp() {
        return numCorrPp;
    }

    /**
     * Sets the value of the numCorrPp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setNumCorrPp(Double value) {
        this.numCorrPp = value;
    }

    /**
     * Gets the value of the numCorrPpDecoy property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getNumCorrPpDecoy() {
        return numCorrPpDecoy;
    }

    /**
     * Sets the value of the numCorrPpDecoy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setNumCorrPpDecoy(Double value) {
        this.numCorrPpDecoy = value;
    }

    /**
     * Gets the value of the ppDecoyUncert property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPpDecoyUncert() {
        return ppDecoyUncert;
    }

    /**
     * Sets the value of the ppDecoyUncert property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPpDecoyUncert(Double value) {
        this.ppDecoyUncert = value;
    }

    /**
     * Gets the value of the ppUncert property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPpUncert() {
        return ppUncert;
    }

    /**
     * Sets the value of the ppUncert property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPpUncert(Double value) {
        this.ppUncert = value;
    }

    /**
     * Gets the value of the probCutoff property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getProbCutoff() {
        return probCutoff;
    }

    /**
     * Sets the value of the probCutoff property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setProbCutoff(Double value) {
        this.probCutoff = value;
    }

}
