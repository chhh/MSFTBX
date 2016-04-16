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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * The structure into which encoded binary data goes. Byte ordering is always little endian (Intel style). Computers using a different endian style must convert to/from little endian when writing/reading mzML
 * 
 * <p>Java class for BinaryDataArrayType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BinaryDataArrayType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psi.hupo.org/ms/mzml}ParamGroupType">
 *       &lt;sequence>
 *         &lt;element name="binary" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *       &lt;attribute name="arrayLength" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="dataProcessingRef" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="encodedLength" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BinaryDataArrayType", propOrder = {
    "binary"
})
public class BinaryDataArrayType
    extends ParamGroupType
{

    @XmlElement(required = true)
    protected byte[] binary;
    @XmlAttribute(name = "arrayLength")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger arrayLength;
    @XmlAttribute(name = "dataProcessingRef")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object dataProcessingRef;
    @XmlAttribute(name = "encodedLength", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger encodedLength;

    /**
     * Gets the value of the binary property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBinary() {
        return binary;
    }

    /**
     * Sets the value of the binary property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBinary(byte[] value) {
        this.binary = value;
    }

    /**
     * Gets the value of the arrayLength property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getArrayLength() {
        return arrayLength;
    }

    /**
     * Sets the value of the arrayLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setArrayLength(BigInteger value) {
        this.arrayLength = value;
    }

    /**
     * Gets the value of the dataProcessingRef property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDataProcessingRef() {
        return dataProcessingRef;
    }

    /**
     * Sets the value of the dataProcessingRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDataProcessingRef(Object value) {
        this.dataProcessingRef = value;
    }

    /**
     * Gets the value of the encodedLength property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEncodedLength() {
        return encodedLength;
    }

    /**
     * Sets the value of the encodedLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEncodedLength(BigInteger value) {
        this.encodedLength = value;
    }

}
