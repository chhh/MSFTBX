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
package umich.ms.fileio.filetypes.mzidentml.jaxb.primitive;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * IonType defines the index of fragmentation ions being reported, importing a CV term for the type of ion e.g. b ion. Example: if b3 b7 b8 and b10 have been identified, the index attribute will contain 3 7 8 10, and the corresponding values will be reported in parallel arrays below
 *
 * <p>Java class for IonTypeType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="IonTypeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FragmentArray" type="{http://psidev.info/psi/pi/mzIdentML/1.2}FragmentArrayType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cvParam" type="{http://psidev.info/psi/pi/mzIdentML/1.2}CVParamType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="index" type="{http://psidev.info/psi/pi/mzIdentML/1.2}listOfIntegers" />
 *       &lt;attribute name="charge" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IonTypeType", propOrder = {
    "fragmentArray",
    "cvParam"
})
public class IonTypeType {

    @XmlElement(name = "FragmentArray")
    protected List<FragmentArrayType> fragmentArray;
    @XmlElement(required = true)
    protected List<CVParamType> cvParam;
    @XmlAttribute(name = "index")
    protected List<String> index;
    @XmlAttribute(name = "charge", required = true)
    protected int charge;

    /**
     * Gets the value of the fragmentArray property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fragmentArray property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFragmentArray().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FragmentArrayType }
     *
     *
     */
    public List<FragmentArrayType> getFragmentArray() {
        if (fragmentArray == null) {
            fragmentArray = new ArrayList<FragmentArrayType>(1);
        }
        return this.fragmentArray;
    }

    /**
     * Gets the value of the cvParam property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cvParam property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCvParam().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CVParamType }
     *
     *
     */
    public List<CVParamType> getCvParam() {
        if (cvParam == null) {
            cvParam = new ArrayList<CVParamType>(1);
        }
        return this.cvParam;
    }

    /**
     * Gets the value of the index property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the index property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIndex().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     *
     *
     */
    public List<String> getIndex() {
        if (index == null) {
            index = new ArrayList<String>(1);
        }
        return this.index;
    }

    /**
     * Gets the value of the charge property.
     *
     */
    public int getCharge() {
        return charge;
    }

    /**
     * Sets the value of the charge property.
     *
     */
    public void setCharge(int value) {
        this.charge = value;
    }

}
