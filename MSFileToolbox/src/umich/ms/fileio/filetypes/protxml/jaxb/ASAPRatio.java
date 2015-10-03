//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.22 at 02:29:00 PM EDT 
//


package umich.ms.fileio.filetypes.protxml.jaxb;

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
    protected List<ASAPRatio.ASAPSeq> asapSeq;
    @XmlAttribute(name = "ratio_mean", required = true)
    @XmlJavaTypeAdapter(Adapter4 .class)
    @XmlSchemaType(name = "double")
    protected Double ratioMean;
    @XmlAttribute(name = "ratio_standard_dev", required = true)
    @XmlJavaTypeAdapter(Adapter4 .class)
    @XmlSchemaType(name = "double")
    protected Double ratioStandardDev;
    @XmlAttribute(name = "ratio_number_peptides", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer ratioNumberPeptides;
    @XmlAttribute(name = "heavy2light_ratio_mean")
    @XmlJavaTypeAdapter(Adapter4 .class)
    @XmlSchemaType(name = "double")
    protected Double heavy2LightRatioMean;
    @XmlAttribute(name = "heavy2light_ratio_standard_dev")
    @XmlJavaTypeAdapter(Adapter4 .class)
    @XmlSchemaType(name = "double")
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
     * {@link ASAPRatio.ASAPSeq }
     * 
     * 
     */
    public List<ASAPRatio.ASAPSeq> getASAPSeq() {
        if (asapSeq == null) {
            asapSeq = new ArrayList<ASAPRatio.ASAPSeq>();
        }
        return this.asapSeq;
    }

    /**
     * Gets the value of the ratioMean property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Double getRatioMean() {
        return ratioMean;
    }

    /**
     * Sets the value of the ratioMean property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatioMean(Double value) {
        this.ratioMean = value;
    }

    /**
     * Gets the value of the ratioStandardDev property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Double getRatioStandardDev() {
        return ratioStandardDev;
    }

    /**
     * Sets the value of the ratioStandardDev property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatioStandardDev(Double value) {
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
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
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
    public static class ASAPSeq {

        @XmlElement(name = "ASAP_Peak", required = true)
        protected List<ASAPRatio.ASAPSeq.ASAPPeak> asapPeak;
        @XmlAttribute(name = "status", required = true)
        protected String status;
        @XmlAttribute(name = "include", required = true)
        protected String include;
        @XmlAttribute(name = "datanum", required = true)
        @XmlJavaTypeAdapter(Adapter1 .class)
        @XmlSchemaType(name = "nonNegativeInteger")
        protected Integer datanum;
        @XmlAttribute(name = "ratio_mean", required = true)
        @XmlJavaTypeAdapter(Adapter4 .class)
        @XmlSchemaType(name = "double")
        protected Double ratioMean;
        @XmlAttribute(name = "ratio_standard_dev", required = true)
        @XmlJavaTypeAdapter(Adapter4 .class)
        @XmlSchemaType(name = "double")
        protected Double ratioStandardDev;
        @XmlAttribute(name = "heavy2light_ratio_mean", required = true)
        @XmlJavaTypeAdapter(Adapter4 .class)
        @XmlSchemaType(name = "double")
        protected Double heavy2LightRatioMean;
        @XmlAttribute(name = "heavy2light_ratio_standard_dev", required = true)
        @XmlJavaTypeAdapter(Adapter4 .class)
        @XmlSchemaType(name = "double")
        protected Double heavy2LightRatioStandardDev;
        @XmlAttribute(name = "weight", required = true)
        @XmlJavaTypeAdapter(Adapter4 .class)
        @XmlSchemaType(name = "double")
        protected Double weight;
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
         * {@link ASAPRatio.ASAPSeq.ASAPPeak }
         * 
         * 
         */
        public List<ASAPRatio.ASAPSeq.ASAPPeak> getASAPPeak() {
            if (asapPeak == null) {
                asapPeak = new ArrayList<ASAPRatio.ASAPSeq.ASAPPeak>();
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
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Double getRatioMean() {
            return ratioMean;
        }

        /**
         * Sets the value of the ratioMean property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRatioMean(Double value) {
            this.ratioMean = value;
        }

        /**
         * Gets the value of the ratioStandardDev property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Double getRatioStandardDev() {
            return ratioStandardDev;
        }

        /**
         * Sets the value of the ratioStandardDev property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRatioStandardDev(Double value) {
            this.ratioStandardDev = value;
        }

        /**
         * Gets the value of the heavy2LightRatioMean property.
         * 
         * @return
         *     possible object is
         *     {@link String }
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
         *     {@link String }
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
         *     {@link String }
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
         *     {@link String }
         *     
         */
        public void setHeavy2LightRatioStandardDev(Double value) {
            this.heavy2LightRatioStandardDev = value;
        }

        /**
         * Gets the value of the weight property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Double getWeight() {
            return weight;
        }

        /**
         * Sets the value of the weight property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWeight(Double value) {
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
         *         &lt;element name="ASAP_Dta" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="peptide_index" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="include" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
         *       &lt;attribute name="peptide_binary_ind" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "asapDta"
        })
        public static class ASAPPeak {

            @XmlElement(name = "ASAP_Dta", required = true)
            protected List<ASAPRatio.ASAPSeq.ASAPPeak.ASAPDta> asapDta;
            @XmlAttribute(name = "status", required = true)
            protected String status;
            @XmlAttribute(name = "include", required = true)
            protected String include;
            @XmlAttribute(name = "datanum", required = true)
            @XmlJavaTypeAdapter(Adapter1 .class)
            @XmlSchemaType(name = "nonNegativeInteger")
            protected Integer datanum;
            @XmlAttribute(name = "ratio_mean", required = true)
            @XmlJavaTypeAdapter(Adapter4 .class)
            @XmlSchemaType(name = "double")
            protected Double ratioMean;
            @XmlAttribute(name = "ratio_standard_dev", required = true)
            @XmlJavaTypeAdapter(Adapter4 .class)
            @XmlSchemaType(name = "double")
            protected Double ratioStandardDev;
            @XmlAttribute(name = "heavy2light_ratio_mean", required = true)
            @XmlJavaTypeAdapter(Adapter4 .class)
            @XmlSchemaType(name = "double")
            protected Double heavy2LightRatioMean;
            @XmlAttribute(name = "heavy2light_ratio_standard_dev", required = true)
            @XmlJavaTypeAdapter(Adapter4 .class)
            @XmlSchemaType(name = "double")
            protected Double heavy2LightRatioStandardDev;
            @XmlAttribute(name = "weight", required = true)
            @XmlJavaTypeAdapter(Adapter4 .class)
            @XmlSchemaType(name = "double")
            protected Double weight;
            @XmlAttribute(name = "peptide_binary_ind", required = true)
            protected String peptideBinaryInd;

            /**
             * Gets the value of the asapDta property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the asapDta property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getASAPDta().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ASAPRatio.ASAPSeq.ASAPPeak.ASAPDta }
             * 
             * 
             */
            public List<ASAPRatio.ASAPSeq.ASAPPeak.ASAPDta> getASAPDta() {
                if (asapDta == null) {
                    asapDta = new ArrayList<ASAPRatio.ASAPSeq.ASAPPeak.ASAPDta>();
                }
                return this.asapDta;
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
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public Double getRatioMean() {
                return ratioMean;
            }

            /**
             * Sets the value of the ratioMean property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRatioMean(Double value) {
                this.ratioMean = value;
            }

            /**
             * Gets the value of the ratioStandardDev property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public Double getRatioStandardDev() {
                return ratioStandardDev;
            }

            /**
             * Sets the value of the ratioStandardDev property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRatioStandardDev(Double value) {
                this.ratioStandardDev = value;
            }

            /**
             * Gets the value of the heavy2LightRatioMean property.
             * 
             * @return
             *     possible object is
             *     {@link String }
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
             *     {@link String }
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
             *     {@link String }
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
             *     {@link String }
             *     
             */
            public void setHeavy2LightRatioStandardDev(Double value) {
                this.heavy2LightRatioStandardDev = value;
            }

            /**
             * Gets the value of the weight property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public Double getWeight() {
                return weight;
            }

            /**
             * Sets the value of the weight property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setWeight(Double value) {
                this.weight = value;
            }

            /**
             * Gets the value of the peptideBinaryInd property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPeptideBinaryInd() {
                return peptideBinaryInd;
            }

            /**
             * Sets the value of the peptideBinaryInd property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPeptideBinaryInd(String value) {
                this.peptideBinaryInd = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="peptide_index" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="include" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class ASAPDta {

                @XmlAttribute(name = "peptide_index", required = true)
                protected String peptideIndex;
                @XmlAttribute(name = "include", required = true)
                protected String include;

                /**
                 * Gets the value of the peptideIndex property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPeptideIndex() {
                    return peptideIndex;
                }

                /**
                 * Sets the value of the peptideIndex property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPeptideIndex(String value) {
                    this.peptideIndex = value;
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

            }

        }

    }

}