### Prot.XML schema to java sources generation
Use `xjc` as usual.  
ProtXML schema has a java incompatibility in it, redefining two types extending the same parent (`anySimpleType`) and
 specifying the same attributes in the extensions. `xjc` doesn't like that, but in this case we can just remove the 
 redefinition, and use one `nameValueType` definition everywhere.  
So the original schema had to be changed, modifying this:  

    <xs:element name="peptide" maxOccurs="unbounded">
		<xs:complexType>
            <xs:sequence>
                <xs:element name="parameter" minOccurs="0" maxOccurs="unbounded">
                    <xs:complexType>
                        <xs:simpleContent>
                            <xs:extension base="xs:anySimpleType">
                                <xs:attribute name="name" type="xs:string" use="required"/>
                                <xs:attribute name="value" type="xs:anySimpleType" use="required"/>
                                <xs:attribute name="type" type="xs:anySimpleType"/>
                            </xs:extension>
                        </xs:simpleContent>
                    </xs:complexType>
                </xs:element>

to this:  

    <xs:element name="peptide" maxOccurs="unbounded">
    		<xs:complexType>
                <xs:sequence>
                    <xs:element name="parameter" type="nameValueType" minOccurs="0" maxOccurs="unbounded"/>

and also redefined the `nameValueType` user-defined type at the end of the file from:  

    <xs:complexType name="nameValueType">
        <xs:simpleContent>
            <xs:extension base="xs:anySimpleType">
                <xs:attribute name="name" type="xs:string" use="required"/>
                <xs:attribute name="value" type="xs:anySimpleType" use="required"/>
                <xs:attribute name="type" type="xs:anySimpleType"/>
            </xs:extension>
        </xs:simpleContent>
    </xs:complexType>

to this:  
  
    <xs:complexType name="nameValueType">
        <xs:attribute name="name" type="xs:string" use="required"/>
        <xs:attribute name="value" type="xs:string" use="required"/>
        <xs:attribute name="type" type="xs:string"/>
    </xs:complexType>

Also use the provided bindings to properly map data-types. The `bindings_protxml.xml` has a good general type mapping rules
that should be used in other binding files as well.

Go to the `src` directory (e.g. `.../MSFTBX/MSFileToolbox/src`) copy `protXML_v6_modified.xsd` and `bindings_protxml.xml`
 there and execute:  
`xjc -b bindings_protxml.xml -p umich.ms.fileio.filetypes.protxml.jaxb protXML_v6_modified.xsd`  

