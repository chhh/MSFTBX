# How to generate java classes from the xml schema
The bindings file will resolve name clashes in the `.xsd`.
pepXML schemas can be found [here](https://sourceforge.net/p/sashimi/code/HEAD/tree/trunk/trans_proteomic_pipeline/schema/).

`G:\tmp\pepxml>
	"<path/to/jdk>/jdk1.x.x/bin/xjc.exe"
	-b bindings_pepxml_standard_.xml
	-d generated
	-p umich.ms.fileio.filetypes.pepxml.jaxb.standard
	pepXML_v119-fixed.xsd`

# Errors in original file
The original schema contains `<xs:any namespace="##any" processContents="lax" minOccurs="0">`
on line 17, and this makes it incorrect as it is impossible to differentiate between
`<any>` element in the sequence and `<xs:element name="parameter"`. So I just commented it
out. It was useless anyways as `<any>` can only be mapped to plain `Object` in java.

# Changes to original schema
`<xs:attribute name="massdiff" type="xs:float" use="required">` inside `<xs:element name="msms_run_summary" maxOccurs="unbounded">`
was changed to from `xs:string` to `xs:float`, never seen it being a string.

This portion in the beginning had to be changed as well from:
```xml
<xs:element name="msms_pipeline_analysis">
    <xs:complexType>
        <xs:sequence maxOccurs="unbounded">
```
to
```xml
<xs:element name="msms_pipeline_analysis">
    <xs:complexType>
        <xs:sequence>
```
This `maxOccurs="unbounded"` confused the hell out of _xjc_ and it generated a single List<Object>, but without this
redundant unbounded hint it actually could figure out the separate lists inside that _sequence_.

All occurences of regex: `name="massdiff"([^>]*)type="xs:string"` were replaced with (IDEA style) `name="massdiff"$1type="xs:float"`.  

All occurences of regex: `name="ppmtol"([^>]*)type="xs:integer"` were replaced with (IDEA style) `name="ppmtol"$1type="xs:float"`.  



# Generated with  
Execute from `MSFTBX\MSFileToolbox\src\umich\ms\fileio\filetypes\pepxml\resources` directory.  
Will overwrite existing java jaxb files without warning.  

* Standard
  * `xjc -b bindings_pepxml_standard.xml  -no-header -encoding UTF-8 -extension -d "C:\projects\BatMass\MSFTBX\MSFileToolbox\src" -p umich.ms.fileio.filetypes.pepxml.jaxb.standard  pepXML_v120-fixed.xsd`
* Primitive 
  * `xjc -b bindings_pepxml_primitive.xml -no-header -encoding UTF-8 -extension -d "C:\projects\BatMass\MSFTBX\MSFileToolbox\src" -p umich.ms.fileio.filetypes.pepxml.jaxb.primitive pepXML_v120-fixed.xsd`
* __DEPRECATED__ (Nested) 
  * `xjc -no-header -encoding UTF-8 -d "C:\projects\BatMass\MSFTBX\MSFileToolbox\src" -b bindings_pepxml_nested.xml -p umich.ms.fileio.filetypes.pepxml.jaxb.nested pepXML_v120-fixed.xsd`
##  After generation
* All occurrences of  `new ArrayList<>()` were replaced with `new ArrayList<>(1)` to avoid lots of lists
    of default size, which is 10. As in pepxml there might be tens of thousands of such lists which only 
    hold a single element.
* Use the following regex in IDEA for replacement (use _Replace in path_). Search pattern `(new ArrayList<.*?>)\(\)`,
    replacement pattern `$1\(1\)`
* Delete the `namespace` and `attributeFormDefault` from `package-info.java` that sits next to `ObjectFactory.java`
* In `NameValueType.java` (both *standard* and *primitive*) apply the following changes:  
  Change these lines (i.e. remove arguemnts from `@XmlType` annotation):  
  ```java
  -@XmlType(name = "nameValueType", propOrder = {
  -    "value"
  -})
  
  +@XmlType()
  ```
  Remove these lines:
  ```java
  -    @XmlValue
  -    @XmlSchemaType(name = "anySimpleType")
  -    protected Object value;
  ```
  and remove methods `public Object getValue()` and `public void setValue(Object value)`.

# Use like this:

```java
// input file
String path = "G:\\tmp\\pepxml\\test_data\\interact-20130328_EXQ8_NaNa_SA_HeLa_rep04_06.pep.xml";
Path p = Paths.get(path).toAbsolutePath();
File f = new File(p.toString());

// declaring what to parse, we want MsmsPipelineAnalysis elements and don't care about the rest
JAXBContext ctx = JAXBContext.newInstance(MsmsPipelineAnalysis.class);

// run the parser
Unmarshaller unmarshaller  = ctx.createUnmarshaller();
Object unmarshalled = unmarshaller.unmarshal(f);

MsmsPipelineAnalysis pipelineAnalysis = (MsmsPipelineAnalysis) unmarshalled;
```
