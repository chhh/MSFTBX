
/*
 * Copyright (c) 2019 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.pepxml.jaxb.standard;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter3
    extends XmlAdapter<String, Double>
{


    public Double unmarshal(String value) {
        return (javax.xml.bind.DatatypeConverter.parseDouble(value));
    }

    public String marshal(Double value) {
        if (value == null) {
            return null;
        }
        return (javax.xml.bind.DatatypeConverter.printDouble(value));
    }

}
