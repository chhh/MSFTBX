/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.datatypes.scancollection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import umich.ms.datatypes.scan.IScan;

/**
 *
 * @author Dmitry Avtonomov
 */
public class ScanIndex implements Serializable {
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
     * Adds a scan to the index. If the scan has RT set to non-null, will also
     * add it to RT index.<br/>
     * If a scan with the same scan number was already in the index, then it
     * will get replaced.
     *
     * @param scan
     * @return if a scan with the same scan number was already in the index,
     * then it will be returned.
     */
    public IScan add(IScan scan) {
        int num = scan.getNum();
        Double rt = scan.getRt();
        Integer msLevel = scan.getMsLevel();

        IScan oldScan = getNum2scan().put(num, scan);
        if (rt != null) {
            List<IScan> rtEntry = getRt2scan().get(rt);
            if (rtEntry == null) {
                rtEntry = new ArrayList<>(1);
                rtEntry.add(scan);
                getRt2scan().put(rt, rtEntry);
            } else {
                // check if this scan was in the list
                for (int i = 0; i < rtEntry.size(); i++) {
                    IScan s = rtEntry.get(i);
                    if (s.getNum() == scan.getNum()) {
                        rtEntry.set(i, scan);
                        break;
                    }
                }
            }
        }
        return oldScan;
    }

}
