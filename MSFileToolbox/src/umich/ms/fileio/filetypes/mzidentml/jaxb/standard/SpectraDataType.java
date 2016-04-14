
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A data set containing spectra data (consisting of one or more spectra). 
 * 
 * <p>Java class for SpectraDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SpectraDataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}ExternalDataType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SpectrumIDFormat" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SpectrumIDFormatType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpectraDataType", propOrder = {
    "spectrumIDFormat"
})
public class SpectraDataType
    extends ExternalDataType
{

    @XmlElement(name = "SpectrumIDFormat", required = true)
    protected SpectrumIDFormatType spectrumIDFormat;

    /**
     * Gets the value of the spectrumIDFormat property.
     * 
     * @return
     *     possible object is
     *     {@link SpectrumIDFormatType }
     *     
     */
    public SpectrumIDFormatType getSpectrumIDFormat() {
        return spectrumIDFormat;
    }

    /**
     * Sets the value of the spectrumIDFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpectrumIDFormatType }
     *     
     */
    public void setSpectrumIDFormat(SpectrumIDFormatType value) {
        this.spectrumIDFormat = value;
    }

}
