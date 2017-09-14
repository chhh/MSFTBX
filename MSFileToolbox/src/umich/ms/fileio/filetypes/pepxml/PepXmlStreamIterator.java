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

package umich.ms.fileio.filetypes.pepxml;

import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsPipelineAnalysis;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsRunSummary;
import umich.ms.fileio.util.jaxb.JaxbUtils;
import umich.ms.util.XmlUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Avtonomov
 */
class PepXmlStreamIterator implements Iterator<MsmsRunSummary> {

    private BufferedInputStream bis;
    private JAXBContext ctx;
    private Unmarshaller unmarshaller;
    private XMLStreamReader xsr;
    private boolean hasNext = false;

    private PepXmlStreamIterator() {}

    /**
     *
     * @param is The stream will be wrapped into a buffered one but won't be closed at the end of iteration.
     * @return A new iterator, good for one pass over data.
     * @throws FileParsingException
     */
    public static PepXmlStreamIterator create(InputStream is) throws FileParsingException {
        PepXmlStreamIterator it = new PepXmlStreamIterator();
        it.bis = new BufferedInputStream(is);

        try {
            it.ctx = JAXBContext.newInstance(MsmsRunSummary.class);
            it.unmarshaller = it.ctx.createUnmarshaller();
        } catch (JAXBException e) {
            throw new FileParsingException("Could not create JAXBContext/Unmarshaller for " + PepXmlParser.TAG_MSMS_RUN_SUMMARY, e);
        }
        try {
            it.xsr = JaxbUtils.createXmlStreamReader(it.bis, false);
        } catch (JAXBException e) {
            throw new FileParsingException("Could not create XMLStreamReader for " + PepXmlParser.TAG_MSMS_RUN_SUMMARY, e);
        }
        try {
            it.hasNext = XmlUtils.advanceReaderToNextRunSummary(it.xsr, PepXmlParser.TAG_MSMS_RUN_SUMMARY);
        } catch (XMLStreamException e) {
            throw new FileParsingException("Could not advance stream reader to the first" + PepXmlParser.TAG_MSMS_RUN_SUMMARY, e);
        }

        return it;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public MsmsRunSummary next() {
        if (!hasNext)
            throw new NoSuchElementException("Can't advance iterator, reached end");
        MsmsRunSummary unmarshalled;
        try {
            unmarshalled = JaxbUtils.unmarshall(MsmsRunSummary.class, xsr, unmarshaller);
        } catch (JAXBException e) {
            throw new IllegalStateException("Could not unmarshall next XML element", e);
        }
        try {
            hasNext = XmlUtils.advanceReaderToNextRunSummary(xsr, PepXmlParser.TAG_MSMS_RUN_SUMMARY);
        } catch (XMLStreamException e) {
            throw new IllegalStateException("Could not advance stream reader to the next " + PepXmlParser.TAG_MSMS_RUN_SUMMARY, e);
        }
        return unmarshalled;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Removal not supported, it's a parser.");
    }
}
