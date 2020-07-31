
/*
 * Copyright (c) 2019 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.pepxml.jaxb.standard;

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
 *       &lt;attribute name="decoy_string" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="decoy_ratio" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="exclude_string" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="window_prob" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="uniq_iproph_peps" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="uniq_pproph_peps" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="uniq_psm" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "decoy_analysis_summary")
public class DecoyAnalysisSummary {

    @XmlAttribute(name = "decoy_string", required = true)
    protected String decoyString;
    @XmlAttribute(name = "decoy_ratio")
    protected Double decoyRatio;
    @XmlAttribute(name = "exclude_string")
    protected String excludeString;
    @XmlAttribute(name = "window_prob")
    protected String windowProb;
    @XmlAttribute(name = "uniq_iproph_peps")
    protected String uniqIprophPeps;
    @XmlAttribute(name = "uniq_pproph_peps")
    protected String uniqPprophPeps;
    @XmlAttribute(name = "uniq_psm")
    protected String uniqPsm;

    /**
     * Gets the value of the decoyString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecoyString() {
        return decoyString;
    }

    /**
     * Sets the value of the decoyString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecoyString(String value) {
        this.decoyString = value;
    }

    /**
     * Gets the value of the decoyRatio property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDecoyRatio() {
        return decoyRatio;
    }

    /**
     * Sets the value of the decoyRatio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDecoyRatio(Double value) {
        this.decoyRatio = value;
    }

    /**
     * Gets the value of the excludeString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExcludeString() {
        return excludeString;
    }

    /**
     * Sets the value of the excludeString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExcludeString(String value) {
        this.excludeString = value;
    }

    /**
     * Gets the value of the windowProb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWindowProb() {
        return windowProb;
    }

    /**
     * Sets the value of the windowProb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWindowProb(String value) {
        this.windowProb = value;
    }

    /**
     * Gets the value of the uniqIprophPeps property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniqIprophPeps() {
        return uniqIprophPeps;
    }

    /**
     * Sets the value of the uniqIprophPeps property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniqIprophPeps(String value) {
        this.uniqIprophPeps = value;
    }

    /**
     * Gets the value of the uniqPprophPeps property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniqPprophPeps() {
        return uniqPprophPeps;
    }

    /**
     * Sets the value of the uniqPprophPeps property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniqPprophPeps(String value) {
        this.uniqPprophPeps = value;
    }

    /**
     * Gets the value of the uniqPsm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniqPsm() {
        return uniqPsm;
    }

    /**
     * Sets the value of the uniqPsm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniqPsm(String value) {
        this.uniqPsm = value;
    }

}
