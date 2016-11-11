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
package umich.ms.datatypes.scan.props;

import java.io.Serializable;
import umich.ms.util.DoubleRange;

/**
 * Author: Dmitry Avtonomov (dmitriya)
 * Email: dmitriy.avtonomov@gmail.com
 * Date: 3/26/13
 * Time: 4:00 PM
 */
public class PrecursorInfo implements Serializable {

    /** This is the internal scan number, it will only be non-null, if the parent scan itself is still in the mzML/mzXML file. */
    private Integer parentScanNum;
    /**
     * This is the raw parent scan reference (a number or a real string ref, like in mzML files).
     * ProteoWizard leaves in this meta-data even if the parent scan itself is
     * not present in the file (e.g. it was not included during conversion with ProteoWizard).
     */
    private String parentScanRefRaw;
    private Double mzRangeStart;
    private Double mzRangeEnd;
    private Double mzTarget;
    private Integer charge;
    private ActivationInfo activationInfo;
    private Double intensity;

    public PrecursorInfo() {
        activationInfo = new ActivationInfo();
    }

    public String getParentScanRefRaw() {
        return parentScanRefRaw;
    }

    public void setParentScanRefRaw(String parentScanRefRaw) {
        this.parentScanRefRaw = parentScanRefRaw;
    }

    /**
     * Every scan can have a parent, if msLevel is > 1
     * type is Integer and not int, so we could use null for MS1 scans
     * @return
     */
    public Integer getParentScanNum() {
        return parentScanNum;
    }

    public void setParentScanNum(Integer parentScanNum) {
        this.parentScanNum = parentScanNum;
    }

    /**
     * m/z of the precursor that was targeted.
     * @return
     */
    public Double getMzTarget() {
        return mzTarget;
    }

    public void setMzTarget(Double mzTarget) {
        this.mzTarget = mzTarget;
    }

    /** Precursor charge is optional in mzXML/mzML, so it can be null. */
    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    /**
     * The beginning of isolation window mz range
     * @return
     */
    public Double getMzRangeStart() {
        return mzRangeStart;
    }

    public void setMzRangeStart(Double mzRangeStart) {
        this.mzRangeStart = mzRangeStart;
    }

    /**
     * The end of isolation window mz range
     * @return
     */
    public Double getMzRangeEnd() {
        return mzRangeEnd;
    }

    /**
     * TODO: might be better to treat null values as infinity and always return non-null DoubleRange.
     * Convenience method to get the precursor range.
     * @return null, if at least one range end is null (not set). Otherwise a new instance of
     * {@link umich.ms.util.DoubleRange}
     */
    public DoubleRange getMzRange() {
        if (mzRangeStart != null && mzRangeEnd != null) {
            return new DoubleRange(mzRangeStart, mzRangeEnd);
        }
        return null;
    }

    public void setMzRangeEnd(Double mzRangeEnd) {
        this.mzRangeEnd = mzRangeEnd;
    }

    public ActivationInfo getActivationInfo() {
        return activationInfo;
    }

    public void setActivationInfo(ActivationInfo activationInfo) {
        this.activationInfo = activationInfo;
    }

    public Double getIntensity() {
        return intensity;
    }

    public void setIntensity(Double intensity) {
        this.intensity = intensity;
    }
}
