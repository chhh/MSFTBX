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
import javax.xml.bind.annotation.XmlValue;
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
 *       &lt;attribute name="byteorder" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="format" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="numvalues" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "content"
})
@XmlRootElement(name = "values", namespace = "http://www.bioml.com/gaml/")
public class Values {

  @XmlValue
  protected String content;
  @XmlAttribute(name = "byteorder", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String byteorder;
  @XmlAttribute(name = "format", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String format;
  @XmlAttribute(name = "numvalues", required = true)
  protected Integer numvalues;

  /**
   * Gets the value of the content property.
   *
   * @return possible object is {@link String }
   */
  public String getContent() {
    return content;
  }

  /**
   * Sets the value of the content property.
   *
   * @param value allowed object is {@link String }
   */
  public void setContent(String value) {
    this.content = value;
  }

  /**
   * Gets the value of the byteorder property.
   *
   * @return possible object is {@link String }
   */
  public String getByteorder() {
    return byteorder;
  }

  /**
   * Sets the value of the byteorder property.
   *
   * @param value allowed object is {@link String }
   */
  public void setByteorder(String value) {
    this.byteorder = value;
  }

  /**
   * Gets the value of the format property.
   *
   * @return possible object is {@link String }
   */
  public String getFormat() {
    return format;
  }

  /**
   * Sets the value of the format property.
   *
   * @param value allowed object is {@link String }
   */
  public void setFormat(String value) {
    this.format = value;
  }

  /**
   * Gets the value of the numvalues property.
   *
   * @return possible object is {@link Integer }
   */
  public Integer getNumvalues() {
    return numvalues;
  }

  /**
   * Sets the value of the numvalues property.
   *
   * @param value allowed object is {@link Integer }
   */
  public void setNumvalues(Integer value) {
    this.numvalues = value;
  }

}
