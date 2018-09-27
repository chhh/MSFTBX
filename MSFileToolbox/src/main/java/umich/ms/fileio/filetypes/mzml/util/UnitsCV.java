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

import java.util.concurrent.TimeUnit;

/**
 * Used to convert retention times in mzML files.
 *
 * @author Dmitry Avtonomov
 */
public enum UnitsCV {

  UO_NANOSECONDS("UO:0000150", "nanosecond", TimeUnit.NANOSECONDS),
  UO_MICROSECONDS("UO:0000029", "microsecond", TimeUnit.MICROSECONDS),
  UO_MILLISECONDS("UO:0000028", "millisecond", TimeUnit.MILLISECONDS),
  UO_SECONDS("UO:0000010", "second", TimeUnit.SECONDS),
  UO_MINUTES("UO:0000031", "minute", TimeUnit.MINUTES),
  UO_HOURS("UO:0000032", "hour", TimeUnit.HOURS);
  public final String accession;
  public final String name;
  public final TimeUnit timeUnit;

  UnitsCV(String accession, String name, TimeUnit timeUnit) {
    this.accession = accession;
    this.name = name;
    this.timeUnit = timeUnit;
  }

  /**
   * Converts mzML representation of time into minutes.
   */
  public static double inMinutes(String uoAccession, String value) {
    double val = Double.parseDouble(value);

    if (UO_SECONDS.accession.equals(uoAccession)) {
      return val / 60d;
    } else if (UO_MINUTES.accession.equals(uoAccession)) {
      return val;
    } else if (UO_MILLISECONDS.accession.equals(uoAccession)) {
      return val / 1e3d / 60d;
    } else if (UO_MICROSECONDS.accession.equals(uoAccession)) {
      return val / 1e6d / 60d;
    } else if (UO_NANOSECONDS.accession.equals(uoAccession)) {
      return val / 1e9d / 60d;
    } else if (UO_HOURS.accession.equals(uoAccession)) {
      return val * 60d;
    }

    throw new IllegalArgumentException(
        "Unsupported ontology ref for time units encountered: " + uoAccession);
  }
}
