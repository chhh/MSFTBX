
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

package umich.ms.fileio.filetypes.protxml.jaxb.primitive;

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
    protected double fdrPp;
    @XmlAttribute(name = "fdr_pp_decoy")
    protected double fdrPpDecoy;
    @XmlAttribute(name = "num_corr_pp")
    protected double numCorrPp;
    @XmlAttribute(name = "num_corr_pp_decoy")
    protected double numCorrPpDecoy;
    @XmlAttribute(name = "pp_decoy_uncert")
    protected double ppDecoyUncert;
    @XmlAttribute(name = "pp_uncert")
    protected double ppUncert;
    @XmlAttribute(name = "prob_cutoff")
    protected double probCutoff;

    /**
     * Gets the value of the fdrPp property.
     * 
     */
    public double getFdrPp() {
        return fdrPp;
    }

    /**
     * Sets the value of the fdrPp property.
     * 
     */
    public void setFdrPp(double value) {
        this.fdrPp = value;
    }

    /**
     * Gets the value of the fdrPpDecoy property.
     * 
     */
    public double getFdrPpDecoy() {
        return fdrPpDecoy;
    }

    /**
     * Sets the value of the fdrPpDecoy property.
     * 
     */
    public void setFdrPpDecoy(double value) {
        this.fdrPpDecoy = value;
    }

    /**
     * Gets the value of the numCorrPp property.
     * 
     */
    public double getNumCorrPp() {
        return numCorrPp;
    }

    /**
     * Sets the value of the numCorrPp property.
     * 
     */
    public void setNumCorrPp(double value) {
        this.numCorrPp = value;
    }

    /**
     * Gets the value of the numCorrPpDecoy property.
     * 
     */
    public double getNumCorrPpDecoy() {
        return numCorrPpDecoy;
    }

    /**
     * Sets the value of the numCorrPpDecoy property.
     * 
     */
    public void setNumCorrPpDecoy(double value) {
        this.numCorrPpDecoy = value;
    }

    /**
     * Gets the value of the ppDecoyUncert property.
     * 
     */
    public double getPpDecoyUncert() {
        return ppDecoyUncert;
    }

    /**
     * Sets the value of the ppDecoyUncert property.
     * 
     */
    public void setPpDecoyUncert(double value) {
        this.ppDecoyUncert = value;
    }

    /**
     * Gets the value of the ppUncert property.
     * 
     */
    public double getPpUncert() {
        return ppUncert;
    }

    /**
     * Sets the value of the ppUncert property.
     * 
     */
    public void setPpUncert(double value) {
        this.ppUncert = value;
    }

    /**
     * Gets the value of the probCutoff property.
     * 
     */
    public double getProbCutoff() {
        return probCutoff;
    }

    /**
     * Sets the value of the probCutoff property.
     * 
     */
    public void setProbCutoff(double value) {
        this.probCutoff = value;
    }

}
