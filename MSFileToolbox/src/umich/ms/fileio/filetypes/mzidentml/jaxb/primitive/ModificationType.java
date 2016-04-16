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
 * A molecule modification specification. If n modifications have been found on a peptide, there should be n instances of Modification. If multiple modifications are provided as cvParams, it is assumed that the modification is ambiguous i.e. one modification or another. A cvParam must be provided with the identification of the modification sourced from a suitable CV e.g. UNIMOD. If the modification is not present in the CV (and this will be checked by the semantic validator within a given tolerance window), there is a “unknown modification�\u009d CV term that must be used instead. A neutral loss should be defined as an additional CVParam within Modification. If more complex information should be given about neutral losses (such as presence/absence on particular product ions), this can additionally be encoded within the FragmentationArray.
 *
 * <p>Java class for ModificationType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ModificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cvParam" type="{http://psidev.info/psi/pi/mzIdentML/1.2}CVParamType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="location" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="residues" type="{http://psidev.info/psi/pi/mzIdentML/1.2}listOfChars" />
 *       &lt;attribute name="avgMassDelta" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="monoisotopicMassDelta" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModificationType", propOrder = {
    "cvParam"
})
public class ModificationType {

    @XmlElement(required = true)
    protected List<CVParamType> cvParam;
    @XmlAttribute(name = "location")
    protected int location;
    @XmlAttribute(name = "residues")
    protected List<String> residues;
    @XmlAttribute(name = "avgMassDelta")
    protected double avgMassDelta;
    @XmlAttribute(name = "monoisotopicMassDelta")
    protected double monoisotopicMassDelta;

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
     * Gets the value of the location property.
     *
     */
    public int getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     *
     */
    public void setLocation(int value) {
        this.location = value;
    }

    /**
     * Gets the value of the residues property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the residues property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResidues().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     *
     *
     */
    public List<String> getResidues() {
        if (residues == null) {
            residues = new ArrayList<String>(1);
        }
        return this.residues;
    }

    /**
     * Gets the value of the avgMassDelta property.
     *
     */
    public double getAvgMassDelta() {
        return avgMassDelta;
    }

    /**
     * Sets the value of the avgMassDelta property.
     *
     */
    public void setAvgMassDelta(double value) {
        this.avgMassDelta = value;
    }

    /**
     * Gets the value of the monoisotopicMassDelta property.
     *
     */
    public double getMonoisotopicMassDelta() {
        return monoisotopicMassDelta;
    }

    /**
     * Sets the value of the monoisotopicMassDelta property.
     *
     */
    public void setMonoisotopicMassDelta(double value) {
        this.monoisotopicMassDelta = value;
    }

}
