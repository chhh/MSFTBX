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

import javolution.xml.internal.stream.XMLStreamReaderImpl;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Produces {@link javolution.xml.internal.stream.XMLStreamReaderImpl} objects for reuse in XML
 * parser-workers.
 *
 * @author Dmitry Avtonomov
 */
public class XMLStreamReaderFactory extends BasePooledObjectFactory<XMLStreamReaderImpl> {

  @Override
  public XMLStreamReaderImpl create() throws Exception {
    return new XMLStreamReaderImpl();
  }

  @Override
  public PooledObject<XMLStreamReaderImpl> wrap(XMLStreamReaderImpl xmlReader) {
    return new DefaultPooledObject<>(xmlReader);
  }

  @Override
  public void passivateObject(PooledObject<XMLStreamReaderImpl> p) throws Exception {
    XMLStreamReaderImpl reader = p.getObject();
    if (reader == null) {
      return;
    }
    reader.reset();
  }
}
