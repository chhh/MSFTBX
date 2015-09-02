//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.19 at 05:41:02 PM EDT 
//


package umich.ms.fileio.filetypes.pepxml.jaxb;

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
