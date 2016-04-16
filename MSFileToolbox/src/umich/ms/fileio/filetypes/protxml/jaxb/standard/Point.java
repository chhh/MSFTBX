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
package umich.ms.fileio.filetypes.protxml.jaxb.standard;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="logratio" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="obs_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="model_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Point {

    @XmlAttribute(name = "logratio", required = true)
    protected double logratio;
    @XmlAttribute(name = "obs_distr", required = true)
    protected double obsDistr;
    @XmlAttribute(name = "model_distr", required = true)
    protected double modelDistr;

    /**
     * Gets the value of the logratio property.
     * 
     */
    public double getLogratio() {
        return logratio;
    }

    /**
     * Sets the value of the logratio property.
     * 
     */
    public void setLogratio(double value) {
        this.logratio = value;
    }

    /**
     * Gets the value of the obsDistr property.
     * 
     */
    public double getObsDistr() {
        return obsDistr;
    }

    /**
     * Sets the value of the obsDistr property.
     * 
     */
    public void setObsDistr(double value) {
        this.obsDistr = value;
    }

    /**
     * Gets the value of the modelDistr property.
     * 
     */
    public double getModelDistr() {
        return modelDistr;
    }

    /**
     * Sets the value of the modelDistr property.
     * 
     */
    public void setModelDistr(double value) {
        this.modelDistr = value;
    }

}
