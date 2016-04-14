
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * References to the individual component samples within a mixed parent sample. 
 * 
 * <p>Java class for SubSampleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubSampleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="sample_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubSampleType")
public class SubSampleType {

    @XmlAttribute(name = "sample_ref", required = true)
    protected String sampleRef;

    /**
     * Gets the value of the sampleRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSampleRef() {
        return sampleRef;
    }

    /**
     * Sets the value of the sampleRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSampleRef(String value) {
        this.sampleRef = value;
    }

}
