package umich.ms.util.base64;

import org.apache.commons.pool2.ObjectPool;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.util.ByteArrayHolder;
import umich.ms.util.PooledByteArrayHolders;

/**
* Created by dmitriya on 2015-02-25.
*/
public class Base64ContextPooled extends Base64Context {
    protected ObjectPool<ByteArrayHolder> pool;

    public Base64ContextPooled() {
        super();
        pool = PooledByteArrayHolders.getInstance().getPool();
    }

    @Override
    public void close() throws FileParsingException {
        if (bytesHolder != null) {
            try {
                pool.returnObject(bytesHolder);
                bytesHolder = null;
            } catch (Exception e) {
                throw new FileParsingException(
                        "Could not return a ByteArrayHolder back to the common pool", e);
            }
        }
    }

    @Override
    public byte[] ensureBufferHasCapacityLeft(int size) throws FileParsingException {
        if (bytesHolder == null) {
            try {
                bytesHolder = pool.borrowObject();
            } catch (Exception e) {
                throw new FileParsingException(
                        "Could not borrow a ByteArrayHolder from the common pool", e);
            }
        }
        bytesHolder.ensureHasSpace(size);
        return bytesHolder.getUnderlyingBytes();
    }


}
