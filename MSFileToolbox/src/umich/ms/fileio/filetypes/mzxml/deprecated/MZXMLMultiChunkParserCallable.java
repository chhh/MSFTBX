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
package umich.ms.fileio.filetypes.mzxml.deprecated;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.concurrent.Callable;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import umich.ms.datatypes.scan.IScan;
import umich.ms.fileio.filetypes.mzxml.MZXMLMultiSpectraParser;
import umich.ms.fileio.filetypes.util.MultiSpectraParser;

/**
 * Takes a string with possibly many {@code <scan>} elements and returns parsed
 * scans.
 * @deprecated Now Javolution is used instead, see {@link MZXMLMultiSpectraParser}.
 * @see MZXMLMultiSpectraParser
 * @see MultiSpectraParser
 */
@Deprecated
class MZXMLMultiChunkParserCallable implements Callable<List<IScan>> {
    private static final boolean useJdkSaxParser = true;
    private static final boolean isSaxParserXmlFormatTolerant = true;
    private static final SAXParserFactory parserFactory;
    static {
        parserFactory = SAXParserFactory.newInstance();
        parserFactory.setNamespaceAware(false);
        parserFactory.setValidating(false);
    }
    private String str;
    private final boolean excludeEmptyScans;
    private Integer numScansInChunk = null;

    MZXMLMultiChunkParserCallable(String str, boolean excludeEmptyScans, Integer numScansInChunk) {
        this.str = str;
        this.excludeEmptyScans = excludeEmptyScans;
        this.numScansInChunk = numScansInChunk;
    }

    @Override
    public List<IScan> call() throws SAXException, IOException, ParserConfigurationException {
        XMLReader parser;
        if (useJdkSaxParser) {
            // This one is JDK default (probably Xerces), good speed, but can't handle misformatted XML
            parser = parserFactory.newSAXParser().getXMLReader();
        } else {
            // This is 2x slower than JDK7 implementation
            // with misformatted XML, because it treats it like HTML
            parser = XMLReaderFactory.createXMLReader("hotsax.html.sax.SaxParser");
            // This one is 2x-3x slower than default, but can cope with misformatted XML
            //XMLReader parser = XMLReaderFactory.createXMLReader("org.ccil.cowan.tagsoup.Parser");
            // as fast as default, maybe a little faster, but can't handle misformatted XML
            //XMLReader parser = XMLReaderFactory.createXMLReader("com.bluecast.xml.Piccolo");
        }
        MZXMLListOfScanDefaultSAXHandler handler = new MZXMLListOfScanDefaultSAXHandler(excludeEmptyScans, numScansInChunk);
        parser.setContentHandler(handler);
        try {
            // TODO: this creates a lot of garbage, but there is no good way to be sure,
            // that this chunk of XML is well formatted.
            if (useJdkSaxParser) {
                str = "<container>".concat(str).concat("</container>");
            } else if (!isSaxParserXmlFormatTolerant) {
                str = "<container>".concat(str).concat("</container>");
            }
            //System.out.println(containerWrappedString);
            parser.parse(new InputSource(new StringReader(str)));
        } catch (SAXException | IOException e) {
            throw e;
        }
        //parser = null;
        return handler.getParsedScans();
    }

}
