package umich.ms.fileio.filetypes.mzml;

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
import umich.ms.fileio.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static umich.ms.fileio.filetypes.mzml.MzmlFlux.fluxScans;
import static umich.ms.fileio.filetypes.mzml.MzmlFlux.sleepQuietly;


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
      sleepQuietly(100);
    }
  }

  @Test
  public void fluxTest() throws IOException {

    for (Path path : paths) {
      log.info("Flux using file: {}", path);
      Assume.assumeTrue(Files.exists(path));

      final AtomicLong datapointCount = new AtomicLong();
      MZMLFile mzml = new MZMLFile(path.toString());

      Disposable sub = fluxScans(mzml, false)
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
        sleepQuietly(100);
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

}