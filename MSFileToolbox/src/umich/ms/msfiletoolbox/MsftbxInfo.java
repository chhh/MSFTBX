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

import java.util.*;

/**
 * @author Dmitry Avtonomov
 */
public class MsftbxInfo {
    public static final String version = "1.2.1";

    private static TreeMap<String, List<String>> changelog = new TreeMap<>();
    private static Map<String, List<String>> changelogImmutable = Collections.unmodifiableMap(changelog);

    static {
        String v120 = "v1.2.0";
        List<String> v120notes = new LinkedList<>();
        v120notes.add("Added fragmentation energy and reaction time");
        changelog.put(v120, v120notes);

        String v121 = "v1.2.1";
        List<String> v121notes = new LinkedList<>();
        v121notes.add("Mavenized, published to Maven Central");
        changelog.put(v121, v121notes);
    }

    public static String getVersion() {
        return version;
    }

    public static Map<String, List<String>> getChangelog() {
        return changelogImmutable;
    }
}
