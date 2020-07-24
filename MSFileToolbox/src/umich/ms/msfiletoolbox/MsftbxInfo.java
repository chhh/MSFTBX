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

package umich.ms.msfiletoolbox;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Dmitry Avtonomov
 */
public class MsftbxInfo {

  public static final String version = "1.8.7";

  private static final TreeMap<String, List<String>> changelog = new TreeMap<>();
  private static final Map<String, List<String>> changelogImmutable = Collections
      .unmodifiableMap(changelog);

  static {

    changelog.put("v1.8.7", Arrays.asList(
            "Add ion mobility binary data array to mzML."
    ));

    changelog.put("v1.8.6", Arrays.asList(
        "Force include JAXB API implementation from Glassfish.",
        "Remove unsued Thermo RAW experiments."
    ));

    changelog.put("v1.8.5", Arrays.asList(
        "Update dependencies. Update build systems."
    ));

    changelog.put("v1.8.4", Arrays.asList(
        "Gradle build. Fix rt2scan index building bug when several scans had the same RT."
    ));

    changelog.put("v1.8.3", Arrays.asList(
        "Minor updates to Agilent CEF parsing. Added AgilentCefParser class for simplicity."
    ));

    changelog.put("v1.8.2", Arrays.asList(
        "Include JAXB dependency that's required to run with Java 9. Jaxb is removed from the Java SE "
            +
            "SDK and moved into Java EE. For future-proofing, including the jaxb-api dependency. " +
            "Other jaxb stuff seems to be unnecessary."
    ));

    String v180 = "v1.8.0";
    List<String> v180notes = new LinkedList<>();
    v180notes.add(
        "PepXml, ProtXml, MzIdentMl parsers updated to use Doubles instead of Floats everywhere. This "
            +
            "will break old code that used some of the values as floats.");
    changelog.put(v180, v180notes);

    String v170 = "v1.7.0";
    List<String> v170notes = new LinkedList<>();
    v170notes.add(
        "Complete overhaul of how run header is parsed for mzML and mzXML. Should now be completely "
            +
            "tolerant to character encoding issues, e.g. full set of UTF-8 chars is supported.");
    changelog.put(v170, v170notes);

    String v161 = "v1.6.1";
    List<String> v161notes = new LinkedList<>();
    v161notes
        .add("Minor changes in XmlUtils and an example of unmarshalling 'peptideprophet_summary'," +
            " which is not a part of standard unmarshalled data object (because of a flaw in pepxml schema).");
    changelog.put(v161, v161notes);

    String v160 = "v1.6.0";
    List<String> v160notes = new LinkedList<>();
    v160notes.add(
        "Added @XmlRootElement annotation to MzIdentMlType JAXB class to enable proper marshalling.");
    v160notes.add(
        "Changes to NameValueType in PepXml definition. Was causing some strange error on some systems "
            +
            "about incorrect annotations.");
    changelog.put(v160, v160notes);

    String v150 = "v1.5.0";
    List<String> v150notes = new LinkedList<>();
    v150notes
        .add("Changed how precursor ions are reported. PrecursorInfo now provides `mzTarget` " +
            "as the actual targeted m/z, and also `mzTargetMono`, which is the assumed " +
            "monoisotopic m/z of that ion.");
    changelog.put(v150, v150notes);

    String v140 = "v1.4.0";
    List<String> v140notes = new LinkedList<>();
    v140notes.add(
        "Fixed regression bug in mzXML header parsing. Original files without parent location were causing NPEs.");
    changelog.put(v140, v140notes);

    String v131 = "v1.3.1";
    List<String> v131notes = new LinkedList<>();
    v131notes.add(
        "PepXML parser updated to pepxml schema v120. Includes PTM Prophet and related changes.");
    changelog.put(v131, v131notes);

    String v130 = "v1.3.0";
    List<String> v130notes = new LinkedList<>();
    v130notes.add("Multiple improvements to mzML parsing.");
    v130notes.add("Support for ReferenceableParamGroups in mzML.");
    v130notes.add("Significant non-indexed files parsing speed up.");
    changelog.put(v130, v130notes);

    String v123 = "v1.2.3";
    List<String> v123notes = new LinkedList<>();
    v123notes.add("Add test resources path to Maven POM.");
    v123notes.add(
        "If anything goes wrong during index parsing, tries to re-index the file instead of throwing exceptions.");
    v123notes.add("Support for ReferenceableParamGroups in mzML.");
    v123notes.add("Fix BOM for mzML/mzXML files.");
    v123notes.add("Change deps to Javolution-MSFTBX v6.11.7");
    v123notes.add("Use CharArray.contentEquals() in mzML parser");
    changelog.put(v123, v123notes);

    String v121 = "v1.2.1";
    List<String> v121notes = new LinkedList<>();
    v121notes.add("Mavenized, published to Maven Central");
    changelog.put(v121, v121notes);

    String v120 = "v1.2.0";
    List<String> v120notes = new LinkedList<>();
    v120notes.add("Added fragmentation energy and reaction time");
    changelog.put(v120, v120notes);
  }

  public static String getVersion() {
    return version;
  }

  public static Map<String, List<String>> getChangelog() {
    return changelogImmutable;
  }
}
