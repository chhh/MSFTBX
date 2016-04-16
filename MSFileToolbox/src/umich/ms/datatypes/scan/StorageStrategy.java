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
package umich.ms.datatypes.scan;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.util.StrongReference;

/**
 * Enum holding spectrumRef reference types.
 * Created by dmitriya on 2015-03-04.
 */
@SuppressWarnings("rawtypes")
public enum StorageStrategy {
    STRONG(StrongReference.class, new SpecRefBuilder() {
        @Override
        public Reference<ISpectrum> build(ISpectrum spec) {
            return new StrongReference<>(spec);
        }
    }),
    SOFT(SoftReference.class, new SpecRefBuilder() {
        @Override
        public Reference<ISpectrum> build(ISpectrum spec) {
            return new SoftReference<>(spec);
        }
    }),
    WEAK(WeakReference.class, new SpecRefBuilder() {
        @Override
        public Reference<ISpectrum> build(ISpectrum spec) {
            return new WeakReference<>(spec);
        }
    });

    private final Class<? extends Reference> refType;
    private final SpecRefBuilder builder;

    StorageStrategy(Class<? extends Reference> refType, SpecRefBuilder builder) {
        this.refType = refType;
        this.builder = builder;
    }

    public Reference<ISpectrum> getRef(ISpectrum spec) {
        return builder.build(spec);
    }

    private static abstract class SpecRefBuilder {
        public abstract Reference<ISpectrum> build(ISpectrum spec);
    }
}
