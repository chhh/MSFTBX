package umich.ms.fileio.filetypes.pepxml.example;

import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.pepxml.PepXmlParser;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsPipelineAnalysis;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsRunSummary;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.SpectrumQuery;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Dmitry Avtonomov on 2016-04-13.
 */
public class PepXmlSimpleExample {

    public static void main(String[] args) throws FileParsingException {


        if (args.length < 1)
            throw new IllegalArgumentException("You must specify the filepath using command line arguments");
        Path path = Paths.get(args[0]);


        // a single call to parse the whole file
        MsmsPipelineAnalysis msmsPipelineAnalysis = PepXmlParser.parse(path);


        List<MsmsRunSummary> msmsRunSummaries = msmsPipelineAnalysis.getMsmsRunSummary();
        for (MsmsRunSummary msmsRunSummary : msmsRunSummaries) {
            List<SpectrumQuery> spectrumQueries = msmsRunSummary.getSpectrumQuery();
            System.out.printf("Spectrum queries from MS/MS run summary: %s\n", msmsRunSummary.getBaseName());
            for (SpectrumQuery sq : spectrumQueries) {
                System.out.printf("Spec ID: [%s], RT: [%.2f], precursor neutral mass: [%.3f]\n",
                                  sq.getSpectrum(), sq.getRetentionTimeSec(), sq.getPrecursorNeutralMass());
            }
            System.out.printf("Done with MS/MS run summary: %s\n", msmsRunSummary.getBaseName());
        }


        System.out.println();
    }
}
