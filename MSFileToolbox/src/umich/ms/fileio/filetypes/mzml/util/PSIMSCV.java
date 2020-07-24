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
package umich.ms.fileio.filetypes.mzml.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javolution.text.CharArray;
import org.biojava.nbio.ontology.Ontology;
import org.biojava.nbio.ontology.Synonym;
import org.biojava.nbio.ontology.Term;
import org.biojava.nbio.ontology.Triple;
import org.biojava.nbio.ontology.io.OboParser;

/**
 * Commonly used Ontology terms in mzML files.
 *
 * @author Dmitry Avtonomov
 * @see <a href="http://psidev.cvs.sourceforge.net/viewvc/psidev/psi/psi-ms/mzML/controlledVocabulary/psi-ms.obo">PSI-MS
 * obo</a>
 */
public enum PSIMSCV {
  UO_SECONDS("UO:0000010", "second"),
  UO_MINUTES("UO:0000031", "minute"),
  UO_NANOSECONDS("UO:0000150", "nanosecond"),
  UO_MICROSECONDS("UO:0000029", "microsecond"),
  UO_MILLISECONDS("UO:0000028", "millisecond"),
  UO_HOURS("UO:0000032", "hour"),
  MS_LEVEL("MS:1000511", "ms level"),
  MS_POLARITY_POS("MS:1000130", "positive scan"),
  MS_POLARITY_POS_OBSOLETE("MS:1000077", "positive ion mode"),
  MS_POLARITY_NEG("MS:1000129", "negative scan"),
  MS_POLARITY_NEG_OBSOLETE("MS:1000076", "negative ion mode"),
  MS_SPECTRUM_TYPE("MS:1000559", "spectrum type"),
  MS_MASS_SPECTRUM("MS:1000294", "mass spectrum"),
  MS_CALIBRATION_SPECTRUM("MS:1000928", "calibration spectrum"),
  MS_MS1_SPECTRUM("MS:1000579", "MS1 spectrum"),
  MS_MSN_SPECTRUM("MS:1000580", "MSn spectrum"),
  MS_MSN_SPECTRUM_OBSOLETE_1("MS:1000339", "nth generation product ion spectrum"),
  MS_MSN_SPECTRUM_OBSOLETE_2("MS:1000343", "product ion spectrum"),
  MS_SCAN_TYPE_FULL("MS:1000498", "full scan"),
  MS_SCAN_TYPE_ZOOM("MS:1000497", "zoom scan"),
  MS_SCAN_TYPE_SIM("MS:1000582", "SIM spectrum"),
  MS_SCAN_TYPE_CRM("MS:1000581", "CRM spectrum"),
  MS_SCAN_TYPE_SRM("MS:1000583", "SRM spectrum"),
  MS_CENTROIDED("MS:1000127", "centroid spectrum"),
  MS_PROFILE("MS:1000128", "profile spectrum"),
  MS_MZ_OBSERVED_LO("MS:1000528", "lowest observed m/z"),     // observed value
  MS_MZ_OBSERVED_LO_INST_SETTING("MS:1000501", "scan window lower limit"), // instrument setting
  MS_MZ_OBSERVED_HI("MS:1000527", "highest observed m/z"),    // observed value
  MS_MZ_OBSERVED_HI_INST_SETTING("MS:1000500", "scan window upper limit"), // instrument setting
  MS_BASEPEAK_MZ("MS:1000504", "base peak m/z"),
  MS_BASEPEAK_INTENSITY("MS:1000505", "base peak intensity"),
  MS_TIC("MS:1000285", "total ion current"),
  MS_RT_SCAN_START("MS:1000016", "scan start time"),
  MS_IM("MS:1002815", "inverse reduced ion mobility"),
  MS_RT_RETENTION_TIME("MS:1000894", "retention time"),
  MS_RT_RETENTION_TIME_LOCAL("MS:1000895", "local retention time"),
  MS_RT_RETENTION_TIME_NORMALIZED("MS:1000896", "normalized retention time"),
  MS_ION_INJECTION_TIME("MS:1000927", "ion injection time"),
  MS_PRECURSOR_ISO_WND_TARGET("MS:1000827", "isolation window target m/z"),
  MS_PRECURSOR_ISO_WND_LO_OFFSET("MS:1000828", "isolation window lower offset"),
  MS_PRECURSOR_ISO_WND_LO_OBSOLETE("MS:1000794", "isolation window lower limit"),
  MS_PRECURSOR_ISO_WND_HI_OFFSET("MS:1000829", "isolation window upper offset"),
  MS_PRECURSOR_ISO_WND_HI_OBSOLETE("MS:1000793", "isolation window upper limit"),
  MS_PRECURSOR_INTENSITY("MS:1000042", "peak intensity"),
  MS_PRECURSOR_MZ("MS:1000744", "selected ion m/z"),
  MS_PRECURSOR_CHARGE("MS:1000041", "charge state"),
  MS_DATA_ARRAY_MZ("MS:1000514", "m/z array"),
  MS_DATA_ARRAY_INTENSITY("MS:1000515", "intensity array"),
  MS_DATA_ARRAY_IM("MS:1002816", "mean inverse reduced ion mobility array"),
  MS_PRECISION_32("MS:1000521", "32-bit float"),
  MS_PRECISION_64("MS:1000523", "64-bit float"),
  MS_COMPRESSION_ZLIB("MS:1000574", "zlib compression"),
  MS_COMPRESSION_NONE("MS:1000576", "no compression"),
  MS_COMPRESSION_NUMPRESS_LIN_PRED("MS:1002312", "MS-Numpress linear prediction compression",
      "Numpress LINPRED"),
  MS_COMPRESSION_NUMPRESS_POS_INT("MS:1002313", "MS-Numpress positive integer compression",
      "Numpress POSINT"),
  MS_COMPRESSION_NUMPRESS_LOG_FLOAT("MS:1002314", "MS-Numpress short logged float compression",
      "Numpress SHLOGF"),
  MS_COMPRESSION_NUMPRESS_LIN_PRED_ZLIB("MS:1002746",
      "MS-Numpress linear prediction compression followed by zlib compression",
      "Numpress LINPRED+ZLIB"),
  MS_COMPRESSION_NUMPRESS_POS_INT_ZLIB("MS:1002747",
      "MS-Numpress positive integer compression followed by zlib compression",
      "Numpress POSINT+ZLIB"),
  MS_COMPRESSION_NUMPRESS_LOG_FLOAT_ZLIB("MS:1002748",
      "MS-Numpress short logged float compression followed by zlib compression",
      "Numpress SHLOGF+ZLIB"),

  MS_DISSOCIATION_METHOD("MS:1000044", "dissociation method"),
  MS_PEAK_PICKING("MS:1000035", "peak picking"),
  MS_ACTIVATION_ENERGY_1("MS:1000045", "collision energy"),
  MS_ACTIVATION_ENERGY_2("MS:1000509", "activation energy"),
  MS_ACTIVATION_ENERGY_LO("MS:1002013", "collision energy ramp start"),
  MS_ACTIVATION_ENERGY_HI("MS:1002014", "collision energy ramp end"),
  MS_ACTIVATION_ENERGY_SUP("MS:1002680", "supplemental collision energy"),

  MS_INSTRUMENT("MS:1000463", "instrument"),
  MS_INSTRUMENT_MODEL("MS:1000031", "instrument model"),
  MS_INSTRUMENT_VENDOR_OBSOLETE("MS:1000030", "vendor"),
  MS_INSTRUMENT_SERIAL_NUMBER("MS:1000529", "instrument serial number"),

  MS_INSTRUMENT_COMPONENT_SOURCE("MS:1000458", "source"),
  MS_INSTRUMENT_COMPONENT_ANALYZER("MS:1000443", "mass analyzer type"),
  MS_INSTRUMENT_COMPONENT_DETECTOR("MS:1000026", "detector type"),
  MS_INSTRUMENT_IONIZATION_TYPE("MS:1000008", "ionization type"),


  MS_HASH_SHA1("MS:1000569", "SHA-1"),
  MS_HASH_MD5("MS:1000568", "MD5"),

//    MS_PRECURSOR_ACTIVATION_CID         ("MS:1000133", "collision-induced dissociation", "CID"),
//    MS_PRECURSOR_ACTIVATION_PLASMA      ("MS:1000134", "plasma desorption", "Plasma Desorption"),
//    MS_PRECURSOR_ACTIVATION_POST_SOURCE ("MS:1000135", "post-source decay", "Post-Source Decay"),
//    MS_PRECURSOR_ACTIVATION_SID         ("MS:1000136", "surface-induced dissociation", "SID"),
//    MS_PRECURSOR_ACTIVATION_BIRD         ("MS:1000242", "blackbody infrared radiative dissociation", "BIRD"),
  ;

  protected static final Map<String, PSIMSCV> mapString2PSIMSCV;
  protected static final Map<CharArray, PSIMSCV> mapCharArr2PSIMSCV;
  //@StaticResource // this annotation brings dependency on Common Annotations from NBP
  final static String psiMsOboPath = "umich/ms/fileio/filetypes/mzml/util/psi-ms.obo";
  public static Map<String, ActivationCVTerm> mapString2ActivationCVTerm = new HashMap<>(30);
  public static Map<CharArray, ActivationCVTerm> mapCharArr2ActivationCVTerm = new HashMap<>(30);
  public static Map<String, Term> MAP_ANALYZER_TYPE = new HashMap<>(30);
  public static Map<String, InstrumentModelCVTerm> MAP_INSTRUMENT_MODEL = new HashMap<>(250);
  public static Map<String, Term> MAP_PEAK_PICKING = new HashMap<>(5);
  public static Map<String, Term> MAP_IONIZATION_TYPE = new HashMap<>(5);
  public static Map<String, Term> MAP_MASS_SPECTRUM = new HashMap<>(50);
  public static Map<String, Term> MAP_SPECTRUM_TYPE = new HashMap<>(100);
  public static Map<String, Term> MAP_NOT_MASS_SPECTRUM = new HashMap<>(100);
  public static Map<CharArray, Term> MAP_NOT_MASS_SPECTRUM_CHAR = new HashMap<>(100);
  public static Ontology ONTOLOGY;

  static {
    PSIMSCV[] values = PSIMSCV.values();

    // initialize the hashmaps for String and CharArray keys
    mapString2PSIMSCV = new HashMap<>(values.length);
    mapCharArr2PSIMSCV = new HashMap<>(values.length);
    for (PSIMSCV entry : values) {
      mapString2PSIMSCV.put(entry.accession, entry);
      mapCharArr2PSIMSCV.put(new CharArray(entry.accession), entry);
    }

    OboParser oboParser = new OboParser();
    Ontology ontoMs;
    try {

      ClassLoader classLoader = PSIMSCV.class.getClassLoader();
      //URL resourceURL = PSIMSCV.class.getClassLoader().getResource(psiMsOboPath); // this works in NBP app
      InputStream is = classLoader.getResourceAsStream(psiMsOboPath);
      BufferedReader oboFile = new BufferedReader(new InputStreamReader(is));

      ontoMs = oboParser.parseOBO(oboFile, "PSI-MS", "Mass Spectrometry controlled vocabulary");
      ONTOLOGY = ontoMs;

      // gather activation type entries from the .obo
      TermCallback callbackActivationTerm = new TermCallback() {
        @Override
        public void perform(Term term, boolean isLeaf) {
          String accession = term.getName();
          String name = term.getDescription();
          String shortName = tryGetShortNameFromSynonyms(term, name);
          String definition = tryGetDescription(term);

          ActivationCVTerm activationCVTerm = new ActivationCVTerm(accession, name, definition,
              shortName);
          mapString2ActivationCVTerm.put(accession, activationCVTerm);
          mapCharArr2ActivationCVTerm.put(new CharArray(accession), activationCVTerm);
        }
      };
      gatherRootedInfo(MS_DISSOCIATION_METHOD.accession, ontoMs, callbackActivationTerm);

      // gather analyzer types from the .obo
      TermCallback callbackAnalyzerType = new TermCallback() {
        @Override
        public void perform(Term term, boolean isLeaf) {
          if (!isLeaf) {
            return;
          }
          MAP_ANALYZER_TYPE.put(term.getName(), term);
        }
      };
      gatherRootedInfo(MS_INSTRUMENT_COMPONENT_ANALYZER.accession, ontoMs, callbackAnalyzerType);

      // gather all Spectrum Types
      TermCallback callbackSpectrumTypeTerm = new TermCallback() {
        @Override
        public void perform(Term term, boolean isLeaf) {
          if (!isLeaf) {
            return;
          }
          MAP_SPECTRUM_TYPE.put(term.getName(), term);
        }
      };
      gatherRootedInfo(MS_SPECTRUM_TYPE.accession, ontoMs, callbackSpectrumTypeTerm);

      // gather all Mass Spectrum types
      TermCallback callbackMassSpectrumTerm = new TermCallback() {
        @Override
        public void perform(Term term, boolean isLeaf) {
          String accession = term.getName();
          MAP_MASS_SPECTRUM.put(accession, term);
        }
      };
      gatherRootedInfo(MS_MASS_SPECTRUM.accession, ontoMs, callbackMassSpectrumTerm);

      // all known non-mass spectrum types
      for (Map.Entry<String, Term> e : MAP_SPECTRUM_TYPE.entrySet()) {
        if (MS_CALIBRATION_SPECTRUM.accession.equals(e.getKey())) // calibration spectrum
        {
          continue;
        }
        final Term term = MAP_MASS_SPECTRUM.get(e.getKey());
        if (term == null) {
          MAP_NOT_MASS_SPECTRUM.put(e.getKey(), e.getValue());
          MAP_NOT_MASS_SPECTRUM_CHAR.put(new CharArray(e.getKey()), e.getValue());
        }
      }

      // gather instrument models from the .obo
      try {
        Term instModelTermRoot = ontoMs.getTerm(MS_INSTRUMENT_MODEL.accession);
        Set<Triple> triplesVendors = ontoMs.getTriples(null, instModelTermRoot, null);
        // iterate over vendors
        for (Triple tVendor : triplesVendors) {
          Term subject = tVendor.getSubject();
          String accessionVendor = subject.getName();
          String nameVendor = subject.getDescription();
          String shortNameVendor = tryGetShortNameFromSynonyms(subject, nameVendor);
          String instModelEnding = "instrument model";
          if (shortNameVendor.endsWith(instModelEnding)) {
            shortNameVendor = shortNameVendor.substring(0, shortNameVendor.indexOf(instModelEnding))
                .trim();
          }
          final String shortNameVendorFinal = shortNameVendor;
          TermCallback callbackInstrumentModel = new TermCallback() {
            @Override
            public void perform(Term term, boolean isLeaf) {
              if (!isLeaf) {
                return;
              }
              String accessionModel = term.getName();
              String nameModel = term.getDescription();
              String shortNameModel = tryGetShortNameFromSynonyms(term, nameModel);
              InstrumentModelCVTerm instrumentModelCVTerm = new InstrumentModelCVTerm(
                  accessionModel, nameModel, shortNameVendorFinal, shortNameModel);
              MAP_INSTRUMENT_MODEL.put(accessionModel, instrumentModelCVTerm);
            }
          };
          gatherRootedInfo(accessionVendor, ontoMs, callbackInstrumentModel);
        }
      } catch (NoSuchElementException e) {
        // could not get the root element for all mass spec instruments
      }

      // collect peak-picking terms to determine if a file has been centroided (DataProcessing section of mzML)
      TermCallback callbackPeakPickingTerm = new TermCallback() {
        @Override
        public void perform(Term term, boolean isLeaf) {
          MAP_PEAK_PICKING.put(term.getName(), term);
        }
      };
      gatherRootedInfo(MS_PEAK_PICKING.accession, ontoMs, callbackPeakPickingTerm);

      TermCallback callbackIonizationType = new TermCallback() {
        @Override
        public void perform(Term term, boolean isLeaf) {
          MAP_IONIZATION_TYPE.put(term.getName(), term);
        }
      };
      gatherRootedInfo(MS_INSTRUMENT_IONIZATION_TYPE.accession, ontoMs, callbackIonizationType);

    } catch (ParseException | IOException e) {
      e.printStackTrace();
    }
  }

  public String accession;
  public String description;
  public String defaultStringRepresentation;

  PSIMSCV(String accession, String description) {
    this.accession = accession;
    this.description = description;
    this.defaultStringRepresentation = this.description;
  }

  PSIMSCV(String accession, String description, String defaultStringRepresentation) {
    this.accession = accession;
    this.description = description;
    this.defaultStringRepresentation = defaultStringRepresentation;
  }

  private static void gatherRootedInfo(String rootAccession, Ontology ontology,
      TermCallback callback) {
    try {
      Term term = ontology.getTerm(rootAccession);
      drillDownTerms(ontology, term, callback);
    } catch (NoSuchElementException e) {
      // could not find the root element for all activations in the controlled vocabulary
    }
  }

  /**
   * Tries to find a list of synonims and selects the shortest one. If multiple synonims of same
   * length exist, then the last one is chosen.
   *
   * @param shortNameDefault normally, you should pass the long name here, so it's used as a
   * fallback. If no shorter name can be found in synonims, this one will be returned.
   * @return the shortest synonim, or the default value
   */
  private static String tryGetShortNameFromSynonyms(Term term, String shortNameDefault) {
    String shortName = shortNameDefault;
    for (Object synonym : term.getSynonyms()) {
      if (synonym instanceof Synonym) {
        Synonym s = (Synonym) synonym;
        if (s.getName().length() <= shortName.length()) {
          shortName = s.getName();
        }
      }
    }
    return shortName;
  }

  /**
   * Tries to find definition in the vocabulary term.
   *
   * @return empty string, if no definition could be found
   */
  private static String tryGetDescription(Term term) {
    String def = "";
    Object defObj = term.getAnnotation().getProperty("def");
    if (defObj != null && defObj instanceof String) {
      def = (String) defObj;
    }
    return def;
  }

  /**
   * Iterates the ontology tree, following any "is_a" relationships. E.g., if you sart with an
   * "instrument model" term, then it will find all "_manufacturer_name_ instrument model" terms,
   * and then the "intrument models" themselves.
   *
   * @param rootTerm the starting term, from which to start searching for "is_a" relationships.
   * @param callback will be called once for each node visited with {@code isLeaf} set to false, and
   * once with {@code isLeaf} set to true, if the term is a leaf node in the tree.
   */
  private static void drillDownTerms(Ontology ontology, Term rootTerm, TermCallback callback) {
    drillDownTerms(ontology, rootTerm, callback, 0);
  }

  /**
   * Iterates the ontology tree, following any "is_a" relationships. E.g., if you sart with an
   * "instrument model" term, then it will find all "_manufacturer_name_ instrument model" terms,
   * and then the "intrument models" themselves.
   * <b>WARNING:<b/>
   * If the provided root term has no children, i.e. it's technically a leaf term, the callback for
   * it will be called with {@code isLeaf} set to false - top level root term is never considered a
   * leaf.
   *
   * @param rootTerm the starting term, from which to start searching for "is_a" relationships.
   * @param callback will be called once for each node visited with {@code isLeaf} set to false, and
   * once with {@code isLeaf} set to true, if the term is a leaf node in the tree.
   */
  private static void drillDownTerms(Ontology ontology, Term rootTerm, TermCallback callback,
      int recursionLevel) {
    Set<Triple> triples = ontology.getTriples(null, rootTerm, null);
    if (triples != null && triples.isEmpty() && recursionLevel != 0) {
      // this term has no "is_a" children, call callback function
      callback.perform(rootTerm, true);
    } else {
      callback.perform(rootTerm, false);
    }
    for (Triple t : triples) {
      Term subject = t.getSubject();
      drillDownTerms(ontology, subject, callback, recursionLevel + 1);
    }
  }

  public static PSIMSCV fromAccession(String msAccession) {
    return mapString2PSIMSCV.get(msAccession);
  }

  public static PSIMSCV fromAccession(CharArray msAccession) {
    return mapCharArr2PSIMSCV.get(msAccession);
  }

  public static InstrumentModelCVTerm instrumentFromAccession(String msAccession) {
    return MAP_INSTRUMENT_MODEL.get(msAccession);
  }

  /**
   * Returns the possible shortest abbreviation for a given activation method.
   *
   * @param msAccession string of the form "MS:00012323"
   * @return null, if we couldn't find the accession among activation methods described in OBO file
   */
  public static String activationMethodFromAccession(String msAccession) {
    ActivationCVTerm activationCVTerm = mapString2ActivationCVTerm.get(msAccession);
    return activationCVTerm == null ? null : activationCVTerm.getShortName();
  }

  /**
   * Returns the possible shortest abbreviation for a given activation method.
   *
   * @param msAccession string of the form "MS:00012323"
   * @return null, if we couldn't find the accession among activation methods described in OBO file
   */
  public static String activationMethodFromAccession(CharArray msAccession) {
    ActivationCVTerm activationCVTerm = mapCharArr2ActivationCVTerm.get(msAccession);
    return activationCVTerm == null ? null : activationCVTerm.getShortName();
  }

  /**
   * Checks if this accession maps to a known descendant of ["MS:1000035", "peak picking"] term.
   *
   * @return true, if the term "is_a" "MS:1000035" (peak picking), can be the root term itself, or
   * one of its children.
   */
  public static boolean isPeakPickingTerm(String msAccession) {
    return MAP_PEAK_PICKING.get(msAccession) != null;
  }

  private interface TermCallback {

    void perform(Term term, boolean isLeaf);
  }
}
