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


public class DiaFrameMsMsInfo {

  private long frame;
  private long windowGroup;


  public long getFrame() {
    return frame;
  }

  public void setFrame(long frame) {
    this.frame = frame;
  }


  public long getWindowGroup() {
    return windowGroup;
  }

  public void setWindowGroup(long windowGroup) {
    this.windowGroup = windowGroup;
  }

}