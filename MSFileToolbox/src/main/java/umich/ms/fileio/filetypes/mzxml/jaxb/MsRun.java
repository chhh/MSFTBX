/*
 * Copyright 2016 Dmitry Avtonomov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package umich.ms.fileio.filetypes.mzxml.jaxb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.Duration;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parentFile" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anySimpleType">
 *                 &lt;attribute name="fileName" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                 &lt;attribute name="fileType" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;enumeration value="RAWData"/>
 *                       &lt;enumeration value="processedData"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="fileSha1" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;length value="40"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="msInstrument" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="msManufacturer">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType">
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="msModel" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
 *                   &lt;element name="msIonisation" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
 *                   &lt;element name="msMassAnalyzer">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType">
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="msDetector" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
 *                   &lt;element ref="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}software"/>
 *                   &lt;element name="msResolution" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType" minOccurs="0"/>
 *                   &lt;element ref="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}operator" minOccurs="0"/>
 *                   &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *                     &lt;element name="nameValue" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}namevalueType"/>
 *                     &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/sequence>
 *                 &lt;attribute name="msInstrumentID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="dataProcessing" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}software"/>
 *                   &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *                     &lt;element name="processingOperation" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}namevalueType"/>
 *                     &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/sequence>
 *                 &lt;attribute name="intensityCutoff" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="centroided" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="deisotoped" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="chargeDeconvoluted" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="spotIntegration" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="separation" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}separationTechnique" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="spotting" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="plate" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="plateManufacturer" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
 *                             &lt;element name="plateModel" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
 *                             &lt;element name="pattern" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="spottingPattern" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
 *                                       &lt;element name="orientation">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;attribute name="firstSpotID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="secondSpotID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="spot" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="maldiMatrix" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="spotID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="spotXPosition" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="spotYPosition" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="spotDiameter" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="plateID" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *                           &lt;attribute name="spotXCount" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *                           &lt;attribute name="spotYCount" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="robot" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="robotManufacturer" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
 *                             &lt;element name="robotModel" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="timePerSpot" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *                           &lt;attribute name="deadVolume" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}scan" maxOccurs="unbounded"/>
 *         &lt;element name="sha1" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="40"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="scanCount" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="startTime" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *       &lt;attribute name="endTime" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "parentFile",
    "msInstrument",
    "dataProcessing",
    "separation",
    "spotting",
    "scan",
    "sha1"
})
@XmlRootElement(name = "msRun")
public class MsRun {

  @XmlElement(required = true)
  protected List<MsRun.ParentFile> parentFile;
  protected List<MsRun.MsInstrument> msInstrument;
  @XmlElement(required = true)
  protected List<MsRun.DataProcessing> dataProcessing;
  protected MsRun.Separation separation;
  protected MsRun.Spotting spotting;
  @XmlElement(required = true)
  protected List<Scan> scan;
  protected String sha1;
  @XmlAttribute(name = "scanCount")
  @XmlSchemaType(name = "positiveInteger")
  protected BigInteger scanCount;
  @XmlAttribute(name = "startTime")
  protected String startTime;
  @XmlAttribute(name = "endTime")
  protected String endTime;

  /**
   * Gets the value of the parentFile property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the parentFile property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getParentFile().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link MsRun.ParentFile }
   */
  public List<MsRun.ParentFile> getParentFile() {
    if (parentFile == null) {
      parentFile = new ArrayList<MsRun.ParentFile>();
    }
    return this.parentFile;
  }

  /**
   * Gets the value of the msInstrument property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the msInstrument property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getMsInstrument().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link MsRun.MsInstrument }
   */
  public List<MsRun.MsInstrument> getMsInstrument() {
    if (msInstrument == null) {
      msInstrument = new ArrayList<MsRun.MsInstrument>();
    }
    return this.msInstrument;
  }

  /**
   * Gets the value of the dataProcessing property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the dataProcessing property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getDataProcessing().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link MsRun.DataProcessing }
   */
  public List<MsRun.DataProcessing> getDataProcessing() {
    if (dataProcessing == null) {
      dataProcessing = new ArrayList<MsRun.DataProcessing>();
    }
    return this.dataProcessing;
  }

  /**
   * Gets the value of the separation property.
   *
   * @return possible object is {@link MsRun.Separation }
   */
  public MsRun.Separation getSeparation() {
    return separation;
  }

  /**
   * Sets the value of the separation property.
   *
   * @param value allowed object is {@link MsRun.Separation }
   */
  public void setSeparation(MsRun.Separation value) {
    this.separation = value;
  }

  /**
   * Gets the value of the spotting property.
   *
   * @return possible object is {@link MsRun.Spotting }
   */
  public MsRun.Spotting getSpotting() {
    return spotting;
  }

  /**
   * Sets the value of the spotting property.
   *
   * @param value allowed object is {@link MsRun.Spotting }
   */
  public void setSpotting(MsRun.Spotting value) {
    this.spotting = value;
  }

  /**
   * Gets the value of the scan property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the scan property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getScan().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Scan }
   */
  public List<Scan> getScan() {
    if (scan == null) {
      scan = new ArrayList<Scan>();
    }
    return this.scan;
  }

  /**
   * Gets the value of the sha1 property.
   *
   * @return possible object is {@link String }
   */
  public String getSha1() {
    return sha1;
  }

  /**
   * Sets the value of the sha1 property.
   *
   * @param value allowed object is {@link String }
   */
  public void setSha1(String value) {
    this.sha1 = value;
  }

  /**
   * Gets the value of the scanCount property.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getScanCount() {
    return scanCount;
  }

  /**
   * Sets the value of the scanCount property.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setScanCount(BigInteger value) {
    this.scanCount = value;
  }

  /**
   * Gets the value of the startTime property.
   *
   * @return possible object is {@link Duration }
   */
  public String getStartTime() {
    return startTime;
  }

  /**
   * Sets the value of the startTime property.
   *
   * @param value allowed object is {@link Duration }
   */
  public void setStartTime(String value) {
    this.startTime = value;
  }

  /**
   * Gets the value of the endTime property.
   *
   * @return possible object is {@link Duration }
   */
  public String getEndTime() {
    return endTime;
  }

  /**
   * Sets the value of the endTime property.
   *
   * @param value allowed object is {@link Duration }
   */
  public void setEndTime(String value) {
    this.endTime = value;
  }


  /**
   * <p>Java class for anonymous complex type.
   *
   * <p>The following schema fragment specifies the expected content contained within this class.
   *
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element ref="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}software"/>
   *         &lt;sequence maxOccurs="unbounded" minOccurs="0">
   *           &lt;element name="processingOperation" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}namevalueType"/>
   *           &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;/sequence>
   *       &lt;/sequence>
   *       &lt;attribute name="intensityCutoff" type="{http://www.w3.org/2001/XMLSchema}float" />
   *       &lt;attribute name="centroided" type="{http://www.w3.org/2001/XMLSchema}boolean" />
   *       &lt;attribute name="deisotoped" type="{http://www.w3.org/2001/XMLSchema}boolean" />
   *       &lt;attribute name="chargeDeconvoluted" type="{http://www.w3.org/2001/XMLSchema}boolean" />
   *       &lt;attribute name="spotIntegration" type="{http://www.w3.org/2001/XMLSchema}boolean" />
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {
      "software",
      "processingOperationAndComment"
  })
  public static class DataProcessing {

    @XmlElement(required = true)
    protected Software software;
    @XmlElements({
        @XmlElement(name = "processingOperation", type = NamevalueType.class),
        @XmlElement(name = "comment", type = String.class)
    })
    protected List<Object> processingOperationAndComment;
    @XmlAttribute(name = "intensityCutoff")
    protected Float intensityCutoff;
    @XmlAttribute(name = "centroided")
    protected Boolean centroided;
    @XmlAttribute(name = "deisotoped")
    protected Boolean deisotoped;
    @XmlAttribute(name = "chargeDeconvoluted")
    protected Boolean chargeDeconvoluted;
    @XmlAttribute(name = "spotIntegration")
    protected Boolean spotIntegration;

    /**
     * Software used to convert the data. If data has been processed (e.g. profile > centroid) by
     * any additional progs these should be added too.
     *
     * @return possible object is {@link Software }
     */
    public Software getSoftware() {
      return software;
    }

    /**
     * Sets the value of the software property.
     *
     * @param value allowed object is {@link Software }
     */
    public void setSoftware(Software value) {
      this.software = value;
    }

    /**
     * Gets the value of the processingOperationAndComment property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the processingOperationAndComment property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessingOperationAndComment().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link NamevalueType } {@link String
     * }
     */
    public List<Object> getProcessingOperationAndComment() {
      if (processingOperationAndComment == null) {
        processingOperationAndComment = new ArrayList<Object>();
      }
      return this.processingOperationAndComment;
    }

    /**
     * Gets the value of the intensityCutoff property.
     *
     * @return possible object is {@link Float }
     */
    public Float getIntensityCutoff() {
      return intensityCutoff;
    }

    /**
     * Sets the value of the intensityCutoff property.
     *
     * @param value allowed object is {@link Float }
     */
    public void setIntensityCutoff(Float value) {
      this.intensityCutoff = value;
    }

    /**
     * Gets the value of the centroided property.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isCentroided() {
      return centroided;
    }

    /**
     * Sets the value of the centroided property.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setCentroided(Boolean value) {
      this.centroided = value;
    }

    /**
     * Gets the value of the deisotoped property.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isDeisotoped() {
      return deisotoped;
    }

    /**
     * Sets the value of the deisotoped property.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setDeisotoped(Boolean value) {
      this.deisotoped = value;
    }

    /**
     * Gets the value of the chargeDeconvoluted property.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isChargeDeconvoluted() {
      return chargeDeconvoluted;
    }

    /**
     * Sets the value of the chargeDeconvoluted property.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setChargeDeconvoluted(Boolean value) {
      this.chargeDeconvoluted = value;
    }

    /**
     * Gets the value of the spotIntegration property.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isSpotIntegration() {
      return spotIntegration;
    }

    /**
     * Sets the value of the spotIntegration property.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setSpotIntegration(Boolean value) {
      this.spotIntegration = value;
    }

  }


  /**
   * <p>Java class for anonymous complex type.
   *
   * <p>The following schema fragment specifies the expected content contained within this class.
   *
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element name="msManufacturer">
   *           &lt;complexType>
   *             &lt;complexContent>
   *               &lt;extension base="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType">
   *               &lt;/extension>
   *             &lt;/complexContent>
   *           &lt;/complexType>
   *         &lt;/element>
   *         &lt;element name="msModel" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
   *         &lt;element name="msIonisation" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
   *         &lt;element name="msMassAnalyzer">
   *           &lt;complexType>
   *             &lt;complexContent>
   *               &lt;extension base="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType">
   *               &lt;/extension>
   *             &lt;/complexContent>
   *           &lt;/complexType>
   *         &lt;/element>
   *         &lt;element name="msDetector" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
   *         &lt;element ref="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}software"/>
   *         &lt;element name="msResolution" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType" minOccurs="0"/>
   *         &lt;element ref="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}operator" minOccurs="0"/>
   *         &lt;sequence maxOccurs="unbounded" minOccurs="0">
   *           &lt;element name="nameValue" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}namevalueType"/>
   *           &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;/sequence>
   *       &lt;/sequence>
   *       &lt;attribute name="msInstrumentID" type="{http://www.w3.org/2001/XMLSchema}int" />
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {
      "msManufacturer",
      "msModel",
      "msIonisation",
      "msMassAnalyzer",
      "msDetector",
      "software",
      "msResolution",
      "operator",
      "nameValueAndComment"
  })
  public static class MsInstrument {

    @XmlElement(required = true)
    protected MsRun.MsInstrument.MsManufacturer msManufacturer;
    @XmlElement(required = true)
    protected OntologyEntryType msModel;
    @XmlElement(required = true)
    protected OntologyEntryType msIonisation;
    @XmlElement(required = true)
    protected MsRun.MsInstrument.MsMassAnalyzer msMassAnalyzer;
    @XmlElement(required = true)
    protected OntologyEntryType msDetector;
    @XmlElement(required = true)
    protected Software software;
    protected OntologyEntryType msResolution;
    protected Operator operator;
    @XmlElements({
        @XmlElement(name = "nameValue", type = NamevalueType.class),
        @XmlElement(name = "comment", type = String.class)
    })
    protected List<Object> nameValueAndComment;
    /**
     * Even though according to schema this must be an integer, we've seen cases, where ProteoWizard
     * uses a String
     */
    @XmlAttribute(name = "msInstrumentID")
    protected String msInstrumentID;
    /**
     * This is not in mzXML 3.2 standard, but ProteoWizard version="2.2.3052" uses this attribute
     * instead of msInstrumentID
     */
    @XmlAttribute(name = "id")
    protected String stringInstrumentID;

    public String getStringInstrumentID() {
      return stringInstrumentID;
    }

    public void setStringInstrumentID(String stringInstrumentID) {
      this.stringInstrumentID = stringInstrumentID;
    }

    /**
     * Gets the value of the msManufacturer property.
     *
     * @return possible object is {@link MsRun.MsInstrument.MsManufacturer }
     */
    public MsRun.MsInstrument.MsManufacturer getMsManufacturer() {
      return msManufacturer;
    }

    /**
     * Sets the value of the msManufacturer property.
     *
     * @param value allowed object is {@link MsRun.MsInstrument.MsManufacturer }
     */
    public void setMsManufacturer(MsRun.MsInstrument.MsManufacturer value) {
      this.msManufacturer = value;
    }

    /**
     * Gets the value of the msModel property.
     *
     * @return possible object is {@link OntologyEntryType }
     */
    public OntologyEntryType getMsModel() {
      return msModel;
    }

    /**
     * Sets the value of the msModel property.
     *
     * @param value allowed object is {@link OntologyEntryType }
     */
    public void setMsModel(OntologyEntryType value) {
      this.msModel = value;
    }

    /**
     * Gets the value of the msIonisation property.
     *
     * @return possible object is {@link OntologyEntryType }
     */
    public OntologyEntryType getMsIonisation() {
      return msIonisation;
    }

    /**
     * Sets the value of the msIonisation property.
     *
     * @param value allowed object is {@link OntologyEntryType }
     */
    public void setMsIonisation(OntologyEntryType value) {
      this.msIonisation = value;
    }

    /**
     * Gets the value of the msMassAnalyzer property.
     *
     * @return possible object is {@link MsRun.MsInstrument.MsMassAnalyzer }
     */
    public MsRun.MsInstrument.MsMassAnalyzer getMsMassAnalyzer() {
      return msMassAnalyzer;
    }

    /**
     * Sets the value of the msMassAnalyzer property.
     *
     * @param value allowed object is {@link MsRun.MsInstrument.MsMassAnalyzer }
     */
    public void setMsMassAnalyzer(MsRun.MsInstrument.MsMassAnalyzer value) {
      this.msMassAnalyzer = value;
    }

    /**
     * Gets the value of the msDetector property.
     *
     * @return possible object is {@link OntologyEntryType }
     */
    public OntologyEntryType getMsDetector() {
      return msDetector;
    }

    /**
     * Sets the value of the msDetector property.
     *
     * @param value allowed object is {@link OntologyEntryType }
     */
    public void setMsDetector(OntologyEntryType value) {
      this.msDetector = value;
    }

    /**
     * Gets the value of the software property.
     *
     * @return possible object is {@link Software }
     */
    public Software getSoftware() {
      return software;
    }

    /**
     * Sets the value of the software property.
     *
     * @param value allowed object is {@link Software }
     */
    public void setSoftware(Software value) {
      this.software = value;
    }

    /**
     * Gets the value of the msResolution property.
     *
     * @return possible object is {@link OntologyEntryType }
     */
    public OntologyEntryType getMsResolution() {
      return msResolution;
    }

    /**
     * Sets the value of the msResolution property.
     *
     * @param value allowed object is {@link OntologyEntryType }
     */
    public void setMsResolution(OntologyEntryType value) {
      this.msResolution = value;
    }

    /**
     * Gets the value of the operator property.
     *
     * @return possible object is {@link Operator }
     */
    public Operator getOperator() {
      return operator;
    }

    /**
     * Sets the value of the operator property.
     *
     * @param value allowed object is {@link Operator }
     */
    public void setOperator(Operator value) {
      this.operator = value;
    }

    /**
     * Gets the value of the nameValueAndComment property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the nameValueAndComment property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNameValueAndComment().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link NamevalueType } {@link String
     * }
     */
    public List<Object> getNameValueAndComment() {
      if (nameValueAndComment == null) {
        nameValueAndComment = new ArrayList<Object>();
      }
      return this.nameValueAndComment;
    }

    /**
     * Gets the value of the msInstrumentID property.
     *
     * @return possible object is {@link Integer }
     */
    public String getMsInstrumentID() {
      return msInstrumentID;
    }

    /**
     * Sets the value of the msInstrumentID property.
     *
     * @param value allowed object is {@link Integer }
     */
    public void setMsInstrumentID(String value) {
      this.msInstrumentID = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType">
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class MsManufacturer
        extends OntologyEntryType {


    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType">
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class MsMassAnalyzer
        extends OntologyEntryType {


    }

  }


  /**
   * <p>Java class for anonymous complex type.
   *
   * <p>The following schema fragment specifies the expected content contained within this class.
   *
   * <pre>
   * &lt;complexType>
   *   &lt;simpleContent>
   *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anySimpleType">
   *       &lt;attribute name="fileName" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
   *       &lt;attribute name="fileType" use="required">
   *         &lt;simpleType>
   *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
   *             &lt;enumeration value="RAWData"/>
   *             &lt;enumeration value="processedData"/>
   *           &lt;/restriction>
   *         &lt;/simpleType>
   *       &lt;/attribute>
   *       &lt;attribute name="fileSha1" use="required">
   *         &lt;simpleType>
   *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
   *             &lt;length value="40"/>
   *           &lt;/restriction>
   *         &lt;/simpleType>
   *       &lt;/attribute>
   *     &lt;/extension>
   *   &lt;/simpleContent>
   * &lt;/complexType>
   * </pre>
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {
      "value"
  })
  public static class ParentFile {

    @XmlValue
    @XmlSchemaType(name = "anySimpleType")
    protected Object value;
    @XmlAttribute(name = "fileName", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String fileName;
    @XmlAttribute(name = "fileType", required = true)
    protected String fileType;
    @XmlAttribute(name = "fileSha1", required = true)
    protected String fileSha1;

    /**
     * Gets the value of the value property.
     *
     * @return possible object is {@link Object }
     */
    public Object getValue() {
      return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is {@link Object }
     */
    public void setValue(Object value) {
      this.value = value;
    }

    /**
     * Gets the value of the fileName property.
     *
     * @return possible object is {@link String }
     */
    public String getFileName() {
      return fileName;
    }

    /**
     * Sets the value of the fileName property.
     *
     * @param value allowed object is {@link String }
     */
    public void setFileName(String value) {
      this.fileName = value;
    }

    /**
     * Gets the value of the fileType property.
     *
     * @return possible object is {@link String }
     */
    public String getFileType() {
      return fileType;
    }

    /**
     * Sets the value of the fileType property.
     *
     * @param value allowed object is {@link String }
     */
    public void setFileType(String value) {
      this.fileType = value;
    }

    /**
     * Gets the value of the fileSha1 property.
     *
     * @return possible object is {@link String }
     */
    public String getFileSha1() {
      return fileSha1;
    }

    /**
     * Sets the value of the fileSha1 property.
     *
     * @param value allowed object is {@link String }
     */
    public void setFileSha1(String value) {
      this.fileSha1 = value;
    }

  }


  /**
   * <p>Java class for anonymous complex type.
   *
   * <p>The following schema fragment specifies the expected content contained within this class.
   *
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element ref="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}separationTechnique" maxOccurs="unbounded"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {
      "separationTechnique"
  })
  public static class Separation {

    @XmlElement(required = true)
    protected List<SeparationTechniqueType> separationTechnique;

    /**
     * Gets the value of the separationTechnique property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the separationTechnique property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSeparationTechnique().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link SeparationTechniqueType }
     */
    public List<SeparationTechniqueType> getSeparationTechnique() {
      if (separationTechnique == null) {
        separationTechnique = new ArrayList<SeparationTechniqueType>();
      }
      return this.separationTechnique;
    }

  }


  /**
   * <p>Java class for anonymous complex type.
   *
   * <p>The following schema fragment specifies the expected content contained within this class.
   *
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element name="plate" maxOccurs="unbounded">
   *           &lt;complexType>
   *             &lt;complexContent>
   *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                 &lt;sequence>
   *                   &lt;element name="plateManufacturer" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
   *                   &lt;element name="plateModel" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
   *                   &lt;element name="pattern" minOccurs="0">
   *                     &lt;complexType>
   *                       &lt;complexContent>
   *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                           &lt;sequence>
   *                             &lt;element name="spottingPattern" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
   *                             &lt;element name="orientation">
   *                               &lt;complexType>
   *                                 &lt;complexContent>
   *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                                     &lt;attribute name="firstSpotID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
   *                                     &lt;attribute name="secondSpotID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
   *                                   &lt;/restriction>
   *                                 &lt;/complexContent>
   *                               &lt;/complexType>
   *                             &lt;/element>
   *                           &lt;/sequence>
   *                         &lt;/restriction>
   *                       &lt;/complexContent>
   *                     &lt;/complexType>
   *                   &lt;/element>
   *                   &lt;element name="spot" maxOccurs="unbounded">
   *                     &lt;complexType>
   *                       &lt;complexContent>
   *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                           &lt;sequence>
   *                             &lt;element name="maldiMatrix" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
   *                           &lt;/sequence>
   *                           &lt;attribute name="spotID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
   *                           &lt;attribute name="spotXPosition" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
   *                           &lt;attribute name="spotYPosition" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
   *                           &lt;attribute name="spotDiameter" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
   *                         &lt;/restriction>
   *                       &lt;/complexContent>
   *                     &lt;/complexType>
   *                   &lt;/element>
   *                 &lt;/sequence>
   *                 &lt;attribute name="plateID" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
   *                 &lt;attribute name="spotXCount" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
   *                 &lt;attribute name="spotYCount" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
   *               &lt;/restriction>
   *             &lt;/complexContent>
   *           &lt;/complexType>
   *         &lt;/element>
   *         &lt;element name="robot" minOccurs="0">
   *           &lt;complexType>
   *             &lt;complexContent>
   *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                 &lt;sequence>
   *                   &lt;element name="robotManufacturer" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
   *                   &lt;element name="robotModel" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
   *                 &lt;/sequence>
   *                 &lt;attribute name="timePerSpot" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
   *                 &lt;attribute name="deadVolume" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
   *               &lt;/restriction>
   *             &lt;/complexContent>
   *           &lt;/complexType>
   *         &lt;/element>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {
      "plate",
      "robot"
  })
  public static class Spotting {

    @XmlElement(required = true)
    protected List<MsRun.Spotting.Plate> plate;
    protected MsRun.Spotting.Robot robot;

    /**
     * Gets the value of the plate property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the plate property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlate().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link MsRun.Spotting.Plate }
     */
    public List<MsRun.Spotting.Plate> getPlate() {
      if (plate == null) {
        plate = new ArrayList<MsRun.Spotting.Plate>();
      }
      return this.plate;
    }

    /**
     * Gets the value of the robot property.
     *
     * @return possible object is {@link MsRun.Spotting.Robot }
     */
    public MsRun.Spotting.Robot getRobot() {
      return robot;
    }

    /**
     * Sets the value of the robot property.
     *
     * @param value allowed object is {@link MsRun.Spotting.Robot }
     */
    public void setRobot(MsRun.Spotting.Robot value) {
      this.robot = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="plateManufacturer" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
     *         &lt;element name="plateModel" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
     *         &lt;element name="pattern" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="spottingPattern" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
     *                   &lt;element name="orientation">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="firstSpotID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="secondSpotID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="spot" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="maldiMatrix" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="spotID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="spotXPosition" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="spotYPosition" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="spotDiameter" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="plateID" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
     *       &lt;attribute name="spotXCount" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
     *       &lt;attribute name="spotYCount" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "plateManufacturer",
        "plateModel",
        "pattern",
        "spot"
    })
    public static class Plate {

      @XmlElement(required = true)
      protected OntologyEntryType plateManufacturer;
      @XmlElement(required = true)
      protected OntologyEntryType plateModel;
      protected MsRun.Spotting.Plate.Pattern pattern;
      @XmlElement(required = true)
      protected List<MsRun.Spotting.Plate.Spot> spot;
      @XmlAttribute(name = "plateID", required = true)
      @XmlSchemaType(name = "positiveInteger")
      protected BigInteger plateID;
      @XmlAttribute(name = "spotXCount", required = true)
      @XmlSchemaType(name = "positiveInteger")
      protected BigInteger spotXCount;
      @XmlAttribute(name = "spotYCount", required = true)
      @XmlSchemaType(name = "positiveInteger")
      protected BigInteger spotYCount;

      /**
       * Gets the value of the plateManufacturer property.
       *
       * @return possible object is {@link OntologyEntryType }
       */
      public OntologyEntryType getPlateManufacturer() {
        return plateManufacturer;
      }

      /**
       * Sets the value of the plateManufacturer property.
       *
       * @param value allowed object is {@link OntologyEntryType }
       */
      public void setPlateManufacturer(OntologyEntryType value) {
        this.plateManufacturer = value;
      }

      /**
       * Gets the value of the plateModel property.
       *
       * @return possible object is {@link OntologyEntryType }
       */
      public OntologyEntryType getPlateModel() {
        return plateModel;
      }

      /**
       * Sets the value of the plateModel property.
       *
       * @param value allowed object is {@link OntologyEntryType }
       */
      public void setPlateModel(OntologyEntryType value) {
        this.plateModel = value;
      }

      /**
       * Gets the value of the pattern property.
       *
       * @return possible object is {@link MsRun.Spotting.Plate.Pattern }
       */
      public MsRun.Spotting.Plate.Pattern getPattern() {
        return pattern;
      }

      /**
       * Sets the value of the pattern property.
       *
       * @param value allowed object is {@link MsRun.Spotting.Plate.Pattern }
       */
      public void setPattern(MsRun.Spotting.Plate.Pattern value) {
        this.pattern = value;
      }

      /**
       * Gets the value of the spot property.
       *
       * <p>
       * This accessor method returns a reference to the live list, not a snapshot. Therefore any
       * modification you make to the returned list will be present inside the JAXB object. This is
       * why there is not a <CODE>set</CODE> method for the spot property.
       *
       * <p>
       * For example, to add a new item, do as follows:
       * <pre>
       *    getSpot().add(newItem);
       * </pre>
       *
       *
       * <p>
       * Objects of the following type(s) are allowed in the list {@link MsRun.Spotting.Plate.Spot
       * }
       */
      public List<MsRun.Spotting.Plate.Spot> getSpot() {
        if (spot == null) {
          spot = new ArrayList<MsRun.Spotting.Plate.Spot>();
        }
        return this.spot;
      }

      /**
       * Gets the value of the plateID property.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getPlateID() {
        return plateID;
      }

      /**
       * Sets the value of the plateID property.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setPlateID(BigInteger value) {
        this.plateID = value;
      }

      /**
       * Gets the value of the spotXCount property.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getSpotXCount() {
        return spotXCount;
      }

      /**
       * Sets the value of the spotXCount property.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setSpotXCount(BigInteger value) {
        this.spotXCount = value;
      }

      /**
       * Gets the value of the spotYCount property.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getSpotYCount() {
        return spotYCount;
      }

      /**
       * Sets the value of the spotYCount property.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setSpotYCount(BigInteger value) {
        this.spotYCount = value;
      }


      /**
       * <p>Java class for anonymous complex type.
       *
       * <p>The following schema fragment specifies the expected content contained within this
       * class.
       *
       * <pre>
       * &lt;complexType>
       *   &lt;complexContent>
       *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *       &lt;sequence>
       *         &lt;element name="spottingPattern" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
       *         &lt;element name="orientation">
       *           &lt;complexType>
       *             &lt;complexContent>
       *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *                 &lt;attribute name="firstSpotID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
       *                 &lt;attribute name="secondSpotID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
       *               &lt;/restriction>
       *             &lt;/complexContent>
       *           &lt;/complexType>
       *         &lt;/element>
       *       &lt;/sequence>
       *     &lt;/restriction>
       *   &lt;/complexContent>
       * &lt;/complexType>
       * </pre>
       */
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name = "", propOrder = {
          "spottingPattern",
          "orientation"
      })
      public static class Pattern {

        @XmlElement(required = true)
        protected OntologyEntryType spottingPattern;
        @XmlElement(required = true)
        protected MsRun.Spotting.Plate.Pattern.Orientation orientation;

        /**
         * Gets the value of the spottingPattern property.
         *
         * @return possible object is {@link OntologyEntryType }
         */
        public OntologyEntryType getSpottingPattern() {
          return spottingPattern;
        }

        /**
         * Sets the value of the spottingPattern property.
         *
         * @param value allowed object is {@link OntologyEntryType }
         */
        public void setSpottingPattern(OntologyEntryType value) {
          this.spottingPattern = value;
        }

        /**
         * Gets the value of the orientation property.
         *
         * @return possible object is {@link MsRun.Spotting.Plate.Pattern.Orientation }
         */
        public MsRun.Spotting.Plate.Pattern.Orientation getOrientation() {
          return orientation;
        }

        /**
         * Sets the value of the orientation property.
         *
         * @param value allowed object is {@link MsRun.Spotting.Plate.Pattern.Orientation }
         */
        public void setOrientation(MsRun.Spotting.Plate.Pattern.Orientation value) {
          this.orientation = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this
         * class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="firstSpotID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="secondSpotID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Orientation {

          @XmlAttribute(name = "firstSpotID", required = true)
          protected String firstSpotID;
          @XmlAttribute(name = "secondSpotID", required = true)
          protected String secondSpotID;

          /**
           * Gets the value of the firstSpotID property.
           *
           * @return possible object is {@link String }
           */
          public String getFirstSpotID() {
            return firstSpotID;
          }

          /**
           * Sets the value of the firstSpotID property.
           *
           * @param value allowed object is {@link String }
           */
          public void setFirstSpotID(String value) {
            this.firstSpotID = value;
          }

          /**
           * Gets the value of the secondSpotID property.
           *
           * @return possible object is {@link String }
           */
          public String getSecondSpotID() {
            return secondSpotID;
          }

          /**
           * Sets the value of the secondSpotID property.
           *
           * @param value allowed object is {@link String }
           */
          public void setSecondSpotID(String value) {
            this.secondSpotID = value;
          }

        }

      }


      /**
       * <p>Java class for anonymous complex type.
       *
       * <p>The following schema fragment specifies the expected content contained within this
       * class.
       *
       * <pre>
       * &lt;complexType>
       *   &lt;complexContent>
       *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *       &lt;sequence>
       *         &lt;element name="maldiMatrix" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
       *       &lt;/sequence>
       *       &lt;attribute name="spotID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
       *       &lt;attribute name="spotXPosition" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
       *       &lt;attribute name="spotYPosition" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
       *       &lt;attribute name="spotDiameter" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
       *     &lt;/restriction>
       *   &lt;/complexContent>
       * &lt;/complexType>
       * </pre>
       */
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name = "", propOrder = {
          "maldiMatrix"
      })
      public static class Spot {

        @XmlElement(required = true)
        protected OntologyEntryType maldiMatrix;
        @XmlAttribute(name = "spotID", required = true)
        protected String spotID;
        @XmlAttribute(name = "spotXPosition", required = true)
        protected String spotXPosition;
        @XmlAttribute(name = "spotYPosition", required = true)
        protected String spotYPosition;
        @XmlAttribute(name = "spotDiameter")
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger spotDiameter;

        /**
         * Gets the value of the maldiMatrix property.
         *
         * @return possible object is {@link OntologyEntryType }
         */
        public OntologyEntryType getMaldiMatrix() {
          return maldiMatrix;
        }

        /**
         * Sets the value of the maldiMatrix property.
         *
         * @param value allowed object is {@link OntologyEntryType }
         */
        public void setMaldiMatrix(OntologyEntryType value) {
          this.maldiMatrix = value;
        }

        /**
         * Gets the value of the spotID property.
         *
         * @return possible object is {@link String }
         */
        public String getSpotID() {
          return spotID;
        }

        /**
         * Sets the value of the spotID property.
         *
         * @param value allowed object is {@link String }
         */
        public void setSpotID(String value) {
          this.spotID = value;
        }

        /**
         * Gets the value of the spotXPosition property.
         *
         * @return possible object is {@link String }
         */
        public String getSpotXPosition() {
          return spotXPosition;
        }

        /**
         * Sets the value of the spotXPosition property.
         *
         * @param value allowed object is {@link String }
         */
        public void setSpotXPosition(String value) {
          this.spotXPosition = value;
        }

        /**
         * Gets the value of the spotYPosition property.
         *
         * @return possible object is {@link String }
         */
        public String getSpotYPosition() {
          return spotYPosition;
        }

        /**
         * Sets the value of the spotYPosition property.
         *
         * @param value allowed object is {@link String }
         */
        public void setSpotYPosition(String value) {
          this.spotYPosition = value;
        }

        /**
         * Gets the value of the spotDiameter property.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getSpotDiameter() {
          return spotDiameter;
        }

        /**
         * Sets the value of the spotDiameter property.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setSpotDiameter(BigInteger value) {
          this.spotDiameter = value;
        }

      }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="robotManufacturer" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
     *         &lt;element name="robotModel" type="{http://sashimi.sourceforge.net/schema_revision/mzXML_3.2}ontologyEntryType"/>
     *       &lt;/sequence>
     *       &lt;attribute name="timePerSpot" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
     *       &lt;attribute name="deadVolume" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "robotManufacturer",
        "robotModel"
    })
    public static class Robot {

      @XmlElement(required = true)
      protected OntologyEntryType robotManufacturer;
      @XmlElement(required = true)
      protected OntologyEntryType robotModel;
      @XmlAttribute(name = "timePerSpot", required = true)
      protected Duration timePerSpot;
      @XmlAttribute(name = "deadVolume")
      @XmlSchemaType(name = "nonNegativeInteger")
      protected BigInteger deadVolume;

      /**
       * Gets the value of the robotManufacturer property.
       *
       * @return possible object is {@link OntologyEntryType }
       */
      public OntologyEntryType getRobotManufacturer() {
        return robotManufacturer;
      }

      /**
       * Sets the value of the robotManufacturer property.
       *
       * @param value allowed object is {@link OntologyEntryType }
       */
      public void setRobotManufacturer(OntologyEntryType value) {
        this.robotManufacturer = value;
      }

      /**
       * Gets the value of the robotModel property.
       *
       * @return possible object is {@link OntologyEntryType }
       */
      public OntologyEntryType getRobotModel() {
        return robotModel;
      }

      /**
       * Sets the value of the robotModel property.
       *
       * @param value allowed object is {@link OntologyEntryType }
       */
      public void setRobotModel(OntologyEntryType value) {
        this.robotModel = value;
      }

      /**
       * Gets the value of the timePerSpot property.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getTimePerSpot() {
        return timePerSpot;
      }

      /**
       * Sets the value of the timePerSpot property.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setTimePerSpot(Duration value) {
        this.timePerSpot = value;
      }

      /**
       * Gets the value of the deadVolume property.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getDeadVolume() {
        return deadVolume;
      }

      /**
       * Sets the value of the deadVolume property.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setDeadVolume(BigInteger value) {
        this.deadVolume = value;
      }

    }

  }

}
