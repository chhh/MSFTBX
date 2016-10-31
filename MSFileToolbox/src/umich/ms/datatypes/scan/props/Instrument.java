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
 * Author: Dmitry Avtonomov
 */
public class Instrument implements Serializable {
    private static final long serialVersionUID = 268119072813701156L;

    protected String manufacturer;
    protected String model;
    protected String serialNumber;
    protected String analyzer;
    protected String detector;
    protected String ionisation;

    public static final String ID_UNKNOWN = "ID_UNKNOWN";
    public static final String UNKNOWN_MANUFACTURER = "Vendor N/A";
    public static final String UNKNOWN_SERIAL_NUMBER = "Serial Number N/A";
    public static final String UNKNOWN_MODEL = "Model N/A";
    public static final String UNKNOWN_ANALYZER = "Analyzer N/A";
    public static final String UNKNOWN_DETECTOR = "Detector N/A";
    public static final String UNKNOWN_IONISATION = "Ionisation N/A";


    public Instrument() {
        manufacturer = UNKNOWN_MANUFACTURER;
        model = UNKNOWN_MODEL;
        serialNumber = UNKNOWN_SERIAL_NUMBER;
        analyzer = UNKNOWN_ANALYZER;
        detector = UNKNOWN_DETECTOR;
        ionisation = UNKNOWN_IONISATION;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
        if (o == null || getClass() != o.getClass()) return false;

        Instrument that = (Instrument) o;

        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (serialNumber != null ? !serialNumber.equals(that.serialNumber) : that.serialNumber != null) return false;
        if (analyzer != null ? !analyzer.equals(that.analyzer) : that.analyzer != null) return false;
        if (detector != null ? !detector.equals(that.detector) : that.detector != null) return false;
        return ionisation != null ? ionisation.equals(that.ionisation) : that.ionisation == null;

    }

    @Override
    public int hashCode() {
        int result = manufacturer != null ? manufacturer.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (serialNumber != null ? serialNumber.hashCode() : 0);
        result = 31 * result + (analyzer != null ? analyzer.hashCode() : 0);
        result = 31 * result + (detector != null ? detector.hashCode() : 0);
        result = 31 * result + (ionisation != null ? ionisation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", analyzer='" + analyzer + '\'' +
                ", detector='" + detector + '\'' +
                ", ionisation='" + ionisation + '\'' +
                '}';
    }
}
