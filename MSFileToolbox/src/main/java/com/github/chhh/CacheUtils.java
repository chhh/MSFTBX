package com.github.chhh;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheUtils {
  private static final Logger log = LoggerFactory.getLogger(CacheUtils.class);
  public static final String SYS_TEMP_DIR = System.getProperty("java.io.tmpdir");

  private CacheUtils() {}

  private static Path locateTempFile(Path tempDir, String fn) throws FileNotFoundException {
    Path file = tempDir.resolve(fn);
    if (!Files.exists(file))
      throw new FileNotFoundException("File '" + fn + "' not found in: " + tempDir.toString());
    if (!Files.isRegularFile(file) || !Files.isReadable(file))
      throw new FileNotFoundException("File '" + file.toString() + "' is not a regular readable file.");
    return file;
  }


  /**
   * @param fn File-name for the temp file. Location is predetermined.
   */
  public static Path getTempFile(String fn) {
    Path p = getSystemTempDir().resolve(fn);
    if (!Files.exists(p.getParent())) {
      try {
        Files.createDirectories(p.getParent());
      } catch (IOException e) {
        throw new IllegalStateException("Could not create directory structure for a temporary file: " + p.toString());
      }
    }
    return p;
  }

  /**
   * System-wide temporary directory.
   * @return
   */
  public static Path getSystemTempDir() {
    if (SYS_TEMP_DIR == null || SYS_TEMP_DIR.isEmpty())
      throw new IllegalStateException("Could not locate system-wide temporary directory");
    return Paths.get(SYS_TEMP_DIR);
  }

}
