
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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The collection of sequences (DBSequence or Peptide) identified and their relationship between each other (PeptideEvidence) to be referenced elsewhere in the results. 
 * 
 * <p>Java class for SequenceCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SequenceCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DBSequence" type="{http://psidev.info/psi/pi/mzIdentML/1.2}DBSequenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Peptide" type="{http://psidev.info/psi/pi/mzIdentML/1.2}PeptideType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PeptideEvidence" type="{http://psidev.info/psi/pi/mzIdentML/1.2}PeptideEvidenceType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SequenceCollectionType", propOrder = {
    "dbSequence",
    "peptide",
    "peptideEvidence"
})
public class SequenceCollectionType {

    @XmlElement(name = "DBSequence")
    protected List<DBSequenceType> dbSequence;
    @XmlElement(name = "Peptide")
    protected List<PeptideType> peptide;
    @XmlElement(name = "PeptideEvidence")
    protected List<PeptideEvidenceType> peptideEvidence;

    /**
     * Gets the value of the dbSequence property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dbSequence property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDBSequence().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DBSequenceType }
     * 
     * 
     */
    public List<DBSequenceType> getDBSequence() {
        if (dbSequence == null) {
            dbSequence = new ArrayList<DBSequenceType>(1);
        }
        return this.dbSequence;
    }

    /**
     * Gets the value of the peptide property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the peptide property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeptide().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeptideType }
     * 
     * 
     */
    public List<PeptideType> getPeptide() {
        if (peptide == null) {
            peptide = new ArrayList<PeptideType>(1);
        }
        return this.peptide;
    }

    /**
     * Gets the value of the peptideEvidence property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the peptideEvidence property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeptideEvidence().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeptideEvidenceType }
     * 
     * 
     */
    public List<PeptideEvidenceType> getPeptideEvidence() {
        if (peptideEvidence == null) {
            peptideEvidence = new ArrayList<PeptideEvidenceType>(1);
        }
        return this.peptideEvidence;
    }

}
