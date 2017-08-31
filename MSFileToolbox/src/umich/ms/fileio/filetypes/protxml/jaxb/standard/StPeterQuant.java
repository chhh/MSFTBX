
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
 *         &lt;element name="StPeterQuant_peptide" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="sequence" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="charge" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="spectralIndex" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="SIn" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="ng" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "stPeterQuantPeptide"
})
@XmlRootElement(name = "StPeterQuant")
public class StPeterQuant {

    @XmlElement(name = "StPeterQuant_peptide")
    protected List<StPeterQuantPeptide> stPeterQuantPeptide;
    @XmlAttribute(name = "SIn", required = true)
    protected double sIn;
    @XmlAttribute(name = "ng")
    protected Double ng;

    /**
     * Gets the value of the stPeterQuantPeptide property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stPeterQuantPeptide property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStPeterQuantPeptide().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StPeterQuantPeptide }
     * 
     * 
     */
    public List<StPeterQuantPeptide> getStPeterQuantPeptide() {
        if (stPeterQuantPeptide == null) {
            stPeterQuantPeptide = new ArrayList<StPeterQuantPeptide>(1);
        }
        return this.stPeterQuantPeptide;
    }

    /**
     * Gets the value of the sIn property.
     * 
     */
    public double getSIn() {
        return sIn;
    }

    /**
     * Sets the value of the sIn property.
     * 
     */
    public void setSIn(double value) {
        this.sIn = value;
    }

    /**
     * Gets the value of the ng property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getNg() {
        return ng;
    }

    /**
     * Sets the value of the ng property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setNg(Double value) {
        this.ng = value;
    }

}
