
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ProcessingMethodType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ProcessingMethodType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psi.hupo.org/ms/mzml}ParamGroupType">
 *       &lt;attribute name="order" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="softwareRef" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessingMethodType")
public class ProcessingMethodType
    extends ParamGroupType {

  @XmlAttribute(name = "order", required = true)
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name = "nonNegativeInteger")
  protected Integer order;
  @XmlAttribute(name = "softwareRef", required = true)
  @XmlIDREF
  @XmlSchemaType(name = "IDREF")
  protected Object softwareRef;

  /**
   * Gets the value of the order property.
   *
   * @return possible object is {@link String }
   */
  public Integer getOrder() {
    return order;
  }

  /**
   * Sets the value of the order property.
   *
   * @param value allowed object is {@link String }
   */
  public void setOrder(Integer value) {
    this.order = value;
  }

  /**
   * Gets the value of the softwareRef property.
   *
   * @return possible object is {@link Object }
   */
  public Object getSoftwareRef() {
    return softwareRef;
  }

  /**
   * Sets the value of the softwareRef property.
   *
   * @param value allowed object is {@link Object }
   */
  public void setSoftwareRef(Object value) {
    this.softwareRef = value;
  }

}
