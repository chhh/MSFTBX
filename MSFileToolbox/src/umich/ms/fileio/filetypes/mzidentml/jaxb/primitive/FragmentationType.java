
package umich.ms.fileio.filetypes.mzidentml.jaxb.primitive;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The product ions identified in this result.
 * 
 * <p>Java class for FragmentationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FragmentationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IonType" type="{http://psidev.info/psi/pi/mzIdentML/1.2}IonTypeType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FragmentationType", propOrder = {
    "ionType"
})
public class FragmentationType {

    @XmlElement(name = "IonType", required = true)
    protected List<IonTypeType> ionType;

    /**
     * Gets the value of the ionType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ionType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIonType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IonTypeType }
     * 
     * 
     */
    public List<IonTypeType> getIonType() {
        if (ionType == null) {
            ionType = new ArrayList<IonTypeType>();
        }
        return this.ionType;
    }

}
