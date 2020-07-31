/*
 * Copyright (c) 2019 Dmitry Avtonomov
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

package umich.ms.fileio.filetypes.bruker.dao;


public class GroupProperty {

  private long propertyGroup;
  private long property;
  private String value;


  public long getPropertyGroup() {
    return propertyGroup;
  }

  public void setPropertyGroup(long propertyGroup) {
    this.propertyGroup = propertyGroup;
  }


  public long getProperty() {
    return property;
  }

  public void setProperty(long property) {
    this.property = property;
  }


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
