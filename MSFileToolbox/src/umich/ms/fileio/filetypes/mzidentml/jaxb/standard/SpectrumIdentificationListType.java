
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

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
 * &lt;complexType name="SpectrumIdentificationListType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FragmentationTable" type="{http://psidev.info/psi/pi/mzIdentML/1.2}FragmentationTableType" minOccurs="0"/&gt;
 *         &lt;element name="SpectrumIdentificationResult" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SpectrumIdentificationResultType" maxOccurs="unbounded"/&gt;
 *         &lt;group ref="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamGroup" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="numSequencesSearched" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpectrumIdentificationListType", propOrder = {
    "fragmentationTable",
    "spectrumIdentificationResults",
    "cvParamsAndUserParams"
})
public class SpectrumIdentificationListType
    extends IdentifiableType
{

    @XmlElement(name = "FragmentationTable")
    protected FragmentationTableType fragmentationTable;
    @XmlElement(name = "SpectrumIdentificationResult", required = true)
    protected List<SpectrumIdentificationResultType> spectrumIdentificationResults;
    @XmlElements({
        @XmlElement(name = "cvParam", type = CVParamType.class),
        @XmlElement(name = "userParam", type = UserParamType.class)
    })
    protected List<AbstractParamType> cvParamsAndUserParams;
    @XmlAttribute(name = "numSequencesSearched")
    protected Long numSequencesSearched;

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
     * Gets the value of the spectrumIdentificationResults property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spectrumIdentificationResults property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpectrumIdentificationResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpectrumIdentificationResultType }
     * 
     * 
     */
    public List<SpectrumIdentificationResultType> getSpectrumIdentificationResults() {
        if (spectrumIdentificationResults == null) {
            spectrumIdentificationResults = new ArrayList<SpectrumIdentificationResultType>();
        }
        return this.spectrumIdentificationResults;
    }

    /**
     * Gets the value of the cvParamsAndUserParams property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cvParamsAndUserParams property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCvParamsAndUserParams().add(newItem);
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
    public List<AbstractParamType> getCvParamsAndUserParams() {
        if (cvParamsAndUserParams == null) {
            cvParamsAndUserParams = new ArrayList<AbstractParamType>();
        }
        return this.cvParamsAndUserParams;
    }

    /**
     * Gets the value of the numSequencesSearched property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumSequencesSearched() {
        return numSequencesSearched;
    }

    /**
     * Sets the value of the numSequencesSearched property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumSequencesSearched(Long value) {
        this.numSequencesSearched = value;
    }

}
