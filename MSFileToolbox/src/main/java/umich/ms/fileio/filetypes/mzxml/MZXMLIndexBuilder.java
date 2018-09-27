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

import java.nio.charset.StandardCharsets;
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
import umich.ms.logging.LogHelper;
import umich.ms.util.OffsetLength;

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
   *
   * @param info info about offsets in the file and in the currently read buffer
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

      final XMLStreamReaderImpl.LocationImpl location = reader.getLocation();
      int eventType = XMLStreamConstants.END_DOCUMENT;
      CharArray localName, attr;
      Attributes attrs;
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

        // Process the read event
        switch (eventType) {

          case XMLStreamConstants.START_ELEMENT:
            localName = reader.getLocalName();
            attrs = reader.getAttributes();

            if (localName.contentEquals(MZXMLMultiSpectraParser.TAG.SCAN.name)) {
              if (vars.offsetLo != null) {
                // this means we've encountered nested Spectrum tags
                long lastStartTagPos = location.getLastStartTagPos();
                vars.length = (int) (vars.offsetLo - lastStartTagPos);
                addAndFlush(result, info.offsetInFile);
              }

              //tagScanStart(reader);
              vars.offsetLo = location.getLastStartTagPos();
              try {
                vars.scanNumRaw = attrs.getValue(MZXMLMultiSpectraParser.ATTR.SCAN_NUM.name)
                    .toInt();
              } catch (NumberFormatException e) {
                throw new FileParsingException("Malformed scan number while building index", e);
              }

            }
            break;

          case XMLStreamConstants.CHARACTERS:
            break;

          case XMLStreamConstants.END_ELEMENT:
            localName = reader.getLocalName();

            if (localName.contentEquals(MZXMLMultiSpectraParser.TAG.SCAN.name)) {
              vars.offsetHi = location.getTotalCharsRead();
              addAndFlush(result, info.offsetInFile);
            }

            break;
        }
      } while (eventType != XMLStreamConstants.END_DOCUMENT);

    } finally {
      addAndFlush(result, info.offsetInFile);

      // we need to return the reaer to the pool, if we borrowed it from there
      if (pool != null && reader != null) {
        pool.returnObject(reader);
      }
    }

    return result;
  }

  private void addAndFlush(Result<MZXMLIndexElement> result, long offsetInFile)
      throws FileParsingException {
//        if (vars.scanNumRaw == -1 || vars.offsetLo == null) {
//            throw new IllegalStateException("When building index some variables were not set");
//        }
//
//        int len = vars.length != null ? vars.length : -1;
//        OffsetLength offsetLength = new OffsetLength(offsetInFile + vars.offset, len);
//        MZXMLIndexElement idxElem = new MZXMLIndexElement(vars.scanNumRaw, vars.scanNumRaw, offsetLength);
//        if (len != -1) {
//            result.addIndexElement(idxElem);
//        } else {
//            result.addStartTag(idxElem);
//        }

    //---------------------------------------------------------------

    if (vars.offsetLo != null) {
      // start tag was there
      if (vars.scanNumRaw == -1) {
        throw new IllegalStateException(
            "When building index raw scan number was not found for offset: " +
                offsetInFile + vars.offsetLo);
      }
      if (vars.offsetHi != null) {
        // fully enclosed scan
        long len = vars.offsetHi - vars.offsetLo;
        if (len < 0) {
          throw new FileParsingException("Calculated length was less than zero");
        }
        if (len > Integer.MAX_VALUE) {
          throw new FileParsingException("Calculated length was larger than Integer.MAX_VALUE");
        }
        result.addIndexElement(new MZXMLIndexElement(
            vars.scanNumRaw, vars.scanNumRaw,
            new OffsetLength(offsetInFile + vars.offsetLo, (int) len)));

      } else {
        // start tag only
        result.addStartTag(new MZXMLIndexElement(
            vars.scanNumRaw, vars.scanNumRaw, new OffsetLength(offsetInFile + vars.offsetLo, -1)));
      }
    } else if (vars.offsetHi != null) {
      // end tag only
      result.addCloseTag(new MZXMLIndexElement(
          -1, -1, new OffsetLength(offsetInFile + vars.offsetHi, 0)));
    }

    vars.reset();

    //---------------------------------------------------------------

    vars.reset();
  }
}
