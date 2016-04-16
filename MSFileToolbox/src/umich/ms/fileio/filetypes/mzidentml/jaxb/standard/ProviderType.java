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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The provider of the document in terms of the Contact and the software the produced the document instance. 
 * 
 * <p>Java class for ProviderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProviderType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://psidev.info/psi/pi/mzIdentML/1.2}IdentifiableType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ContactRole" type="{http://psidev.info/psi/pi/mzIdentML/1.2}ContactRoleType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="analysisSoftware_ref" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProviderType", propOrder = {
    "contactRole"
})
public class ProviderType
    extends IdentifiableType
{

    @XmlElement(name = "ContactRole")
    protected ContactRoleType contactRole;
    @XmlAttribute(name = "analysisSoftware_ref")
    protected String analysisSoftwareRef;

    /**
     * Gets the value of the contactRole property.
     * 
     * @return
     *     possible object is
     *     {@link ContactRoleType }
     *     
     */
    public ContactRoleType getContactRole() {
        return contactRole;
    }

    /**
     * Sets the value of the contactRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactRoleType }
     *     
     */
    public void setContactRole(ContactRoleType value) {
        this.contactRole = value;
    }

    /**
     * Gets the value of the analysisSoftwareRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnalysisSoftwareRef() {
        return analysisSoftwareRef;
    }

    /**
     * Sets the value of the analysisSoftwareRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnalysisSoftwareRef(String value) {
        this.analysisSoftwareRef = value;
    }

}
