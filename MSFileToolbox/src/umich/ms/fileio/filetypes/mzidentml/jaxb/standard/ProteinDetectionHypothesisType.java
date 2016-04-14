
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
 * A single result of the ProteinDetection analysis (i.e. a protein).
 *
 * <p>Java class for ProteinDetectionHypothesisType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ProteinDetectionHypothesisType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PeptideHypothesis" type="{http://psidev.info/psi/pi/mzIdentML/1.2}PeptideHypothesisType" maxOccurs="unbounded"/&gt;
 *         &lt;group ref="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamGroup" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="dBSequence_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="passThreshold" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProteinDetectionHypothesisType", propOrder = {
    "peptideHypothesis",
    "paramGroup"
})
public class ProteinDetectionHypothesisType
    extends IdentifiableType
{

    @XmlElement(name = "PeptideHypothesis", required = true)
    protected List<PeptideHypothesisType> peptideHypothesis;
    @XmlElements({
        @XmlElement(name = "cvParam", type = CVParamType.class),
        @XmlElement(name = "userParam", type = UserParamType.class)
    })
    protected List<AbstractParamType> paramGroup;
    @XmlAttribute(name = "dBSequence_ref", required = true)
    protected String dbSequenceRef;
    @XmlAttribute(name = "passThreshold", required = true)
    protected boolean passThreshold;

    /**
     * Gets the value of the peptideHypothesis property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the peptideHypothesis property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeptideHypothesis().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeptideHypothesisType }
     *
     *
     */
    public List<PeptideHypothesisType> getPeptideHypothesis() {
        if (peptideHypothesis == null) {
            peptideHypothesis = new ArrayList<PeptideHypothesisType>(1);
        }
        return this.peptideHypothesis;
    }

    /**
     * Scores or parameters associated with this ProteinDetectionHypothesis e.g. p-value Gets the value of the paramGroup property.
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
     * Gets the value of the dbSequenceRef property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDBSequenceRef() {
        return dbSequenceRef;
    }

    /**
     * Sets the value of the dbSequenceRef property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDBSequenceRef(String value) {
        this.dbSequenceRef = value;
    }

    /**
     * Gets the value of the passThreshold property.
     *
     */
    public boolean isPassThreshold() {
        return passThreshold;
    }

    /**
     * Sets the value of the passThreshold property.
     *
     */
    public void setPassThreshold(boolean value) {
        this.passThreshold = value;
    }

}
