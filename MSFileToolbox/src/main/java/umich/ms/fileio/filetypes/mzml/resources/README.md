# How to generate jaxb stuff

```
cd MSFTBX\MSFileToolbox\src 
xjc.exe 
    -b bindings_mzml_standard.xml -no-header -encoding UTF-8 -extension   
    -p umich.ms.fileio.filetypes.mzml.jaxb 
    -d D:\projects\BatMass\MSFTBX\MSFileToolbox\src\ 
    D:\projects\BatMass\MSFTBX\MSFileToolbox\src\umich\ms\fileio\filetypes\mzml\resources\mzML1.1.0.xsd
```

## After generation

* Change `required = true` to `required = false` for all annotations in `MzMLType.java`. E.g.:
  ```
    @XmlElement(required = true) -> @XmlElement(required = false) 
    protected RunType run;
  ```
  This allows parsing just the other header stuff with the \<run\> tag cut 
  out from the file and other needed fields missing.


* From `package-info.java` that sits next to the generated `ObjectFactory.java`
  remove annotation parameters, like so:  
  Before
  ```
  @javax.xml.bind.annotation.XmlSchema(namespace = "http://psi.hupo.org/ms/mzml", elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)
  package umich.ms.fileio.filetypes.mzml.jaxb;
  ```
  After
  ```
  @javax.xml.bind.annotation.XmlSchema()
    package umich.ms.fileio.filetypes.mzml.jaxb;
  ```

