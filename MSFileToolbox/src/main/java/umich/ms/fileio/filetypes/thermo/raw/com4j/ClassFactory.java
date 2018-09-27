/*
 * Copyright (c) 2016 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.thermo.raw.com4j;

import com4j.COM4J;

/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {

  private ClassFactory() {
  } // instanciation is not allowed


  /**
   * MSFileReader XRawfile Class
   */
  public static umich.ms.fileio.filetypes.thermo.raw.com4j.IXRawfile createMSFileReader_XRawfile() {
    return COM4J.createInstance(umich.ms.fileio.filetypes.thermo.raw.com4j.IXRawfile.class,
        "{1D23188D-53FE-4C25-B032-DC70ACDBDC02}");
  }

  /**
   * MSFileReader XVirMS Class
   */
  public static umich.ms.fileio.filetypes.thermo.raw.com4j.IXVirMS createMSFileReader_XVirMS() {
    return COM4J.createInstance(umich.ms.fileio.filetypes.thermo.raw.com4j.IXVirMS.class,
        "{6F2F5BD9-1C11-4CBC-AF0D-6DE0E3B06E3B}");
  }

  /**
   * MSFileReader XVirUV Class
   */
  public static umich.ms.fileio.filetypes.thermo.raw.com4j.IXVirUV createMSFileReader_XVirUV() {
    return COM4J.createInstance(umich.ms.fileio.filetypes.thermo.raw.com4j.IXVirUV.class,
        "{B3DB6431-A183-43F1-8BBD-F354064D9041}");
  }
}
