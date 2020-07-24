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
package umich.ms.fileio.filetypes.xmlbased;

import umich.ms.datatypes.index.impl.IndexElementDefault;
import umich.ms.util.OffsetLength;

/**
 * @author Dmitry Avtonomov
 */
public class AbstractXMLBasedIndexElement extends IndexElementDefault implements
    XMLBasedIndexElement {

  /**
   * The offset and length of scan entry in the XML file.
   */
  protected OffsetLength offlen;

  public AbstractXMLBasedIndexElement(int num, int numRaw, OffsetLength offlen) {
    super(num, numRaw);
    this.offlen = offlen;
  }

  @Override
  public OffsetLength getOffsetLength() {
    return offlen;
  }

  /**
   * Use carefully, the only intended use for this mutator is to extend the already existing
   * offset-length pairs if the original length was not extending all the way up to the next "scan"
   * tag.
   */
  @Override
  public void setOffsetLength(OffsetLength offlen) {
    this.offlen = offlen;
  }

  @Override
  public String toString() {
    return "{" +
        "offlen=" + offlen +
        ", numRaw=" + numRaw +
        ", num=" + num +
        '}';
  }
}
