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
 *         &lt;element name="fragment_masses" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="channel" use="required" type="{http://regis-web.systemsbiology.net/protXML}positiveInt" />
 *                 &lt;attribute name="mz" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="isotopic_contributions" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="contributing_channel" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="affected_channel" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="channel" use="required" type="{http://regis-web.systemsbiology.net/protXML}positiveInt" />
 *                                     &lt;attribute name="correction" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="channel" use="required" type="{http://regis-web.systemsbiology.net/protXML}positiveInt" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="mass_tolerance" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="centroiding_preference" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="normalization" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="output_type" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="channel_code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="min_pep_prob" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="min_pep_wt" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="min_prot_prob" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fragmentMasses",
    "isotopicContributions"
})
@XmlRootElement(name = "libra_summary")
public class LibraSummary {

    @XmlElement(name = "fragment_masses", required = true)
    protected List<FragmentMasses> fragmentMasses;
    @XmlElement(name = "isotopic_contributions")
    protected IsotopicContributions isotopicContributions;
    @XmlAttribute(name = "mass_tolerance", required = true)
    protected float massTolerance;
    @XmlAttribute(name = "centroiding_preference", required = true)
    protected int centroidingPreference;
    @XmlAttribute(name = "normalization", required = true)
    protected int normalization;
    @XmlAttribute(name = "output_type", required = true)
    protected int outputType;
    @XmlAttribute(name = "channel_code")
    protected String channelCode;
    @XmlAttribute(name = "min_pep_prob", required = true)
    protected float minPepProb;
    @XmlAttribute(name = "min_pep_wt", required = true)
    protected float minPepWt;
    @XmlAttribute(name = "min_prot_prob", required = true)
    protected float minProtProb;

    /**
     * Gets the value of the fragmentMasses property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fragmentMasses property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFragmentMasses().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FragmentMasses }
     *
     *
     */
    public List<FragmentMasses> getFragmentMasses() {
        if (fragmentMasses == null) {
            fragmentMasses = new ArrayList<FragmentMasses>(1);
        }
        return this.fragmentMasses;
    }

    /**
     * Gets the value of the isotopicContributions property.
     *
     * @return
     *     possible object is
     *     {@link IsotopicContributions }
     *
     */
    public IsotopicContributions getIsotopicContributions() {
        return isotopicContributions;
    }

    /**
     * Sets the value of the isotopicContributions property.
     *
     * @param value
     *     allowed object is
     *     {@link IsotopicContributions }
     *
     */
    public void setIsotopicContributions(IsotopicContributions value) {
        this.isotopicContributions = value;
    }

    /**
     * Gets the value of the massTolerance property.
     *
     */
    public float getMassTolerance() {
        return massTolerance;
    }

    /**
     * Sets the value of the massTolerance property.
     *
     */
    public void setMassTolerance(float value) {
        this.massTolerance = value;
    }

    /**
     * Gets the value of the centroidingPreference property.
     *
     */
    public int getCentroidingPreference() {
        return centroidingPreference;
    }

    /**
     * Sets the value of the centroidingPreference property.
     *
     */
    public void setCentroidingPreference(int value) {
        this.centroidingPreference = value;
    }

    /**
     * Gets the value of the normalization property.
     *
     */
    public int getNormalization() {
        return normalization;
    }

    /**
     * Sets the value of the normalization property.
     *
     */
    public void setNormalization(int value) {
        this.normalization = value;
    }

    /**
     * Gets the value of the outputType property.
     *
     */
    public int getOutputType() {
        return outputType;
    }

    /**
     * Sets the value of the outputType property.
     *
     */
    public void setOutputType(int value) {
        this.outputType = value;
    }

    /**
     * Gets the value of the channelCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * Sets the value of the channelCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setChannelCode(String value) {
        this.channelCode = value;
    }

    /**
     * Gets the value of the minPepProb property.
     *
     */
    public float getMinPepProb() {
        return minPepProb;
    }

    /**
     * Sets the value of the minPepProb property.
     *
     */
    public void setMinPepProb(float value) {
        this.minPepProb = value;
    }

    /**
     * Gets the value of the minPepWt property.
     *
     */
    public float getMinPepWt() {
        return minPepWt;
    }

    /**
     * Sets the value of the minPepWt property.
     *
     */
    public void setMinPepWt(float value) {
        this.minPepWt = value;
    }

    /**
     * Gets the value of the minProtProb property.
     *
     */
    public float getMinProtProb() {
        return minProtProb;
    }

    /**
     * Sets the value of the minProtProb property.
     *
     */
    public void setMinProtProb(float value) {
        this.minProtProb = value;
    }

}
