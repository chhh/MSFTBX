package umich.ms.fileio.filetypes.pepxml.example;


import umich.ms.fileio.filetypes.pepxml.jaxb.MsmsPipelineAnalysis;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * This is just a demo of how this auto-generated binding class can be used to get PSMs from a pep.xml file.
 * @author Dmitry Avtonomov
 */
public class JaxbPepXmlTest {

    public static void main(String[] args) throws JAXBException {

        // input file
        String path = "G:\\tmp\\pepxml\\test_data\\interact-20130328_EXQ8_NaNa_SA_HeLa_rep04_06.pep.xml";
        Path p = Paths.get(path).toAbsolutePath();
        File f = new File(p.toString());

        // declaring what to parse
        JAXBContext ctx = JAXBContext.newInstance(MsmsPipelineAnalysis.class);

        // run the parser
        Unmarshaller unmarshaller  = ctx.createUnmarshaller();
        Object unmarshalled = unmarshaller.unmarshal(f);


        // use the unmarshalled object
        MsmsPipelineAnalysis pipelineAnalysis = (MsmsPipelineAnalysis) unmarshalled;
        if (pipelineAnalysis.getMsmsRunSummary().isEmpty()) {
            error("MS/MS run summary was empty!");
        }

        MsmsPipelineAnalysis.MsmsRunSummary run = pipelineAnalysis.getMsmsRunSummary().get(0);
        List<MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery> queries = run.getSpectrumQuery();
        if (queries.isEmpty()) {
            error("Spectrum queries table was empty!");
        }

        for (MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery query : queries) {
            List<MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult> searchResult = query.getSearchResult();
            if (searchResult.isEmpty()) {
                error(String.format("Search RESULT was empty for query #%d [spec id: %s]", query.getIndex(), query.getSpectrum()));
            }
            MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult result = searchResult.get(0);
            List<MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult.SearchHit> searchHit = result.getSearchHit();
            if (searchHit.isEmpty()) {
                error(String.format("Search HIT was empty for query #%d [spec id: %s]", query.getIndex(), query.getSpectrum()));
            }
            MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult.SearchHit firstHit = searchHit.get(0);
            MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult.SearchHit.ModificationInfo mods = firstHit.getModificationInfo();

            if (mods != null) {
                System.out.printf("mod info is not null for query #%d [spec id: %s]\n", query.getIndex(), query.getSpectrum());
                if (!mods.getModAminoacidMass().isEmpty()) {
                    int x = 1;
                }
                if (mods.getModCtermMass() != null) {
                    int y = 1;
                }
                if (mods.getModNtermMass() != null) {
                    int z = 1;
                }
            }
        }


        int a = 1;
    }


    private static void error(String msg) {
        System.err.println(msg);
        System.exit(1);
    }
}
