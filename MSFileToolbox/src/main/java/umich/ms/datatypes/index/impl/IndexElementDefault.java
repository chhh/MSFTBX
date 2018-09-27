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
package umich.ms.datatypes.index.impl;

import umich.ms.datatypes.index.IndexElement;

/**
 * @author Dmitry Avtonomov
 */
public class IndexElementDefault implements IndexElement {

  private static final long serialVersionUID = -6425013462440420720L;

  /**
   * Internal scan number.
   */
  protected final int num;
  /**
   * Raw scan number from raw file.
   */
  protected final int numRaw;

  public IndexElementDefault(int num, int numRaw) {
    this.num = num;
    this.numRaw = numRaw;
  }

  @Override
  public int getNumber() {
    return num;
  }

  @Override
  public int getRawNumber() {
    return numRaw;
  }

  @Override
  public String getId() {
    return String.valueOf(numRaw);
  }

  @Override
  public String toString() {
    return "Num: " + num + " RawNum: " + numRaw + " ID: " + getId();
  }
}
