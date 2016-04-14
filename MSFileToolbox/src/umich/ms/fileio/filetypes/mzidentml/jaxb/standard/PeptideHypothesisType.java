
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Peptide evidence on which this ProteinHypothesis is based by reference to a PeptideEvidence element.
 *
 * <p>Java class for PeptideHypothesisType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PeptideHypothesisType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SpectrumIdentificationItemRef" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SpectrumIdentificationItemRefType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="peptideEvidence_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeptideHypothesisType", propOrder = {
    "spectrumIdentificationItemRef"
})
public class PeptideHypothesisType {

    @XmlElement(name = "SpectrumIdentificationItemRef", required = true)
    protected List<SpectrumIdentificationItemRefType> spectrumIdentificationItemRef;
    @XmlAttribute(name = "peptideEvidence_ref", required = true)
    protected String peptideEvidenceRef;

    /**
     * Gets the value of the spectrumIdentificationItemRef property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spectrumIdentificationItemRef property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpectrumIdentificationItemRef().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpectrumIdentificationItemRefType }
     *
     *
     */
    public List<SpectrumIdentificationItemRefType> getSpectrumIdentificationItemRef() {
        if (spectrumIdentificationItemRef == null) {
            spectrumIdentificationItemRef = new ArrayList<SpectrumIdentificationItemRefType>(1);
        }
        return this.spectrumIdentificationItemRef;
    }

    /**
     * Gets the value of the peptideEvidenceRef property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPeptideEvidenceRef() {
        return peptideEvidenceRef;
    }

    /**
     * Sets the value of the peptideEvidenceRef property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPeptideEvidenceRef(String value) {
        this.peptideEvidenceRef = value;
    }

}
