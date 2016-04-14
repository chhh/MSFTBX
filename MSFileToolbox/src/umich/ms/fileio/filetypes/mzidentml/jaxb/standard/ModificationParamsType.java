
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The specification of static/variable modifications (e.g. Oxidation of Methionine) that are to be considered in the spectra search.
 *
 * <p>Java class for ModificationParamsType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ModificationParamsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SearchModification" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SearchModificationType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModificationParamsType", propOrder = {
    "searchModification"
})
public class ModificationParamsType {

    @XmlElement(name = "SearchModification", required = true)
    protected List<SearchModificationType> searchModification;

    /**
     * Gets the value of the searchModification property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchModification property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchModification().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchModificationType }
     *
     *
     */
    public List<SearchModificationType> getSearchModification() {
        if (searchModification == null) {
            searchModification = new ArrayList<SearchModificationType>(1);
        }
        return this.searchModification;
    }

}
