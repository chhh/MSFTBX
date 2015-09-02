/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.util;

import umich.ms.datatypes.spectrum.ISpectrum;

/**
 * Utilities for working with {@link ISpectrum} objects.
 * @author Dmitry Avtonomov
 */
public class SpectrumUtils {
    private SpectrumUtils() {}

    /**
     * Given an m/z value, calculates what a certain ppm accuracy converts to in terms of m/z.
     * @param mz base m/z at which the calculation is done
     * @param ppmDelta ppm difference, which is to be converted to amu.
     * @return
     */
    public static double ppm2amu(double mz, double ppmDelta) {
        return (mz / 1e6) * ppmDelta;
    }

    /**
     * Given an absolute m/z difference ({@code amu} parameter), find out how much ppm that is.
     * @param mz base m/z at which the calculation is done.
     * @param amuDelta absolute m/z difference, which is to be converted to ppm.
     * @return
     */
    public static double amu2ppm(double mz, double amuDelta) {
        return amuDelta / (mz / 1e6);
    }

    /**
     * Check if the 2nd m/z value is within some PPM distance from the 1st one. PPM will be calculated based on the 1st
     * m/z value.
     * @param mz1 PPM tolerance will be calculated relative to this value
     * @param mz2 the value to check for being within some PPM range
     * @param ppm
     * @return
     */
    public static boolean isWithinPpm(double mz1, double mz2, double ppm) {
        return Math.abs(amu2ppm(mz1, mz1 - mz2)) <= ppm;
    }
}
