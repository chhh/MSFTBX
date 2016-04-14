
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * The complete set of Contacts (people and organisations) for this file. 
 * 
 * <p>Java class for AuditCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuditCollectionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice maxOccurs="unbounded"&gt;
 *         &lt;element name="Person" type="{http://psidev.info/psi/pi/mzIdentML/1.2}PersonType"/&gt;
 *         &lt;element name="Organization" type="{http://psidev.info/psi/pi/mzIdentML/1.2}OrganizationType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuditCollectionType", propOrder = {
    "personsAndOrganizations"
})
public class AuditCollectionType {

    @XmlElements({
        @XmlElement(name = "Person", type = PersonType.class),
        @XmlElement(name = "Organization", type = OrganizationType.class)
    })
    protected List<AbstractContactType> personsAndOrganizations;

    /**
     * Gets the value of the personsAndOrganizations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personsAndOrganizations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonsAndOrganizations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersonType }
     * {@link OrganizationType }
     * 
     * 
     */
    public List<AbstractContactType> getPersonsAndOrganizations() {
        if (personsAndOrganizations == null) {
            personsAndOrganizations = new ArrayList<AbstractContactType>();
        }
        return this.personsAndOrganizations;
    }

}
