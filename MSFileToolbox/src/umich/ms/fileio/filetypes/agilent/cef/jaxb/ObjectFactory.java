
/*
 * Copyright (c) 2018 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.agilent.cef.jaxb;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the umich.ms.fileio.filetypes.agilent.cef.jaxb package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation for XML content. The Java representation of
 * XML content can consist of schema derived interfaces and classes representing the binding of
 * schema type definitions, element declarations and model groups.  Factory methods for each of
 * these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {


  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: umich.ms.fileio.filetypes.agilent.cef.jaxb
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link MatchScores }
   */
  public MatchScores createMatchScores() {
    return new MatchScores();
  }

  /**
   * Create an instance of {@link Match }
   */
  public Match createMatch() {
    return new Match();
  }

  /**
   * Create an instance of {@link CEF }
   */
  public CEF createCEF() {
    return new CEF();
  }

  /**
   * Create an instance of {@link CompoundList }
   */
  public CompoundList createCompoundList() {
    return new CompoundList();
  }

  /**
   * Create an instance of {@link Compound }
   */
  public Compound createCompound() {
    return new Compound();
  }

  /**
   * Create an instance of {@link Location }
   */
  public Location createLocation() {
    return new Location();
  }

  /**
   * Create an instance of {@link CompoundScores }
   */
  public CompoundScores createCompoundScores() {
    return new CompoundScores();
  }

  /**
   * Create an instance of {@link CpdScore }
   */
  public CpdScore createCpdScore() {
    return new CpdScore();
  }

  /**
   * Create an instance of {@link Results }
   */
  public Results createResults() {
    return new Results();
  }

  /**
   * Create an instance of {@link Molecule }
   */
  public Molecule createMolecule() {
    return new Molecule();
  }

  /**
   * Create an instance of {@link Spectrum }
   */
  public Spectrum createSpectrum() {
    return new Spectrum();
  }

  /**
   * Create an instance of {@link MSDetails }
   */
  public MSDetails createMSDetails() {
    return new MSDetails();
  }

  /**
   * Create an instance of {@link RTRanges }
   */
  public RTRanges createRTRanges() {
    return new RTRanges();
  }

  /**
   * Create an instance of {@link RTRange }
   */
  public RTRange createRTRange() {
    return new RTRange();
  }

  /**
   * Create an instance of {@link Device }
   */
  public Device createDevice() {
    return new Device();
  }

  /**
   * Create an instance of {@link MSPeaks }
   */
  public MSPeaks createMSPeaks() {
    return new MSPeaks();
  }

  /**
   * Create an instance of {@link P }
   */
  public P createP() {
    return new P();
  }

}
