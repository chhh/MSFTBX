
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
 * The masses of residues used in the search.
 *
 * <p>Java class for MassTableType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="MassTableType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Residue" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ResidueType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AmbiguousResidue" type="{http://psidev.info/psi/pi/mzIdentML/1.2}AmbiguousResidueType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;group ref="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamGroup" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="msLevel" use="required" type="{http://psidev.info/psi/pi/mzIdentML/1.2}listOfIntegers" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MassTableType", propOrder = {
    "residue",
    "ambiguousResidue",
    "paramGroup"
})
public class MassTableType
    extends IdentifiableType
{

    @XmlElement(name = "Residue")
    protected List<ResidueType> residue;
    @XmlElement(name = "AmbiguousResidue")
    protected List<AmbiguousResidueType> ambiguousResidue;
    @XmlElements({
        @XmlElement(name = "cvParam", type = CVParamType.class),
        @XmlElement(name = "userParam", type = UserParamType.class)
    })
    protected List<AbstractParamType> paramGroup;
    @XmlAttribute(name = "msLevel", required = true)
    protected List<String> msLevel;

    /**
     * Gets the value of the residue property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the residue property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResidue().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResidueType }
     *
     *
     */
    public List<ResidueType> getResidue() {
        if (residue == null) {
            residue = new ArrayList<ResidueType>(1);
        }
        return this.residue;
    }

    /**
     * Gets the value of the ambiguousResidue property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ambiguousResidue property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmbiguousResidue().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AmbiguousResidueType }
     *
     *
     */
    public List<AmbiguousResidueType> getAmbiguousResidue() {
        if (ambiguousResidue == null) {
            ambiguousResidue = new ArrayList<AmbiguousResidueType>(1);
        }
        return this.ambiguousResidue;
    }

    /**
     * Additional parameters or descriptors for the MassTable.Gets the value of the paramGroup property.
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
            paramGroup = new ArrayList<AbstractParamType>(1);
        }
        return this.paramGroup;
    }

    /**
     * Gets the value of the msLevel property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the msLevel property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMsLevel().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     *
     *
     */
    public List<String> getMsLevel() {
        if (msLevel == null) {
            msLevel = new ArrayList<String>(1);
        }
        return this.msLevel;
    }

}
