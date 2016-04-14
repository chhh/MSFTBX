
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Specification of a search modification as parameter for a spectra search. Contains the name of the modification, the mass, the specificity and whether it is a static modification. 
 * 
 * <p>Java class for SearchModificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchModificationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SpecificityRules" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SpecificityRulesType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="cvParam" type="{http://psidev.info/psi/pi/mzIdentML/1.2}CVParamType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="fixedMod" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="massDelta" use="required" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *       &lt;attribute name="residues" use="required" type="{http://psidev.info/psi/pi/mzIdentML/1.2}listOfCharsOrAny" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchModificationType", propOrder = {
    "specificityRules",
    "cvParams"
})
public class SearchModificationType {

    @XmlElement(name = "SpecificityRules")
    protected List<SpecificityRulesType> specificityRules;
    @XmlElement(name = "cvParam", required = true)
    protected List<CVParamType> cvParams;
    @XmlAttribute(name = "fixedMod", required = true)
    protected boolean fixedMod;
    @XmlAttribute(name = "massDelta", required = true)
    protected float massDelta;
    @XmlAttribute(name = "residues", required = true)
    protected List<String> residues;

    /**
     * Gets the value of the specificityRules property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the specificityRules property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecificityRules().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpecificityRulesType }
     * 
     * 
     */
    public List<SpecificityRulesType> getSpecificityRules() {
        if (specificityRules == null) {
            specificityRules = new ArrayList<SpecificityRulesType>();
        }
        return this.specificityRules;
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
     * Gets the value of the fixedMod property.
     * 
     */
    public boolean isFixedMod() {
        return fixedMod;
    }

    /**
     * Sets the value of the fixedMod property.
     * 
     */
    public void setFixedMod(boolean value) {
        this.fixedMod = value;
    }

    /**
     * Gets the value of the massDelta property.
     * 
     */
    public float getMassDelta() {
        return massDelta;
    }

    /**
     * Sets the value of the massDelta property.
     * 
     */
    public void setMassDelta(float value) {
        this.massDelta = value;
    }

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
     * {@link String }
     * 
     * 
     */
    public List<String> getResidues() {
        if (residues == null) {
            residues = new ArrayList<String>();
        }
        return this.residues;
    }

}
