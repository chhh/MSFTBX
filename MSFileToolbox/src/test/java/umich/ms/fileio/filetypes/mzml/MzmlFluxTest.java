package umich.ms.fileio.filetypes.mzml;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.AtomicDouble;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.pool2.PoolUtils;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.SoftReferenceObjectPool;
import org.jooq.lambda.Seq;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;
import umich.ms.datatypes.LCMSData;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.ResourceUtils;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.agilent.cef.jaxb.Match;
import umich.ms.util.MathHelper;
import umich.ms.util.Pool;
import umich.ms.util.SpectrumUtils;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class MzmlFluxTest {
  private static final Logger log = LoggerFactory.getLogger(MzmlFluxTest.class);

  private static final String RESOURCE_LOCATION = "mzml";
  private List<Path> paths;

  @Before
  public void setUp() {
    log.debug("Running setup");
    paths = ResourceUtils.getResources(MZMLFileTest.class, RESOURCE_LOCATION);
  }

  @After
  public void tearDown() {
    log.debug("Running cleanup");
    paths.clear();
    paths = null;
  }

  @Test
  public void fluxParallelExceptionText() {
    ParallelFlux<Integer> nums = Flux.range(1, 10)
        .parallel(2, 1)
        .runOn(Schedulers.parallel(), 1)
        .map(i -> {
          if (i == 3) {
            throw new IllegalStateException("whack!");
          }
          return i;
        });

    Disposable sub = nums.sequential(3)
        .subscribeOn(Schedulers.elastic())
        .subscribe(i -> {
          System.out.printf("Got num %d\n", i);
        });

    while (!sub.isDisposed()) {
      //log("Waiting pipeline to complete");
      MzmlFlux.sleepQuietly(100);
    }
  }

  @Test
  public void fluxTest() throws IOException {

    for (Path path : paths) {
      log.info("Flux using file: {}", path);
      Assume.assumeTrue(Files.exists(path));

      final AtomicLong datapointCount = new AtomicLong();
      MZMLFile mzml = new MZMLFile(path.toString());

      Disposable sub = MzmlFlux.fluxScans(mzml, false)
          .doOnError(throwable -> {
            log.error("Error during parsing scans", throwable);
          })
          .doOnSubscribe(subscription -> {
            log.debug("Flux Scans started");
          })
          .doOnComplete(() -> {
            log.debug("Flux Scans complete");
          })
          .subscribe(scan -> {
            // do something with scans here`

            //log.debug("Got scan: {}", scan.toString());
            datapointCount.addAndGet(scan.getSpectrum().getMZs().length);
          });

      while (!sub.isDisposed()) {
        //log("Waiting pipeline to complete");
        MzmlFlux.sleepQuietly(100);
      }

      log.info("All scans have total {} data points in {}\n\n==================\n\n", datapointCount.get(), path);
    }
  }

  @Test
  public void okioBufferTest() {
    byte[] bytes1 = "some-text".getBytes(StandardCharsets.UTF_8);
    byte[] bytes2 = "other-text".getBytes(StandardCharsets.UTF_8);
    okio.Buffer buf1 = new okio.Buffer();
    okio.Buffer buf2 = new okio.Buffer();

    buf1.write(bytes1);
    buf2.write(bytes2);
    buf2.write(buf1, bytes1.length);

    System.out.println("Buf 1: " + buf1.readUtf8());
    System.out.println("Buf 2: " + buf2.readUtf8());


  }


  @Test
  public void fluxTracePeaks() throws IOException, FileParsingException {
    Path dir = Paths.get("D:\\ms-data\\TMTIntegrator_v1.1.4\\TMT-I-Test\\01CPTAC_CCRCC_W_JHU_20171007");
    String fn = "01CPTAC_CCRCC_W_JHU_20171007_LUMOS_f01.mzML";

    Path file = dir.resolve(fn);
    MZMLFile mzml = new MZMLFile(file.toString());

    final int prefetch = 10;
    int maxThreads = Runtime.getRuntime().availableProcessors() - 1;
//    int maxThreads = 4;
    final int threads = Math.max(1, maxThreads);
    Flux<IScan> gen = MzmlFlux.fluxScans(mzml, true);


    final AtomicInteger counter = new AtomicInteger(0);
    final AtomicLong dataPoints = new AtomicLong(0);
    long timeLo = System.nanoTime();

    Disposable sub = gen
        .parallel(threads, prefetch)    // switch to parallel processing after generator
        .runOn(Schedulers.parallel(), prefetch)         // if you don't do this, it won't run in parallel
        .doOnNext(iScan -> {
          log.info("Side effect! Scan #{}", iScan.getNum());
        })
        .filter(iScan -> iScan.getMsLevel() == 1)

        .ordered(Comparator.comparingInt(IScan::getNum), prefetch)
        .buffer(3, 1)

        .parallel(threads, prefetch)
        .runOn(Schedulers.parallel(), prefetch)

        .map(iScans -> {
          String scans = iScans.stream().map(IScan::getNum).map(Object::toString).collect(Collectors.joining(", "));
          Map<Integer, List<IScan>> byMsLevel = Seq.seq(iScans).groupBy(IScan::getMsLevel);
          String counts = Seq.seq(byMsLevel.entrySet())
              .map(kv -> String.format("MS%d: %d scans", kv.getKey(), kv.getValue().size()))
              .toString(", ");
          String res = String.format("Scan #s [%05d]: %s. %s", iScans.get(0).getNum(), scans, counts);
          //log.debug("Produced: {}", res);

          //sleepQuietly(5);

          return res;
        })

        .ordered(String::compareTo, prefetch)

        .subscribeOn(Schedulers.elastic())      // generator will run on this scheduler (once subscribed)
        .subscribe(o -> {
          counter.incrementAndGet();
          log.debug("Got final mapped result: {}", o);
        });

    while (!sub.isDisposed()) {
      //log("Waiting pipeline to complete");
      sleepQuietly(100);
    }
    long timeHi = System.nanoTime();
    log.info("Counter value: {}, elapsed: {}s", counter.get(), (timeHi - timeLo) / 1e9f);
  }

  public static class TracingOpts {
    double mzTolPpm = 30;
    int maxGapLen = 5;
    int minPtsToAdd = 3;
  }

  @Test
  public void fluxTracePeaks2() throws IOException {
    //Path dir = Paths.get("D:\\ms-data\\TMTIntegrator_v1.1.4\\TMT-I-Test\\01CPTAC_CCRCC_W_JHU_20171007");
    Path dir = Paths.get("C:\\ms-data\\mzml");
    String fn = "01CPTAC_CCRCC_W_JHU_20171007_LUMOS_f01.mzML";

    Path file = dir.resolve(fn);
    MZMLFile mzml = new MZMLFile(file.toString());

    log.debug("Working with file: {}", mzml.getPath());
    final Stopwatch sw = Stopwatch.createStarted();
//    LCMSData lcmsData = new LCMSData(mzml);
//    lcmsData.load(LCMSDataSubset.STRUCTURE_ONLY);
//    log.debug("Loading structure took {}s", new DecimalFormat("0.00").format(sw.elapsed(TimeUnit.NANOSECONDS) / 1e9));
//    IScanCollection s = lcmsData.getScans();

    AtomicInteger lastProcessedScanNum = new AtomicInteger(-1);

    sw.stop().reset();
    final int prefetch = 10;
    final int threads = Math.max(1, Runtime.getRuntime().availableProcessors() - 1);

    final AtomicInteger scanCount = new AtomicInteger(0);
    final TracingOpts opts = new TracingOpts();
    ConcurrentSkipListMap<Double, Trace> tracesAll = new ConcurrentSkipListMap<>();
    final ConcurrentLinkedDeque<Trace> tracesNew = new ConcurrentLinkedDeque<>();
    final ConcurrentLinkedDeque<Trace> tracesUpdated = new ConcurrentLinkedDeque<>();
    final ArrayList<Trace> tracesComplete = new ArrayList<>();

    final Pool<Trace> pool = new Pool<>(() -> new Trace(10), Trace::reset);
    Function<Trace, Double> traceKeyExtractor = trace -> trace.mzAvgWeighted;

    Flux<IScan> gen = MzmlFlux.fluxScansSeq(mzml);
    Disposable sub = gen
        .doOnSubscribe(subscription -> sw.start()) // start the timer when the pipeline starts working
        .doOnComplete(() -> {
          log.info("Called doOnComplete()");
          sw.stop();
          pool.purge();
        })
        //.parallel(threads, prefetch)
        .filter(scan -> scan.getMsLevel() == 1)
        .doOnNext(scan -> {
          int i = scanCount.incrementAndGet();
          int prevScanNum = lastProcessedScanNum.getAndSet(scan.getNum());
          if (prevScanNum >= scan.getNum()) {
            log.error("Arriving scan with lower number than previous one");
          }
          if (i % 1000 == 0) {
            log.info("Got to scan i={} #{}@{}min, Active traces: {}, Completed traces: {}",
                i, scan.getNum(), scan.getRt(), tracesAll.size(), tracesComplete.size());
          }
        })
        .doOnNext(scan -> {
          ISpectrum spec = scan.getSpectrum();

          // update existing traces
//          IntStream.range(0, spec.getMZs().length).parallel().forEach(index -> {
          IntStream.range(0, spec.getMZs().length).forEach(index -> {
            double mz = spec.getMZs()[index];
            double ab = spec.getIntensities()[index];
            float rt = scan.getRt().floatValue();
            int scanNum = scan.getNum();
            if (ab <= 0)
              return;
            double mzTol = SpectrumUtils.ppm2amu(mz, opts.mzTolPpm);
            final ConcurrentNavigableMap<Double, Trace> range = tracesAll.subMap(mz - mzTol, true, mz + mzTol, true);

            if (range.isEmpty()) { // create new trace
              Trace t = pool.borrow();
              t.add(mz, (float) ab, scanNum, rt);
              tracesNew.add(t);

            } else if (range.size() == 1) { // update an exising trace
              Trace t = range.firstEntry().getValue();
              t.add(mz, (float) ab, scanNum, rt);

            } else { // if many possible traces match, select one with closest intensity and update
              Trace bestMatch = range.firstEntry().getValue();
              double bestDiff = Math.abs(ab - bestMatch.abs[bestMatch.ptr]);
              for (Map.Entry<Double, Trace> entry : range.entrySet()) {
                Trace t = entry.getValue();
                double diff = Math.abs(ab - t.abs[t.ptr]);
                if (diff < bestDiff) {
                  bestDiff = diff;
                  bestMatch = t;
                }
              }
              bestMatch.add(mz, (float) ab, scanNum, rt);
            }
          });

          // maintenance, done sequentially

          final int curScanNum = scan.getNum();
          // remove/update traces that did not get a match from this spectrum
          Iterator<Map.Entry<Double, Trace>> it = tracesAll.entrySet().iterator();
          while (it.hasNext()) {
            Map.Entry<Double, Trace> next = it.next();
            Trace t = next.getValue();

            if (curScanNum == t.scanNums[t.ptr]) { // was updated on this iteration
              t.zeroAbStretch = 0;
              it.remove();
              tracesUpdated.add(t);

            } else { // was not updated on this iteration
              t.zeroAbStretch += 1;
              if (t.zeroAbStretch > opts.maxGapLen) { // if long gap - complete trace
                it.remove();
                if (t.ptr + 1 >= opts.minPtsToAdd) {
                  tracesComplete.add(t);
                } else { // trace didn't fit criteria, discard
                  //pool.surrender(t);
                }
              }
            }
          }

          // reinsert new and updated traces back into the tree
          for (Trace t : tracesUpdated) {
            tracesAll.put(traceKeyExtractor.apply(t), t);
          }
          tracesUpdated.clear();
          for (Trace t : tracesNew) {
            tracesAll.put(traceKeyExtractor.apply(t), t);
          }
          tracesNew.clear();
        })

        //.ordered((o1, o2) -> Integer.compare(o1.getNum(), o2.getNum()), prefetch)

        .subscribeOn(Schedulers.elastic())      // generator will run on this scheduler (once subscribed)
        .subscribe(o -> {
//          String scans = o.stream().map(IScan::getNum).map(Object::toString).collect(Collectors.joining(", "));
//          Map<Integer, List<IScan>> byMsLevel = Seq.seq(o).groupBy(IScan::getMsLevel);
//          String counts = Seq.seq(byMsLevel.entrySet())
//              .map(kv -> String.format("MS%d: %d scans", kv.getKey(), kv.getValue().size()))
//              .toString(", ");
          //log.debug("Got final mapped result: {}", o);
          //System.out.printf("Got scans: %s\n", scans); // do something with generated and processed item
        });

    while (!sub.isDisposed()) {
      //log("Waiting pipeline to complete");
      sleepQuietly(100);
    }

    // in the end add all currently active traces to Completed list as well
    tracesComplete.addAll(tracesAll.values());
    tracesComplete.sort((o1, o2) -> Integer.compare(o2.ptr, o1.ptr));

    writeTraces(tracesComplete, dir.resolve(fn + ".traces.mzrt.csv"));

    long timeHi = System.nanoTime();
    log.info("Counter value: {}, elapsed: {}s", scanCount.get(), (sw.elapsed(TimeUnit.NANOSECONDS)) / 1e9f);
  }

  public static String colorToHex(Color color) {
    return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
  }

  private void writeTraces(ArrayList<Trace> tracesComplete, Path path) throws IOException {
    log.info("Writing traces to: {}", path);
    CSVPrinter printer = null;
    try {
      printer = CSVFormat.DEFAULT.withHeader(
              "mzLo",
              "mzHi",
              "rtLo",
              "rtHi",
              "color",
              "opacity"
      ).print(path, StandardCharsets.UTF_8);
      for (Trace t : tracesComplete) {
        double mzLo = Double.POSITIVE_INFINITY;
        double mzHi = Double.NEGATIVE_INFINITY;
        for (int i =0; i <= t.ptr; i++) {
          double mz = t.mzs[i];
          if (mz < mzLo) mzLo = mz;
          if (mz > mzHi) mzHi = mz;
        }
        String color = colorToHex(Color.GREEN);
        float opacity = 0.3f;
        DecimalFormat df5 = new DecimalFormat("0.00000");
        DecimalFormat df3 = new DecimalFormat("0.000");
        printer.printRecord(
                df5.format(mzLo),
                df5.format(mzHi),
                df3.format(t.rtLo),
                df3.format(t.rtHi),
                color,
                df3.format(opacity)
        );
      }

      printer.printRecord();
    } finally {
      if (printer != null) {
        printer.close(true);
      }
    }
  }

  static void sleepQuietly(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new IllegalStateException();
    }
  }

}