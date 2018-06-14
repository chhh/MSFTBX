
/*
 * Copyright (c) 2018 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.agilent.cef.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
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
 *         &lt;element ref="{}MSDetails"/>
 *         &lt;element ref="{}RTRanges" minOccurs="0"/>
 *         &lt;element ref="{}Device"/>
 *         &lt;element ref="{}MSPeaks"/>
 *       &lt;/sequence>
 *       &lt;attribute name="cpdAlgo" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="satLimit" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "msDetails",
    "rtRanges",
    "device",
    "msPeaks"
})
@XmlRootElement(name = "Spectrum")
public class Spectrum {

    @XmlElement(name = "MSDetails", required = true)
    protected MSDetails msDetails;
    @XmlElement(name = "RTRanges")
    protected RTRanges rtRanges;
    @XmlElement(name = "Device", required = true)
    protected Device device;
    @XmlElement(name = "MSPeaks", required = true)
    protected MSPeaks msPeaks;
    @XmlAttribute(name = "cpdAlgo", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String cpdAlgo;
    @XmlAttribute(name = "satLimit")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "integer")
    protected Integer satLimit;
    @XmlAttribute(name = "type", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String type;

    /**
     * Gets the value of the msDetails property.
     * 
     * @return
     *     possible object is
     *     {@link MSDetails }
     *     
     */
    public MSDetails getMSDetails() {
        return msDetails;
    }

    /**
     * Sets the value of the msDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link MSDetails }
     *     
     */
    public void setMSDetails(MSDetails value) {
        this.msDetails = value;
    }

    /**
     * Gets the value of the rtRanges property.
     * 
     * @return
     *     possible object is
     *     {@link RTRanges }
     *     
     */
    public RTRanges getRTRanges() {
        return rtRanges;
    }

    /**
     * Sets the value of the rtRanges property.
     * 
     * @param value
     *     allowed object is
     *     {@link RTRanges }
     *     
     */
    public void setRTRanges(RTRanges value) {
        this.rtRanges = value;
    }

    /**
     * Gets the value of the device property.
     * 
     * @return
     *     possible object is
     *     {@link Device }
     *     
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Sets the value of the device property.
     * 
     * @param value
     *     allowed object is
     *     {@link Device }
     *     
     */
    public void setDevice(Device value) {
        this.device = value;
    }

    /**
     * Gets the value of the msPeaks property.
     * 
     * @return
     *     possible object is
     *     {@link MSPeaks }
     *     
     */
    public MSPeaks getMSPeaks() {
        return msPeaks;
    }

    /**
     * Sets the value of the msPeaks property.
     * 
     * @param value
     *     allowed object is
     *     {@link MSPeaks }
     *     
     */
    public void setMSPeaks(MSPeaks value) {
        this.msPeaks = value;
    }

    /**
     * Gets the value of the cpdAlgo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpdAlgo() {
        return cpdAlgo;
    }

    /**
     * Sets the value of the cpdAlgo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpdAlgo(String value) {
        this.cpdAlgo = value;
    }

    /**
     * Gets the value of the satLimit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getSatLimit() {
        return satLimit;
    }

    /**
     * Sets the value of the satLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSatLimit(Integer value) {
        this.satLimit = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
