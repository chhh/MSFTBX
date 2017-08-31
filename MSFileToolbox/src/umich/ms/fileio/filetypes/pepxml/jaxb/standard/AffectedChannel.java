
/*
 * Copyright (c) 2017 Dmitry Avtonomov
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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="channel" use="required" type="{http://regis-web.systemsbiology.net/pepXML}positiveInt" />
 *       &lt;attribute name="correction" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class AffectedChannel {

    @XmlAttribute(name = "channel", required = true)
    protected long channel;
    @XmlAttribute(name = "correction", required = true)
    protected float correction;

    /**
     * Gets the value of the channel property.
     * 
     */
    public long getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     */
    public void setChannel(long value) {
        this.channel = value;
    }

    /**
     * Gets the value of the correction property.
     * 
     */
    public float getCorrection() {
        return correction;
    }

    /**
     * Sets the value of the correction property.
     * 
     */
    public void setCorrection(float value) {
        this.correction = value;
    }

}
