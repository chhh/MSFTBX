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
package umich.ms.fileio.filetypes.protxml.jaxb.primitive;

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
 * 
 * 
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
    protected double heavy2LightAdjRatioMean;
    @XmlAttribute(name = "heavy2light_adj_ratio_standard_dev")
    protected double heavy2LightAdjRatioStandardDev;
    @XmlAttribute(name = "pvalue")
    protected double pvalue;
    @XmlAttribute(name = "decimal_pvalue")
    protected double decimalPvalue;

    /**
     * Gets the value of the adjRatioMean property.
     * 
     */
    public double getAdjRatioMean() {
        return adjRatioMean;
    }

    /**
     * Sets the value of the adjRatioMean property.
     * 
     */
    public void setAdjRatioMean(double value) {
        this.adjRatioMean = value;
    }

    /**
     * Gets the value of the adjRatioStandardDev property.
     * 
     */
    public double getAdjRatioStandardDev() {
        return adjRatioStandardDev;
    }

    /**
     * Sets the value of the adjRatioStandardDev property.
     * 
     */
    public void setAdjRatioStandardDev(double value) {
        this.adjRatioStandardDev = value;
    }

    /**
     * Gets the value of the heavy2LightAdjRatioMean property.
     * 
     */
    public double getHeavy2LightAdjRatioMean() {
        return heavy2LightAdjRatioMean;
    }

    /**
     * Sets the value of the heavy2LightAdjRatioMean property.
     * 
     */
    public void setHeavy2LightAdjRatioMean(double value) {
        this.heavy2LightAdjRatioMean = value;
    }

    /**
     * Gets the value of the heavy2LightAdjRatioStandardDev property.
     * 
     */
    public double getHeavy2LightAdjRatioStandardDev() {
        return heavy2LightAdjRatioStandardDev;
    }

    /**
     * Sets the value of the heavy2LightAdjRatioStandardDev property.
     * 
     */
    public void setHeavy2LightAdjRatioStandardDev(double value) {
        this.heavy2LightAdjRatioStandardDev = value;
    }

    /**
     * Gets the value of the pvalue property.
     * 
     */
    public double getPvalue() {
        return pvalue;
    }

    /**
     * Sets the value of the pvalue property.
     * 
     */
    public void setPvalue(double value) {
        this.pvalue = value;
    }

    /**
     * Gets the value of the decimalPvalue property.
     * 
     */
    public double getDecimalPvalue() {
        return decimalPvalue;
    }

    /**
     * Sets the value of the decimalPvalue property.
     * 
     */
    public void setDecimalPvalue(double value) {
        this.decimalPvalue = value;
    }

}
