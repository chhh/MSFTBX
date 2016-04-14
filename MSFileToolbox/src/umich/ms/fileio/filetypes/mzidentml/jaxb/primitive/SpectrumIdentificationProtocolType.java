
package umich.ms.fileio.filetypes.mzidentml.jaxb.primitive;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The parameters and settings of a SpectrumIdentification analysis.
 *
 * <p>Java class for SpectrumIdentificationProtocolType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SpectrumIdentificationProtocolType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType">
 *       &lt;sequence>
 *         &lt;element name="SearchType" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamType"/>
 *         &lt;element name="AdditionalSearchParams" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamListType" minOccurs="0"/>
 *         &lt;element name="ModificationParams" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ModificationParamsType" minOccurs="0"/>
 *         &lt;element name="Enzymes" type="{http://psidev.info/psi/pi/mzIdentML/1.2}EnzymesType" minOccurs="0"/>
 *         &lt;element name="MassTable" type="{http://psidev.info/psi/pi/mzIdentML/1.2}MassTableType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FragmentTolerance" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ToleranceType" minOccurs="0"/>
 *         &lt;element name="ParentTolerance" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ToleranceType" minOccurs="0"/>
 *         &lt;element name="Threshold" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamListType"/>
 *         &lt;element name="DatabaseFilters" type="{http://psidev.info/psi/pi/mzIdentML/1.2}DatabaseFiltersType" minOccurs="0"/>
 *         &lt;element name="DatabaseTranslation" type="{http://psidev.info/psi/pi/mzIdentML/1.2}DatabaseTranslationType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="analysisSoftware_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpectrumIdentificationProtocolType", propOrder = {
    "searchType",
    "additionalSearchParams",
    "modificationParams",
    "enzymes",
    "massTable",
    "fragmentTolerance",
    "parentTolerance",
    "threshold",
    "databaseFilters",
    "databaseTranslation"
})
public class SpectrumIdentificationProtocolType
    extends IdentifiableType
{

    @XmlElement(name = "SearchType", required = true)
    protected ParamType searchType;
    @XmlElement(name = "AdditionalSearchParams")
    protected ParamListType additionalSearchParams;
    @XmlElement(name = "ModificationParams")
    protected ModificationParamsType modificationParams;
    @XmlElement(name = "Enzymes")
    protected EnzymesType enzymes;
    @XmlElement(name = "MassTable")
    protected List<MassTableType> massTable;
    @XmlElement(name = "FragmentTolerance")
    protected ToleranceType fragmentTolerance;
    @XmlElement(name = "ParentTolerance")
    protected ToleranceType parentTolerance;
    @XmlElement(name = "Threshold", required = true)
    protected ParamListType threshold;
    @XmlElement(name = "DatabaseFilters")
    protected DatabaseFiltersType databaseFilters;
    @XmlElement(name = "DatabaseTranslation")
    protected DatabaseTranslationType databaseTranslation;
    @XmlAttribute(name = "analysisSoftware_ref", required = true)
    protected String analysisSoftwareRef;

    /**
     * Gets the value of the searchType property.
     *
     * @return
     *     possible object is
     *     {@link ParamType }
     *
     */
    public ParamType getSearchType() {
        return searchType;
    }

    /**
     * Sets the value of the searchType property.
     *
     * @param value
     *     allowed object is
     *     {@link ParamType }
     *
     */
    public void setSearchType(ParamType value) {
        this.searchType = value;
    }

    /**
     * Gets the value of the additionalSearchParams property.
     *
     * @return
     *     possible object is
     *     {@link ParamListType }
     *
     */
    public ParamListType getAdditionalSearchParams() {
        return additionalSearchParams;
    }

    /**
     * Sets the value of the additionalSearchParams property.
     *
     * @param value
     *     allowed object is
     *     {@link ParamListType }
     *
     */
    public void setAdditionalSearchParams(ParamListType value) {
        this.additionalSearchParams = value;
    }

    /**
     * Gets the value of the modificationParams property.
     *
     * @return
     *     possible object is
     *     {@link ModificationParamsType }
     *
     */
    public ModificationParamsType getModificationParams() {
        return modificationParams;
    }

    /**
     * Sets the value of the modificationParams property.
     *
     * @param value
     *     allowed object is
     *     {@link ModificationParamsType }
     *
     */
    public void setModificationParams(ModificationParamsType value) {
        this.modificationParams = value;
    }

    /**
     * Gets the value of the enzymes property.
     *
     * @return
     *     possible object is
     *     {@link EnzymesType }
     *
     */
    public EnzymesType getEnzymes() {
        return enzymes;
    }

    /**
     * Sets the value of the enzymes property.
     *
     * @param value
     *     allowed object is
     *     {@link EnzymesType }
     *
     */
    public void setEnzymes(EnzymesType value) {
        this.enzymes = value;
    }

    /**
     * Gets the value of the massTable property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the massTable property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMassTable().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MassTableType }
     *
     *
     */
    public List<MassTableType> getMassTable() {
        if (massTable == null) {
            massTable = new ArrayList<MassTableType>(1);
        }
        return this.massTable;
    }

    /**
     * Gets the value of the fragmentTolerance property.
     *
     * @return
     *     possible object is
     *     {@link ToleranceType }
     *
     */
    public ToleranceType getFragmentTolerance() {
        return fragmentTolerance;
    }

    /**
     * Sets the value of the fragmentTolerance property.
     *
     * @param value
     *     allowed object is
     *     {@link ToleranceType }
     *
     */
    public void setFragmentTolerance(ToleranceType value) {
        this.fragmentTolerance = value;
    }

    /**
     * Gets the value of the parentTolerance property.
     *
     * @return
     *     possible object is
     *     {@link ToleranceType }
     *
     */
    public ToleranceType getParentTolerance() {
        return parentTolerance;
    }

    /**
     * Sets the value of the parentTolerance property.
     *
     * @param value
     *     allowed object is
     *     {@link ToleranceType }
     *
     */
    public void setParentTolerance(ToleranceType value) {
        this.parentTolerance = value;
    }

    /**
     * Gets the value of the threshold property.
     *
     * @return
     *     possible object is
     *     {@link ParamListType }
     *
     */
    public ParamListType getThreshold() {
        return threshold;
    }

    /**
     * Sets the value of the threshold property.
     *
     * @param value
     *     allowed object is
     *     {@link ParamListType }
     *
     */
    public void setThreshold(ParamListType value) {
        this.threshold = value;
    }

    /**
     * Gets the value of the databaseFilters property.
     *
     * @return
     *     possible object is
     *     {@link DatabaseFiltersType }
     *
     */
    public DatabaseFiltersType getDatabaseFilters() {
        return databaseFilters;
    }

    /**
     * Sets the value of the databaseFilters property.
     *
     * @param value
     *     allowed object is
     *     {@link DatabaseFiltersType }
     *
     */
    public void setDatabaseFilters(DatabaseFiltersType value) {
        this.databaseFilters = value;
    }

    /**
     * Gets the value of the databaseTranslation property.
     *
     * @return
     *     possible object is
     *     {@link DatabaseTranslationType }
     *
     */
    public DatabaseTranslationType getDatabaseTranslation() {
        return databaseTranslation;
    }

    /**
     * Sets the value of the databaseTranslation property.
     *
     * @param value
     *     allowed object is
     *     {@link DatabaseTranslationType }
     *
     */
    public void setDatabaseTranslation(DatabaseTranslationType value) {
        this.databaseTranslation = value;
    }

    /**
     * Gets the value of the analysisSoftwareRef property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAnalysisSoftwareRef() {
        return analysisSoftwareRef;
    }

    /**
     * Sets the value of the analysisSoftwareRef property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAnalysisSoftwareRef(String value) {
        this.analysisSoftwareRef = value;
    }

}
