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
package umich.ms.fileio.filetypes.pepxml.jaxb.standard;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="enzyme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="max_num_internal_cleavages" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="min_number_termini" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class EnzymaticSearchConstraint {

    @XmlAttribute(name = "enzyme", required = true)
    protected String enzyme;
    @XmlAttribute(name = "max_num_internal_cleavages", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer maxNumInternalCleavages;
    @XmlAttribute(name = "min_number_termini", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer minNumberTermini;

    /**
     * Gets the value of the enzyme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnzyme() {
        return enzyme;
    }

    /**
     * Sets the value of the enzyme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnzyme(String value) {
        this.enzyme = value;
    }

    /**
     * Gets the value of the maxNumInternalCleavages property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getMaxNumInternalCleavages() {
        return maxNumInternalCleavages;
    }

    /**
     * Sets the value of the maxNumInternalCleavages property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxNumInternalCleavages(Integer value) {
        this.maxNumInternalCleavages = value;
    }

    /**
     * Gets the value of the minNumberTermini property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getMinNumberTermini() {
        return minNumberTermini;
    }

    /**
     * Sets the value of the minNumberTermini property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinNumberTermini(Integer value) {
        this.minNumberTermini = value;
    }

}
