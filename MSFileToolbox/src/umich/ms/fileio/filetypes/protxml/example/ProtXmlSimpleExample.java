package umich.ms.fileio.filetypes.protxml.example;

import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.pepxml.PepXmlParser;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsPipelineAnalysis;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsRunSummary;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.SpectrumQuery;
import umich.ms.fileio.filetypes.protxml.ProtXmlParser;
import umich.ms.fileio.filetypes.protxml.jaxb.standard.ProteinGroup;
import umich.ms.fileio.filetypes.protxml.jaxb.standard.ProteinSummary;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Dmitry Avtonomov
 */
public class ProtXmlSimpleExample {
    public static void main(String[] args) throws FileParsingException {
        if (args.length < 1)
            throw new IllegalArgumentException("You must specify the filepath using command line arguments");
        Path path = Paths.get(args[0]);


        // a single call to parse the whole file
        ProteinSummary proteinSummary = ProtXmlParser.parse(path);


        List<ProteinGroup> proteinGroups = proteinSummary.getProteinGroup();
        System.out.printf("Processing ProtXML: [%s]\n", path.toString());
        for (ProteinGroup pg : proteinGroups) {
            System.out.printf("Protein group: [%s] contains %d proteins and has %.4f probability\n",
                    pg.getGroupNumber(), pg.getProtein().size(), pg.getProbability());
        }

        System.out.println();
    }
}
