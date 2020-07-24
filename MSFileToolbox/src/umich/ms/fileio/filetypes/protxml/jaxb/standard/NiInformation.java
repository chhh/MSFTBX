
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

package umich.ms.fileio.filetypes.protxml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="ni_distribution" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="bin_no" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="ni_lower_bound_incl" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="ni_upper_bound_excl" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="ni_lower_bound_excl" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="ni_upper_bound_incl" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="pos_freq" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="neg_freq" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="pos_to_neg_ratio" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="alt_pos_to_neg_ratio" type="{http://www.w3.org/2001/XMLSchema}double" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "niDistribution"
})
public class NiInformation {

  @XmlElement(name = "ni_distribution", required = true)
  protected List<NiDistribution> niDistribution;

  /**
   * Gets the value of the niDistribution property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the niDistribution property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getNiDistribution().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link NiDistribution }
   */
  public List<NiDistribution> getNiDistribution() {
    if (niDistribution == null) {
      niDistribution = new ArrayList<NiDistribution>(1);
    }
    return this.niDistribution;
  }

}
