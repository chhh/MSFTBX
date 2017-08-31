# Prot.XML schema to java sources generation
Use `xjc` as usual.  

# Generated with
### v7
`xjc.exe -b bindings_protxml_standard.xml 
-d "D:\projects\BatMass\MSFTBX\MSFileToolbox\src" -p umich.ms.fileio.filetypes.protxml.jaxb.standard protXML_v7-fixed.xsd`  

### v8
- Standard:  
  `xjc.exe -b bindings_protxml_standard.xml 
-no-header -encoding UTF-8 -extension 
-d "C:\projects\BatMass\MSFTBX\MSFileToolbox\src" 
-p umich.ms.fileio.filetypes.protxml.jaxb.standard 
protXML_v8-fixed.xsd`  

- Primitive:    
  `xjc.exe -b bindings_protxml_primitive.xml 
-no-header -encoding UTF-8 -extension 
-d "C:\projects\BatMass\MSFTBX\MSFileToolbox\src" 
-p umich.ms.fileio.filetypes.protxml.jaxb.primitive 
protXML_v8-fixed.xsd`  

##  After generation
* All occurrences of  `new ArrayList<>()` were replaced with `new ArrayList<>(1)` to avoid lots of lists
    of default size, which is 10. As in pepxml there might be tens of thousands of such lists which only 
    hold a single element.  
    Regex in IDEA for replacement (use _Replace in path_). Search pattern `(new ArrayList<.*?>)\(\)`,
    replacement pattern `$1\(1\)`
* Delete the `namespace` and `attributeFormDefault` from `package-info.java` that sits next to `ObjectFactory.java`

# Modifications to the original schema
The schema has repetitive elements, so I manually extracted two types:
```xml
<xs:complexType name="modification_info">
    <xs:sequence>
        <xs:element name="mod" minOccurs="0" maxOccurs="unbounded">
            <xs:complexType>
                <xs:sequence>
                    <xs:element name="mod_aminoacid_mass" minOccurs="0" maxOccurs="unbounded">
                        <xs:complexType>
                            <xs:attribute name="position" type="xs:string" use="required"/>
                            <xs:attribute name="mass" type="xs:string" use="required"/>
                        </xs:complexType>
                    </xs:element>
                </xs:sequence>
                <xs:attribute name="mod_nterm_mass" type="xs:string"/>
                <xs:attribute name="mod_cterm_mass" type="xs:string"/>
                <xs:attribute name="modified_peptide" type="xs:string"/>
            </xs:complexType>
        </xs:element>
    </xs:sequence>
    <xs:attribute name="peptide_sequence" type="xs:string" use="required"/>
    <xs:attribute name="charge" type="xs:positiveInteger" use="required"/>
    <xs:attribute name="calc_neutral_pep_mass" type="xs:double" use="optional" />
</xs:complexType>

<xs:complexType name="protein_annotation">
    <xs:attribute name="protein_description" type="xs:string" use="required"/>
    <xs:attribute name="ipi_name" type="xs:string"/>
    <xs:attribute name="refseq_name" type="xs:string"/>
    <xs:attribute name="swissprot_name" type="xs:string"/>
    <xs:attribute name="ensembl_name" type="xs:string"/>
    <xs:attribute name="trembl_name" type="xs:string"/>
    <xs:attribute name="locus_link_name" type="xs:string"/>
    <xs:attribute name="flybase" type="xs:string"/>
</xs:complexType>
```

Also use the provided bindings to properly map data-types. The `bindings_protxml.xml` has a good general type mapping rules
that should be used in other binding files as well.

Go to the `src` directory (e.g. `.../MSFTBX/MSFileToolbox/src`) copy `protXML_v6_modified.xsd` and `bindings_protxml.xml`
 there and execute:  
`xjc -b bindings_protxml.xml -p umich.ms.fileio.filetypes.protxml.jaxb protXML_v6_modified.xsd`  

