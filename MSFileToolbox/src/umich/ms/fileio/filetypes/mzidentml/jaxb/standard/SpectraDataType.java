
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

package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A data set containing spectra data (consisting of one or more spectra). 
 * 
 * <p>Java class for SpectraDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SpectraDataType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}ExternalDataType">
 *       &lt;sequence>
 *         &lt;element name="SpectrumIDFormat" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SpectrumIDFormatType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpectraDataType", propOrder = {
    "spectrumIDFormat"
})
public class SpectraDataType
    extends ExternalDataType
{

    @XmlElement(name = "SpectrumIDFormat", required = true)
    protected SpectrumIDFormatType spectrumIDFormat;

    /**
     * Gets the value of the spectrumIDFormat property.
     * 
     * @return
     *     possible object is
     *     {@link SpectrumIDFormatType }
     *     
     */
    public SpectrumIDFormatType getSpectrumIDFormat() {
        return spectrumIDFormat;
    }

    /**
     * Sets the value of the spectrumIDFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpectrumIDFormatType }
     *     
     */
    public void setSpectrumIDFormat(SpectrumIDFormatType value) {
        this.spectrumIDFormat = value;
    }

}
