package umich.ms.fileio.filetypes.mzidentml.example;

import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.mzidentml.MzIdentMLParser;
import umich.ms.fileio.filetypes.mzidentml.jaxb.standard.MzIdentMLType;
import umich.ms.fileio.filetypes.mzidentml.jaxb.standard.SpectrumIdentificationItemType;
import umich.ms.fileio.filetypes.mzidentml.jaxb.standard.SpectrumIdentificationListType;
import umich.ms.fileio.filetypes.mzidentml.jaxb.standard.SpectrumIdentificationResultType;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * As simple as it gets. The parsed structure though does not provide all the interlinking of elements,
 * you'll have to do that manually.
 *
 * Created by Dmitry Avtonomov on 2016-04-12.
 */
public class MzIdentMlExample {

    public static void main(String[] args) throws FileParsingException, XMLStreamException, JAXBException {

        if (args.length < 1)
            throw new IllegalArgumentException("You must specify the filepath using command line arguments");
        Path path = Paths.get(args[0]);


        // a single call to parse the whole file
        MzIdentMLType mzid = MzIdentMLParser.parse(path);


        List<SpectrumIdentificationListType> spectrumIdentificationLists = mzid.getDataCollection().getAnalysisData().getSpectrumIdentificationList();
        for (SpectrumIdentificationListType sil : spectrumIdentificationLists) {
            System.out.printf("Processing spec id list: %s, number of sequences searched: %s\n", sil.getName(), sil.getNumSequencesSearched());
            List<SpectrumIdentificationResultType> spectrumIdentificationResults = sil.getSpectrumIdentificationResult();
            for (SpectrumIdentificationResultType sir : spectrumIdentificationResults) {
                List<SpectrumIdentificationItemType> spectrumIdentificationItems = sir.getSpectrumIdentificationItem();
                for (SpectrumIdentificationItemType si : spectrumIdentificationItems) {
                    System.out.printf("Spectrum ID: %s, calc m/z: %.3f, exp m/z: %.3f\n",
                                      si.getId(), si.getCalculatedMassToCharge(), si.getExperimentalMassToCharge());
                }
            }
            System.out.printf("Done processing spec id list: %s\n", sil.getName());
        }


        System.out.println();

    }
}
