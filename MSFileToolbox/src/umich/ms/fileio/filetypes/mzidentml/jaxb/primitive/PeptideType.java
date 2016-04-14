
package umich.ms.fileio.filetypes.mzidentml.jaxb.primitive;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * One (poly)peptide (a sequence with modifications). The combination of Peptide sequence and modifications must be unique in the file.
 * 
 * <p>Java class for PeptideType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PeptideType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType">
 *       &lt;sequence>
 *         &lt;element name="PeptideSequence" type="{http://psidev.info/psi/pi/mzIdentML/1.2}sequence"/>
 *         &lt;element name="Modification" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ModificationType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SubstitutionModification" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SubstitutionModificationType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;group ref="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamGroup" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeptideType", propOrder = {
    "peptideSequence",
    "modification",
    "substitutionModification",
    "paramGroup"
})
public class PeptideType
    extends IdentifiableType
{

    @XmlElement(name = "PeptideSequence", required = true)
    protected String peptideSequence;
    @XmlElement(name = "Modification")
    protected List<ModificationType> modification;
    @XmlElement(name = "SubstitutionModification")
    protected List<SubstitutionModificationType> substitutionModification;
    @XmlElements({
        @XmlElement(name = "cvParam", type = CVParamType.class),
        @XmlElement(name = "userParam", type = UserParamType.class)
    })
    protected List<AbstractParamType> paramGroup;

    /**
     * Gets the value of the peptideSequence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeptideSequence() {
        return peptideSequence;
    }

    /**
     * Sets the value of the peptideSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeptideSequence(String value) {
        this.peptideSequence = value;
    }

    /**
     * Gets the value of the modification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the modification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ModificationType }
     * 
     * 
     */
    public List<ModificationType> getModification() {
        if (modification == null) {
            modification = new ArrayList<ModificationType>();
        }
        return this.modification;
    }

    /**
     * Gets the value of the substitutionModification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the substitutionModification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubstitutionModification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubstitutionModificationType }
     * 
     * 
     */
    public List<SubstitutionModificationType> getSubstitutionModification() {
        if (substitutionModification == null) {
            substitutionModification = new ArrayList<SubstitutionModificationType>();
        }
        return this.substitutionModification;
    }

    /**
     * Additional descriptors of this peptide sequence Gets the value of the paramGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paramGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParamGroup().add(newItem);
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
    public List<AbstractParamType> getParamGroup() {
        if (paramGroup == null) {
            paramGroup = new ArrayList<AbstractParamType>();
        }
        return this.paramGroup;
    }

}
