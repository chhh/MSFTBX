
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The inputs to the analyses including the databases searched, the spectral data and the source file converted to mzIdentML. 
 * 
 * <p>Java class for InputsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InputsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SourceFile" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SourceFileType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SearchDatabase" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SearchDatabaseType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SpectraData" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SpectraDataType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InputsType", propOrder = {
    "sourceFiles",
    "searchDatabases",
    "spectraDatas"
})
public class InputsType {

    @XmlElement(name = "SourceFile")
    protected List<SourceFileType> sourceFiles;
    @XmlElement(name = "SearchDatabase")
    protected List<SearchDatabaseType> searchDatabases;
    @XmlElement(name = "SpectraData", required = true)
    protected List<SpectraDataType> spectraDatas;

    /**
     * Gets the value of the sourceFiles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sourceFiles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSourceFiles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SourceFileType }
     * 
     * 
     */
    public List<SourceFileType> getSourceFiles() {
        if (sourceFiles == null) {
            sourceFiles = new ArrayList<SourceFileType>();
        }
        return this.sourceFiles;
    }

    /**
     * Gets the value of the searchDatabases property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchDatabases property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchDatabases().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchDatabaseType }
     * 
     * 
     */
    public List<SearchDatabaseType> getSearchDatabases() {
        if (searchDatabases == null) {
            searchDatabases = new ArrayList<SearchDatabaseType>();
        }
        return this.searchDatabases;
    }

    /**
     * Gets the value of the spectraDatas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spectraDatas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpectraDatas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpectraDataType }
     * 
     * 
     */
    public List<SpectraDataType> getSpectraDatas() {
        if (spectraDatas == null) {
            spectraDatas = new ArrayList<SpectraDataType>();
        }
        return this.spectraDatas;
    }

}
