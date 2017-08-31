
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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tag for encapsulating roc curves for pepXML
 * 
 * <p>Java class for rocErrorDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rocErrorDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="roc_data_point" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="min_prob" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="sensitivity" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="error" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="num_corr" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *                 &lt;attribute name="num_incorr" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="error_point" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="error" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="min_prob" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="num_corr" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *                 &lt;attribute name="num_incorr" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="charge" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="charge_est_correct" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rocErrorDataType", propOrder = {
    "rocDataPoint",
    "errorPoint"
})
public class RocErrorDataType {

    @XmlElement(name = "roc_data_point")
    protected List<RocDataPoint> rocDataPoint;
    @XmlElement(name = "error_point")
    protected List<ErrorPoint> errorPoint;
    @XmlAttribute(name = "charge", required = true)
    protected String charge;
    @XmlAttribute(name = "charge_est_correct")
    protected float chargeEstCorrect;

    /**
     * Gets the value of the rocDataPoint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rocDataPoint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRocDataPoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RocDataPoint }
     * 
     * 
     */
    public List<RocDataPoint> getRocDataPoint() {
        if (rocDataPoint == null) {
            rocDataPoint = new ArrayList<RocDataPoint>(1);
        }
        return this.rocDataPoint;
    }

    /**
     * Gets the value of the errorPoint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorPoint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorPoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErrorPoint }
     * 
     * 
     */
    public List<ErrorPoint> getErrorPoint() {
        if (errorPoint == null) {
            errorPoint = new ArrayList<ErrorPoint>(1);
        }
        return this.errorPoint;
    }

    /**
     * Gets the value of the charge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharge() {
        return charge;
    }

    /**
     * Sets the value of the charge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharge(String value) {
        this.charge = value;
    }

    /**
     * Gets the value of the chargeEstCorrect property.
     * 
     */
    public float getChargeEstCorrect() {
        return chargeEstCorrect;
    }

    /**
     * Sets the value of the chargeEstCorrect property.
     * 
     */
    public void setChargeEstCorrect(float value) {
        this.chargeEstCorrect = value;
    }

}
