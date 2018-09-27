
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
 *       &lt;attribute name="asapratio_id" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" default="1" />
 *       &lt;attribute name="background_ratio_mean" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="background_ratio_stdev" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="background_fitting_error" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="analysis_distribution_file" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="full_analysis_distribution_file" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="asap_prot_id" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" default="1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ASAP_pvalue_analysis_summary")
public class ASAPPvalueAnalysisSummary {

  @XmlAttribute(name = "asapratio_id")
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name = "nonNegativeInteger")
  protected Integer asapratioId;
  @XmlAttribute(name = "background_ratio_mean", required = true)
  protected double backgroundRatioMean;
  @XmlAttribute(name = "background_ratio_stdev", required = true)
  protected double backgroundRatioStdev;
  @XmlAttribute(name = "background_fitting_error", required = true)
  protected double backgroundFittingError;
  @XmlAttribute(name = "analysis_distribution_file", required = true)
  protected String analysisDistributionFile;
  @XmlAttribute(name = "full_analysis_distribution_file")
  protected String fullAnalysisDistributionFile;
  @XmlAttribute(name = "asap_prot_id")
  @XmlJavaTypeAdapter(Adapter1.class)
  @XmlSchemaType(name = "nonNegativeInteger")
  protected Integer asapProtId;

  /**
   * Gets the value of the asapratioId property.
   *
   * @return possible object is {@link String }
   */
  public int getAsapratioId() {
    if (asapratioId == null) {
      return new Adapter1().unmarshal("1");
    } else {
      return asapratioId;
    }
  }

  /**
   * Sets the value of the asapratioId property.
   *
   * @param value allowed object is {@link String }
   */
  public void setAsapratioId(Integer value) {
    this.asapratioId = value;
  }

  /**
   * Gets the value of the backgroundRatioMean property.
   */
  public double getBackgroundRatioMean() {
    return backgroundRatioMean;
  }

  /**
   * Sets the value of the backgroundRatioMean property.
   */
  public void setBackgroundRatioMean(double value) {
    this.backgroundRatioMean = value;
  }

  /**
   * Gets the value of the backgroundRatioStdev property.
   */
  public double getBackgroundRatioStdev() {
    return backgroundRatioStdev;
  }

  /**
   * Sets the value of the backgroundRatioStdev property.
   */
  public void setBackgroundRatioStdev(double value) {
    this.backgroundRatioStdev = value;
  }

  /**
   * Gets the value of the backgroundFittingError property.
   */
  public double getBackgroundFittingError() {
    return backgroundFittingError;
  }

  /**
   * Sets the value of the backgroundFittingError property.
   */
  public void setBackgroundFittingError(double value) {
    this.backgroundFittingError = value;
  }

  /**
   * Gets the value of the analysisDistributionFile property.
   *
   * @return possible object is {@link String }
   */
  public String getAnalysisDistributionFile() {
    return analysisDistributionFile;
  }

  /**
   * Sets the value of the analysisDistributionFile property.
   *
   * @param value allowed object is {@link String }
   */
  public void setAnalysisDistributionFile(String value) {
    this.analysisDistributionFile = value;
  }

  /**
   * Gets the value of the fullAnalysisDistributionFile property.
   *
   * @return possible object is {@link String }
   */
  public String getFullAnalysisDistributionFile() {
    return fullAnalysisDistributionFile;
  }

  /**
   * Sets the value of the fullAnalysisDistributionFile property.
   *
   * @param value allowed object is {@link String }
   */
  public void setFullAnalysisDistributionFile(String value) {
    this.fullAnalysisDistributionFile = value;
  }

  /**
   * Gets the value of the asapProtId property.
   *
   * @return possible object is {@link String }
   */
  public int getAsapProtId() {
    if (asapProtId == null) {
      return new Adapter1().unmarshal("1");
    } else {
      return asapProtId;
    }
  }

  /**
   * Sets the value of the asapProtId property.
   *
   * @param value allowed object is {@link String }
   */
  public void setAsapProtId(Integer value) {
    this.asapProtId = value;
  }

}
