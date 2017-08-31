
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

package umich.ms.fileio.filetypes.pepxml.jaxb.primitive;

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
 *         &lt;element name="search_hit" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="alternative_protein" type="{http://regis-web.systemsbiology.net/pepXML}altProteinDataType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/pepXML}modInfoDataType" minOccurs="0"/>
 *                   &lt;element name="xlink" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="linked_peptide" maxOccurs="2" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="alternative_protein" type="{http://regis-web.systemsbiology.net/pepXML}altProteinDataType" maxOccurs="unbounded" minOccurs="0"/>
 *                                       &lt;element name="modification_info" type="{http://regis-web.systemsbiology.net/pepXML}modInfoDataType" minOccurs="0"/>
 *                                       &lt;element name="xlink_score" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="peptide" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="peptide_prev_aa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="peptide_next_aa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="protein" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="num_tot_proteins" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *                                     &lt;attribute name="calc_neutral_pep_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                     &lt;attribute name="complement_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                                     &lt;attribute name="designation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="xlink_score" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="identifier" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="search_score" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="analysis_result" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;any processContents='lax' maxOccurs="unbounded"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="analysis" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="id" type="{http://regis-web.systemsbiology.net/pepXML}positiveInt" default="1" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="parameter" type="{http://regis-web.systemsbiology.net/pepXML}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="hit_rank" use="required" type="{http://regis-web.systemsbiology.net/pepXML}positiveInt" />
 *                 &lt;attribute name="peptide" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="peptide_prev_aa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="peptide_next_aa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="protein" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="num_tot_proteins" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *                 &lt;attribute name="num_matched_ions" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="tot_num_ions" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="calc_neutral_pep_mass" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="massdiff" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="num_tol_term" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="num_missed_cleavages" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="num_matched_peptides" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="xlink_type">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;enumeration value="na"/>
 *                       &lt;enumeration value="xl"/>
 *                       &lt;enumeration value="loop"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="is_rejected" default="0">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger">
 *                       &lt;enumeration value="0"/>
 *                       &lt;enumeration value="1"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="protein_descr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="calc_pI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="protein_mw" type="{http://www.w3.org/2001/XMLSchema}double" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="search_id" type="{http://regis-web.systemsbiology.net/pepXML}positiveInt" default="1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "searchHit"
})
public class SearchResult {

    @XmlElement(name = "search_hit")
    protected List<SearchHit> searchHit;
    @XmlAttribute(name = "search_id")
    protected long searchId;

    /**
     * Gets the value of the searchHit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchHit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchHit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchHit }
     * 
     * 
     */
    public List<SearchHit> getSearchHit() {
        if (searchHit == null) {
            searchHit = new ArrayList<SearchHit>(1);
        }
        return this.searchHit;
    }

    /**
     * Gets the value of the searchId property.
     * 
     */
    public long getSearchId() {
        return searchId;
    }

    /**
     * Sets the value of the searchId property.
     * 
     */
    public void setSearchId(long value) {
        this.searchId = value;
    }

}
