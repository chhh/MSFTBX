
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
    "residues",
    "ambiguousResidues",
    "cvParamsAndUserParams"
})
public class MassTableType
    extends IdentifiableType
{

    @XmlElement(name = "Residue")
    protected List<ResidueType> residues;
    @XmlElement(name = "AmbiguousResidue")
    protected List<AmbiguousResidueType> ambiguousResidues;
    @XmlElements({
        @XmlElement(name = "cvParam", type = CVParamType.class),
        @XmlElement(name = "userParam", type = UserParamType.class)
    })
    protected List<AbstractParamType> cvParamsAndUserParams;
    @XmlAttribute(name = "msLevel", required = true)
    protected List<String> msLevels;

    /**
     * Gets the value of the residues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the residues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResidues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResidueType }
     * 
     * 
     */
    public List<ResidueType> getResidues() {
        if (residues == null) {
            residues = new ArrayList<ResidueType>();
        }
        return this.residues;
    }

    /**
     * Gets the value of the ambiguousResidues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ambiguousResidues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmbiguousResidues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AmbiguousResidueType }
     * 
     * 
     */
    public List<AmbiguousResidueType> getAmbiguousResidues() {
        if (ambiguousResidues == null) {
            ambiguousResidues = new ArrayList<AmbiguousResidueType>();
        }
        return this.ambiguousResidues;
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
     * Gets the value of the msLevels property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the msLevels property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMsLevels().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMsLevels() {
        if (msLevels == null) {
            msLevels = new ArrayList<String>();
        }
        return this.msLevels;
    }

}
