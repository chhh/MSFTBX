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
package umich.ms.fileio.filetypes.mzml.util;

/**
 * @author Dmitry Avtonomov
 */
public class ActivationCVTerm extends CVTerm {

  public static final String UNKNOWN_METHOD = "Unknown";
  public final String description;
  public final String shortName;

  public ActivationCVTerm(String accession, String name, String description, String shortName) {
    super(accession, name);
    this.description = description;
    this.shortName = shortName;
  }

  public ActivationCVTerm(String accession, String name, String description) {
    super(accession, name);
    this.description = description;
    this.shortName = null;
  }

  public String getShortName() {
    return shortName != null ? shortName : name;
  }
}
