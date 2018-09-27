
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
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Scan or acquisition from original raw file used to create this peak list, as specified in
 * sourceFile.
 *
 * <p>Java class for ScanType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ScanType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psi.hupo.org/ms/mzml}ParamGroupType">
 *       &lt;sequence>
 *         &lt;element name="scanWindowList" type="{http://psi.hupo.org/ms/mzml}ScanWindowListType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="spectrumRef" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sourceFileRef" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="externalSpectrumID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="instrumentConfigurationRef" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScanType", propOrder = {
    "scanWindowList"
})
public class ScanType
    extends ParamGroupType {

  protected ScanWindowListType scanWindowList;
  @XmlAttribute(name = "spectrumRef")
  protected String spectrumRef;
  @XmlAttribute(name = "sourceFileRef")
  @XmlIDREF
  @XmlSchemaType(name = "IDREF")
  protected Object sourceFileRef;
  @XmlAttribute(name = "externalSpectrumID")
  protected String externalSpectrumID;
  @XmlAttribute(name = "instrumentConfigurationRef")
  @XmlIDREF
  @XmlSchemaType(name = "IDREF")
  protected Object instrumentConfigurationRef;

  /**
   * Gets the value of the scanWindowList property.
   *
   * @return possible object is {@link ScanWindowListType }
   */
  public ScanWindowListType getScanWindowList() {
    return scanWindowList;
  }

  /**
   * Sets the value of the scanWindowList property.
   *
   * @param value allowed object is {@link ScanWindowListType }
   */
  public void setScanWindowList(ScanWindowListType value) {
    this.scanWindowList = value;
  }

  /**
   * Gets the value of the spectrumRef property.
   *
   * @return possible object is {@link String }
   */
  public String getSpectrumRef() {
    return spectrumRef;
  }

  /**
   * Sets the value of the spectrumRef property.
   *
   * @param value allowed object is {@link String }
   */
  public void setSpectrumRef(String value) {
    this.spectrumRef = value;
  }

  /**
   * Gets the value of the sourceFileRef property.
   *
   * @return possible object is {@link Object }
   */
  public Object getSourceFileRef() {
    return sourceFileRef;
  }

  /**
   * Sets the value of the sourceFileRef property.
   *
   * @param value allowed object is {@link Object }
   */
  public void setSourceFileRef(Object value) {
    this.sourceFileRef = value;
  }

  /**
   * Gets the value of the externalSpectrumID property.
   *
   * @return possible object is {@link String }
   */
  public String getExternalSpectrumID() {
    return externalSpectrumID;
  }

  /**
   * Sets the value of the externalSpectrumID property.
   *
   * @param value allowed object is {@link String }
   */
  public void setExternalSpectrumID(String value) {
    this.externalSpectrumID = value;
  }

  /**
   * Gets the value of the instrumentConfigurationRef property.
   *
   * @return possible object is {@link Object }
   */
  public Object getInstrumentConfigurationRef() {
    return instrumentConfigurationRef;
  }

  /**
   * Sets the value of the instrumentConfigurationRef property.
   *
   * @param value allowed object is {@link Object }
   */
  public void setInstrumentConfigurationRef(Object value) {
    this.instrumentConfigurationRef = value;
  }

}
