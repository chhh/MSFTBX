
/*
 * Copyright (c) 2016 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.pepxml.jaxb.primitive;

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
 *       &lt;attribute name="light_firstscan" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="light_lastscan" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="light_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="heavy_firstscan" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="heavy_lastscan" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="heavy_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="mass_tol" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="ratio" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="heavy2light_ratio" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="light_area" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="heavy_area" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="decimal_ratio" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "xpressratio_result")
public class XpressratioResult {

    @XmlAttribute(name = "light_firstscan", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long lightFirstscan;
    @XmlAttribute(name = "light_lastscan", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long lightLastscan;
    @XmlAttribute(name = "light_mass", required = true)
    protected float lightMass;
    @XmlAttribute(name = "heavy_firstscan", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long heavyFirstscan;
    @XmlAttribute(name = "heavy_lastscan", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long heavyLastscan;
    @XmlAttribute(name = "heavy_mass", required = true)
    protected float heavyMass;
    @XmlAttribute(name = "mass_tol", required = true)
    protected float massTol;
    @XmlAttribute(name = "ratio", required = true)
    protected String ratio;
    @XmlAttribute(name = "heavy2light_ratio", required = true)
    protected String heavy2LightRatio;
    @XmlAttribute(name = "light_area", required = true)
    protected float lightArea;
    @XmlAttribute(name = "heavy_area", required = true)
    protected float heavyArea;
    @XmlAttribute(name = "decimal_ratio", required = true)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "decimal")
    protected Double decimalRatio;

    /**
     * Gets the value of the lightFirstscan property.
     *
     */
    public long getLightFirstscan() {
        return lightFirstscan;
    }

    /**
     * Sets the value of the lightFirstscan property.
     *
     */
    public void setLightFirstscan(long value) {
        this.lightFirstscan = value;
    }

    /**
     * Gets the value of the lightLastscan property.
     *
     */
    public long getLightLastscan() {
        return lightLastscan;
    }

    /**
     * Sets the value of the lightLastscan property.
     *
     */
    public void setLightLastscan(long value) {
        this.lightLastscan = value;
    }

    /**
     * Gets the value of the lightMass property.
     *
     */
    public float getLightMass() {
        return lightMass;
    }

    /**
     * Sets the value of the lightMass property.
     *
     */
    public void setLightMass(float value) {
        this.lightMass = value;
    }

    /**
     * Gets the value of the heavyFirstscan property.
     *
     */
    public long getHeavyFirstscan() {
        return heavyFirstscan;
    }

    /**
     * Sets the value of the heavyFirstscan property.
     *
     */
    public void setHeavyFirstscan(long value) {
        this.heavyFirstscan = value;
    }

    /**
     * Gets the value of the heavyLastscan property.
     *
     */
    public long getHeavyLastscan() {
        return heavyLastscan;
    }

    /**
     * Sets the value of the heavyLastscan property.
     *
     */
    public void setHeavyLastscan(long value) {
        this.heavyLastscan = value;
    }

    /**
     * Gets the value of the heavyMass property.
     *
     */
    public float getHeavyMass() {
        return heavyMass;
    }

    /**
     * Sets the value of the heavyMass property.
     *
     */
    public void setHeavyMass(float value) {
        this.heavyMass = value;
    }

    /**
     * Gets the value of the massTol property.
     *
     */
    public float getMassTol() {
        return massTol;
    }

    /**
     * Sets the value of the massTol property.
     *
     */
    public void setMassTol(float value) {
        this.massTol = value;
    }

    /**
     * Gets the value of the ratio property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRatio() {
        return ratio;
    }

    /**
     * Sets the value of the ratio property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRatio(String value) {
        this.ratio = value;
    }

    /**
     * Gets the value of the heavy2LightRatio property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getHeavy2LightRatio() {
        return heavy2LightRatio;
    }

    /**
     * Sets the value of the heavy2LightRatio property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setHeavy2LightRatio(String value) {
        this.heavy2LightRatio = value;
    }

    /**
     * Gets the value of the lightArea property.
     *
     */
    public float getLightArea() {
        return lightArea;
    }

    /**
     * Sets the value of the lightArea property.
     *
     */
    public void setLightArea(float value) {
        this.lightArea = value;
    }

    /**
     * Gets the value of the heavyArea property.
     *
     */
    public float getHeavyArea() {
        return heavyArea;
    }

    /**
     * Sets the value of the heavyArea property.
     *
     */
    public void setHeavyArea(float value) {
        this.heavyArea = value;
    }

    /**
     * Gets the value of the decimalRatio property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Double getDecimalRatio() {
        return decimalRatio;
    }

    /**
     * Sets the value of the decimalRatio property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDecimalRatio(Double value) {
        this.decimalRatio = value;
    }

}
