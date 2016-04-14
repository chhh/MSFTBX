
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Other classes in the model can be specified as sub-classes, inheriting from Identifiable. Identifiable gives classes a unique identifier within the scope and a name that need not be unique.
 * 
 * <p>Java class for IdentifiableType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdentifiableType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentifiableType")
@XmlSeeAlso({
    MzIdentMLType.class,
    TranslationTableType.class,
    SpectrumIdentificationProtocolType.class,
    MeasureType.class,
    SpectrumIdentificationListType.class,
    SpectrumIdentificationItemType.class,
    SpectrumIdentificationResultType.class,
    ProteinDetectionProtocolType.class,
    ProteinDetectionListType.class,
    ProteinDetectionHypothesisType.class,
    ProteinAmbiguityGroupType.class,
    PeptideType.class,
    AnalysisSoftwareType.class,
    EnzymeType.class,
    MassTableType.class,
    PeptideEvidenceType.class,
    DBSequenceType.class,
    SampleType.class,
    ExternalDataType.class,
    AbstractContactType.class,
    BibliographicReferenceType.class,
    ProtocolApplicationType.class,
    ProviderType.class
})
public abstract class IdentifiableType {

    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
