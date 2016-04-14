
package umich.ms.fileio.filetypes.mzidentml.jaxb.primitive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * The lists of spectrum identifications that are input to the protein detection process. 
 * 
 * <p>Java class for InputSpectrumIdentificationsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InputSpectrumIdentificationsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="spectrumIdentificationList_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InputSpectrumIdentificationsType")
public class InputSpectrumIdentificationsType {

    @XmlAttribute(name = "spectrumIdentificationList_ref", required = true)
    protected String spectrumIdentificationListRef;

    /**
     * Gets the value of the spectrumIdentificationListRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpectrumIdentificationListRef() {
        return spectrumIdentificationListRef;
    }

    /**
     * Sets the value of the spectrumIdentificationListRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpectrumIdentificationListRef(String value) {
        this.spectrumIdentificationListRef = value;
    }

}
