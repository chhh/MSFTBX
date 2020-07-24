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

import java.lang.ref.WeakReference;

/**
 * Use when you need to be able to switch between Weak and Strong referencing.
 *
 * @author Dmitry Avtonomov
 */
public class StrongReference<T> extends WeakReference<T> {

  private T referent;

  public StrongReference(T referent) {
    super(null);
    this.referent = referent;
  }

  @Override
  public boolean enqueue() {
    return false;
  }

  @Override
  public boolean isEnqueued() {
    return false;
  }

  @Override
  public void clear() {
    referent = null;
  }

  @Override
  public T get() {
    return referent;
  }
}
