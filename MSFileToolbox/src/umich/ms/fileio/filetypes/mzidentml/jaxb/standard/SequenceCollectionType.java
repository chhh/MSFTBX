
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

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
 * &lt;complexType name="SequenceCollectionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DBSequence" type="{http://psidev.info/psi/pi/mzIdentML/1.2}DBSequenceType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Peptide" type="{http://psidev.info/psi/pi/mzIdentML/1.2}PeptideType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="PeptideEvidence" type="{http://psidev.info/psi/pi/mzIdentML/1.2}PeptideEvidenceType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SequenceCollectionType", propOrder = {
    "dbSequences",
    "peptides",
    "peptideEvidences"
})
public class SequenceCollectionType {

    @XmlElement(name = "DBSequence")
    protected List<DBSequenceType> dbSequences;
    @XmlElement(name = "Peptide")
    protected List<PeptideType> peptides;
    @XmlElement(name = "PeptideEvidence")
    protected List<PeptideEvidenceType> peptideEvidences;

    /**
     * Gets the value of the dbSequences property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dbSequences property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDBSequences().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DBSequenceType }
     * 
     * 
     */
    public List<DBSequenceType> getDBSequences() {
        if (dbSequences == null) {
            dbSequences = new ArrayList<DBSequenceType>();
        }
        return this.dbSequences;
    }

    /**
     * Gets the value of the peptides property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the peptides property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeptides().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeptideType }
     * 
     * 
     */
    public List<PeptideType> getPeptides() {
        if (peptides == null) {
            peptides = new ArrayList<PeptideType>();
        }
        return this.peptides;
    }

    /**
     * Gets the value of the peptideEvidences property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the peptideEvidences property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeptideEvidences().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeptideEvidenceType }
     * 
     * 
     */
    public List<PeptideEvidenceType> getPeptideEvidences() {
        if (peptideEvidences == null) {
            peptideEvidences = new ArrayList<PeptideEvidenceType>();
        }
        return this.peptideEvidences;
    }

}
