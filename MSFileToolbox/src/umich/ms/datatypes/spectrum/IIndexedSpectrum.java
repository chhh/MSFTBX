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

/**
 * This class represents a spectrumRef stored in some indexed collection (fast random access).
 * That is individual mz/intensity pairs can be addressed by an integer index.
 *
 * Author: Dmitry Avtonomov (dmitriya)
 * Email: dmitriy.avtonomov@gmail.com
 * Date: 3/1/13
 * Time: 2:15 PM
 */
@Deprecated
public interface IIndexedSpectrum {
    public double getMz(int idx);
    public double getIntensity(int idx);
    public void setMz(int idx, double mz);
    public void setIntensity(int idx, double intensity);
    /**
     * Get one datapoint from the array
     * @param idx
     * @return double[2] array. [0] - mz, [1] - intensity
     */
    public double[] getMzIntPair(int idx);
    public void setMzIntPair(int idx, double mz, double intensity);
}
