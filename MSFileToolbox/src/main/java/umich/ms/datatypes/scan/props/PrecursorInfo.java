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
import java.util.List;

import com.dmtavt.batmass.io.ms.api.IsolationRange;
import umich.ms.util.DoubleRange;

/**
 * Author: Dmitry Avtonomov (dmitriya)
 */
public class PrecursorInfo implements Serializable {
  public static class IsolationRange2D {
    public final double mzLo;
    public final double mzHi;
    public final double imLo;
    public final double imHi;

    public IsolationRange2D(double mzLo, double mzHi, double imLo, double imHi) {
      this.mzLo = mzLo;
      this.mzHi = mzHi;
      this.imLo = imLo;
      this.imHi = imHi;
    }
  }

  /**
   * This is the internal scan number, it will only be non-null, if the parent scan itself is still
   * in the mzML/mzXML file.
   */
  private Integer parentScanNum;
  /**
   * This is the raw parent scan reference (a number or a real string ref, like in mzML files).
   * ProteoWizard leaves in this meta-data even if the parent scan itself is not present in the file
   * (e.g. it was not included during conversion with ProteoWizard).
   */
  private String parentScanRefRaw;
  private Double isolationWindowMzLo;
  private Double isolationWindowMzHi;
  private Double mzTarget;
  private Double mzTargetMono;
  private Integer charge;
  private ActivationInfo activationInfo;
  private Double intensity;
  private List<IsolationRange2D> isolationRanges;
  private Integer precursorMsLevel = null;

  public PrecursorInfo() {
    activationInfo = new ActivationInfo();
  }

  public List<IsolationRange2D> getIsolationRanges() {
    return isolationRanges;
  }

  public void setIsolationRanges(List<IsolationRange2D> isolationRanges) {
    this.isolationRanges = isolationRanges;
  }

  public String getParentScanRefRaw() {
    return parentScanRefRaw;
  }

  public void setParentScanRefRaw(String parentScanRefRaw) {
    this.parentScanRefRaw = parentScanRefRaw;
  }

  /**
   * Every scan can have a parent, if msLevel is &gt; 1 type is Integer and not int, so we could use
   * null for MS1 scans
   */
  public Integer getParentScanNum() {
    return parentScanNum;
  }

  public void setParentScanNum(Integer parentScanNum) {
    this.parentScanNum = parentScanNum;
  }

  /**
   * In case of DDA runs, the m/z value for the actual peak that triggered fragmentation. For DIA
   * runs doesn't really mean anything, will likely be the center of the selection window.
   */
  public Double getMzTarget() {
    return mzTarget;
  }

  public void setMzTarget(Double mzTarget) {
    this.mzTarget = mzTarget;
  }

  /**
   * The assumed monoisotopic m/z of the selected ion. Oftentimes will be almost exactly the same as
   * as 'mzTarget', however, if the instrument makes a mistake or just the most intense ion is not
   * the monoisotope, as is the case with larger peptides, then this value will be different from
   * 'mzTarget'.
   *
   * @return Null in case this value was not reported or in case it was reported as zero.
   */
  public Double getMzTargetMono() {
    return mzTargetMono;
  }

  public void setMzTargetMono(Double mzTargetMono) {
    this.mzTargetMono = mzTargetMono;
  }

  /**
   * Precursor charge is optional in mzXML/mzML, so it can be null.
   */
  public Integer getCharge() {
    return charge;
  }

  public void setCharge(Integer charge) {
    this.charge = charge;
  }

  /**
   * The beginning of isolation window mz range
   */
  public Double getMzRangeStart() {
    return isolationWindowMzLo;
  }

  public void setMzRangeStart(Double mzRangeStart) {
    this.isolationWindowMzLo = mzRangeStart;
  }

  /**
   * The end of isolation window mz range
   */
  public Double getMzRangeEnd() {
    return isolationWindowMzHi;
  }

  public void setMzRangeEnd(Double mzRangeEnd) {
    this.isolationWindowMzHi = mzRangeEnd;
  }

  /**
   * TODO: might be better to treat null values as infinity and always return non-null DoubleRange.
   * Convenience method to get the precursor isolation window ange.
   *
   * @return null, if at least one range end is null (not set). Otherwise a new instance of {@link
   * umich.ms.util.DoubleRange}
   */
  public DoubleRange getMzRange() {
    if (isolationWindowMzLo != null && isolationWindowMzHi != null) {
      return new DoubleRange(isolationWindowMzLo, isolationWindowMzHi);
    }
    return null;
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

  public Integer getPrecursorMsLevel() {
    return precursorMsLevel;
  }

  public void setPrecursorMsLevel(Integer precursorMsLevel) {
    this.precursorMsLevel = precursorMsLevel;
  }
}
