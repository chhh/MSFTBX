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
package umich.ms.fileio.filetypes.mzml.jaxb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * List of binary data arrays.
 * 
 * <p>Java class for BinaryDataArrayListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BinaryDataArrayListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="binaryDataArray" type="{http://psi.hupo.org/ms/mzml}BinaryDataArrayType" maxOccurs="unbounded" minOccurs="2"/>
 *       &lt;/sequence>
 *       &lt;attribute name="count" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BinaryDataArrayListType", propOrder = {
    "binaryDataArray"
})
public class BinaryDataArrayListType {

    @XmlElement(required = true)
    protected List<BinaryDataArrayType> binaryDataArray;
    @XmlAttribute(name = "count", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger count;

    /**
     * Gets the value of the binaryDataArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the binaryDataArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBinaryDataArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BinaryDataArrayType }
     * 
     * 
     */
    public List<BinaryDataArrayType> getBinaryDataArray() {
        if (binaryDataArray == null) {
            binaryDataArray = new ArrayList<BinaryDataArrayType>();
        }
        return this.binaryDataArray;
    }

    /**
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCount(BigInteger value) {
        this.count = value;
    }

}
