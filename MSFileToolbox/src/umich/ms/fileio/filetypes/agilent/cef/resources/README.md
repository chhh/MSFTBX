### Agilent CEF jaxb bindings generation

Generate `xsd` schema using `trang` (https://github.com/relaxng/jing-trang) from a local .cef file.
Note that `trang` should be compiled using `ant` that comes with it, just check out `trang` and run `./ant` from
the cloned directory.  
`java -Xmx4G -jar trang.jar -I xml -O xsd D20130118-LC2-PP0001259-E6-I23-P.cef AgilentCEF.xsd`


Then use `xjc` to generate JAXB bindings, note that we remap `xs:integer` and `xs:decimal` types from their
default values (`BigInteger` and `BigDecimal`) using bindings in `bindings_cef.xml`.    
In the bindings note the line `<jxb:bindings schemaLocation="AgilentCEF.xsd" node="/xsd:schema">` which specifies
that remapping is only done for the `AgilentCEF.xsd` schema.  

To generate the source files, copy the schema and bindings to `<project dir>/src` (i.e. the folder where umich.ms...)
package directories are located and from there:  
`xjc -d jaxb -p umich.ms.fileio.filetypes.agilent.cef.jaxb -b AgilentCEF.xsd`    

