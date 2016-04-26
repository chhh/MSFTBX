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
package umich.ms.util.base64;

import org.apache.commons.pool2.ObjectPool;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.util.ByteArrayHolder;
import umich.ms.util.PooledByteArrayHolders;

/**
* @author Dmitry Avtonomov
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
