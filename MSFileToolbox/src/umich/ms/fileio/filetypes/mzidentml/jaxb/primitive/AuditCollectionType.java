
package umich.ms.fileio.filetypes.mzidentml.jaxb.primitive;

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
 * &lt;complexType name="AuditCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="Person" type="{http://psidev.info/psi/pi/mzIdentML/1.2}PersonType"/>
 *         &lt;element name="Organization" type="{http://psidev.info/psi/pi/mzIdentML/1.2}OrganizationType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuditCollectionType", propOrder = {
    "personOrOrganization"
})
public class AuditCollectionType {

    @XmlElements({
        @XmlElement(name = "Person", type = PersonType.class),
        @XmlElement(name = "Organization", type = OrganizationType.class)
    })
    protected List<AbstractContactType> personOrOrganization;

    /**
     * Gets the value of the personOrOrganization property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personOrOrganization property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonOrOrganization().add(newItem);
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
    public List<AbstractContactType> getPersonOrOrganization() {
        if (personOrOrganization == null) {
            personOrOrganization = new ArrayList<AbstractContactType>(1);
        }
        return this.personOrOrganization;
    }

}
