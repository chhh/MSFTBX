
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
 * A database sequence from the specified SearchDatabase (nucleic acid or amino acid). If the sequence is nucleic acid, the source nucleic acid sequence
 * should be given in the seq attribute rather than a translated sequence.
 *
 * <p>Java class for DBSequenceType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DBSequenceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Seq" type="{http://psidev.info/psi/pi/mzIdentML/1.2}sequence" minOccurs="0"/&gt;
 *         &lt;group ref="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamGroup" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="searchDatabase_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="accession" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DBSequenceType", propOrder = {
    "seq",
    "paramGroup"
})
public class DBSequenceType
    extends IdentifiableType
{

    @XmlElement(name = "Seq")
    protected String seq;
    @XmlElements({
        @XmlElement(name = "cvParam", type = CVParamType.class),
        @XmlElement(name = "userParam", type = UserParamType.class)
    })
    protected List<AbstractParamType> paramGroup;
    @XmlAttribute(name = "length")
    protected Integer length;
    @XmlAttribute(name = "searchDatabase_ref", required = true)
    protected String searchDatabaseRef;
    @XmlAttribute(name = "accession", required = true)
    protected String accession;

    /**
     * Gets the value of the seq property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSeq() {
        return seq;
    }

    /**
     * Sets the value of the seq property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSeq(String value) {
        this.seq = value;
    }

    /**
     * Additional descriptors for the sequence, such as taxon, description line etc.Gets the value of the paramGroup property.
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
     * Gets the value of the length property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setLength(Integer value) {
        this.length = value;
    }

    /**
     * Gets the value of the searchDatabaseRef property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSearchDatabaseRef() {
        return searchDatabaseRef;
    }

    /**
     * Sets the value of the searchDatabaseRef property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSearchDatabaseRef(String value) {
        this.searchDatabaseRef = value;
    }

    /**
     * Gets the value of the accession property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAccession() {
        return accession;
    }

    /**
     * Sets the value of the accession property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAccession(String value) {
        this.accession = value;
    }

}
