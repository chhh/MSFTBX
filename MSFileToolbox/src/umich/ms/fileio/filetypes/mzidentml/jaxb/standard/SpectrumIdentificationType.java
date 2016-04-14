
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * An Analysis which tries to identify peptides in input spectra, referencing the database searched, the input spectra, the output results and the protocol that is run. 
 * 
 * <p>Java class for SpectrumIdentificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SpectrumIdentificationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}ProtocolApplicationType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InputSpectra" type="{http://psidev.info/psi/pi/mzIdentML/1.2}InputSpectraType" maxOccurs="unbounded"/&gt;
 *         &lt;element name="SearchDatabaseRef" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SearchDatabaseRefType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="spectrumIdentificationProtocol_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="spectrumIdentificationList_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpectrumIdentificationType", propOrder = {
    "inputSpectras",
    "searchDatabaseReves"
})
public class SpectrumIdentificationType
    extends ProtocolApplicationType
{

    @XmlElement(name = "InputSpectra", required = true)
    protected List<InputSpectraType> inputSpectras;
    @XmlElement(name = "SearchDatabaseRef", required = true)
    protected List<SearchDatabaseRefType> searchDatabaseReves;
    @XmlAttribute(name = "spectrumIdentificationProtocol_ref", required = true)
    protected String spectrumIdentificationProtocolRef;
    @XmlAttribute(name = "spectrumIdentificationList_ref", required = true)
    protected String spectrumIdentificationListRef;

    /**
     * Gets the value of the inputSpectras property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inputSpectras property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInputSpectras().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InputSpectraType }
     * 
     * 
     */
    public List<InputSpectraType> getInputSpectras() {
        if (inputSpectras == null) {
            inputSpectras = new ArrayList<InputSpectraType>();
        }
        return this.inputSpectras;
    }

    /**
     * Gets the value of the searchDatabaseReves property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchDatabaseReves property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchDatabaseReves().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchDatabaseRefType }
     * 
     * 
     */
    public List<SearchDatabaseRefType> getSearchDatabaseReves() {
        if (searchDatabaseReves == null) {
            searchDatabaseReves = new ArrayList<SearchDatabaseRefType>();
        }
        return this.searchDatabaseReves;
    }

    /**
     * Gets the value of the spectrumIdentificationProtocolRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpectrumIdentificationProtocolRef() {
        return spectrumIdentificationProtocolRef;
    }

    /**
     * Sets the value of the spectrumIdentificationProtocolRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpectrumIdentificationProtocolRef(String value) {
        this.spectrumIdentificationProtocolRef = value;
    }

    /**
     * Gets the value of the spectrumIdentificationListRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpectrumIdentificationListRef() {
        return spectrumIdentificationListRef;
    }

    /**
     * Sets the value of the spectrumIdentificationListRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpectrumIdentificationListRef(String value) {
        this.spectrumIdentificationListRef = value;
    }

}
