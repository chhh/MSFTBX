/*
 * Copyright (c) 2017 Dmitry Avtonomov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package umich.ms.util;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author Dmitry Avtonomov
 */
public class XmlUtils {
    private XmlUtils() {}

    /**
     * Advances the Stream Reader to the next occurrence of a user-specified tag.
     * @param xsr The reader to advance.
     * @param tag The tag to advance to. No brackets, just the name.
     * @return True if advanced successfully, false when the end of document was successfully reached.
     * @throws XMLStreamException In all cases other than described by 'return'.
     */
    public static boolean advanceReaderToNext(XMLStreamReader xsr, String tag) throws XMLStreamException {
        if (tag == null)
            throw new IllegalArgumentException("Tag name can't be null");
        if (xsr == null)
            throw new IllegalArgumentException("Stream Reader can't be null");
        do {
            if (xsr.next() == XMLStreamConstants.END_DOCUMENT)
                return false;
        } while (!(xsr.isStartElement() && xsr.getLocalName().equals(tag)));

        return true;
    }
}
