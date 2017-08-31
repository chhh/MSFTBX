
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

package umich.ms.fileio.filetypes.pepxml.jaxb.primitive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="author" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="elution" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="labeled_residues" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="area_flag" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="static_quant" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="specified_residue_masses" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "asapratio_summary")
public class AsapratioSummary {

    @XmlAttribute(name = "version", required = true)
    protected String version;
    @XmlAttribute(name = "author", required = true)
    protected String author;
    @XmlAttribute(name = "elution", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "integer")
    protected Integer elution;
    @XmlAttribute(name = "labeled_residues", required = true)
    protected String labeledResidues;
    @XmlAttribute(name = "area_flag", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer areaFlag;
    @XmlAttribute(name = "static_quant", required = true)
    protected String staticQuant;
    @XmlAttribute(name = "specified_residue_masses")
    protected String specifiedResidueMasses;

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
     * Gets the value of the elution property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getElution() {
        return elution;
    }

    /**
     * Sets the value of the elution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElution(Integer value) {
        this.elution = value;
    }

    /**
     * Gets the value of the labeledResidues property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabeledResidues() {
        return labeledResidues;
    }

    /**
     * Sets the value of the labeledResidues property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabeledResidues(String value) {
        this.labeledResidues = value;
    }

    /**
     * Gets the value of the areaFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getAreaFlag() {
        return areaFlag;
    }

    /**
     * Sets the value of the areaFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaFlag(Integer value) {
        this.areaFlag = value;
    }

    /**
     * Gets the value of the staticQuant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStaticQuant() {
        return staticQuant;
    }

    /**
     * Sets the value of the staticQuant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStaticQuant(String value) {
        this.staticQuant = value;
    }

    /**
     * Gets the value of the specifiedResidueMasses property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecifiedResidueMasses() {
        return specifiedResidueMasses;
    }

    /**
     * Sets the value of the specifiedResidueMasses property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecifiedResidueMasses(String value) {
        this.specifiedResidueMasses = value;
    }

}
