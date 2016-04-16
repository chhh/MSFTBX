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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The collection of protocols which include the parameters and settings of the performed analyses.
 *
 * <p>Java class for AnalysisProtocolCollectionType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AnalysisProtocolCollectionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SpectrumIdentificationProtocol" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SpectrumIdentificationProtocolType" maxOccurs="unbounded"/&gt;
 *         &lt;element name="ProteinDetectionProtocol" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ProteinDetectionProtocolType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalysisProtocolCollectionType", propOrder = {
    "spectrumIdentificationProtocol",
    "proteinDetectionProtocol"
})
public class AnalysisProtocolCollectionType {

    @XmlElement(name = "SpectrumIdentificationProtocol", required = true)
    protected List<SpectrumIdentificationProtocolType> spectrumIdentificationProtocol;
    @XmlElement(name = "ProteinDetectionProtocol")
    protected ProteinDetectionProtocolType proteinDetectionProtocol;

    /**
     * Gets the value of the spectrumIdentificationProtocol property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spectrumIdentificationProtocol property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpectrumIdentificationProtocol().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpectrumIdentificationProtocolType }
     *
     *
     */
    public List<SpectrumIdentificationProtocolType> getSpectrumIdentificationProtocol() {
        if (spectrumIdentificationProtocol == null) {
            spectrumIdentificationProtocol = new ArrayList<SpectrumIdentificationProtocolType>(1);
        }
        return this.spectrumIdentificationProtocol;
    }

    /**
     * Gets the value of the proteinDetectionProtocol property.
     *
     * @return
     *     possible object is
     *     {@link ProteinDetectionProtocolType }
     *
     */
    public ProteinDetectionProtocolType getProteinDetectionProtocol() {
        return proteinDetectionProtocol;
    }

    /**
     * Sets the value of the proteinDetectionProtocol property.
     *
     * @param value
     *     allowed object is
     *     {@link ProteinDetectionProtocolType }
     *
     */
    public void setProteinDetectionProtocol(ProteinDetectionProtocolType value) {
        this.proteinDetectionProtocol = value;
    }

}
