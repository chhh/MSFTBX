
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
 *       &lt;attribute name="terminus" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="massdiff" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="variable" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="symbol" type="{http://regis-web.systemsbiology.net/pepXML}term_symbolType" />
 *       &lt;attribute name="protein_terminus" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class TerminalModification {

    @XmlAttribute(name = "terminus", required = true)
    protected String terminus;
    @XmlAttribute(name = "massdiff", required = true)
    protected float massdiff;
    @XmlAttribute(name = "mass", required = true)
    protected float mass;
    @XmlAttribute(name = "variable", required = true)
    protected String variable;
    @XmlAttribute(name = "symbol")
    protected String symbol;
    @XmlAttribute(name = "protein_terminus", required = true)
    protected String proteinTerminus;
    @XmlAttribute(name = "description")
    protected String description;

    /**
     * Gets the value of the terminus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminus() {
        return terminus;
    }

    /**
     * Sets the value of the terminus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminus(String value) {
        this.terminus = value;
    }

    /**
     * Gets the value of the massdiff property.
     * 
     */
    public float getMassdiff() {
        return massdiff;
    }

    /**
     * Sets the value of the massdiff property.
     * 
     */
    public void setMassdiff(float value) {
        this.massdiff = value;
    }

    /**
     * Gets the value of the mass property.
     * 
     */
    public float getMass() {
        return mass;
    }

    /**
     * Sets the value of the mass property.
     * 
     */
    public void setMass(float value) {
        this.mass = value;
    }

    /**
     * Gets the value of the variable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariable() {
        return variable;
    }

    /**
     * Sets the value of the variable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariable(String value) {
        this.variable = value;
    }

    /**
     * Gets the value of the symbol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the value of the symbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSymbol(String value) {
        this.symbol = value;
    }

    /**
     * Gets the value of the proteinTerminus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProteinTerminus() {
        return proteinTerminus;
    }

    /**
     * Sets the value of the proteinTerminus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProteinTerminus(String value) {
        this.proteinTerminus = value;
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

}
