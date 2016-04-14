
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The software packages used to perform the analyses.
 * 			
 * 
 * <p>Java class for AnalysisSoftwareListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AnalysisSoftwareListType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AnalysisSoftware" type="{http://psidev.info/psi/pi/mzIdentML/1.2}AnalysisSoftwareType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalysisSoftwareListType", propOrder = {
    "analysisSoftwares"
})
public class AnalysisSoftwareListType {

    @XmlElement(name = "AnalysisSoftware", required = true)
    protected List<AnalysisSoftwareType> analysisSoftwares;

    /**
     * Gets the value of the analysisSoftwares property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the analysisSoftwares property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnalysisSoftwares().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnalysisSoftwareType }
     * 
     * 
     */
    public List<AnalysisSoftwareType> getAnalysisSoftwares() {
        if (analysisSoftwares == null) {
            analysisSoftwares = new ArrayList<AnalysisSoftwareType>();
        }
        return this.analysisSoftwares;
    }

}
