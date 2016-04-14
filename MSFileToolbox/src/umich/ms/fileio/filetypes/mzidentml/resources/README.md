# MzIdentML java JAXB bindings
These schemas are much better than pepxml, they actually compile without errors with _xjc_.

# Generated with
* `cd BatMass\MSFTBX\MSFileToolbox\src\umich\ms\fileio\filetypes\mzidentml\resources`
* Primitive `"C:\Programs\Java\jdk1.8.0_60\bin\xjc.exe" -b bindings_mzidml_primitive.xml -no-header -extension -p umich.ms.fileio.filetypes.mzidentml.jaxb.primitive -d "D:\projects\BatMass\MSFTBX\MSFileToolbox\src" mzIdentML1.2.0-candidate.xsd`
* Standard `"D:\tmp\jaxb-ri-2.2.11\jaxb-ri\bin\xjc.bat" -b bindings_mzidml_standard.xml -no-header -extension -p umich.ms.fileio.filetypes.mzidentml.jaxb.standard -d "D:\projects\BatMass\MSFTBX\MSFileToolbox\src" mzIdentML1.2.0-candidate.xsd`

## After generation 
Replace `(new ArrayList<.*?>)\(\)` with `$1\(1\)` to default all ArrayList initializations to a single element.

