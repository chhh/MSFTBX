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
package umich.ms.fileio.filetypes.mzxml;

import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import javolution.xml.internal.stream.XMLStreamReaderImpl;
import org.apache.commons.pool2.ObjectPool;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.xmlbased.AbstractXMLBasedDataSource;
import umich.ms.fileio.filetypes.xmlbased.IndexBuilder;

/**
 * @author Dmitry Avtonomov
 */
public class MZXMLFile extends AbstractXMLBasedDataSource<MZXMLIndexElement, MZXMLIndex> {

  private MZXMLIndex index;

  /**
   * @param path Path to the file
   */
  public MZXMLFile(String path) {
    super(path);
  }

  /**
   * MzXML file constructor.
   *
   * @param path path to the original file.
   * @param excludeEmptyScans if true, will enable the use of advanced memory handling features.
   */
  public MZXMLFile(String path, boolean excludeEmptyScans) {
    super(path);
    this.excludeEmptyScans = excludeEmptyScans;
  }

  public ObjectPool<XMLStreamReaderImpl> getReaderPool() {
    return readerPool;
  }

  @Override
  public MZXMLIndex getIndex() {
    return index;
  }

  @Override
  public MZXMLIndex parseIndex() throws FileParsingException {
    MZXMLIndexParser parser = new MZXMLIndexParser(this);
    return parser.parse();
  }

  @Override
  public MZXMLIndex fetchIndex() throws FileParsingException {
    MZXMLIndex tmp = index;
    if (tmp == null) {
      synchronized (this) {
        tmp = getIndex();
        if (tmp == null) {
          tmp = parseIndex();
          index = tmp;
        }
      }
    }
    return tmp;
  }


  @Override
  public LCMSRunInfo parseRunInfo() throws FileParsingException {
    MZXMLRunHeaderParser parser = new MZXMLRunHeaderParser(this);
    return parser.parse();
  }

  @Override
  protected void releaseResources() {
    index = null;
  }

  @Override
  public MZXMLMultiSpectraParser getSpectraParser(InputStream inputStream,
      LCMSDataSubset subset, ObjectPool<XMLStreamReaderImpl> readerPool, Integer numSpectra) {
    MZXMLMultiSpectraParser parser;
    try {
      parser = new MZXMLMultiSpectraParser(inputStream, subset, this);
    } catch (FileParsingException ex) {
      throw new IllegalStateException(ex);
    }
    parser.setNumScansToProcess(numSpectra);
    parser.setReaderPool(readerPool);
    return parser;
  }

  @Override
  public IndexBuilder<MZXMLIndexElement> getIndexBuilder(IndexBuilder.Info info) {
    return new MZXMLIndexBuilder(info, getReaderPool());
  }

  @Override
  protected MZXMLIndex fixIndex(MZXMLIndex idx) {
    super.fixIndex(idx);
    int curInternalNum = 1;
    MZXMLIndexElement eOld, eNew;
    MZXMLIndex newIndex = new MZXMLIndex();
    Set<Map.Entry<Integer, MZXMLIndexElement>> entries = idx.getMapByNum().entrySet();
    for (Map.Entry<Integer, MZXMLIndexElement> entry : entries) {
      eOld = entry.getValue();
      eNew = new MZXMLIndexElement(curInternalNum, eOld.getRawNumber(), eOld.getOffsetLength());
      newIndex.add(eNew);
      curInternalNum += 1;
    }
    return newIndex;
  }

}
