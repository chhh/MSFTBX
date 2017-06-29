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

package umich.ms.fileio.filetypes.mzml;

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
public class MZMLIndexBuilder implements IndexBuilder<MZMLIndexElement> {
    private static final Logger logger = LoggerFactory.getLogger(MZMLIndexBuilder.class);

    private ObjectPool<XMLStreamReaderImpl> pool;
    private Info info;
    private MzmlVars vars;

    public MZMLIndexBuilder(Info info, ObjectPool<XMLStreamReaderImpl> pool) {
        this.info = info;
        this.pool = pool;
        this.vars = new MzmlVars();
    }


    @Override
    public Result<MZMLIndexElement> call() throws Exception {
        return buildIndex(info);
    }

    /**
     * For use with Executors, consider using  instead of calling this method directly.
     * @param info info about offsets in file and in currently read buffer
     * @return
     * @throws FileParsingException
     */
    @Override
    public Result<MZMLIndexElement> buildIndex(final Info info) throws Exception {
        Result<MZMLIndexElement> result = new IndexBuilder.Result<>(info);

        int numOpeningScanTagsFound = 0;
        vars.reset();

        XMLStreamReaderImpl reader = (pool == null) ? new XMLStreamReaderImpl() : pool.borrowObject();
        try {
            reader.setInput(info.is, StandardCharsets.UTF_8.name());
            LogHelper.setJavolutionLogLevelFatal();

            int eventType;
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


                // process the read event
                switch (eventType) {

                    case XMLStreamConstants.START_ELEMENT:
                        CharArray localName = reader.getLocalName();
                        Attributes attrs = reader.getAttributes();

                        if (localName.contentEquals(MZMLMultiSpectraParser.TAG.SPECTRUM.name)) {
                            numOpeningScanTagsFound += 1;

                            if (vars.offset != null) {
                                // this means we've encountered nested Spectrum tags
                                logger.info("Found nested");
                                long lastStartTagPos = reader.getLocation().getLastStartTagPos();
                                vars.length = (int)(vars.offset - lastStartTagPos);
                                addCurIndexElementAndFlushVars(result, info.offsetInFile);
                            }

                            // these are required attributes, if they're not there, just throw an exception
                            try {
                                vars.spectrumIndex = attrs.getValue(MZMLMultiSpectraParser.ATTR.SPECTRUM_INDEX.name).toInt();
                                vars.spectrumId = attrs.getValue(MZMLMultiSpectraParser.ATTR.SPECTRUM_ID.name).toString();
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

                        if (localName.contentEquals(MZMLMultiSpectraParser.TAG.SPECTRUM.name)) {
                            final XMLStreamReaderImpl.LocationImpl loc = reader.getLocation();
                            vars.length = (int)(loc.getCharacterOffset() + loc.getBomLength() - vars.offset);
                            addCurIndexElementAndFlushVars(result, info.offsetInFile);
                        }

                        break;

                    case XMLStreamConstants.END_DOCUMENT:
                        break;
                }

            } while (eventType != XMLStreamConstants.END_DOCUMENT);

        } finally {
            if (pool != null && reader != null) {
                pool.returnObject(reader);
            }
        }

        return result;
    }

    private void addCurIndexElementAndFlushVars(IndexBuilder.Result<MZMLIndexElement> result, long offsetInFile) {

        if (vars.spectrumIndex == null || vars.spectrumId == null || vars.offset == null) {
            throw new IllegalStateException("When building index some variables were not set");
        }

        int len = vars.length != null ? vars.length : -1;
        OffsetLength offsetLength = new OffsetLength(offsetInFile + vars.offset, len);
        MZMLIndexElement idxElem = new MZMLIndexElement(vars.spectrumIndex + 1, vars.spectrumIndex, vars.spectrumId, offsetLength);
        if (len != -1) {
            result.addIndexElement(idxElem);
        } else {
            result.addUnfinishedIndexElement(idxElem);
        }

        vars.reset();
    }

}
