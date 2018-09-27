
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
 * Uncontrolled user parameters (essentially allowing free text). Before using these, one should
 * verify whether there is an appropriate CV term available, and if so, use the CV term instead
 *
 * <p>Java class for UserParamType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="UserParamType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="unitAccession" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="unitName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="unitCvRef" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserParamType")
public class UserParamType {

  @XmlAttribute(name = "name", required = true)
  protected String name;
  @XmlAttribute(name = "type")
  protected String type;
  @XmlAttribute(name = "value")
  protected String value;
  @XmlAttribute(name = "unitAccession")
  protected String unitAccession;
  @XmlAttribute(name = "unitName")
  protected String unitName;
  @XmlAttribute(name = "unitCvRef")
  @XmlIDREF
  @XmlSchemaType(name = "IDREF")
  protected Object unitCvRef;

  /**
   * Gets the value of the name property.
   *
   * @return possible object is {@link String }
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the value of the name property.
   *
   * @param value allowed object is {@link String }
   */
  public void setName(String value) {
    this.name = value;
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
   * Gets the value of the value property.
   *
   * @return possible object is {@link String }
   */
  public String getValue() {
    return value;
  }

  /**
   * Sets the value of the value property.
   *
   * @param value allowed object is {@link String }
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Gets the value of the unitAccession property.
   *
   * @return possible object is {@link String }
   */
  public String getUnitAccession() {
    return unitAccession;
  }

  /**
   * Sets the value of the unitAccession property.
   *
   * @param value allowed object is {@link String }
   */
  public void setUnitAccession(String value) {
    this.unitAccession = value;
  }

  /**
   * Gets the value of the unitName property.
   *
   * @return possible object is {@link String }
   */
  public String getUnitName() {
    return unitName;
  }

  /**
   * Sets the value of the unitName property.
   *
   * @param value allowed object is {@link String }
   */
  public void setUnitName(String value) {
    this.unitName = value;
  }

  /**
   * Gets the value of the unitCvRef property.
   *
   * @return possible object is {@link Object }
   */
  public Object getUnitCvRef() {
    return unitCvRef;
  }

  /**
   * Sets the value of the unitCvRef property.
   *
   * @param value allowed object is {@link Object }
   */
  public void setUnitCvRef(Object value) {
    this.unitCvRef = value;
  }

}
