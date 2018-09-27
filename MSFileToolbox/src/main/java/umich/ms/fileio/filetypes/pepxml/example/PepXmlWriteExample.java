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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.ActivationMethodType;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.ModAminoacidMass;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.ModificationInfo;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsPipelineAnalysis;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsRunSummary;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.SearchHit;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.SearchResult;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.SpectrumQuery;

/**
 * An example of how a pepxml file can be written using the provided bindings
 *
 * Created by Dmitry Avtonomov on 2015-10-17.
 */
public class PepXmlWriteExample {

  public static void main(String[] args) throws JAXBException, IOException {

    MsmsPipelineAnalysis msmsPipelineAnalysis = new MsmsPipelineAnalysis();
    List<MsmsRunSummary> msmsRunSummaryList = msmsPipelineAnalysis.getMsmsRunSummary();
    MsmsRunSummary msmsRunSummary = new MsmsRunSummary();
    msmsRunSummaryList.add(msmsRunSummary);

    List<SpectrumQuery> spectrumQueryList = msmsRunSummary.getSpectrumQuery();
    SpectrumQuery spectrumQuery = new SpectrumQuery();
    spectrumQueryList.add(spectrumQuery);

    spectrumQuery.setRetentionTimeSec(15d);
    spectrumQuery.setActivationMethod(ActivationMethodType.HCD);
    spectrumQuery.setAssumedCharge(3);
    spectrumQuery.setStartScan(301);
    spectrumQuery.setEndScan(302);

    List<SearchResult> searchResultList = spectrumQuery.getSearchResult();
    SearchResult searchResult = new SearchResult();
    searchResultList.add(searchResult);

    List<SearchHit> searchHitList = searchResult.getSearchHit();
    SearchHit searchHit = new SearchHit();
    searchHitList.add(searchHit);

    searchHit.setMassdiff(147.01f);
    searchHit.setPeptide("PEPTIDESEQUENCE");
    searchHit.setProtein("PROTEINSEQUENCE");
    searchHit.setNumMissedCleavages(1);
    searchHit.setPeptidePrevAa("K");
    searchHit.setPeptideNextAa("Q");
    searchHit.setTotNumIons(27);

    ModificationInfo modInfo = new ModificationInfo();
    List<ModAminoacidMass> modList = modInfo.getModAminoacidMass();
    ModAminoacidMass mod = new ModAminoacidMass();
    mod.setMass(57.123456);
    mod.setPosition(3);
    modList.add(mod);
    mod = new ModAminoacidMass();
    mod.setMass(15.999);
    mod.setPosition(5);
    modList.add(mod);
    searchHit.setModificationInfo(modInfo);

    JAXBContext jaxbContext = JAXBContext.newInstance(MsmsPipelineAnalysis.class);
    Marshaller marshaller = jaxbContext.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

    Path path = Paths.get("D:\\tmp\\asd\\jaxb.test.pep.xml");
    Files.createDirectories(path.getParent());

    java.io.File file = path.toFile();
    marshaller.marshal(msmsPipelineAnalysis, file);
  }
}
