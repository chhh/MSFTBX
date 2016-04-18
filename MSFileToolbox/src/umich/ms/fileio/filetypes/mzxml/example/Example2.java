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

package umich.ms.fileio.filetypes.mzxml.example;

import umich.ms.datatypes.scan.IScan;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.mzxml.MZXMLFile;
import umich.ms.fileio.filetypes.mzxml.MZXMLIndex;
import umich.ms.fileio.filetypes.mzxml.MZXMLIndexElement;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NavigableMap;

/**
 * Created by Dmitry Avtonomov on 2016-04-18.
 */
public class Example2 {
    public static void main(String[] args) throws FileParsingException {

        // Creating data source
        Path path = Paths.get("some-path-to.mzXML");
        path = Paths.get(args[0]);
        MZXMLFile source = new MZXMLFile(path.toString());

        // Notice that we use fetchIndex() instead of getIndex().
        // fetchIndex() will either get a cached copy or parse it from
        // disk, if no cache is available. The index will be cached after parsing.
        MZXMLIndex index = source.fetchIndex();

        // The index gives you the scan numbers, on the lowest level you can parse
        // the file using those numbers. We need the raw scan numbers (the numbers
        // as they're used in the file). The internal scan numbering scheme always
        // renumbers all scans starting from 1 and increasing by 1 consecutively.

        for (Integer scanNumRaw : index.getMapByRawNum().keySet()) {
            // The second parameter asks the parser to parse the spectrum along
            // with meta-info about the scan itself
            IScan scan = source.parseScan(scanNumRaw, true);

            // Do something with the scan.
            // Note that some features, like scan.getChildScans() will not work in
            // this case, as there is not enough information to build those
            // relationships.

            System.out.println(scan.toString());
        }
    }
}
