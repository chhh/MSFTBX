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

package com.dmtavt.batmass.io.thermo;

import java.io.InputStream;
import java.io.OutputStream;

public class ExternalRunningServer implements IGrpcServerProcess {

  private final int port;

  public ExternalRunningServer(int port) {
    this.port = port;
  }

  @Override
  public String serverName() {
    return "External server on port: " + port;
  }

  @Override
  public boolean isRunning() {
    return true;
  }

  @Override
  public Integer port() {
    return port;
  }

  @Override
  public Process stop() {
    return new Process() {
      @Override
      public OutputStream getOutputStream() {
        return null;
      }

      @Override
      public InputStream getInputStream() {
        return null;
      }

      @Override
      public InputStream getErrorStream() {
        return null;
      }

      @Override
      public int waitFor() throws InterruptedException {
        return 0;
      }

      @Override
      public int exitValue() {
        return 0;
      }

      @Override
      public void destroy() {

      }
    };
  }

  @Override
  public IGrpcServerProcess start() {
    return this;
  }
}
