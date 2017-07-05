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

import umich.ms.datatypes.scan.PeaksCompression;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.helpers.DefaultHandler;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.impl.ScanDefault;
import umich.ms.datatypes.scan.props.Polarity;
import umich.ms.datatypes.scan.props.PrecursorInfo;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.datatypes.spectrum.impl.SpectrumDefault;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.mzxml.MZXMLPeaksDecoder;
import umich.ms.util.ByteArrayHolder;
import umich.ms.util.base64.Base64;
import umich.ms.util.base64.Base64Context;
import umich.ms.util.base64.Base64ContextPooled;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

/**
 * @deprecated true SAX is not used anymore for parsing, {@link umich.ms.fileio.filetypes.mzxml.MZXMLMultiSpectraParser}
 * is used instead.
 *
 * This is the handler for use in SAX parsing Scans from mzXML files.
 * It can handle multiple Scans being given to it in a string/stream,
 * BUT if you are doing this, be sure to wrap multiple <scan> elements
 * in a container tag (anything you like) and don't forget to close this
 * tag afterwards!
 *
 * WARNING: after getting the parsed scans, their parent field won't be set properly!
 * So you'll need to traverse the list of Scans sorted by scan number, and set the
 * parent/child relationships yourself.
 *
 * Author: Dmitry Avtonomov (dmitriya)
 */
@Deprecated
class MZXMLListOfScanDefaultSAXHandler extends DefaultHandler {
    /** This is the list of parsed scans. */
    private List<IScan> parsedScans;

    private ScanDefault curScan;
    private Double isolationWindowWidth = null;
    private int peaksCount = 0;
    private StringBuilder charBuffer = new StringBuilder(1 << 18); // 256k buffer
    private String compressionType = null;
    private Integer precision = null;
    private PrecursorInfo precursorInfo = null;
    private static DatatypeFactory dataFactory = null;

    private boolean removeEmptyScans;

    static {
        try {
            // this is the name of the implementation that we want to get for DatatypeFactory, it's in JDK
            //String pkgName = "org.apache.xerces.jaxp.datatype.DatatypeFactoryImpl";
            ClassLoader orig = Thread.currentThread().getContextClassLoader();
            // setting the classloader to the classloader of THIS class' context,
            // otherwise in this class' context we won't be able to see DatatypeFactory service providers
            // this is because Xerces uses it's own context classloader, which breaks everything.
            Thread.currentThread().setContextClassLoader(MZXMLListOfScanDefaultSAXHandler.class.getClassLoader());
            try {
                dataFactory = DatatypeFactory.newInstance();
            } finally {
                // restore the classloader to the original Context classloader of this thread
                Thread.currentThread().setContextClassLoader(orig);
            }
        } catch (DatatypeConfigurationException e) {
            Logger.getLogger(MZXMLListOfScanDefaultSAXHandler.class.getCanonicalName()).log(Level.SEVERE,
                    "Couldn't construst DatatypeFactory.newInstance() in a static block");
        }
    }

    /**
     *
     * @param removeEmptyScans If we encounter a scan with no m/z values, should it be removed?
     * @param numScansToBeProcessed if you know beforehand, how many scans are to be processed by this handler,
     *                              specify it here, otherwise set to null
     */
    public MZXMLListOfScanDefaultSAXHandler(boolean removeEmptyScans, Integer numScansToBeProcessed) {
        this.removeEmptyScans = removeEmptyScans;
        if (numScansToBeProcessed != null) {
            this.parsedScans = new ArrayList<>(numScansToBeProcessed);
        } else {
            this.parsedScans = new ArrayList<>();
        }
    }

    public boolean isRemoveEmptyScans() {
        return removeEmptyScans;
    }

    /**
     * If set to true, will remove scans with no m/z values from the returned list of scans.
     * @param removeEmptyScans
     */
    public void setRemoveEmptyScans(boolean removeEmptyScans) {
        this.removeEmptyScans = removeEmptyScans;
    }

//    @Override
//    public void fatalError(SAXParseException e) throws SAXException {
//        int a = 1;
//    }
//
//    @Override
//    public void error(SAXParseException e) throws SAXException {
//        int a = 1;
//        return;
//    }

    public List<IScan> getParsedScans() {
        return parsedScans;
    }


    private String checkTagLocalNameQualifiedName(String lName, String qName) throws SAXException {
        String tagName = null;
        if (lName != null && !lName.isEmpty()) {
            tagName = lName;
        } else if (qName != null && !qName.isEmpty()) {
            tagName = qName;
        } else {
            throw new SAXException("Found weird tag which had both localName and qualifiedName of zero size");
        }
        return tagName;
    }

    /**
     * Checks if a specific tag contained an attribute. If it didn't contain it and
     * the attribute was marked as required, then and Exception is thrown.
     * @param attrs Attributes object to search in
     * @param attrName name of the attribute (case sensitive, but we try to overcome this in code)
     * @param tagName parent tag of that attribute
     * @param required is the attribute required? if not, and it's not found, null will be returned
     * @return String value of the attribute, or null if it was not found and was not required
     * @throws SAXNotRecognizedException if the attribute was required, but could not be found
     */
    private String checkCurrentAttribute(Attributes attrs, String attrName, String tagName, boolean required) throws SAXNotRecognizedException {
        String attrValue = attrs.getValue(attrName);
        if (attrValue == null) {
            // the getValue method is case sensitive, so we will sometimes not
            // get the value at first attempt, so we try lowercase version
            attrValue = attrs.getValue(attrName.toLowerCase());
            if (attrValue == null && required)
                throw new SAXNotRecognizedException("Could not find \"" + attrName + "\" property in <" + tagName + ">");
        }
        return attrValue;
    }

    @Override
    public void startElement(String namespaceURI,
                             String lName, // local name
                             String qName, // qualified name
                             Attributes attrs) throws SAXException {

        String tagName = checkTagLocalNameQualifiedName(lName, qName);
        // <scan>
        if (tagName.equalsIgnoreCase("scan")) {

            addCurScanAndFlushVars();

            // Only num, msLevel & peaksCount values are required according to mzXML standard, the others are optional
            String curAttr;
            curAttr = checkCurrentAttribute(attrs, "num", tagName, true);
            int scanNumber = Integer.parseInt(curAttr);

            curAttr = checkCurrentAttribute(attrs, "msLevel", tagName, true);
            int msLevel = Integer.parseInt(curAttr);
            curAttr = checkCurrentAttribute(attrs, "peaksCount", tagName, true);
            peaksCount = Integer.parseInt(curAttr);


            // Parse retention time
            double retentionTime = -1d;
            String retentionTimeStr = checkCurrentAttribute(attrs, "retentionTime", tagName, true);
            if (retentionTimeStr != null) {
                retentionTime = dataFactory.newDuration(retentionTimeStr).getTimeInMillis(new Date()) / 1000d / 60d;
            } else {
                throw new SAXException("Could not read retention time for scan num: " + Integer.toString(scanNumber));
            }

            // Parse Polarity
            Polarity polarity = null;
            String polarityStr = checkCurrentAttribute(attrs, "polarity", tagName, false);
            if (polarityStr != null) {
                switch (polarityStr) {
                    case "+":
                        polarity = Polarity.POSITIVE;
                        break;
                    case "-":
                        polarity = Polarity.NEGATIVE;
                        break;
                    default:
                        polarity = null;
                }
            }

            // Parse centroided
            boolean centroided = false;
            String centroidedStr = checkCurrentAttribute(attrs, "centroided", tagName, false);
            if (centroidedStr != null) {
                if (centroidedStr.equals("1") || Boolean.parseBoolean(centroidedStr) == true) {
                    centroided = true;
                }
            }

            curScan = new ScanDefault(scanNumber, retentionTime, msLevel, centroided);
            curScan.setPolarity(polarity);

            return;
        }

        // <peaks>
        if (tagName.equalsIgnoreCase("peaks")) {
            // clean the current char buffer for the new element
            charBuffer.setLength(0);
            String compressionTypeStr = checkCurrentAttribute(attrs, "compressionType", tagName, false);
            if ((compressionTypeStr == null) || (compressionTypeStr.equals("none"))) {
                compressionType = null;
            } else {
                compressionType = compressionTypeStr;
            }
            String precisionStr = checkCurrentAttribute(attrs, "precision", tagName, false);
            if (precisionStr != null)
                precision = Integer.parseInt(precisionStr);
            else
                precision = 32; // if the precision was not set in the orignial mzXML, we don't try to guess anything.
            return;
        }

        // <precursorMz>
        if (tagName.equalsIgnoreCase("precursorMz")) {
            // clean the current char buffer for the new element
            charBuffer.setLength(0);
            precursorInfo = new PrecursorInfo();
            String parentScanNum = checkCurrentAttribute(attrs, "precursorScanNum", tagName, false);
            if (parentScanNum != null) {
                precursorInfo.setParentScanNum(Integer.parseInt(parentScanNum));
            }

            String windowWideness = checkCurrentAttribute(attrs, "windowWideness", tagName, false);
            if (windowWideness != null) {
                isolationWindowWidth = Double.parseDouble(windowWideness);
            }
            return;
        }

    }

    private void addCurScanAndFlushVars() {

        if (curScan != null) {
            if (isRemoveEmptyScans() && peaksCount == 0) {
                // Don't add this Scan
            } else {
                if (precursorInfo != null && curScan.getMsLevel() > 1) {
                    curScan.setPrecursor(precursorInfo);
                }
                // should this scan have a spectrumRef parsed? If so, then check if all important values were parsed
                // from scan info, otherwise calc them from the spectrum.
                ISpectrum spectrum = curScan.getSpectrumRef().get();
                // the scan must have been created with StorageStrategy set to STRONG, so if we don't find the spectrum
                // here, it means, that spectrum parsing was not needed for this scan in the first place
                if (spectrum != null) {
                    if (curScan.getTic() == null)               curScan.setTic(spectrum.getSumInt());
                    if (curScan.getScanMzWindowLower() == null) curScan.setScanMzWindowLower(spectrum.getMinMZ());
                    if (curScan.getScanMzWindowUpper() == null) curScan.setScanMzWindowUpper(spectrum.getMaxMZ());
                    if (curScan.getBasePeakIntensity() == null) curScan.setBasePeakIntensity(spectrum.getMaxInt());
                }
                parsedScans.add(curScan);
            }
        }
        curScan = null;
        precision = null;
        precursorInfo = null;
        compressionType = null;
        peaksCount = 0;
    }


    @Override
    public void endElement(String namespaceURI,
                           String lName, // local name
                           String qName  // qualified name
    ) throws SAXException {

        String tagName = checkTagLocalNameQualifiedName(lName, qName);

        // </scan>
        if (tagName.equalsIgnoreCase("scan")) {
            addCurScanAndFlushVars();
            return;
        }

        // </precursorMz>
        if (tagName.equalsIgnoreCase("precursorMz")) {
            double precursorMzLo = Double.parseDouble(charBuffer.toString());
            double precursorMzHi = precursorMzLo;
            if (isolationWindowWidth != null) {
                precursorMzLo = precursorMzLo - isolationWindowWidth/2d;
                precursorMzHi = precursorMzHi + isolationWindowWidth/2d;
            }
            precursorInfo.setMzRangeStart(precursorMzLo);
            precursorInfo.setMzRangeEnd(precursorMzHi);
            curScan.setPrecursor(precursorInfo);

            isolationWindowWidth = null;
            return;
        }

        // </peaks>
        if (tagName.equalsIgnoreCase("peaks")) {
            if (isRemoveEmptyScans() && charBuffer.length() == 0) {
                // some ABSciex converted data has empty scans, they don't even have a <precursorMz> tag
                // so those are actually useless, we'll just skip them...
                return;
            }

            try {
                char[] chars = new char[charBuffer.length()];
                charBuffer.getChars(0, charBuffer.length(), chars, 0);
                Base64 base64 = new Base64();
                Base64Context ctx = new Base64ContextPooled(); // this ctx will borrow a ByteArrayHolder from pool
                Base64Context decodedB64 = base64.decode(chars, 0, chars.length, ctx);
                ByteArrayHolder bah = decodedB64.readResults();
                PeaksCompression compression = PeaksCompression.NONE;
                if (compressionType != null && "zlib".equalsIgnoreCase(compressionType)) {
                    compression = PeaksCompression.ZLIB;
                }
                MZXMLPeaksDecoder.DecodedData decoded =
                        MZXMLPeaksDecoder.decode(
                                bah.getUnderlyingBytes(), bah.getPosition(),
                                precision, compression);
                ctx.close(); // close the context, so it could return it's ByteArrayHolder to the pool

                //String peaksStr = charBuffer.toString();
                //MZXMLPeaksDecoder.DecodedData decoded =
                //        MZXMLPeaksDecoder.decode(
                //                MZXMLPeaksDecoder.decodedBase64Apache(peaksStr), precision, compressionType);

                double basepeakMz = decoded.mzs.length == 0 ? 0 : decoded.mzs[decoded.maxIntensityPos];
                ISpectrum spectrum = new SpectrumDefault(
                        decoded.mzs, decoded.intensities,
                        decoded.minIntensity, decoded.minIntensityNonZero,
                        decoded.maxIntensity, decoded.maxIntensityMz, decoded.intensitySum);
                curScan.setSpectrum(spectrum, false);
            } catch (DataFormatException | IOException | FileParsingException e) {
                throw new SAXException("A problem occurred when decoding Base64 peaks string.", e);
            }

//            ISpectrum spectrumRef = new SpectrumDefault(new double[0], new double[0]);
//            curScan.setSpectrum(spectrumRef);

            return;
        }
    }

    /**
     * characters()
     *
     * @param buf
     * @param offset
     * @param len
     * @throws org.xml.sax.SAXException
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char buf[], int offset, int len) throws SAXException {
        charBuffer.append(buf, offset, len);
    }
}
