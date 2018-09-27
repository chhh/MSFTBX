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
package umich.ms.datatypes.scancollection;


import java.io.Serializable;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.StorageStrategy;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.LCMSDataSource;
import umich.ms.util.IntervalST;

/**
 * Manages scan meta-info and spectral data loading.
 *
 * @author Dmitry Avtonomov
 */
public interface IScanCollection extends Serializable {

  /**
   * The storage strategy, that should be used by default by all implementations of this interface.
   * Default is STRONG. Users should be able to configure this property after calling the
   * constructor, but before loading any data from an {@link LCMSDataSource}.
   */
  static final StorageStrategy DEFAULT_STORAGE_STRATEGY = StorageStrategy.STRONG;
  /**
   * The minimum relative overlap between two spectra's MS1 ranges or MS2 isolation windows, for
   * spectra to be considered as belonging to the same group.
   */
  double MIN_GROUP_OVERLAP = 0.5;

  /**
   * Tells if the collection has at least one scan in it.
   */
  boolean isEmpty();

  /**
   * Adds a new scan to this collection, maintains all the proper inside mappings. If a scan with
   * the same scan number is already in the map it will be replaced and the old entry will be
   * returned. If no replacement took place - null is returned.
   *
   * @return null if the scan was just added, or the old Scan, if it was replaced
   */
  IScan addScan(IScan scan);

  /**
   * Get a map which links actual scan numbers to instances of Scan objects
   *
   * @return the mapping
   */
  TreeMap<Integer, IScan> getMapNum2scan();

  /**
   * Get a map, holding spectra groupped by precursor m/z isolation window. MS1 spectra are groupped
   * by their overall m/z range.
   */
  TreeMap<Integer, IntervalST<Double, TreeMap<Integer, IScan>>> getMapMsLevel2rangeGroups();

  /**
   * Get a map which links MS Levels (starting from 1) to maps of (scanNum =&gt; scanObject)
   *
   * @return the mapping
   */
  TreeMap<Integer, ScanIndex> getMapMsLevel2index();

  /**
   * Maps retention time (in minutes) to lists of Scan objects. Generally there will be just one
   * Scan object in the list, but newer instruments, like  Thermo Fusion should be able to measure
   * several spectra simultaneously hence the use of List here for future-proofing.
   *
   * @return the mapping
   */
  TreeMap<Double, List<IScan>> getMapRt2scan();

  /**
   * The name says it all.
   *
   * @param scanNum Scan number as it was in the original MS file
   * @return Scan or null, if no such scan number exists in this ScanCollection
   */
  IScan getScanByNum(int scanNum);

  /**
   * Scan with closest Number less or equal to the provided one is returned.
   *
   * @param scanNum scan number
   * @return Scan or null, if the ScanCollection didn't have any scans with numbers &lt;= than this
   * one
   */
  IScan getScanByNumLower(int scanNum);

  /**
   * Scan with the closest Number greater or equal to the provided one is returned.
   *
   * @param scanNum scan number
   * @return Scan or null, if the ScanCollection didn't have any scans with numbers &gt;= than this
   * one
   */
  IScan getScanByNumUpper(int scanNum);

  /**
   * Scan with the closest Number is returned.
   *
   * @param scanNum scan number
   */
  IScan getScanByNumClosest(int scanNum);

  /**
   * Provided RT <i>MUST</i> be <i>EXACT</i> RT, existing in the map.
   *
   * @param rt RT in minutes
   * @return list of Scans or null if no such RT exists
   */
  List<IScan> getScansByRt(double rt);

  /**
   * Scan List with closest RT less or equal to the provided one are returned.
   */
  List<IScan> getScansByRtLower(double rt);

  /**
   * Scan List with the closest RT greater or equal to the provided one are returned.
   */
  List<IScan> getScansByRtUpper(double rt);

  /**
   * Scans with the closest RT are returned.
   */
  List<IScan> getScansByRtClosest(double rt);

  /**
   * A view(not a copy!) of the original scan map, containing only scans from numStart to numEnd
   * (inclusive). Scan numbers don't need to be exact matches, scans from the closed interval
   * [numStart; numEnd] are returned.
   *
   * @return A view or null if there were no scans at this ms level in [numStart; numEnd] interval
   */
  NavigableMap<Integer, IScan> getScansByNumSpanAtMsLevel(int numStart, int numEnd, int msLevel);

  /**
   * @param numStart Starting scan number (inclusive)
   * @param numEnd The last scan number in range (inclusive)
   */
  TreeMap<Integer, NavigableMap<Integer, IScan>> getScansByNumSpan(int numStart, int numEnd);

  /**
   * Same as {@link #getScansByRtSpan(double, double)}, but only searches at one MS Level
   *
   * @param rtStart The beginning of RT window
   * @param rtEnd The end of RT window
   * @return null, if no scans were in this window
   */
  NavigableMap<Integer, IScan> getScansByRtSpanAtMsLevel(double rtStart, double rtEnd, int msLevel);

  /**
   * Returns a range of scans, whose RT is in the inclusive interval [rtStart; rtEnd]. That means,
   * ceil RT match is used for rtStart and floor RT for rtEnd.
   *
   * @param rtStart The beginning of RT window
   * @param rtEnd The end of RT window
   * @return null, if no scans were in this window
   */
  TreeMap<Integer, NavigableMap<Integer, IScan>> getScansByRtSpan(double rtStart, double rtEnd);

  /**
   * Get the total number of scans in this ScansCollection.
   *
   * @return number of scans
   * @see #getScanCountAtMsLevel(int)
   */
  int getScanCount();

  /**
   * If the msLevel doesn't exist in this ScanCollectionDefault, returns null
   *
   * @return null, if msLevel doesn't exist. Actual number of scans otherwise.
   */
  Integer getScanCountAtMsLevel(int msLevel);

  /**
   * Gets the next scan in the collection (by number).
   *
   * @return null, if the provided scan number is the last number in the collection.
   */
  IScan getNextScan(int scanNum);

  /**
   * Finds the next scan at the same MS level, as the scan with scanNum. If the scan number provided
   * is not in the map, this method will find the next existing one.
   *
   * @param scanNum Scan number
   * @param msLevel MS Level at which to search for that scan number
   * @return either next Scan, or null, if there are no further scans at this level. If MS level is
   * incorrect, also returns null.
   */
  IScan getNextScanAtMsLevel(int scanNum, int msLevel);

  /**
   * Convenience method, calls {@link #getNextScanAtMsLevel(int, int)} internally
   *
   * @param scan an existing scan from THIS ScanCollection
   */
  IScan getNextScanAtSameMsLevel(IScan scan);

  /**
   * Gets the previous scan in the collection (by number).
   *
   * @return null, if the provided scan number is the first number in the collection.
   */
  IScan getPrevScan(int scanNum);

  /**
   * Finds the next scan at the same MS level, as the scan with scanNum. If the scan number provided
   * is not in the map, this method will find the next existing one.
   *
   * @param scanNum Scan number
   * @param msLevel MS Level at which to search for that scan number
   * @return either next Scan, or null, if there are no further scans at this level. If MS level is
   * incorrect, also returns null.
   */
  IScan getPrevScanAtMsLevel(int scanNum, int msLevel);

  /**
   * Convenience method, calls {@link #getPrevScanAtMsLevel(int, int)} internally
   */
  IScan getPrevScanAtSameMsLevel(IScan scan);

  /**
   * RT of the last scan in this scan collection.
   */
  Double getRtMax();

  /**
   * RT of the first scan. Useful if a scan collection is a subset of the original run, in which
   * case start RT might be e.g. 40min.
   */
  Double getRtMin();

  LCMSRunInfo getRunInfo();

  void setRunInfo(LCMSRunInfo runInfo);

  StorageStrategy getDefaultStorageStrategy();

  void setDefaultStorageStrategy(StorageStrategy storageStrategy);

  void setStorageStrategy(LCMSDataSubset subset, StorageStrategy storageStrategy);

  void setStorageStrategy(LCMSDataSubset subset, StorageStrategy strategyInSet,
      StorageStrategy strategyNotInSet);

  /**
   * <b>IMPLEMENTATIONS MUST BE SYNCHRONIZED</b>
   * Loads spectra for a subset of scans, or all of them, depending on the configuration.
   *
   * @param subset configuration, for which scans what data should be loaded. Can't be null. Scans
   * in the range of scan numbers should be loaded, but spectra should be loaded only for the scans,
   * specified by MS level and m/z ranges.
   * @param storageStrategy spectral storage strategy, can be null, in which case the default
   * storage strategy of the underlying ScanCollection should be used
   */
  void loadData(LCMSDataSubset subset, StorageStrategy storageStrategy) throws FileParsingException;

  /**
   * <b>IMPLEMENTATIONS MUST BE SYNCHRONIZED</b>
   * Loads spectra for a subset of scans, or all of them, depending on the configuration. Uses the
   * default storage strategy.
   *
   * @param subset configuration, for which scans what data should be loaded. Can't be null. Scans
   * in the range of scan numbers should be loaded, but spectra should be loaded only for the scans,
   * specified by MS level and m/z ranges.
   */
  void loadData(LCMSDataSubset subset) throws FileParsingException;

  /**
   * <b>IMPLEMENTATIONS MUST BE SYNCHRONIZED</b>
   * Unloads <b>SPECTRA</b> from the underlying ScanCollection.
   * <b>This method should be synchronized.</b>
   */
  void unloadData(LCMSDataSubset subset);

  /**
   * <b>IMPLEMENTATIONS MUST BE SYNCHRONIZED</b>
   * Unloads <b>SPECTRA</b> from the underlying ScanCollection.
   * <b>This method should be synchronized.</b>
   *
   * @param subset the subset to be unloaded
   * @param exlude a set of LCMSDataSubsets to be excluded from the unloading operation
   */
  void unloadData(LCMSDataSubset subset, Set<LCMSDataSubset> exlude);

  /**
   * Bring the collection back to initial empty state. Users should be able to add scans to the
   * collection afterwards as if they called the constructor again. The reason for having this
   * method is re-use of the exact scan collection type in LCMSData (BatMass) after a request to
   * release memory.
   */
  void reset();

  LCMSDataSource<?> getDataSource();

  /**
   * Set the data source from which the collection will be filled.
   */
  void setDataSource(LCMSDataSource<?> source);

  /**
   * Decides if spectra should be automatically loaded, when a spectrumRef is requested from a Scan,
   * but there is no spectrumRef present.
   */
  boolean isAutoloadSpectra();

  /**
   * Sets the new configuration for spectra autoloading.
   */
  void isAutoloadSpectra(boolean newVal);

  /**
   * Creates a scan stub, using the right {@link umich.ms.datatypes.scan.StorageStrategy}.
   *
   * @param num scan number
   */
  IScan createScanStub(int num);
}
