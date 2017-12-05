[![Maven Central](https://img.shields.io/maven-central/v/com.github.chhh/msftbx.svg)]()

# MSFTBX
The acronym stands for `Mass Spectrometry File Toolbox`. This is a library for access to some common mass-spectrometry/proteomics data formats from Java:  
 - mzML
 - mzXML
 - pepXML/pep.xml
 - protXML/prot.xml
 - mzIdentML
 - cef (Agilent)
 - GPMdb XML

This library is what drives [BatMass](https://github.com/chhh/batmass).

## Citing
Please cite the following paper if you used [MSFTBX](https://github.com/chhh/msftbx) or [BatMass](https://github.com/chhh/batmass) in your work:  
[Avtonomov D.M. et al: J. Proteome Res. June 16, 2016. DOI: 10.1021/acs.jproteome.6b00021](https://dx.doi.org/10.1021/acs.jproteome.6b00021)

## Maven dependency
```xml
<dependency>
    <groupId>com.github.chhh</groupId>
    <artifactId>msftbx</artifactId>
    <version>1.8.0</version>
</dependency>
```

## Release notes
#### v1.8.0
 - **Incompatible change to previous versions**. PepXml, ProtXml, MzIdentMl parsers now use Doubles instead of Floats everywhere. Any old code using old Float properties
 might break now.

## How to use
To get started quickly, follow the tutorial: http://www.batmass.org/tutorial/data-access-layer/#parsing-lc-ms-data-mzml-mzxml-files

## Features
- Parsers for mzML/mzXML with unified API
  - Very fast, multi-threaded
  - Rich standardized API for contents of those files (scan and run meta-info, not just spectra).
  - msNumpress compression support for mzML
  - Automated LC/MS run structure determination:
    - Data structures for parent-child relationship between spectra
    - Indexes for scans based on scan numbers, retention times both globally
    and for each MS level separately
    - Convenient methods to get next-previous scans at the same MS level
  - Tolerant to malformed data
    - Can handle MS2 scan tags nested inside MS1 scans
    - Tolerant to missing or broken file index
    - Reindexing on the fly
  - Memory management
    - Automated spectra parsing on demand
      - You can parse just the structure of an LC/MS run without the spectral data, the memory footprint in this case will be very small. Only when spectra are requested
      will they be parsed.
      - Soft referencing of spectral data for GC
    - Tracking of which loaded data is not being used by any components with automated unloading.
- Upcoming support for Thermo RAW files on Windows
- pepXML parser and writer
- protXML parser and writer
- mzIdentML parser
- GPMdb XML files parser
- Agilent .cef files parser

## Binary distribution
Get pre-built jars from [Maven Central](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.chhh%22%20AND%20a%3A%22msftbx%22).  

### Building with Maven (preferred)
`cd ./MSFileToolbox && mvn clean package`  
Will produce the jar files with just the library `msftbx-X.X.X.jar` as well as one large jar `msftbx-X.X.X-jar-with-dependencies.jar`.
The latter can be used as is, it includes all the needed dependencies.

### Building a NetBeans Platform module
**NetBeans Module**: Open the root directory in NetBeans as a project. You will see `MSFTBX` module suite which consists of 3 modules: _MSFileToolbox Module_ - (this is the main thing), _MSFileToolbox Libx_ - these are the depencies, and _Auto Update (MSFTBX)_ - this is the update center for NetBeans Platform projects (you definitely don't need this) .

## Dependencies
- SLF4J
- Google Guava
- Apache Commons Pool 2
- OboParser from [Biojava](http://biojava.org/)'s submodule [Ontology](https://github.com/biojava/biojava/tree/master/biojava-ontology)
- [Javolution](http://javolution.org/) Core (slightly modified, sources are [here](https://github.com/chhh/javolution-msftbx), this modified dependency is
published on Maven Central)



## Notes
When dealing with mzIdentML files (.mzid) you will encounter `AbstractParamType`.
In the definition of mzIdentML both `cvParam` and `userParam` inherit from it
and both `cvParam` and `userParam` can be stored in the same list. Thus, when
you get such a list, you'll need to cast manually to the concrete type like so:
```java
List<AbstractParamType> paramGroup = blabla.getParamGroup();
for (AbstractParamType param : paramGroup) {

	if (param instanceof CVParamType) {
		CVParamType p = (CVParamType)param;
		// do something with cvParam

	} else if (param instanceof UserParamType) {
		UserParamType p = (UserParamType)param;
		// do something with userParam

	}
}
```
