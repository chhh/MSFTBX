package umich.ms.fileio.filetypes.pepxml.example;

import umich.ms.fileio.filetypes.pepxml.jaxb.MsmsPipelineAnalysis;
import umich.ms.fileio.filetypes.pepxml.jaxb.NameValueType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Avtonomov on 2016-02-18.
 */
public class Datta {
    public static void main(String[] args) throws Exception {
        // input file
        //String p1 = "E:\\andy\\datta-score\\Gygi_IC\\b1906_293T_proteinID_01A_QE3_122212.pepXML";
        //String p2 = "E:\\andy\\datta-score\\Gygi_ICnorm\\b1906_293T_proteinID_01A_QE3_122212.pepXML";


        String pathIn = args[0];
        ArrayList<String> paths = new ArrayList<>();
        paths.add(pathIn);

        //paths.add(p2);
        File fileOut = Paths.get(pathIn + ".txt").toFile();


        try (PrintWriter printWriter = new PrintWriter(fileOut)) {
            for (String path : paths) {
                Path p = Paths.get(path).toAbsolutePath();
                File f = new File(p.toString());

                // declaring what to parse

                // run the parser
                JAXBContext ctx = JAXBContext.newInstance(MsmsPipelineAnalysis.class);
                Unmarshaller unmarshaller  = ctx.createUnmarshaller();
                Object unmarshalled = unmarshaller.unmarshal(f);
                MsmsPipelineAnalysis pipelineAnalysis = (MsmsPipelineAnalysis) unmarshalled;


                // use the unmarshalled object
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
                    for (MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult.SearchHit hit : searchHit) {
                        if (hit.getHitRank() > 10)
                            break;
                        String scoreStr = hit.getSearchScore().get(0).getValueNameValueType();
                        Double scoreVal = Double.parseDouble(scoreStr);

                        printWriter.printf("%s,%d,%s,%s,%d\n",
                                          query.getSpectrum(), hit.getHitRank(), hit.getPeptide(),
                                          scoreStr, hit.getNumMatchedIons());
                    }

                }
            }

            printWriter.flush();
        }




    }

    private static void error(String msg) {
        System.err.println(msg);
        System.exit(1);
    }
}
