package umich.ms.fileio.filetypes.mzml;

import org.junit.Assume;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static umich.ms.fileio.filetypes.mzml.MzmlFlux.fluxScans;
import static umich.ms.fileio.filetypes.mzml.MzmlFlux.sleepQuietly;


public class MzmlFluxTest {
  private static final Logger log = LoggerFactory.getLogger(MzmlFluxTest.class);

  @org.junit.Test
  public void fluxParallelExceptionText() {
    ParallelFlux<Integer> nums = Flux.range(1, 10)
        .parallel(2, 1)
        .runOn(Schedulers.parallel(), 1)
        .map(i -> {
          if (i == 3)
            throw new IllegalStateException("whack!");
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

  @org.junit.Test
  public void tokenizeTest() throws IOException {

    Path file = Paths.get("D:\\ms-data\\TMTIntegrator_v1.1.4\\TMT-I-Test\\03CPTAC_CCRCC_W_JHU_20171022\\03CPTAC_CCRCC_W_JHU_20171022_LUMOS_f03.mzML");
    log.debug("Using file: {}", file);
    System.out.println("File: " + file);
    Assume.assumeTrue(Files.exists(file));

    MZMLFile mzml = new MZMLFile(file.toString());
    Disposable sub = fluxScans(mzml)
        .doOnError(throwable -> {
          log.error("error in processing", throwable);
        })
        .sequential()
        .subscribe(scans -> {
          //log.debug("Got scan: {}", scans.toString());
        });

    while (!sub.isDisposed()) {
      //log("Waiting pipeline to complete");
      sleepQuietly(100);
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