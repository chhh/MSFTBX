
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
import javax.xml.bind.annotation.XmlType;


/**
 * An Analysis which tries to identify peptides in input spectra, referencing the database searched, the input spectra, the output results and the protocol that is run. 
 * 
 * <p>Java class for SpectrumIdentificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SpectrumIdentificationType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}ProtocolApplicationType">
 *       &lt;sequence>
 *         &lt;element name="InputSpectra" type="{http://psidev.info/psi/pi/mzIdentML/1.2}InputSpectraType" maxOccurs="unbounded"/>
 *         &lt;element name="SearchDatabaseRef" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SearchDatabaseRefType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="spectrumIdentificationProtocol_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="spectrumIdentificationList_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpectrumIdentificationType", propOrder = {
    "inputSpectra",
    "searchDatabaseRef"
})
public class SpectrumIdentificationType
    extends ProtocolApplicationType
{

    @XmlElement(name = "InputSpectra", required = true)
    protected List<InputSpectraType> inputSpectra;
    @XmlElement(name = "SearchDatabaseRef", required = true)
    protected List<SearchDatabaseRefType> searchDatabaseRef;
    @XmlAttribute(name = "spectrumIdentificationProtocol_ref", required = true)
    protected String spectrumIdentificationProtocolRef;
    @XmlAttribute(name = "spectrumIdentificationList_ref", required = true)
    protected String spectrumIdentificationListRef;

    /**
     * Gets the value of the inputSpectra property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inputSpectra property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInputSpectra().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InputSpectraType }
     * 
     * 
     */
    public List<InputSpectraType> getInputSpectra() {
        if (inputSpectra == null) {
            inputSpectra = new ArrayList<InputSpectraType>(1);
        }
        return this.inputSpectra;
    }

    /**
     * Gets the value of the searchDatabaseRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchDatabaseRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchDatabaseRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchDatabaseRefType }
     * 
     * 
     */
    public List<SearchDatabaseRefType> getSearchDatabaseRef() {
        if (searchDatabaseRef == null) {
            searchDatabaseRef = new ArrayList<SearchDatabaseRefType>(1);
        }
        return this.searchDatabaseRef;
    }

    /**
     * Gets the value of the spectrumIdentificationProtocolRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpectrumIdentificationProtocolRef() {
        return spectrumIdentificationProtocolRef;
    }

    /**
     * Sets the value of the spectrumIdentificationProtocolRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpectrumIdentificationProtocolRef(String value) {
        this.spectrumIdentificationProtocolRef = value;
    }

    /**
     * Gets the value of the spectrumIdentificationListRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpectrumIdentificationListRef() {
        return spectrumIdentificationListRef;
    }

    /**
     * Sets the value of the spectrumIdentificationListRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpectrumIdentificationListRef(String value) {
        this.spectrumIdentificationListRef = value;
    }

}
