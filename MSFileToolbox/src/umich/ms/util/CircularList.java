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
import java.util.LinkedList;

/**
 * @author Dmitry Avtonomov
 */
class CircularList<E> extends LinkedList<E> {

  Iterator<E> circularIter = null;

  E nextItem() {
    if (circularIter == null) {
      circularIter = iterator();
    } else if (!circularIter.hasNext()) {
      circularIter = iterator();
    }
    return circularIter.next();
  }

  void destroyIterationCircle() {
    circularIter = null;
  }

}
