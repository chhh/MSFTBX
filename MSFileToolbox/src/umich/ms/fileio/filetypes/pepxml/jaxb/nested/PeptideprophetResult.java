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
package umich.ms.fileio.filetypes.pepxml.jaxb.nested;

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
 *         &lt;element name="search_score_summary" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="2"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="probability" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="all_ntt_prob" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="analysis" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "searchScoreSummary"
})
@XmlRootElement(name = "peptideprophet_result")
public class PeptideprophetResult {

    @XmlElement(name = "search_score_summary")
    protected PeptideprophetResult.SearchScoreSummary searchScoreSummary;
    @XmlAttribute(name = "probability", required = true)
    protected float probability;
    @XmlAttribute(name = "all_ntt_prob")
    protected String allNttProb;
    @XmlAttribute(name = "analysis")
    protected String analysis;

    /**
     * Gets the value of the searchScoreSummary property.
     *
     * @return
     *     possible object is
     *     {@link PeptideprophetResult.SearchScoreSummary }
     *
     */
    public PeptideprophetResult.SearchScoreSummary getSearchScoreSummary() {
        return searchScoreSummary;
    }

    /**
     * Sets the value of the searchScoreSummary property.
     *
     * @param value
     *     allowed object is
     *     {@link PeptideprophetResult.SearchScoreSummary }
     *
     */
    public void setSearchScoreSummary(PeptideprophetResult.SearchScoreSummary value) {
        this.searchScoreSummary = value;
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

    /**
     * Gets the value of the allNttProb property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAllNttProb() {
        return allNttProb;
    }

    /**
     * Sets the value of the allNttProb property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAllNttProb(String value) {
        this.allNttProb = value;
    }

    /**
     * Gets the value of the analysis property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAnalysis() {
        return analysis;
    }

    /**
     * Sets the value of the analysis property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAnalysis(String value) {
        this.analysis = value;
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
     *         &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="2"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "parameter"
    })
    public static class SearchScoreSummary {

        @XmlElement(required = true)
        protected List<NameValueType> parameter;

        /**
         * Gets the value of the parameter property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the parameter property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParameter().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link NameValueType }
         *
         *
         */
        public List<NameValueType> getParameter() {
            if (parameter == null) {
                parameter = new ArrayList<NameValueType>(1);
            }
            return this.parameter;
        }

    }

}
