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
package umich.ms.fileio.filetypes.pepxml.jaxb.primitive;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inputfile" type="{http://regis-web.systemsbiology.net/pepXML}inputFileType" maxOccurs="unbounded"/>
 *         &lt;element name="roc_error_data" type="{http://regis-web.systemsbiology.net/pepXML}rocErrorDataType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mixturemodel" type="{http://regis-web.systemsbiology.net/pepXML}mixtureModelType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="options" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="est_tot_num_correct_psm" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="est_tot_num_correct_pep" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "inputfile",
    "rocErrorData",
    "mixturemodel"
})
@XmlRootElement(name = "interprophet_summary")
public class InterprophetSummary {

    @XmlElement(required = true)
    protected List<InputFileType> inputfile;
    @XmlElement(name = "roc_error_data")
    protected List<RocErrorDataType> rocErrorData;
    protected List<MixtureModelType> mixturemodel;
    @XmlAttribute(name = "version", required = true)
    protected String version;
    @XmlAttribute(name = "options")
    protected String options;
    @XmlAttribute(name = "est_tot_num_correct_psm")
    protected float estTotNumCorrectPsm;
    @XmlAttribute(name = "est_tot_num_correct_pep")
    protected float estTotNumCorrectPep;

    /**
     * Gets the value of the inputfile property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inputfile property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInputfile().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InputFileType }
     *
     *
     */
    public List<InputFileType> getInputfile() {
        if (inputfile == null) {
            inputfile = new ArrayList<InputFileType>(1);
        }
        return this.inputfile;
    }

    /**
     * Gets the value of the rocErrorData property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rocErrorData property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRocErrorData().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RocErrorDataType }
     *
     *
     */
    public List<RocErrorDataType> getRocErrorData() {
        if (rocErrorData == null) {
            rocErrorData = new ArrayList<RocErrorDataType>(1);
        }
        return this.rocErrorData;
    }

    /**
     * Gets the value of the mixturemodel property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mixturemodel property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMixturemodel().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MixtureModelType }
     *
     *
     */
    public List<MixtureModelType> getMixturemodel() {
        if (mixturemodel == null) {
            mixturemodel = new ArrayList<MixtureModelType>(1);
        }
        return this.mixturemodel;
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
     * Gets the value of the options property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOptions() {
        return options;
    }

    /**
     * Sets the value of the options property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOptions(String value) {
        this.options = value;
    }

    /**
     * Gets the value of the estTotNumCorrectPsm property.
     *
     */
    public float getEstTotNumCorrectPsm() {
        return estTotNumCorrectPsm;
    }

    /**
     * Sets the value of the estTotNumCorrectPsm property.
     *
     */
    public void setEstTotNumCorrectPsm(float value) {
        this.estTotNumCorrectPsm = value;
    }

    /**
     * Gets the value of the estTotNumCorrectPep property.
     *
     */
    public float getEstTotNumCorrectPep() {
        return estTotNumCorrectPep;
    }

    /**
     * Sets the value of the estTotNumCorrectPep property.
     *
     */
    public void setEstTotNumCorrectPep(float value) {
        this.estTotNumCorrectPep = value;
    }

}
