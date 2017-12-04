
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

package umich.ms.fileio.filetypes.pepxml.jaxb.primitive;

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
 *       &lt;attribute name="status" use="required" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="left_valley" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="right_valley" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="background" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="area" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="area_error" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="time" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="time_width" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="is_heavy" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "asapratio_lc_heavypeak")
public class AsapratioLcHeavypeak {

    @XmlAttribute(name = "status", required = true)
    protected byte status;
    @XmlAttribute(name = "left_valley", required = true)
    protected int leftValley;
    @XmlAttribute(name = "right_valley", required = true)
    protected int rightValley;
    @XmlAttribute(name = "background", required = true)
    protected double background;
    @XmlAttribute(name = "area", required = true)
    protected double area;
    @XmlAttribute(name = "area_error", required = true)
    protected double areaError;
    @XmlAttribute(name = "time", required = true)
    protected double time;
    @XmlAttribute(name = "time_width", required = true)
    protected double timeWidth;
    @XmlAttribute(name = "is_heavy", required = true)
    protected String isHeavy;

    /**
     * Gets the value of the status property.
     * 
     */
    public byte getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(byte value) {
        this.status = value;
    }

    /**
     * Gets the value of the leftValley property.
     * 
     */
    public int getLeftValley() {
        return leftValley;
    }

    /**
     * Sets the value of the leftValley property.
     * 
     */
    public void setLeftValley(int value) {
        this.leftValley = value;
    }

    /**
     * Gets the value of the rightValley property.
     * 
     */
    public int getRightValley() {
        return rightValley;
    }

    /**
     * Sets the value of the rightValley property.
     * 
     */
    public void setRightValley(int value) {
        this.rightValley = value;
    }

    /**
     * Gets the value of the background property.
     * 
     */
    public double getBackground() {
        return background;
    }

    /**
     * Sets the value of the background property.
     * 
     */
    public void setBackground(double value) {
        this.background = value;
    }

    /**
     * Gets the value of the area property.
     * 
     */
    public double getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     */
    public void setArea(double value) {
        this.area = value;
    }

    /**
     * Gets the value of the areaError property.
     * 
     */
    public double getAreaError() {
        return areaError;
    }

    /**
     * Sets the value of the areaError property.
     * 
     */
    public void setAreaError(double value) {
        this.areaError = value;
    }

    /**
     * Gets the value of the time property.
     * 
     */
    public double getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     */
    public void setTime(double value) {
        this.time = value;
    }

    /**
     * Gets the value of the timeWidth property.
     * 
     */
    public double getTimeWidth() {
        return timeWidth;
    }

    /**
     * Sets the value of the timeWidth property.
     * 
     */
    public void setTimeWidth(double value) {
        this.timeWidth = value;
    }

    /**
     * Gets the value of the isHeavy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsHeavy() {
        return isHeavy;
    }

    /**
     * Sets the value of the isHeavy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsHeavy(String value) {
        this.isHeavy = value;
    }

}
