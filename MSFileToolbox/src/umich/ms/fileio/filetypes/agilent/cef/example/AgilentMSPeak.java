/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package umich.ms.fileio.filetypes.agilent.cef.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dmitry Avtonomov
 */
public class AgilentMSPeak {
    public static final int CHARGE_UNKNOWN = Integer.MIN_VALUE;
    public static String GRP_MOL_IDENTITY = "grp_id";
    public static String GRP_M_COUNT = "grp_m_cnt";
    public static String GRP_Z_SIGN = "grp_z_sgn";
    public static String GRP_Z_COUNT = "grp_z_cnt";
    public static String GRP_Z_CARRIER = "grp_z_crr";
    public static String GRP_ADDUCT = "grp_add";
    public static String GRP_ISOTOPE_NUM = "grp_iso_n";

    // this works
//    public static Pattern RE_PEAK_DESCRIPTION = Pattern.compile(String.format(
//            "(\\d*M(?:\\+|-)(\\d*)(\\w+)(?:\\+\\[[\\w\\+\\-]+?\\])?(?:\\+\\d*)?)",
//            GRP_MOL_IDENTITY));
    public static Pattern RE_PEAK_DESCRIPTION = Pattern.compile(String.format(
            "(?<%1$s>(?<%2$s>\\d*)M(?<%3$s>\\+|-)(?<%4$s>\\d*)(?<%5$s>\\w+)(?:\\+(?<%6$s>\\[[\\w\\+\\-]+?\\]))?(?:\\+(?<%$7s>\\d*))?)",
            GRP_MOL_IDENTITY, GRP_M_COUNT, GRP_Z_SIGN, GRP_Z_COUNT, GRP_Z_CARRIER, GRP_ADDUCT, GRP_ISOTOPE_NUM));

    protected double rt;
    protected double mz;
    protected double abMax;
    protected double abTot;
    protected int z = CHARGE_UNKNOWN;
    protected String ionDescription = "";

    public double getRt() {
        return rt;
    }

    public void setRt(double rt) {
        this.rt = rt;
    }

    public double getMz() {
        return mz;
    }

    public void setMz(double mz) {
        this.mz = mz;
    }

    public double getAbMax() {
        return abMax;
    }

    public void setAbMax(double abMax) {
        this.abMax = abMax;
    }

    public double getAbTot() {
        return abTot;
    }

    public void setAbTot(double abTot) {
        this.abTot = abTot;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getIonDescription() {
        return ionDescription;
    }

    public void setIonDescription(String ionDescription) {
        this.ionDescription = ionDescription;
    }

    public Matcher parseIonSignature() {
        Matcher m = RE_PEAK_DESCRIPTION.matcher(ionDescription);
        if (m.find()) {
            return m;
        }
        return null;
    }
}
