/*
 * Copyright (c) 2016 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.mzml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javolution.xml.sax.Attributes;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.fileio.filetypes.mzml.jaxb.CVParamType;
import umich.ms.fileio.filetypes.mzml.jaxb.MzMLType;
import umich.ms.fileio.filetypes.mzml.util.CvAttrs;

/**
 * @author Dmitry Avtonomov
 */
public class MZMLRunInfo extends LCMSRunInfo {

  private MzMLType parsedInfo = null;
  private Map<String, List<CVParamType>> refParamGroups = new HashMap<>();
  private Map<String, List<javolution.xml.sax.Attributes>> refParamGroupsAttrs = new HashMap<>();

  public MZMLRunInfo(LCMSRunInfo runInfo) {
    this.date = runInfo.getRunStartTime();
    this.instruments = runInfo.getInstruments();
    this.defaultInstrumentID = runInfo.getDefaultInstrumentID();
    this.isCentroided = runInfo.isCentroided();
    this.software = runInfo.getSoftware();
    this.originalFiles = runInfo.getOriginalFiles();
    setDefaultInstrumentID(getDefaultInstrumentID());
  }

  public MZMLRunInfo(MzMLType parsedInfo) {
    super();
    this.parsedInfo = parsedInfo;
  }

  public Map<String, List<CVParamType>> getRefParamGroups() {
    return refParamGroups;
  }

  public MzMLType getParsedInfo() {
    return parsedInfo;
  }

  public Map<String, List<Attributes>> getRefParamGroupsAttrs() {
    return refParamGroupsAttrs;
  }

  public void addRefParamGroup(String id, List<CVParamType> cvParams) {
    List<Attributes> list = new ArrayList<>(cvParams.size());
    for (CVParamType cv : cvParams) {
      list.add(CvAttrs.create(cv));
    }
    refParamGroups.put(id, cvParams);
    refParamGroupsAttrs.put(id, list);
  }
}
