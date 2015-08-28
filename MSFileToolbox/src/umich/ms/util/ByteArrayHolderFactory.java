package umich.ms.util;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by dmitriya on 2015-02-24.
 */
public class ByteArrayHolderFactory extends BasePooledObjectFactory<ByteArrayHolder> {
    protected volatile int defaultSize = 8192; // default to 8192

    public ByteArrayHolderFactory() {
    }

    public ByteArrayHolderFactory(int defaultSize) {
        this.defaultSize = defaultSize;
    }

    public int getDefaultSize() {
        return defaultSize;
    }

    /**
     * Sets the default size of byte buffers, produced by this factory.
     * @param defaultSize new default size
     */
    public synchronized void setDefaultSize(int defaultSize) {
        this.defaultSize = defaultSize;
    }

    /**
     * Updates the default byte buffer size, if the new value is larger, than
     * the current default.
     * @param defaultSize new default size
     */
    public synchronized void setDefaultSizeMax(int defaultSize) {
        if (this.defaultSize < defaultSize) {
            this.defaultSize = defaultSize;
        }
    }

    @Override
    public ByteArrayHolder create() throws Exception {
        return new ByteArrayHolder(getDefaultSize());
    }

    @Override
    public PooledObject<ByteArrayHolder> wrap(ByteArrayHolder obj) {
        return new DefaultPooledObject<>(obj);
    }

    @Override
    public void passivateObject(PooledObject<ByteArrayHolder> p) throws Exception {
        ByteArrayHolder bah = p.getObject();
        if (bah == null) return;
        bah.reset();
    }

    @Override
    public void activateObject(PooledObject<ByteArrayHolder> p) throws Exception {
        ByteArrayHolder bah = p.getObject();
        if (bah == null) return;
        bah.reset();
    }
}
