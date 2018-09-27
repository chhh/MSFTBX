/*
 * Copyright (c) 2016 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.mzxml.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeMap;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.StorageStrategy;
import umich.ms.datatypes.scancollection.impl.ScanCollectionDefault;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.mzxml.MZXMLFile;

/**
 * Created by Dmitry Avtonomov on 2016-04-18.
 */
public class Example3 {

  public static void main(String[] args) throws FileParsingException {

    // Creating data source
    Path path = Paths.get("some-path-to.mzXML");
    path = Paths.get(args[0]);
    MZXMLFile source = new MZXMLFile(path.toString());

    // This is a data structure used to store scans and to navigate around the run
    ScanCollectionDefault scans = new ScanCollectionDefault();
    // Softly reference spectral data, make it reclaimable by GC
    scans.setDefaultStorageStrategy(StorageStrategy.SOFT);
    // Set it to automatically re-parse spectra from the file if spectra were not yet parsed or were reclaimed
    // to make auto-loading work you'll need to use IScan#fetchSpectrum() method instead of IScan#getSpectrum()
    scans.isAutoloadSpectra(true);

    // Set our mzXML file as the data source for this scan collection
    scans.setDataSource(source);
    // Set number of threads for multi-threaded parsing.
    // null means use as many cores as reported by Runtime.getRuntime().availableProcessors()
    source.setNumThreadsForParsing(null);
    // load the meta-data about the whole run, with forced parsing of MS1 spectra
    // as we have enabled auto-loading, then if we ever invoke IScan#fetchSpectrum()
    // on an MS2 spectrum, for which the spectrum has not been parsed, it will be
    // obtained from disk automatically. And because of Soft referencing, the GC
    // will be able to reclaim it.
    scans.loadData(LCMSDataSubset.MS1_WITH_SPECTRA);

    // let's traverse the data-structure
    TreeMap<Integer, IScan> num2scanMap = scans.getMapNum2scan();
    for (IScan scan : num2scanMap.values()) {
      ISpectrum spectrum = scan.getSpectrum();
      if (spectrum == null) {
        System.out.printf("%s does NOT have a parsed spectrum\n", scan.toString());
      } else {
        System.out.printf("%s has a parsed spectrum, it contains %d data points\n", scan.toString(),
            spectrum.getMZs().length);
      }
    }
  }
}
