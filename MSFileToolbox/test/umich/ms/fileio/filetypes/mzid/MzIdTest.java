/*
 * Copyright (c) 2017 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.mzid;

import org.junit.Assert;
import org.junit.Test;
import umich.ms.fileio.filetypes.mzidentml.MzIdentMLParser;
import umich.ms.fileio.filetypes.mzidentml.jaxb.standard.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MzIdTest {

    List<Path> paths;

    private Path getResource(String name) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        final URI uri = classLoader.getResource("mzid").toURI();
        final Path path = Paths.get(uri).toAbsolutePath();
        return Paths.get(path.toString(), name);
    }

    @org.junit.Before
    public void setUp() throws Exception {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            final URI uri = classLoader.getResource("mzid").toURI();
            final Path path = Paths.get(uri);

            final DirectoryStream<Path> stream = Files.newDirectoryStream(path);
            paths = new ArrayList<>();
            for (Path p : stream) {
                //if (Files.isRegularFile(p) && p.getFileName().toString().equals("mzidLib_rosetta_2a_uniprot_proteogrouped.mzid")) {
                if (Files.isRegularFile(p)) {
                    paths.add(p);
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException("Could not set up test env in MZMLFileTest");
        }
    }

    @org.junit.After
    public void tearDown() throws Exception {
        paths.clear();
        paths = null;
    }

    @Test
    public void testParsing() throws Exception {

        for (Path path : paths) {
            MzIdentMLType mzid = MzIdentMLParser.parse(path);

            Assert.assertFalse(String.format("Peptide list was empty for file '%s'", path), mzid.getSequenceCollection().getPeptide().isEmpty());
            Assert.assertFalse(String.format("Peptide Evidence list was empty for file '%s'", path), mzid.getSequenceCollection().getPeptideEvidence().isEmpty());


//            List<PeptideEvidenceType> peptideEvidence = mzid.getSequenceCollection().getPeptideEvidence();
//            for (PeptideEvidenceType pe : peptideEvidence) {
//                List<AbstractParamType> paramGroup = pe.getParamGroup();
//                for (AbstractParamType param : paramGroup) {
//                    if (param instanceof CVParamType) {
//                        CVParamType p = (CVParamType)param;
//                        // do something with cvParam
//                        int a = 1;
//                    } else if (param instanceof UserParamType) {
//                        UserParamType p = (UserParamType)param;
//                        // do something with userParam
//                        int a = 1;
//                    }
//                }
//            }
        }
    }

}
