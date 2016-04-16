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
package umich.ms.datatypes.scan;

/**
 * Peaks (m/z and intensity) compression methods used in mzML and mzXML files.
 * @author Dmitry Avtonomov
 */
public enum PeaksCompression {

    /** No compression. */
    NONE,
    /** Zlib compression. */
    ZLIB,
    /** MSNumPress compression - linear prediction. Only supported by mzML. */
    NUMPRESS_LINPRED,
    /** MSNumPress compression - positive integer. Only supported by mzML. */
    NUMPRESS_POSINT,
    /** MSNumPress compression - short logged float. Only supported by mzML. */
    NUMPRESS_SHLOGF
}
