package umich.ms.datatypes.scancollection.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.StorageStrategy;
import umich.ms.datatypes.scan.impl.ScanDefault;
import umich.ms.datatypes.scan.props.PrecursorInfo;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.scancollection.ScanCollectionHelper;
import umich.ms.datatypes.scancollection.ScanIndex;
import umich.ms.datatypes.scancollection.ScanIndexRoot;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.LCMSDataSource;
import umich.ms.util.Interval1D;
import umich.ms.util.IntervalST;

/**
 * The default implementation of {@link umich.ms.datatypes.scancollection.IScanCollection}.
 * @author Dmitry Avtonomov
 */
public class ScanCollectionDefault implements IScanCollection {
    public LCMSRunInfo runInfo;
    protected ScanIndexRoot index;
    public TreeMap<Integer, IntervalST<Double, TreeMap<Integer, IScan>>> msLevel2rangeGroups;
    protected StorageStrategy defaultStorageStrategy;
    protected boolean isAutoloadSpectra;
    protected LCMSDataSource<?> source;
    private volatile boolean isFinalized = false;


    public ScanCollectionDefault() {
        
        initFields();
        
        defaultStorageStrategy = IScanCollection.DEFAULT_STORAGE_STRATEGY;
        isAutoloadSpectra = false;
    }

    /**
     * With this constructor you can pre-configure the scan collection to
     * automatically load spectra if they were not parsed in the first place.
     * Using this feature, you can parse only the structure of the file, and
     * then safely access spectra from any scans. If the spectrum is not there
     * (i.e. the reference to {@link ISpectrum} in an {@link IScan} returns
     * {@code null}), then the spectrum should be automatically parsed from the
     * file. This will be very slow, if you do it this was to access all MS1
     * spectra in a large run, for example.
     *
     * @param isAutoloadSpectra
     */
    public ScanCollectionDefault(boolean isAutoloadSpectra) {
        this();
        this.isAutoloadSpectra = isAutoloadSpectra;
    }

    /**
     * Sets all fields to their fresh new values, except for those,
     * that can be changed by user after the creation of this collection.
     * Basically will just free up scan maps.
     */
    private void initFields() {
        runInfo = null;
        index = new ScanIndexRoot();
        msLevel2rangeGroups = new TreeMap<>();
    }

    @Override
    public StorageStrategy getDefaultStorageStrategy() {
        return defaultStorageStrategy;
    }

    @Override
    public void setDefaultStorageStrategy(StorageStrategy storageStrategy) {
        this.defaultStorageStrategy = storageStrategy;
    }

    @Override
    public boolean isAutoloadSpectra() {
        return isAutoloadSpectra;
    }

    @Override
    public void isAutoloadSpectra(boolean newVal) {
        isAutoloadSpectra = newVal;
    }

    @Override
    public LCMSDataSource<?> getDataSource() {
        return source;
    }

    @Override
    public void setDataSource(LCMSDataSource<?> source) {
        this.source = source;
    }


    /**
     * Adds a new scan to this collection, maintains all the proper inside mappings.
     * If a scan with the same scan number is already in the map it will be replaced
     * and the old entry will be returned. If no replacement took place - null is returned.
     * @param scan scan to be added
     * @return null if the scan was just added, or the old Scan, if it was replaced
     */
    @Override
    public IScan addScan(final IScan scan) {
        IScan oldScan = index.add(scan);


        // update available precursor m/z ranges map

        //Double avgWndMz = null;
        Interval1D<Double> si = null; // search interval
        if (scan.getMsLevel() == 1) {
            if (scan.getScanMzWindowLower() != null && scan.getScanMzWindowUpper() != null) {
                // we don't have precursors for MS1 scans, so we treat their full mass range
                // as the range for classification
                //avgWndMz = (scan.getScanMzWindowLower() + scan.getScanMzWindowUpper()) / 2d;
                si = new Interval1D<>(scan.getScanMzWindowLower(), scan.getScanMzWindowUpper());
            }
        } else {
            // for MS^N scans, we're only interested in cases, when the precursor window is known,
            // otherwise we'll try to guess the windows.
            if (scan.getPrecursor() != null
                    && scan.getPrecursor().getMzRangeStart() != null
                    && scan.getPrecursor().getMzRangeEnd() != null) {
                // if they're not equal, we have a range to search for
                // but we still don't want to do the grouping if the range
                //avgWndMz = (scan.getPrecursor().getMzRangeStart() + scan.getPrecursor().getMzRangeEnd()) / 2d;
                si =  new Interval1D<>(scan.getPrecursor().getMzRangeStart(), scan.getPrecursor().getMzRangeEnd());
            }
        }

        if (si != null) {
            IntervalST<Double, TreeMap<Integer, IScan>> rangeTreeAtMsLvl = msLevel2rangeGroups.get(scan.getMsLevel());
            if (rangeTreeAtMsLvl == null) {
                rangeTreeAtMsLvl = new IntervalST<>();
                msLevel2rangeGroups.put(scan.getMsLevel(), rangeTreeAtMsLvl);
            }

            // get all the nodes intersecting our current interval
            Iterable<IntervalST.Node<Double, TreeMap<Integer, IScan>>> nodes = rangeTreeAtMsLvl.searchAll(si);
            IntervalST.Node<Double, TreeMap<Integer, IScan>> bestNode = null;
            double bestOverlapSum = 0d;

            // find the best-overlapping interval
            double siLen = si.hi - si.lo;
            double ciLen, diff1, diff2, ciOverlap, siOverlap, overlap;
            for (IntervalST.Node<Double, TreeMap<Integer, IScan>> node : nodes) {
                Interval1D<Double> ci = node.getInterval(); // current interval (already in collection)
                ciLen = ci.hi - ci.lo;
                diff1 = si.hi - ci.lo;
                diff2 = ci.hi - si.lo;
                overlap = Math.min(Math.min(Math.min(ciLen, siLen), diff1), diff2);
                if (ciLen == 0 && siLen == 0) {
                    // that's the case of AB Sciex SWATH data, when precursors are given
                    // as single m/z values, instead of windows.
                    // In that case we just add the interval to the list, but
                    // when finalizing the ScanCollection we'll remove the whole
                    // MS2 subtree, if every precursor interval in it doesn't contain
                    // more than one scan.
                    bestNode = node;
                    // we don't expect to find more than one zero-length interval in the tree,
                    // so it's safe to stop searching for best-overlapping interval
                    break;
                }
                if (overlap > 0) {
                    ciOverlap = overlap / ciLen;
                    siOverlap = overlap / siLen;
                    if (ciOverlap >= MIN_GROUP_OVERLAP && siOverlap >= MIN_GROUP_OVERLAP) {
                        double overlapSum = ciOverlap + siOverlap;
                        if (overlapSum > bestOverlapSum) {
                            bestNode = node;
                            bestOverlapSum = overlapSum;
                        }
                    }
                }
            }

            if (bestNode == null) {
                // there were no intervals intersecting our current interval at all
                // or they didn't pass the criteria to be accepted.
                // it's safe to just add the current one.
                TreeMap<Integer, IScan> scanMap = new TreeMap<>();
                scanMap.put(scan.getNum(), scan);
                rangeTreeAtMsLvl.put(si, scanMap);
            } else {
                // we've found a node which overlaps our interval
                // rolling update of average
                // x: the update value to be added to the previous average
                // c: the new incoming value
                // N: the number of items int prev_avg
                // x = (c - prev_avg) / (N + 1)

                Interval1D<Double> ci = bestNode.getInterval();
                int nodeSize = bestNode.getValue().size();

                if (!si.lo.equals(ci.lo) || !si.hi.equals(ci.hi)) {
                    // if the boundaries need to be updated, the only way is to
                    // update the whole range tree by putting a new interval there
                    double newLo = ci.lo + (si.lo - ci.lo) / (nodeSize + 1);
                    double newHi = ci.hi + (si.hi - ci.hi) / (nodeSize + 1);
                    IntervalST.Node<Double, TreeMap<Integer, IScan>> removed = rangeTreeAtMsLvl.remove(ci);
                    removed.getValue().put(scan.getNum(), scan);
                    rangeTreeAtMsLvl.put(new Interval1D<>(newLo, newHi), removed.getValue());
                } else {
                    IntervalST.Node<Double, TreeMap<Integer, IScan>> node = rangeTreeAtMsLvl.get(si);
                    node.getValue().put(scan.getNum(), scan);
                }
            }
        }

        return oldScan;
    }

    /**
     * Get a map, holding spectra groupped by precursor m/z isolation window.<br/>
     * MS1 spectra are groupped by their overall m/z range.
     * @return Interval search tree for every level, this is only created after a call to {@link #finalize()}.
     *         A call to {@link #finalize()} will be made for you automatically, if you load scans using an
     *         {@link LCMSDataSubset} with lo/hi scan nums as null.
     */
    @Override
    public TreeMap<Integer, IntervalST<Double, TreeMap<Integer, IScan>>> getMapMsLevel2rangeGroups() {
        return msLevel2rangeGroups;
    }

    /**
     * Get a map which links actual scan numbers to instances of Scan objects.
     * @return mapping from internal (1-based) scan numbers to scans
     */
    @Override
    public TreeMap<Integer, IScan> getMapNum2scan() {
        return index.getNum2scan();
    }

    /**
     * Get a map which links MS Levels (starting from 1) to maps of (scanNum => scanObject).
     * @return the mapping
     */
    @Override
    public TreeMap<Integer, ScanIndex> getMapMsLevel2index() {
        return index.getMsLvl2index();
    }

    /**
     * Maps retention time (in minutes) to lists of Scan objects.
     * Generally there will be just one Scan object in the list,
     * but newer instruments, like  Thermo Fusion should be able to measure several spectra simultaneously
     * hence the use of List here for future-proofing.
     * @return the mapping
     */
    @Override
    public TreeMap<Double, List<IScan>> getMapRt2scan() {
        return index.getRt2scan();
    }

    @Override
    public LCMSRunInfo getRunInfo() {
        return runInfo;
    }

    @Override
    public void setRunInfo(LCMSRunInfo runInfo) {
        this.runInfo = runInfo;
    }

    /**
     * RT of the last scan in this scan collection.
     * @return
     */
    @Override
    public Double getRtMax() {
        return getMapRt2scan().lastKey();
    }

    /**
     * RT of the first scan. Useful if a scan collection is a subset of the
     * original run, in which case start RT might be e.g. 40min.
     * @return
     */
    @Override
    public Double getRtMin() {
        return getMapRt2scan().firstKey();
    }

    /**
     * The name says it all.
     * @param scanNum Scan number as it was in the original MS file
     * @return Scan or null, if no such scan number exists in this ScanCollection
     */
    @Override
    public IScan getScanByNum(int scanNum) {
        IScan scan = getNum2scan().get(scanNum);
        if (scan != null) return scan;
        return null;
    }

    /**
     * Scan with closest Number STRICTLY less than the provided one is returned.
     * @param scanNum scan number
     * @return Scan or null, if the ScanCollection didn't have any scans with numbers <= than this one
     */
    @Override
    public IScan getScanByNumLower(int scanNum) {
        IScan scan = getNum2scan().lowerEntry(scanNum).getValue();
        if (scan != null) return scan;
        return null;
    }

    /**
     * Scan with the closest Number greater or equal to the provided one is returned.
     * @param scanNum scan number
     * @return Scan or null, if the ScanCollection didn't have any scans with numbers >= than this one
     */
    @Override
    public IScan getScanByNumUpper(int scanNum) {
        IScan scan = getNum2scan().ceilingEntry(scanNum).getValue();
        if (scan != null) return scan;
        return null;
    }

    /**
     * Scan with the closest Number is returned.
     * @param scanNum scan number
     * @return null might be returned in an extreme case when the scan collection
     * is empty.
     */
    @Override
    public IScan getScanByNumClosest(int scanNum) {
        IScan result = null;
        Map.Entry<Integer, IScan> lower = getNum2scan().lowerEntry(scanNum);
        Map.Entry<Integer, IScan> upper = getNum2scan().ceilingEntry(scanNum);
        if (upper != null && lower != null) {
            result = Integer.compare(lower.getKey(), upper.getKey()) > 0 ? lower.getValue() : upper.getValue();
        } else if (upper != null) {
            result = upper.getValue();
        } else if (lower != null) {
            result = lower.getValue();
        }
        return result;
    }

    /**
     * Provided RT <i>MUST</i> be <i>EXACT</i> RT, existing in the map.
     * @param rt RT in minutes
     * @return list of Scans or null if no such RT exists
     */
    @Override
    public List<IScan> getScansByRt(double rt) {
        List<IScan> scans = getRt2scan().get(rt);
        if (scans != null) return scans;
        return null;
    }

    /**
     * Scan List with closest RT less or equal to the provided one are returned.
     * @param rt
     * @return
     */
    @Override
    public List<IScan> getScansByRtLower(double rt) {
        Map.Entry<Double, List<IScan>> lowerEntry = getRt2scan().lowerEntry(rt);
        if (lowerEntry == null)
            return null;
        List<IScan> scans = lowerEntry.getValue();
        if (scans != null) return scans;
        return null;
    }

    /**
     * Scan List with the closest RT greater or equal to the provided one are returned.
     * @param rt
     * @return
     */
    @Override
    public List<IScan> getScansByRtUpper(double rt) {
        Map.Entry<Double, List<IScan>> ceilingEntry = getMapRt2scan().ceilingEntry(rt);
        if (ceilingEntry == null)
            return null;
        List<IScan> scans = ceilingEntry.getValue();
        if (scans != null) return scans;
        return null;
    }

    /**
     * Scans with the closest RT are returned.
     * @param rt
     * @return null if no scans were found at all, but this should only happen
     *         if your ScanCollection is empty.
     */
    @Override
    public List<IScan> getScansByRtClosest(double rt) {
        List<IScan> result = null;
        Map.Entry<Double, List<IScan>> lower = getMapRt2scan().lowerEntry(rt);
        Map.Entry<Double, List<IScan>> upper = getMapRt2scan().ceilingEntry(rt);
        if (upper != null && lower != null) {
            if (Math.abs(rt - lower.getKey()) <= Math.abs(rt - upper.getKey())) {
                result = lower.getValue();
            } else {
                result = upper.getValue();
            }
//            result = Double.compare(lower.getKey(), upper.getKey()) > 0 ? lower.getValue() : upper.getValue();
        } else if (upper != null) {
            result = upper.getValue();
        } else if (lower != null) {
            result = lower.getValue();
        }
        return result;
    }


    /**
     * A view(not a copy!) of the original scan map, containing only scans from numStart to numEnd (inclusive).
     * Scan numbers don't need to be exact matches, scans from the closed interval [numStart; numEnd] are returned.
     * @param numStart
     * @param numEnd
     * @param msLevel
     * @return A view or null if there were no scans at this ms level in [numStart; numEnd] interval
     */
    @Override
    public NavigableMap<Integer, IScan> getScansByNumSpanAtMsLevel(int numStart, int numEnd, int msLevel) {
        NavigableMap<Integer, IScan> subMap = null;
        TreeMap<Integer, IScan> msLevelMap = getMapMsLevel2index().get(msLevel).getNum2scan();
        if (msLevelMap != null) {
            subMap = msLevelMap.subMap(numStart, true, numEnd, true);
        }
        if (subMap != null && subMap.size() > 0) {
            return subMap;
        }
        return null;
    }

    /**
     *
     * @param numStart Starting scan number (inclusive)
     * @param numEnd The last scan number in range (inclusive)
     * @return
     */
    @Override
    public TreeMap<Integer, NavigableMap<Integer, IScan>> getScansByNumSpan(int numStart, int numEnd) {
        TreeMap<Integer, NavigableMap<Integer, IScan>> viewMap = new TreeMap<>();
        boolean hasNonZeroElements = false;
        for (Integer i : getMapMsLevel2index().keySet()) {
            NavigableMap<Integer, IScan> scansByNumSpanAtMsLevel = getScansByNumSpanAtMsLevel(numStart, numEnd, i);
            if (scansByNumSpanAtMsLevel != null) {
                hasNonZeroElements = true;
                viewMap.put(i, scansByNumSpanAtMsLevel);
            }
        }
        if (hasNonZeroElements)
            return viewMap;
        return null;
    }

    /**
     * Same as {@link #getScansByRtSpan(double, double)}, but only searches at one MS Level.<br/>
     * If it so happens, that the RTs are between just 2 consecutive scans, then null is returned.<br/>
     * You get a view of the original scan map, not a copy!
     * @param rtStart The beginning of RT window
     * @param rtEnd The end of RT window
     * @param msLevel MS Level at which to get scans
     * @return null, if no scans were in this window.
     */
    @Override
    public NavigableMap<Integer, IScan> getScansByRtSpanAtMsLevel(double rtStart, double rtEnd, int msLevel) {
        if (getNum2scan().size() == 0) return null;
        List<IScan> startScans = getScansByRtUpper(rtStart);
        int startScanNum =
                startScans == null ?
                        getNum2scan().firstEntry().getValue().getNum() :
                        startScans.get(0).getNum();
        List<IScan> endScans = getScansByRtLower(rtEnd);
        int endScanNum =
                endScans == null ?
                        getNum2scan().lastEntry().getValue().getNum() :
                        endScans.get(0).getNum();
        if (endScanNum < startScanNum) {
            return null;
        }
        NavigableMap<Integer, IScan> scansByNumSpanAtMsLevel = getScansByNumSpanAtMsLevel(startScanNum, endScanNum, msLevel);
        if (scansByNumSpanAtMsLevel != null && scansByNumSpanAtMsLevel.size() > 0)
            return scansByNumSpanAtMsLevel;
        return null;
    }

    /**
     * Returns a range of scans, whose RT is in the inclusive interval [rtStart; rtEnd].
     * That means, ceil RT match is used for rtStart and floor RT for rtEnd.
     * If it so happens, that the RTs are between just 2 consecutive scans, then null is returned.
     * @param rtStart The beginning of RT window
     * @param rtEnd The end of RT window
     * @return null, if no scans were in this window
     */
    @Override
    public TreeMap<Integer, NavigableMap<Integer, IScan>> getScansByRtSpan(double rtStart, double rtEnd) {
        TreeMap<Integer, NavigableMap<Integer, IScan>> viewMap = new TreeMap<>();
        boolean hasNonZeroElements = false;
        for (Integer i : getMapMsLevel2index().keySet()) {
            NavigableMap<Integer, IScan> scansByRtSpanAtMsLevel = getScansByRtSpanAtMsLevel(rtStart, rtEnd, i);
            if (scansByRtSpanAtMsLevel != null) {
                hasNonZeroElements = true;
                viewMap.put(i, scansByRtSpanAtMsLevel);
            }
        }
        if (hasNonZeroElements)
            return viewMap;
        return null;
    }

    /**
     * Get the total number of scans in this ScansCollection.
     * @see #getScanCountAtMsLevel(int)
     * @return number of scans
     */
    @Override
    public int getScanCount() {
        return getNum2scan().size();
    }

    /**
     * If the msLevel doesn't exist in this ScanCollectionDefault, returns null
     * @param msLevel
     * @return null, if msLevel doesn't exist. Actual number of scans otherwise.
     */
    @Override
    public Integer getScanCountAtMsLevel(int msLevel) {
        TreeMap<Integer, IScan> msLevelScanMap = getMapMsLevel2index().get(msLevel).getNum2scan();
        if (msLevelScanMap != null) {
            return msLevelScanMap.size();
        }
        return null;
    }

    @Override
    public IScan getNextScan(int scanNum) {
        Map.Entry<Integer, IScan> higherEntry = getMapNum2scan().higherEntry(scanNum);
        return higherEntry == null ? null : higherEntry.getValue();
    }

    /**
     * Finds the next scan at the same MS level, as the scan with scanNum.
     * If the scan number provided is not in the map, this method will find the next existing one.
     * @param scanNum Scan number
     * @param msLevel MS Level at which to search for that scan number. That MS level
     * must be present in the collection, otherwise an NPE is thrown.
     * @return either next Scan, or null, if there are no further scans at this level.
     * If MS level is incorrect, also returns null.
     * @throws NullPointerException
     */
    @Override
    public IScan getNextScanAtMsLevel(int scanNum, int msLevel) {
        TreeMap<Integer, IScan> msLevelMap = getMapMsLevel2index().get(msLevel).getNum2scan();
        if (msLevelMap == null) return null;
        Map.Entry<Integer, IScan> entry = msLevelMap.ceilingEntry(scanNum + 1);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }

    /**
     * Convenience method, calls {@link #getNextScanAtMsLevel(int, int)} internally
     * @param scan an existing scan from THIS ScanCollection
     * @return
     */
    @Override
    public IScan getNextScanAtSameMsLevel(IScan scan) {
        return getNextScanAtMsLevel(scan.getNum(), scan.getMsLevel());
    }

    @Override
    public IScan getPrevScan(int scanNum) {
        Map.Entry<Integer, IScan> lowerEntry = getMapNum2scan().lowerEntry(scanNum);
        return lowerEntry == null ? null : lowerEntry.getValue();
    }

    /**
     * Finds the next scan at the same MS level, as the scan with scanNum.
     * If the scan number provided is not in the map, this method will find the next existing one.
     * @param scanNum Scan number
     * @param msLevel MS Level at which to search for that scan number
     * @return either next Scan, or null, if there are no further scans at this level.
     * If MS level is incorrect, also returns null.
     */
    @Override
    public IScan getPrevScanAtMsLevel(int scanNum, int msLevel) {
        TreeMap<Integer, IScan> msLevelMap = getMapMsLevel2index().get(msLevel).getNum2scan();
        if (msLevelMap == null) return null;
        Map.Entry<Integer, IScan> entry = msLevelMap.floorEntry(scanNum - 1);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }

    /**
     * Convenience method, calls {@link #getPrevScanAtMsLevel(int, int)} internally
     * @param scan
     * @return
     */
    @Override
    public IScan getPrevScanAtSameMsLevel(IScan scan) {
        return getPrevScanAtMsLevel(scan.getNum(), scan.getMsLevel());
    }

    /////////////////////////////////////////////////
    /////////////////////////////////////////////////
    ////
    ////    Data Manupulation Section
    ////
    /////////////////////////////////////////////////
    /////////////////////////////////////////////////


    @Override
    public void setStorageStrategy(LCMSDataSubset subset, StorageStrategy storageStrategy) {
        NavigableMap<Integer, IScan> scansInSubsetByNumber = getScansInSubsetByNumber(getMapNum2scan(), subset);

        for (IScan scan : scansInSubsetByNumber.values()) {
            if (subset.isInSubset(scan)) {
                scan.setStorageStrategy(storageStrategy);
            }
        }
    }

    @Override
    public void setStorageStrategy(LCMSDataSubset subset, StorageStrategy strategyInSet, StorageStrategy strategyNotInSet) {
        if (strategyInSet == null && strategyNotInSet == null) {
            return;
        }
        if (strategyInSet != null && strategyNotInSet == null) {
            NavigableMap<Integer, IScan> subMap = getScansInSubsetByNumber(getMapNum2scan(), subset);
            setStorageStrategyForMap(subset, subMap, strategyInSet, strategyNotInSet);

        } else if (strategyInSet == null && strategyNotInSet != null) {
            List<NavigableMap<Integer, IScan>> subMaps = getScansNotInSubsetByNumber(subset);
            for (NavigableMap<Integer, IScan> subMap : subMaps) {
                setStorageStrategyForMap(subset, subMap, strategyInSet, strategyNotInSet);
            }
            for (IScan scan : getMapNum2scan().values()) {
                if (subset.isInSubset(scan)) {
                    scan.setStorageStrategy(strategyInSet);
                } else {
                    scan.setStorageStrategy(strategyNotInSet);
                }
            }
        } else {
            setStorageStrategyForMap(subset, getMapNum2scan(), strategyInSet, strategyNotInSet);
        }
    }

    private void setStorageStrategyForMap(LCMSDataSubset subset, NavigableMap<Integer, IScan> map,
                                          StorageStrategy strategyInSet, StorageStrategy strategyNotInSet) {
        for (IScan scan : map.values()) {
            if (subset.isInSubset(scan)) {
                scan.setStorageStrategy(strategyInSet);
            } else {
                scan.setStorageStrategy(strategyNotInSet);
            }
        }
    }

    /**
     * It won't filter the scanMap according the the full subset predicate, it
     * only selects scan number range. Further checks for ms-levels and
     * precursor ranges is required. It's just a convenience method.
     * @param scanMap use {@link #getMapNum2scan() } for the broadest use
     * @param subset only scan numbers will be used for filtering the scanMap
     * @return
     */
    private NavigableMap<Integer, IScan> getScansInSubsetByNumber(NavigableMap<Integer, IScan> scanMap, LCMSDataSubset subset) {
        NavigableMap<Integer, IScan> scansInSubsetByNumber = scanMap;
        if (subset.getScanNumLo() != null) {
            scansInSubsetByNumber.subMap(subset.getScanNumLo(), true, scansInSubsetByNumber.lastKey(), true);
        }
        if (subset.getScanNumHi() != null) {
            scansInSubsetByNumber.subMap(scansInSubsetByNumber.firstKey(), true, subset.getScanNumHi(), true);
        }
        return scansInSubsetByNumber;
    }

    private List<NavigableMap<Integer, IScan>> getScansNotInSubsetByNumber(LCMSDataSubset subset) {
        ArrayList<NavigableMap<Integer, IScan>> maps = new ArrayList<>(2);
        if (subset.getScanNumLo() != null && getMapNum2scan().lowerKey(subset.getScanNumLo()) != null) {
            NavigableMap<Integer, IScan> map = getMapNum2scan().subMap(
                    getMapNum2scan().firstKey(), true, getMapNum2scan().lowerKey(subset.getScanNumLo()), true);
            if (map.size() > 0) {
                maps.add(map);
            }
        }
        if (subset.getScanNumHi() != null && getMapNum2scan().higherKey(subset.getScanNumHi()) != null) {
            NavigableMap<Integer, IScan> map = getMapNum2scan().subMap(
                    getMapNum2scan().higherKey(subset.getScanNumHi()), true, getMapNum2scan().lastKey(), true);
            if (map.size() > 0) {
                maps.add(map);
            }
        }
        return maps;
    }

    @Override
    public synchronized void loadData(final LCMSDataSubset subset, StorageStrategy storageStrategy)
            throws FileParsingException {
        if (source == null) {
            throw new IllegalStateException("LCMSDataSource must be set before loading any data");
        }
        if (subset == null) {
            throw new IllegalArgumentException("Data subset can't be null, " +
                    "if you want to parse everything use LCMSDataSubset.WHOLE_RUN");
        }
        if (storageStrategy == null) {
            storageStrategy = defaultStorageStrategy;
        }
        List<IScan> parsedScans = source.parse(subset);
        TreeMap<Integer, IScan> idx = index.getNum2scan();
        for (IScan parsedScan : parsedScans) {

            // do we already have this scan in the index?
            IScan existingScan = idx.get(parsedScan.getNum());
            if (existingScan != null) {
                if (existingScan.getSpectrum() == null && subset.isInSubset(parsedScan)) {
                    existingScan.setStorageStrategy(storageStrategy);
                    // the scan existed, and because parsed scan is in subset
                    // it must have spectrum
                    ISpectrum spectrum = parsedScan.getSpectrum();
                    if (spectrum != null) {
                        existingScan.setSpectrum(spectrum, true);
                    } else {
                        StringBuilder msg = new StringBuilder();

                        boolean inSubset = subset.isInSubset(parsedScan);

                        msg.append(String.format(
                                "Parsed scan was considered to be in subset,"
                                        + " but spectrum was null."
                                        + " Scan MS%d #%d @ %.3f",
                                parsedScan.getMsLevel(), parsedScan.getNum(), parsedScan.getRt()));
                        PrecursorInfo precursor = parsedScan.getPrecursor();
                        if (precursor != null) {
                            msg.append(String.format(", Precursor: %.4f-%.4f", precursor.getMzRangeStart(), precursor.getMzRangeEnd()));
                        }
                        msg.append(". Subset: ");
                        msg.append(subset.toString());

                        System.err.println(msg.toString());
                    }

                }
            } else {
                parsedScan.setStorageStrategy(storageStrategy);
                parsedScan.setScanCollection(this);
                addScan(parsedScan);
            }
        }

        // if the whole scan range was already loaded once, it means it should have been
        // finalized, so no sense in finalizing again
        if (!isFinalized) {
            if (subset.getScanNumLo() == null && subset.getScanNumHi() == null) {
                // if all the scans were requested to be parsed, then we can finalize
                ScanCollectionHelper.finalizeScanCollection(this);
                ScanCollectionHelper.finalizePrecursorWindows(this);
            }
        }
    }

    @Override
    public synchronized void unloadData(LCMSDataSubset subset) {

        // we only check against ms-levels map and not ms-levels-to-range-groups
        // because the latter is only created after a call to finalizeScanCollection()
        // which happens only when the whole run is requested to be loaded by
        // calling loadData(new LCMSDataSubset(null, null, <some ms-levels set>, <some precursor-range list>))
        if (subset.getMsLvls() != null) {
            for(Integer msLvl : subset.getMsLvls()) {
                ScanIndex mapAtMsLvl = getMapMsLevel2index().get(msLvl);
                if (mapAtMsLvl != null) {
                    NavigableMap<Integer, IScan> scansInSubsetByNumber = getScansInSubsetByNumber(mapAtMsLvl.getNum2scan(), subset);
                    for (IScan scan : scansInSubsetByNumber.values()) {
                        if (subset.isInSubset(scan)) {
                            scan.setSpectrum(null, false);
                        }
                    }
                }
            }
        } else {
            NavigableMap<Integer, IScan> scansInSubsetByNumber = getScansInSubsetByNumber(getMapNum2scan(), subset);
            for (IScan scan : scansInSubsetByNumber.values()) {
                if (subset.isInSubset(scan)) {
                    scan.setSpectrum(null, false);
                }
            }
        }
    }

    @Override
    public void unloadData(LCMSDataSubset subset, Set<LCMSDataSubset> exlude) {
        
        // we only check against ms-levels map and not ms-levels-to-range-groups
        // because the latter is only created after a call to finalizeScanCollection()
        // which happens only when the whole run is requested to be loaded by
        // calling loadData(new LCMSDataSubset(null, null, <some ms-levels set>, <some precursor-range list>))
        boolean isOkToUnload = false;
        if (subset.getMsLvls() != null) {
            for(Integer msLvl : subset.getMsLvls()) {
                ScanIndex mapAtMsLvl = getMapMsLevel2index().get(msLvl);
                if (mapAtMsLvl != null) {
                    NavigableMap<Integer, IScan> scansInSubsetByNumber = getScansInSubsetByNumber(mapAtMsLvl.getNum2scan(), subset);
                    unloadSpectraConditionally(scansInSubsetByNumber, subset, exlude);
                }
            }
        } else {
            NavigableMap<Integer, IScan> scansInSubsetByNumber = getScansInSubsetByNumber(getMapNum2scan(), subset);
            unloadSpectraConditionally(scansInSubsetByNumber, subset, exlude);
        }
        
    }

    /**
     * Unloads spectra from scans that match {@code subset} variable and do not match
     * all of subsets from {@code exclude} variable.
     * @param scansInSubsetByNumber pre-filtered scan map, e.g. by scan numbers, ms-levels, etc.
     * @param subset
     * @param exlude
     */
    private void unloadSpectraConditionally(NavigableMap<Integer, IScan> scansInSubsetByNumber, LCMSDataSubset subset, Set<LCMSDataSubset> exlude) {
        boolean isOkToUnload;
        for (IScan scan : scansInSubsetByNumber.values()) {
            if (subset.isInSubset(scan)) {
                isOkToUnload = true;
                for (LCMSDataSubset exludedSubset : exlude) {
                    if (exludedSubset.isInSubset(scan)) {
                        isOkToUnload = false;
                        break;
                    }
                }
                if (isOkToUnload) {
                    scan.setSpectrum(null, false);
                }
            }
        }
    }

    @Override
    public IScan createScanStub(int num) {
        ScanDefault scan = new ScanDefault(this, num);
        scan.setStorageStrategy(this.getDefaultStorageStrategy());
        return scan;
    }

    /**
     * @deprecated This is just a side effect of encapsulating the old Maps into
     * the new ScanIndex. Use {@link #getMapNum2scan() } instead.
     * @return the num2scan
     */
    @Deprecated
    public TreeMap<Integer, IScan> getNum2scan() {
        return index.getNum2scan();
    }

    /**
     * @deprecated This is just a side effect of encapsulating the old Maps into
     * the new ScanIndex. Use {@link #getMapRt2scan() } instead.
     * @return the rt2scan
     */
    @Deprecated
    public TreeMap<Double, List<IScan>> getRt2scan() {
        return index.getRt2scan();
    }

    @Override
    public void reset() {
        initFields();
    }
}
