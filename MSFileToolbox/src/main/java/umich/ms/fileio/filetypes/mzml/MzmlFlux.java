package umich.ms.fileio.filetypes.mzml;

import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;
import umich.ms.datatypes.IScanFlux;
import umich.ms.datatypes.scan.IScan;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.util.OffsetLength;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MzmlFlux implements IScanFlux {
  private static final Logger log = LoggerFactory.getLogger(MzmlFlux.class);
  private final String path;
  private final MZMLFile mzml;

  public MzmlFlux(String path) {
    this.path = path;
    this.mzml = new MZMLFile(path);
  }

  @Override
  public boolean supportsScanFlux() {
    return true;
  }

  @Override
  public Flux<IScan> flux() throws FileParsingException {
    final MZMLRunInfo runInfo = mzml.fetchRunInfo();

    final InputStream is;
    try {
      is = Files.newInputStream(Paths.get(path));
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }

    final int threads = 2;
    final int prefetch = 2;
    AtomicInteger ai = new AtomicInteger();

    Flux<Buffer> gen = Flux.generate(
        () -> is,
        (inputStream, sink) -> {
          Buffer buf = readBuffer();
          if (buf == null) {
            sink.complete();
          } else {
            sink.next(buf);
          }
          return inputStream;
        },
        inputStream -> {
          if (inputStream != null) {
            try {
              inputStream.close();
            } catch (IOException e) {
              throw new IllegalStateException();
            }
          }
        }
    );
    return null;
  }

  Buffer readBuffer() {
    return null;
  }

  static class ReadState {
    private final BufferedSource bs;
    private long read = 0;
    List<OffsetLength> offsets = new ArrayList<>();

    public ReadState(InputStream is) {
      bs = Okio.buffer(Okio.source(is));
    }
  }

  static class BufferInfo {
    final long offset;
    final long length;
    final Buffer buffer;

    public BufferInfo(long offset, long length, Buffer buffer) {
      this.offset = offset;
      this.length = length;
      this.buffer = buffer;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", BufferInfo.class.getSimpleName() + "[", "]")
          .add("offset=" + offset)
          .add("length=" + length)
          .add("buffer=" + buffer)
          .toString();
    }
  }

  static class ScanXml {
    final long offset;
    final String content;

    public ScanXml(long offset, String content) {
      this.offset = offset;
      this.content = content;
    }

    @Override
    public String toString() {
       final int limit = 40;
       final boolean chop = content.length() > limit;
      return new StringJoiner(", ", ScanXml.class.getSimpleName() + "[", "]")
          .add("offset=" + offset)
          .add("content='" + (chop ? content.substring(0, limit) + "..." : content) + "'")
          .toString();
    }
  }

  public static Flux<ScanXml> tokenizeFlux(InputStream is) {
    byte[] bytesLo = "<spectrum ".getBytes(StandardCharsets.UTF_8);
    byte[] bytesHi = "</spectrum>".getBytes(StandardCharsets.UTF_8);
    byte[] bytesHi2 = ">".getBytes(StandardCharsets.UTF_8);
    final Pool<Buffer> pool = new Pool<>(Buffer::new, Buffer::clear);

    final int threads = 4;
    final int prefetch = 4;

    Flux<BufferInfo> gen = Flux.generate(
        () -> new ReadState(is),
        (state, sink) -> {
          try {
            BufferedSource peekSrc = state.bs.peek();
            long lo = find(peekSrc, bytesLo);
            state.read += lo;
            long offset = state.read - bytesLo.length;
            //log.debug("Found start > {} @ {}", lo, offset);

            long hi = find(peekSrc, bytesHi);
            state.read += hi;
            long length = hi + bytesLo.length;
            //log.debug("Found end   > {} @ {}", hi, total);

            state.bs.skip(lo - bytesLo.length);
            Buffer buf = pool.borrow();
            buf.write(state.bs, length);

            final List<OffsetLength> l = state.offsets;
            l.add(new OffsetLength(offset, (int) (length)));
            final int printEvery = 100;
            if (l.size() % printEvery == 0) {
              System.out.printf("Size: %d; %s\n", l.size(), l.subList(l.size() - 2, l.size()));
            }
            sink.next(new BufferInfo(offset, length, buf));
          } catch (EOFException e) {
            log.debug("Flux reader complete");
            sink.complete();
          } catch (IOException e) {
            sink.error(e);
          }

          return state;
        },
        readState -> {
          try {
            readState.bs.close();
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
        });

    ParallelFlux<BufferInfo> pf = gen.parallel(threads, prefetch);
    pf = pf.runOn(Schedulers.parallel(), prefetch);

    ParallelFlux<ScanXml> pf2 = pf.map(bufferInfo -> {
      //log.debug("Converting to string: {}", bufferInfo);
      String content = bufferInfo.buffer.readUtf8();
      ScanXml scanXml = new ScanXml(bufferInfo.offset, content);
      //log.debug("Converted to string: {}", scanXml);
      return scanXml;
    });

    Flux<ScanXml> seq = pf2.sequential(prefetch * 10);
    //Flux<ScanXml> seq = pf2.ordered(Comparator.comparingLong(o -> o.offset), prefetch).subscribeOn(Schedulers.elastic());
    seq = seq.subscribeOn(Schedulers.elastic());

    return seq;
  }

  static void sleepQuietly(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new IllegalStateException();
    }
  }

  public static void tokenize(InputStream is) throws FileParsingException {

    byte[] bytesLo = "<spectrum ".getBytes(StandardCharsets.UTF_8);
    byte[] bytesHi = "</spectrum>".getBytes(StandardCharsets.UTF_8);
    byte[] bytesHi2 = ">".getBytes(StandardCharsets.UTF_8);

    BufferedSource bufSrc = Okio.buffer(Okio.source(is));
    Pool<Buffer> pool = new Pool<>(Buffer::new, Buffer::clear);

    long read = 0;
    List<OffsetLength> list = new ArrayList<>();

    long lo = -1, hi = -1;
    while (true) {
      try {
        BufferedSource peekSrc = bufSrc.peek();
        lo = find(peekSrc, bytesLo);
        read += lo;
        long offset = read - bytesLo.length;

        //log.debug("Found start > {} @ {}", lo, offset);
        hi = find(peekSrc, bytesHi);
        read += hi;
        long length = hi + bytesLo.length;
        //log.debug("Found end   > {} @ {}", hi, total);

        bufSrc.skip(lo - bytesLo.length);
        Buffer bufLocal = pool.borrow();
        bufLocal.write(bufSrc, length);

        String s = bufLocal.readUtf8();
        list.add(new OffsetLength(offset, (int) (length)));
        final int printEvery = 100;
        if (list.size() % printEvery == 0) {
          System.out.printf("Size: %d; %s\n", list.size(), list.subList(list.size() - 2, list.size()));
        }

        int a = 1;
        pool.surrender(bufLocal);

      } catch (EOFException e) {
        // end of file
        if (lo > 0 && hi > 0 && lo > hi) {
          // found the start, but not the end of a scan
          throw new FileParsingException("File ended in the middle of scan that started at offset " + lo);
        }
        break;
      } catch (IOException e) {
        throw new FileParsingException(e);
      }
    }


    log.debug("Done reading");
  }

  private static long find(BufferedSource source, byte[] seq) throws IOException {
    int pos = 0;
    long read = 0;
    while (true) {
      byte b = source.readByte();
      read += 1;
      if (b == seq[pos]) {
        pos += 1;
        if (pos == seq.length) { // found
          return read;
        }
      } else {
        pos = 0;
      }
    }
  }

  private static long find(ByteBuffer source, byte[] seq) {
    int pos = 0;
    long read = 0;
    while (true) {
      byte b = source.get();
      read += 1;
      if (b == seq[pos]) {
        pos += 1;
        if (pos == seq.length) { // found
          return read;
        }
      } else {
        pos = 0;
      }
    }
  }

  public static class Needle {
    public final byte[] bytes;
    public int pos = 0;

    public Needle(byte[] bytes) {
      this.bytes = bytes;
    }

    boolean extendMatch(byte b) {
      if (b == bytes[pos]) {
        pos += 1;
        if (pos == bytes.length) {
          pos = 0;
          return true;
        }
      } else {
        pos = 0;
      }
      return false;
    }
  }

  public static class Pool<T> {
    private final Supplier<T> factory;
    private final Consumer<T> cleanup;
    private final ConcurrentLinkedDeque<T> pool = new ConcurrentLinkedDeque<>();

    public Pool(Supplier<T> factory, Consumer<T> cleanup) {
      this.factory = factory;
      this.cleanup = cleanup != null ? cleanup : (t) -> {
      };
    }

    public T borrow() {
      T t = pool.pollFirst();
      return t != null ? t : factory.get();
    }

    public void surrender(T t) {
      cleanup.accept(t);
      pool.addLast(t);
    }
  }
}
