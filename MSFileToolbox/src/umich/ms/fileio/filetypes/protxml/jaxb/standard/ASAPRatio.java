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
package umich.ms.fileio.filetypes.protxml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="ASAP_Seq" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ASAP_Peak" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ASAP_Dta" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="peptide_index" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="include" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="status" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="include" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="datanum" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                           &lt;attribute name="ratio_mean" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="ratio_standard_dev" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="heavy2light_ratio_mean" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="heavy2light_ratio_standard_dev" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="weight" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="peptide_binary_ind" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
 *                 &lt;attribute name="light_sequence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="ratio_mean" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="ratio_standard_dev" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="ratio_number_peptides" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="heavy2light_ratio_mean" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="heavy2light_ratio_standard_dev" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="status" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="peptide_inds" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "asapSeq"
})
@XmlRootElement(name = "ASAPRatio")
public class ASAPRatio {

    @XmlElement(name = "ASAP_Seq")
    protected List<ASAPSeq> asapSeq;
    @XmlAttribute(name = "ratio_mean", required = true)
    protected double ratioMean;
    @XmlAttribute(name = "ratio_standard_dev", required = true)
    protected double ratioStandardDev;
    @XmlAttribute(name = "ratio_number_peptides", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer ratioNumberPeptides;
    @XmlAttribute(name = "heavy2light_ratio_mean")
    protected Double heavy2LightRatioMean;
    @XmlAttribute(name = "heavy2light_ratio_standard_dev")
    protected Double heavy2LightRatioStandardDev;
    @XmlAttribute(name = "description")
    protected String description;
    @XmlAttribute(name = "status")
    protected String status;
    @XmlAttribute(name = "peptide_inds")
    protected String peptideInds;

    /**
     * Gets the value of the asapSeq property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the asapSeq property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getASAPSeq().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ASAPSeq }
     *
     *
     */
    public List<ASAPSeq> getASAPSeq() {
        if (asapSeq == null) {
            asapSeq = new ArrayList<ASAPSeq>(1);
        }
        return this.asapSeq;
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
     * Gets the value of the ratioNumberPeptides property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Integer getRatioNumberPeptides() {
        return ratioNumberPeptides;
    }

    /**
     * Sets the value of the ratioNumberPeptides property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRatioNumberPeptides(Integer value) {
        this.ratioNumberPeptides = value;
    }

    /**
     * Gets the value of the heavy2LightRatioMean property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getHeavy2LightRatioMean() {
        return heavy2LightRatioMean;
    }

    /**
     * Sets the value of the heavy2LightRatioMean property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setHeavy2LightRatioMean(Double value) {
        this.heavy2LightRatioMean = value;
    }

    /**
     * Gets the value of the heavy2LightRatioStandardDev property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getHeavy2LightRatioStandardDev() {
        return heavy2LightRatioStandardDev;
    }

    /**
     * Sets the value of the heavy2LightRatioStandardDev property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setHeavy2LightRatioStandardDev(Double value) {
        this.heavy2LightRatioStandardDev = value;
    }

    /**
     * Gets the value of the description property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDescription(String value) {
        this.description = value;
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
     * Gets the value of the peptideInds property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPeptideInds() {
        return peptideInds;
    }

    /**
     * Sets the value of the peptideInds property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPeptideInds(String value) {
        this.peptideInds = value;
    }

}
