
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The analyses performed to get the results, which map the input and output data sets. Analyses are for example: SpectrumIdentification (resulting in peptides) or ProteinDetection (assemble proteins from peptides).
 *
 * <p>Java class for AnalysisCollectionType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AnalysisCollectionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SpectrumIdentification" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SpectrumIdentificationType" maxOccurs="unbounded"/&gt;
 *         &lt;element name="ProteinDetection" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ProteinDetectionType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalysisCollectionType", propOrder = {
    "spectrumIdentification",
    "proteinDetection"
})
public class AnalysisCollectionType {

    @XmlElement(name = "SpectrumIdentification", required = true)
    protected List<SpectrumIdentificationType> spectrumIdentification;
    @XmlElement(name = "ProteinDetection")
    protected ProteinDetectionType proteinDetection;

    /**
     * Gets the value of the spectrumIdentification property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spectrumIdentification property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpectrumIdentification().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpectrumIdentificationType }
     *
     *
     */
    public List<SpectrumIdentificationType> getSpectrumIdentification() {
        if (spectrumIdentification == null) {
            spectrumIdentification = new ArrayList<SpectrumIdentificationType>(1);
        }
        return this.spectrumIdentification;
    }

    /**
     * Gets the value of the proteinDetection property.
     *
     * @return
     *     possible object is
     *     {@link ProteinDetectionType }
     *
     */
    public ProteinDetectionType getProteinDetection() {
        return proteinDetection;
    }

    /**
     * Sets the value of the proteinDetection property.
     *
     * @param value
     *     allowed object is
     *     {@link ProteinDetectionType }
     *
     */
    public void setProteinDetection(ProteinDetectionType value) {
        this.proteinDetection = value;
    }

}
