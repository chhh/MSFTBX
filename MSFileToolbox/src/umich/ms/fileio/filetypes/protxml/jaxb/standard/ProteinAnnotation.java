
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

package umich.ms.fileio.filetypes.protxml.jaxb.standard;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for protein_annotation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="protein_annotation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="protein_description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ipi_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="refseq_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="swissprot_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ensembl_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="trembl_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="locus_link_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="flybase" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "protein_annotation")
public class ProteinAnnotation {

    @XmlAttribute(name = "protein_description", required = true)
    protected String proteinDescription;
    @XmlAttribute(name = "ipi_name")
    protected String ipiName;
    @XmlAttribute(name = "refseq_name")
    protected String refseqName;
    @XmlAttribute(name = "swissprot_name")
    protected String swissprotName;
    @XmlAttribute(name = "ensembl_name")
    protected String ensemblName;
    @XmlAttribute(name = "trembl_name")
    protected String tremblName;
    @XmlAttribute(name = "locus_link_name")
    protected String locusLinkName;
    @XmlAttribute(name = "flybase")
    protected String flybase;

    /**
     * Gets the value of the proteinDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProteinDescription() {
        return proteinDescription;
    }

    /**
     * Sets the value of the proteinDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProteinDescription(String value) {
        this.proteinDescription = value;
    }

    /**
     * Gets the value of the ipiName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpiName() {
        return ipiName;
    }

    /**
     * Sets the value of the ipiName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpiName(String value) {
        this.ipiName = value;
    }

    /**
     * Gets the value of the refseqName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefseqName() {
        return refseqName;
    }

    /**
     * Sets the value of the refseqName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefseqName(String value) {
        this.refseqName = value;
    }

    /**
     * Gets the value of the swissprotName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwissprotName() {
        return swissprotName;
    }

    /**
     * Sets the value of the swissprotName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwissprotName(String value) {
        this.swissprotName = value;
    }

    /**
     * Gets the value of the ensemblName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnsemblName() {
        return ensemblName;
    }

    /**
     * Sets the value of the ensemblName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnsemblName(String value) {
        this.ensemblName = value;
    }

    /**
     * Gets the value of the tremblName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTremblName() {
        return tremblName;
    }

    /**
     * Sets the value of the tremblName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTremblName(String value) {
        this.tremblName = value;
    }

    /**
     * Gets the value of the locusLinkName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocusLinkName() {
        return locusLinkName;
    }

    /**
     * Sets the value of the locusLinkName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocusLinkName(String value) {
        this.locusLinkName = value;
    }

    /**
     * Gets the value of the flybase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlybase() {
        return flybase;
    }

    /**
     * Sets the value of the flybase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlybase(String value) {
        this.flybase = value;
    }

}
