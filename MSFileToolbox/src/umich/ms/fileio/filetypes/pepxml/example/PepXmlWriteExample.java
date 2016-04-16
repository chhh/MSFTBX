/* 
 * Copyright 2016 Dmitry Avtonomov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package umich.ms.fileio.filetypes.pepxml.example;

import umich.ms.fileio.filetypes.pepxml.jaxb.nested.ActivationMethodType;
import umich.ms.fileio.filetypes.pepxml.jaxb.nested.MsmsPipelineAnalysis;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * An example of how a pepxml file can be written using the provided bindings
 *
 * Created by Dmitry Avtonomov on 2015-10-17.
 */
public class PepXmlWriteExample {
    public static void main(String[] args) throws JAXBException {

        MsmsPipelineAnalysis msmsPipelineAnalysis = new MsmsPipelineAnalysis();
        List<MsmsPipelineAnalysis.MsmsRunSummary> msmsRunSummaryList = msmsPipelineAnalysis.getMsmsRunSummary();
        MsmsPipelineAnalysis.MsmsRunSummary msmsRunSummary = new MsmsPipelineAnalysis.MsmsRunSummary();
        msmsRunSummaryList.add(msmsRunSummary);

        List<MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery> spectrumQueryList = msmsRunSummary.getSpectrumQuery();
        MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery spectrumQuery = new MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery();
        spectrumQueryList.add(spectrumQuery);

        spectrumQuery.setRetentionTimeSec(15f);
        spectrumQuery.setActivationMethod(ActivationMethodType.HCD);
        spectrumQuery.setAssumedCharge(3);
        spectrumQuery.setStartScan(301);
        spectrumQuery.setEndScan(302);

        List<MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult> searchResultList = spectrumQuery.getSearchResult();
        MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult searchResult = new MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult();
        searchResultList.add(searchResult);

        List<MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult.SearchHit> searchHitList = searchResult.getSearchHit();
        MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult.SearchHit searchHit = new MsmsPipelineAnalysis.MsmsRunSummary.SpectrumQuery.SearchResult.SearchHit();
        searchHitList.add(searchHit);

        searchHit.setMassdiff("147.01");
        searchHit.setPeptide("PEPTIDESEQUENCE");
        searchHit.setProtein("PROTEINSEQUENCE");
        searchHit.setNumMissedCleavages(1);
        searchHit.setPeptidePrevAa("K");
        searchHit.setPeptideNextAa("Q");
        searchHit.setTotNumIons(27);

        JAXBContext jaxbContext = JAXBContext.newInstance(MsmsPipelineAnalysis.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        Path path = Paths.get("D:\\tmp\\asd\\jaxb.test.pep.xml");
        java.io.File file = path.toFile();
        marshaller.marshal(msmsPipelineAnalysis, file);
    }
}
