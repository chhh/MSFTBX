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
package umich.ms.fileio.filetypes;

import java.util.List;

import umich.ms.datatypes.IScanFlux;
import umich.ms.datatypes.IScanIterator;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.index.Index;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.exceptions.FileParsingException;

/**
 * Wrapper interface for working with all LC/MS files.
 *
 * @param <T> index type
 * @author Dmitry Avtonomov
 */
public interface LCMSDataSource<T extends Index<?>> {

  /**
   * Some identifier for the source, that can be used as its name.
   */
  String getName();

  /**
   * How many batches of scans will be processed in parallel. Disk I/O is done once per batch.
   */
  Integer getNumThreadsForParsing();

  /**
   * How many batches of scans will be processed in parallel. Disk I/O is done once per batch.
   *
   * @param numThreadsForParsing must be greater than zero
   */
  void setNumThreadsForParsing(Integer numThreadsForParsing);

  /**
   * Timeout (in seconds) that worker threads are allowed for parsing one batch of spectra.
   */
  long getParsingTimeout();

  /**
   * Timeout (in seconds) that worker threads are allowed for parsing one batch of spectra.
   *
   * @param parsingTimeout must be not less than 1 second
   */
  void setParsingTimeout(long parsingTimeout);

  /**
   * How many spectra each CPU will be given in one batch. E.g. you assigned 2 threads using {@link
   * #setNumThreadsForParsing(java.lang.Integer)}, and set this parameter to 20, then 40 spectra
   * will be read from disk in one batch and each thread will be given 20 spectra at once for
   * processing.
   */
  int getTasksPerCpuPerBatch();

  /**
   * How many spectra each CPU will be given in one batch. E.g. you assigned 2 threads using {@link
   * #setNumThreadsForParsing(Integer)}, and set this parameter to 20, then 40 spectra will be read
   * from disk in one batch and each thread will be given 20 spectra at once for processing.
   * <b>WARNING:</b> If you set this parameter to a large number, consider using {@link
   * #setParsingTimeout(long)} to increase
   * the allowed processing time for each worker thread.
   *
   * @param tasksPerCpuPerBatch must be greater than zero
   */
  void setTasksPerCpuPerBatch(int tasksPerCpuPerBatch);

  /**
   * @return true, if empty scans will be removed from parsing results.
   */
  boolean isExcludeEmptyScans();

  /**
   * If true, empty scans will be discarded during parsing. Need to be called prior to parsing.
   */
  void setExcludeEmptyScans(boolean excludeEmptyScans);

  /**
   * The intent is to be able to free the memory, e.g. release references to ScanCollection, etc.
   */
  void releaseMemory();

  /**
   * Gets the run info, not attempting to parse it. Will return a non null value only if {@link
   * #fetchRunInfo() } was called prior.
   */
  LCMSRunInfo getRunInfo();

  /**
   * Gets the run info, parsing it if it yet hasn't been, and caches for reuse.
   */
  LCMSRunInfo fetchRunInfo() throws FileParsingException;

  LCMSRunInfo parseRunInfo() throws FileParsingException;

  /**
   * Gets the index without an attempt to parse it. Will only return a non null value if {@link
   * #fetchIndex() } was called before.
   */
  T getIndex();

  /**
   * Returns the index, or parses it from the original file and caches for future reuse. If you
   * don't want the index to be cached, you can call {@link #parseIndex()} instead.
   */
  T fetchIndex() throws FileParsingException;

  T parseIndex() throws FileParsingException;

  /**
   * Parse a single spectrumRef from file on disk by it's number. Assuming every LCMS file interface
   * must be able to do that.
   *
   * @param num scan number
   */
  ISpectrum parseSpectrum(int num) throws FileParsingException;

  /**
   * Parse a single scan from file on disk by it's number. Assuming every LCMS file interface must
   * be able to do that. The storage strategy should always be {@link
   * umich.ms.datatypes.scan.StorageStrategy#STRONG}, the receiver then can set it to whatever it
   * needs.
   *
   * @param num scan number
   * @param parseSpectrum if false, the spectrumRef should not be parsed. Can provide significant
   * speed and memory benefits if you only need scan metadata
   * @return the value should never be null, throw an exception if it could not be parsed
   */
  IScan parseScan(int num, boolean parseSpectrum) throws FileParsingException;

  /**
   * Parse a range of scan numbers. The storage strategy should always be {@link
   * umich.ms.datatypes.scan.StorageStrategy#STRONG}, the receiver then can set it to whatever it
   * needs.
   *
   * @param subset the region of LC/MS data to be parsed as scans
   * @return ScanCollectionDefault of all scans from the file
   */
  List<IScan> parse(LCMSDataSubset subset) throws FileParsingException;

  /**
   * The storage strategy should always be {@link umich.ms.datatypes.scan.StorageStrategy#STRONG},
   * the receiver then can set it to whatever it needs.
   *
   * @param scanNums List of scan numbers to be parsed. All scan numbers MUST be present in the
   * file.
   * @return ScanCollection of requested scans. Unlike other parseIndexEntries...() methods, this
   * one does not set up proper child/parent relations between spectra. However, PrecursorInfo for
   * MSn spectra is still parsed, so you might get the correct precursorScanNum. This scan num might
   * not be present in the returned scan collection. Moreover, even if the precursorScanNum is
   * parsed, the pointer to parent scan in PrecursorInfo is not set (as no checks are made in this
   * method).
   */
  List<IScan> parse(List<Integer> scanNums) throws FileParsingException;

  default IScanFlux getFlux() {
    return NoFlux.Instance;
  }

  public static class NoFlux implements IScanFlux {
    public static final NoFlux Instance = new NoFlux();
    private NoFlux() {}
  }
}
