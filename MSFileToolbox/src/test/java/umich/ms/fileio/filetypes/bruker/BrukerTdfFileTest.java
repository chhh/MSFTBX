package umich.ms.fileio.filetypes.bruker;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.props.PrecursorInfo;

import java.util.List;

class BrukerTdfFileTest {
  private static final Logger log = LoggerFactory.getLogger(BrukerTdfFileTest.class);

  @BeforeAll
  public static void checkTestFileExists() {
    Assumptions.assumeTrue(Files.exists(BrukerTdfFileBaseTest.path));
  }

  @Test @Order(1)
  void convertImScanNumTo1k0() throws BrukerException {
    final double[] scanNums = {0, 1, 2, 3};
    try (BrukerTdfFile f = new BrukerTdfFile(BrukerTdfFileBaseTest.path.toString())) {
      final double[] scanNumsAs1k0 = f.convertImScanNumTo1k0(1, scanNums);
      final double[] scanNumsAgain = f.convertIm1k0ToScanNum(1, scanNumsAs1k0);
      Assertions.assertArrayEquals(scanNums, scanNumsAgain, 1e-8);

      Peaklist2D raw = f.readFrameRaw(1);
      double[] scanNumsFromIm = f.convertIm1k0ToScanNum(1, raw.ims);
      int a = 1;
    }
  }

  @Test
  void readDiaPasef() throws BrukerException {
    final String path = "D:\\ms-data\\bruker\\20190507_TIMS1_FlMe_SA_HeLa_diaPASEF_py3_1_A1_1_119.d";
    assumeFileExists(path);
    try (BrukerTdfFile f = new BrukerTdfFile(path)) {
      List<FrameInfo> frameInfos = f.readFrameInfos();
      log.info("File has {} frames, max RT {}sec", frameInfos.size(), frameInfos.stream().map(FrameInfo::getRtInSeconds).max(Double::compare));
      Peaklist2D peaklist2D = f.readFrameRaw(2);
      int a = 1;
    }
  }

  @Test
  void readDiaPasefWithIsolationInfo() throws Exception {
    final String path = "D:\\ms-data\\bruker\\20190507_TIMS1_FlMe_SA_HeLa_diaPASEF_py3_1_A1_1_119.d";
    assumeFileExists(path);
    try (BrukerTdfFileBase f = new BrukerTdfFileBase(path, BrukerTdfFileBase.MS2_REPORTING.FULL_2D_SPECTRUM_AS_ONE_SCAN)) {
      List<FrameInfo> frameInfos = f.readFrameInfos();
      log.info("File has {} frames, max RT {}sec", frameInfos.size(), frameInfos.stream().map(FrameInfo::getRtInSeconds).max(Double::compare));
      List<IScan> iScans = f.readFrame(2, true);
      int a = 1;
    }
  }

  private void assumeFileExists(String path) {
    Assumptions.assumeTrue(Files.exists(Paths.get(path)));
  }

  private void assumeFileExists(Path path) {
    Assumptions.assumeTrue(Files.exists(path));
  }

  @Test
  void r1eadDiaPasefWithIsolationInfo() throws Exception {
    final String path = "D:\\ms-data\\bruker\\20190507_TIMS1_FlMe_SA_HeLa_diaPASEF_py3_1_A1_1_119.d";
    assumeFileExists(path);
    try (BrukerTdfFileBase f = new BrukerTdfFileBase(path, BrukerTdfFileBase.MS2_REPORTING.FULL_2D_SPECTRUM_AS_ONE_SCAN)) {
      List<FrameInfo> frameInfos = f.readFrameInfos();
      List<IScan> iScans = f.readFrame(2, true);
      PrecursorInfo pi = iScans.get(0).getPrecursor();
      int a = 1;
    }
  }
}
