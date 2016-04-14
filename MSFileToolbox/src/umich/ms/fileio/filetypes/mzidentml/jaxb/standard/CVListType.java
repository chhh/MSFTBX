
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The list of controlled vocabularies used in the file.
 * 
 * <p>Java class for CVListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CVListType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cv" type="{http://psidev.info/psi/pi/mzIdentML/1.2}cvType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CVListType", propOrder = {
    "cvs"
})
public class CVListType {

    @XmlElement(name = "cv", required = true)
    protected List<CvType> cvs;

    /**
     * Gets the value of the cvs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cvs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCvs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CvType }
     * 
     * 
     */
    public List<CvType> getCvs() {
        if (cvs == null) {
            cvs = new ArrayList<CvType>();
        }
        return this.cvs;
    }

}
