
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

package umich.ms.fileio.filetypes.mzml.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A run in mzML should correspond to a single, consecutive and coherent set of scans on an
 * instrument.
 *
 * <p>Java class for RunType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RunType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psi.hupo.org/ms/mzml}ParamGroupType">
 *       &lt;sequence>
 *         &lt;element name="spectrumList" type="{http://psi.hupo.org/ms/mzml}SpectrumListType" minOccurs="0"/>
 *         &lt;element name="chromatogramList" type="{http://psi.hupo.org/ms/mzml}ChromatogramListType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="defaultInstrumentConfigurationRef" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="defaultSourceFileRef" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="sampleRef" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="startTimeStamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RunType", propOrder = {
    "spectrumList",
    "chromatogramList"
})
public class RunType
    extends ParamGroupType {

  protected SpectrumListType spectrumList;
  protected ChromatogramListType chromatogramList;
  @XmlAttribute(name = "id", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;
  @XmlAttribute(name = "defaultInstrumentConfigurationRef", required = true)
  @XmlIDREF
  @XmlSchemaType(name = "IDREF")
  protected Object defaultInstrumentConfigurationRef;
  @XmlAttribute(name = "defaultSourceFileRef")
  @XmlIDREF
  @XmlSchemaType(name = "IDREF")
  protected Object defaultSourceFileRef;
  @XmlAttribute(name = "sampleRef")
  @XmlIDREF
  @XmlSchemaType(name = "IDREF")
  protected Object sampleRef;
  @XmlAttribute(name = "startTimeStamp")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar startTimeStamp;

  /**
   * Gets the value of the spectrumList property.
   *
   * @return possible object is {@link SpectrumListType }
   */
  public SpectrumListType getSpectrumList() {
    return spectrumList;
  }

  /**
   * Sets the value of the spectrumList property.
   *
   * @param value allowed object is {@link SpectrumListType }
   */
  public void setSpectrumList(SpectrumListType value) {
    this.spectrumList = value;
  }

  /**
   * Gets the value of the chromatogramList property.
   *
   * @return possible object is {@link ChromatogramListType }
   */
  public ChromatogramListType getChromatogramList() {
    return chromatogramList;
  }

  /**
   * Sets the value of the chromatogramList property.
   *
   * @param value allowed object is {@link ChromatogramListType }
   */
  public void setChromatogramList(ChromatogramListType value) {
    this.chromatogramList = value;
  }

  /**
   * Gets the value of the id property.
   *
   * @return possible object is {@link String }
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   *
   * @param value allowed object is {@link String }
   */
  public void setId(String value) {
    this.id = value;
  }

  /**
   * Gets the value of the defaultInstrumentConfigurationRef property.
   *
   * @return possible object is {@link Object }
   */
  public Object getDefaultInstrumentConfigurationRef() {
    return defaultInstrumentConfigurationRef;
  }

  /**
   * Sets the value of the defaultInstrumentConfigurationRef property.
   *
   * @param value allowed object is {@link Object }
   */
  public void setDefaultInstrumentConfigurationRef(Object value) {
    this.defaultInstrumentConfigurationRef = value;
  }

  /**
   * Gets the value of the defaultSourceFileRef property.
   *
   * @return possible object is {@link Object }
   */
  public Object getDefaultSourceFileRef() {
    return defaultSourceFileRef;
  }

  /**
   * Sets the value of the defaultSourceFileRef property.
   *
   * @param value allowed object is {@link Object }
   */
  public void setDefaultSourceFileRef(Object value) {
    this.defaultSourceFileRef = value;
  }

  /**
   * Gets the value of the sampleRef property.
   *
   * @return possible object is {@link Object }
   */
  public Object getSampleRef() {
    return sampleRef;
  }

  /**
   * Sets the value of the sampleRef property.
   *
   * @param value allowed object is {@link Object }
   */
  public void setSampleRef(Object value) {
    this.sampleRef = value;
  }

  /**
   * Gets the value of the startTimeStamp property.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getStartTimeStamp() {
    return startTimeStamp;
  }

  /**
   * Sets the value of the startTimeStamp property.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setStartTimeStamp(XMLGregorianCalendar value) {
    this.startTimeStamp = value;
  }

}
