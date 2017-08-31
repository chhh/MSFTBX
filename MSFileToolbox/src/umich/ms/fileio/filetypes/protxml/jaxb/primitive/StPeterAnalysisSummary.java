
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
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="probability" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="FDR" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="tolerance" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="degenerate_peptides" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sampleLoad" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "StPeter_analysis_summary")
public class StPeterAnalysisSummary {

    @XmlAttribute(name = "version", required = true)
    protected String version;
    @XmlAttribute(name = "probability", required = true)
    protected double probability;
    @XmlAttribute(name = "FDR", required = true)
    protected double fdr;
    @XmlAttribute(name = "tolerance", required = true)
    protected double tolerance;
    @XmlAttribute(name = "degenerate_peptides")
    protected String degeneratePeptides;
    @XmlAttribute(name = "sampleLoad")
    protected double sampleLoad;

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
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
     * Gets the value of the fdr property.
     * 
     */
    public double getFDR() {
        return fdr;
    }

    /**
     * Sets the value of the fdr property.
     * 
     */
    public void setFDR(double value) {
        this.fdr = value;
    }

    /**
     * Gets the value of the tolerance property.
     * 
     */
    public double getTolerance() {
        return tolerance;
    }

    /**
     * Sets the value of the tolerance property.
     * 
     */
    public void setTolerance(double value) {
        this.tolerance = value;
    }

    /**
     * Gets the value of the degeneratePeptides property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDegeneratePeptides() {
        return degeneratePeptides;
    }

    /**
     * Sets the value of the degeneratePeptides property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDegeneratePeptides(String value) {
        this.degeneratePeptides = value;
    }

    /**
     * Gets the value of the sampleLoad property.
     * 
     */
    public double getSampleLoad() {
        return sampleLoad;
    }

    /**
     * Sets the value of the sampleLoad property.
     * 
     */
    public void setSampleLoad(double value) {
        this.sampleLoad = value;
    }

}
