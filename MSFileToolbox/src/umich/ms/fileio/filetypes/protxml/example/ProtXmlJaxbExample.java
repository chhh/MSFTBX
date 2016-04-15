package umich.ms.fileio.filetypes.protxml.example;

import umich.ms.fileio.filetypes.protxml.jaxb.standard.ProteinSummary;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by dmitriya on 2015-09-22.
 */
public class ProtXmlJaxbExample {
    public static void main(String[] args) throws JAXBException {
        String path = args[0];
        Path p = Paths.get(path).toAbsolutePath();
        File f = new File(p.toString());

        // declaring what to parse
        JAXBContext ctx = JAXBContext.newInstance(ProteinSummary.class);

        // run the parser
        Unmarshaller unmarshaller  = ctx.createUnmarshaller();
        Object unmarshalled = unmarshaller.unmarshal(f);


        // use the unmarshalled object
        ProteinSummary ps = (ProteinSummary) unmarshalled;

        System.out.printf("File: %s\nContained %d protein groups.\n", path, ps.getProteinGroup().size());

        int a = 1;
    }
}