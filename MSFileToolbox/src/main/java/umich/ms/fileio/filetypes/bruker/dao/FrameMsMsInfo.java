/*
 * Copyright (c) 2019 Dmitry Avtonomov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package umich.ms.fileio.filetypes.bruker.dao;


public class FrameMsMsInfo {

  private long frame;
  private long parent;
  private double triggerMass;
  private double isolationWidth;
  private long precursorCharge;
  private double collisionEnergy;


  public long getFrame() {
    return frame;
  }

  public void setFrame(long frame) {
    this.frame = frame;
  }


  public long getParent() {
    return parent;
  }

  public void setParent(long parent) {
    this.parent = parent;
  }


  public double getTriggerMass() {
    return triggerMass;
  }

  public void setTriggerMass(double triggerMass) {
    this.triggerMass = triggerMass;
  }


  public double getIsolationWidth() {
    return isolationWidth;
  }

  public void setIsolationWidth(double isolationWidth) {
    this.isolationWidth = isolationWidth;
  }


  public long getPrecursorCharge() {
    return precursorCharge;
  }

  public void setPrecursorCharge(long precursorCharge) {
    this.precursorCharge = precursorCharge;
  }


  public double getCollisionEnergy() {
    return collisionEnergy;
  }

  public void setCollisionEnergy(double collisionEnergy) {
    this.collisionEnergy = collisionEnergy;
  }

}
