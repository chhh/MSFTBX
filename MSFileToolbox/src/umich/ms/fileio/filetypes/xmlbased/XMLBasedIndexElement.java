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

import umich.ms.datatypes.index.IndexElement;
import umich.ms.util.OffsetLength;

/**
 *
 * @author Dmitry Avtonomov
 */
public interface XMLBasedIndexElement extends IndexElement {
    OffsetLength getOffsetLength();

    /**
     * This mutator is needed to facilitate "fixing" of index after it was created.
     * @param offlen
     */
    void setOffsetLength(OffsetLength offlen);
}
