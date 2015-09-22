/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package umich.ms.fileio.filetypes.agilent.cef.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * All compounds from an Agilent .cef file.
 * @author Dmitry Avtonomov
 */
public class AgilentCompounds {
    List<AgilentCompound> compounds;

    public AgilentCompounds() {
        compounds = new ArrayList<>();
    }

    public AgilentCompounds(int startSize) {
        compounds = new ArrayList<>(startSize);
    }

    public List<AgilentCompound> getCompounds() {
        return compounds;
    }

    public int size() {
        return compounds.size();
    }

    public boolean isEmpty() {
        return compounds.isEmpty();
    }

    public boolean add(AgilentCompound e) {
        return compounds.add(e);
    }

    public void splitCompoundsByAdduct() {
        if (compounds.isEmpty())
            return;

        // maps from molecular signature string to the peak
        // signature like: {2M+3Na+[-H2O]}+4
        // means: dimer, positively charged with 3 'Na' ions, with a loss of water, 4th isotopic peak.
        // the thing in curly braces is the unuique identifier for the ion, that we're putting to the map
        HashMap<String, AgilentMSPeak> map;
        List<AgilentCompound> tmp = new ArrayList<>(compounds.size());
        for (AgilentCompound c : compounds) {
            map = new HashMap<>();
            for(AgilentMSPeak p : c.getPeaks()) {
                Matcher matcher = p.parseIonSignature();
                if (matcher == null)
                    throw new IllegalStateException("Ion signature did not match the regexp in AgilentMSPeak");

            }
        }
    }
}
