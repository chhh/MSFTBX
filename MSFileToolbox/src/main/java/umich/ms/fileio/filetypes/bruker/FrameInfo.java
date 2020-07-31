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

package umich.ms.fileio.filetypes.bruker;

import umich.ms.fileio.filetypes.bruker.dao.Frame;

public class FrameInfo {
  private final long frameId;
  private final double rtInSec;
  private final long scanMode;
  private final long msMsType;

  public FrameInfo(long frameId, double rtInSec, long scanMode, long msMsType) {
    this.frameId = frameId;
    this.rtInSec = rtInSec;
    this.scanMode = scanMode;
    this.msMsType = msMsType;
  }

  public long getFrameId() {
    return frameId;
  }

  public long getScanMode() {
    return scanMode;
  }

  public long getMsMsType() {
    return msMsType;
  }

  public double getRtInSeconds() {
    return rtInSec;
  }

  public int getMsLevel() {
    return msMsType == 0 ? 1 : 2;
  }

  public static FrameInfo from (Frame f) {
    return new FrameInfo(f.getId(), f.getTime(), f.getScanMode(), f.getMsMsType());
  }
}
