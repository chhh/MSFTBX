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

import static org.junit.Assert.*;
import static umich.ms.logging.LogHelper.configureJavaUtilLogging;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.junit.Test;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.StorageStrategy;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.scancollection.impl.ScanCollectionDefault;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.Opts;
import umich.ms.fileio.ResourceUtils;
import umich.ms.util.IntervalST;
import umich.ms.util.file.FileListing;

/**
 * @author Dmitry Avtonomov
 */
public class MZMLFileTest {

  private static final String RESOURCE_LOCATION = "mzml";
  List<Path> paths;

  @org.junit.Before
  public void setUp() throws Exception {
    paths = ResourceUtils.getResources(this.getClass(), RESOURCE_LOCATION);
  }

  @org.junit.After
  public void tearDown() throws Exception {
    paths.clear();
    paths = null;
  }

  @org.junit.Test
  public void parseRunInfo() throws Exception {
    for (Path p : paths) {
      System.out.printf("\n\nReading run info from file: %s\n", p.getFileName());

      final MZMLFile mzml = new MZMLFile(p.toString());
      final LCMSRunInfo runInfo = mzml.fetchRunInfo();

      System.out.printf("\tRun info: %s\n", runInfo);
    }
  }

  @org.junit.Test
  public void parseWholeFile() throws Exception {
    for (Path p : paths) {
      System.out.printf("\n\nParsing whole file: %s\n", p.getFileName());

      final MZMLFile mzml = new MZMLFile(p.toString());

      mzml.setNumThreadsForParsing(null);
      mzml.setTasksPerCpuPerBatch(1);
      mzml.setParsingTimeout(30 * 1000);

      final MZMLIndex index = mzml.fetchIndex();

      IScanCollection scans = new ScanCollectionDefault(true);
      scans.setDataSource(mzml);
      scans.loadData(LCMSDataSubset.STRUCTURE_ONLY, StorageStrategy.STRONG);
      System.out.printf("\tFound/parsed %d spectra\n", scans.getMapNum2scan().size());
    }
  }

  @Test
  public void parstTimsTOF() throws Exception {
    final MZMLFile mzml = new MZMLFile((new File("src/test/resources/mzml/timsTOF.mzML")).getAbsolutePath());
    mzml.setNumThreadsForParsing(null);
    IScanCollection scans = new ScanCollectionDefault(true);
    scans.setDataSource(mzml);
    scans.loadData(LCMSDataSubset.STRUCTURE_ONLY);
    TreeMap<Integer, IScan> numScanMap = scans.getMapNum2scan();

    IScan scan = numScanMap.get(1);
    assertEquals(1, (int) scan.getMsLevel());
    assertEquals(1, scan.getNum());
    assertEquals(1786, scan.getBasePeakIntensity(), 0.1);
    assertEquals(4.82482e05, scan.getTic(), 0.1);
    assertEquals(100, scan.getScanMzWindowLower(), 0.1);
    assertEquals(1700, scan.getScanMzWindowUpper(), 0.1);
    assertEquals(449.564209969336 / 60, scan.getRt(), 0.001);
    assertNull(scan.getPrecursor());
    assertNull(scan.getIm());
    ISpectrum spectrum = scan.fetchSpectrum();
    assertArrayEquals(new double[]{104.239393905247, 112.895830865148, 128.860594261069, 129.874983075566, 145.648696978129, 177.96414902657, 185.029518280685, 245.895536792203, 245.90052278429, 282.222776011189, 288.183101939983, 290.148512892271, 303.916014023736, 312.866568226224, 339.360336325965, 340.069457186584, 396.536569259226, 407.893501721442, 408.19537901974, 440.030232509396, 457.804773294497, 459.810536915166, 459.813946034527, 471.857757170813, 477.826836231092, 491.828898929481, 496.80574037131, 513.974956573784, 571.754981372259, 592.184874445908, 622.020288577988, 622.024253665928, 622.024253675928, 622.024253685928, 622.024253705928, 622.024253715928, 622.024253725928, 622.024253735928, 622.024253745928, 622.024253755928, 622.028218766506, 622.028218776506, 622.028218786506, 622.028218796506, 622.028218816506, 622.028218826506, 622.028218836506, 622.028218846506, 622.028218856506, 622.028218866506, 622.028218876506, 622.028218886506, 622.032183909721, 622.032183919721, 622.032183929721, 623.007985687538, 623.019890419654, 623.023858702302, 623.027826977587, 623.027826987587, 623.027827007587, 623.027827027587, 623.027827037587, 623.027827067587, 623.027827077587, 623.03179528551, 623.03179531551, 623.03179532551, 623.035763596071, 704.714382736738, 727.616034442824, 822.009201033879, 822.009201043879, 822.009201053879, 822.013759170282, 822.013759210282, 822.013759220282, 822.013759240282, 822.013759250282, 822.022875501, 822.022875521, 850.044341243965, 850.048976436522, 851.045842738912, 895.500557913535, 914.045302565053, 922.002954917867, 922.002954927867, 922.002954947867, 922.002954957867, 922.002954977867, 922.007782343545, 922.007782353545, 922.007782363545, 922.007782393545, 922.007782403545, 922.007782413545, 922.007782423545, 922.007782453545, 922.007782463545, 922.007782473545, 922.007782483545, 922.007782493545, 922.007782503545, 922.007782513545, 922.007782523545, 922.007782533545, 922.007782543545, 922.012609781859, 922.012609791859, 922.012609801859, 922.012609811859, 922.012609821859, 922.012609861859, 923.002501475621, 923.002501485621, 923.007331517245, 923.007331527245, 923.007331547245, 923.007331567246, 923.007331587246, 923.012161571507, 923.012161581507, 923.012161591507, 923.012161601508, 923.012161621508, 923.012161631508, 923.012161641508, 923.012161651507, 923.012161671507, 923.016991648406, 923.021821727943, 979.990644689821, 1034.00276194254, 1221.98184393104, 1221.98740144864, 1221.98740146864, 1342.52418267179, 1381.6117992411, 1391.34976751804, 1427.72263861261, 1444.30214533646, 1627.92834955395, 1689.02462210946, 1694.22294448407}, spectrum.getMZs(), 0.01);
    assertArrayEquals(new double[]{117, 113, 118, 144, 113, 131, 124, 124, 131, 113, 122, 129, 154, 132, 129, 115, 119, 163, 147, 139, 115, 127, 123, 144, 171, 130, 117, 160, 132, 129, 226, 130, 1289, 909, 1643, 522, 331, 1060, 1336, 124, 393, 154, 1633, 1786, 529, 1244, 1163, 1179, 536, 561, 1319, 1279, 552, 336, 682, 146, 127, 208, 193, 330, 114, 170, 291, 224, 178, 117, 356, 180, 237, 118, 153, 246, 134, 137, 212, 181, 113, 133, 222, 179, 205, 159, 119, 133, 115, 123, 710, 435, 224, 181, 425, 1197, 707, 985, 1066, 204, 760, 694, 118, 631, 1379, 1328, 1003, 400, 323, 1207, 298, 944, 893, 285, 321, 269, 872, 354, 140, 169, 320, 146, 254, 191, 114, 222, 401, 303, 304, 312, 198, 260, 126, 132, 130, 134, 120, 154, 126, 186, 120, 121, 116, 132, 119, 125, 118, 120, 118}, spectrum.getIntensities(), 0.01);
    assertArrayEquals(new double[]{1.0238515733660956, 1.1082483304706425, 1.1020746290185415, 1.2141890909741313, 1.2069916935036322, 1.1010456531317676, 1.1195660980953643, 0.70124928541201, 0.7229160603500849, 0.8177989423698458, 1.170999325983327, 0.8105817797046854, 1.237835157862196, 1.1236814301772158, 0.9476463204538805, 0.8786150078500531, 0.6960900512489925, 0.8281085501032983, 0.728074338115193, 0.8219228736449953, 0.8322321874465521, 0.7992397964872882, 0.8023331528362285, 0.7095036774748016, 0.7940840555661021, 0.8487255612302479, 0.8580022574827555, 1.204935228354479, 0.8961335358824026, 0.7002174532951879, 0.9517665504824355, 0.9497064501457565, 0.9661864309463777, 0.9682462964640676, 0.9620666118598368, 0.9527965896427721, 0.9723659394514225, 0.9569166728992643, 0.9610366387437522, 0.9733958318553799, 0.953826621464545, 0.9754555946523868, 0.9651564871812552, 0.9630965776380631, 0.9703061326322067, 0.9641265360785098, 0.9672163673739557, 0.9589766704976949, 0.9713360397103907, 0.9558866630927126, 0.9600066582897311, 0.9579466753675655, 0.9548566459478324, 0.974425716922342, 0.9692762182167919, 0.9620666118598368, 0.9579466753675655, 0.9661864309463777, 0.9682462964640676, 0.9589766704976949, 0.9569166728992643, 0.9630965776380631, 0.9610366387437522, 0.9692762182167919, 0.9651564871812552, 0.9548566459478324, 0.9600066582897311, 0.9620666118598368, 0.9641265360785098, 0.8219228736449953, 0.7631457506012898, 1.0989876793762952, 1.1082483304706425, 1.1051615127159526, 1.1092772550686327, 1.1020746290185415, 1.1010456531317676, 1.0969296763110679, 1.110306172339967, 1.1061904592943808, 1.1041325588105557, 1.1432276570164985, 1.1452850007636617, 1.1473423152151534, 1.086639221316696, 0.8868592854098833, 1.159685586750054, 1.1751131933128907, 1.1802553627383094, 1.1535140828018606, 1.1565998677297156, 1.1627712398649865, 1.1607141451110832, 1.170999325983327, 1.16688534150236, 1.1545426851009999, 1.1648283053285369, 1.161742696149368, 1.1524854731794292, 1.1740847374631702, 1.1689423483870813, 1.1699708408462248, 1.1658568270766254, 1.1576284480594479, 1.1781985169324662, 1.1679138486058194, 1.1771700830474607, 1.1720278037984662, 1.1637997762580166, 1.1555712800769256, 1.1792269434960563, 1.1761416418409616, 1.1730562742917214, 1.1586570210662013, 1.1586570210662013, 1.1792269434960563, 1.1740847374631702, 1.170999325983327, 1.1607141451110832, 1.16688534150236, 1.1771700830474607, 1.1648283053285369, 1.1658568270766254, 1.1627712398649865, 1.1699708408462248, 1.1730562742917214, 1.1679138486058194, 1.1689423483870813, 1.159685586750054, 1.161742696149368, 1.1720278037984662, 1.1637997762580166, 0.8518178594300059, 0.9424958677910867, 1.3590889133194244, 1.3611431844342885, 1.3631974263190865, 0.8847982600830089, 0.974425716922342, 0.8971639714416851, 1.0197333974329683, 0.926013186211319, 0.953826621464545, 0.9970813322942856, 0.914680252565039}, spectrum.getIMs(), 0.01);

    scan = numScanMap.get(2);
    assertEquals(2, (int) scan.getMsLevel());
    assertEquals(2, scan.getNum());
    assertEquals(1174, scan.getBasePeakIntensity(), 0.1);
    assertEquals(20397, scan.getTic(), 0.1);
    assertEquals(100, scan.getScanMzWindowLower(), 0.1);
    assertEquals(1700, scan.getScanMzWindowUpper(), 0.1);
    assertEquals(449.74565183775297 / 60, scan.getRt(), 0.001);
    assertEquals(1, (int) scan.getPrecursor().getCharge());
    assertEquals(922.488431793529, scan.getPrecursor().getMzTarget(), 1e-5);
    assertEquals(922.008162699114, scan.getPrecursor().getMzTargetMono(), 1e-5);
    assertEquals(1, (int) scan.getPrecursor().getParentScanNum());
    assertEquals(1.1674739349000001, scan.getIm(), 1e-5);
    spectrum = scan.fetchSpectrum();
    assertArrayEquals(new double[]{475.941644111497, 544.934417174827, 565.968609353197, 657.969066099315, 677.966614713052, 677.97075427706, 677.974893853705, 677.979033442988, 678.977042987308, 695.95904019833, 769.97620651756, 789.196555194153, 789.991746103623, 789.996214599344, 791.024304331706, 791.051133011241, 791.860678715468, 792.751209553068, 803.170205024824, 837.948051199225, 920.236965862133, 921.602322640714, 921.64093400873, 921.983645341533, 921.98847271666, 921.998127504828, 922.002954917867, 922.007782343545, 922.012609781859, 922.017437232812, 922.027092172629, 922.046402203911, 922.051229743325, 922.13812761378, 922.147783185468, 922.16226663778, 922.253997810316, 922.282966496818, 922.302279207235, 922.466445409553, 922.567849479685, 922.625797164829, 922.973521491261, 922.983181435498, 922.988011426573, 922.992841430285, 923.002501475621, 923.007331517245, 923.012161571507, 923.016991638406, 923.021821717943, 923.026651810117, 923.041142162464, 923.065293002457, 923.132917035214, 923.384113699849, 923.645007942969, 923.678830265168, 923.847951164666, 923.910771441641, 923.930101187306, 923.959096184926, 924.002589534382, 924.007422191953, 924.021920240491, 924.03158566937, 924.113743855787, 924.220071636133, 924.234571352811, 924.253904485305, 925.080584986886, 925.148282796923, 925.298193905178, 925.520665027334, 925.864096857587, 926.251138630154, 926.585027121659, 929.709068628311, 930.998964220204, 944.538220026239, 953.245604519118}, spectrum.getMZs(), 0.01);
    assertArrayEquals(new double[]{52, 25, 58, 41, 20, 9, 61, 28, 50, 36, 9, 24, 34, 42, 24, 51, 9, 42, 22, 9, 53, 79, 20, 108, 80, 658, 2100, 7053, 2328, 268, 123, 36, 9, 54, 21, 52, 9, 9, 55, 9, 25, 70, 51, 71, 30, 42, 285, 685, 1347, 890, 38, 48, 22, 9, 63, 25, 9, 11, 9, 51, 20, 32, 106, 105, 45, 76, 44, 11, 9, 10, 12, 39, 19, 11, 10, 39, 12, 22, 21, 19, 42}, spectrum.getIntensities(), 0.01);
    assertArrayEquals(new double[]{1.1699708408462248, 1.1627712398649865, 1.1720278037984662, 1.1565998677297156, 1.170999325983327, 1.170999325983327, 1.1627712398649865, 1.1637997762580166, 1.1730562742917214, 1.1658568270766254, 1.1555712800769256, 1.1679138486058194, 1.1689423483870813, 1.1699708408462248, 1.1771700830474607, 1.1637997762580166, 1.1658568270766254, 1.1751131933128907, 1.1699708408462248, 1.1699708408462248, 1.1740847374631702, 1.1607141451110832, 1.1637997762580166, 1.1771700830474607, 1.159685586750054, 1.1802553627383094, 1.1751131933128907, 1.1648283053285369, 1.1699708408462248, 1.1781985169324662, 1.1565998677297156, 1.1740847374631702, 1.1771700830474607, 1.170999325983327, 1.1792269434960563, 1.1751131933128907, 1.159685586750054, 1.161742696149368, 1.1730562742917214, 1.1627712398649865, 1.16688534150236, 1.1607141451110832, 1.1689423483870813, 1.1679138486058194, 1.1720278037984662, 1.1658568270766254, 1.1637997762580166, 1.1555712800769256, 1.1761416418409616, 1.1576284480594479, 1.1586570210662013, 1.1792269434960563, 1.1761416418409616, 1.1720278037984662, 1.1658568270766254, 1.1781985169324662, 1.1740847374631702, 1.170999325983327, 1.1637997762580166, 1.170999325983327, 1.1658568270766254, 1.1720278037984662, 1.1555712800769256, 1.1658568270766254, 1.1689423483870813, 1.1607141451110832, 1.1637997762580166, 1.1720278037984662, 1.1648283053285369, 1.1689423483870813, 1.1781985169324662, 1.1658568270766254, 1.161742696149368, 1.1730562742917214, 1.1679138486058194, 1.16688534150236, 1.1565998677297156, 1.1576284480594479, 1.1751131933128907, 1.1607141451110832, 1.170999325983327}, spectrum.getIMs(), 0.01);

    System.out.printf("\tFound/parsed %d spectra\n", scans.getMapNum2scan().size());
  }


  @Test
  public void buildIndexTestLocation() throws Exception {
    String file = "emptyScan.mzML";
    int expectedCount = 1;
    long expectedOffset = 4224;
    int expectedLen = 4754;

    final Path path = ResourceUtils.getResource(this.getClass(), RESOURCE_LOCATION, file);

    final MZMLFile mzml = new MZMLFile(path.toString());
    final MZMLIndex index = mzml.fetchIndex();
    assertEquals("Wrong number of scans in the build index: " + file, expectedCount, index.size());
    final MZMLIndexElement elem = index.getMapByNum().firstEntry().getValue();
    assertEquals("Wrong offset: " + file, expectedOffset, elem.getOffsetLength().offset);
    assertEquals("Wrong length: " + file, expectedLen, elem.getOffsetLength().length);
  }

  @Test
  public void buildIndexTestCount() throws Exception {
    LinkedHashMap<String, Integer> file2scanCount = new LinkedHashMap<>();
    //file2scanCount.put("RawCentriodCidWithMsLevelInRefParamGroup.mzML", 102);
    file2scanCount.put("RawCentriodCidWithMsLevelInRefParamGroup_BOM_formatted.mzML", 102);

    for (Map.Entry<String, Integer> e : file2scanCount.entrySet()) {
      int expectedCount = e.getValue();
      String file = e.getKey();
      final Path path = ResourceUtils.getResource(this.getClass(), RESOURCE_LOCATION, file);

      final MZMLFile mzml = new MZMLFile(path.toString());
      final MZMLIndex index = mzml.fetchIndex();
      assertEquals("Wrong number of scans returned after building index: " + file, expectedCount,
          index.size());
    }
  }

  //@org.junit.Test
  public void parseWholeFileSpeed() throws Exception {
    //String file = "C:\\data\\andy\\q01507.mzML";
    //String file = "C:\\data\\andy\\no-index\\q01507-broken-index.mzML";
    String file = "C:\\data\\andy\\20161207_200ng_HeLa_DIA_VarTest.mzML";
    //String file = "C:\\projects\\batmass\\MSFTBX\\MSFileToolbox\\test\\resources\\mzml\\tiny.pwiz.idx.mzML";

    System.out.printf("Processing file: %s\n", file);
    final MZMLFile mzml = new MZMLFile(file);
    mzml.setNumThreadsForParsing(null);
    mzml.setTasksPerCpuPerBatch(5);
    mzml.setParsingTimeout(30 * 1000);

    long timeLo = System.nanoTime();
    final MZMLIndex index = mzml.fetchIndex();
    long timeHi = System.nanoTime();
    System.out.printf("Index parsing took %.2fs\n", (timeHi - timeLo) / 1e9);

    timeLo = System.nanoTime();
    IScanCollection scans = new ScanCollectionDefault(true);
    scans.setDataSource(mzml);
    //scans.loadData(LCMSDataSubset.WHOLE_RUN, StorageStrategy.STRONG);
    scans.loadData(LCMSDataSubset.STRUCTURE_ONLY, StorageStrategy.STRONG);
    double sum = 0;
    final Set<Map.Entry<Integer, IScan>> entries = scans.getMapNum2scan().entrySet();

    int counter = 0;
    for (Map.Entry<Integer, IScan> entry : entries) {
      final IScan scan = entry.getValue();
      final ISpectrum spec = scan.fetchSpectrum();
      if (spec.getMZs().length > 0) {
        sum += spec.getMZs()[0];
      }
      if (spec.getIntensities().length > 0) {
        sum += spec.getIntensities()[0];
      }
      counter++;
      if (counter % 100 == 0) {
        System.out.printf("Done processing %d\n", entry.getKey());
      }
    }
    timeHi = System.nanoTime();
    System.out.printf("Sum is %.4f\n", sum);
    System.out.printf("Parsing %s took %.4fs\n", file, (timeHi - timeLo) / 1e9);
  }

  //@Test
  public void mzmlFileOldMainMethod() throws Exception {
    configureJavaUtilLogging();

    String[] filenames = {"E:\\andy\\broken-venky-mzml\\HELA_1µg_onColumn_OT120K.mzML"};
//        String[] filenames = {"C:\\tmp\\_garbage\\HELA_1µg_onColumn_OT120K.mzML_h"};
    Integer numThreads = null;
    Integer numSpectraPerThread = 50;
    List<Path> paths = new ArrayList<>();
    for (int i = 0; i < filenames.length; i++) {
      String filename = filenames[i];
      if (i == 0) {
        try {
          int numThreadsParsed = Integer.parseInt(filename);
          numThreads = numThreadsParsed;
          System.out.printf("Setting number of threads to: %d\n", numThreadsParsed);
          continue;
        } catch (NumberFormatException e) {
          // no worries, it's ok
        }
      }
      if (i == 1) {
        try {
          int numSpectraPerThreadParsed = Integer.parseInt(filename);
          numSpectraPerThread = numSpectraPerThreadParsed;
          System.out
              .printf("Setting number of spectra per thread to: %d\n", numSpectraPerThreadParsed);
          continue;
        } catch (NumberFormatException e) {
          // it's ok
        }
      }

      Path path = Paths.get(filename).toAbsolutePath();
      if (!Files.exists(path)) {
        System.err.println("File does not exist: " + path.toString());
        System.exit(1);
      }
      if (Files.isRegularFile(path)) {
        paths.add(path);
      } else if (Files.isDirectory(path)) {
        FileListing fileListing = new FileListing(path, ".*\\.mzML");
        fileListing.setFollowLinks(false);
        fileListing.setRecursive(false);
        paths.addAll(fileListing.findFiles());
      }
    }

    IScanCollection scans;
    for (Path path : paths) {
      if (Opts.DEBUG || true) {
        double fileSize = path.toFile().length() / (1024 * 1024);
        System.out.printf("File: %s (%.2fMb)\n", path.toString(), fileSize);
      }

      MZMLFile mzml = new MZMLFile(path.toString());
      LCMSRunInfo lcmsRunInfo = mzml.fetchRunInfo();
      System.out.println(lcmsRunInfo.toString());

      mzml.setNumThreadsForParsing(numThreads);
      mzml.setTasksPerCpuPerBatch(numSpectraPerThread);
      mzml.setParsingTimeout(30 * 1000);
      long startTime = System.nanoTime();

      MZMLIndex index = mzml.fetchIndex();
      if (index.size() > 0) {
        MZMLIndexElement byNum = index.getByNum(1);
        MZMLIndexElement byRawNum = index.getByRawNum(byNum.getRawNumber());
        MZMLIndexElement byId = index.getById(byNum.getId());

      } else {
        System.err.println("Parsed index was empty!");
      }
      System.out.println("It took: " + (System.nanoTime() - startTime) / 1e9
          + " seconds to parse index (" + index.size() + " spectra)");

      startTime = System.nanoTime();
      scans = new ScanCollectionDefault(true);
      scans.setDataSource(mzml);
      scans.loadData(LCMSDataSubset.WHOLE_RUN, StorageStrategy.STRONG);
      System.out.println("It took: " + (System.nanoTime() - startTime) / 1e9
          + " seconds to parse all scans (" + scans.getScanCount() + " spectra)");

      TreeMap<Integer, IScan> num2scanMap = scans.getMapNum2scan();
      Set<Map.Entry<Integer, IScan>> num2scanEntries = num2scanMap.entrySet();
      int counterMzIntPairs = 0;
      for (Map.Entry<Integer, IScan> next : num2scanEntries) {
        IScan scan = next.getValue();
        if (scan.getSpectrum() != null) {
          counterMzIntPairs += scan.getSpectrum().getMZs().length;
        }
      }

      IScan scan = scans.getMapNum2scan().firstEntry().getValue();
      ISpectrum spectrum = scan.fetchSpectrum();
      double[] mzs = spectrum.getMZs();
      double[] intensities = spectrum.getIntensities();
      System.out.print("First ten valus of m/z and intensity arrays of the 1st scan:\n\t");
      for (int i = 0; i < mzs.length && i < 10; i++) {
        System.out.printf("%.3f=%.1f; ", mzs[i], intensities[i]);
      }
      System.out.println();

      System.out.printf("Total number of mz-intensity pairs: %d\n", counterMzIntPairs);
      DecimalFormat formatter = new DecimalFormat("0.##E0");
      double doubleArraysSizeMb = ((double) counterMzIntPairs * 2 * 8) / (double) (1024 * 1024);
      System.out.printf("Thas is: %s (%.2fMB)\n", formatter.format(counterMzIntPairs),
          doubleArraysSizeMb);

      TreeMap<Integer, IntervalST<Double, TreeMap<Integer, IScan>>> mapMsLevel2rangeGroups = scans
          .getMapMsLevel2rangeGroups();
      Set<Map.Entry<Integer, IntervalST<Double, TreeMap<Integer, IScan>>>> entries = mapMsLevel2rangeGroups
          .entrySet();
      for (Map.Entry<Integer, IntervalST<Double, TreeMap<Integer, IScan>>> next : entries) {
        Integer msLevel = next.getKey();
        IntervalST<Double, TreeMap<Integer, IScan>> mzRangesAtMsLevel = next.getValue();
        System.out.printf("Tree at MS level: %d\n", msLevel);
        Iterator<IntervalST.Node<Double, TreeMap<Integer, IScan>>> iterIntervalTree = mzRangesAtMsLevel
            .iterator();
        while (iterIntervalTree.hasNext()) {
          IntervalST.Node<Double, TreeMap<Integer, IScan>> range = iterIntervalTree.next();
          System.out.printf("Interval: %s contains %d scans\n", range.getInterval().toString(),
              range.getValue().size());
        }

        System.out.println("=================");
        System.out.println();
      }

      TreeMap<Integer, IScan> mapNum2scan = scans.getMapNum2scan();
      int counter = 0;

      if (false) {
        ArrayList<Integer> scanNums = new ArrayList<>();
        for (Map.Entry<Integer, IScan> num2scan : mapNum2scan.entrySet()) {
          counter++;
          if (counter % 2 == 0) {
            scanNums.add(num2scan.getKey());
          }
        }
        mzml.parse(scanNums);
      }
    }
  }
}
