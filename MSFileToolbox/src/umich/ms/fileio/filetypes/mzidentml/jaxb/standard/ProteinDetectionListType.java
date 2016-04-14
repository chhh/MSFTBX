
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * The protein list resulting from a protein detection process.
 * 
 * <p>Java class for ProteinDetectionListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProteinDetectionListType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ProteinAmbiguityGroup" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ProteinAmbiguityGroupType" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "ProteinDetectionListType", propOrder = {
    "proteinAmbiguityGroups",
    "cvParamsAndUserParams"
})
public class ProteinDetectionListType
    extends IdentifiableType
{

    @XmlElement(name = "ProteinAmbiguityGroup")
    protected List<ProteinAmbiguityGroupType> proteinAmbiguityGroups;
    @XmlElements({
        @XmlElement(name = "cvParam", type = CVParamType.class),
        @XmlElement(name = "userParam", type = UserParamType.class)
    })
    protected List<AbstractParamType> cvParamsAndUserParams;

    /**
     * Gets the value of the proteinAmbiguityGroups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the proteinAmbiguityGroups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProteinAmbiguityGroups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProteinAmbiguityGroupType }
     * 
     * 
     */
    public List<ProteinAmbiguityGroupType> getProteinAmbiguityGroups() {
        if (proteinAmbiguityGroups == null) {
            proteinAmbiguityGroups = new ArrayList<ProteinAmbiguityGroupType>();
        }
        return this.proteinAmbiguityGroups;
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
