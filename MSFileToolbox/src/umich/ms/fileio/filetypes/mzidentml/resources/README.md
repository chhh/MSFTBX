# MzIdentML java JAXB bindings
These schemas are much better than pepxml, they actually compile without errors with _xjc_.

# Generated with
* `cd BatMass\MSFTBX\MSFileToolbox\src\umich\ms\fileio\filetypes\mzidentml\resources`
* Primitive:  
`"C:\Programs\Java\oracle-jdk-8\bin\xjc.exe" -b bindings_mzidml_primitive.xml -no-header -encoding UTF-8 -extension -p umich.ms.fileio.filetypes.mzidentml.jaxb.primitive -d "C:\projects\batmass\MSFTBX\MSFileToolbox\src" mzIdentML1.2.0.xsd`
* Standard:  
`"C:\Programs\Java\oracle-jdk-8\bin\xjc.exe" -b bindings_mzidml_standard.xml -no-header -encoding UTF-8 -extension -p umich.ms.fileio.filetypes.mzidentml.jaxb.standard -d "C:\projects\batmass\MSFTBX\MSFileToolbox\src" mzIdentML1.2.0.xsd`
  * Old version: `"D:\tmp\jaxb-ri-2.2.11\jaxb-ri\bin\xjc.bat" -b bindings_mzidml_standard.xml -no-header -extension -p umich.ms.fileio.filetypes.mzidentml.jaxb.standard -d "D:\projects\BatMass\MSFTBX\MSFileToolbox\src" mzIdentML1.2.0.xsd`

## After generation 
* Replace `(new ArrayList<.*?>)\(\)` with `$1\(1\)` to default all ArrayList initializations to a single element.
* Delete the `namespace` and `attributeFormDefault` from `package-info.java` that sits next to `ObjectFactory.java`

