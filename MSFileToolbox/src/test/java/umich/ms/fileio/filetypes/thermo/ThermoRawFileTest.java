package umich.ms.fileio.filetypes.thermo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.datatypes.LCMSData;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.fileio.exceptions.FileParsingException;

public class ThermoRawFileTest {
  private static final Logger log = LoggerFactory.getLogger(ThermoRawFileTest.class);
//  private String path = "C:\\data\\thermo\\orbi-fusion-etd\\PXD011125\\20170628_Fusion1_SA_MB_PJ_RSext_WCE_WT1.raw";
//  private static String path = "C:\\data\\human-rajamaran-pxd008984\\LNZ308_CT-VT-RT_class1_#1.raw";
//  private static String path = "D:\\ms-data\\thermo\\PXD015650\\M205_3_0718_wa2sNrp1_1.raw";
  private static String path = "D:\\ms-data\\thermo\\eclipse-tmt-sps3_phil_wilmarth\\HOWA-846_SPS-MS3_Eclipse.raw";

  @BeforeAll
  public static void beforeClass() throws Exception {
    Assumptions.assumeTrue(Files.exists(Paths.get(path)), "Test Thermo RAW file must exist: " + path); // LOAD THE VALUE FROM THE PROPERTIES FILE AS NEEDED...
  }

  @Test
  public void parseAllMs2() throws Exception {
    // the Thermo file is AutoClosable, so use try-with-resources to make sure that the
    // rpc server is killed when you're done reading

    //System.setProperty("libs.thermo.dir", "C:\\Users\\chhh\\lib\\msfragger\\MSFragger-3.0\\ext\\thermo");

    try (ThermoRawFile rawFile = new ThermoRawFile(path)) {
//      log.info("Calling rawFile.parse(Arrays.asList(0, 100, 200));");
//      List<IScan> parse1 = rawFile.parse(Arrays.asList(0, 100, 200));
//    List<IScan> parse = rawFile.parse(LCMSDataSubset.MS2_WITH_SPECTRA);
//      log.info("Sleeping 10 sec");
//      Thread.sleep(10000);
//      log.info("Continuing after sleep");
      LCMSData lcmsData = new LCMSData(rawFile);

//      LCMSDataSubset subset = new LCMSDataSubset(1, 10, null, null);
//      LCMSDataSubset subset = LCMSDataSubset.STRUCTURE_ONLY;
//      LCMSDataSubset subset = LCMSDataSubset.WHOLE_RUN;
      rawFile.setTasksPerCpuPerBatch(10);
      log.info("Parsing structure");
      StopWatch sw = StopWatch.createStarted();
      lcmsData.load(LCMSDataSubset.STRUCTURE_ONLY);
      sw.stop();
      log.info("Read structure, {} scans ({}})", lcmsData.getScans().getScanCount(), sw.toString());
      int a = 1;
    }
  }

  @Test public void testMultiplePrecursors() throws FileParsingException {
    try (ThermoRawFile raw = new ThermoRawFile(path)) {
      LCMSData data = new LCMSData(raw);
      data.load(new LCMSDataSubset(1, 30, null, null));
      IScanCollection scans = data.getScans();
      log.info("loaded {} scans", scans.getScanCount());
    }
  }

  @Test
  void testRetries() {
    Assertions.assertThrows(BatmassServerInitError.class, this::throwAfterRetries);
  }

  public void throwAfterRetries() {
    int attempts = 0;
    while (true) {
      try {
        throwBatmassServerInitError();
      } catch (BatmassServerInitError e) {
        if (e.attemptRetry && attempts < 4) {
          continue;
        }
        log.info("Throwing BatmassServerInitError after making {} attempts", attempts);
        throw e;
      } finally {
        attempts += 1;
      }
    }
  }

  private static void throwBatmassServerInitError() {
    throw new BatmassServerInitError("fake error", true);
  }
}
