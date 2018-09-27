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
package umich.ms.fileio.exceptions;

/**
 * Created by Dmitry Avtonomov on 2015-10-26.
 */
public class RunHeaderBoundsNotFound extends RunHeaderParsingException {

  public RunHeaderBoundsNotFound() {
  }

  public RunHeaderBoundsNotFound(String message) {
    super(message);
  }

  public RunHeaderBoundsNotFound(String message, Throwable cause) {
    super(message, cause);
  }

  public RunHeaderBoundsNotFound(Throwable cause) {
    super(cause);
  }
}
