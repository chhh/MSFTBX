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

package umich.ms.fileio.filetypes.mzml.util;

import javolution.text.CharArray;
import javolution.xml.internal.stream.AttributesImpl;
import javolution.xml.internal.stream.NamespacesImpl;
import javolution.xml.sax.Attributes;
import umich.ms.fileio.filetypes.mzml.jaxb.CVParamType;
import umich.ms.util.StringUtils;

/**
 * @author Dmitry Avtonomov
 */
public class CvAttrs {
    public static final CharArray NAME_cvRef = new CharArray("cvRef");
    public static final CharArray NAME_accession = new CharArray("accession");
    public static final CharArray NAME_name = new CharArray("name");
    public static final CharArray NAME_value = new CharArray("value");
    public static final CharArray NAME_unitCvRef = new CharArray("unitCvRef");
    public static final CharArray NAME_unitAccession = new CharArray("unitAccession");
    public static final CharArray NAME_unitName = new CharArray("unitName");

    private CvAttrs() {}

    public static Attributes create(CVParamType cvParam) {
        final AttributesImpl a = new AttributesImpl(new NamespacesImpl());

        //final CharArray cvRef = new CharArray(cvParam.getCvRef());
        //final CharArray unitCvRef = new CharArray(cvParam.getUnitCvRef());

        if (!StringUtils.isNullOrBlank(cvParam.getAccession())) {
            final CharArray accession = new CharArray(cvParam.getAccession());
            a.addAttribute(NAME_accession, null, NAME_accession, accession);
        } else {
            throw new IllegalStateException("Accession can't be null/empty");
        }

        if (!StringUtils.isNullOrBlank(cvParam.getName())) {
            final CharArray name = new CharArray(cvParam.getName());
            a.addAttribute(NAME_name, null, NAME_name, name);
        }

        if (!StringUtils.isNullOrBlank(cvParam.getValue())) {
            final CharArray value = new CharArray(cvParam.getValue());
            a.addAttribute(NAME_value , null, NAME_value, value);
        }

        if (!StringUtils.isNullOrBlank(cvParam.getUnitAccession())) {
            final CharArray unitAccession = new CharArray(cvParam.getUnitAccession());
            a.addAttribute(NAME_unitAccession , null, NAME_unitAccession, unitAccession);
        }

        if (!StringUtils.isNullOrBlank(cvParam.getUnitName())) {
            final CharArray unitName = new CharArray(cvParam.getUnitName());
            a.addAttribute(NAME_unitName , null, NAME_unitName, unitName);
        }

        return a;
    }
}
