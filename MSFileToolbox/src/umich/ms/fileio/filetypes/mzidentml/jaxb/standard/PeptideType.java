
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

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
 * &lt;complexType name="PeptideType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PeptideSequence" type="{http://psidev.info/psi/pi/mzIdentML/1.2}sequence"/&gt;
 *         &lt;element name="Modification" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ModificationType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SubstitutionModification" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SubstitutionModificationType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;group ref="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamGroup" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeptideType", propOrder = {
    "peptideSequence",
    "modifications",
    "substitutionModifications",
    "cvParamsAndUserParams"
})
public class PeptideType
    extends IdentifiableType
{

    @XmlElement(name = "PeptideSequence", required = true)
    protected String peptideSequence;
    @XmlElement(name = "Modification")
    protected List<ModificationType> modifications;
    @XmlElement(name = "SubstitutionModification")
    protected List<SubstitutionModificationType> substitutionModifications;
    @XmlElements({
        @XmlElement(name = "cvParam", type = CVParamType.class),
        @XmlElement(name = "userParam", type = UserParamType.class)
    })
    protected List<AbstractParamType> cvParamsAndUserParams;

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
     * Gets the value of the modifications property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the modifications property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModifications().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ModificationType }
     * 
     * 
     */
    public List<ModificationType> getModifications() {
        if (modifications == null) {
            modifications = new ArrayList<ModificationType>();
        }
        return this.modifications;
    }

    /**
     * Gets the value of the substitutionModifications property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the substitutionModifications property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubstitutionModifications().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubstitutionModificationType }
     * 
     * 
     */
    public List<SubstitutionModificationType> getSubstitutionModifications() {
        if (substitutionModifications == null) {
            substitutionModifications = new ArrayList<SubstitutionModificationType>();
        }
        return this.substitutionModifications;
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

}
