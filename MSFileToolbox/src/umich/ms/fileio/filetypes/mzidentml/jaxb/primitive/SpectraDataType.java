
package umich.ms.fileio.filetypes.mzidentml.jaxb.primitive;

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
 * &lt;complexType name="SpectraDataType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}ExternalDataType">
 *       &lt;sequence>
 *         &lt;element name="SpectrumIDFormat" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SpectrumIDFormatType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
