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


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Dmitry Avtonomov
 * @param <T> type of elements in the index.
 */
public interface IndexBuilder<T extends XMLBasedIndexElement> extends Callable<IndexBuilder.Result<T>> {
    Result<T> buildIndex(Info info) throws Exception;

    /**
     * @author Dmitry Avtonomov
     */
    class Info {
        /** Global offset of the read-buffer (which is shared by multiple parsers) relative to the start of the file. */
        public final long offsetInFile;
        /**
         * Offset in the read-buffer (which is shared by multiple parsers), where this particular parser started
         * parsing.
         */
        public final long offsetInBuffer;
        //private final IndexBuilder<T> builder;
        public final InputStream is;

    //    public Info(long offsetInFile, long offsetInBuffer, IndexBuilder<T> builder) {
        public Info(long offsetInFile, long offsetInBuffer, InputStream is) {
            this.offsetInFile = offsetInFile;
            this.offsetInBuffer = offsetInBuffer;
            this.is = is;
        }
    }

    /**
     * @author Dmitry Avtonomov
     * @param <T> type of index elements
     */
    class Result<T extends XMLBasedIndexElement> {
        private Info info;
        private List<T> indexElements;
        private List<T> unfinishedIndexElements;

        public Result(Info info) {
            this.info = info;
            this.indexElements = new ArrayList<>(100);
            this.unfinishedIndexElements = new ArrayList<>(1);
        }

        public List<T> getIndexElements() {
            return indexElements;
        }

        public List<T> getUnfinishedIndexElements() {
            return unfinishedIndexElements;
        }

        public boolean addIndexElement(T indexElement) {
            return indexElements.add(indexElement);
        }

        public boolean addUnfinishedIndexElement(T indexElement) {
            return unfinishedIndexElements.add(indexElement);
        }

        public Info getInfo() {
            return info;
        }
    }
}
