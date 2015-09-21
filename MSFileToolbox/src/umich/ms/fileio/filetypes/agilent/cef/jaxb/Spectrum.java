//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.20 at 02:40:52 PM EDT 
//


package umich.ms.fileio.filetypes.agilent.cef.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
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
 *       &lt;sequence>
 *         &lt;element ref="{}MSDetails"/>
 *         &lt;element ref="{}RTRanges"/>
 *         &lt;element ref="{}Device"/>
 *         &lt;element ref="{}MSPeaks"/>
 *       &lt;/sequence>
 *       &lt;attribute name="cpdAlgo" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="satLimit" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "msDetails",
    "rtRanges",
    "device",
    "msPeaks"
})
@XmlRootElement(name = "Spectrum")
public class Spectrum {

    @XmlElement(name = "MSDetails", required = true)
    protected MSDetails msDetails;
    @XmlElement(name = "RTRanges", required = true)
    protected RTRanges rtRanges;
    @XmlElement(name = "Device", required = true)
    protected Device device;
    @XmlElement(name = "MSPeaks", required = true)
    protected MSPeaks msPeaks;
    @XmlAttribute(name = "cpdAlgo", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String cpdAlgo;
    @XmlAttribute(name = "satLimit", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "integer")
    protected Integer satLimit;
    @XmlAttribute(name = "type", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String type;

    /**
     * Gets the value of the msDetails property.
     * 
     * @return
     *     possible object is
     *     {@link MSDetails }
     *     
     */
    public MSDetails getMSDetails() {
        return msDetails;
    }

    /**
     * Sets the value of the msDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link MSDetails }
     *     
     */
    public void setMSDetails(MSDetails value) {
        this.msDetails = value;
    }

    /**
     * Gets the value of the rtRanges property.
     * 
     * @return
     *     possible object is
     *     {@link RTRanges }
     *     
     */
    public RTRanges getRTRanges() {
        return rtRanges;
    }

    /**
     * Sets the value of the rtRanges property.
     * 
     * @param value
     *     allowed object is
     *     {@link RTRanges }
     *     
     */
    public void setRTRanges(RTRanges value) {
        this.rtRanges = value;
    }

    /**
     * Gets the value of the device property.
     * 
     * @return
     *     possible object is
     *     {@link Device }
     *     
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Sets the value of the device property.
     * 
     * @param value
     *     allowed object is
     *     {@link Device }
     *     
     */
    public void setDevice(Device value) {
        this.device = value;
    }

    /**
     * Gets the value of the msPeaks property.
     * 
     * @return
     *     possible object is
     *     {@link MSPeaks }
     *     
     */
    public MSPeaks getMSPeaks() {
        return msPeaks;
    }

    /**
     * Sets the value of the msPeaks property.
     * 
     * @param value
     *     allowed object is
     *     {@link MSPeaks }
     *     
     */
    public void setMSPeaks(MSPeaks value) {
        this.msPeaks = value;
    }

    /**
     * Gets the value of the cpdAlgo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpdAlgo() {
        return cpdAlgo;
    }

    /**
     * Sets the value of the cpdAlgo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpdAlgo(String value) {
        this.cpdAlgo = value;
    }

    /**
     * Gets the value of the satLimit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getSatLimit() {
        return satLimit;
    }

    /**
     * Sets the value of the satLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSatLimit(Integer value) {
        this.satLimit = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
