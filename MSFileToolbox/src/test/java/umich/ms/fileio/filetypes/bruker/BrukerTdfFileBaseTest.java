package umich.ms.fileio.filetypes.bruker;

import com.google.common.base.Stopwatch;
import com.google.common.collect.EvictingQueue;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.filetypes.bruker.BrukerTdfFileBase.MS2_REPORTING;

public class BrukerTdfFileBaseTest {
  private static final Logger log = LoggerFactory.getLogger(BrukerTdfFileBaseTest.class);

//  Path path = Paths.get("C:\\data\\bruker\\200ngHeLaPASEF_2min_compressed.d");
//  Path path = Paths.get("C:\\data\\bruker\\20180819_TIMS2_12-2_AnBr_SA_200ng_HeLa_50cm_120min_100ms_11CT_1_A1_01_2767_lossless_compression.d");
  public static Path path = Paths.get("D:\\ms-data\\bruker\\20180819_TIMS2_12-2_AnBr_SA_200ng_HeLa_50cm_120min_100ms_11CT_3_A1_01_2769.d");

  @BeforeAll
  public static void CheckFileExists() {
    Assumptions.assumeTrue(Files.exists(path));
  }

  private void assumeFileExists(String path) {
    Assumptions.assumeTrue(Files.exists(Paths.get(path)));
  }

  private void assumeFileExists(Path path) {
    Assumptions.assumeTrue(Files.exists(path));
  }

  @Test @Disabled
  public void readBrokenMs1() throws Exception {
    long brokenFrameId = 61896;
    Path p = Paths.get("D:\\ms-data\\bruker\\20180819_TIMS2_12-2_AnBr_SA_200ng_HeLa_50cm_120min_100ms_11CT_3_A1_01_2769.d");
    assumeFileExists(path);
    log.info("Reading file: {}", p);
    try (BrukerTdfFileBase raw = new BrukerTdfFileBase(p.toString(), MS2_REPORTING.EACH_PASEF_WINDOW_AS_SEPARATE_SCAN)) {
      List<FrameInfo> infos = raw.readFrameInfos();
      Map<Long, FrameInfo> mappedInfos = infos.stream()
          .collect(Collectors.toMap(FrameInfo::getFrameId, frameInfo -> frameInfo));

      for (int i = 0; i < 5; i++) {
        long id = brokenFrameId + i;
        int msLevel = mappedInfos.get(id).getMsLevel();
        log.info("Working on frame {}, MS{}", id, msLevel);
        log.info("readRawSpectrum(id) frame: {}", id);
        ISpectrum spec = raw.readRawSpectrum(id);
        log.info("readFrame(id, true) frame: {}", id);
        List<IScan> iScans = raw.readFrame(id, true);
        if (msLevel == 1) {
          log.info("readMs1AsPasef(id) frame: {}", id);
          raw.readMs1AsPasef(id);
        }
      }
    }
  }

  @Test
  public void readRawSpectrum() throws Exception {
    if (!Files.exists(path))
      return;
    try (BrukerTdfFileBase raw = new BrukerTdfFileBase(path.toString(), MS2_REPORTING.EACH_PASEF_WINDOW_AS_SEPARATE_SCAN)) {
      ISpectrum spec = raw.readRawSpectrum(1);
    }
  }

  @Test
  public void readFrameInfos() throws Exception {
    if (!Files.exists(path))
      return;
    try (BrukerTdfFileBase raw = new BrukerTdfFileBase(path.toString(), MS2_REPORTING.EACH_PASEF_WINDOW_AS_SEPARATE_SCAN)) {
      List<FrameInfo> frameInfos = raw.readFrameInfos();
      long countMs1 = frameInfos.stream().filter(fi -> fi.getMsLevel() == 1).count();
      long countMs2 = frameInfos.stream().filter(fi -> fi.getMsLevel() == 2).count();
      log.info("Read FrameInfos for {} frames. MS1 count: {}, MS2 count: {}", frameInfos.size(), countMs1, countMs2);
    }
  }

  @Test
  public void readFrameMs2() throws Exception {
    if (!Files.exists(path))
      return;
    try (BrukerTdfFileBase raw = new BrukerTdfFileBase(path.toString(), MS2_REPORTING.EACH_PASEF_WINDOW_AS_SEPARATE_SCAN)) {
      List<FrameInfo> frameInfos = raw.readFrameInfos();
      FrameInfo firstMs2Frame = frameInfos.stream()
          .filter(fi -> fi.getMsLevel() == 2)
          .findFirst().orElse(null);
      if (firstMs2Frame == null) {
        log.info("No MS2 frames found");
        return;
      }
      List<IScan> scans = raw.readFrame(firstMs2Frame.getFrameId(), true);
      for (IScan scan : scans) {
        log.info("Read MS2 scan for precursor: {}", scan.getPrecursor().getMzTargetMono());
      }
    }
  }

  @Test
  public void readFrameMs1AsMs2() throws Exception {
    if (!Files.exists(path))
      return;
    try (BrukerTdfFileBase raw = new BrukerTdfFileBase(path.toString(), MS2_REPORTING.EACH_PASEF_WINDOW_AS_SEPARATE_SCAN)) {
      List<FrameInfo> frameInfos = raw.readFrameInfos();
      List<FrameInfo> list = frameInfos.stream()
          .filter(fi -> fi.getMsLevel() == 1 && fi.getRtInSeconds() > 500)
          .collect(Collectors.toList());
      if (list.isEmpty()) {
        log.info("No MS1 as MS2 frames found");
        return;
      }
      for (FrameInfo fi : list) {
        List<IScan> scans = raw.readMs1AsPasef(fi.getFrameId());
        if (scans != null && !scans.isEmpty()) {
          log.info("Read MS1 scan #{} as MS2 with {} precursors", fi.getFrameId(), scans.size());
          break;
        }
      }
    }
  }

  @Test
  public void testFengchaoSameMz() throws Exception {
//    final long frameId = 1819;
    final long frameId = 12364;
    if (!Files.exists(path))
      return;
    try (BrukerTdfFileBase raw = new BrukerTdfFileBase(path.toString(), MS2_REPORTING.EACH_PASEF_WINDOW_AS_SEPARATE_SCAN)) {
      List<IScan> iScans = raw.readFrame(frameId, false);
      ISpectrum iSpectrum = raw.readRawSpectrum(frameId);

      int[] dupIdxs = {
      695, // : 94.97309082780168,1.4822934643338095,12.0
      718, // : 94.97309082780168,1.4822934643338095,49.0
      719, // : 94.97309082780168,1.4822934643338095,68.0
      720, // : 94.97309082780168,1.4822934643338095,9.0
      722}; // : 94.97309082780168,1.4822934643338095,9.0

      double[] mz = new double[dupIdxs.length];
      double[] ab = new double[dupIdxs.length];
      double[] im = new double[dupIdxs.length];

      double[] mzs = iSpectrum.getMZs();
      double[] abs = iSpectrum.getIntensities();
      double[] ims = iSpectrum.getIMs();

      for (int i = 0; i < dupIdxs.length; i++) {
        int idx = dupIdxs[i];
        mz[i] = mzs[idx];
        ab[i] = abs[idx];
        im[i] = ims[idx];
      }

      int a = 1;
    }
  }


  @Test
  public void readFramesMs1() throws Exception {
    final boolean readSpectra = true;

    if (!Files.exists(path))
      return;
    try (BrukerTdfFileBase raw = new BrukerTdfFileBase(path.toString(), MS2_REPORTING.EACH_PASEF_WINDOW_AS_SEPARATE_SCAN)) {
      List<FrameInfo> frameInfos = raw.readFrameInfos();
      List<FrameInfo> ms1 = frameInfos.stream()
          .filter(fi -> fi.getMsLevel() == 1).collect(Collectors.toList());
      if (ms1.isEmpty()) {
        log.info("No MS1 frames found");
        return;
      }
      Stopwatch swFrame = Stopwatch.createUnstarted();
      Stopwatch swTicker = Stopwatch.createStarted();
      Stopwatch swFile = Stopwatch.createStarted();
      Duration elapsedReadingFrames = Duration.ofNanos(0);
      final int statLen = 10;
      EvictingQueue<Duration> times = EvictingQueue.create(statLen);
      EvictingQueue<Integer> lengths = EvictingQueue.create(statLen);

      int read = 0;
      for (FrameInfo fi : ms1) {
        swFrame.reset().start();
        List<IScan> scans = raw.readFrame(fi.getFrameId(), readSpectra);
        read++;
        if (scans.size() != 1) {
          throw new IllegalStateException(String.format("Reading MS1 frame (id: %d) resulted in "
              + "more than 1 IScan.", fi.getFrameId()));
        }
        swFrame.stop();
        times.add(swFrame.elapsed());
        elapsedReadingFrames = elapsedReadingFrames.plus(swFrame.elapsed());

        IScan scan = scans.get(0);
        //log.info("FrameId #{}, Scan #{}, Rt {}", fi.getFrameId(), scan.getNum(), scan.getRt());
        ISpectrum spec = scan.getSpectrum();
        int length = spec == null ? 0 : spec.getMZs().length;
        lengths.add(length);

        if (swTicker.elapsed().toMillis() > 500) {
          LongSummaryStatistics statTimes = times.stream()
              .mapToLong(Duration::toMillis).summaryStatistics();
          IntSummaryStatistics statLengths = lengths.stream().mapToInt(v -> v)
              .summaryStatistics();
          String elapsedTotal = DurationFormatUtils.formatDuration(swFile.elapsed().toMillis(), "HH:mm:ss");
          String elapsedReading = DurationFormatUtils.formatDuration(elapsedReadingFrames.toMillis(), "HH:mm:ss");
          double speedTotal = 1e3d * read / (double) swFile.elapsed().toMillis();
          double speedCurrent = 1e3d * statTimes.getCount() / (double)statTimes.getSum();
          int leftToReadCount = ms1.size() - read;
          Duration leftToReadEta = Duration.ofSeconds((long) (leftToReadCount / speedCurrent));
          String eta = DurationFormatUtils.formatDuration(leftToReadEta.toMillis(), "HH:mm:ss");

          String msg = String
              .format("Reading MS1 [%d/%d][%.2f F/s total, %.2f F/s last %d] [Avg time per frame: %.1fms] "
                      + "[Spec length avg: %.0f] [Elapsed - total: %s, read: %s] [ETA: %s]",
                  read, ms1.size(),
                  speedTotal, speedCurrent, statTimes.getCount(),
                  statTimes.getAverage(), statLengths.getAverage(),
                  elapsedTotal, elapsedReading, eta);
          System.out.println(msg);
          swTicker.reset().start();
        }

        long limitSecs = 2;
        if (swFile.elapsed().getSeconds() > limitSecs) {
          log.info("Stopping test, ran OK for {} secs", limitSecs);
          return;
        }
      }

      String elapsedTotalHMS = DurationFormatUtils.formatDuration(swFile.elapsed().toMillis(), "HH:mm:ss");
      String msg = String.format("Reading file done in %s.", elapsedTotalHMS);
      System.out.println(msg);

      swTicker.reset();
      swFrame.reset();
      swFile.reset();
    }
  }

  @Test
  public void readFramesMs2() throws Exception {
    final boolean readSpectra = true;

    if (!Files.exists(path))
      return;
    try (BrukerTdfFileBase raw = new BrukerTdfFileBase(path.toString(), MS2_REPORTING.EACH_PASEF_WINDOW_AS_SEPARATE_SCAN)) {
      List<FrameInfo> frameInfos = raw.readFrameInfos();
      List<FrameInfo> ms2 = frameInfos.stream()
          .filter(fi -> fi.getMsLevel() == 2).collect(Collectors.toList());
      if (ms2.isEmpty()) {
        log.info("No MS2 frames found");
        return;
      }
      Stopwatch swFrame = Stopwatch.createUnstarted();
      Stopwatch swTicker = Stopwatch.createStarted();
      Stopwatch swFile = Stopwatch.createStarted();
      Duration elapsedReadingFrames = Duration.ofNanos(0);
      final int statLen = 10;
      EvictingQueue<Duration> times = EvictingQueue.create(statLen);
      EvictingQueue<Integer> lengths = EvictingQueue.create(statLen);
      EvictingQueue<Integer> countPrecursors = EvictingQueue.create(statLen);

      int read = 0;
      for (FrameInfo fi : ms2) {
        swFrame.reset().start();
        List<IScan> scans = raw.readFrame(fi.getFrameId(), readSpectra);
//        ISpectrum spec2d = raw.readRawSpectrum(fi.getFrameId());
//        lengths.add(spec2d.getMZs().length);
        read++;
        swFrame.stop();
        times.add(swFrame.elapsed());
        elapsedReadingFrames = elapsedReadingFrames.plus(swFrame.elapsed());

//        IScan scan = scans.get(0);
//        log.info("FrameId #{}, MS2 sub-scans {}, Rt {}", fi.getFrameId(), scans.size(), scan.getRt());
//        ISpectrum spec = scan.getSpectrum();
        int length = scans.stream()
            .mapToInt(s -> s.getSpectrum() == null ? 0 : s.getSpectrum().getMZs().length).sum();
        lengths.add(length);
        countPrecursors.add(scans.size());

        if (swTicker.elapsed().toMillis() > 500) {
          LongSummaryStatistics statTimes = times.stream()
              .mapToLong(Duration::toMillis).summaryStatistics();
          IntSummaryStatistics statLengths = lengths.stream().mapToInt(v -> v)
              .summaryStatistics();
          IntSummaryStatistics statPrecs = countPrecursors.stream().mapToInt(v -> v)
              .summaryStatistics();
          String elapsedTotal = DurationFormatUtils.formatDuration(swFile.elapsed().toMillis(), "HH:mm:ss");
          String elapsedReading = DurationFormatUtils.formatDuration(elapsedReadingFrames.toMillis(), "HH:mm:ss");
          double speedTotal = 1e3d * read / (double) swFile.elapsed().toMillis();
          double speedCurrent = 1e3d * statTimes.getCount() / (double)statTimes.getSum();
          int leftToReadCount = ms2.size() - read;
          Duration leftToReadEta = Duration.ofSeconds((long) (leftToReadCount / speedCurrent));
          String eta = DurationFormatUtils.formatDuration(leftToReadEta.toMillis(), "HH:mm:ss");

          String msg = String
              .format("Reading MS1 [%d/%d][%.2f F/s total, %.2f F/s last %d] [Avg time per frame: %.1fms] "
                      + "[Spec length avg: %.0f] [Elapsed - total: %s, read: %s] [ETA: %s]",
                  read, ms2.size(),
                  speedTotal, speedCurrent, statTimes.getCount(),
                  statTimes.getAverage(), statLengths.getAverage(),
                  elapsedTotal, elapsedReading, eta);
          System.out.println(msg);
          swTicker.reset().start();
        }

        long limitSecs = 2;
        if (swFile.elapsed().getSeconds() > limitSecs) {
          log.info("Stopping test, ran OK for {} secs", limitSecs);
          return;
        }
      }

      String elapsedTotalHMS = DurationFormatUtils.formatDuration(swFile.elapsed().toMillis(), "HH:mm:ss");
      String msg = String.format("Reading file done in %s.", elapsedTotalHMS);
      System.out.println(msg);

      swTicker.reset();
      swFrame.reset();
      swFile.reset();
    }
  }

  @Test
  public void readFramesMs1AsMs2() throws Exception {
    final boolean readSpectra = true;

    if (!Files.exists(path))
      return;
    try (BrukerTdfFileBase raw = new BrukerTdfFileBase(path.toString(), MS2_REPORTING.EACH_PASEF_WINDOW_AS_SEPARATE_SCAN)) {
      List<FrameInfo> frameInfos = raw.readFrameInfos();
      List<FrameInfo> ms1 = frameInfos.stream()
          .filter(fi -> fi.getMsLevel() == 1).collect(Collectors.toList());
      if (ms1.isEmpty()) {
        log.info("No MS1 as MS2 frames found");
        return;
      }
      Stopwatch swFrame = Stopwatch.createUnstarted();
      Stopwatch swTicker = Stopwatch.createStarted();
      Stopwatch swFile = Stopwatch.createStarted();
      Duration elapsedReadingFrames = Duration.ofNanos(0);
      final int statLen = 10;
      EvictingQueue<Duration> times = EvictingQueue.create(statLen);
      EvictingQueue<Integer> lengths = EvictingQueue.create(statLen);
      EvictingQueue<Integer> countPrecursors = EvictingQueue.create(statLen);

      int read = 0;
      for (FrameInfo fi : ms1) {
        swFrame.reset().start();
        List<IScan> scans = raw.readMs1AsPasef(fi.getFrameId());
//        ISpectrum spec2d = raw.readRawSpectrum(fi.getFrameId());
//        lengths.add(spec2d.getMZs().length);
        read++;
        swFrame.stop();
        times.add(swFrame.elapsed());
        elapsedReadingFrames = elapsedReadingFrames.plus(swFrame.elapsed());

//        IScan scan = scans.get(0);
//        log.info("FrameId #{}, MS2 sub-scans {}, Rt {}", fi.getFrameId(), scans.size(), scan.getRt());
//        ISpectrum spec = scan.getSpectrum();
        int length = scans.stream()
            .mapToInt(s -> s.getSpectrum() == null ? 0 : s.getSpectrum().getMZs().length).sum();
        lengths.add(length);
        countPrecursors.add(scans.size());

        if (swTicker.elapsed().toMillis() > 500) {
          LongSummaryStatistics statTimes = times.stream()
              .mapToLong(Duration::toMillis).summaryStatistics();
          IntSummaryStatistics statLengths = lengths.stream().mapToInt(v -> v)
              .summaryStatistics();
          IntSummaryStatistics statPrecs = countPrecursors.stream().mapToInt(v -> v)
              .summaryStatistics();
          String elapsedTotal = DurationFormatUtils.formatDuration(swFile.elapsed().toMillis(), "HH:mm:ss");
          String elapsedReading = DurationFormatUtils.formatDuration(elapsedReadingFrames.toMillis(), "HH:mm:ss");
          double speedTotal = 1e3d * read / (double) swFile.elapsed().toMillis();
          double speedCurrent = 1e3d * statTimes.getCount() / (double)statTimes.getSum();
          int leftToReadCount = ms1.size() - read;
          Duration leftToReadEta = Duration.ofSeconds((long) (leftToReadCount / speedCurrent));
          String eta = DurationFormatUtils.formatDuration(leftToReadEta.toMillis(), "HH:mm:ss");

          String msg = String
              .format("Reading MS1 [%d/%d][%.2f F/s total, %.2f F/s last %d] [Avg time per frame: %.1fms] "
                      + "[Spec length avg: %.0f] [Elapsed - total: %s, read: %s] [ETA: %s]",
                  read, ms1.size(),
                  speedTotal, speedCurrent, statTimes.getCount(),
                  statTimes.getAverage(), statLengths.getAverage(),
                  elapsedTotal, elapsedReading, eta);
          System.out.println(msg);
          swTicker.reset().start();
        }

        long limitSecs = 2;
        if (swFile.elapsed().getSeconds() > limitSecs) {
          log.info("Stopping test, ran OK for {} secs", limitSecs);
          return;
        }
      }

      String elapsedTotalHMS = DurationFormatUtils.formatDuration(swFile.elapsed().toMillis(), "HH:mm:ss");
      String msg = String.format("Reading file done in %s.", elapsedTotalHMS);
      System.out.println(msg);

      swTicker.reset();
      swFrame.reset();
      swFile.reset();
    }
  }

  public void exampleReadMs1ScansOneByOne() throws Exception {
    if (!Files.exists(path))
      return;
    try (BrukerTdfFileBase raw = new BrukerTdfFileBase(path.toString(), MS2_REPORTING.EACH_PASEF_WINDOW_AS_SEPARATE_SCAN)) {
      List<FrameInfo> frameInfos = raw.readFrameInfos();

      List<FrameInfo> ms1Frames = frameInfos.stream()
          .filter(frameInfo -> frameInfo.getMsLevel() == 1)
          .collect(Collectors.toList());

      for (FrameInfo ms1Frame : ms1Frames) {
        // Just read the 2D spectrum
        ISpectrum spec = raw.readRawSpectrum(ms1Frame.getFrameId());
        // Or read the whole scan with meta-info
        List<IScan> scans = raw.readFrame(ms1Frame.getFrameId(), true);
        // Note that it returns a list. For MS1 scans the list is always with one scan.
        // But for MS2 PASEF scans, when you create the `BrukerTdfFileBase` object with
        // the `MS2_REPORTING.EACH_PASEF_WINDOW_AS_SEPARATE_SCAN` option, multiple MS2 scans
        // will be returned. None of the PASEF MS2 scans will have IM information.
      }
    }
  }

  @org.junit.jupiter.api.Test @Order(1)
  void convertImScanNumTo1k0() throws BrukerException {
    double[] scanNums = {1, 2, 3};
    //double[] oneOverk0 = new double[scanNums.length];
    try (BrukerTdfFile f = new BrukerTdfFile(BrukerTdfFileBaseTest.path.toString())) {
      double[] oneOverk0 = f.convertImScanNumTo1k0(1, scanNums);
      double[] scanNumsAgain = f.convertIm1k0ToScanNum(1, oneOverk0);
      Assertions.assertArrayEquals(scanNums, scanNumsAgain, 1e-8);
    }
  }

}
