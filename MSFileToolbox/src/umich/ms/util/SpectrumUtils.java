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
     * @param ppm
     * @return
     */
    public static double ppm2amu(double mz, double ppm) {
        return (mz / 1e6) * ppm;
    }

    /**
     * Given an absolute m/z difference ({@code amu} parameter), find out how much ppm that is.
     * @param mz base m/z at which the calculation is done.
     * @param amu absolute m/z difference, which is to be converted to ppm.
     * @return
     */
    public static double amu2ppm(double mz, double amu) {
        return amu / (mz / 1e6);
    }
}
