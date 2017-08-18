//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.08.18 at 01:26:07 PM PDT 
//


package umich.ms.io.type.mzidentml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 *  A description of the sample analysed by mass spectrometry using CVParams or UserParams. If a composite sample has been analysed, a parent sample should be defined, which references subsamples. This represents any kind of substance used in an experimental workflow, such as whole organisms, cells, DNA, solutions, compounds and experimental substances (gels, arrays etc.).	
 * 
 * <p>Java class for SampleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SampleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ContactRole" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ContactRoleType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SubSample" type="{http://psidev.info/psi/pi/mzIdentML/1.2}SubSampleType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;group ref="{http://psidev.info/psi/pi/mzIdentML/1.2}ParamGroup" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SampleType", namespace = "http://psidev.info/psi/pi/mzIdentML/1.2", propOrder = {
    "contactRole",
    "subSample",
    "paramGroup"
})
public class SampleType
    extends IdentifiableType
{

    @XmlElement(name = "ContactRole", namespace = "http://psidev.info/psi/pi/mzIdentML/1.2")
    protected List<ContactRoleType> contactRole;
    @XmlElement(name = "SubSample", namespace = "http://psidev.info/psi/pi/mzIdentML/1.2")
    protected List<SubSampleType> subSample;
    @XmlElements({
        @XmlElement(name = "cvParam", namespace = "http://psidev.info/psi/pi/mzIdentML/1.2", type = CVParamType.class),
        @XmlElement(name = "userParam", namespace = "http://psidev.info/psi/pi/mzIdentML/1.2", type = UserParamType.class)
    })
    protected List<AbstractParamType> paramGroup;

    /**
     * Gets the value of the contactRole property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contactRole property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContactRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContactRoleType }
     * 
     * 
     */
    public List<ContactRoleType> getContactRole() {
        if (contactRole == null) {
            contactRole = new ArrayList<ContactRoleType>();
        }
        return this.contactRole;
    }

    /**
     * Gets the value of the subSample property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subSample property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubSample().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubSampleType }
     * 
     * 
     */
    public List<SubSampleType> getSubSample() {
        if (subSample == null) {
            subSample = new ArrayList<SubSampleType>();
        }
        return this.subSample;
    }

    /**
     * The characteristics of a
     * Material.Gets the value of the paramGroup property.
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
            paramGroup = new ArrayList<AbstractParamType>();
        }
        return this.paramGroup;
    }

}
