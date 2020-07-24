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
package umich.ms.fileio.filetypes.mzxml.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the umich.ms.fileio.filetypes.mzxml.jaxb package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation for XML content. The Java representation of
 * XML content can consist of schema derived interfaces and classes representing the binding of
 * schema type definitions, element declarations and model groups.  Factory methods for each of
 * these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _SeparationTechnique_QNAME = new QName(
      "http://sashimi.sourceforge.net/schema_revision/mzXML_3.2", "separationTechnique");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: umich.ms.fileio.filetypes.mzxml.jaxb
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link Scan }
   */
  public Scan createScan() {
    return new Scan();
  }

  /**
   * Create an instance of {@link MsRun }
   */
  public MsRun createMsRun() {
    return new MsRun();
  }

  /**
   * Create an instance of {@link MsRun.Spotting }
   */
  public MsRun.Spotting createMsRunSpotting() {
    return new MsRun.Spotting();
  }

  /**
   * Create an instance of {@link MsRun.Spotting.Plate }
   */
  public MsRun.Spotting.Plate createMsRunSpottingPlate() {
    return new MsRun.Spotting.Plate();
  }

  /**
   * Create an instance of {@link MsRun.Spotting.Plate.Pattern }
   */
  public MsRun.Spotting.Plate.Pattern createMsRunSpottingPlatePattern() {
    return new MsRun.Spotting.Plate.Pattern();
  }

  /**
   * Create an instance of {@link MsRun.MsInstrument }
   */
  public MsRun.MsInstrument createMsRunMsInstrument() {
    return new MsRun.MsInstrument();
  }

  /**
   * Create an instance of {@link Software }
   */
  public Software createSoftware() {
    return new Software();
  }

  /**
   * Create an instance of {@link Scan.ScanOrigin }
   */
  public Scan.ScanOrigin createScanScanOrigin() {
    return new Scan.ScanOrigin();
  }

  /**
   * Create an instance of {@link Scan.PrecursorMz }
   */
  public Scan.PrecursorMz createScanPrecursorMz() {
    return new Scan.PrecursorMz();
  }

  /**
   * Create an instance of {@link Scan.Maldi }
   */
  public Scan.Maldi createScanMaldi() {
    return new Scan.Maldi();
  }

  /**
   * Create an instance of {@link Scan.Peaks }
   */
  public Scan.Peaks createScanPeaks() {
    return new Scan.Peaks();
  }

  /**
   * Create an instance of {@link NamevalueType }
   */
  public NamevalueType createNamevalueType() {
    return new NamevalueType();
  }

  /**
   * Create an instance of {@link SeparationTechniqueType }
   */
  public SeparationTechniqueType createSeparationTechniqueType() {
    return new SeparationTechniqueType();
  }

  /**
   * Create an instance of {@link MsRun.ParentFile }
   */
  public MsRun.ParentFile createMsRunParentFile() {
    return new MsRun.ParentFile();
  }

  /**
   * Create an instance of {@link MsRun.DataProcessing }
   */
  public MsRun.DataProcessing createMsRunDataProcessing() {
    return new MsRun.DataProcessing();
  }

  /**
   * Create an instance of {@link MsRun.Separation }
   */
  public MsRun.Separation createMsRunSeparation() {
    return new MsRun.Separation();
  }

  /**
   * Create an instance of {@link Operator }
   */
  public Operator createOperator() {
    return new Operator();
  }

  /**
   * Create an instance of {@link OntologyEntryType }
   */
  public OntologyEntryType createOntologyEntryType() {
    return new OntologyEntryType();
  }

  /**
   * Create an instance of {@link MsRun.Spotting.Robot }
   */
  public MsRun.Spotting.Robot createMsRunSpottingRobot() {
    return new MsRun.Spotting.Robot();
  }

  /**
   * Create an instance of {@link MsRun.Spotting.Plate.Spot }
   */
  public MsRun.Spotting.Plate.Spot createMsRunSpottingPlateSpot() {
    return new MsRun.Spotting.Plate.Spot();
  }

  /**
   * Create an instance of {@link MsRun.Spotting.Plate.Pattern.Orientation }
   */
  public MsRun.Spotting.Plate.Pattern.Orientation createMsRunSpottingPlatePatternOrientation() {
    return new MsRun.Spotting.Plate.Pattern.Orientation();
  }

  /**
   * Create an instance of {@link MsRun.MsInstrument.MsManufacturer }
   */
  public MsRun.MsInstrument.MsManufacturer createMsRunMsInstrumentMsManufacturer() {
    return new MsRun.MsInstrument.MsManufacturer();
  }

  /**
   * Create an instance of {@link MsRun.MsInstrument.MsMassAnalyzer }
   */
  public MsRun.MsInstrument.MsMassAnalyzer createMsRunMsInstrumentMsMassAnalyzer() {
    return new MsRun.MsInstrument.MsMassAnalyzer();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link SeparationTechniqueType }{@code >}}
   */
  @XmlElementDecl(namespace = "http://sashimi.sourceforge.net/schema_revision/mzXML_3.2", name = "separationTechnique")
  public JAXBElement<SeparationTechniqueType> createSeparationTechnique(
      SeparationTechniqueType value) {
    return new JAXBElement<SeparationTechniqueType>(_SeparationTechnique_QNAME,
        SeparationTechniqueType.class, null, value);
  }

}
