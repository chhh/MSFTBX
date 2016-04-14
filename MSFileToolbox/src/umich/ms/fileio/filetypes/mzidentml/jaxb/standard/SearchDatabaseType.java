
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A database for searching mass spectra. Examples include a set of amino acid sequence entries, nucleotide databases (e.g. 6 frame translated) or annotated spectra libraries. 
 * 
 * <p>Java class for SearchDatabaseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchDatabaseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}ExternalDataType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DatabaseName" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamType"/&gt;
 *         &lt;element name="cvParam" type="{http://psidev.info/psi/pi/mzIdentML/1.2}CVParamType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="releaseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *       &lt;attribute name="numDatabaseSequences" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="numResidues" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchDatabaseType", propOrder = {
    "databaseName",
    "cvParams"
})
public class SearchDatabaseType
    extends ExternalDataType
{

    @XmlElement(name = "DatabaseName", required = true)
    protected ParamType databaseName;
    @XmlElement(name = "cvParam")
    protected List<CVParamType> cvParams;
    @XmlAttribute(name = "version")
    protected String version;
    @XmlAttribute(name = "releaseDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar releaseDate;
    @XmlAttribute(name = "numDatabaseSequences")
    protected Long numDatabaseSequences;
    @XmlAttribute(name = "numResidues")
    protected Long numResidues;

    /**
     * Gets the value of the databaseName property.
     * 
     * @return
     *     possible object is
     *     {@link ParamType }
     *     
     */
    public ParamType getDatabaseName() {
        return databaseName;
    }

    /**
     * Sets the value of the databaseName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamType }
     *     
     */
    public void setDatabaseName(ParamType value) {
        this.databaseName = value;
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
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the releaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets the value of the releaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReleaseDate(XMLGregorianCalendar value) {
        this.releaseDate = value;
    }

    /**
     * Gets the value of the numDatabaseSequences property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumDatabaseSequences() {
        return numDatabaseSequences;
    }

    /**
     * Sets the value of the numDatabaseSequences property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumDatabaseSequences(Long value) {
        this.numDatabaseSequences = value;
    }

    /**
     * Gets the value of the numResidues property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumResidues() {
        return numResidues;
    }

    /**
     * Sets the value of the numResidues property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumResidues(Long value) {
        this.numResidues = value;
    }

}
