
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

package umich.ms.fileio.filetypes.pepxml.jaxb.standard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="cross_linker_info" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="identifier" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="link_sites" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="isotope_labeled" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Y"/>
 *             &lt;enumeration value="N"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "crossLinkerInfo"
})
public class CrossLinker {

    @XmlElement(name = "cross_linker_info", required = true)
    protected List<NameValueType> crossLinkerInfo;
    @XmlAttribute(name = "identifier", required = true)
    protected String identifier;
    @XmlAttribute(name = "mass", required = true)
    protected float mass;
    @XmlAttribute(name = "link_sites", required = true)
    protected String linkSites;
    @XmlAttribute(name = "isotope_labeled", required = true)
    protected String isotopeLabeled;

    /**
     * Gets the value of the crossLinkerInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crossLinkerInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrossLinkerInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameValueType }
     * 
     * 
     */
    public List<NameValueType> getCrossLinkerInfo() {
        if (crossLinkerInfo == null) {
            crossLinkerInfo = new ArrayList<NameValueType>(1);
        }
        return this.crossLinkerInfo;
    }

    /**
     * Gets the value of the identifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifier(String value) {
        this.identifier = value;
    }

    /**
     * Gets the value of the mass property.
     * 
     */
    public float getMass() {
        return mass;
    }

    /**
     * Sets the value of the mass property.
     * 
     */
    public void setMass(float value) {
        this.mass = value;
    }

    /**
     * Gets the value of the linkSites property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkSites() {
        return linkSites;
    }

    /**
     * Sets the value of the linkSites property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkSites(String value) {
        this.linkSites = value;
    }

    /**
     * Gets the value of the isotopeLabeled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsotopeLabeled() {
        return isotopeLabeled;
    }

    /**
     * Sets the value of the isotopeLabeled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsotopeLabeled(String value) {
        this.isotopeLabeled = value;
    }

}
