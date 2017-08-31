
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
 *       &lt;attribute name="sense" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="C"/>
 *             &lt;enumeration value="N"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="min_spacing" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" default="1" />
 *       &lt;attribute name="cut" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="1"/>
 *             &lt;maxLength value="20"/>
 *             &lt;pattern value="[A,C-I,K-N,P-T,VWY]+"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="no_cut">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="0"/>
 *             &lt;maxLength value="20"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Specificity {

    @XmlAttribute(name = "sense", required = true)
    protected String sense;
    @XmlAttribute(name = "min_spacing")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer minSpacing;
    @XmlAttribute(name = "cut", required = true)
    protected String cut;
    @XmlAttribute(name = "no_cut")
    protected String noCut;

    /**
     * Gets the value of the sense property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSense() {
        return sense;
    }

    /**
     * Sets the value of the sense property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSense(String value) {
        this.sense = value;
    }

    /**
     * Gets the value of the minSpacing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public int getMinSpacing() {
        if (minSpacing == null) {
            return new Adapter1().unmarshal("1");
        } else {
            return minSpacing;
        }
    }

    /**
     * Sets the value of the minSpacing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinSpacing(Integer value) {
        this.minSpacing = value;
    }

    /**
     * Gets the value of the cut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCut() {
        return cut;
    }

    /**
     * Sets the value of the cut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCut(String value) {
        this.cut = value;
    }

    /**
     * Gets the value of the noCut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoCut() {
        return noCut;
    }

    /**
     * Sets the value of the noCut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoCut(String value) {
        this.noCut = value;
    }

}
