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


public class DiaFrameMsMsWindow {

  private long windowGroup;
  private long scanNumBegin;
  private long scanNumEnd;
  private double isolationMz;
  private double isolationWidth;
  private double collisionEnergy;


  public long getWindowGroup() {
    return windowGroup;
  }

  public void setWindowGroup(long windowGroup) {
    this.windowGroup = windowGroup;
  }


  public long getScanNumBegin() {
    return scanNumBegin;
  }

  public void setScanNumBegin(long scanNumBegin) {
    this.scanNumBegin = scanNumBegin;
  }


  public long getScanNumEnd() {
    return scanNumEnd;
  }

  public void setScanNumEnd(long scanNumEnd) {
    this.scanNumEnd = scanNumEnd;
  }


  public double getIsolationMz() {
    return isolationMz;
  }

  public void setIsolationMz(double isolationMz) {
    this.isolationMz = isolationMz;
  }


  public double getIsolationWidth() {
    return isolationWidth;
  }

  public void setIsolationWidth(double isolationWidth) {
    this.isolationWidth = isolationWidth;
  }


  public double getCollisionEnergy() {
    return collisionEnergy;
  }

  public void setCollisionEnergy(double collisionEnergy) {
    this.collisionEnergy = collisionEnergy;
  }

}
