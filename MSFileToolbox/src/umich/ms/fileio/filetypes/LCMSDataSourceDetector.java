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
package umich.ms.fileio.filetypes;

import java.nio.file.Path;

/**
 * Implementations of this interface should be marked as Service Providers using {@code @ProviderFor} annotation.
 * Those implementations should follow the standard {@code Service Provider} rules:<br/>
 *  - The class must implement the target interface (this interface in this case)<br/>
 *  - The class must provide a no-args constructor<br/>
 *  - The class must be public<br/>
 *
 *
 * @author Dmitry Avtonomov
 */
public interface LCMSDataSourceDetector {
    /**
     *
     * @param path
     */
    void accepts (Path path);
}
