package com.github.chhh;

import java.net.URI;

public class PathUtilsTest {

  public void extractMainFilePath() throws Exception {
//    String string = "jar:file:/home/ci/tmp/msfragger-20190315-thermo.one-jar.jar!/lib/lib-msftbx-grpc-1.9.6.jar";
    String string = "jar:file:/C:/home/ci/tmp/msfragger-20190315-thermo.one-jar.jar!/lib/lib-msftbx-grpc-1.9.6.jar";
    boolean isWin = false;

    URI uri = new URI(string);
    String s = PathUtils.extractMainFilePath(uri);

    int a = 1;
  }
}
