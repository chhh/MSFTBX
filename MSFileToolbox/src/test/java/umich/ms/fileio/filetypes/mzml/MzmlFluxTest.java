package umich.ms.fileio.filetypes.mzml;

import org.junit.Assume;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import umich.ms.fileio.exceptions.FileParsingException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;

import static umich.ms.fileio.filetypes.mzml.MzmlFlux.sleepQuietly;


public class MzmlFluxTest {
  private static final Logger log = LoggerFactory.getLogger(MzmlFluxTest.class);

  @org.junit.Test
  public void tokenizeTest() throws IOException, FileParsingException {

    Path file = Paths.get("D:\\ms-data\\TMTIntegrator_v1.1.4\\TMT-I-Test\\03CPTAC_CCRCC_W_JHU_20171022\\03CPTAC_CCRCC_W_JHU_20171022_LUMOS_f03.mzML");
    log.debug("Using file: {}", file);
    System.out.println("File: " + file);
    Assume.assumeTrue(Files.exists(file));


    try (InputStream is = Files.newInputStream(file)) {
      Flux<MzmlFlux.ScanXml> scanXmlFlux = MzmlFlux.tokenizeFlux(is);

      long timeLo = System.nanoTime();
      final AtomicLong timeHi = new AtomicLong(-1);
      final AtomicLong off = new AtomicLong();
      Disposable sub = scanXmlFlux.subscribe(
          transofrmed -> {
            if (true || off.incrementAndGet() % 100 == 0) {
              log.debug("Past through: {}", transofrmed);
            }
          },
          err -> err.printStackTrace(),
          () -> {
            timeHi.set(System.nanoTime());
            log.debug("Pipeline complete");
          });

      while (!sub.isDisposed()) {
        //log("Waiting pipeline to complete");
        sleepQuietly(100);
      }

      log.debug("Pipeline was running for [{}ms]", (timeHi.get() - timeLo)/1e6);

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