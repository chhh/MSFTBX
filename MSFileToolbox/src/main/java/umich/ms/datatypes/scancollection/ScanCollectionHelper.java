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
package umich.ms.datatypes.scancollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.props.PrecursorInfo;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.util.IntervalST;

/**
 * Helper utils for ScanCollections.
 *
 * @author Dmitry Avtonomov
 */
public class ScanCollectionHelper {
  private static final Logger log = LoggerFactory.getLogger(ScanCollectionHelper.class);

  /**
   * Defines the ratio of numbers of scans in the larges "precursor group" to the smallest
   * "precursor group". Formula used: {@code Math.abs(maxScansInGroup - minScansInGroup)) /
   * minScansInGroup}
   *
   * A <b>precursor group</b> is a group of scans, which satisfy one of the two conditions:
   * <ul>
   * <li>precursor windows were not known for scans, but several scans
   * had exactly the same precursor mass</li>
   * <li>precursor windows were known and there was significant overlap
   * between the precursor windows</li>
   * </ul>
   *
   * <b>There also must be more than one scan in EACH group, for a run
   * to be auto-detected as a DIA run.</b>
   */
  public static double MAX_PRECURSOR_GROUP_SIZE_DIFF = 0.1;

  private ScanCollectionHelper() {
    throw new AssertionError("This class should not be instantiated");
  }

  /**
   * Sets relationships between parent/child scans based on scan numbering. If scan number P1 is
   * followed by a scan C1 with higher MS level, C1 is considered a child of P1.
   */
  public static void finalizeScanCollection(IScanCollection scans) throws FileParsingException {
    // check if there is more than one MS level, otherwise there are no relationships to set up
    if (scans.getMapMsLevel2index().size() < 2) {
      return;
    }
    int msLevelLo = scans.getMapMsLevel2index().firstKey();
    int msLevelHi = scans.getMapMsLevel2index().lastKey();
    int scanNumLo = scans.getMapNum2scan().firstKey();
    int scanNumHi = scans.getMapNum2scan().lastKey();
    Set<Map.Entry<Integer, ScanIndex>> entries = scans.getMapMsLevel2index().entrySet();

    Set<Integer> msLevels = scans.getMapMsLevel2index().keySet();
    Integer[] msLevelsArr = msLevels.toArray(new Integer[msLevels.size()]);
    Arrays.sort(msLevelsArr);

    for (int i = 0; i < msLevelsArr.length - 1; i++) {
      // if we're at the bottom MS level, these Scans can't have children, stop processing,
      // hence i < msLevelsArr.length - 1 in the for loop
      final int levelCur = msLevelsArr[i];
      final int levelNxt = msLevelsArr[i + 1];
      final TreeMap<Integer, IScan> mapCur = scans.getMapMsLevel2index().get(levelCur).getNum2scan();
      final TreeMap<Integer, IScan> mapNxt = scans.getMapMsLevel2index().get(levelNxt).getNum2scan();
      for (Map.Entry<Integer, IScan> kv : mapCur.entrySet()) {
        final IScan scanCur = kv.getValue();
        final IScan scanNxt = scans.getNextScanAtSameMsLevel(scanCur);
        int upperBound = scanNxt != null ? scanNxt.getNum() : Integer.MAX_VALUE; // exclusive
        Entry<Integer, IScan> scanNxtDeeperLevel = mapNxt.ceilingEntry(scanCur.getNum() + 1);
        SortedMap<Integer, IScan> children = mapNxt.subMap(scanCur.getNum() + 1, upperBound);
        scanCur.setChildScans(children.isEmpty() ? Collections.emptyList() : new ArrayList<>(children.keySet()));

        if (children.isEmpty()) {
          scanCur.setChildScans(Collections.emptyList());
        } else {
          scanCur.setChildScans(new ArrayList<>(children.size()));
          for (Map.Entry<Integer, IScan> childKv : children.entrySet()) {
            IScan child = childKv.getValue();
            PrecursorInfo precursor = child.getPrecursor();
            if (precursor == null) {
              log.debug("Skipping adding child scan for: {}\n"
                  + "\tPotential child scan had no precursor info: {}", scanCur.toString(), child.toString());
              // this should never happen, precursorInfo should be parsed from mzXML in the first place
//              throw new FileParsingException(
//                  String.format("When trying to set parent for Scan #%d, " +
//                          "the precursor (PrecursorInfo) field was null, which should not happen.\n",
//                      childKv.getKey()));
            } else {
              scanCur.getChildScans().add(child.getNum());
              Integer levelParent = scanCur.getMsLevel();
              Integer levelChild = child.getMsLevel();
//              if (precursor.getParentScanNum() == null // update missing info
//                  && levelParent != null && levelChild != null
//                  && levelParent + 1 == levelChild) {
//
//                Double pLo = precursor.getMzRangeStart();
//                Double pHi = precursor.getMzRangeEnd();
//                Double sLo = scanCur.getScanMzWindowLower();
//                Double sHi = scanCur.getScanMzWindowUpper();
//                if (pLo == null || pHi == null || sLo == null || sHi == null) {
//                  // blindly add it
//                  precursor.setParentScanNum(scanCur.getNum());
//                } else {
//                  // if we know precursor isolation window and the scan's m/z scan range
//                  // they must overlap
//                  if (pLo > sHi || sLo > pHi) {
//                    log.warn("Precursor isolation window and the scan's m/z scan range don't "
//                        + "overlap. Parent scan [{}], child scan [{}]", scanCur.toString(), child.toString());
//                  }
//                  precursor.setParentScanNum(scanCur.getNum());
////                  // calculate overlap
////                  if (pLo.equals(pHi)) { // it's a single point
////                    precursor.setParentScanNum(scanCur.getNum());
////                  } else {
////                    final double minOverlap = 0.75;
////                    double overlap = Math.min(pHi, sHi) - Math.max(pLo, sLo);
////                  }
//                }
//              }

              // this else condition seems to not hold when some scans were cut out from mzXML file with ProteoWizrd.
              // E.g. when only MS2 scans are kept and all MS1 scans were removed, then the precursor info still
              // has that link to MS1 scan, but the scan itself is not in the file, thus the inferred value is incorrect.
//                        else {
//                            // well this is weird, should never happen:
//                            // the Scan contained PrecursorInfo, but the number was different from the inferred one:
//                            // inference is done by selecting the first MS1 scan that is BEFORE this MSn scan
//                            throw new FileParsingException(String.format("When trying to set parent for Scan #%d, "+
//                                    "the Scan contained PrecursorInfo, but the number was different from the inferred one.\n", childNum));
//                        }

            }
          }
        }
      }
    }
  }

  /**
   * Calling this method only makes sense if the whole LC/MS structure has been parsed.
   *
   * @param scans the scan collection, it will be modified in-place
   */
  public static void finalizePrecursorWindows(IScanCollection scans) {
    TreeMap<Integer, IntervalST<Double, TreeMap<Integer, IScan>>> mapMsLevel2rangeGroups = scans
        .getMapMsLevel2rangeGroups();

    List<Integer> msLevelsToRemove = new ArrayList<>();

    msLevelLoop:
    for (Map.Entry<Integer, IntervalST<Double, TreeMap<Integer, IScan>>> entry : mapMsLevel2rangeGroups
        .entrySet()) {

      int msLevel = entry.getKey();
      IntervalST<Double, TreeMap<Integer, IScan>> rangeMapMS2 = entry.getValue();

      if (rangeMapMS2.size() == 1) {
        msLevelsToRemove.add(msLevel);
      } else {
        // if we have a precursor range map, we want to see if it actually contains
        // ranges that have lots of MS2 spectra in them.
        // ALL ranges are required to have more than 1 MS2 spectra

        for (IntervalST.Node<Double, TreeMap<Integer, IScan>> node : rangeMapMS2) {
          if (node.getValue().size() <= 1) {
            // if we didn't have at least 1 scans in even one range, then discard the whole sub-tree
            msLevelsToRemove.add(msLevel);
            continue msLevelLoop;
          }
        }

        // we also check that all ranges contain approximately equal amount of scans
        // THIS WILL NOT WORK FOR MSX DATA, UNLESS "maxScanCountDiff" is set to some large value, which
        // should be at least [ms1_mz_range / (msx_isolation_width * msx_multiplex_number)]
        if (!isAllRangesHaveApproxSameScanCounts(rangeMapMS2)) {
          msLevelsToRemove.add(msLevel);
          continue msLevelLoop;
        }

        // otherwise we just keep it there, but the tree needs to be recreated, because the ranges are incorrect
      }
    }
      for (Integer msLevel : msLevelsToRemove) {
          mapMsLevel2rangeGroups.remove(msLevel);
      }
  }

  private static boolean isAllRangesHaveApproxSameScanCounts(
      IntervalST<Double, TreeMap<Integer, IScan>> rangeMapMS2) {
    boolean allRangesHaveApproxSameScanCounts = true;
    int maxScansInGroup = 0, minScansInGroup = Integer.MAX_VALUE;
    for (IntervalST.Node<Double, TreeMap<Integer, IScan>> node : rangeMapMS2) {
      TreeMap<Integer, IScan> range = node.getValue();
        if (range.size() > maxScansInGroup) {
            maxScansInGroup = range.size();
        }
        if (range.size() < minScansInGroup) {
            minScansInGroup = range.size();
        }
    }

      if (minScansInGroup == 0 || maxScansInGroup == 0 || minScansInGroup == Integer.MAX_VALUE) {
          throw new IllegalStateException(
                  "Something went wrong when guessing precursor groupings for DIA experiment.");
      }

    if (rangeMapMS2.size() == 1) {
      // case .size() == 0 was checked above
      // MS2 range map had only one precursor range and more than 1 scan mapped to it - should be fine
    } else {
      double ratio = ((double) Math.abs(maxScansInGroup - minScansInGroup)) / minScansInGroup;
      if (ratio > MAX_PRECURSOR_GROUP_SIZE_DIFF) {
        allRangesHaveApproxSameScanCounts = false;
      }
    }
    return allRangesHaveApproxSameScanCounts;
  }
}
