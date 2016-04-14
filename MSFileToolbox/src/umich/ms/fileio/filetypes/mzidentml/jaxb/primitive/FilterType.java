
package umich.ms.fileio.filetypes.mzidentml.jaxb.primitive;

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
 * &lt;complexType name="FilterType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FilterType" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamType"/>
 *         &lt;element name="Include" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamListType" minOccurs="0"/>
 *         &lt;element name="Exclude" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterType", propOrder = {
    "filterType",
    "include",
    "exclude"
})
public class FilterType {

    @XmlElement(name = "FilterType", required = true)
    protected ParamType filterType;
    @XmlElement(name = "Include")
    protected ParamListType include;
    @XmlElement(name = "Exclude")
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
