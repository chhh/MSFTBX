
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
 * PeptideEvidence links a specific Peptide element to a specific position in a DBSequence. There must only be one PeptideEvidence item per Peptide-to-DBSequence-position. 
 * 
 * <p>Java class for PeptideEvidenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PeptideEvidenceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType"&gt;
 *       &lt;sequence&gt;
 *         &lt;group ref="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamGroup" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="dBSequence_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="peptide_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="start" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="end" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="pre"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;pattern value="[ABCDEFGHIJKLMNOPQRSTUVWXYZ?\-]{1}"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="post"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;pattern value="[ABCDEFGHIJKLMNOPQRSTUVWXYZ?\-]{1}"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="translationTable_ref" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="frame" type="{http://psidev.info/psi/pi/mzIdentML/1.2}allowed_frames" /&gt;
 *       &lt;attribute name="isDecoy" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeptideEvidenceType", propOrder = {
    "cvParamsAndUserParams"
})
public class PeptideEvidenceType
    extends IdentifiableType
{

    @XmlElements({
        @XmlElement(name = "cvParam", type = CVParamType.class),
        @XmlElement(name = "userParam", type = UserParamType.class)
    })
    protected List<AbstractParamType> cvParamsAndUserParams;
    @XmlAttribute(name = "dBSequence_ref", required = true)
    protected String dbSequenceRef;
    @XmlAttribute(name = "peptide_ref", required = true)
    protected String peptideRef;
    @XmlAttribute(name = "start")
    protected Integer start;
    @XmlAttribute(name = "end")
    protected Integer end;
    @XmlAttribute(name = "pre")
    protected String pre;
    @XmlAttribute(name = "post")
    protected String post;
    @XmlAttribute(name = "translationTable_ref")
    protected String translationTableRef;
    @XmlAttribute(name = "frame")
    protected Integer frame;
    @XmlAttribute(name = "isDecoy")
    protected Boolean isDecoy;

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
     * Gets the value of the peptideRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeptideRef() {
        return peptideRef;
    }

    /**
     * Sets the value of the peptideRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeptideRef(String value) {
        this.peptideRef = value;
    }

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStart(Integer value) {
        this.start = value;
    }

    /**
     * Gets the value of the end property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEnd(Integer value) {
        this.end = value;
    }

    /**
     * Gets the value of the pre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPre() {
        return pre;
    }

    /**
     * Sets the value of the pre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPre(String value) {
        this.pre = value;
    }

    /**
     * Gets the value of the post property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPost() {
        return post;
    }

    /**
     * Sets the value of the post property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPost(String value) {
        this.post = value;
    }

    /**
     * Gets the value of the translationTableRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranslationTableRef() {
        return translationTableRef;
    }

    /**
     * Sets the value of the translationTableRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranslationTableRef(String value) {
        this.translationTableRef = value;
    }

    /**
     * Gets the value of the frame property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFrame() {
        return frame;
    }

    /**
     * Sets the value of the frame property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFrame(Integer value) {
        this.frame = value;
    }

    /**
     * Gets the value of the isDecoy property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsDecoy() {
        if (isDecoy == null) {
            return false;
        } else {
            return isDecoy;
        }
    }

    /**
     * Sets the value of the isDecoy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDecoy(Boolean value) {
        this.isDecoy = value;
    }

}
