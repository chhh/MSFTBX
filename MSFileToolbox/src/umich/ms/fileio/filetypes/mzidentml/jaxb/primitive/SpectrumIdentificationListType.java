
package umich.ms.fileio.filetypes.mzidentml.jaxb.primitive;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * Represents the set of all search results from SpectrumIdentification.
 * 
 * <p>Java class for SpectrumIdentificationListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SpectrumIdentificationListType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType">
 *       &lt;sequence>
 *         &lt;element name="FragmentationTable" type="{http://psidev.info/psi/pi/mzIdentML/1.2}FragmentationTableType" minOccurs="0"/>
 *         &lt;element name="SpectrumIdentificationResult" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SpectrumIdentificationResultType" maxOccurs="unbounded"/>
 *         &lt;group ref="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamGroup" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="numSequencesSearched" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpectrumIdentificationListType", propOrder = {
    "fragmentationTable",
    "spectrumIdentificationResult",
    "paramGroup"
})
public class SpectrumIdentificationListType
    extends IdentifiableType
{

    @XmlElement(name = "FragmentationTable")
    protected FragmentationTableType fragmentationTable;
    @XmlElement(name = "SpectrumIdentificationResult", required = true)
    protected List<SpectrumIdentificationResultType> spectrumIdentificationResult;
    @XmlElements({
        @XmlElement(name = "cvParam", type = CVParamType.class),
        @XmlElement(name = "userParam", type = UserParamType.class)
    })
    protected List<AbstractParamType> paramGroup;
    @XmlAttribute(name = "numSequencesSearched")
    protected long numSequencesSearched;

    /**
     * Gets the value of the fragmentationTable property.
     * 
     * @return
     *     possible object is
     *     {@link FragmentationTableType }
     *     
     */
    public FragmentationTableType getFragmentationTable() {
        return fragmentationTable;
    }

    /**
     * Sets the value of the fragmentationTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link FragmentationTableType }
     *     
     */
    public void setFragmentationTable(FragmentationTableType value) {
        this.fragmentationTable = value;
    }

    /**
     * Gets the value of the spectrumIdentificationResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spectrumIdentificationResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpectrumIdentificationResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpectrumIdentificationResultType }
     * 
     * 
     */
    public List<SpectrumIdentificationResultType> getSpectrumIdentificationResult() {
        if (spectrumIdentificationResult == null) {
            spectrumIdentificationResult = new ArrayList<SpectrumIdentificationResultType>();
        }
        return this.spectrumIdentificationResult;
    }

    /**
     * Scores or output parameters associated with the SpectrumIdentificationList.Gets the value of the paramGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paramGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParamGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CVParamType }
     * {@link UserParamType }
     * 
     * 
     */
    public List<AbstractParamType> getParamGroup() {
        if (paramGroup == null) {
            paramGroup = new ArrayList<AbstractParamType>();
        }
        return this.paramGroup;
    }

    /**
     * Gets the value of the numSequencesSearched property.
     * 
     */
    public long getNumSequencesSearched() {
        return numSequencesSearched;
    }

    /**
     * Sets the value of the numSequencesSearched property.
     * 
     */
    public void setNumSequencesSearched(long value) {
        this.numSequencesSearched = value;
    }

}
