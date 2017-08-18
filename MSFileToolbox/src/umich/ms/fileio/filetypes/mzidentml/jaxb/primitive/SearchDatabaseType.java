
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

package umich.ms.fileio.filetypes.mzidentml.jaxb.primitive;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A database for searching mass spectra. Examples include a set of amino acid sequence entries, nucleotide databases (e.g. 6 frame translated) or annotated spectra libraries. 
 * 
 * <p>Java class for SearchDatabaseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchDatabaseType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}ExternalDataType">
 *       &lt;sequence>
 *         &lt;element name="DatabaseName" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamType"/>
 *         &lt;element name="cvParam" type="{http://psidev.info/psi/pi/mzIdentML/1.2}CVParamType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="releaseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="numDatabaseSequences" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="numResidues" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchDatabaseType", propOrder = {
    "databaseName",
    "cvParam"
})
public class SearchDatabaseType
    extends ExternalDataType
{

    @XmlElement(name = "DatabaseName", required = true)
    protected ParamType databaseName;
    protected List<CVParamType> cvParam;
    @XmlAttribute(name = "version")
    protected String version;
    @XmlAttribute(name = "releaseDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar releaseDate;
    @XmlAttribute(name = "numDatabaseSequences")
    protected long numDatabaseSequences;
    @XmlAttribute(name = "numResidues")
    protected long numResidues;

    /**
     * Gets the value of the databaseName property.
     * 
     * @return
     *     possible object is
     *     {@link ParamType }
     *     
     */
    public ParamType getDatabaseName() {
        return databaseName;
    }

    /**
     * Sets the value of the databaseName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamType }
     *     
     */
    public void setDatabaseName(ParamType value) {
        this.databaseName = value;
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
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the releaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets the value of the releaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReleaseDate(XMLGregorianCalendar value) {
        this.releaseDate = value;
    }

    /**
     * Gets the value of the numDatabaseSequences property.
     * 
     */
    public long getNumDatabaseSequences() {
        return numDatabaseSequences;
    }

    /**
     * Sets the value of the numDatabaseSequences property.
     * 
     */
    public void setNumDatabaseSequences(long value) {
        this.numDatabaseSequences = value;
    }

    /**
     * Gets the value of the numResidues property.
     * 
     */
    public long getNumResidues() {
        return numResidues;
    }

    /**
     * Sets the value of the numResidues property.
     * 
     */
    public void setNumResidues(long value) {
        this.numResidues = value;
    }

}
