
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
 *         &lt;element name="ASAP_Peak" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ASAP_Dta" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="peptide_index" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="include" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="status" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="include" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="datanum" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="ratio_mean" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="ratio_standard_dev" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="heavy2light_ratio_mean" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="heavy2light_ratio_standard_dev" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="weight" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="peptide_binary_ind" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="status" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="include" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="datanum" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="ratio_mean" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="ratio_standard_dev" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="heavy2light_ratio_mean" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="heavy2light_ratio_standard_dev" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="weight" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="light_sequence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "asapPeak"
})
public class ASAPSeq {

    @XmlElement(name = "ASAP_Peak", required = true)
    protected List<ASAPPeak> asapPeak;
    @XmlAttribute(name = "status", required = true)
    protected String status;
    @XmlAttribute(name = "include", required = true)
    protected String include;
    @XmlAttribute(name = "datanum", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer datanum;
    @XmlAttribute(name = "ratio_mean", required = true)
    protected double ratioMean;
    @XmlAttribute(name = "ratio_standard_dev", required = true)
    protected double ratioStandardDev;
    @XmlAttribute(name = "heavy2light_ratio_mean", required = true)
    protected double heavy2LightRatioMean;
    @XmlAttribute(name = "heavy2light_ratio_standard_dev", required = true)
    protected double heavy2LightRatioStandardDev;
    @XmlAttribute(name = "weight", required = true)
    protected double weight;
    @XmlAttribute(name = "light_sequence", required = true)
    protected String lightSequence;

    /**
     * Gets the value of the asapPeak property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the asapPeak property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getASAPPeak().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ASAPPeak }
     * 
     * 
     */
    public List<ASAPPeak> getASAPPeak() {
        if (asapPeak == null) {
            asapPeak = new ArrayList<ASAPPeak>(1);
        }
        return this.asapPeak;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the include property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInclude() {
        return include;
    }

    /**
     * Sets the value of the include property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInclude(String value) {
        this.include = value;
    }

    /**
     * Gets the value of the datanum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getDatanum() {
        return datanum;
    }

    /**
     * Sets the value of the datanum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatanum(Integer value) {
        this.datanum = value;
    }

    /**
     * Gets the value of the ratioMean property.
     * 
     */
    public double getRatioMean() {
        return ratioMean;
    }

    /**
     * Sets the value of the ratioMean property.
     * 
     */
    public void setRatioMean(double value) {
        this.ratioMean = value;
    }

    /**
     * Gets the value of the ratioStandardDev property.
     * 
     */
    public double getRatioStandardDev() {
        return ratioStandardDev;
    }

    /**
     * Sets the value of the ratioStandardDev property.
     * 
     */
    public void setRatioStandardDev(double value) {
        this.ratioStandardDev = value;
    }

    /**
     * Gets the value of the heavy2LightRatioMean property.
     * 
     */
    public double getHeavy2LightRatioMean() {
        return heavy2LightRatioMean;
    }

    /**
     * Sets the value of the heavy2LightRatioMean property.
     * 
     */
    public void setHeavy2LightRatioMean(double value) {
        this.heavy2LightRatioMean = value;
    }

    /**
     * Gets the value of the heavy2LightRatioStandardDev property.
     * 
     */
    public double getHeavy2LightRatioStandardDev() {
        return heavy2LightRatioStandardDev;
    }

    /**
     * Sets the value of the heavy2LightRatioStandardDev property.
     * 
     */
    public void setHeavy2LightRatioStandardDev(double value) {
        this.heavy2LightRatioStandardDev = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     */
    public void setWeight(double value) {
        this.weight = value;
    }

    /**
     * Gets the value of the lightSequence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLightSequence() {
        return lightSequence;
    }

    /**
     * Sets the value of the lightSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLightSequence(String value) {
        this.lightSequence = value;
    }

}
