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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence>
 *         &lt;element ref="{http://www.bioml.com/gaml/}attribute" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.bioml.com/gaml/}Xdata"/>
 *         &lt;element ref="{http://www.bioml.com/gaml/}Ydata"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="label" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "attribute",
    "xdata",
    "ydata"
})
@XmlRootElement(name = "trace", namespace = "http://www.bioml.com/gaml/")
public class Trace {

  @XmlElement(namespace = "http://www.bioml.com/gaml/")
  protected List<Attribute> attribute;
  @XmlElement(name = "Xdata", namespace = "http://www.bioml.com/gaml/", required = true)
  protected Xdata xdata;
  @XmlElement(name = "Ydata", namespace = "http://www.bioml.com/gaml/", required = true)
  protected Ydata ydata;
  @XmlAttribute(name = "id")
  protected Integer id;
  @XmlAttribute(name = "label", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NMTOKEN")
  protected String label;
  @XmlAttribute(name = "type", required = true)
  @XmlSchemaType(name = "anySimpleType")
  protected String type;

  /**
   * Gets the value of the attribute property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the attribute property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getAttribute().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Attribute }
   */
  public List<Attribute> getAttribute() {
    if (attribute == null) {
      attribute = new ArrayList<Attribute>();
    }
    return this.attribute;
  }

  /**
   * Gets the value of the xdata property.
   *
   * @return possible object is {@link Xdata }
   */
  public Xdata getXdata() {
    return xdata;
  }

  /**
   * Sets the value of the xdata property.
   *
   * @param value allowed object is {@link Xdata }
   */
  public void setXdata(Xdata value) {
    this.xdata = value;
  }

  /**
   * Gets the value of the ydata property.
   *
   * @return possible object is {@link Ydata }
   */
  public Ydata getYdata() {
    return ydata;
  }

  /**
   * Sets the value of the ydata property.
   *
   * @param value allowed object is {@link Ydata }
   */
  public void setYdata(Ydata value) {
    this.ydata = value;
  }

  /**
   * Gets the value of the id property.
   *
   * @return possible object is {@link Integer }
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   *
   * @param value allowed object is {@link Integer }
   */
  public void setId(Integer value) {
    this.id = value;
  }

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
