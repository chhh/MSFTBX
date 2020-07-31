
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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="mixturemodel_distribution" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="posmodel_distribution">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="type" type="{http://regis-web.systemsbiology.net/pepXML}model_dis_type" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="negmodel_distribution">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="type" type="{http://regis-web.systemsbiology.net/pepXML}model_dis_type" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="mixturemodel" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="point" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="pos_dens" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="neg_dens" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="pos_bandwidth" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="neg_bandwidth" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *       &lt;attribute name="precursor_ion_charge" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="comments" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="prior_probability" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="est_tot_correct" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="tot_num_spectra" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="num_iterations" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mixturemodelDistributionOrMixturemodel"
})
public class MixtureModelling {

    @XmlElements({
        @XmlElement(name = "mixturemodel_distribution", type = MixturemodelDistribution.class),
        @XmlElement(name = "mixturemodel", type = Mixturemodel.class)
    })
    protected List<Object> mixturemodelDistributionOrMixturemodel;
    @XmlAttribute(name = "precursor_ion_charge", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer precursorIonCharge;
    @XmlAttribute(name = "comments", required = true)
    protected String comments;
    @XmlAttribute(name = "prior_probability", required = true)
    protected double priorProbability;
    @XmlAttribute(name = "est_tot_correct", required = true)
    protected double estTotCorrect;
    @XmlAttribute(name = "tot_num_spectra", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer totNumSpectra;
    @XmlAttribute(name = "num_iterations", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer numIterations;

    /**
     * Gets the value of the mixturemodelDistributionOrMixturemodel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mixturemodelDistributionOrMixturemodel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMixturemodelDistributionOrMixturemodel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MixturemodelDistribution }
     * {@link Mixturemodel }
     * 
     * 
     */
    public List<Object> getMixturemodelDistributionOrMixturemodel() {
        if (mixturemodelDistributionOrMixturemodel == null) {
            mixturemodelDistributionOrMixturemodel = new ArrayList<Object>(1);
        }
        return this.mixturemodelDistributionOrMixturemodel;
    }

    /**
     * Gets the value of the precursorIonCharge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getPrecursorIonCharge() {
        return precursorIonCharge;
    }

    /**
     * Sets the value of the precursorIonCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrecursorIonCharge(Integer value) {
        this.precursorIonCharge = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
    }

    /**
     * Gets the value of the priorProbability property.
     * 
     */
    public double getPriorProbability() {
        return priorProbability;
    }

    /**
     * Sets the value of the priorProbability property.
     * 
     */
    public void setPriorProbability(double value) {
        this.priorProbability = value;
    }

    /**
     * Gets the value of the estTotCorrect property.
     * 
     */
    public double getEstTotCorrect() {
        return estTotCorrect;
    }

    /**
     * Sets the value of the estTotCorrect property.
     * 
     */
    public void setEstTotCorrect(double value) {
        this.estTotCorrect = value;
    }

    /**
     * Gets the value of the totNumSpectra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getTotNumSpectra() {
        return totNumSpectra;
    }

    /**
     * Sets the value of the totNumSpectra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNumSpectra(Integer value) {
        this.totNumSpectra = value;
    }

    /**
     * Gets the value of the numIterations property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getNumIterations() {
        return numIterations;
    }

    /**
     * Sets the value of the numIterations property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumIterations(Integer value) {
        this.numIterations = value;
    }

}
