
package umich.ms.fileio.filetypes.mzidentml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A specification of how a nucleic acid sequence database was translated for searching.
 *
 * <p>Java class for DatabaseTranslationType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DatabaseTranslationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TranslationTable" type="{http://psidev.info/psi/pi/mzIdentML/1.2}TranslationTableType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="frames" type="{http://psidev.info/psi/pi/mzIdentML/1.2}listOfAllowedFrames" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatabaseTranslationType", propOrder = {
    "translationTable"
})
public class DatabaseTranslationType {

    @XmlElement(name = "TranslationTable", required = true)
    protected List<TranslationTableType> translationTable;
    @XmlAttribute(name = "frames")
    protected List<Integer> frames;

    /**
     * Gets the value of the translationTable property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the translationTable property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTranslationTable().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TranslationTableType }
     *
     *
     */
    public List<TranslationTableType> getTranslationTable() {
        if (translationTable == null) {
            translationTable = new ArrayList<TranslationTableType>(1);
        }
        return this.translationTable;
    }

    /**
     * Gets the value of the frames property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the frames property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFrames().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     *
     *
     */
    public List<Integer> getFrames() {
        if (frames == null) {
            frames = new ArrayList<Integer>(1);
        }
        return this.frames;
    }

}
