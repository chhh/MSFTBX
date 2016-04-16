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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The list of enzymes used in experiment
 *
 * <p>Java class for EnzymesType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="EnzymesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Enzyme" type="{http://psidev.info/psi/pi/mzIdentML/1.2}EnzymeType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="independent" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnzymesType", propOrder = {
    "enzyme"
})
public class EnzymesType {

    @XmlElement(name = "Enzyme", required = true)
    protected List<EnzymeType> enzyme;
    @XmlAttribute(name = "independent")
    protected Boolean independent;

    /**
     * Gets the value of the enzyme property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enzyme property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnzyme().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnzymeType }
     *
     *
     */
    public List<EnzymeType> getEnzyme() {
        if (enzyme == null) {
            enzyme = new ArrayList<EnzymeType>(1);
        }
        return this.enzyme;
    }

    /**
     * Gets the value of the independent property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIndependent() {
        return independent;
    }

    /**
     * Sets the value of the independent property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIndependent(Boolean value) {
        this.independent = value;
    }

}
