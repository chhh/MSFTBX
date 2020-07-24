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

import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.scan.IScan;

/**
 * A universal interface that can be used if you want to traverse all scans in a run and don't care
 * about higher level stuff that {@link umich.ms.datatypes.scancollection.IScanCollection}
 * provides.
 *
 * @author Dmitry Avtonomov
 */
public interface SequentialScanReader {

  /**
   * Gets the next scan.
   *
   * @param subset filter that determines which scans.
   * @param removeNonMatching if true, the reader won't return non-matching scans at all. Otherwise
   * will return them, but without spectra parsed.
   * @return null if the end of LC/MS run has been reached.
   */
  IScan next(LCMSDataSubset subset, boolean removeNonMatching);
}
