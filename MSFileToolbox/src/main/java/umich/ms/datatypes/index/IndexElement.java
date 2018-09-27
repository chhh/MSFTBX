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
package umich.ms.datatypes.index;

import java.io.Serializable;

/**
 * A mapping from internal scan numbers (<b>which start at one</b>) to scan numbering in the
 * original files. mzXML files are required to provide a scan number, those scan numbers are not
 * always continuous, e.g.:
 * <ul>
 * <li>
 * In mzXML data converted from AB Sciex raw files one might have the first scan number as 2037, the
 * second as 4056 and so on, they are incrementing, but not incrementing by one.
 * </li>
 * <li>
 * In mzML data converted from Agilent raw files using proteowizard (msconvert.exe) each scan has a
 * separate "index" attribute, which starts at zero (index="0") and then and "id" which is a unique
 * textual representation of the scan's identity of the form id="cycleNumber=1, experimentNumber=1,
 * scanNumber=1". This scanNumber=1 is not always a unique number, it's some internal vendor
 * specific string.
 * </li>
 * <li>
 * There are many other example
 * </li>
 * </ul>
 *
 * In order to avoid all the confusion about scan numbering, MSFileToolbox uses a separate numbering
 * and indexing scheme. Internally all scan numbers start at one and then increment by one in
 * retention time order. Numbering from the original file is maintained, be it a raw vendor file
 * (e.g. Thermo uses normal 1 based numbering, Agilent has an ordinal number stored internally in
 * their files, but also has those 'experiment number' and other stuff), or an XML based file. The
 * ID is also retained
 *
 * @author Dmitry Avtonomov
 */
public interface IndexElement extends Serializable {

  /**
   * Internal scan number, starting from 1, incrementing by one for each consecutive scan. This is
   * the number you get from {@link umich.ms.datatypes.scan.IScan#getNum()}
   */
  int getNumber();

  /**
   * The scan number from the raw file. For mzXML files this will often (not always) be the same as
   * the internal number. For mzML this will typically be 1 less, than the internal number (the
   * value of "index" attribute of a spectrum tag).
   */
  int getRawNumber();

  /**
   * The textual representation of an ID of a scan. mzML provides those IDs by default, in mzXML
   * there is no such element, so this will return the same as {@link #getRawNumber()}.
   */
  String getId();
}
