
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

package umich.ms.fileio.filetypes.mzml.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Description of a particular hardware configuration of a mass spectrometer. Each configuration must have one (and only one) of the three different components used for an analysis. For hybrid instruments, such as an LTQ-FT, there must be one configuration for each permutation of the components that is used in the document. For software configuration, use a ReferenceableParamGroup element.
 *
 * <p>Java class for InstrumentConfigurationType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="InstrumentConfigurationType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://psi.hupo.org/ms/mzml}ParamGroupType">
 *       &lt;sequence>
 *         &lt;element name="componentList" type="{http://psi.hupo.org/ms/mzml}ComponentListType" minOccurs="0"/>
 *         &lt;element name="softwareRef" type="{http://psi.hupo.org/ms/mzml}SoftwareRefType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="scanSettingsRef" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstrumentConfigurationType", propOrder = {
    "componentList",
    "softwareRef"
})
public class InstrumentConfigurationType
    extends ParamGroupType
{

    protected ComponentListType componentList;
    protected SoftwareRefType softwareRef;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "scanSettingsRef")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object scanSettingsRef;

    /**
     * Gets the value of the componentList property.
     *
     * @return
     *     possible object is
     *     {@link ComponentListType }
     *
     */
    public ComponentListType getComponentList() {
        return componentList;
    }

    /**
     * Sets the value of the componentList property.
     *
     * @param value
     *     allowed object is
     *     {@link ComponentListType }
     *
     */
    public void setComponentList(ComponentListType value) {
        this.componentList = value;
    }

    /**
     * Gets the value of the softwareRef property.
     *
     * @return
     *     possible object is
     *     {@link SoftwareRefType }
     *
     */
    public SoftwareRefType getSoftwareRef() {
        return softwareRef;
    }

    /**
     * Sets the value of the softwareRef property.
     *
     * @param value
     *     allowed object is
     *     {@link SoftwareRefType }
     *
     */
    public void setSoftwareRef(SoftwareRefType value) {
        this.softwareRef = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the scanSettingsRef property.
     *
     * @return
     *     possible object is
     *     {@link Object }
     *
     */
    public Object getScanSettingsRef() {
        return scanSettingsRef;
    }

    /**
     * Sets the value of the scanSettingsRef property.
     *
     * @param value
     *     allowed object is
     *     {@link Object }
     *
     */
    public void setScanSettingsRef(Object value) {
        this.scanSettingsRef = value;
    }

}
