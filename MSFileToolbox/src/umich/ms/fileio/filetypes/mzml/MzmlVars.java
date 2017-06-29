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

import umich.ms.datatypes.scan.PeaksCompression;
import umich.ms.datatypes.scan.impl.ScanDefault;
import umich.ms.datatypes.scan.props.PrecursorInfo;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * @author Dmitry Avtonomov
 */
public class MzmlVars {
    boolean isNonMassSpectrum;
    ScanDefault curScan;
    Integer defaultArrayLength;
    Integer spectrumIndex;
    String spectrumId;
    Integer precursorSpectrumIndex;
    String activationMethodAbbreviation;
    List<PrecursorInfo> precursors;
    Double precursorIsoWndTarget;
    Double precursorIsoWndLoOffset;
    Double precursorIsoWndHiOffset;
    Double precursorIntensity;

    // vars for Index Building
    Long offset;
    Integer length;

    public enum BIN_DATA_TYPE {MZ, INTENSITY}


    Integer precision;
    EnumSet<PeaksCompression> compressions;
    BIN_DATA_TYPE binDataType;
    MZMLPeaksDecoder.DecodedData mzData;
    MZMLPeaksDecoder.DecodedData intensityData;

    public MzmlVars() {
        reset();
    }

    public void flushBinDataDescription() {
        precision = null;
        compressions = null;
        binDataType = null;
    }

    /**
     * Resets all held variables to their default values.
     */
    public final void reset() {
        isNonMassSpectrum = false;
        curScan = null;
        defaultArrayLength = null;
        spectrumIndex = null;
        spectrumId = null;
        precursorSpectrumIndex = null;
        activationMethodAbbreviation = null;
        precursors = new ArrayList<>(1);
        precursorIsoWndTarget = null;
        precursorIsoWndLoOffset = null;
        precursorIsoWndHiOffset = null;
        precursorIntensity = null;

        offset = null;
        length = null;

        precision = null;
        compressions = null;
        binDataType = null;
        mzData = null;
        intensityData = null;
    }

    public EnumSet<PeaksCompression> getCompressions() {
        if (compressions == null) {
            compressions = EnumSet.noneOf(PeaksCompression.class);
        }
        return compressions;
    }
}
