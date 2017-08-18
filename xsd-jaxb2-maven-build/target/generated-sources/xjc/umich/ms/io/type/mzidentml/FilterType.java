//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.08.18 at 01:26:07 PM PDT 
//


package umich.ms.io.type.mzidentml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Filters applied to the search database. The filter must include at least one of Include and Exclude. If both are used, it is assumed that inclusion is performed first. 
 * 
 * <p>Java class for FilterType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FilterType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FilterType" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamType"/&gt;
 *         &lt;element name="Include" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamListType" minOccurs="0"/&gt;
 *         &lt;element name="Exclude" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamListType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterType", namespace = "http://psidev.info/psi/pi/mzIdentML/1.2", propOrder = {
    "filterType",
    "include",
    "exclude"
})
public class FilterType {

    @XmlElement(name = "FilterType", namespace = "http://psidev.info/psi/pi/mzIdentML/1.2", required = true)
    protected ParamType filterType;
    @XmlElement(name = "Include", namespace = "http://psidev.info/psi/pi/mzIdentML/1.2")
    protected ParamListType include;
    @XmlElement(name = "Exclude", namespace = "http://psidev.info/psi/pi/mzIdentML/1.2")
    protected ParamListType exclude;

    /**
     * Gets the value of the filterType property.
     * 
     * @return
     *     possible object is
     *     {@link ParamType }
     *     
     */
    public ParamType getFilterType() {
        return filterType;
    }

    /**
     * Sets the value of the filterType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamType }
     *     
     */
    public void setFilterType(ParamType value) {
        this.filterType = value;
    }

    /**
     * Gets the value of the include property.
     * 
     * @return
     *     possible object is
     *     {@link ParamListType }
     *     
     */
    public ParamListType getInclude() {
        return include;
    }

    /**
     * Sets the value of the include property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamListType }
     *     
     */
    public void setInclude(ParamListType value) {
        this.include = value;
    }

    /**
     * Gets the value of the exclude property.
     * 
     * @return
     *     possible object is
     *     {@link ParamListType }
     *     
     */
    public ParamListType getExclude() {
        return exclude;
    }

    /**
     * Sets the value of the exclude property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamListType }
     *     
     */
    public void setExclude(ParamListType value) {
        this.exclude = value;
    }

}
