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

package umich.ms.fileio.filetypes.thermo.raw.test;

import com4j.Holder;
import com4j.Variant;
import umich.ms.fileio.filetypes.thermo.raw.ThermoRawFile;
import umich.ms.fileio.filetypes.thermo.raw.com4j.ClassFactory;
import umich.ms.fileio.filetypes.thermo.raw.com4j.IXRawfile;
import umich.ms.fileio.filetypes.thermo.raw.com4j.IXRawfile5;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dmitry Avtonomov
 */
public class ThermoTest {
    public static void main(String[] args) throws IOException {

        if (!ThermoRawFile.isPlatformSupported())
            throw new IllegalStateException("This platform is not supported.");
        if (!ThermoRawFile.isThermoLibInstalled())
            throw new IllegalStateException("Thermo RawFileReader.dll is not installed. Either download an installer " +
                    "and install or register the dll using regsvr32.");


        IXRawfile xRawfile = ClassFactory.createMSFileReader_XRawfile();
        IXRawfile5 xRawfile5 = xRawfile.queryInterface(IXRawfile5.class);

        String directory = "C:\\data\\andrews\\xlink";
        Path dirPath = Paths.get(directory);
        DirectoryStream<Path> paths = Files.newDirectoryStream(dirPath, "*.raw");

//        String[] files = {
//                "534_DERL_1_BirAFLAG_Go_RIPA_26092013_BR1.raw",
//                "1562_03.raw",
//                "140120_Lysate90min_38.raw",
//                "b1926_293T_proteinID_06A_QE3_122212.raw"
//        };

        for (Path path : paths) {
            System.out.printf("Working with file: %s\n", path);

            xRawfile5.open(path.toString());
            Holder<Integer> isThereMsData = new Holder<>();
            xRawfile5.isThereMSData(isThereMsData);
            if (isThereMsData.value != 1)
                System.out.printf("There is no MS data in the file, skipping.\n");


            Holder<Integer> numControllers = new Holder<>();
            xRawfile5.getNumberOfControllers(numControllers);
            System.out.printf("Num controllers: %d\n", numControllers.value);

            ArrayList<Integer> goodControllers = new ArrayList<>();
            for (int i = 0; i <= numControllers.value; i++) {
                Holder<Integer> controllerType = new Holder<>();
                controllerType.value = null;
                xRawfile5.getControllerType(i, controllerType);
                System.out.printf("Controller number %d is of type %d.\n", i, controllerType.value);

                xRawfile5.setCurrentController(controllerType.value, i);
                Holder<Integer> firstSpectrumNumber = new Holder<>();
                xRawfile5.getFirstSpectrumNumber(firstSpectrumNumber);
                System.out.printf("It says the first spectrum number is: %d.\n", firstSpectrumNumber.value);
                Holder<Integer> lastSpectrumNumber = new Holder<>();
                xRawfile5.getLastSpectrumNumber(lastSpectrumNumber);
                System.out.printf("It says the last spectrum number is: %d.\n", lastSpectrumNumber.value);

                if (firstSpectrumNumber.value > 0 && lastSpectrumNumber.value > 0) {
                    Holder<String> instModel = new Holder<>();
                    xRawfile5.getInstModel(instModel);
                    Holder<String> instName = new Holder<>();
                    xRawfile5.getInstName(instName);
                    Holder<String> instSerNum = new Holder<>();
                    xRawfile5.getInstSerialNumber(instSerNum);
                    Holder<String> instVerHard = new Holder<>();
                    xRawfile5.getInstHardwareVersion(instVerHard);
                    Holder<String> instVerSoft = new Holder<>();
                    xRawfile5.getInstSoftwareVersion(instVerSoft);
                    Holder<Integer> instId = new Holder<>();
                    xRawfile5.getInstrumentID(instId);
                    System.out.printf("Instrument info:\n\tModel: %s\n\tName: %s\n\tSerial number: %s\n\tHardware ver: %s\n\tSoftware ver: %s\n\tID: %s\n",
                                      instModel.value, instName.value, instSerNum.value, instVerHard.value, instVerSoft.value, instId.value);
                }

                if (firstSpectrumNumber.value > 0 && lastSpectrumNumber.value > 0)
                    goodControllers.add(i);
            }

            if (goodControllers.isEmpty())
                throw new IllegalStateException("Could not find a single good MS controller.");
            if (goodControllers.size() > 1)
                throw new IllegalStateException("Found more than one good MS controller.");

            Holder<Integer> type = new Holder<>();
            int ctrlNum = goodControllers.get(0);
            xRawfile5.getControllerType(ctrlNum, type);
            int ctrlTyp = type.value;
            xRawfile5.setCurrentController(ctrlTyp, ctrlNum);

            Holder<Integer> firstScanNum = new Holder<>();
            xRawfile5.getFirstSpectrumNumber(firstScanNum);

            Holder<Integer> lastScanNum = new Holder<>();
            xRawfile5.getLastSpectrumNumber(lastScanNum);

            if (false) {
                for (int i = firstScanNum.value; i <= lastScanNum.value; i++) {
                    Holder<String> filterStr = new Holder<>();
                    xRawfile5.getFilterForScanNum(i, filterStr);
                    System.out.printf("Scan %04d: %s\n", i, filterStr.value);

                    Variant doubleData = new Variant();
                    Variant flagsData = new Variant();
                    Holder<Integer> numOfMsOrders = new Holder<>();
                    xRawfile5.getAllMSOrderData(i, doubleData, flagsData, numOfMsOrders);
                    System.out.printf("Scan %d has %d MS orders\n", i, numOfMsOrders.value);
                    if (numOfMsOrders.value > 0) {
                        Object isolationData = doubleData.get();
                        int a = 1;
                    }
                }
            }

            if (true) {
                Variant filters = new Variant();
                Holder<Integer> filtersSize = new Holder<>();
                xRawfile5.getFilters(filters, filtersSize);
                Object filtersArray = filters.get();
                System.out.printf("\tThe file has a total of %d filter lines\n", filtersSize.value);
                Pattern pattern = Pattern.compile("^([A-Z]+).*");
                String[] filtersStrArray = (String[])filtersArray;
                Set<String> uniqueInstIdentifiers = new HashSet<>();
                for (String s : filtersStrArray) {
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches())
                        uniqueInstIdentifiers.add(matcher.group(1));
                }
                if (!uniqueInstIdentifiers.isEmpty()) {
                    System.out.printf("\tInstrument identifiers: %s\n", Arrays.toString(uniqueInstIdentifiers.toArray(new String[0])));
                }
            }

            Holder<Integer> scanNum = new Holder<>(15);
            String filter = "";
            int cutoffType = 0;
            int cutoffValue = 0;
            Holder<Integer> arrSize = new Holder<>();
            Holder<Double> cetroidWidth = new Holder<>();
            Variant masses = new Variant(Variant.Type.VT_EMPTY);
            Variant flags = new Variant(Variant.Type.VT_EMPTY);
            xRawfile5.getMassListFromScanNum(scanNum, filter, cutoffType, cutoffValue,
                                             Integer.MAX_VALUE, 0, cetroidWidth, masses, flags, arrSize);


            Variant.Type massesTypeAfterCall = masses.getType();
            Variant.Type flagsTypeAfterCall = flags.getType();

            Object massesRetrievedValue = masses.get();


            xRawfile5.close();
        }
    }
}
