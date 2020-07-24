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
package umich.ms.util.base64;
/*
 * Base64 decoding exception.
 * Copyright (C) 2002-2010 Stephen Ostermiller
 * http://ostermiller.org/contact.pl?regarding=Java+Utilities
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * See LICENSE.txt for details.
 */

import java.io.IOException;

/**
 * Exception that is thrown when an unexpected character is encountered during Base64 decoding.  One
 * could catch this exception and use the unexpected character for some other purpose such as
 * including it with data that comes at the end of a Base64 encoded section of an email message.
 *
 * @author Stephen Ostermiller http://ostermiller.org/contact.pl?regarding=Java+Utilities
 */
public class Base64DecodingException extends IOException {

  /**
   * Serial version ID
   */
  private static final long serialVersionUID = 2411555227634603928L;
  private char c;

  /**
   * Construct an new exception.
   *
   * @param message message later to be returned by a getMessage() call.
   * @param c character that caused this error.
   */
  public Base64DecodingException(String message, char c) {
    super(message);
    this.c = c;
  }

  /**
   * Get the character that caused this error.
   *
   * @return the character that caused this error.
   */
  public char getChar() {
    return c;
  }
}
