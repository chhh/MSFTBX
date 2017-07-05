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
package umich.ms.fileio.filetypes.mzxml;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import umich.ms.datatypes.scan.PeaksCompression;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.mzml.MZMLPeaksDecoder;
import umich.ms.fileio.filetypes.util.ZlibInflater;
import umich.ms.util.ByteArrayHolder;

/**
 * Author: Dmitry Avtonomov (dmitriya)
 */
public class MZXMLPeaksDecoder {
    
    // Suppress default constructor for noninstantiability
    private MZXMLPeaksDecoder() {
        throw new AssertionError();
    }

    /**
     * Convenience shortcut, which assumes the whole {@code bytesIn} array is useful data.
     * @param bytesIn
     * @param precision
     * @param compression
     * @return
     * @throws FileParsingException
     * @throws IOException
     * @throws DataFormatException
     */
    public static DecodedData decode(byte[] bytesIn, int precision, PeaksCompression compression)
            throws FileParsingException, IOException, DataFormatException {
        return decode(bytesIn, bytesIn.length, precision, compression);
    }

    /**
     * Converts a base64 encoded mz-intensity string from used in mzXML and mzML
     * files to an array of doubles.
     * If the original precision was 32 bit, you still get doubles as output, would
     * be too complicated to provide another method to parseIndexEntries them as floats.
     * Hopefully some day everything will be in 64 bits anyway.
     * @param bytesIn Byte array, decoded from a base64 encoded string
     *                E.g. like: eNoNxltIkwEYBuAOREZFhrCudGFbbraTU+Zmue...
     * @param lengthIn length of data to be treated as mz/int values
     * @param precision allowed values: 32 and 64
     * @param compression null or {@link PeaksCompression#NONE} have the
     *                    same effect. Otherwise the binary data will be inflated according to the compression rules.
     * @return
     * @throws DataFormatException
     * @throws IOException
     * @throws umich.ms.fileio.exceptions.FileParsingException
     */
    public static DecodedData decode(byte[] bytesIn, int lengthIn, int precision, PeaksCompression compression)
            throws DataFormatException, IOException, FileParsingException {
        if (compression == null) {
            compression = PeaksCompression.NONE;
        }
        // for some reason there sometimes might be zero length <peaks> tags (ms2 usually)
        // in this case we jsut return an empty result
        if(bytesIn.length == 0 || lengthIn == 0) {
            return DecodedData.createEmpty();
        }

        ByteArrayHolder bytes;
        boolean isBytesFromPool = false;
        
        switch (compression) {
            case ZLIB:
                bytes = ZlibInflater.zlibUncompressBuffer(bytesIn, lengthIn, null);
                isBytesFromPool = true;
                break;
            case NUMPRESS_LINPRED:
            case NUMPRESS_POSINT:
            case NUMPRESS_SHLOGF:
                throw new UnsupportedOperationException("Numpress compression is not yet supported");
            default:
                // we didn't need to uncompress anything, so we'll just wrap the input
                // in a holder
                bytes = new ByteArrayHolder(bytesIn);
                bytes.setPosition(lengthIn);
        }


        int decodedLen = bytes.getPosition(); // in bytes
        byte[] decoded = bytes.getUnderlyingBytes();
        int chunkSize = precision / 8;   // in bytes
        int numPeaks = decodedLen / ( 2 * chunkSize );

        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////
        //////                                             //////
        //////                                             //////
        //////               CRITICAL SPOT                 //////
        //////                                             //////
        //////       We might not have enough memory       //////
        //////                                             //////
        /////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////

        try {
            double[] mzsArray = new double[numPeaks];
            double[] intArray = new double[numPeaks];

            int maxIntensityPos, minIntensityPos, minIntensityNonZeroPos;
            double maxIntensity, minIntensity, minIntensityNonZero, intensitySum;

            int offset;
            maxIntensityPos = 0;
            maxIntensity = Double.NEGATIVE_INFINITY;
            minIntensityPos = 0;
            minIntensity = Double.POSITIVE_INFINITY;
            minIntensityNonZeroPos = 0;
            minIntensityNonZero = Double.POSITIVE_INFINITY;
            intensitySum = 0d;

            switch (precision) {
                case (32): {
                    int asInt;
                    float mzAsFloat, intAsFloat;
                    
                    for (int i = 0; i < numPeaks; i++) {
                        offset = i * 2 * chunkSize;

                        // hopefully this way is faster
                        asInt = ((decoded[offset + 3] & 0xFF)) // zero shift
                                | ((decoded[offset + 2] & 0xFF) << 8)
                                | ((decoded[offset + 1] & 0xFF) << 16)
                                | ((decoded[offset] & 0xFF) << 24);
                        mzAsFloat = Float.intBitsToFloat(asInt);
                        offset += chunkSize; // moving the pointer to intensity part of this mz/intensity pair
                        asInt = ((decoded[offset + 3] & 0xFF)) // zero shift
                                | ((decoded[offset + 2] & 0xFF) << 8)
                                | ((decoded[offset + 1] & 0xFF) << 16)
                                | ((decoded[offset] & 0xFF) << 24);
                        intAsFloat = Float.intBitsToFloat(asInt);
                        assert (intAsFloat >= 0); // doesn't make sense otherwise

                        if (intAsFloat > maxIntensity) {
                            maxIntensity = intAsFloat;
                            maxIntensityPos = i;
                        }

                        if (intAsFloat < minIntensityNonZero) {
                            if (intAsFloat > 0) {
                                minIntensityNonZero = intAsFloat;
                                minIntensityNonZeroPos = i;
                            }
                            if (intAsFloat < minIntensity) {
                                minIntensity = intAsFloat;
                                minIntensityPos = i;
                            }
                        }

                        intensitySum = intensitySum + intAsFloat;

                        mzsArray[i] = mzAsFloat;
                        intArray[i] = intAsFloat;
                    }
                    break;
                }
                case (64): {
                    long asLong;
                    double mzAsDouble, intAsDouble;

                    for (int i = 0; i < numPeaks; i++) {
                        offset = i * 2 * chunkSize;

                        asLong = ((long) (decoded[offset + 7] & 0xFF)) // zero shift
                                | ((long) (decoded[offset + 6] & 0xFF) << 8)
                                | ((long) (decoded[offset + 5] & 0xFF) << 16)
                                | ((long) (decoded[offset + 4] & 0xFF) << 24)
                                | ((long) (decoded[offset + 3] & 0xFF) << 32)
                                | ((long) (decoded[offset + 2] & 0xFF) << 40)
                                | ((long) (decoded[offset + 1] & 0xFF) << 48)
                                | ((long) (decoded[offset] & 0xFF) << 56);
                        mzAsDouble = Double.longBitsToDouble(asLong);
                        offset += chunkSize; // moving the pointer to intensity part of this mz/intensity pair
                        asLong = ((long) (decoded[offset + 7] & 0xFF)) // zero shift
                                | ((long) (decoded[offset + 6] & 0xFF) << 8)
                                | ((long) (decoded[offset + 5] & 0xFF) << 16)
                                | ((long) (decoded[offset + 4] & 0xFF) << 24)
                                | ((long) (decoded[offset + 3] & 0xFF) << 32)
                                | ((long) (decoded[offset + 2] & 0xFF) << 40)
                                | ((long) (decoded[offset + 1] & 0xFF) << 48)
                                | ((long) (decoded[offset] & 0xFF) << 56);
                        intAsDouble = Double.longBitsToDouble(asLong);
                        assert (intAsDouble >= 0); // doesn't make sense otherwise

                        if (intAsDouble > maxIntensity) {
                            maxIntensity = intAsDouble;
                            maxIntensityPos = i;
                        }

                        if (intAsDouble < minIntensityNonZero) {
                            if (intAsDouble > 0) {
                                minIntensityNonZero = intAsDouble;
                                minIntensityNonZeroPos = i;
                            }
                            if (intAsDouble < minIntensity) {
                                minIntensity = intAsDouble;
                                minIntensityPos = i;
                            }
                        }

                        intensitySum = intensitySum + intAsDouble;

                        mzsArray[i] = mzAsDouble;
                        intArray[i] = intAsDouble;
                    }
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Precision can only be 32/64 bits, other values are not valid.");
                    // this was the easy way, don't want to throw it away for educational reasons
                    // it's a little slower than the methods used above
                    /*
                     for (int i = 0; i < numPeaks; i++) {
                     offset = i * 2 * chunkSize;
                     if (precision == 32) {
                     mzAsDouble = ByteBuffer.wrap(decoded, offset, chunkSize).getFloat();
                     intAsDouble = ByteBuffer.wrap(decoded, offset + chunkSize, chunkSize).getFloat();
                     } else if (precision ==  64) {
                     mzAsDouble = ByteBuffer.wrap(decoded, offset, chunkSize).getDouble();
                     intAsDouble = ByteBuffer.wrap(decoded, offset + chunkSize, chunkSize).getDouble();
                     }

                     peaksArray[0][i] = mzAsDouble;
                     peaksArray[1][i] = intAsDouble;
                     }
                     */
                }
            }

            return new DecodedData(mzsArray, intArray,
                    maxIntensityPos, maxIntensity,
                    minIntensityPos, minIntensity,
                    minIntensityNonZeroPos, minIntensityNonZero,
                    intensitySum);

        } catch (OutOfMemoryError oom) {
            throw new FileParsingException("Could not allocate arrays during spectra decoding step", oom);
        } finally {
            // return ByteArrayHolder to the pool
            if (isBytesFromPool) {
                try {
                    ZlibInflater.getPool().returnObject(bytes);
                } catch (Exception e) {
                    throw new FileParsingException("Could not return ByteArrayHolder to the pool.", e);
                }
            }
        }


    }

    private static byte[] charsToBytes(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
        //Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
        //Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
        return bytes;
    }

    public static class DecodedData {
        public final double[] mzs;
        public final double[] intensities;
        public final int maxIntensityPos;
        public final double maxIntensity;
        public final double maxIntensityMz;
        public final int minIntensityPos;
        public final double minIntensity;
        public final int minIntensityNonZeroPos;
        public final double minIntensityNonZero;
        public final double intensitySum;

        public DecodedData(double[] mzs, double[] intensities,
                           int maxIntensityPos, double maxIntensity,
                           int minIntensityPos, double minIntensity,
                           int minIntensityNonZeroPos, double minIntensityNonZero,
                           double intensitySum) {
            this.mzs = mzs;
            this.intensities = intensities;
            this.maxIntensityPos = maxIntensityPos;
            this.maxIntensity = maxIntensity;
            this.minIntensityPos = minIntensityPos;
            this.minIntensity = minIntensity;
            this.minIntensityNonZeroPos = minIntensityNonZeroPos;
            this.minIntensityNonZero = minIntensityNonZero;
            this.intensitySum = intensitySum;

            maxIntensityMz = mzs.length == 0 ? 0 : mzs[maxIntensityPos];
        }

        public static DecodedData createEmpty() {
            return new DecodedData(new double[0], new double[0], -1, 0, -1, 0, -1, 0, 0);
        }
    }
}
