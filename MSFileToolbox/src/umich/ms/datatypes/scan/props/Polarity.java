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
