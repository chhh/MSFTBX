//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.11 at 10:44:04 PM EDT 
//


package umich.ms.fileio.filetypes.pepxml.jaxb.old;

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
 *       &lt;attribute name="aminoacid" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="massdiff" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="variable" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="peptide_terminus" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="symbol" type="{http://regis-web.systemsbiology.net/pepXML}aa_symbolType" />
 *       &lt;attribute name="binary" type="{http://www.w3.org/2001/XMLSchema}string" />
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
public class AminoacidModification {

    @XmlAttribute(name = "aminoacid", required = true)
    protected String aminoacid;
    @XmlAttribute(name = "massdiff", required = true)
    protected String massdiff;
    @XmlAttribute(name = "mass", required = true)
    protected float mass;
    @XmlAttribute(name = "variable", required = true)
    protected String variable;
    @XmlAttribute(name = "peptide_terminus")
    protected String peptideTerminus;
    @XmlAttribute(name = "symbol")
    protected String symbol;
    @XmlAttribute(name = "binary")
    protected String binary;
    @XmlAttribute(name = "description")
    protected String description;

    /**
     * Gets the value of the aminoacid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAminoacid() {
        return aminoacid;
    }

    /**
     * Sets the value of the aminoacid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAminoacid(String value) {
        this.aminoacid = value;
    }

    /**
     * Gets the value of the massdiff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMassdiff() {
        return massdiff;
    }

    /**
     * Sets the value of the massdiff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMassdiff(String value) {
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
     * Gets the value of the peptideTerminus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeptideTerminus() {
        return peptideTerminus;
    }

    /**
     * Sets the value of the peptideTerminus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeptideTerminus(String value) {
        this.peptideTerminus = value;
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
     * Gets the value of the binary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinary() {
        return binary;
    }

    /**
     * Sets the value of the binary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinary(String value) {
        this.binary = value;
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