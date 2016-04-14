
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Helper type to allow either a cvParam or a userParam to be provided for an element.
 * 
 * <p>Java class for ParamType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParamType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;group ref="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamGroup"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParamType", propOrder = {
    "userParam",
    "cvParam"
})
public class ParamType {

    protected UserParamType userParam;
    protected CVParamType cvParam;

    /**
     * Gets the value of the userParam property.
     * 
     * @return
     *     possible object is
     *     {@link UserParamType }
     *     
     */
    public UserParamType getUserParam() {
        return userParam;
    }

    /**
     * Sets the value of the userParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserParamType }
     *     
     */
    public void setUserParam(UserParamType value) {
        this.userParam = value;
    }

    /**
     * Gets the value of the cvParam property.
     * 
     * @return
     *     possible object is
     *     {@link CVParamType }
     *     
     */
    public CVParamType getCvParam() {
        return cvParam;
    }

    /**
     * Sets the value of the cvParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link CVParamType }
     *     
     */
    public void setCvParam(CVParamType value) {
        this.cvParam = value;
    }

}
