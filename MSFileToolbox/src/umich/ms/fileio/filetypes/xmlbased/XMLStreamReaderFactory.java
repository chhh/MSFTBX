package umich.ms.fileio.filetypes.xmlbased;

import javolution.xml.internal.stream.XMLStreamReaderImpl;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Produces {@link javolution.xml.internal.stream.XMLStreamReaderImpl} objects for reuse in XML parser-workers.
 * Created by dmitriya on 2015-02-19.
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
        if (reader == null) return;
        reader.reset();
    }
}
