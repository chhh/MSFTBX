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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * A database sequence from the specified SearchDatabase (nucleic acid or amino acid). If the sequence is nucleic acid, the source nucleic acid sequence
 * should be given in the seq attribute rather than a translated sequence.
 *
 * <p>Java class for DBSequenceType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DBSequenceType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType">
 *       &lt;sequence>
 *         &lt;element name="Seq" type="{http://psidev.info/psi/pi/mzIdentML/1.2}sequence" minOccurs="0"/>
 *         &lt;group ref="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamGroup" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="searchDatabase_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="accession" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DBSequenceType", propOrder = {
    "seq",
    "paramGroup"
})
public class DBSequenceType
    extends IdentifiableType
{

    @XmlElement(name = "Seq")
    protected String seq;
    @XmlElements({
        @XmlElement(name = "cvParam", type = CVParamType.class),
        @XmlElement(name = "userParam", type = UserParamType.class)
    })
    protected List<AbstractParamType> paramGroup;
    @XmlAttribute(name = "length")
    protected int length;
    @XmlAttribute(name = "searchDatabase_ref", required = true)
    protected String searchDatabaseRef;
    @XmlAttribute(name = "accession", required = true)
    protected String accession;

    /**
     * Gets the value of the seq property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSeq() {
        return seq;
    }

    /**
     * Sets the value of the seq property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSeq(String value) {
        this.seq = value;
    }

    /**
     * Additional descriptors for the sequence, such as taxon, description line etc.Gets the value of the paramGroup property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paramGroup property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParamGroup().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CVParamType }
     * {@link UserParamType }
     *
     *
     */
    public List<AbstractParamType> getParamGroup() {
        if (paramGroup == null) {
            paramGroup = new ArrayList<AbstractParamType>(1);
        }
        return this.paramGroup;
    }

    /**
     * Gets the value of the length property.
     *
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     *
     */
    public void setLength(int value) {
        this.length = value;
    }

    /**
     * Gets the value of the searchDatabaseRef property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSearchDatabaseRef() {
        return searchDatabaseRef;
    }

    /**
     * Sets the value of the searchDatabaseRef property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSearchDatabaseRef(String value) {
        this.searchDatabaseRef = value;
    }

    /**
     * Gets the value of the accession property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAccession() {
        return accession;
    }

    /**
     * Sets the value of the accession property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAccession(String value) {
        this.accession = value;
    }

}
