
/*
 * Copyright (c) 2016 Dmitry Avtonomov
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
 *         &lt;element ref="{http://regis-web.systemsbiology.net/pepXML}asapratio_peptide_data"/>
 *       &lt;/sequence>
 *       &lt;attribute name="mean" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="error" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="heavy2light_mean" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="heavy2light_error" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "asapratioPeptideData"
})
@XmlRootElement(name = "asapratio_result")
public class AsapratioResult {

    @XmlElement(name = "asapratio_peptide_data", required = true)
    protected AsapratioPeptideData asapratioPeptideData;
    @XmlAttribute(name = "mean", required = true)
    protected float mean;
    @XmlAttribute(name = "error", required = true)
    protected float error;
    @XmlAttribute(name = "heavy2light_mean", required = true)
    protected float heavy2LightMean;
    @XmlAttribute(name = "heavy2light_error", required = true)
    protected float heavy2LightError;

    /**
     * Gets the value of the asapratioPeptideData property.
     *
     * @return
     *     possible object is
     *     {@link AsapratioPeptideData }
     *
     */
    public AsapratioPeptideData getAsapratioPeptideData() {
        return asapratioPeptideData;
    }

    /**
     * Sets the value of the asapratioPeptideData property.
     *
     * @param value
     *     allowed object is
     *     {@link AsapratioPeptideData }
     *
     */
    public void setAsapratioPeptideData(AsapratioPeptideData value) {
        this.asapratioPeptideData = value;
    }

    /**
     * Gets the value of the mean property.
     *
     */
    public float getMean() {
        return mean;
    }

    /**
     * Sets the value of the mean property.
     *
     */
    public void setMean(float value) {
        this.mean = value;
    }

    /**
     * Gets the value of the error property.
     *
     */
    public float getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     *
     */
    public void setError(float value) {
        this.error = value;
    }

    /**
     * Gets the value of the heavy2LightMean property.
     *
     */
    public float getHeavy2LightMean() {
        return heavy2LightMean;
    }

    /**
     * Sets the value of the heavy2LightMean property.
     *
     */
    public void setHeavy2LightMean(float value) {
        this.heavy2LightMean = value;
    }

    /**
     * Gets the value of the heavy2LightError property.
     *
     */
    public float getHeavy2LightError() {
        return heavy2LightError;
    }

    /**
     * Sets the value of the heavy2LightError property.
     *
     */
    public void setHeavy2LightError(float value) {
        this.heavy2LightError = value;
    }

}
