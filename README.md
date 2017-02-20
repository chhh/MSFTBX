# MSFTBX
The acronym stands for `Mass Spectrometry File Toolbox`.  
This is a library for access to some common mass-spectrometry/proteomics data formats from Java.  
This library is what drives [BatMass](https://github.com/chhh/batmass).

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

## Building
You can find pre-compiled binaries [here](https://github.com/chhh/MSFTBX/releases/latest).  

**JAR**: You can load `MSFileToolbox` subdirectory as a project into Intellij IDEA IDE and build the jar from there. `Main Menu -> Build -> Build Artifacts`.  
**NetBeans Module**: Open the root directory in NetBeans as a project. You will see `MSFTBX` module suite which consists of 3 modules: _MSFileToolbox Module_ - (this is the main thing), _MSFileToolbox Libx_ - these are the depencies, and _Auto Update (MSFTBX)_ - this is the update center for NetBeans Platform projects (you definitely don't need this) .

I will mavenize the project in near future.

## Dependencies
- JAXB
- SLF4J
- Guava 19
- Apache Commons Pool 2
- OboParser from [Biojava](http://biojava.org/)'s submodule [Ontology](https://github.com/biojava/biojava/tree/master/biojava-ontology)
- [Javolution](http://javolution.org/) Core (slightly modified, sources are [here](https://github.com/chhh/javolution-msftbx))
