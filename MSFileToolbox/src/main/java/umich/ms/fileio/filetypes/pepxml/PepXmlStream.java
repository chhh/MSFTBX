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

import java.io.InputStream;
import java.util.Iterator;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsRunSummary;

/**
 * @author Dmitry Avtonomov
 */
public class PepXmlStream implements Iterable<MsmsRunSummary> {

  private volatile InputStream is;
  private volatile boolean used = false;

  public static PepXmlStream create(InputStream is) {
    PepXmlStream pxs = new PepXmlStream();
    pxs.is = is;
    return pxs;
  }

  @Override
  public Iterator<MsmsRunSummary> iterator() {
    if (used) {
      throw new IllegalStateException(
          "Not allowed to iterate a stream twice, create a new PepXmlStream.");
    }
    used = true;
    try {
      return PepXmlStreamIterator.create(is);
    } catch (FileParsingException e) {
      throw new IllegalStateException("Could not create PepXmlIterator", e);
    }
  }


}
