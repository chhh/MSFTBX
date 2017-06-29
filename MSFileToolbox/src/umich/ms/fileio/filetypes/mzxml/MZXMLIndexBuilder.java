/*
 * Copyright (c) 2016 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.mzxml;

import javolution.text.CharArray;
import javolution.xml.internal.stream.XMLStreamReaderImpl;
import javolution.xml.sax.Attributes;
import javolution.xml.stream.XMLStreamConstants;
import javolution.xml.stream.XMLStreamException;
import javolution.xml.stream.XMLUnexpectedEndOfDocumentException;
import javolution.xml.stream.XMLUnexpectedEndTagException;
import org.apache.commons.pool2.ObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.xmlbased.IndexBuilder;
import umich.ms.fileio.filetypes.xmlbased.OffsetLength;
import umich.ms.logging.LogHelper;

import java.nio.charset.StandardCharsets;

/**
 * @author Dmitry Avtonomov
 */
public class MZXMLIndexBuilder implements IndexBuilder<MZXMLIndexElement> {
    private static final Logger logger = LoggerFactory.getLogger(MZXMLIndexBuilder.class);

    private ObjectPool<XMLStreamReaderImpl> pool;
    private Info info;
    private MzxmlVars vars;

    public MZXMLIndexBuilder(Info info, ObjectPool<XMLStreamReaderImpl> pool) {
        this.info = info;
        this.pool = pool;
        this.vars = new MzxmlVars();
    }

    @Override
    public Result<MZXMLIndexElement> call() throws Exception {
        return buildIndex(info);
    }

    /**
     * For use with Executors, consider using  instead of calling this method directly.
     * @param info info about offsets in the file and in the currently read buffer
     * @return
     * @throws FileParsingException
     */

    @Override
    public Result<MZXMLIndexElement> buildIndex(final IndexBuilder.Info info) throws Exception {
        Result<MZXMLIndexElement> result = new IndexBuilder.Result<>(info);

        int numOpeningScanTagsFound = 0;
        vars.reset();

        XMLStreamReaderImpl reader = (pool == null) ? new XMLStreamReaderImpl() : pool.borrowObject();
        try {
            reader.setInput(info.is, StandardCharsets.UTF_8.name());
            LogHelper.setJavolutionLogLevelFatal();

            int eventType = XMLStreamConstants.END_DOCUMENT;
            CharArray localName, attr;
            Attributes attrs;
            do {
                // Read the next XML element
                try {
                    eventType = reader.next();
                } catch (XMLStreamException e) {
                    if (e instanceof XMLUnexpectedEndTagException) {
                        eventType = XMLStreamConstants.END_ELEMENT;
                        continue;
                    }
                    if (e instanceof XMLUnexpectedEndOfDocumentException) {
                        // as we're reading arbitrary chunks of file, we will almost always finish parsing by hitting this condition
                        if (vars.offset != null) {
                            addCurIndexElementAndFlushVars(result, info.offsetInFile);
                        }
                        return result;
                    }
                    throw new FileParsingException(e);
                }


                // Process the read event
                switch (eventType) {

                    case XMLStreamConstants.START_ELEMENT:
                        localName = reader.getLocalName();
                        attrs = reader.getAttributes();

                        if (localName.equals(MZXMLMultiSpectraParser.TAG.SCAN.name)) {
                            if (vars.offset != null) {
                                // this means we've encountered nested Spectrum tags
                                long lastStartTagPos = reader.getLocation().getLastStartTagPos();
                                vars.length = (int)(vars.offset - lastStartTagPos);
                                addCurIndexElementAndFlushVars(result, info.offsetInFile);
                            }

                            //tagScanStart(reader);
                            try {
                                vars.scanNumRaw = attrs.getValue(MZXMLMultiSpectraParser.ATTR.SCAN_NUM.name).toInt();
                                vars.offset = reader.getLocation().getLastStartTagPos();
                            } catch (NumberFormatException e) {
                                throw new FileParsingException("Malformed scan number while building index", e);
                            }

                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        localName = reader.getLocalName();

                        if (localName.equals(MZXMLMultiSpectraParser.TAG.SCAN.name)) {
                            final XMLStreamReaderImpl.LocationImpl loc = reader.getLocation();
                            vars.length = (int)(loc.getCharacterOffset() + loc.getBomLength() - vars.offset);
                            addCurIndexElementAndFlushVars(result, info.offsetInFile);
                        }

                        break;
                }
            } while (eventType != XMLStreamConstants.END_DOCUMENT);

        } finally {
            // we need to return the reaer to the pool, if we borrowed it from there
            if (pool != null) {
                pool.returnObject(reader);
            }
        }

        return result;
    }

    private void addCurIndexElementAndFlushVars(IndexBuilder.Result<MZXMLIndexElement> result, long offsetInFile) {
        if (vars.scanNumRaw == -1 || vars.offset == null) {
            throw new IllegalStateException("When building index some variables were not set");
        }

        int len = vars.length != null ? vars.length : -1;
        OffsetLength offsetLength = new OffsetLength(offsetInFile + vars.offset, len);
        MZXMLIndexElement idxElem = new MZXMLIndexElement(vars.scanNumRaw, vars.scanNumRaw, offsetLength);
        if (len != -1) {
            result.addIndexElement(idxElem);
        } else {
            result.addUnfinishedIndexElement(idxElem);
        }

        vars.reset();
    }
}
