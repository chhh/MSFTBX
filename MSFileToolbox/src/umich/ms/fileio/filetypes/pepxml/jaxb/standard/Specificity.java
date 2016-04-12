//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.11 at 08:24:52 PM EDT 
//


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