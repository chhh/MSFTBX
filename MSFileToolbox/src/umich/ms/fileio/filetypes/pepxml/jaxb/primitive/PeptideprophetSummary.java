
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

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="inputfile" type="{http://regis-web.systemsbiology.net/pepXML}inputFileType" maxOccurs="unbounded"/>
 *         &lt;element name="roc_error_data" type="{http://regis-web.systemsbiology.net/pepXML}rocErrorDataType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="distribution_point" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="fvalue" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="obs_1_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="model_1_pos_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="model_1_neg_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="obs_2_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="model_2_pos_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="model_2_neg_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="obs_3_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="model_3_pos_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="model_3_neg_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="obs_4_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="model_4_pos_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="model_4_neg_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="obs_5_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="model_5_pos_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="model_5_neg_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="obs_6_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="model_6_pos_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="model_6_neg_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="obs_7_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="model_7_pos_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="model_7_neg_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="mixture_model" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded">
 *                   &lt;element name="mixturemodel_distribution" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="posmodel_distribution">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="type" type="{http://regis-web.systemsbiology.net/pepXML}model_dis_type" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="negmodel_distribution">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="type" type="{http://regis-web.systemsbiology.net/pepXML}model_dis_type" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="mixturemodel" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="point" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                     &lt;attribute name="pos_dens" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                     &lt;attribute name="neg_dens" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="pos_bandwidth" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                           &lt;attribute name="neg_bandwidth" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/choice>
 *                 &lt;attribute name="precursor_ion_charge" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="comments" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="prior_probability" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="est_tot_correct" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="tot_num_spectra" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="num_iterations" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="author" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="min_prob" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="options" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="est_tot_num_correct" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "inputfile",
    "rocErrorData",
    "distributionPoint",
    "mixtureModel"
})
@XmlRootElement(name = "peptideprophet_summary")
public class PeptideprophetSummary {

    @XmlElement(required = true)
    protected List<InputFileType> inputfile;
    @XmlElement(name = "roc_error_data")
    protected List<RocErrorDataType> rocErrorData;
    @XmlElement(name = "distribution_point")
    protected List<DistributionPoint> distributionPoint;
    @XmlElement(name = "mixture_model")
    protected List<MixtureModelling> mixtureModel;
    @XmlAttribute(name = "version", required = true)
    protected String version;
    @XmlAttribute(name = "author", required = true)
    protected String author;
    @XmlAttribute(name = "min_prob", required = true)
    protected float minProb;
    @XmlAttribute(name = "options")
    protected String options;
    @XmlAttribute(name = "est_tot_num_correct")
    protected float estTotNumCorrect;

    /**
     * Gets the value of the inputfile property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inputfile property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInputfile().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InputFileType }
     *
     *
     */
    public List<InputFileType> getInputfile() {
        if (inputfile == null) {
            inputfile = new ArrayList<InputFileType>();
        }
        return this.inputfile;
    }

    /**
     * Gets the value of the rocErrorData property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rocErrorData property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRocErrorData().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RocErrorDataType }
     *
     *
     */
    public List<RocErrorDataType> getRocErrorData() {
        if (rocErrorData == null) {
            rocErrorData = new ArrayList<RocErrorDataType>();
        }
        return this.rocErrorData;
    }

    /**
     * Gets the value of the distributionPoint property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the distributionPoint property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDistributionPoint().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DistributionPoint }
     *
     *
     */
    public List<DistributionPoint> getDistributionPoint() {
        if (distributionPoint == null) {
            distributionPoint = new ArrayList<DistributionPoint>();
        }
        return this.distributionPoint;
    }

    /**
     * Gets the value of the mixtureModel property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mixtureModel property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMixtureModel().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MixtureModelling }
     *
     *
     */
    public List<MixtureModelling> getMixtureModel() {
        if (mixtureModel == null) {
            mixtureModel = new ArrayList<MixtureModelling>();
        }
        return this.mixtureModel;
    }

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
     * Gets the value of the author property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAuthor(String value) {
        this.author = value;
    }

    /**
     * Gets the value of the minProb property.
     *
     */
    public float getMinProb() {
        return minProb;
    }

    /**
     * Sets the value of the minProb property.
     *
     */
    public void setMinProb(float value) {
        this.minProb = value;
    }

    /**
     * Gets the value of the options property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOptions() {
        return options;
    }

    /**
     * Sets the value of the options property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOptions(String value) {
        this.options = value;
    }

    /**
     * Gets the value of the estTotNumCorrect property.
     *
     */
    public float getEstTotNumCorrect() {
        return estTotNumCorrect;
    }

    /**
     * Sets the value of the estTotNumCorrect property.
     *
     */
    public void setEstTotNumCorrect(float value) {
        this.estTotNumCorrect = value;
    }

}
