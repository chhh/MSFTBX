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
package umich.ms.fileio.chunk;

import org.apache.commons.pool2.ObjectPool;
import umich.ms.util.ByteArrayHolder;

/**
 * @author Dmitry Avtonomov
 */
public class FileChunk {

  int chunkNum;
  long offset;
  int length;
  //    WeakReference<ByteArrayHolder> bahRef = new WeakReference<ByteArrayHolder>(null);
  ByteArrayHolder bah;
  ObjectPool<ByteArrayHolder> pool;

  public FileChunk(int chunkNum, long offset, int length) {
    this.chunkNum = chunkNum;
    this.offset = offset;
    this.length = length;
    this.bah = null;
  }

  public int getChunkNum() {
    return chunkNum;
  }

  public long getOffset() {
    return offset;
  }

  public int getLength() {
    return length;
  }

  public ByteArrayHolder getBah() {
    return bah;
  }

  public void setBah(ByteArrayHolder bah) {
    setBah(bah, null);
  }

  /**
   * Sets the byte[] and indicates which pool this array is from.
   */
  public void setBah(ByteArrayHolder bah, ObjectPool<ByteArrayHolder> pool) {
    this.bah = bah;
    this.pool = pool;
  }

  public ObjectPool<ByteArrayHolder> getPool() {
    return pool;
  }

  public void setPool(ObjectPool<ByteArrayHolder> pool) {
    this.pool = pool;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FileChunk)) {
      return false;
    }

    FileChunk fileChunk = (FileChunk) o;

    if (chunkNum != fileChunk.chunkNum) {
      return false;
    }
    if (offset != fileChunk.offset) {
      return false;
    }
    return length == fileChunk.length;

  }

  @Override
  public int hashCode() {
    int result = chunkNum;
    result = 31 * result + (int) (offset ^ (offset >>> 32));
    result = 31 * result + length;
    return result;
  }
}
