
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
 *       &lt;attribute name="fvalue" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="obs_1_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="model_1_pos_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="model_1_neg_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="obs_2_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="model_2_pos_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="model_2_neg_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="obs_3_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="model_3_pos_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="model_3_neg_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="obs_4_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="model_4_pos_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="model_4_neg_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="obs_5_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="model_5_pos_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="model_5_neg_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="obs_6_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="model_6_pos_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="model_6_neg_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="obs_7_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="model_7_pos_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="model_7_neg_distr" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class DistributionPoint {

    @XmlAttribute(name = "fvalue", required = true)
    protected double fvalue;
    @XmlAttribute(name = "obs_1_distr", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer obs1Distr;
    @XmlAttribute(name = "model_1_pos_distr", required = true)
    protected double model1PosDistr;
    @XmlAttribute(name = "model_1_neg_distr", required = true)
    protected double model1NegDistr;
    @XmlAttribute(name = "obs_2_distr", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer obs2Distr;
    @XmlAttribute(name = "model_2_pos_distr", required = true)
    protected double model2PosDistr;
    @XmlAttribute(name = "model_2_neg_distr", required = true)
    protected double model2NegDistr;
    @XmlAttribute(name = "obs_3_distr", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer obs3Distr;
    @XmlAttribute(name = "model_3_pos_distr", required = true)
    protected double model3PosDistr;
    @XmlAttribute(name = "model_3_neg_distr", required = true)
    protected double model3NegDistr;
    @XmlAttribute(name = "obs_4_distr", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer obs4Distr;
    @XmlAttribute(name = "model_4_pos_distr", required = true)
    protected double model4PosDistr;
    @XmlAttribute(name = "model_4_neg_distr", required = true)
    protected double model4NegDistr;
    @XmlAttribute(name = "obs_5_distr", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer obs5Distr;
    @XmlAttribute(name = "model_5_pos_distr", required = true)
    protected double model5PosDistr;
    @XmlAttribute(name = "model_5_neg_distr", required = true)
    protected double model5NegDistr;
    @XmlAttribute(name = "obs_6_distr", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer obs6Distr;
    @XmlAttribute(name = "model_6_pos_distr", required = true)
    protected double model6PosDistr;
    @XmlAttribute(name = "model_6_neg_distr", required = true)
    protected double model6NegDistr;
    @XmlAttribute(name = "obs_7_distr", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Integer obs7Distr;
    @XmlAttribute(name = "model_7_pos_distr", required = true)
    protected double model7PosDistr;
    @XmlAttribute(name = "model_7_neg_distr", required = true)
    protected double model7NegDistr;

    /**
     * Gets the value of the fvalue property.
     * 
     */
    public double getFvalue() {
        return fvalue;
    }

    /**
     * Sets the value of the fvalue property.
     * 
     */
    public void setFvalue(double value) {
        this.fvalue = value;
    }

    /**
     * Gets the value of the obs1Distr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getObs1Distr() {
        return obs1Distr;
    }

    /**
     * Sets the value of the obs1Distr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObs1Distr(Integer value) {
        this.obs1Distr = value;
    }

    /**
     * Gets the value of the model1PosDistr property.
     * 
     */
    public double getModel1PosDistr() {
        return model1PosDistr;
    }

    /**
     * Sets the value of the model1PosDistr property.
     * 
     */
    public void setModel1PosDistr(double value) {
        this.model1PosDistr = value;
    }

    /**
     * Gets the value of the model1NegDistr property.
     * 
     */
    public double getModel1NegDistr() {
        return model1NegDistr;
    }

    /**
     * Sets the value of the model1NegDistr property.
     * 
     */
    public void setModel1NegDistr(double value) {
        this.model1NegDistr = value;
    }

    /**
     * Gets the value of the obs2Distr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getObs2Distr() {
        return obs2Distr;
    }

    /**
     * Sets the value of the obs2Distr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObs2Distr(Integer value) {
        this.obs2Distr = value;
    }

    /**
     * Gets the value of the model2PosDistr property.
     * 
     */
    public double getModel2PosDistr() {
        return model2PosDistr;
    }

    /**
     * Sets the value of the model2PosDistr property.
     * 
     */
    public void setModel2PosDistr(double value) {
        this.model2PosDistr = value;
    }

    /**
     * Gets the value of the model2NegDistr property.
     * 
     */
    public double getModel2NegDistr() {
        return model2NegDistr;
    }

    /**
     * Sets the value of the model2NegDistr property.
     * 
     */
    public void setModel2NegDistr(double value) {
        this.model2NegDistr = value;
    }

    /**
     * Gets the value of the obs3Distr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getObs3Distr() {
        return obs3Distr;
    }

    /**
     * Sets the value of the obs3Distr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObs3Distr(Integer value) {
        this.obs3Distr = value;
    }

    /**
     * Gets the value of the model3PosDistr property.
     * 
     */
    public double getModel3PosDistr() {
        return model3PosDistr;
    }

    /**
     * Sets the value of the model3PosDistr property.
     * 
     */
    public void setModel3PosDistr(double value) {
        this.model3PosDistr = value;
    }

    /**
     * Gets the value of the model3NegDistr property.
     * 
     */
    public double getModel3NegDistr() {
        return model3NegDistr;
    }

    /**
     * Sets the value of the model3NegDistr property.
     * 
     */
    public void setModel3NegDistr(double value) {
        this.model3NegDistr = value;
    }

    /**
     * Gets the value of the obs4Distr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getObs4Distr() {
        return obs4Distr;
    }

    /**
     * Sets the value of the obs4Distr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObs4Distr(Integer value) {
        this.obs4Distr = value;
    }

    /**
     * Gets the value of the model4PosDistr property.
     * 
     */
    public double getModel4PosDistr() {
        return model4PosDistr;
    }

    /**
     * Sets the value of the model4PosDistr property.
     * 
     */
    public void setModel4PosDistr(double value) {
        this.model4PosDistr = value;
    }

    /**
     * Gets the value of the model4NegDistr property.
     * 
     */
    public double getModel4NegDistr() {
        return model4NegDistr;
    }

    /**
     * Sets the value of the model4NegDistr property.
     * 
     */
    public void setModel4NegDistr(double value) {
        this.model4NegDistr = value;
    }

    /**
     * Gets the value of the obs5Distr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getObs5Distr() {
        return obs5Distr;
    }

    /**
     * Sets the value of the obs5Distr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObs5Distr(Integer value) {
        this.obs5Distr = value;
    }

    /**
     * Gets the value of the model5PosDistr property.
     * 
     */
    public double getModel5PosDistr() {
        return model5PosDistr;
    }

    /**
     * Sets the value of the model5PosDistr property.
     * 
     */
    public void setModel5PosDistr(double value) {
        this.model5PosDistr = value;
    }

    /**
     * Gets the value of the model5NegDistr property.
     * 
     */
    public double getModel5NegDistr() {
        return model5NegDistr;
    }

    /**
     * Sets the value of the model5NegDistr property.
     * 
     */
    public void setModel5NegDistr(double value) {
        this.model5NegDistr = value;
    }

    /**
     * Gets the value of the obs6Distr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getObs6Distr() {
        return obs6Distr;
    }

    /**
     * Sets the value of the obs6Distr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObs6Distr(Integer value) {
        this.obs6Distr = value;
    }

    /**
     * Gets the value of the model6PosDistr property.
     * 
     */
    public double getModel6PosDistr() {
        return model6PosDistr;
    }

    /**
     * Sets the value of the model6PosDistr property.
     * 
     */
    public void setModel6PosDistr(double value) {
        this.model6PosDistr = value;
    }

    /**
     * Gets the value of the model6NegDistr property.
     * 
     */
    public double getModel6NegDistr() {
        return model6NegDistr;
    }

    /**
     * Sets the value of the model6NegDistr property.
     * 
     */
    public void setModel6NegDistr(double value) {
        this.model6NegDistr = value;
    }

    /**
     * Gets the value of the obs7Distr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getObs7Distr() {
        return obs7Distr;
    }

    /**
     * Sets the value of the obs7Distr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObs7Distr(Integer value) {
        this.obs7Distr = value;
    }

    /**
     * Gets the value of the model7PosDistr property.
     * 
     */
    public double getModel7PosDistr() {
        return model7PosDistr;
    }

    /**
     * Sets the value of the model7PosDistr property.
     * 
     */
    public void setModel7PosDistr(double value) {
        this.model7PosDistr = value;
    }

    /**
     * Gets the value of the model7NegDistr property.
     * 
     */
    public double getModel7NegDistr() {
        return model7NegDistr;
    }

    /**
     * Sets the value of the model7NegDistr property.
     * 
     */
    public void setModel7NegDistr(double value) {
        this.model7NegDistr = value;
    }

}
