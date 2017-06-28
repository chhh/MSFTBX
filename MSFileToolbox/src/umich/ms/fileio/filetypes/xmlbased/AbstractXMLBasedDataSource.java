/*
 * Copyright 2016 Dmitry Avtonomov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package umich.ms.fileio.filetypes.xmlbased;

import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javolution.xml.internal.stream.XMLStreamReaderImpl;
import javolution.xml.stream.XMLStreamException;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.SoftReferenceObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.index.Index;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.AbstractLCMSDataSource;
import umich.ms.fileio.filetypes.util.MultiSpectraParser;
import umich.ms.util.ByteArrayHolder;
import umich.ms.util.PooledByteArrayHolders;

/**
 * @param <I> the type of index
 * @param <E> The type of elements in the index
 *
 * @author Dmitry Avtonomov
 */
public abstract class AbstractXMLBasedDataSource<E extends XMLBasedIndexElement, I extends Index<E>> extends AbstractLCMSDataSource<I> {
    /** 1024 bytes - the minimum size, that an index builder should assign to workers. */
    private final int INDEX_BUILDER_MIN_READ_SIZE = 1 << 10;
//    private final int INDEX_BUILDER_MIN_READ_SIZE = 1000;
    /** 512 byte overlaps between readers. */
    private final int INDEX_BUILDER_MIN_OVERLAP = 1 << 9;
//    private final int INDEX_BUILDER_MIN_OVERLAP = 500;
    /** 8MB read buffer. */
    private final int INDEX_BUILDER_READ_BUF_SIZE = 1 << 23;
//    private final int INDEX_BUILDER_READ_BUF_SIZE = 1000000;
    protected transient ObjectPool<XMLStreamReaderImpl> readerPool = instantiateReaderPool();
    // TODO: WARNING: ACHTUNG: remove this field, was here for testing
    public volatile transient long time_reading = 0;

    Logger log = LoggerFactory.getLogger(AbstractXMLBasedDataSource.class);

    public AbstractXMLBasedDataSource(String path) {
        super(path);
    }

    @Override
    public String getName() {
        return getPath();
    }

    @Override
    public void releaseMemory() {
        runInfo = null;
        readerPool.close();
        readerPool = instantiateReaderPool();
        releaseResources();
        System.gc(); // it's up to JVM to decide if the memory should be actually freed
    }

    /**
     * This method will be called by {@link #releaseMemory()} in addition to cleaning up resources,
     * used by this abstract implementation.
     * Clean up here anything additional, that your subclass has created, such as a file-type-specific index.
     */
    protected abstract void releaseResources();

    protected ObjectPool<XMLStreamReaderImpl> instantiateReaderPool() {
        return new SoftReferenceObjectPool<>(new XMLStreamReaderFactory());
    }




    //    /**
    //     * <b>You probably don't need that method</b>, it parses the spectra indexes (where in the file each
    //     * spectrumRef description starts and ends).<br/>
    //     * It will be called automatically for you if you call parsing methods that don't take an index as input.<br/>
    //     * The index will not be stored in this MZXMLFile instance, if you want caching to work, just call {@link #fetchIndex()}.
    //     * @return TreeMap, keys: spectrumRef number, vals: byte offset from the beginning of the file where this spectrumRef
    //     * starts. The last element of this map holds the end position of the last spectrumRef.
    //     * @throws umich.ms.fileio.exceptions.FileParsingException
    //     */
    //    public TreeMap<Integer, OffsetLength> parseIndex() throws FileParsingException {
    //        MZXMLIndexParser parser = new MZXMLIndexParser(this);
    //        TreeMap<Integer, OffsetLength> idx = parser.parse();
    //        return idx;
    //    }
    @Override
    public List<IScan> parse(LCMSDataSubset subset) throws FileParsingException {
        I idx = fetchIndex(); // make sure, that the index is parsed
        if (idx.getMapByNum().isEmpty()) {
            // if the index was empty - there's nothing to parse
            return Collections.emptyList();
        }
        LCMSRunInfo inf = fetchRunInfo(); // make sure we have the runInfo
        // figure out which scans are to be read
        NavigableMap<Integer, E> idxMap = idx.getMapByNum();
        Integer scanNumLo = subset.getScanNumLo() == null ? idxMap.firstKey() : idxMap.ceilingKey(subset.getScanNumLo());;
        Integer scanNumHi = subset.getScanNumHi() == null ? idxMap.lastKey() : idxMap.floorKey(subset.getScanNumHi());
        NavigableMap<Integer, E> subIdx = idxMap.subMap(scanNumLo, true, scanNumHi, true);
        if (subIdx.isEmpty()) {
            throw new FileParsingException("The run does not contain any spectra in the number range you provided!");
        }
        List<IScan> scans = new ArrayList<>(subIdx.size());
        int numThreads = getNumThreadsForParsing();
        int numSpectraPerThread = getTasksPerCpuPerBatch();
        ExecutorService exec = Executors.newFixedThreadPool(numThreads);

        Set<? extends Map.Entry<Integer, ? extends XMLBasedIndexElement>> entrySet = subIdx.entrySet();
        Iterator<? extends Map.Entry<Integer, ? extends XMLBasedIndexElement>> idxEntriesIter = entrySet.iterator();

        // set up read buffers
        int readLen = 1 << 18; // 256k default read buffer size
        byte[] readBuf1 = new byte[readLen];
        byte[] readBuf2 = new byte[readLen];
        byte[] readBufTmp;

        try {
            RandomAccessFile raf = this.getRandomAccessFile();
            ArrayList<OffsetLength> readTasks = null;
            do {
                // This is needed for cancellable tasks
                if (Thread.interrupted()) {
                    log.debug("Main AbstractXMLBasedDataSource read thread was interrupted, parsing cancelled.");
                    throw new FileParsingException("Thread interrupted, parsing was cancelled.");
                }
                // check if we have read something in the previous iteration, if we did, then use the 2nd read buffer
                if (readTasks != null && !readTasks.isEmpty()) {
                    // if we did read something on the previous iteration before submitting parsing tasks, use it
                    // just flip buffers
                    readBufTmp = readBuf1;
                    readBuf1 = readBuf2;
                    readBuf2 = readBufTmp;
                } else {
                    // figure out which spectra to read in this batch and read them
                    int numScansToRead = numThreads * numSpectraPerThread;
                    readTasks = new ArrayList<>(numScansToRead);
                    readBuf1 = readContinuousBatchOfSpectra(idxEntriesIter, raf, readBuf1, readTasks, numScansToRead);
                }
                // distribute the spectra between workers
                int[] workerScanCounts = distributeParseLoad(numThreads, readTasks);
                // submit the tasks to executor service
                ArrayList<Future<List<IScan>>> parseTasks = submitParseTasks(subset, runInfo, numThreads, exec, readBuf1, readTasks, workerScanCounts, true);
                // before blocking on waiting for the parsing tasks to complete,
                // initiate another read
                // figure out which spectra to read in this batch and read them
                int maxScansToReadInBatch = numThreads * numSpectraPerThread;
                readTasks = new ArrayList<>(maxScansToReadInBatch);
                if (idxEntriesIter.hasNext()) {
                    readBuf2 = readContinuousBatchOfSpectra(idxEntriesIter, raf, readBuf2, readTasks, maxScansToReadInBatch);
                }
                // block and wait for all the parsers to finish, at this point
                // we already have the next chunk read-in while the parsers were busy
                for (Future<List<IScan>> parseTask : parseTasks) {
                    try {
                        List<IScan> parsedScans = parseTask.get(getParsingTimeout(), TimeUnit.SECONDS);
                        if (parsedScans != null) {
                            for (IScan scan : parsedScans) {
                                scans.add(scan);
                            }
                        }
                    } catch (InterruptedException | TimeoutException | ExecutionException | NullPointerException e) {
                        throw new FileParsingException(e);
                    }
                }
            } while (idxEntriesIter.hasNext() || !readTasks.isEmpty());
            this.close();
        } catch (IOException ex) {
            throw new FileParsingException(ex);
        } finally {
            this.close();
            exec.shutdown();
        }
        // wait for the executor pool to shut down
        try {
            exec.awaitTermination(getParsingTimeout(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new FileParsingException(String.format("Executor pool failed to shut down in %d sec!", getParsingTimeout()), e);
        }
        return scans;
    }

    protected int[] distributeParseLoad(int numWorkers, ArrayList<OffsetLength> readTasks) {
        int[] workerScanCounts = new int[numWorkers];
        Arrays.fill(workerScanCounts, readTasks.size() / numWorkers);
        int leftoverScans = readTasks.size() % numWorkers;
        for (int i = 0; i < leftoverScans; i++) {
            workerScanCounts[i]++;
        }
        return workerScanCounts;
    }

    /**
     * The index as it is built by index builder contains offsets of "scan" tags, and their lengths. This leaves a gap
     * between spectra (there might be spaces, newlines and tabs), so if you read a file like that with MzML parser,
     * it will do lot's of small reads, because it will have to skip those gaps. It's faster to read the file including
     * those gaps, so we'll be fixing the initially-built "proper" index, to one, that has lengths of spectra spanning
     * from the beginning of one spectrum tag, to the beginning of the next one.
     * @param idx
     */
    protected I fixIndex(I idx) {
        if (idx.size() < 2) {
            return idx;
        }
        NavigableMap<Integer, E> map = idx.getMapByNum();
        Set<? extends Map.Entry<Integer, E>> entries = map.entrySet();
        Iterator<? extends Map.Entry<Integer, E>> it = entries.iterator();

        // we have at least 2 elements in the iterator
        Map.Entry<Integer, E> curr, next;
        OffsetLength currOfflen, nextOfflen;
        curr = it.next();
        while (it.hasNext()) {
            next = it.next();
            currOfflen = curr.getValue().getOffsetLength();
            nextOfflen = next.getValue().getOffsetLength();
            curr.getValue().setOffsetLength(new OffsetLength(currOfflen.offset, (int)(nextOfflen.offset - currOfflen.offset)));
            curr = next;
        }
        return idx;
    }

    protected ArrayList<Future<List<IScan>>> submitParseTasks(LCMSDataSubset subset, LCMSRunInfo info,
            int numWorkers, ExecutorService exec, byte[] readBuf1, ArrayList<OffsetLength> readTasks,
            int[] workerScanCounts, boolean areScansContinuous) {
        ArrayList<Future<List<IScan>>> parseTasks = new ArrayList<>(numWorkers);
        int numSpectraAssignedForParsing = 0;
        long baseOffset = readTasks.get(0).offset; // this is the offset of the smallest scan num we've read
        int offsetInReadBufForCurWorker = 0;
        for (int thisWorkerScanCount : workerScanCounts) {
            if (thisWorkerScanCount == 0) {
                // it means this worker has nothing to parse, so will all other workers, no need to continue
                break;
            }
            // calculate the positions in the read byte[] which should be submitted for this worker
            int readTaskLoNum = numSpectraAssignedForParsing;
            int readTaskHiNum = numSpectraAssignedForParsing + thisWorkerScanCount - 1;
            ByteArrayInputStream bais;
            if (areScansContinuous) {
                OffsetLength readTaskLo = readTasks.get(readTaskLoNum);
                OffsetLength readTaskHi = readTasks.get(readTaskHiNum);
                int offsetInReadBuf = (int) (readTaskLo.offset - baseOffset);
                int lengthOfRead = (int) (readTaskHi.offset - readTaskLo.offset + readTaskHi.length);
                bais = new ByteArrayInputStream(readBuf1, offsetInReadBuf, lengthOfRead);
            } else {
                // if scans were not continuous, we need to iterate over scan index elements, adding up their lengths
                int fullReadTasksLenForThisWorker = 0;
                for (int i = numSpectraAssignedForParsing; i <= readTaskHiNum; i++) {
                    fullReadTasksLenForThisWorker += readTasks.get(i).length;
                }
                bais = new ByteArrayInputStream(readBuf1, offsetInReadBufForCurWorker, fullReadTasksLenForThisWorker);
                offsetInReadBufForCurWorker += fullReadTasksLenForThisWorker;
            }
            // submit the job for this worker
            Future<List<IScan>> task;
            MultiSpectraParser parser = getSpectraParser(bais, subset, readerPool, thisWorkerScanCount);
            task = exec.submit(parser);
            parseTasks.add(task);
            numSpectraAssignedForParsing += thisWorkerScanCount;
        }
        return parseTasks;
    }

    /**
     * File type specific parser.
     * @param inputStream a stream, from which the parser will parse the actual scan. If this stream is some sort of a
     *                    FileStream, make sure you use buffering, because reading from the stream in the parser is
     *                    normally unbuffered.
     * @param subset the only purpose of this subset object is to identify scans, for which spectra should be parsed
     * @param readerPool can be null, then a new reader will be created every time. It is highly recommended to provide
     *                   a valid pool
     * @param numSpectra can be null. If you know how many spectra can be parsed from the {@code inputStream}, then
     *                   pass it here. If set, and this number of spectra was not reached when parsing, an exception will
     *                   be thrown.
     * @return a parser, which can handle a block of text, containing, possibly, multiple spectra
     */
    public abstract MultiSpectraParser getSpectraParser(InputStream inputStream,
            LCMSDataSubset subset, ObjectPool<XMLStreamReaderImpl> readerPool, Integer numSpectra);

    protected byte[] readContinuousBatchOfSpectra(Iterator<? extends Map.Entry<Integer, ? extends XMLBasedIndexElement>> entries,
            RandomAccessFile file, byte[] readBuf, ArrayList<OffsetLength> readTasks, int maxScansToReadInBatch) throws IOException {
        while (entries.hasNext() && readTasks.size() < maxScansToReadInBatch) {
            readTasks.add(entries.next().getValue().getOffsetLength());
        }
        OffsetLength readFirst = readTasks.get(0);
        OffsetLength readLast = readTasks.get(readTasks.size() - 1);

        long readOffset = readFirst.offset;
        // TODO: WARNING ACHTUNG - possible overflow here if single spectra are very large, this has finally bitten me in the ass
        int readLength = (int) (readLast.offset - readOffset + readLast.length);
        // check if the buffer size is enough and expand accordingly, or clean the old buffer
        if (readBuf.length < readLength) {
            readBuf = new byte[readLength];
        } else {
            Arrays.fill(readBuf, (byte) 0);
        }
        long time_start = System.nanoTime();
        // read everything into the buffer
        file.seek(readOffset);
        file.readFully(readBuf, 0, readLength);
        time_reading = time_reading + (System.nanoTime() - time_start);
        return readBuf;
    }

    /**
     * Reads up to (@code maxScansToRead) scans from the iterator, checking for continuity of scans in the
     * original file, so it could do sequential reads.
     * @param scanNumsIter iterator over scan numbers to be read
     * @param maxScansToReadInBatch max number of scans to read from the provided iterator
     * @param readTasks a list, where scan index elements, that were read in this call, will be placed
     * @param index the index of XML files
     * @param file random acccess file to read from
     * @param readBuf the buffer to read to, might be changed and grown, new instance will be returned by this method
     * @return the read buffer, might not be the same array instance, the buffer could have been grown to
     * accommodate all spectra from the iterator.
     * @throws IOException
     */
    protected byte[] readListOfSpectra(ListIterator<Integer> scanNumsIter, int maxScansToReadInBatch,
            ArrayList<OffsetLength> readTasks, NavigableMap<Integer, ? extends XMLBasedIndexElement> index,
            RandomAccessFile file, byte[] readBuf) throws IOException {
        if (!scanNumsIter.hasNext()) {
            throw new IllegalArgumentException("Scan number iterator had no active elements");
        }
        Arrays.fill(readBuf, (byte) 2);
        Integer scanNumCur;
        Integer scanNumPrev = null;
        Integer scanNumLower;
        int batchStart = 0;
        int batchLen = 0;
        int readBufPos = 0;
        // process the first scan
        scanNumCur = scanNumsIter.next();
        OffsetLength offsetLength = index.get(scanNumCur).getOffsetLength();
        if (offsetLength == null) {
            throw new IllegalArgumentException("The scan number requested for reading was not in the index");
        }
        readTasks.add(offsetLength);
        batchLen++;
        if (!scanNumsIter.hasNext() || maxScansToReadInBatch == 1) {
            long readOffset = readTasks.get(batchStart).offset;
            int readLength = (int) (offsetLength.offset - readOffset + offsetLength.length);
            // check if the buffer size is enough and expand accordingly, or clean the old buffer
            int spaceLeft = readBuf.length - readBufPos;
            if (spaceLeft < readLength) {
                readBuf = Arrays.copyOf(readBuf, readBuf.length + readLength); // grow more than needed
            }
            file.seek(readOffset);
            file.readFully(readBuf, readBufPos, readLength);
            return readBuf;
        }
        while (scanNumsIter.hasNext() && readTasks.size() < maxScansToReadInBatch) {
            scanNumCur = scanNumsIter.next();
            offsetLength = index.get(scanNumCur).getOffsetLength();
            if (offsetLength == null) {
                throw new IllegalArgumentException("The scan number requested for reading was not in the index");
            }
            scanNumLower = index.lowerKey(scanNumCur);
            // check continuity of requested spectra in the file
            if (scanNumPrev != scanNumLower) {
                // discontinuity or no more scans
                // flush the current batch
                long readOffset = readTasks.get(batchStart).offset;
                OffsetLength lastReadTask = readTasks.get(batchStart + batchLen - 1);
                int readLength = (int) (lastReadTask.offset - readOffset + lastReadTask.length);
                // check if the buffer size is enough and expand accordingly, or clean the old buffer
                int spaceLeft = readBuf.length - readBufPos;
                if (spaceLeft < readLength) {
                    readBuf = Arrays.copyOf(readBuf, readBuf.length + readLength); // grow more than needed
                }
                file.seek(readOffset);
                file.readFully(readBuf, readBufPos, readLength);
                readBufPos += readLength;
                batchLen = 0;
                batchStart = readTasks.size();
            }
            readTasks.add(offsetLength);
            batchLen++;
            scanNumPrev = scanNumCur;
        }
        if (batchLen > 0) {
            // if we have something left to process, flush the last batch
            long readOffset = readTasks.get(batchStart).offset;
            OffsetLength lastReadTask = readTasks.get(batchStart + batchLen - 1);
            int readLength = (int) (lastReadTask.offset - readOffset + lastReadTask.length);
            // check if the buffer size is enough and expand accordingly, or clean the old buffer
            int spaceLeft = readBuf.length - readBufPos;
            if (spaceLeft < readLength) {
                readBuf = Arrays.copyOf(readBuf, readBuf.length + readLength); // grow more than needed
            }
            file.seek(readOffset);
            file.readFully(readBuf, readBufPos, readLength);
        }
        return readBuf;
    }

    //    /**
    //     * @deprecated This was not such a good idea, the speed is largely the same or worse as compared to
    //     * reading the whole file multithreaded in sequential manner, rather than trying to use random access to
    //     * separate scan headers. For SSDs this won't matter as well, as the read speeds are so great. The only difference
    //     * is in memory usage. This parsing of the structure doesn't use any memory, while parsing multithreaded for some
    //     * reason eats 200-300Mb.
    //     *
    //     * Parses the complete structure of the file - all scan meta-info is parsed and finalized (links between
    //     * parent and child scans are established).
    //     * @return
    //     */
    //    @Deprecated
    //    public IScanCollection parseStructure() throws FileParsingException {
    //        BasicSetup setup = new BasicSetup().invoke();
    //        TreeMap<Integer, ScanIndexElement> idx = setup.getIndex();
    //        ScanFactory scanFactory = setup.getScanFactory();
    //        IScanCollection scns = setup.getScans();
    //        LCMSRunInfo info = setup.getRunInfo();
    //
    //        InputStream inputStream = null;
    //        try {
    //            SeekableByteChannel chan = Files.newByteChannel(Paths.get(getPath()), StandardOpenOption.READ);
    //            // 1024 bytes should be enough for most scan headers to be read in one buffered read
    ////            RandomInputStream ris = new RandomInputStream(new umich.ms.external.ucar.BufferedRandomAccessFile(getPath(), "r", 1024));
    ////            inputStream = (InputStream) ris;
    //
    //            RandomAccessFile raf = this.getRandomAccessFile();
    //            int bufSize = 128;
    //            byte[] readBuf = new byte[bufSize];
    //            boolean isRetry = false;
    //
    //            Set<Map.Entry<Integer, ScanIndexElement>> idxEntries = idx.entrySet();
    //            long offset;
    //            boolean goToNextScan = true;
    //            int repeatCount = 0;
    //            int maxRepeats = 5;
    //            Map.Entry<Integer, ScanIndexElement> idxElem = null;
    //            for (Iterator<Map.Entry<Integer, ScanIndexElement>> iterator = idxEntries.iterator(); iterator.hasNext(); ) {
    //                if (goToNextScan) {
    //                    idxElem = iterator.next();
    //                }
    //                offset = idxElem.getValue().offset;
    ////                raf.seek(offset);
    ////                raf.readFully(readBuf);
    ////                inputStream = new ByteArrayInputStream(readBuf);
    //
    //                chan.position(offset);
    //                inputStream = Channels.newInputStream(chan);
    //
    //                Set<Integer> msLevelsToParseSpectra = Collections.emptySet();
    //                MultiSpectraParser parser = getSpectraParser(inputStream, scanFactory, info, msLevelsToParseSpectra, this.readerPool, 1);
    //                try {
    //                    List<IScan> scan = parser.call();
    //                    if (scan != null && !scan.isEmpty()) {
    //                        scns.addScan(scan.get(0));
    //                        goToNextScan = true;
    //                        repeatCount = 0;
    //                    } else {
    //                        if (repeatCount >= maxRepeats) {
    //                            throw new FileParsingException("When parsing structure parser returned a null or empty list of parsed scans.");
    //                        }
    //                        bufSize = bufSize * 2;
    //                        readBuf = new byte[bufSize];
    //                        goToNextScan = false;
    //                        repeatCount++;
    //                    }
    //                } catch (Exception e) {
    //                    if (repeatCount >= maxRepeats) {
    //                        throw new FileParsingException("Error in parser, when parsing structure", e);
    //                    }
    //                    bufSize = bufSize * 2;
    //                    readBuf = new byte[bufSize];
    //                    goToNextScan = false;
    //                    repeatCount++;
    //                }
    //
    //            }
    //
    //
    ////            for (Map.Entry<Integer, ScanIndexElement> idxEntry : idxEntries) {
    ////                offset = idxEntry.getValue().offset;
    ////                raf.seek(offset);
    ////                raf.readFully(readBuf);
    ////                inputStream = new ByteArrayInputStream(readBuf);
    ////
    ////                // seek to the desired position
    //////                chan.position(offset);
    //////                inputStream = Channels.newInputStream(chan);
    ////
    //////                ris.seek(offset);
    ////
    ////                MultiSpectraParser parser = getSpectraParser(inputStream, scanFactory, 1, info, Collections.EMPTY_SET, this.readerPool);
    ////                try {
    ////                    List<IScan> scan = parser.call();
    ////                    if (scan != null && !scan.isEmpty()) {
    ////                        scans.addScan(scan.get(0));
    ////                    } else {
    ////                        readBuf =
    ////                        throw new FileParsingException("When parsing structure parser returned a null or empty list of parsed scans.");
    ////                    }
    ////                } catch (Exception e) {
    ////                    throw new FileParsingException("Error in parser, when parsing structure", e);
    ////                }
    ////            }
    //        } catch (Exception e) {
    //            throw new FileParsingException(e);
    //        } finally {
    //            if (inputStream != null) {
    //                try {
    //                    inputStream.close();
    //                } catch (IOException e) {
    //                    throw new FileParsingException("Could not close RandomInputStream", e);
    //                }
    //            }
    //            this.close();
    //        }
    //        ScanCollectionHelper.finalizeScanCollection(scns);
    //        ScanCollectionHelper.finalizePrecursorWindows(scns);
    //        return scns;
    //    }
    /**
     * @deprecated this method has not been updated to the new parsing version
     * @param scanNums List of scan numbers to be parsed. All scan numbers MUST be present in the file.
     * @return
     * @throws FileParsingException
     */
    @Override
    @Deprecated
    public List<IScan> parse(List<Integer> scanNums) throws FileParsingException {
        // the basic idea here is that you read in a single thread into a large byte array
        // and then submit what's been read to the parsers
        if (scanNums.isEmpty()) {
            throw new IllegalArgumentException("The scan list you provided contained no valid scan numbers.");
        }
        I idx = fetchIndex(); // make sure, that the index is parsed
        LCMSRunInfo inf = fetchRunInfo(); // make sure we have the runInfo
        for (Integer scanNum : scanNums) {
            if (idx.getByNum(scanNum) == null) {
                throw new IllegalArgumentException(String.format("One of the scan numbers you requested didn't exist in the Index (scan #%d)", scanNum));
            }
        }
        List<IScan> scans = new ArrayList<>(scanNums.size());
        int numWorkers = getNumThreadsForParsing();
        int numSpectraPerWorker = getTasksPerCpuPerBatch();
        ExecutorService exec = Executors.newFixedThreadPool(numWorkers);
        ListIterator<Integer> scanNumsIter = scanNums.listIterator();
        LCMSDataSubset subset = LCMSDataSubset.WHOLE_RUN;
        // this is the main read buffer
        byte[] readBuf1 = new byte[1 << 18]; // 256k default read buffer size
        // this is the second read buffer, used for reads, while threads are parsing the previous batch
        byte[] readBuf2 = new byte[1 << 18]; // 256k default read buffer size
        try {
            RandomAccessFile raf = this.getRandomAccessFile();
            ArrayList<OffsetLength> readTasks = null;
            do {
                // This is needed for cancellable tasks
                if (Thread.interrupted()) {
                    throw new FileParsingException("Thread interrupted, parsing was cancelled.");
                }
                // check if we have read something in the previous iteration, if we did, then use the 2nd read buffer
                if (readTasks != null && !readTasks.isEmpty()) {
                    // if we did read something on the previous iteration before submitting parsing tasks, use it
                    // just flip buffers
                    byte[] readBufTmp = readBuf1;
                    readBuf1 = readBuf2;
                    readBuf2 = readBufTmp;
                } else {
                    // figure out which spectra to read in this batch and read them
                    int maxScansToReadInBatch = numWorkers * numSpectraPerWorker;
                    readTasks = new ArrayList<>(maxScansToReadInBatch);
                    readBuf1 = readListOfSpectra(scanNumsIter, maxScansToReadInBatch, readTasks, idx.getMapByNum(), raf, readBuf1);
                }
                // distribute the spectra between workers
                int[] workerScanCounts = distributeParseLoad(numWorkers, readTasks);
                // submit the tasks to executor service
                ArrayList<Future<List<IScan>>> parseTasks = submitParseTasks(subset, runInfo, numWorkers, exec, readBuf1, readTasks, workerScanCounts, false);
                // before blocking on waiting for the parsing tasks to complete,
                // initiate another read
                // figure out which spectra to read in this batch and read them
                int maxScansToReadInBatch = numWorkers * numSpectraPerWorker;
                readTasks = new ArrayList<>(maxScansToReadInBatch);
                if (scanNumsIter.hasNext()) {
                    readBuf2 = readListOfSpectra(scanNumsIter, maxScansToReadInBatch, readTasks, idx.getMapByNum(), raf, readBuf2);
                }
                // block and wait for all the parsers to finish, at this point
                // we already have the next chunk read-in while the parsers were busy
                for (Future<List<IScan>> parseTask : parseTasks) {
                    try {
                        List<IScan> parsedScans = parseTask.get(getParsingTimeout(), TimeUnit.SECONDS);
                        if (parsedScans != null) {
                            for (IScan scan : parsedScans) {
                                scans.add(scan);
                            }
                        }
                    } catch (InterruptedException | TimeoutException | ExecutionException | NullPointerException e) {
                        throw new FileParsingException(e);
                    }
                }
            } while (scanNumsIter.hasNext() || !readTasks.isEmpty());
            this.close();
        } catch (IOException ex) {
            throw new FileParsingException(ex);
        } finally {
            this.close();
            exec.shutdown();
        }
        // wait for the executor pool to shut down
        try {
            exec.awaitTermination(getParsingTimeout(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new FileParsingException(String.format("Executor pool failed to shut down in %d sec!", getParsingTimeout()), e);
        }
        return scans;
    }

    @Override
    public IScan parseScan(int num, boolean parseSpectrum) throws FileParsingException {
        // prepare for parsing
        NavigableMap<Integer, ? extends XMLBasedIndexElement> idx = fetchIndex().getMapByNum();
        LCMSRunInfo info = fetchRunInfo();
        XMLBasedIndexElement indexElement = idx.get(num);
        if (indexElement == null)
            throw new FileParsingException(String.format("No such scan number found in the index [%d]", num));
        OffsetLength offsetLength = indexElement.getOffsetLength();
        if (offsetLength == null) {
            throw new IllegalArgumentException("The scan you've requested to parse spectrumRef for was not found in the index");
        }
        try {
            long offset = offsetLength.offset;
            int length = offsetLength.length;
            // get a read buffer
            ByteArrayHolder bah = PooledByteArrayHolders.getInstance().getPool().borrowObject();
            bah.ensureCapacity(length);
            // do the read IO
            RandomAccessFile raf = this.getRandomAccessFile();
            raf.seek(offset);
            raf.readFully(bah.getUnderlyingBytes(), 0, length);
            bah.setPosition(length); // just to make sure, that BAH knows the valid data range
            // we've read the whole scan from file, wrap it into a ByteStream and pass to the parser
            ByteArrayInputStream is = new ByteArrayInputStream(bah.getUnderlyingBytes(), 0, length);

            // This is just a trick to fool the parser into parsing everything.
            // It doesn't cause any trouble, as we've only read a single scan from the file.
            LCMSDataSubset subset = LCMSDataSubset.WHOLE_RUN;
            if (!parseSpectrum) {
                subset = LCMSDataSubset.STRUCTURE_ONLY;
            }
            MultiSpectraParser parser = getSpectraParser(is, subset, readerPool, 1);
            List<IScan> scansParsed = parser.call();
            if (scansParsed == null || scansParsed.isEmpty()) {
                throw new FileParsingException("Could not parse a single spectrumRef from the file");
            }
            if (scansParsed.size() != 1) {
                throw new FileParsingException("Somehow more than one scan was parsed, when we tried to parse a single spectrumRef");
            }
            IScan scan = scansParsed.get(0);
            return scan;
        } catch (Exception e) {
            throw new FileParsingException(e);
        } finally {
            this.close();
        }
    }

    @Override
    public ISpectrum parseSpectrum(int num) throws FileParsingException {
        IScan scan = parseScan(num, true);
        if (scan == null) {
            throw new FileParsingException("Could not parse spectrumRef from file");
        }
        return scan.getSpectrum(); // this must be safe, as the spectrum parsed by parseScan() must be STRONGly referenced
    }

    private class ReadBuf {
        final byte[] buf;
        int read = -1;
        long offsetInFile = -1;

        public ReadBuf(byte[] buf) {
            this.buf = buf;
        }

        void clear() {
            read = -1;
            offsetInFile = -1;
        }

        boolean filled() {
            return read > 0;
        }
    }

    /**
     * Be careful with this method, it will go over the whole file, building index from scans that it can find.
     * It will ignore the index in file, even if it's present!<br/>
     * This method is called automatically if an index was requested via {@link #fetchIndex()} and the file contained
     * no index.
     * @param idx index instance to add parsed index elements to, this index will be modified.
     * @return
     * @throws FileParsingException
     */
    public I buildIndex(I idx) throws FileParsingException {
        int numWorkers = getNumThreadsForParsing();
        ExecutorService exec = Executors.newFixedThreadPool(numWorkers);

        // set up read buffers
        final int readLen = INDEX_BUILDER_READ_BUF_SIZE;
        ReadBuf readBuf1 = new ReadBuf(new byte[readLen]);
        ReadBuf readBuf2 = new ReadBuf(new byte[readLen]);


        ArrayList<E> unfinishedIndexElements = new ArrayList<>(100);
        // this is the second read buffer, used for reads, while threads are parsing the previous batch

        // if the file has BOM, skip it
        int bomLength = -1;
        try {
            XMLStreamReaderImpl reader = new XMLStreamReaderImpl();
            reader.setInput(this.getBufferedInputStream(), StandardCharsets.UTF_8.name());
            bomLength = reader.getLocation().getBomLength();
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new FileParsingException(e);
        } finally {
            this.close();
        }
        if (bomLength == -1) {
            throw new IllegalStateException("BOM length returned as '-1' not allowed");
        }

        // now actually read the file
        try {
            RandomAccessFile raf = this.getRandomAccessFile();
            final long fileLen = raf.length();
            if (fileLen == 0) {
                throw new FileParsingException("File size was zero when trying to build index.");
            }
            readBuf1.offsetInFile = bomLength; // start at the end of BOM
            int iteration = 0;
            do {
                iteration++;
                // This is needed for cancellable tasks
                if (Thread.interrupted()) {
                    throw new FileParsingException("Thread interrupted, parsing was cancelled.");
                }
                // check if we have read something in the previous iteration, if we did, then use the 2nd read buffer
                if (readBuf2.filled()) {
                    // if we did read something on the previous iteration before submitting parsing tasks, use it
                    // by flipping the buffers
                    ReadBuf readBufTmp = readBuf1;
                    readBuf1 = readBuf2;
                    readBuf2 = readBufTmp;
                    readBuf2.clear();
                } else {
                    // this is the first read
                    raf.seek(readBuf1.offsetInFile);

                    // TODO: ACHTUNG: WARING: when there is no index, there is still a bug here
                    // the fix was lost in the HDD crash :(((
                    readBuf1.read = raf.read(readBuf1.buf);
                }

                // distribute the buffer between workers
                IndexBuilder.Info[] infos = distributeIndexBuilders(readBuf1, numWorkers);
                List<Future<IndexBuilder.Result<E>>> futures = submitIndexBuilders(infos, exec);

                // before blocking on waiting for the parsing tasks to complete, initiate another read
                readBuf2.clear();
                readBuf2.offsetInFile = readBuf1.offsetInFile + readBuf1.read - this.INDEX_BUILDER_MIN_OVERLAP;
                if (readBuf2.offsetInFile > readBuf1.offsetInFile) { // we're not yet further than the EOF
                    raf.seek(readBuf2.offsetInFile);
                    raf.read(readBuf2.buf);
                }

                // block and wait for all the parsers to finish, at this point
                // we already have the next chunk read-in while the parsers were busy
                for (Future<IndexBuilder.Result<E>> future : futures) {
                    try {
                        IndexBuilder.Result<E> result = future.get(getParsingTimeout(), TimeUnit.SECONDS);
                        if (result != null) {
                            List<E> indexElements = result.getIndexElements();
                            for (E indexElement : indexElements) {
                                idx.add(indexElement);
                            }
                            List<E> unfinishedElems = result.getUnfinishedIndexElements();
                            if (!unfinishedElems.isEmpty()) {
                                unfinishedIndexElements.addAll(unfinishedElems);
                            }
                        } else {
                            throw new FileParsingException("Result was null, which should never happen");
                        }
                    } catch (InterruptedException | TimeoutException | ExecutionException | NullPointerException e) {
                        throw new FileParsingException(e);
                    }
                }
                readBuf1.clear();
            } while (readBuf2.filled());

            if (!unfinishedIndexElements.isEmpty()) {
                for (E e : unfinishedIndexElements) {
                    E byNum = idx.getByNum(e.getNumber());
                    if (byNum == null) {
                        idx.add(e); // we'll definitely need to run fixIndex() after doing that!
                    }
                }
            }
            // this is pretty much required as the index's internal numbering is incorrect at this point
            // and the raw numbers are used as internal numbers
            idx = fixIndex(idx);

            this.close();
        } catch (IOException ex) {
            throw new FileParsingException(ex);
        } finally {
            this.close();
            exec.shutdown();
        }
        // wait for the executor pool to shut down
        try {
            exec.awaitTermination(getParsingTimeout(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new FileParsingException(String.format("Executor pool failed to shut down in %d sec!", getParsingTimeout()), e);
        }

        return idx;
    }

    private IndexBuilder.Info[] distributeIndexBuilders(ReadBuf rb, int numWorkers) {

        byte[] buf = rb.buf;
        int len = rb.read;
        long offsetInFile = rb.offsetInFile;
        if (len == 0) {
            return new IndexBuilder.Info[0];
        }

        final int baseReadLen = INDEX_BUILDER_MIN_READ_SIZE + INDEX_BUILDER_MIN_OVERLAP;

        if (len <= baseReadLen) {
            // if we only have enough bytes for one worker - so be it
            ByteArrayInputStream is = new ByteArrayInputStream(buf, 0, len);
            IndexBuilder.Info worker = new IndexBuilder.Info(offsetInFile, 0, is);
            return new IndexBuilder.Info[]{worker};
        }

        /**
         *         |----------|
         * |----------|    |----------| N segments of total length S
         *          ^           ^
         *          O(overlap)   X(length of one segment)
         */
        double bytesPerWorker = ((double)len + (numWorkers - 1) * INDEX_BUILDER_MIN_OVERLAP ) / numWorkers;
        double numWorkersForMinReadLengths = (len - INDEX_BUILDER_MIN_OVERLAP) / (double)INDEX_BUILDER_MIN_READ_SIZE;
        int bpw = (int)Math.ceil(bytesPerWorker);
        int nwfmrl = (int)Math.ceil(numWorkersForMinReadLengths);

        int numAssignedWorkers = Math.min(numWorkers, nwfmrl);
        IndexBuilder.Info[] workers = new IndexBuilder.Info[numAssignedWorkers];

        int curOffset = 0;
        int curReadLen = numAssignedWorkers == numWorkers ? bpw : baseReadLen;
        for (int i = 0; i < workers.length; i++) {
            ByteArrayInputStream is = new ByteArrayInputStream(buf, curOffset, curReadLen);
            IndexBuilder.Info worker = new IndexBuilder.Info(offsetInFile, curOffset, is);
            workers[i] = worker;
            curOffset += curReadLen - INDEX_BUILDER_MIN_OVERLAP;
            if (curOffset + curReadLen > len) {
                curReadLen = len - curOffset;
            }
        }

        // we don't have enough workers, each will have to process more than the minimum

        return workers;
    }

    public abstract IndexBuilder<E> getIndexBuilder(IndexBuilder.Info info);

    private List<Future<IndexBuilder.Result<E>>> submitIndexBuilders(IndexBuilder.Info[] builders, ExecutorService exec) {
        ArrayList<Future<IndexBuilder.Result<E>>> result = new ArrayList<>(builders.length);
        for (IndexBuilder.Info info : builders) {
            IndexBuilder<E> builder = getIndexBuilder(info);
            //MultiSpectraParser parser = getSpectraParser(info.is, LCMSDataSubset.WHOLE_RUN, readerPool, null);
            //Info builder = parser.getIndexBuilder(info.offsetInFile, info.offsetInBuffer);
            Future<IndexBuilder.Result<E>> task = exec.submit(builder);
            result.add(task);
        }
        return result;
    }
}
