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
package umich.ms.datatypes.scan.props;

import java.io.Serializable;


/**
 * Author: Dmitry Avtonomov (dmitriya)
 * Email: dmitriy.avtonomov@gmail.com
 * Date: 3/25/13
 * Time: 5:37 PM
 */
public class Instrument implements Serializable {
    private static final long serialVersionUID = 5457880259281215955L;

    protected String manufacturer;
    protected String model;
    protected String analyzer;
    protected String detector;
    protected String ionisation;

    public static final String ID_UNKNOWN = "ID_UNKNOWN";
    public static final String UNKNOWN_MANUFACTURER = "Vendor N/A";
    public static final String UNKNOWN_MODEL = "Model N/A";
    public static final String UNKNOWN_ANALYZER = "Analyzer N/A";
    public static final String UNKNOWN_DETECTOR = "Detector N/A";
    public static final String UNKNOWN_IONISATION = "Ionisation N/A";


    public Instrument() {
        manufacturer = "";
        model = "";
        analyzer = "";
        detector = "";
        ionisation = "";
    }

    public Instrument(String manufacturer, String model, String analyzer) {
        this();
        this.manufacturer = manufacturer;
        this.model = model;
        this.analyzer = analyzer;
    }

    public Instrument(String manufacturer, String model, String ionisation, String analyzer, String detector) {
        this();
        this.manufacturer = manufacturer;
        this.model = model;
        this.ionisation = ionisation;
        this.analyzer = analyzer;
        this.detector = detector;
    }

    /**
     * Only to be used, when you need to construct a ScanCollection without any knowledge about the real run,
     * so you have no idea about the instrument.
     * @return
     */
    public static final Instrument getDummy() {
        return new Instrument();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIonisation() {
        return ionisation;
    }

    public void setIonisation(String ionisation) {
        this.ionisation = ionisation;
    }

    public String getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(String analyzer) {
        this.analyzer = analyzer;
    }

    public String getDetector() {
        return detector;
    }

    public void setDetector(String detector) {
        this.detector = detector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instrument)) return false;

        Instrument that = (Instrument) o;

        if (analyzer != null ? !analyzer.equals(that.analyzer) : that.analyzer != null) return false;
        if (detector != null ? !detector.equals(that.detector) : that.detector != null) return false;
        if (ionisation != null ? !ionisation.equals(that.ionisation) : that.ionisation != null) return false;
        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = manufacturer != null ? manufacturer.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (ionisation != null ? ionisation.hashCode() : 0);
        result = 31 * result + (analyzer != null ? analyzer.hashCode() : 0);
        result = 31 * result + (detector != null ? detector.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", ionisation='" + ionisation + '\'' +
                ", analyzer='" + analyzer + '\'' +
                ", detector='" + detector + '\'' +
                '}';
    }
}
