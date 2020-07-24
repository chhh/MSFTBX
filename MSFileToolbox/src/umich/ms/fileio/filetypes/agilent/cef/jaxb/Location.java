
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
 *       &lt;attribute name="a" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="m" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="rt" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="v" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="y" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Location")
public class Location {

  @XmlAttribute(name = "a")
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name = "integer")
  protected Integer a;
  @XmlAttribute(name = "m", required = true)
  @XmlJavaTypeAdapter(Adapter2.class)
  @XmlSchemaType(name = "decimal")
  protected Double m;
  @XmlAttribute(name = "rt", required = true)
  @XmlJavaTypeAdapter(Adapter2.class)
  @XmlSchemaType(name = "decimal")
  protected Double rt;
  @XmlAttribute(name = "v")
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name = "integer")
  protected Integer v;
  @XmlAttribute(name = "y", required = true)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name = "integer")
  protected Integer y;

  /**
   * Gets the value of the a property.
   *
   * @return possible object is {@link String }
   */
  public Integer getA() {
    return a;
  }

  /**
   * Sets the value of the a property.
   *
   * @param value allowed object is {@link String }
   */
  public void setA(Integer value) {
    this.a = value;
  }

  /**
   * Gets the value of the m property.
   *
   * @return possible object is {@link String }
   */
  public Double getM() {
    return m;
  }

  /**
   * Sets the value of the m property.
   *
   * @param value allowed object is {@link String }
   */
  public void setM(Double value) {
    this.m = value;
  }

  /**
   * Gets the value of the rt property.
   *
   * @return possible object is {@link String }
   */
  public Double getRt() {
    return rt;
  }

  /**
   * Sets the value of the rt property.
   *
   * @param value allowed object is {@link String }
   */
  public void setRt(Double value) {
    this.rt = value;
  }

  /**
   * Gets the value of the v property.
   *
   * @return possible object is {@link String }
   */
  public Integer getV() {
    return v;
  }

  /**
   * Sets the value of the v property.
   *
   * @param value allowed object is {@link String }
   */
  public void setV(Integer value) {
    this.v = value;
  }

  /**
   * Gets the value of the y property.
   *
   * @return possible object is {@link String }
   */
  public Integer getY() {
    return y;
  }

  /**
   * Sets the value of the y property.
   *
   * @param value allowed object is {@link String }
   */
  public void setY(Integer value) {
    this.y = value;
  }

}
