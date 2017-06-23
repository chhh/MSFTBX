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
package umich.ms.datatypes.lcmsrun;

import umich.ms.datatypes.scan.props.Instrument;

import java.io.Serializable;
import java.util.*;

/**
 * Temporarily stores processed LC/MS file run header. For now only isntrument info, but
 * applied data-processing and parent files can also be retrieved.<br/>
 * If only one instrument is added, it will set it as the default one.
 * @author Dmitry Avtonomov
 */
public class LCMSRunInfo implements Serializable {
    private static final long serialVersionUID = -45967049048809479L;

    protected Map<String, Instrument> instruments = new HashMap<>(2);
    protected String defaultInstrumentID = null;
    protected Boolean isCentroided = null;
    protected boolean isDefaultExplicitlySet = false;
    protected Date date = null;
    protected List<MsSoftware> software = new ArrayList<>(5);
    protected List<OriginalFile> originalFiles = new ArrayList<>(1);

    public LCMSRunInfo() {}

    /**
     * If any of data-processing entries in mzXML or a similar file has mentioned, that the files has been centroided.
     * This value should be used as an override in cases, when a single scan doesn't provide info about it being
     * centroided or not. If a scan doesn't provide a "centroided" value, and the run header also has no info, it should
     * be considered an {@link java.lang.IllegalStateException}.
     * @return {@code null} if the value was not encountered in the run header
     */
    public Boolean isCentroided() {
        return isCentroided;
    }

    public void setCentroided(Boolean isCentroided) {
        this.isCentroided = isCentroided;
    }

    /**
     * If only one instrument is added, it will be set as the default instrument, all the scans, that you add
     * to the ScanCollection will implicitly refer to this one instrument.
     * @param instrument
     * @param id some identifier for mapping instruments. Instrumnt list is normally stored at the beginning of the
     *           run file, so it's a mapping from this list, to instrument ID specified for each spectrumRef.
     */
    public void addInstrument(Instrument instrument, String id) {
        if (instrument == null || id == null) {
            throw new IllegalArgumentException("Instruemnt and ID must be non-null values.");
        }
        if (instruments.size() == 0) {
            defaultInstrumentID = id;

        } else if (instruments.size() > 0 && !isDefaultExplicitlySet) {
            unsetDefaultInstrument();
        }
        instruments.put(id, instrument);
    }

    public final void removeInstrument(String id) {
        instruments.remove(id);
        if (defaultInstrumentID != null && defaultInstrumentID.equals(id)) {
            unsetDefaultInstrument();
        }
    }

    public void removeInstrument(Instrument instrument) {
        String keyToDelete = null;
        for (Map.Entry<String, Instrument> stringInstrumentEntry : instruments.entrySet()) {
            String id = stringInstrumentEntry.getKey();
            Instrument existingInstrument = stringInstrumentEntry.getValue();
            if (existingInstrument.equals(instrument)) {
                keyToDelete = id;
                break;
            }
        }
        if (keyToDelete != null) {
            removeInstrument(keyToDelete);
        }
    }

    public Map<String, Instrument> getInstruments() {
        return instruments;
    }

    /**
     * Get instrument by ID.
     * @param id
     * @return null, if no instrument was associated with the id.
     */
    public Instrument getInstrument(String id) {
        return instruments.get(id);
    }

    public Instrument getDefaultInstrument() {
        return instruments.get(defaultInstrumentID);
    }

    public String getDefaultInstrumentID() {
        return defaultInstrumentID;
    }

    /**
     * Call with null parameter to unset.
     * @param id this id must be present in the run info already.
     */
    public void setDefaultInstrumentID(String id) {
        if (id == null) {
            unsetDefaultInstrument();
            return;
        }

        if (instruments.containsKey(id)) {
            defaultInstrumentID = id;
            isDefaultExplicitlySet = true;
        } else {
            throw new IllegalArgumentException("The instrument map did not contain provided instrument ID, " +
                    "have you added the instrument first?");
        }
    }

    private void unsetDefaultInstrument() {
        defaultInstrumentID = null;
        isDefaultExplicitlySet = false;
    }

    /**
     * Only use if you can't get real run info.
     * @return
     */
    public static LCMSRunInfo createDummyInfo() {
        LCMSRunInfo lcmsRunInfo = new LCMSRunInfo();
        lcmsRunInfo.addInstrument(Instrument.getDummy(), Instrument.ID_UNKNOWN);
        return lcmsRunInfo;
    }

    public Date getRunStartTime() {
        return this.date;
    }

    public void setRunStartTime(Date runStartTime) {
        this.date = runStartTime;
    }

    public List<MsSoftware> getSoftware() {
        return software;
    }

    public List<OriginalFile> getOriginalFiles() {
        return originalFiles;
    }

    @Override
    public String toString() {
        return "LCMSRunInfo{" +
                "instruments=" + instruments +
                ", date=" + date +
                ", defaultInstrumentID='" + defaultInstrumentID + '\'' +
                ", isCentroided=" + isCentroided +
                ", isDefaultExplicitlySet=" + isDefaultExplicitlySet +
                '}';
    }
}
