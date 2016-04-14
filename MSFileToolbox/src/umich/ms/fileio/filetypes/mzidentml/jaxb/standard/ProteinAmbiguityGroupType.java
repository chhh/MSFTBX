
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * A set of logically related results from a protein detection, for example to represent conflicting assignments of peptides to proteins.
 *
 *
 * <p>Java class for ProteinAmbiguityGroupType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ProteinAmbiguityGroupType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ProteinDetectionHypothesis" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ProteinDetectionHypothesisType" maxOccurs="unbounded"/&gt;
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
@XmlType(name = "ProteinAmbiguityGroupType", propOrder = {
    "proteinDetectionHypothesis",
    "paramGroup"
})
public class ProteinAmbiguityGroupType
    extends IdentifiableType
{

    @XmlElement(name = "ProteinDetectionHypothesis", required = true)
    protected List<ProteinDetectionHypothesisType> proteinDetectionHypothesis;
    @XmlElements({
        @XmlElement(name = "cvParam", type = CVParamType.class),
        @XmlElement(name = "userParam", type = UserParamType.class)
    })
    protected List<AbstractParamType> paramGroup;

    /**
     * Gets the value of the proteinDetectionHypothesis property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the proteinDetectionHypothesis property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProteinDetectionHypothesis().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProteinDetectionHypothesisType }
     *
     *
     */
    public List<ProteinDetectionHypothesisType> getProteinDetectionHypothesis() {
        if (proteinDetectionHypothesis == null) {
            proteinDetectionHypothesis = new ArrayList<ProteinDetectionHypothesisType>(1);
        }
        return this.proteinDetectionHypothesis;
    }

    /**
     * Scores or parameters associated with the ProteinAmbiguityGroup.Gets the value of the paramGroup property.
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

}
