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
package umich.ms.fileio.filetypes.gpmdb.jaxb;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the umich.gpmdb.jaxb package.
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
   * for package: umich.gpmdb.jaxb
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link Bioml }
   */
  public Bioml createBioml() {
    return new Bioml();
  }

  /**
   * Create an instance of {@link Group }
   */
  public Group createGroup() {
    return new Group();
  }

  /**
   * Create an instance of {@link Protein }
   */
  public Protein createProtein() {
    return new Protein();
  }

  /**
   * Create an instance of {@link Note }
   */
  public Note createNote() {
    return new Note();
  }

  /**
   * Create an instance of {@link File }
   */
  public File createFile() {
    return new File();
  }

  /**
   * Create an instance of {@link Peptide }
   */
  public Peptide createPeptide() {
    return new Peptide();
  }

  /**
   * Create an instance of {@link Domain }
   */
  public Domain createDomain() {
    return new Domain();
  }

  /**
   * Create an instance of {@link Aa }
   */
  public Aa createAa() {
    return new Aa();
  }

  /**
   * Create an instance of {@link Trace }
   */
  public Trace createTrace() {
    return new Trace();
  }

  /**
   * Create an instance of {@link Attribute }
   */
  public Attribute createAttribute() {
    return new Attribute();
  }

  /**
   * Create an instance of {@link Xdata }
   */
  public Xdata createXdata() {
    return new Xdata();
  }

  /**
   * Create an instance of {@link GAMLValues }
   */
  public GAMLValues createGAMLValues() {
    return new GAMLValues();
  }

  /**
   * Create an instance of {@link Values }
   */
  public Values createValues() {
    return new Values();
  }

  /**
   * Create an instance of {@link Ydata }
   */
  public Ydata createYdata() {
    return new Ydata();
  }

}
