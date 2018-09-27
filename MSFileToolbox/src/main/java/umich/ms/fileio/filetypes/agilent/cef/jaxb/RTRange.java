
/*
 * Copyright (c) 2018 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.agilent.cef.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="max" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="min" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "RTRange")
public class RTRange {

  @XmlAttribute(name = "max", required = true)
  @XmlJavaTypeAdapter(Adapter2.class)
  @XmlSchemaType(name = "decimal")
  protected Double max;
  @XmlAttribute(name = "min", required = true)
  @XmlJavaTypeAdapter(Adapter2.class)
  @XmlSchemaType(name = "decimal")
  protected Double min;

  /**
   * Gets the value of the max property.
   *
   * @return possible object is {@link String }
   */
  public Double getMax() {
    return max;
  }

  /**
   * Sets the value of the max property.
   *
   * @param value allowed object is {@link String }
   */
  public void setMax(Double value) {
    this.max = value;
  }

  /**
   * Gets the value of the min property.
   *
   * @return possible object is {@link String }
   */
  public Double getMin() {
    return min;
  }

  /**
   * Sets the value of the min property.
   *
   * @param value allowed object is {@link String }
   */
  public void setMin(Double value) {
    this.min = value;
  }

}
