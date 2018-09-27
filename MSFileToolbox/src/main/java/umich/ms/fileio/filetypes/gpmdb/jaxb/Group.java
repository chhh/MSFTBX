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
 *         &lt;element ref="{}protein" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}group" minOccurs="0"/>
 *         &lt;element ref="{}note" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.bioml.com/gaml/}trace" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="act" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="expect" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="fI" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="label" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="maxI" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="mh" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="rt" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="sumI" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="z" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "protein",
    "group",
    "note",
    "trace"
})
@XmlRootElement(name = "group")
public class Group {

  protected List<Protein> protein;
  protected Group group;
  protected List<Note> note;
  @XmlElement(namespace = "http://www.bioml.com/gaml/")
  protected List<Trace> trace;
  @XmlAttribute(name = "act")
  protected Integer act;
  @XmlAttribute(name = "expect")
  protected Double expect;
  @XmlAttribute(name = "fI")
  protected Double fi;
  @XmlAttribute(name = "id")
  protected Integer id;
  @XmlAttribute(name = "label", required = true)
  @XmlSchemaType(name = "anySimpleType")
  protected String label;
  @XmlAttribute(name = "maxI")
  protected Integer maxI;
  @XmlAttribute(name = "mh")
  protected Double mh;
  @XmlAttribute(name = "rt")
  @XmlSchemaType(name = "anySimpleType")
  protected String rt;
  @XmlAttribute(name = "sumI")
  protected Double sumI;
  @XmlAttribute(name = "type", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String type;
  @XmlAttribute(name = "z")
  protected Integer z;

  /**
   * Gets the value of the protein property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the protein property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getProtein().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Protein }
   */
  public List<Protein> getProtein() {
    if (protein == null) {
      protein = new ArrayList<Protein>();
    }
    return this.protein;
  }

  /**
   * Gets the value of the group property.
   *
   * @return possible object is {@link Group }
   */
  public Group getGroup() {
    return group;
  }

  /**
   * Sets the value of the group property.
   *
   * @param value allowed object is {@link Group }
   */
  public void setGroup(Group value) {
    this.group = value;
  }

  /**
   * Gets the value of the note property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the note property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getNote().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Note }
   */
  public List<Note> getNote() {
    if (note == null) {
      note = new ArrayList<Note>();
    }
    return this.note;
  }

  /**
   * Gets the value of the trace property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the trace property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getTrace().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Trace }
   */
  public List<Trace> getTrace() {
    if (trace == null) {
      trace = new ArrayList<Trace>();
    }
    return this.trace;
  }

  /**
   * Gets the value of the act property.
   *
   * @return possible object is {@link Integer }
   */
  public Integer getAct() {
    return act;
  }

  /**
   * Sets the value of the act property.
   *
   * @param value allowed object is {@link Integer }
   */
  public void setAct(Integer value) {
    this.act = value;
  }

  /**
   * Gets the value of the expect property.
   *
   * @return possible object is {@link Double }
   */
  public Double getExpect() {
    return expect;
  }

  /**
   * Sets the value of the expect property.
   *
   * @param value allowed object is {@link Double }
   */
  public void setExpect(Double value) {
    this.expect = value;
  }

  /**
   * Gets the value of the fi property.
   *
   * @return possible object is {@link Double }
   */
  public Double getFI() {
    return fi;
  }

  /**
   * Sets the value of the fi property.
   *
   * @param value allowed object is {@link Double }
   */
  public void setFI(Double value) {
    this.fi = value;
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
   * Gets the value of the maxI property.
   *
   * @return possible object is {@link Integer }
   */
  public Integer getMaxI() {
    return maxI;
  }

  /**
   * Sets the value of the maxI property.
   *
   * @param value allowed object is {@link Integer }
   */
  public void setMaxI(Integer value) {
    this.maxI = value;
  }

  /**
   * Gets the value of the mh property.
   *
   * @return possible object is {@link Double }
   */
  public Double getMh() {
    return mh;
  }

  /**
   * Sets the value of the mh property.
   *
   * @param value allowed object is {@link Double }
   */
  public void setMh(Double value) {
    this.mh = value;
  }

  /**
   * Gets the value of the rt property.
   *
   * @return possible object is {@link String }
   */
  public String getRt() {
    return rt;
  }

  /**
   * Sets the value of the rt property.
   *
   * @param value allowed object is {@link String }
   */
  public void setRt(String value) {
    this.rt = value;
  }

  /**
   * Gets the value of the sumI property.
   *
   * @return possible object is {@link Double }
   */
  public Double getSumI() {
    return sumI;
  }

  /**
   * Sets the value of the sumI property.
   *
   * @param value allowed object is {@link Double }
   */
  public void setSumI(Double value) {
    this.sumI = value;
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

  /**
   * Gets the value of the z property.
   *
   * @return possible object is {@link Integer }
   */
  public Integer getZ() {
    return z;
  }

  /**
   * Sets the value of the z property.
   *
   * @param value allowed object is {@link Integer }
   */
  public void setZ(Integer value) {
    this.z = value;
  }

}
