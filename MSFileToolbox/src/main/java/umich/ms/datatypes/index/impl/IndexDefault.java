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
package umich.ms.datatypes.index.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import umich.ms.datatypes.index.Index;
import umich.ms.datatypes.index.IndexElement;

/**
 * Raw and ID maps are lazily created on the first request for a search. Most of the time users will
 * only query the index by the internal number, unless a different mapping is required for something
 * like mapping from .pep.xml results, which stores some raw identifier.
 *
 * <b>This class is not thread safe during the creation stage.</b>
 * Queries after creation can be done from multiple threads. NOTE: Each add operation will clear
 * internal maps for raw numbers
 *
 * @author Dmitry Avtonomov
 */
public class IndexDefault<T extends IndexElement> implements Index<T> {

  private static final long serialVersionUID = 2038378308383734437L;

  protected final TreeMap<Integer, T> mapByNum;
  protected volatile TreeMap<Integer, T> mapByRawNum;
  protected volatile HashMap<String, T> mapById;

  public IndexDefault() {
    mapByNum = new TreeMap<>();
  }

  @Override
  public NavigableMap<Integer, T> getMapByNum() {
    return mapByNum;
  }

  @Override
  public NavigableMap<Integer, T> getMapByRawNum() {
    TreeMap<Integer, T> tmp = mapByRawNum;
    if (tmp == null) {
      synchronized (this) {
        tmp = mapByRawNum;
        if (tmp == null) {
          tmp = createMapByRawNum();
          mapByRawNum = tmp;
        }
      }
    }
    return tmp;
  }

  @Override
  public Map<String, T> getMapById() {
    HashMap<String, T> tmp = mapById;
    if (tmp == null) {
      synchronized (this) {
        tmp = mapById;
        if (tmp == null) {
          tmp = createMapById();
          mapById = tmp;
        }
      }
    }
    return tmp;
  }

  @Override
  public int size() {
    return mapByNum.size();
  }

  /**
   * Add a {@link umich.ms.datatypes.index.IndexElement} to the index. Don't use this method, unless
   * you're building a new index.
   *
   * @param indexElement scan index element implementation
   */
  @Override
  public void add(T indexElement) {
    mapByNum.put(indexElement.getNumber(), indexElement);
    if (mapByRawNum != null) {
      mapByRawNum.put(indexElement.getRawNumber(), indexElement);
    }
    if (mapById != null) {
      mapById.put(indexElement.getId(), indexElement);
    }
  }

  @Override
  public T getByNum(int num) {
    return mapByNum.get(num);
  }

  @Override
  public T getByRawNum(int num) {
    NavigableMap<Integer, T> tmp = getMapByRawNum();
    return tmp.get(num);
  }

  @Override
  public T getById(String id) {
    Map<String, T> tmp = getMapById();
    return tmp.get(id);
  }

  private TreeMap<Integer, T> createMapByRawNum() {
    TreeMap<Integer, T> map = new TreeMap<>();
    for (Map.Entry<Integer, T> entrySet : mapByNum.entrySet()) {
      T value = entrySet.getValue();
      map.put(value.getRawNumber(), value);
    }
    return map;
  }

  private HashMap<String, T> createMapById() {
    HashMap<String, T> map = new HashMap<>();
    for (Map.Entry<Integer, T> entrySet : mapByNum.entrySet()) {
      T value = entrySet.getValue();
      String id = value.getId();
      if (id == null) {
//                throw new IllegalStateException("This paticular file type's index"
//                        + " did not implement returning a valid string ID for"
//                        + " a scan.");
        id = String.valueOf(value.getRawNumber());
      }
      map.put(id, value);
    }
    return map;
  }
}
