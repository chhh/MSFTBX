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
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.xmlbased.IndexBuilder;
import umich.ms.fileio.filetypes.xmlbased.OffsetLength;
import umich.ms.logging.LogHelper;

import java.nio.charset.StandardCharsets;

/**
 * @author Dmitry Avtonomov
 */
public class MZMLIndexBuilder implements IndexBuilder<MZMLIndexElement> {

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
                        // it's ok to have unexpected closing tags
                        eventType = reader.getEventType();
                    } else if (e instanceof XMLUnexpectedEndOfDocumentException) {
                        // as we're reading arbitrary chunks of file, we will almost always finish parsing by hitting this condition
                        break;
                    } else {
                        throw new FileParsingException(e);
                    }
                }


                // process the read event
                switch (eventType) {

                    case XMLStreamConstants.START_ELEMENT:
                        CharArray localName = reader.getLocalName();
                        Attributes attrs = reader.getAttributes();

                        if (localName.contentEquals(MZMLMultiSpectraParser.TAG.SPECTRUM.name)) {

                            if (vars.offsetLo != null) {
                                throw new FileParsingException("Nested spectrum tags not allowed in mzml.");
                            }

                            // these are required attributes, if they're not there, just throw an exception
                            try {
                                vars.spectrumIndex = attrs.getValue(MZMLMultiSpectraParser.ATTR.SPECTRUM_INDEX.name).toInt();
                                vars.spectrumId = attrs.getValue(MZMLMultiSpectraParser.ATTR.SPECTRUM_ID.name).toString();
                                vars.offsetLo = reader.getLocation().getLastStartTagPos();
                                addAndFlush(result, info.offsetInFile);

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
                            vars.offsetHi = (long)loc.getCharacterOffset();
                            addAndFlush(result, info.offsetInFile);
                        }

                        break;
                }

            } while (eventType != XMLStreamConstants.END_DOCUMENT);

        } finally {
            addAndFlush(result, info.offsetInFile);

            if (pool != null && reader != null) {
                pool.returnObject(reader);
            }
        }

        return result;
    }

    private void addAndFlush(Result<MZMLIndexElement> result, long offsetInFile) throws FileParsingException {

        if (vars.offsetLo != null) {
            // start tag was there
            if (vars.spectrumIndex == null || vars.spectrumId == null) {
                final long l = offsetInFile + vars.offsetLo;
                throw new IllegalStateException("When building index spectrum index or id were not found for offset: " + l);
            }
            if (vars.offsetHi != null) {
                // fully enclosed scan
                long len = vars.offsetHi - vars.offsetLo;
                if (len < 0)
                    throw new FileParsingException("Calculated length was less than zero");
                if (len > Integer.MAX_VALUE)
                    throw new FileParsingException("Calculated length was larger than Integer.MAX_VALUE");
                result.addIndexElement(new MZMLIndexElement(
                        vars.spectrumIndex + 1, vars.spectrumIndex, vars.spectrumId, new OffsetLength(offsetInFile + vars.offsetLo, (int)len)));

            } else {
                // start tag only
                result.addStartTag(new MZMLIndexElement(
                        vars.spectrumIndex + 1, vars.spectrumIndex, vars.spectrumId, new OffsetLength(offsetInFile + vars.offsetLo, -1)));
            }
        } else if (vars.offsetHi != null){
            // end tag only
            result.addCloseTag(new MZMLIndexElement(
                    -1, -1, "closing-tag", new OffsetLength(offsetInFile + vars.offsetHi, 0)));
        }

        vars.reset();
    }

}
