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

package umich.ms.fileio.filetypes.mzidentml.example;

import com.google.common.base.Stopwatch;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.mzidentml.MzIdentMLParser;
import umich.ms.fileio.filetypes.mzidentml.jaxb.standard.AbstractParamType;
import umich.ms.fileio.filetypes.mzidentml.jaxb.standard.MzIdentMLType;
import umich.ms.fileio.filetypes.mzidentml.jaxb.standard.PeptideEvidenceType;
import umich.ms.fileio.filetypes.mzidentml.jaxb.standard.UserParamType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Dmitry Avtonomov
 */
public class MzIdentMlReadWriteExample {
    public static void main(String[] args) throws FileParsingException, XMLStreamException, JAXBException, IOException {

        if (args.length < 1)
            throw new IllegalArgumentException("You must specify the filepath using command line arguments");
        Path pathIn = Paths.get(args[0]).toAbsolutePath();
        if (!Files.exists(pathIn))
            throw new FileNotFoundException("Input file does not exist: " + pathIn.toString());

        System.out.println("Will inject values into: " + pathIn.toString());

        final Path file = pathIn.getFileName();
        final Path dir = pathIn.getParent();
        final Stopwatch timer = Stopwatch.createUnstarted();

        // READ
        double sizeInMb = pathIn.toFile().length() / (1024.0 * 1024.0);
        System.out.printf("Parsing file (%.2fMB)\n", sizeInMb);
        timer.reset().start();
        MzIdentMLType mzid = MzIdentMLParser.parse(pathIn);
        timer.stop();
        double elapsedSec = timer.elapsed(TimeUnit.MILLISECONDS)/1000.0;
        System.out.printf("Done reading in (%.2fs, %.2fMB/s).\n", elapsedSec, sizeInMb/elapsedSec);



        // MODIFY
        // we will modify the list of peptide evidence
        timer.reset().start();
        final List<PeptideEvidenceType> pepEvidence = mzid.getSequenceCollection().getPeptideEvidence();
        for (PeptideEvidenceType psm : pepEvidence) {
            final List<AbstractParamType> paramGroup = psm.getParamGroup(); // cvParams and userParams are stored here

            // we will inject a userParam
            final UserParamType u = new UserParamType();
            u.setName("myName");
            u.setType("myType");
            u.setUnitAccession("myUnitAccession");
            u.setUnitName("myUnitName");
            u.setValue("myValue");

            // inject
            paramGroup.add(u);
        }
        timer.stop();
        System.out.printf("Done modifying (%.2f).\n", timer.elapsed(TimeUnit.MILLISECONDS)/1000.0);


        // WRITE
        JAXBContext jaxbContext = JAXBContext.newInstance(MzIdentMLType.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        String fileOut = file + ".out.ignore";
        Path pathOut = dir.resolve(fileOut);
        Files.createDirectories(pathOut.getParent());
        timer.reset().start();
        marshaller.marshal(mzid, pathOut.toFile());
        timer.stop();
        System.out.printf("Done writing output (%.2fs).\n", timer.elapsed(TimeUnit.MILLISECONDS)/1000.0);


        // DELETE the created file when JVM stops
        //pathOut.toFile().deleteOnExit();
    }
}
