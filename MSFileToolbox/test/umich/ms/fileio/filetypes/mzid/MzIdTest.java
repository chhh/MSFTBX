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
import umich.ms.fileio.ResourceUtils;
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

    @org.junit.Before
    public void setUp() throws Exception {
        paths = ResourceUtils.getResources(this.getClass(), "mzid");
    }

    @org.junit.After
    public void tearDown() throws Exception {
        paths.clear();
        paths = null;
    }

    @Test
    public void testParsing() throws Exception {

        for (Path path : paths) {
            System.out.printf("Test parsing ");
            MzIdentMLType mzid = MzIdentMLParser.parse(path);

            Assert.assertFalse(String.format("Peptide List was empty for file '%s'", path), mzid.getSequenceCollection().getPeptide().isEmpty());
            Assert.assertFalse(String.format("Peptide Evidence List was empty for file '%s'", path), mzid.getSequenceCollection().getPeptideEvidence().isEmpty());

            Assert.assertFalse(String.format("Spectrum Identification List was empty for file '%s'", path), mzid.getDataCollection().getAnalysisData().getSpectrumIdentificationList().isEmpty());

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
