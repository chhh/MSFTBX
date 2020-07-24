
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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
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
 *       &lt;attribute name="adj_ratio_mean" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="adj_ratio_standard_dev" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="heavy2light_adj_ratio_mean" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="heavy2light_adj_ratio_standard_dev" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="pvalue" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="decimal_pvalue" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ASAPRatio_pvalue")
public class ASAPRatioPvalue {

  @XmlAttribute(name = "adj_ratio_mean", required = true)
  protected double adjRatioMean;
  @XmlAttribute(name = "adj_ratio_standard_dev", required = true)
  protected double adjRatioStandardDev;
  @XmlAttribute(name = "heavy2light_adj_ratio_mean")
  protected Double heavy2LightAdjRatioMean;
  @XmlAttribute(name = "heavy2light_adj_ratio_standard_dev")
  protected Double heavy2LightAdjRatioStandardDev;
  @XmlAttribute(name = "pvalue")
  protected Double pvalue;
  @XmlAttribute(name = "decimal_pvalue")
  protected Double decimalPvalue;

  /**
   * Gets the value of the adjRatioMean property.
   */
  public double getAdjRatioMean() {
    return adjRatioMean;
  }

  /**
   * Sets the value of the adjRatioMean property.
   */
  public void setAdjRatioMean(double value) {
    this.adjRatioMean = value;
  }

  /**
   * Gets the value of the adjRatioStandardDev property.
   */
  public double getAdjRatioStandardDev() {
    return adjRatioStandardDev;
  }

  /**
   * Sets the value of the adjRatioStandardDev property.
   */
  public void setAdjRatioStandardDev(double value) {
    this.adjRatioStandardDev = value;
  }

  /**
   * Gets the value of the heavy2LightAdjRatioMean property.
   *
   * @return possible object is {@link Double }
   */
  public Double getHeavy2LightAdjRatioMean() {
    return heavy2LightAdjRatioMean;
  }

  /**
   * Sets the value of the heavy2LightAdjRatioMean property.
   *
   * @param value allowed object is {@link Double }
   */
  public void setHeavy2LightAdjRatioMean(Double value) {
    this.heavy2LightAdjRatioMean = value;
  }

  /**
   * Gets the value of the heavy2LightAdjRatioStandardDev property.
   *
   * @return possible object is {@link Double }
   */
  public Double getHeavy2LightAdjRatioStandardDev() {
    return heavy2LightAdjRatioStandardDev;
  }

  /**
   * Sets the value of the heavy2LightAdjRatioStandardDev property.
   *
   * @param value allowed object is {@link Double }
   */
  public void setHeavy2LightAdjRatioStandardDev(Double value) {
    this.heavy2LightAdjRatioStandardDev = value;
  }

  /**
   * Gets the value of the pvalue property.
   *
   * @return possible object is {@link Double }
   */
  public Double getPvalue() {
    return pvalue;
  }

  /**
   * Sets the value of the pvalue property.
   *
   * @param value allowed object is {@link Double }
   */
  public void setPvalue(Double value) {
    this.pvalue = value;
  }

  /**
   * Gets the value of the decimalPvalue property.
   *
   * @return possible object is {@link Double }
   */
  public Double getDecimalPvalue() {
    return decimalPvalue;
  }

  /**
   * Sets the value of the decimalPvalue property.
   *
   * @param value allowed object is {@link Double }
   */
  public void setDecimalPvalue(Double value) {
    this.decimalPvalue = value;
  }

}
