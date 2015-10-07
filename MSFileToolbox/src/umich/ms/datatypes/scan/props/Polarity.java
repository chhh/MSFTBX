package umich.ms.datatypes.scan.props;

import java.io.Serializable;

/**
 * Author: Dmitry Avtonomov (dmitriya)
 * Email: dmitriy.avtonomov@gmail.com
 * Date: 3/25/13
 * Time: 5:11 PM
 */
public enum Polarity {
    POSITIVE (+1, "+"),
    NEGATIVE (-1, "-"),
    NEUTRAL  ( 0, " ");

    public int polarity;
    String strRepresentation;

    Polarity(int polarity, String strRepresentation) {
        this.polarity = polarity;
        this.strRepresentation = strRepresentation;
    }

    public int getSign() {
        return polarity;
    }

    /**
     * Parse Polarity from a string.
     * @param s string to be parsed, must match exactly one of the polarity values
     * @return null, if the string is not one of the string values of this enum
     */
    public static Polarity parse(String s) {
        for (int i = 0; i < Polarity.values().length; i++) {
            if (Polarity.values()[i].equals(s)) {
                return Polarity.values()[i];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return strRepresentation;
    }
}
