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
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * An array of values for a given type of measure and for a particular ion type, in parallel to the index of ions identified.
 *
 * <p>Java class for FragmentArrayType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FragmentArrayType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="values" use="required" type="{http://psidev.info/psi/pi/mzIdentML/1.2}listOfFloats" /&gt;
 *       &lt;attribute name="measure_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FragmentArrayType")
public class FragmentArrayType {

    @XmlAttribute(name = "values", required = true)
    protected List<Float> values;
    @XmlAttribute(name = "measure_ref", required = true)
    protected String measureRef;

    /**
     * Gets the value of the values property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the values property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValues().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Float }
     *
     *
     */
    public List<Float> getValues() {
        if (values == null) {
            values = new ArrayList<Float>(1);
        }
        return this.values;
    }

    /**
     * Gets the value of the measureRef property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMeasureRef() {
        return measureRef;
    }

    /**
     * Sets the value of the measureRef property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMeasureRef(String value) {
        this.measureRef = value;
    }

}
