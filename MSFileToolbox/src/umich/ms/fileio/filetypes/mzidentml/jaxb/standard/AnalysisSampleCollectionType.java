
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The samples analysed can optionally be recorded using CV terms for descriptions. If a composite sample has been analysed, the subsample association can be used to build a hierarchical description.
 *
 * <p>Java class for AnalysisSampleCollectionType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AnalysisSampleCollectionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Sample" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SampleType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalysisSampleCollectionType", propOrder = {
    "sample"
})
public class AnalysisSampleCollectionType {

    @XmlElement(name = "Sample", required = true)
    protected List<SampleType> sample;

    /**
     * Gets the value of the sample property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sample property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSample().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SampleType }
     *
     *
     */
    public List<SampleType> getSample() {
        if (sample == null) {
            sample = new ArrayList<SampleType>(1);
        }
        return this.sample;
    }

}
