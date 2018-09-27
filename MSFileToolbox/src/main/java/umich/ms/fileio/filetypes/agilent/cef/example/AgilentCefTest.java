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
package umich.ms.fileio.filetypes.agilent.cef.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import umich.ms.fileio.filetypes.agilent.cef.jaxb.CEF;

/**
 * Created by Dmitry Avtonomov on 2015-09-22.
 */
public class AgilentCefTest {

  public static void main(String[] args) throws JAXBException, IOException {

    // input file
    String path = "E:\\Metabo\\Instrument_Evaluation_Agilent\\UMichigan-Metabolomics-WatersColumn-DilutionCurve\\MassHunterAnalysis\\MFE-POS\\CO-10000\\D20130118-LC2-PP0001259-E6-I23-P.cef";
    Path p = Paths.get(path).toAbsolutePath();
    File f = new File(p.toString());

    // Unmarshall manually
    // declaring what to parse
    JAXBContext ctx = JAXBContext.newInstance(CEF.class);

    // run the parser
    Unmarshaller unmarshaller = ctx.createUnmarshaller();
    Object unmarshalled = unmarshaller.unmarshal(f);

    // use the unmarshalled object
    CEF cef = (CEF) unmarshalled;

    // Unmarshall to custom data-structure
    AgilentCefFile cefFile = new AgilentCefFile(p);
    AgilentCompounds compounds = cefFile.create();
    compounds.splitCompoundsByAdduct();

  }
}
