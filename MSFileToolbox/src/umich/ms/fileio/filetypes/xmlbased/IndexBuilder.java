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
        public final int length;
        public final InputStream is;

        public final String asString;
        public final int index;
        public static int counter = 0;

        public Info(long offsetInFile, int length, InputStream is, String asString) {
            this.offsetInFile = offsetInFile;
            this.length = length;
            this.is = is;
            this.asString = asString;
            this.index = counter++;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "index=" + index +
                    ", offsetInFile=" + offsetInFile +
                    ", length=" + length +
                    ", offsetInFileEnd=" + (offsetInFile + length) +
                    '}';
        }
    }

    /**
     * @author Dmitry Avtonomov
     * @param <T> type of index elements
     */
    class Result<T extends XMLBasedIndexElement> {
        private Info info;
        private List<T> indexElements;
        private List<T> closeTagLocs;
        private List<T> startTagLocs;

        public Result(Info info) {
            this.info = info;
            this.indexElements = new ArrayList<>(32);
            this.closeTagLocs = new ArrayList<>(32);
            this.startTagLocs = new ArrayList<>(32);
        }

        public List<T> getIndexElements() {
            return indexElements;
        }

        public List<T> getCloseTagLocs() {
            return closeTagLocs;
        }

        public List<T> getStartTagLocs() {
            return startTagLocs;
        }

        public void addIndexElement(T indexElement) {
            indexElements.add(indexElement);
        }

        public void addCloseTag(T indexElement) {
            closeTagLocs.add(indexElement);
        }

        public void addStartTag(T indexElement) {
            startTagLocs.add(indexElement);
        }

        public Info getInfo() {
            return info;
        }
    }
}
