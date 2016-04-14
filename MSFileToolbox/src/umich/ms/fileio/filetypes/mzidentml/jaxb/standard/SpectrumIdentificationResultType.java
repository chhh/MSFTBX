
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * All identifications made from searching one spectrum. For PMF data, all peptide identifications will be listed underneath as SpectrumIdentificationItems. For MS/MS data, there will be ranked SpectrumIdentificationItems corresponding to possible different peptide IDs.
 * 
 * <p>Java class for SpectrumIdentificationResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SpectrumIdentificationResultType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SpectrumIdentificationItem" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SpectrumIdentificationItemType" maxOccurs="unbounded"/&gt;
 *         &lt;group ref="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamGroup" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="spectrumID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="spectraData_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpectrumIdentificationResultType", propOrder = {
    "spectrumIdentificationItems",
    "cvParamsAndUserParams"
})
public class SpectrumIdentificationResultType
    extends IdentifiableType
{

    @XmlElement(name = "SpectrumIdentificationItem", required = true)
    protected List<SpectrumIdentificationItemType> spectrumIdentificationItems;
    @XmlElements({
        @XmlElement(name = "cvParam", type = CVParamType.class),
        @XmlElement(name = "userParam", type = UserParamType.class)
    })
    protected List<AbstractParamType> cvParamsAndUserParams;
    @XmlAttribute(name = "spectrumID", required = true)
    protected String spectrumID;
    @XmlAttribute(name = "spectraData_ref", required = true)
    protected String spectraDataRef;

    /**
     * Gets the value of the spectrumIdentificationItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spectrumIdentificationItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpectrumIdentificationItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpectrumIdentificationItemType }
     * 
     * 
     */
    public List<SpectrumIdentificationItemType> getSpectrumIdentificationItems() {
        if (spectrumIdentificationItems == null) {
            spectrumIdentificationItems = new ArrayList<SpectrumIdentificationItemType>();
        }
        return this.spectrumIdentificationItems;
    }

    /**
     * Gets the value of the cvParamsAndUserParams property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cvParamsAndUserParams property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCvParamsAndUserParams().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CVParamType }
     * {@link UserParamType }
     * 
     * 
     */
    public List<AbstractParamType> getCvParamsAndUserParams() {
        if (cvParamsAndUserParams == null) {
            cvParamsAndUserParams = new ArrayList<AbstractParamType>();
        }
        return this.cvParamsAndUserParams;
    }

    /**
     * Gets the value of the spectrumID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpectrumID() {
        return spectrumID;
    }

    /**
     * Sets the value of the spectrumID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpectrumID(String value) {
        this.spectrumID = value;
    }

    /**
     * Gets the value of the spectraDataRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpectraDataRef() {
        return spectraDataRef;
    }

    /**
     * Sets the value of the spectraDataRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpectraDataRef(String value) {
        this.spectraDataRef = value;
    }

}
