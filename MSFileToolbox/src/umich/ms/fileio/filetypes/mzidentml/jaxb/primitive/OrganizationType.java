
package umich.ms.fileio.filetypes.mzidentml.jaxb.primitive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Organizations are entities like companies, universities, government agencies. Any additional information such as the address, email etc. should be supplied either as CV parameters or as user parameters. 
 * 
 * <p>Java class for OrganizationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrganizationType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}AbstractContactType">
 *       &lt;sequence>
 *         &lt;element name="Parent" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ParentOrganizationType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganizationType", propOrder = {
    "parent"
})
public class OrganizationType
    extends AbstractContactType
{

    @XmlElement(name = "Parent")
    protected ParentOrganizationType parent;

    /**
     * Gets the value of the parent property.
     * 
     * @return
     *     possible object is
     *     {@link ParentOrganizationType }
     *     
     */
    public ParentOrganizationType getParent() {
        return parent;
    }

    /**
     * Sets the value of the parent property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParentOrganizationType }
     *     
     */
    public void setParent(ParentOrganizationType value) {
        this.parent = value;
    }

}
