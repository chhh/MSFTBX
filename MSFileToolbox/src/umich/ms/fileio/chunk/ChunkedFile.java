package umich.ms.fileio.chunk;

import com.google.common.util.concurrent.*;
import org.apache.commons.pool2.impl.SoftReferenceObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.util.ByteArrayHolder;
import umich.ms.util.ByteArrayHolderFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dmitry Avtonomov
 */
public class ChunkedFile implements FileChunkSource {
    private Path path;
    /** in bytes, 8MB default. */
    private int chunkSize;
    private static final int CHUNK_SIZE_DEFAULT = 1024 * 1024 * 8;
    /** In bytes. */
    private int chunkOverlap;
    private static final int CHUNK_OVERLAP_DEFAULT = 512;

    private FileChunk[] chunks;

    private SoftReferenceObjectPool<ByteArrayHolder> pool;
    private ByteArrayHolderFactory factory;

    /** How many chunks should be available pre-cached. */
    private int chunkBufferSize = 1;
    /**
     * If the number of available chunks in cache drops below {@code chunkBufferSize * chunkBufferLoadFactor},
     * additional reading should be scheduled.
     */
    private double chunkBufferLoadFactor = 0.5;
    private ConcurrentSkipListMap<Integer, FileChunk> chunksInUse = null;
    private ConcurrentSkipListMap<Integer, FileChunk> chunksPreRead = null;
    private ConcurrentSkipListMap<Integer, FileChunk> chunksScheduled = null;
    private AtomicInteger nextChunkNum = new AtomicInteger(-1);
    ListeningExecutorService execIo = null;
    ExecutorService execFinalize = null;
    private volatile RandomAccessFile raf = null;

    private static final Logger log = LoggerFactory.getLogger(ChunkedFile.class);


    public ChunkedFile(Path path) {
        this(path, CHUNK_SIZE_DEFAULT, CHUNK_OVERLAP_DEFAULT);
    }

    public ChunkedFile(Path path, int chunkSize, int chunkOverlap) {
        if (chunkOverlap > 0.5 * chunkSize)
            throw new IllegalArgumentException(String.format(
                    "Chunk overlap is not allowed to be more than 0.5 of chunk size. " +
                            "You tried to set overlap %d when chunk size was %d", chunkOverlap, chunkSize));
        this.path = path;
        this.chunkSize = chunkSize;
        this.chunkOverlap = chunkOverlap;
        factory = new ByteArrayHolderFactory();
        factory.setDefaultSize(chunkSize);
        pool = new SoftReferenceObjectPool<>(factory);
    }

    /**
     * Check if the file is still valid.
     */
    public void init() throws IOException {
        if (!Files.exists(path))
            throw new FileNotFoundException("Could not find a file under path: " + path.toAbsolutePath().toString());
        if (Files.size(path) == 0) {
            throw new IllegalStateException("File size can't be zero for chunked files");
        }
        chunks = chunkFile();
        chunksInUse = new ConcurrentSkipListMap<>();
        chunksPreRead = new ConcurrentSkipListMap<>();
        chunksScheduled = new ConcurrentSkipListMap<>();
        nextChunkNum = new AtomicInteger(-1);
        if (raf != null)
            raf.close();
        execIo = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
        execFinalize = Executors.newSingleThreadExecutor();
    }

    public int getChunkBufferSize() {
        return chunkBufferSize;
    }

    public void setChunkBufferSize(int chunkBufferSize) {
        this.chunkBufferSize = chunkBufferSize;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    private void setChunkSize(int chunkSize, boolean resetFactorySettings) {
        this.chunkSize = chunkSize;
        if (resetFactorySettings) {
            factory.setDefaultSize(chunkSize);
        }
    }

    public int getChunkOverlap() {
        return chunkOverlap;
    }

    public ByteArrayHolderFactory getFactory() {
        return factory;
    }

    public FileChunk[] getChunks() {
        return chunks;
    }

    public SoftReferenceObjectPool<ByteArrayHolder> getPool() {
        return pool;
    }

    private FileChunk[] chunkFile() {
        final int readLen = chunkSize;

        final long fileLen = path.toFile().length();

        if (fileLen <= readLen) {
            // if we only have enough bytes for one worker - so be it
            return new FileChunk[]{new FileChunk(0, 0L, (int)fileLen)};
        }

        /**
         *         |----------|
         * |----------|    |----------| N segments of total length S
         *          ^           ^
         *          O(overlap)   X(length of one segment)
         */
        long numChunksL = (long)Math.ceil((double)(fileLen - chunkOverlap) / (double)(chunkSize - chunkOverlap));
        if (numChunksL > Integer.MAX_VALUE)
            throw new IllegalStateException("Num chunks can't be more than Integer.MAX_VALUE, file too large or chunk size too small");
        int numChunks = (int)numChunksL;
        FileChunk[] fileChunks = new FileChunk[numChunks];
        long curOffset = 0;
        int curLen;
        FileChunk fileChunk;
        long lenToEOF;
        for (int i = 0; i < numChunks; i++) {
            lenToEOF = fileLen - curOffset;
            curLen =  lenToEOF < chunkSize ? (int)(lenToEOF) : chunkSize;
            fileChunk = new FileChunk(i, curOffset, curLen);
            fileChunks[i] = fileChunk;
            curOffset = curOffset + curLen - chunkOverlap;
        }
        return fileChunks;
    }

    @Override
    public FileChunk next() {
        final int nextNum = nextChunkNum.incrementAndGet();
        log.debug("Got next() request, next num '{}', running on thread {}", nextNum, Thread.currentThread().getName());
        if (nextNum > chunks.length - 1) {
            synchronized (this) {
                if (raf != null)
                    try {
                        raf.close();
                    } catch (IOException e) {
                        log.error("Something awful, could not close RandomAccessFile", e);
                    }
                execIo.shutdown();
                execFinalize.shutdown();
                int timeout = 5;
                TimeUnit timeUnit = TimeUnit.SECONDS;
                try {
                    execIo.awaitTermination(timeout, timeUnit);
                    execFinalize.awaitTermination(timeout, timeUnit);
                } catch (InterruptedException e) {
                    log.error("Could not stop executors withing {} {}", timeout, timeUnit.toString());
                }
                return null;
            }
        }

        // do we have that scan read?
        FileChunk fileChunk = chunksPreRead.get(nextNum);
        if (fileChunk == null) {
            // it has not yet been read, check if it is scheduled for reading
            fileChunk = chunksScheduled.get(nextNum);
            if (fileChunk == null) {
                synchronized (this) {
                    // chunk was neither read nor scheduled
                    // check again, it might have been scheduled by some other thread
                    fileChunk = chunksPreRead.get(nextNum);
                    if (fileChunk == null) {
                        fileChunk = chunksScheduled.get(nextNum);
                        if (fileChunk == null) {
                            // it has definitely not been read or scheduled yet, so we should do it
                            schedule(nextNum);
                        }
                    }
                    try {
                        while ((fileChunk = chunksPreRead.get(nextNum)) == null) {
                            log.debug("Thread '{}' is waiting to be woken up to try and get its target chunk #{}", Thread.currentThread().getName(), nextNum);
                            wait();
                            log.debug("Thread '{}' is woke up, trying to get its target chunk #{}", Thread.currentThread().getName(), nextNum);
                        }
                    } catch (InterruptedException e) {
                        log.warn("A thread scheduled a chunk of file to be read, but was interrupted while waiting on the monitor", e);
                        e.printStackTrace();
                    }
                }
            }
        }
        if (fileChunk == null) {
            log.error("FileChunk was null while chunk number less than total number of chunks, should not happen");
        }
        return fileChunk;
    }

    protected synchronized void schedule(final int chunkNum) {
        chunksScheduled.putIfAbsent(chunkNum, chunks[chunkNum]);

        int chunksAvailable = chunksScheduled.size() + chunksPreRead.size();
        int bufferLoLimit = (int) Math.ceil(chunkBufferSize * chunkBufferLoadFactor);
        if (chunksAvailable < bufferLoLimit) {
            int scheduledChunkNum = chunkNum;
            for (int i = 0; i < chunkBufferSize - chunksAvailable; i++) {
                scheduledChunkNum++;
                if (scheduledChunkNum >= chunks.length)
                    break;
                chunksScheduled.putIfAbsent(scheduledChunkNum, chunks[scheduledChunkNum]);
            }
        }

        ListenableFuture<?> future = execIo.submit(new Runnable() {
            @Override
            public void run() {
                Map.Entry<Integer, FileChunk> entry;
                while ((entry = chunksScheduled.pollFirstEntry()) != null) {
                    Integer num = entry.getKey();
                    FileChunk chunk = entry.getValue();
                    ByteArrayHolder bah = null;
                    try {
                        bah = pool.borrowObject();
                    } catch (Exception e) {
                        log.error("Something awful happened when borrowing ByteArrayHolder from pool", e);
                        throw new IllegalStateException(e);
                    }

                    try {
                        if (raf == null)
                            raf = new RandomAccessFile(path.toFile(), "r"); // this code is only executed on a single thread, so it's ok
                        bah.ensureCapacity(chunk.getLength());
                        log.debug("Seeking to position in file for read @{} : {}", chunk.getOffset(), chunk.getLength());
                        raf.seek(chunk.getOffset());
                        raf.readFully(bah.getUnderlyingBytes(), 0, chunk.getLength());
                        bah.setPosition(chunk.getLength());
                        chunk.setBah(bah, pool);
                        chunksPreRead.put(num, chunk);
                    } catch (IOException e) {
                        log.error("Something awful happened when reading file", e);
                        throw new IllegalStateException(e);
                    }
                }
            }
        });
        Futures.addCallback(future, new FutureCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                synchronized (ChunkedFile.this) {
                    ChunkedFile.this.notifyAll();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                synchronized (ChunkedFile.this) {
                    log.error("Everything has blown up!", t);
                    execFinalize.shutdown();
                    execIo.shutdown();

                    int timeout = 1;
                    TimeUnit timeUnit = TimeUnit.SECONDS;
                    try {
                        execIo.awaitTermination(timeout, timeUnit);
                        execFinalize.awaitTermination(timeout, timeUnit);
                    } catch (InterruptedException e) {
                        log.error("Could not stop executors withing {} {}", timeout, timeUnit.toString());
                    }
                }
            }
        }, execFinalize);

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        umich.ms.logging.LogHelper.configureJavaUtilLogging();

        String path = "E:\\andy\\q01507.mzML_h";
        Path p = Paths.get(path);
        final ChunkedFile chunkedFile = new ChunkedFile(p, 1024, 128);
        chunkedFile.init();
        chunkedFile.setChunkBufferSize(3);
        FileChunk nextChunk;

        int numThreads = 3;
        ExecutorService exec = Executors.newFixedThreadPool(numThreads);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                FileChunk nextChunk;
                while ((nextChunk = chunkedFile.next()) != null) {
                    log.debug("Thread '{}' received chunk #{}", Thread.currentThread().getName(), nextChunk.getChunkNum());
                    String s = new String(nextChunk.getBah().getUnderlyingBytes());
                    //System.out.printf("Woohoo thread '%s' got text:\n\t%s..(%d chars)\n", Thread.currentThread().getName(), s.subSequence(0, 20), s.length());
                    int a = 1;
                }
                log.debug("Thread '{}' received null for next chunk, terminating", Thread.currentThread().getName());
            }
        };
        for (int i = 0; i < numThreads; i++) {
            exec.submit(runnable);
        }

        exec.shutdown();
        exec.awaitTermination(5, TimeUnit.SECONDS);

        log.debug("Main thread finished");
    }
}
