# How to generate java classes from the xml schema
The bindings file will resolve name clashes in the `.xsd`.
pepXML schemas can be found [here](https://sourceforge.net/p/sashimi/code/HEAD/tree/trunk/trans_proteomic_pipeline/schema/).


G:\tmp\pepxml>
	"<path/to/jdk>/jdk1.x.x/bin/xjc.exe" 
	-b bindings_pepxml_standard_.xml 
	-d generated 
	-p umich.ms.fileio.filetypes.pepxml.jaxb.standard
	pepXML_v118.xsd
	
	
	
Use like this:

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
