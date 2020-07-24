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
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="at" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="modified" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="pm" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "aa")
public class Aa {

  @XmlAttribute(name = "at", required = true)
  protected Integer at;
  @XmlAttribute(name = "id")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String id;
  @XmlAttribute(name = "modified", required = true)
  protected Double modified;
  @XmlAttribute(name = "pm")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String pm;
  @XmlAttribute(name = "type", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String type;

  /**
   * Gets the value of the at property.
   *
   * @return possible object is {@link Integer }
   */
  public Integer getAt() {
    return at;
  }

  /**
   * Sets the value of the at property.
   *
   * @param value allowed object is {@link Integer }
   */
  public void setAt(Integer value) {
    this.at = value;
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
   * Gets the value of the modified property.
   *
   * @return possible object is {@link Double }
   */
  public Double getModified() {
    return modified;
  }

  /**
   * Sets the value of the modified property.
   *
   * @param value allowed object is {@link Double }
   */
  public void setModified(Double value) {
    this.modified = value;
  }

  /**
   * Gets the value of the pm property.
   *
   * @return possible object is {@link String }
   */
  public String getPm() {
    return pm;
  }

  /**
   * Sets the value of the pm property.
   *
   * @param value allowed object is {@link String }
   */
  public void setPm(String value) {
    this.pm = value;
  }

  /**
   * Gets the value of the type property.
   *
   * @return possible object is {@link String }
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the value of the type property.
   *
   * @param value allowed object is {@link String }
   */
  public void setType(String value) {
    this.type = value;
  }

}
