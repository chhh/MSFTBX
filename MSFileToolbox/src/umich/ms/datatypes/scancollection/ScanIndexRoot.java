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
import java.util.TreeMap;
import umich.ms.datatypes.scan.IScan;

/**
 *
 * @author Dmitry Avtonomov
 */
public class ScanIndexRoot extends ScanIndex implements Serializable {
    private static final long serialVersionUID = -1544137529430270821L;

    protected TreeMap<Integer, ScanIndex> msLvl2index;

    public ScanIndexRoot() {
        num2scan = new TreeMap<>();
        rt2scan = new TreeMap<>();
        msLvl2index = new TreeMap<>();
    }


    public TreeMap<Integer, ScanIndex> getMsLvl2index() {
        return msLvl2index;
    }


    @Override
    public IScan add(IScan scan) {
        IScan oldScan = super.add(scan);

        Integer msLevel = scan.getMsLevel();
        if (msLevel != null) {
            ScanIndex indexAtMsLvl = msLvl2index.get(msLevel);
            if (indexAtMsLvl == null) {
                indexAtMsLvl = new ScanIndex();
                msLvl2index.put(msLevel, indexAtMsLvl);
            }
            indexAtMsLvl.add(scan);
        }
        return oldScan;
    }
}
