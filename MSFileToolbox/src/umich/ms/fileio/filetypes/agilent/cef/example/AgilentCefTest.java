package umich.ms.fileio.filetypes.agilent.cef.example;

import umich.ms.fileio.filetypes.agilent.cef.jaxb.CEF;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Dmitry Avtonomov on 2015-09-22.
 */
public class AgilentCefTest {
    public static void main(String[] args) throws JAXBException, IOException {


        // input file
        String path = "E:\\Metabo\\Instrument_Evaluation_Agilent\\UMichigan-Metabolomics-WatersColumn-DilutionCurve\\MassHunterAnalysis\\MFE-POS\\CO-10000\\D20130118-LC2-PP0001259-E6-I23-P.cef";
        Path p = Paths.get(path).toAbsolutePath();
        File f = new File(p.toString());

        // Unmarshall manually
        // declaring what to parse
        JAXBContext ctx = JAXBContext.newInstance(CEF.class);

        // run the parser
        Unmarshaller unmarshaller  = ctx.createUnmarshaller();
        Object unmarshalled = unmarshaller.unmarshal(f);


        // use the unmarshalled object
        CEF cef = (CEF) unmarshalled;


        // Unmarshall to custom data-structure
        AgilentCefFile cefFile = new AgilentCefFile(p);
        AgilentCompounds compounds = cefFile.create();
        compounds.splitCompoundsByAdduct();

        int a = 1;
    }
}
