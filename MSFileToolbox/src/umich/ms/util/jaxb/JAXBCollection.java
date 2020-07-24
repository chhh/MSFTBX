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
package umich.ms.util.jaxb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAnyElement;

/**
 * Generic collection wrapper class.
 *
 * Makes it easier to (un)marshall a collectiono of a single type.
 *
 * @author Arno Moonen <info@arnom.nl>
 */
public class JAXBCollection<T> {

  @XmlAnyElement(lax = true)
  private List<T> items;

  public JAXBCollection(Collection<T> contents) {
    if (contents instanceof List) {
      items = (List<T>) contents;
    } else {
      items = new ArrayList<T>(contents);
    }

  }

  public JAXBCollection() {
    this(new ArrayList<T>());
  }

  public List<T> getItems() {
    return items;
  }

}
