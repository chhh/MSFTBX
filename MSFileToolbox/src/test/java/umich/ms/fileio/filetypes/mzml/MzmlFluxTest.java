package umich.ms.fileio.filetypes.mzml;

import org.jooq.lambda.Seq;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;
import umich.ms.datatypes.scan.IScan;
import umich.ms.fileio.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


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
  public void fluxTracePeaks() throws IOException {
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

    Disposable sub = gen.parallel(threads, prefetch)    // switch to parallel processing after generator
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
          String res = String.format("Scan #s [%05d]: %s. %s",iScans.get(0).getNum(), scans, counts);
          //log.debug("Produced: {}", res);

          //sleepQuietly(5);

          return res;
        })

        .ordered(String::compareTo, prefetch)

        .subscribeOn(Schedulers.elastic())      // generator will run on this scheduler (once subscribed)
        .subscribe(o -> {
//          String scans = o.stream().map(IScan::getNum).map(Object::toString).collect(Collectors.joining(", "));
//          Map<Integer, List<IScan>> byMsLevel = Seq.seq(o).groupBy(IScan::getMsLevel);
//          String counts = Seq.seq(byMsLevel.entrySet())
//              .map(kv -> String.format("MS%d: %d scans", kv.getKey(), kv.getValue().size()))
//              .toString(", ");
          counter.incrementAndGet();
          log.debug("Got final mapped result: {}", o);
          //System.out.printf("Got scans: %s\n", scans); // do something with generated and processed item
        });

    while (!sub.isDisposed()) {
      //log("Waiting pipeline to complete");
      sleepQuietly(100);
    }
    long timeHi = System.nanoTime();
    log.info("Counter value: {}, elapsed: {}s", counter.get(), (timeHi - timeLo)/1e9f);
  }

  static void sleepQuietly(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new IllegalStateException();
    }
  }

}