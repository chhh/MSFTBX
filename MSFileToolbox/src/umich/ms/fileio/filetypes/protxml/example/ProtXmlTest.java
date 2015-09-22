package umich.ms.fileio.filetypes.protxml.example;

import umich.ms.fileio.filetypes.protxml.jaxb.ProteinSummary;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by dmitriya on 2015-09-22.
 */
public class ProtXmlTest {
    public static void main(String[] args) throws JAXBException {
        // input file
        String path = "D:\\_garbage\\interact-Ewing_set1.prot.xml";
        Path p = Paths.get(path).toAbsolutePath();
        File f = new File(p.toString());

        // declaring what to parse
        JAXBContext ctx = JAXBContext.newInstance(ProteinSummary.class);

        // run the parser
        Unmarshaller unmarshaller  = ctx.createUnmarshaller();
        Object unmarshalled = unmarshaller.unmarshal(f);


        // use the unmarshalled object
        ProteinSummary ps = (ProteinSummary) unmarshalled;

        int a = 1;
    }
}
