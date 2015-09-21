# GPMDB XML file parser

Internally uses JAXB, the XSD schema was automatically generated from raw XML files. 
`Peptide` tag parser was modified so that protein sequence was not stored.

## XSD schema generation

Done using this generator https://github.com/relaxng/jing-trang (Build it with ANT provided
  in the repo! This is important, won't compile otherwise)  
The command is along the following lines:  
`java -Xmx4G –jar trang.jar –I xml –O xsd myFile.xml myFile.xsd`

Then use `xjc` to generate JAXB bindings:  
`xjc -d java-src-gen-large -p umich.gpmdb.jaxb GPM64510013519.xml`

When the schema is generated, change all `BigDecimal` and `BigInteger` instances to
`Double` and `Integer`, also remove parsing of protein sequence in `Peptide` tag,
 this uses too much memory and is not needed at all.
