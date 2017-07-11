
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

package umich.ms.fileio.filetypes.pepxml.jaxb.nested;

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
 *         &lt;element name="mod_aminoacid_probability" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="position" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="probability" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="prior" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="ptm" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ptm_peptide" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "modAminoacidProbability"
})
@XmlRootElement(name = "ptmprophet_result")
public class PtmprophetResult {

    @XmlElement(name = "mod_aminoacid_probability", required = true)
    protected List<PtmprophetResult.ModAminoacidProbability> modAminoacidProbability;
    @XmlAttribute(name = "prior", required = true)
    protected float prior;
    @XmlAttribute(name = "ptm", required = true)
    protected String ptm;
    @XmlAttribute(name = "ptm_peptide", required = true)
    protected String ptmPeptide;

    /**
     * Gets the value of the modAminoacidProbability property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the modAminoacidProbability property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModAminoacidProbability().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PtmprophetResult.ModAminoacidProbability }
     *
     *
     */
    public List<PtmprophetResult.ModAminoacidProbability> getModAminoacidProbability() {
        if (modAminoacidProbability == null) {
            modAminoacidProbability = new ArrayList<PtmprophetResult.ModAminoacidProbability>();
        }
        return this.modAminoacidProbability;
    }

    /**
     * Gets the value of the prior property.
     *
     */
    public float getPrior() {
        return prior;
    }

    /**
     * Sets the value of the prior property.
     *
     */
    public void setPrior(float value) {
        this.prior = value;
    }

    /**
     * Gets the value of the ptm property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPtm() {
        return ptm;
    }

    /**
     * Sets the value of the ptm property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPtm(String value) {
        this.ptm = value;
    }

    /**
     * Gets the value of the ptmPeptide property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPtmPeptide() {
        return ptmPeptide;
    }

    /**
     * Sets the value of the ptmPeptide property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPtmPeptide(String value) {
        this.ptmPeptide = value;
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
     *       &lt;attribute name="position" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *       &lt;attribute name="probability" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ModAminoacidProbability {

        @XmlAttribute(name = "position", required = true)
        @XmlJavaTypeAdapter(Adapter1 .class)
        @XmlSchemaType(name = "integer")
        protected Integer position;
        @XmlAttribute(name = "probability", required = true)
        protected float probability;

        /**
         * Gets the value of the position property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public Integer getPosition() {
            return position;
        }

        /**
         * Sets the value of the position property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPosition(Integer value) {
            this.position = value;
        }

        /**
         * Gets the value of the probability property.
         *
         */
        public float getProbability() {
            return probability;
        }

        /**
         * Sets the value of the probability property.
         *
         */
        public void setProbability(float value) {
            this.probability = value;
        }

    }

}
