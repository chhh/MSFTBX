/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.fileio.filetypes;

import umich.ms.datatypes.index.Index;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.util.AbstractFile;

import java.io.Serializable;


/**
 * Provides some basic defaults for simple LCMSFile interface methods.
 * @author dmitriya
 * @param <T>
 */
public abstract class AbstractLCMSDataSource<T extends Index<?>> extends AbstractFile implements LCMSDataSource<T>, Serializable {
    private static final long serialVersionUID = 474635825669722535L;

    /**
     * How many spectra each CPU will be given in one batch. E.g. you assigned 2 threads using
     * {@link #setNumThreadsForParsing(java.lang.Integer)}, and set this parameter to 20, then 40 spectra will be read from disk
     * in one batch and each thread will be given 20 spectra at once for processing.
     */
    protected volatile int tasksPerCpuPerBatch = 10;
    /**
     * Timeout In seconds.
     */
    protected volatile long parsingTimeout = 30L;
    /**
     * When set to true, will discard scans with no m/z values.
     */
    protected volatile boolean excludeEmptyScans = false;
    protected volatile LCMSRunInfo runInfo = null;
    /**
     * How many batches of scans will be processed in parallel. Disk I/O is done once per batch.
     */
    private volatile Integer numThreadsForParsing = null;

    /**
     * Create a new source with autoload of spectra disabled.
     * @param path
     */
    public AbstractLCMSDataSource(String path) {
        super(path);
    }

    @Override
    public LCMSRunInfo getRunInfo() {
        return runInfo;
    }

    @Override
    public LCMSRunInfo fetchRunInfo() throws FileParsingException {
        LCMSRunInfo info = runInfo;
        if (runInfo == null) {
            synchronized (this) {
                info = runInfo;
                if (info == null) {
                    runInfo = info = parseRunInfo();
                }
            }
        }
        return info;
    }

    /** Some ABSciex data has <i>"peaks"</i> tag empty and no <i>"precursor"</i> specified in such cases for MS2 - these are useless.
     * Some Thermo data has <i>"peaks"</i> tag empty for both MS1 and MS2 data, but these actually have <i>"precursor"</i> for MS2
     * so at least it makes some sense to include such scans.
     * @see #setExcludeEmptyScans(boolean)
     */
    @Override
    public boolean isExcludeEmptyScans() {
        return excludeEmptyScans;
    }

    /**
     * If true, empty scans will be discarded during parsing.<br/>
     * Need to be called prior to parsing.
     * @param excludeEmptyScans default value is false
     */
    @Override
    public void setExcludeEmptyScans(boolean excludeEmptyScans) {
        this.excludeEmptyScans = excludeEmptyScans;
    }

    /**
     * How many batches of scans will be processed in parallel. Disk I/O is done once per batch.
     * If {@link #setNumThreadsForParsing(Integer)} has been called with null parameter before, then
     * the number of currently available hardware threads is returned.
     * @return
     */
    @Override
    public Integer getNumThreadsForParsing() {
        return numThreadsForParsing == null ? Runtime.getRuntime().availableProcessors() : numThreadsForParsing;
    }

    /**
     * How many batches of scans will be processed in parallel. Disk I/O is done once per batch.
     * @param numThreadsForParsing must be greater than zero, if null, defaults to the number of
     * available processor cores
     */
    @Override
    public void setNumThreadsForParsing(Integer numThreadsForParsing) {
        if (numThreadsForParsing == null) {
            this.numThreadsForParsing = Runtime.getRuntime().availableProcessors();
            return;
        }
        if (numThreadsForParsing < 1) {
            throw new IllegalArgumentException("The number of threads can not be less than 1.");
        }
        this.numThreadsForParsing = numThreadsForParsing;
    }

    /**
     * How many spectra each CPU will be given in one batch. E.g. you assigned 2 threads using
     * {@link #setNumThreadsForParsing(java.lang.Integer)}, and set this parameter to 20, then 40 spectra will be read from disk
     * in one batch and each thread will be given 20 spectra at once for processing.
     * @return
     */
    @Override
    public int getTasksPerCpuPerBatch() {
        return tasksPerCpuPerBatch;
    }

    /**
     * How many spectra each CPU will be given in one batch. E.g. you assigned 2 threads using
     * {@link #setNumThreadsForParsing(java.lang.Integer)}, and set this parameter to 20, then 40 spectra will be read from disk
     * in one batch and each thread will be given 20 spectra at once for processing.
     * <b>WARNING:</b> If you set this parameter to a large number, consider using {@link #setParsingTimeout(long)} to increase
     * the allowed processing time for each worker thread.
     * @param tasksPerCpuPerBatch must be greater than zero
     */
    @Override
    public void setTasksPerCpuPerBatch(int tasksPerCpuPerBatch) {
        if (tasksPerCpuPerBatch < 1) {
            throw new IllegalArgumentException("The number of tasks per cpu can not be less than 1.");
        }
        this.tasksPerCpuPerBatch = tasksPerCpuPerBatch;
    }

    /**
     * Timeout (in seconds) that worker threads are allowed for parsing one batch of spectra.
     * @return
     */
    @Override
    public long getParsingTimeout() {
        return parsingTimeout;
    }

    /**
     * Timeout (in seconds) that worker threads are allowed for parsing one batch of spectra.
     * @param parsingTimeout (in seconds) must be not less than 1 second
     */
    @Override
    public void setParsingTimeout(long parsingTimeout) {
        if (parsingTimeout < 1L) {
            throw new IllegalArgumentException("Timeout of less than one second is not allowed.");
        }
        if (parsingTimeout < 0L) {
            throw new IllegalArgumentException("Timeout can't be negative.");
        }
        this.parsingTimeout = parsingTimeout;
    }

    //    /**
//     * Parse a range of scan numbers
//     * @param fromNum the number of the first Scan to be parsed from the file (inclusive), can be null
//     * @param toNum the number of the last Scan to be parsed from the file (inclusive), can be null
//     * @return ScanCollectionDefault of all scans from the file
//     * @throws umich.ms.fileio.exceptions.FileParsingException
//     */
//    public IScanCollection parse(Integer fromNum, Integer toNum) throws FileParsingException {
//        return parse(fromNum, toNum, null);
//    }
//
//    /**
//     * Just parseIndexEntries the whole file, Index will be cached.
//     * @return ScanCollectionDefault of all scans from the file
//     * @throws umich.ms.fileio.exceptions.FileParsingException
//     */
//    public IScanCollection parse() throws FileParsingException {
//        return this.parse(null, null);
//    }
//
//    /**
//     * Returns the ScanCollection, parses it first, if necessary.<br/>
//     * If you don't want the ScanCollection to be cached, use
//     * {@link #parse() } instead.
//     * @return
//     * @throws umich.ms.fileio.exceptions.FileParsingException
//     */
//    public IScanCollection fetchScanCollection() throws FileParsingException {
//        IScanCollection scns = scans;
//        if (scns == null) {
//            synchronized (this) {
//                scns = scans;
//                if (scns == null) {
//                    scans = scns = parse();
//                }
//            }
//        }
//        return scns;
//    }


}
