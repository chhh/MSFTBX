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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.datatypes.scan.IScan;

/**
 * @author Dmitry Avtonomov
 */
public class ScanIndex implements Serializable {

  private static final Logger log = LoggerFactory.getLogger(ScanIndex.class);
  private static final long serialVersionUID = -372131408871860232L;

  protected TreeMap<Integer, IScan> num2scan;
  protected TreeMap<Double, List<IScan>> rt2scan;

  public ScanIndex() {
    num2scan = new TreeMap<>();
    rt2scan = new TreeMap<>();
  }

  public TreeMap<Integer, IScan> getNum2scan() {
    return num2scan;
  }

  public TreeMap<Double, List<IScan>> getRt2scan() {
    return rt2scan;
  }

  /**
   * Adds a scan to the index. If the scan has RT set to non-null, will also add it to RT index. If
   * a scan with the same scan number was already in the index, then it will get replaced.
   *
   * @return if a scan with the same scan number was already in the index, then it will be returned.
   */
  public IScan add(IScan scan) {
    int num = scan.getNum();

    IScan oldScan = getNum2scan().put(num, scan);
    log.trace("Adding scan #{} to ScanIndex. num2scan map already contained a scan for scanNum: {}",
        num, num);

    final Double rt = scan.getRt();
    final TreeMap<Double, List<IScan>> rt2scan = getRt2scan();
    if (scan.getRt() != null) {
      List<IScan> scans = rt2scan.get(rt);
      if (scans == null) {
        // no entries for this RT yet
        scans = new ArrayList<>(1);
        scans.add(scan);
        getRt2scan().put(rt, scans);
      } else {
        // check if this scan was in the list
        boolean replaced = false;
        for (int i = 0; i < scans.size(); i++) {
          IScan s = scans.get(i);
          if (s.getNum() == scan.getNum()) {
            scans.set(i, scan);
            replaced = true;
            break;
          }
        }
          if (!replaced) {
              scans.add(scan);
          }
      }
    } else {
      log.debug("Adding scan # to ScanIndex. No RT.", num);
    }
    return oldScan;
  }

}
