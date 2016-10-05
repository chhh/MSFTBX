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

package umich.ms.fileio.filetypes.thermo.raw;

import com4j.ComException;
import com4j.ExecutionException;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.AbstractLCMSDataSource;
import umich.ms.fileio.filetypes.thermo.raw.com4j.ClassFactory;
import umich.ms.fileio.filetypes.thermo.raw.com4j.IXRawfile;
import umich.ms.fileio.filetypes.thermo.raw.com4j.IXRawfile5;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * LC/MS data source for Thermo RAW files. Can only be used on Windows.<br/>
 *
 * Requires installation of MSFileReader library from Thermo, it can be downloaded here:<br/>
 *  https://thermo.flexnetoperations.com<br/>
 * You will need to register there first. The latest version as of this writing (2016-04-25) was 3.1-SP2.
 * Install it as Administrator, otherwise the service might not get registered.
 *
 * Created by Dmitry Avtonomov on 2016-04-25.
 */
public class ThermoRawFile extends AbstractLCMSDataSource<ThermoRawIndex>{
    protected ThermoRawIndex index;
    protected LCMSRunInfo runInfo;
    protected volatile IXRawfile5 xRawfile5 = null;

    /**
     * Just checks if the OS is some flavor of windows.
     */
    public static boolean isPlatformSupported() {
        String osName = System.getProperty("os.name");
        return osName.toLowerCase().startsWith("windows");
    }

    /**
     * Checks if the MSFileReader Type Library from Thermo is installed as a COM object.<br/>
     * We're using the latest IXrawfile5 interface, so if the machine has an older version of the
     * library, which does not provide IXrawfile5, this method returns false.
     */
    public static boolean isThermoLibInstalled() {
        try {
            IXRawfile xRawfile = ClassFactory.createMSFileReader_XRawfile();
            IXRawfile5 xRawfile5 = xRawfile.queryInterface(IXRawfile5.class);
            if (xRawfile5 == null)
                return false;
        } catch (ComException | ExecutionException ex) {
            // looks like we could not find the type library, bail out
            return false;
        }
        return true;
    }

    protected synchronized IXRawfile5 fetchXrawfile () {
        if (xRawfile5 == null) {
            IXRawfile xRawfile = ClassFactory.createMSFileReader_XRawfile();
            xRawfile5 = xRawfile.queryInterface(IXRawfile5.class);
            xRawfile5.open(path);
        }
        return xRawfile5;
    }

    /**
     * Checks if the particular machine the code runs on can read Thermo RAW files.<br/>
     * That is:
     * <ul>
     *     <li>The OS is Windows</li>
     *     <li>Thermo MSFileReader is installed</li>
 *     </ul>
     */
    public static boolean isThermoSupportedHere() {
        return isPlatformSupported() && isThermoLibInstalled();
    }

    /**
     * Create a new source with autoload of spectra disabled.
     *
     * @param path
     */
    public ThermoRawFile(String path) {
        super(path);
    }

    @Override
    public String getName() {
        return path;
    }

    @Override
    public void releaseMemory() {
        synchronized (this) {
            if (xRawfile5 != null) {
                xRawfile5.close();
                xRawfile5 = null;
            }
            index = null;
            runInfo = null;
        }
    }

    @Override
    public LCMSRunInfo parseRunInfo() throws FileParsingException {
        LCMSRunInfo info = new LCMSRunInfo();
        return null;
    }

    @Override
    public ThermoRawIndex getIndex() {
        return null;
    }

    @Override
    public ThermoRawIndex fetchIndex() throws FileParsingException {
        return null;
    }

    @Override
    public ThermoRawIndex parseIndex() throws FileParsingException {
        return null;
    }

    @Override
    public ISpectrum parseSpectrum(int num) throws FileParsingException {
        return null;
    }

    @Override
    public IScan parseScan(int num, boolean parseSpectrum) throws FileParsingException {
        return null;
    }

    @Override
    public List<IScan> parse(LCMSDataSubset subset) throws FileParsingException {
        return null;
    }

    @Override
    public List<IScan> parse(List<Integer> scanNums) throws FileParsingException {
        return null;
    }
}
