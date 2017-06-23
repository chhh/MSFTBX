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
package umich.ms.util;

import java.util.Iterator;
import java.util.Objects;

/**
 * String utilities.
 */
public abstract class StringUtils {
    private StringUtils() {
        throw new AssertionError("This class can't be instantiated");
    }

    public static boolean isNullOrEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNullOrWhitespace(CharSequence s) {
        return s == null || isWhitespace(s);
    }

    public static boolean isNullOrBlank(CharSequence s) {
        return s == null || s.length() == 0 || isWhitespace(s);
    }

    private static boolean isWhitespace(CharSequence s) {
        int length = s.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Join strings with a separator.
     * @param iterable
     * @param separator
     * @return
     */
    public static String join(final Iterable<?> iterable, final String separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }

    /**
     * Join strings with a separator.
     * @param iterator
     * @param separator
     * @return
     */
    public static String join(final Iterator<?> iterator, final String separator) {

        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        final Object first = iterator.next();
        if (!iterator.hasNext()) {
            final String result = Objects.toString(first);
            return result;
        }

        // two or more elements
        final StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            final Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    /**
     * A common method for all enums since they can't have another base class
     * @param <T> Enum type
     * @param c enum type. All enums must be all caps.
     * @param string case insensitive
     * @return corresponding enum, or null
     */
    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if( c != null && string != null ) {
            return Enum.valueOf(c, string.trim().toUpperCase());
        }
        return null;
    }
}
