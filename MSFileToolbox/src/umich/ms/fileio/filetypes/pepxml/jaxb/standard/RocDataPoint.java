
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

package umich.ms.fileio.filetypes.pepxml.jaxb.standard;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *       &lt;attribute name="min_prob" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="sensitivity" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="error" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="num_corr" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="num_incorr" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class RocDataPoint {

    @XmlAttribute(name = "min_prob", required = true)
    protected double minProb;
    @XmlAttribute(name = "sensitivity", required = true)
    protected double sensitivity;
    @XmlAttribute(name = "error", required = true)
    protected double error;
    @XmlAttribute(name = "num_corr", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long numCorr;
    @XmlAttribute(name = "num_incorr", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long numIncorr;

    /**
     * Gets the value of the minProb property.
     * 
     */
    public double getMinProb() {
        return minProb;
    }

    /**
     * Sets the value of the minProb property.
     * 
     */
    public void setMinProb(double value) {
        this.minProb = value;
    }

    /**
     * Gets the value of the sensitivity property.
     * 
     */
    public double getSensitivity() {
        return sensitivity;
    }

    /**
     * Sets the value of the sensitivity property.
     * 
     */
    public void setSensitivity(double value) {
        this.sensitivity = value;
    }

    /**
     * Gets the value of the error property.
     * 
     */
    public double getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     */
    public void setError(double value) {
        this.error = value;
    }

    /**
     * Gets the value of the numCorr property.
     * 
     */
    public long getNumCorr() {
        return numCorr;
    }

    /**
     * Sets the value of the numCorr property.
     * 
     */
    public void setNumCorr(long value) {
        this.numCorr = value;
    }

    /**
     * Gets the value of the numIncorr property.
     * 
     */
    public long getNumIncorr() {
        return numIncorr;
    }

    /**
     * Sets the value of the numIncorr property.
     * 
     */
    public void setNumIncorr(long value) {
        this.numIncorr = value;
    }

}
