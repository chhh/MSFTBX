/*
 * Copyright (c) 2017 Dmitry Avtonomov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package umich.ms.fileio.filetypes.pepxml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.fileio.ResourceUtils;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.*;
import umich.ms.util.jaxb.JaxbUtils;
import umich.ms.util.xml.XmlUtils;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author Dmitry Avtonomov
 */
public class PepXmlTest {
  private static final Logger log = LoggerFactory.getLogger(PepXmlTest.class);

  List<Path> paths;

  @org.junit.Before
  public void setUp() throws Exception {
    paths = ResourceUtils.getResources(this.getClass(), "pepxml");
  }

  @org.junit.After
  public void tearDown() throws Exception {
    paths.clear();
    paths = null;
  }

  //@Test
  public void testCustomFile() throws Exception {
    final Path path = Paths.get("");
    final MsmsPipelineAnalysis mmpa = PepXmlParser.parse(path);
    SearchHit searchHitWithMoreThanOneProtein = mmpa.getMsmsRunSummary().get(0).getSpectrumQuery()
        .stream()
        .flatMap(spectrumQuery -> spectrumQuery.getSearchResult().stream())
        .flatMap(searchResult -> searchResult.getSearchHit().stream())
        .filter(searchHit -> searchHit.getNumTotProteins() > 1)
        .findFirst().orElse(null);

//        SearchHit searchHitWithMoreThanOneMod = mmpa.getMsmsRunSummary().get(0).getSpectrumQuery().stream()
//            .flatMap(spectrumQuery -> spectrumQuery.getSearchResult().stream())
//            .flatMap(searchResult -> searchResult.getSearchHit().stream())
//            .filter(searchHit -> {
//                ModificationInfo modInfo = searchHit.getModificationInfo();
//                if (modInfo == null) return false;
//                return modInfo.getModAminoacidMass().size() >= 2;
//            })
//            .findFirst().orElse(null);
//
//        List<SearchHit> interestingHits = mmpa.getMsmsRunSummary().get(0).getSpectrumQuery().stream()
//            .flatMap(spectrumQuery -> spectrumQuery.getSearchResult().stream())
//            .flatMap(searchResult -> searchResult.getSearchHit().stream())
//            .filter(searchHit -> {
//                ModificationInfo modInfo = searchHit.getModificationInfo();
//                if (modInfo == null) return false;
//                if (modInfo.getModAminoacidMass().isEmpty()) return false;
//                int pepSeqLen = searchHit.getPeptide().length();
//                for (ModAminoacidMass mod : modInfo.getModAminoacidMass()) {
//                    if (mod.getPosition() == pepSeqLen) return true;
//                }
//                return false;
//            }).collect(Collectors.toList());

    List<SearchHit> interestingHits = mmpa.getMsmsRunSummary().get(0).getSpectrumQuery().stream()
        .flatMap(spectrumQuery -> spectrumQuery.getSearchResult().stream())
        .flatMap(searchResult -> searchResult.getSearchHit().stream())
        .filter(searchHit -> {
          ModificationInfo modInfo = searchHit.getModificationInfo();
          if (modInfo == null) {
            return false;
          }
          if (modInfo.getModCtermMass() != null || modInfo.getModNtermMass() != null) {
            return true;
          }
          return false;
        }).collect(Collectors.toList());

    int a = 1;
  }

  @Test
  public void testParsing() throws Exception {

    System.out.println("Test parsing whole files.");
    for (Path path : paths) {
      String p = path.toString().toLowerCase();
      if (!p.endsWith(".zip") && !p.endsWith(".gz")) {
        // non-compressed pepxml files, just parse the whole thing at once
        System.out.printf("Parsing whole file: %s\n", path);
        final MsmsPipelineAnalysis msmsPipelineAnalysis = PepXmlParser.parse(path);

        Assert.assertFalse(msmsPipelineAnalysis.getMsmsRunSummary().isEmpty());
        for (MsmsRunSummary msmsRunSummary : msmsPipelineAnalysis.getMsmsRunSummary()) {
          assertMsMsRunSummaryOk(msmsRunSummary);
        }
      }

      System.out.println("Done.");
    }
  }

  @Test
  public void testAnalysisSummary() throws FileNotFoundException, JAXBException, XMLStreamException {

    String fn = "q02261.pep.xml";
    Path p = paths.stream().filter(path -> path.endsWith(fn)).findFirst()
        .orElseThrow(() -> new FileNotFoundException(fn));

    final XMLStreamReader xsr = JaxbUtils.createXmlStreamReader(p, false);
    // advance the input stream to the beginning of <peptideprophet_summary>

    final String tag = "analysis_summary";

    while (umich.ms.util.xml.XmlUtils.advanceReaderToNext(xsr, tag)) {
      AnalysisSummary summary = JaxbUtils.unmarshal(AnalysisSummary.class, xsr);
      log.info("Found '{}': {}", tag, summary.getAnalysis());
    }
  }

  @Test
  public void testIteratorParsing() throws Exception {

    System.out.println("Test parsing files iteratively.");
    for (Path path : paths) {
      String p = path.toString().toLowerCase();
      if (p.endsWith(".zip") || p.endsWith(".gz")) {
        // compressed pepxml files
        System.out.printf("Compressed file detected: %s\n", path);
        if (true) {
          System.out.println("Skipping for now...");
          continue;
        }
        System.out.printf("Parsing iteratively: %s\n", path);

        final FileInputStream fis = new FileInputStream(path.toString());
        final Inflater inflater = new Inflater(false);
        //final InflaterInputStream iis = new InflaterInputStream(fis, inflater);

        try (final InflaterInputStream iis = new InflaterInputStream(fis, inflater)) {
          Iterator<MsmsRunSummary> it = PepXmlParser.parse(iis);
          while (it.hasNext()) {
            MsmsRunSummary msmsRunSummary = it.next();
            assertMsMsRunSummaryOk(msmsRunSummary);
          }
        }

      } else {

        System.out.printf("Parsing iteratively: %s\n", path);
        try (FileInputStream fis = new FileInputStream(path.toString())) {
          Iterator<MsmsRunSummary> it = PepXmlParser.parse(fis);

          while (it.hasNext()) {
            MsmsRunSummary msmsRunSummary = it.next();
            assertMsMsRunSummaryOk(msmsRunSummary);
          }
        }

      }
      System.out.println("Done.");
    }
  }

  private void assertMsMsRunSummaryOk(MsmsRunSummary msmsRunSummary) {
    final List<SearchSummary> searchSummaries = msmsRunSummary.getSearchSummary();
    Assert.assertFalse("Search summaries should not be empty in test data",
        searchSummaries.isEmpty());

    final List<SpectrumQuery> spectrumQueries = msmsRunSummary.getSpectrumQuery();
    Assert.assertFalse("Spectrum queries should not be empty in test data",
        spectrumQueries.isEmpty());
  }
}
