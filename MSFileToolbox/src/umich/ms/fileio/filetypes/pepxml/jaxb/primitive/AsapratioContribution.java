
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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element ref="{http://regis-web.systemsbiology.net/pepXML}asapratio_lc_lightpeak"/>
 *         &lt;element ref="{http://regis-web.systemsbiology.net/pepXML}asapratio_lc_heavypeak"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ratio" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="error" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="charge" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="use" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "asapratioLcLightpeak",
    "asapratioLcHeavypeak"
})
@XmlRootElement(name = "asapratio_contribution")
public class AsapratioContribution {

    @XmlElement(name = "asapratio_lc_lightpeak", required = true)
    protected AsapratioLcLightpeak asapratioLcLightpeak;
    @XmlElement(name = "asapratio_lc_heavypeak", required = true)
    protected AsapratioLcHeavypeak asapratioLcHeavypeak;
    @XmlAttribute(name = "ratio", required = true)
    protected float ratio;
    @XmlAttribute(name = "error", required = true)
    protected float error;
    @XmlAttribute(name = "charge", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer charge;
    @XmlAttribute(name = "use", required = true)
    @XmlSchemaType(name = "unsignedByte")
    protected short use;

    /**
     * Gets the value of the asapratioLcLightpeak property.
     * 
     * @return
     *     possible object is
     *     {@link AsapratioLcLightpeak }
     *     
     */
    public AsapratioLcLightpeak getAsapratioLcLightpeak() {
        return asapratioLcLightpeak;
    }

    /**
     * Sets the value of the asapratioLcLightpeak property.
     * 
     * @param value
     *     allowed object is
     *     {@link AsapratioLcLightpeak }
     *     
     */
    public void setAsapratioLcLightpeak(AsapratioLcLightpeak value) {
        this.asapratioLcLightpeak = value;
    }

    /**
     * Gets the value of the asapratioLcHeavypeak property.
     * 
     * @return
     *     possible object is
     *     {@link AsapratioLcHeavypeak }
     *     
     */
    public AsapratioLcHeavypeak getAsapratioLcHeavypeak() {
        return asapratioLcHeavypeak;
    }

    /**
     * Sets the value of the asapratioLcHeavypeak property.
     * 
     * @param value
     *     allowed object is
     *     {@link AsapratioLcHeavypeak }
     *     
     */
    public void setAsapratioLcHeavypeak(AsapratioLcHeavypeak value) {
        this.asapratioLcHeavypeak = value;
    }

    /**
     * Gets the value of the ratio property.
     * 
     */
    public float getRatio() {
        return ratio;
    }

    /**
     * Sets the value of the ratio property.
     * 
     */
    public void setRatio(float value) {
        this.ratio = value;
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
     * Gets the value of the use property.
     * 
     */
    public short getUse() {
        return use;
    }

    /**
     * Sets the value of the use property.
     * 
     */
    public void setUse(short value) {
        this.use = value;
    }

}
