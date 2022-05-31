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
package umich.ms.fileio.filetypes.mzml;

import java.io.InputStream;
import java.util.*;

import javolution.xml.internal.stream.XMLStreamReaderImpl;
import org.apache.commons.pool2.ObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.datatypes.IScanFlux;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.scan.IScan;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.xmlbased.AbstractXMLBasedDataSource;
import umich.ms.fileio.filetypes.xmlbased.IndexBuilder;

/**
 * @author Dmitry Avtonomov
 */
public class MZMLFile extends AbstractXMLBasedDataSource<MZMLIndexElement, MZMLIndex> {
  private static final Logger log = LoggerFactory.getLogger(MZMLFile.class);

  private MZMLIndex index;

  public MZMLFile(String path) {
    super(path);
  }

  public ObjectPool<XMLStreamReaderImpl> getReaderPool() {
    return readerPool;
  }

  @Override
  public MZMLIndex getIndex() {
    return index;
  }

  @Override
  public MZMLIndex fetchIndex() throws FileParsingException {
    MZMLIndex tmp = index;
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
  public MZMLIndex parseIndex() throws FileParsingException {
    MZMLIndexParser parser = new MZMLIndexParser(this);
    return parser.parse();
  }

  @Override
  public MZMLRunInfo fetchRunInfo() throws FileParsingException {
    MZMLRunInfo info = (MZMLRunInfo) runInfo;
    if (runInfo == null) {
      synchronized (this) {
        info = (MZMLRunInfo) runInfo;
        if (info == null) {
          runInfo = info = parseRunInfo();
        }
      }
    }
    return info;
  }

  @Override
  public MZMLRunInfo parseRunInfo() throws FileParsingException {
    MZMLRunHeaderParser parser = new MZMLRunHeaderParser(this);
    return parser.parse();
  }

  @Override
  protected void releaseResources() {
    index = null;
  }

  @Override
  public MZMLMultiSpectraParser getSpectraParser(InputStream inputStream,
      LCMSDataSubset subset, ObjectPool<XMLStreamReaderImpl> readerPool, Integer numSpectra) {
    MZMLMultiSpectraParser parser;
    parser = new MZMLMultiSpectraParser(inputStream, subset, this);
    parser.setNumScansToProcess(numSpectra);
    parser.setReaderPool(readerPool);
    return parser;
  }

  @Override
  public IndexBuilder<MZMLIndexElement> getIndexBuilder(IndexBuilder.Info info) {
    return new MZMLIndexBuilder(info, getReaderPool());
  }

  @Override
  public IScanFlux getFlux() {
    return new MzmlFlux(this.path);
  }
}
