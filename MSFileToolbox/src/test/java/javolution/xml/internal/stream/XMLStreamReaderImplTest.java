package javolution.xml.internal.stream;

import javolution.xml.stream.XMLStreamConstants;
import javolution.xml.stream.XMLStreamException;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import static org.junit.Assert.*;

/**
 * @author Dmitry Avtonomov
 */
public class XMLStreamReaderImplTest {
    @Test
    public void setInput() throws Exception {
        String[] files = new String[]{
//        //final String s = "C:\\projects\\repos\\msdk\\msdk-io\\msdk-io-mzml2\\src\\test\\resources\\RawCentriodCidWithMsLevelInRefParamGroup.mzML";
//            /* final String isBom = */ "C:\\projects\\repos\\msdk\\msdk-io\\msdk-io-mzml2\\src\\test\\resources\\RawCentriodCidWithMsLevelInRefParamGroupFormatted.mzML",
//            /* final String noBom = */ "C:\\projects\\repos\\msdk\\msdk-io\\msdk-io-mzml2\\src\\test\\resources\\RawCentriodCidWithMsLevelInRefParamGroupFormattedNoBom.mzML",
//            /* final String noBom16 = */ "C:\\projects\\repos\\msdk\\msdk-io\\msdk-io-mzml2\\src\\test\\resources\\RawCentriodCidWithMsLevelInRefParamGroupFormattedNoBomUTF16.mzML",
        };
        for (String file : files) {
            final XMLStreamReaderImpl reader = new XMLStreamReaderImpl();
            final FileInputStream fis = new FileInputStream(file);
            final BufferedInputStream bis = new BufferedInputStream(fis);
            reader.setInput(bis);

            int event = XMLStreamConstants.END_DOCUMENT;
            do {
                try {
                    event = reader.next();
                } catch (XMLStreamException ex) {
                    int a = 1;
                }
            } while (event != XMLStreamConstants.END_DOCUMENT);
        }
    }

    @Test
    public void setInput1() throws Exception {
    }

    @Test
    public void setInput2() throws Exception {
    }

}