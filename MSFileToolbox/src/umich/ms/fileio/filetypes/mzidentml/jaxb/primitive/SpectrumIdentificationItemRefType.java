
package umich.ms.fileio.filetypes.mzidentml.jaxb.primitive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Reference(s) to the SpectrumIdentificationItem element(s) that support the given PeptideEvidence element. Using these references it is possible to indicate which spectra were actually accepted as evidence for this peptide identification in the given protein.
 * 
 * <p>Java class for SpectrumIdentificationItemRefType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SpectrumIdentificationItemRefType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="spectrumIdentificationItem_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpectrumIdentificationItemRefType")
public class SpectrumIdentificationItemRefType {

    @XmlAttribute(name = "spectrumIdentificationItem_ref", required = true)
    protected String spectrumIdentificationItemRef;

    /**
     * Gets the value of the spectrumIdentificationItemRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpectrumIdentificationItemRef() {
        return spectrumIdentificationItemRef;
    }

    /**
     * Sets the value of the spectrumIdentificationItemRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpectrumIdentificationItemRef(String value) {
        this.spectrumIdentificationItemRef = value;
    }

}
