
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * IonType defines the index of fragmentation ions being reported, importing a CV term for the type of ion e.g. b ion. Example: if b3 b7 b8 and b10 have been identified, the index attribute will contain 3 7 8 10, and the corresponding values will be reported in parallel arrays below 
 * 
 * <p>Java class for IonTypeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IonTypeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FragmentArray" type="{http://psidev.info/psi/pi/mzIdentML/1.2}FragmentArrayType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="cvParam" type="{http://psidev.info/psi/pi/mzIdentML/1.2}CVParamType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="index" type="{http://psidev.info/psi/pi/mzIdentML/1.2}listOfIntegers" /&gt;
 *       &lt;attribute name="charge" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IonTypeType", propOrder = {
    "fragmentArraies",
    "cvParams"
})
public class IonTypeType {

    @XmlElement(name = "FragmentArray")
    protected List<FragmentArrayType> fragmentArraies;
    @XmlElement(name = "cvParam", required = true)
    protected List<CVParamType> cvParams;
    @XmlAttribute(name = "index")
    protected List<String> indices;
    @XmlAttribute(name = "charge", required = true)
    protected int charge;

    /**
     * Gets the value of the fragmentArraies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fragmentArraies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFragmentArraies().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FragmentArrayType }
     * 
     * 
     */
    public List<FragmentArrayType> getFragmentArraies() {
        if (fragmentArraies == null) {
            fragmentArraies = new ArrayList<FragmentArrayType>();
        }
        return this.fragmentArraies;
    }

    /**
     * Gets the value of the cvParams property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cvParams property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCvParams().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CVParamType }
     * 
     * 
     */
    public List<CVParamType> getCvParams() {
        if (cvParams == null) {
            cvParams = new ArrayList<CVParamType>();
        }
        return this.cvParams;
    }

    /**
     * Gets the value of the indices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the indices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIndices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIndices() {
        if (indices == null) {
            indices = new ArrayList<String>();
        }
        return this.indices;
    }

    /**
     * Gets the value of the charge property.
     * 
     */
    public int getCharge() {
        return charge;
    }

    /**
     * Sets the value of the charge property.
     * 
     */
    public void setCharge(int value) {
        this.charge = value;
    }

}
