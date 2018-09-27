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

package umich.ms.fileio.filetypes.mzxml;

import umich.ms.datatypes.scan.impl.ScanDefault;

/**
 * @author Dmitry Avtonomov
 */
class MzxmlVars {

  public int msLevel;
  // <scan> atrributes
  ScanDefault curScan;
  int scanNumRaw;
  int peaksCount;
  // <peaks> attributes
  boolean isPeaksTagReached;
  int compressedLen;       // required, in practice not always set
  String compressionType;  // required, in practice not always set
  int precisionMz;
  int precisionInt;

  // vars for Index Building
  Long offsetLo;
  Long offsetHi;
  Integer length;

  /**
   * Check {@link #reset()} source for default values
   */
  public MzxmlVars() {
    reset();
  }

  public final void reset() {
    curScan = null;
    scanNumRaw = -1;
    peaksCount = -1;
    msLevel = -1;

    isPeaksTagReached = false;
    compressedLen = -1;
    compressionType = "none";
    precisionMz = 32;
    precisionInt = 32;

    offsetLo = null;
    offsetHi = null;
    length = null;
  }
}
