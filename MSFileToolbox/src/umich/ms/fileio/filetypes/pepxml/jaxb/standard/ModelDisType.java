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
package umich.ms.fileio.filetypes.pepxml.jaxb.standard;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for model_dis_type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="model_dis_type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="discrete"/>
 *     &lt;enumeration value="gaussian"/>
 *     &lt;enumeration value="extremevalue"/>
 *     &lt;enumeration value="gamma"/>
 *     &lt;enumeration value="evd"/>
 *     &lt;enumeration value="non-parametric"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "model_dis_type")
@XmlEnum
public enum ModelDisType {

    @XmlEnumValue("discrete")
    DISCRETE("discrete"),
    @XmlEnumValue("gaussian")
    GAUSSIAN("gaussian"),
    @XmlEnumValue("extremevalue")
    EXTREMEVALUE("extremevalue"),
    @XmlEnumValue("gamma")
    GAMMA("gamma"),
    @XmlEnumValue("evd")
    EVD("evd"),
    @XmlEnumValue("non-parametric")
    NON_PARAMETRIC("non-parametric");
    private final String value;

    ModelDisType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ModelDisType fromValue(String v) {
        for (ModelDisType c: ModelDisType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
