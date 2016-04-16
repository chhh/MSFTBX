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
package umich.ms.datatypes.spectrum;

import java.io.Serializable;
import java.util.Arrays;
import umich.ms.util.SpectrumUtils;

/**
 * Author: Dmitry Avtonomov (dmitriya)
 * Email: dmitriy.avtonomov@gmail.com
 * Date: 3/1/13
 * Time: 2:04 PM
 */
public abstract class AbstractSpectrum implements ISpectrum, Serializable {
    private static final long serialVersionUID = 4857567189693575413L;

    protected double minMZ;
    protected double maxMZ;
    protected double minInt;
    protected double minIntNonZero;
    protected double maxInt;
    protected double maxIntMz;
    protected double sumInt;

    @Override
    public double getMinMZ() {
        return minMZ;
    }

    @Override
    public double getMaxMZ() {
        return maxMZ;
    }

    @Override
    public double getMinInt() {
        return minInt;
    }

    @Override
    public double getMinIntNonZero() {
        return minIntNonZero;
    }

    @Override
    public double getMaxInt() {
        return maxInt;
    }

    @Override
    public double getMaxIntMz() {
        return maxIntMz;
    }

    @Override
    public double getSumInt() {
        return sumInt;
    }

    protected void setMinMZ(double minMZ) {
        this.minMZ = minMZ;
    }

    protected void setMaxMZ(double maxMZ) {
        this.maxMZ = maxMZ;
    }

    protected void setMinInt(double minInt) {
        this.minInt = minInt;
    }

    public void setMinIntNonZero(double minIntNonZero) {
        this.minIntNonZero = minIntNonZero;
    }

    protected void setMaxInt(double maxInt) {
        this.maxInt = maxInt;
    }

    public void setMaxIntMz(double maxIntMz) {
        this.maxIntMz = maxIntMz;
    }

    protected void setSumInt(double sumInt) {
        this.sumInt = sumInt;
    }

    @Override
    public Integer findMzIdxCeiling(double mz) {
        double[] mzs = getMZs();
        int pos = Arrays.binarySearch(mzs, mz);
        if (pos >= 0) return pos;
        //if we get here, it means, that [pos = (-(insertion point) - 1)]
        //pos = -(pos + 1);
        pos = ~pos;
        if (pos == mzs.length) return null;
        return pos;

    }

    @Override
    public Integer findMzIdxFloor(double mz) {
        double[] mzs = getMZs();
        int pos = Arrays.binarySearch(mzs, mz);
        if (pos >= 0) return pos;
        //if we get here, it means, that [pos = (-(insertion point) - 1)]
        //pos = -(pos + 1);
        pos = ~pos;
        if (pos == 0) return null;
        return pos - 1;
    }

    @Override
    public Integer findClosestMzIdx(double mz) {
        double[] mzs = getMZs();
        int pos = Arrays.binarySearch(mzs, mz);
        if (pos >= 0) return pos;
        //if we get here, it means, that [pos = (-(insertion point) - 1)]
        //pos = -(pos + 1);
        pos = ~pos;
        if (pos == 0) return pos;
        if (pos == mzs.length) return pos - 1;
        return (mz - mzs[pos-1]) <= (mzs[pos] - mz) ? pos - 1 : pos;
    }

    @Override
    public int[] findMzIdxsWithinPpm(double mz, double ppm) {
        double mzTolerance = SpectrumUtils.ppm2amu(mz, ppm);
        return findMzIdxs(mz - mzTolerance, mz + mzTolerance);
    }
}
