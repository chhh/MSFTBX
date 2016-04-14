
package umich.ms.fileio.filetypes.mzidentml.jaxb.primitive;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The list of enzymes used in experiment
 * 
 * <p>Java class for EnzymesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnzymesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Enzyme" type="{http://psidev.info/psi/pi/mzIdentML/1.2}EnzymeType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="independent" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnzymesType", propOrder = {
    "enzyme"
})
public class EnzymesType {

    @XmlElement(name = "Enzyme", required = true)
    protected List<EnzymeType> enzyme;
    @XmlAttribute(name = "independent")
    protected boolean independent;

    /**
     * Gets the value of the enzyme property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enzyme property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnzyme().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnzymeType }
     * 
     * 
     */
    public List<EnzymeType> getEnzyme() {
        if (enzyme == null) {
            enzyme = new ArrayList<EnzymeType>();
        }
        return this.enzyme;
    }

    /**
     * Gets the value of the independent property.
     * 
     */
    public boolean isIndependent() {
        return independent;
    }

    /**
     * Sets the value of the independent property.
     * 
     */
    public void setIndependent(boolean value) {
        this.independent = value;
    }

}
