/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.datatypes.scancollection;

import java.util.TreeMap;
import umich.ms.datatypes.scan.IScan;

/**
 *
 * @author Dmitry Avtonomov
 */
public class ScanIndexRoot extends ScanIndex {

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
