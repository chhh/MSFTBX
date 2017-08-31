
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
 *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="pos_dens" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="neg_dens" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="neg_obs_dens" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="pos_obs_dens" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class MixtureModelPoint {

    @XmlAttribute(name = "value")
    protected Float value;
    @XmlAttribute(name = "pos_dens")
    protected Float posDens;
    @XmlAttribute(name = "neg_dens")
    protected Float negDens;
    @XmlAttribute(name = "neg_obs_dens")
    protected Float negObsDens;
    @XmlAttribute(name = "pos_obs_dens")
    protected Float posObsDens;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setValue(Float value) {
        this.value = value;
    }

    /**
     * Gets the value of the posDens property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getPosDens() {
        return posDens;
    }

    /**
     * Sets the value of the posDens property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setPosDens(Float value) {
        this.posDens = value;
    }

    /**
     * Gets the value of the negDens property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getNegDens() {
        return negDens;
    }

    /**
     * Sets the value of the negDens property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setNegDens(Float value) {
        this.negDens = value;
    }

    /**
     * Gets the value of the negObsDens property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getNegObsDens() {
        return negObsDens;
    }

    /**
     * Sets the value of the negObsDens property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setNegObsDens(Float value) {
        this.negObsDens = value;
    }

    /**
     * Gets the value of the posObsDens property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getPosObsDens() {
        return posObsDens;
    }

    /**
     * Sets the value of the posObsDens property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setPosObsDens(Float value) {
        this.posObsDens = value;
    }

}
