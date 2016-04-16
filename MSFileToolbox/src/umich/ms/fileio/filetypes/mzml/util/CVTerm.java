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
 * Just the most generic representation of a CV term.
 * Created by dmitriya on 2015-03-30.
 */
public class CVTerm {
    public final String accession;
    public final String name;

    public CVTerm(String accession, String name) {
        this.accession = accession;
        this.name = name;
    }
}
