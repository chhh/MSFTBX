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
package umich.ms.fileio.filetypes.gpmdb.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}GAML.values">
 *       &lt;attribute name="label" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="units" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Xdata", namespace = "http://www.bioml.com/gaml/")
public class Xdata
    extends GAMLValues {

  @XmlAttribute(name = "label", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NMTOKEN")
  protected String label;
  @XmlAttribute(name = "units", required = true)
  @XmlSchemaType(name = "anySimpleType")
  protected String units;

  /**
   * Gets the value of the label property.
   *
   * @return possible object is {@link String }
   */
  public String getLabel() {
    return label;
  }

  /**
   * Sets the value of the label property.
   *
   * @param value allowed object is {@link String }
   */
  public void setLabel(String value) {
    this.label = value;
  }

  /**
   * Gets the value of the units property.
   *
   * @return possible object is {@link String }
   */
  public String getUnits() {
    return units;
  }

  /**
   * Sets the value of the units property.
   *
   * @param value allowed object is {@link String }
   */
  public void setUnits(String value) {
    this.units = value;
  }

}
