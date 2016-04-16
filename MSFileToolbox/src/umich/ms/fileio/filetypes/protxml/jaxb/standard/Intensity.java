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
 *       &lt;attribute name="mz" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="ratio" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="error" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Intensity {

    @XmlAttribute(name = "mz", required = true)
    protected float mz;
    @XmlAttribute(name = "ratio", required = true)
    protected float ratio;
    @XmlAttribute(name = "error", required = true)
    protected float error;

    /**
     * Gets the value of the mz property.
     * 
     */
    public float getMz() {
        return mz;
    }

    /**
     * Sets the value of the mz property.
     * 
     */
    public void setMz(float value) {
        this.mz = value;
    }

    /**
     * Gets the value of the ratio property.
     * 
     */
    public float getRatio() {
        return ratio;
    }

    /**
     * Sets the value of the ratio property.
     * 
     */
    public void setRatio(float value) {
        this.ratio = value;
    }

    /**
     * Gets the value of the error property.
     * 
     */
    public float getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     */
    public void setError(float value) {
        this.error = value;
    }

}
