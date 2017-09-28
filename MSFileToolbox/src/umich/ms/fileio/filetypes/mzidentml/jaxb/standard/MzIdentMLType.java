
/*
 * Copyright (c) 2017 Dmitry Avtonomov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * The upper-most hierarchy level of mzIdentML with sub-containers for example describing software, protocols and search results (spectrum identifications or protein detection results). 
 * 
 * <p>Java class for MzIdentMLType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MzIdentMLType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType">
 *       &lt;sequence>
 *         &lt;element name="cvList" type="{http://psidev.info/psi/pi/mzIdentML/1.2}CVListType"/>
 *         &lt;element name="AnalysisSoftwareList" type="{http://psidev.info/psi/pi/mzIdentML/1.2}AnalysisSoftwareListType" minOccurs="0"/>
 *         &lt;element name="Provider" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ProviderType" minOccurs="0"/>
 *         &lt;element name="AuditCollection" type="{http://psidev.info/psi/pi/mzIdentML/1.2}AuditCollectionType" minOccurs="0"/>
 *         &lt;element name="AnalysisSampleCollection" type="{http://psidev.info/psi/pi/mzIdentML/1.2}AnalysisSampleCollectionType" minOccurs="0"/>
 *         &lt;element name="SequenceCollection" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SequenceCollectionType" minOccurs="0"/>
 *         &lt;element name="AnalysisCollection" type="{http://psidev.info/psi/pi/mzIdentML/1.2}AnalysisCollectionType"/>
 *         &lt;element name="AnalysisProtocolCollection" type="{http://psidev.info/psi/pi/mzIdentML/1.2}AnalysisProtocolCollectionType"/>
 *         &lt;element name="DataCollection" type="{http://psidev.info/psi/pi/mzIdentML/1.2}DataCollectionType"/>
 *         &lt;element name="BibliographicReference" type="{http://psidev.info/psi/pi/mzIdentML/1.2}BibliographicReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="creationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="version" use="required" type="{http://psidev.info/psi/pi/mzIdentML/1.2}versionRegex" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "MzIdentML")
@XmlType(name = "MzIdentMLType", propOrder = {
    "cvList",
    "analysisSoftwareList",
    "provider",
    "auditCollection",
    "analysisSampleCollection",
    "sequenceCollection",
    "analysisCollection",
    "analysisProtocolCollection",
    "dataCollection",
    "bibliographicReference"
})
public class MzIdentMLType
    extends IdentifiableType
{

    @XmlElement(required = true)
    protected CVListType cvList;
    @XmlElement(name = "AnalysisSoftwareList")
    protected AnalysisSoftwareListType analysisSoftwareList;
    @XmlElement(name = "Provider")
    protected ProviderType provider;
    @XmlElement(name = "AuditCollection")
    protected AuditCollectionType auditCollection;
    @XmlElement(name = "AnalysisSampleCollection")
    protected AnalysisSampleCollectionType analysisSampleCollection;
    @XmlElement(name = "SequenceCollection")
    protected SequenceCollectionType sequenceCollection;
    @XmlElement(name = "AnalysisCollection", required = true)
    protected AnalysisCollectionType analysisCollection;
    @XmlElement(name = "AnalysisProtocolCollection", required = true)
    protected AnalysisProtocolCollectionType analysisProtocolCollection;
    @XmlElement(name = "DataCollection", required = true)
    protected DataCollectionType dataCollection;
    @XmlElement(name = "BibliographicReference")
    protected List<BibliographicReferenceType> bibliographicReference;
    @XmlAttribute(name = "creationDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;
    @XmlAttribute(name = "version", required = true)
    protected String version;

    /**
     * Gets the value of the cvList property.
     * 
     * @return
     *     possible object is
     *     {@link CVListType }
     *     
     */
    public CVListType getCvList() {
        return cvList;
    }

    /**
     * Sets the value of the cvList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CVListType }
     *     
     */
    public void setCvList(CVListType value) {
        this.cvList = value;
    }

    /**
     * Gets the value of the analysisSoftwareList property.
     * 
     * @return
     *     possible object is
     *     {@link AnalysisSoftwareListType }
     *     
     */
    public AnalysisSoftwareListType getAnalysisSoftwareList() {
        return analysisSoftwareList;
    }

    /**
     * Sets the value of the analysisSoftwareList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnalysisSoftwareListType }
     *     
     */
    public void setAnalysisSoftwareList(AnalysisSoftwareListType value) {
        this.analysisSoftwareList = value;
    }

    /**
     * Gets the value of the provider property.
     * 
     * @return
     *     possible object is
     *     {@link ProviderType }
     *     
     */
    public ProviderType getProvider() {
        return provider;
    }

    /**
     * Sets the value of the provider property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProviderType }
     *     
     */
    public void setProvider(ProviderType value) {
        this.provider = value;
    }

    /**
     * Gets the value of the auditCollection property.
     * 
     * @return
     *     possible object is
     *     {@link AuditCollectionType }
     *     
     */
    public AuditCollectionType getAuditCollection() {
        return auditCollection;
    }

    /**
     * Sets the value of the auditCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuditCollectionType }
     *     
     */
    public void setAuditCollection(AuditCollectionType value) {
        this.auditCollection = value;
    }

    /**
     * Gets the value of the analysisSampleCollection property.
     * 
     * @return
     *     possible object is
     *     {@link AnalysisSampleCollectionType }
     *     
     */
    public AnalysisSampleCollectionType getAnalysisSampleCollection() {
        return analysisSampleCollection;
    }

    /**
     * Sets the value of the analysisSampleCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnalysisSampleCollectionType }
     *     
     */
    public void setAnalysisSampleCollection(AnalysisSampleCollectionType value) {
        this.analysisSampleCollection = value;
    }

    /**
     * Gets the value of the sequenceCollection property.
     * 
     * @return
     *     possible object is
     *     {@link SequenceCollectionType }
     *     
     */
    public SequenceCollectionType getSequenceCollection() {
        return sequenceCollection;
    }

    /**
     * Sets the value of the sequenceCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link SequenceCollectionType }
     *     
     */
    public void setSequenceCollection(SequenceCollectionType value) {
        this.sequenceCollection = value;
    }

    /**
     * Gets the value of the analysisCollection property.
     * 
     * @return
     *     possible object is
     *     {@link AnalysisCollectionType }
     *     
     */
    public AnalysisCollectionType getAnalysisCollection() {
        return analysisCollection;
    }

    /**
     * Sets the value of the analysisCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnalysisCollectionType }
     *     
     */
    public void setAnalysisCollection(AnalysisCollectionType value) {
        this.analysisCollection = value;
    }

    /**
     * Gets the value of the analysisProtocolCollection property.
     * 
     * @return
     *     possible object is
     *     {@link AnalysisProtocolCollectionType }
     *     
     */
    public AnalysisProtocolCollectionType getAnalysisProtocolCollection() {
        return analysisProtocolCollection;
    }

    /**
     * Sets the value of the analysisProtocolCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnalysisProtocolCollectionType }
     *     
     */
    public void setAnalysisProtocolCollection(AnalysisProtocolCollectionType value) {
        this.analysisProtocolCollection = value;
    }

    /**
     * Gets the value of the dataCollection property.
     * 
     * @return
     *     possible object is
     *     {@link DataCollectionType }
     *     
     */
    public DataCollectionType getDataCollection() {
        return dataCollection;
    }

    /**
     * Sets the value of the dataCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataCollectionType }
     *     
     */
    public void setDataCollection(DataCollectionType value) {
        this.dataCollection = value;
    }

    /**
     * Gets the value of the bibliographicReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bibliographicReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBibliographicReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BibliographicReferenceType }
     * 
     * 
     */
    public List<BibliographicReferenceType> getBibliographicReference() {
        if (bibliographicReference == null) {
            bibliographicReference = new ArrayList<BibliographicReferenceType>(1);
        }
        return this.bibliographicReference;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
