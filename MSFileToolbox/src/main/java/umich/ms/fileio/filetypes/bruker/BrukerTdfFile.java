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

package umich.ms.fileio.filetypes.bruker;

import com.sun.jna.Pointer;
import umich.ms.fileio.filetypes.bruker.dao.Frame;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.fileio.filetypes.bruker.dao.PasefFrameMsMsInfo;

public class BrukerTdfFile implements AutoCloseable {

  private static final Logger log = LoggerFactory.getLogger(BrukerTdfFile.class);

  public final Path pathDir;
  private final Path pathAnalysisTdf;
  final BrukerTdfSql sql;
  private static final String ANALYSIS_TDF_FN = "analysis.tdf";


  private long timsHandle;
  private byte[] readBuf;
  private int[] indexBuf;
  private byte[] errorBuf;
  private final Object readLock = new Object();

  public BrukerTdfFile(String pathDir) {
    try {
      this.pathDir = Paths.get(pathDir).toAbsolutePath().normalize();
      if (!Files.exists(this.pathDir)) {
        throw new FileNotFoundException("Path not exists");
      }
      if (!Files.isDirectory(this.pathDir)) {
        throw new IllegalArgumentException("Path must be a directory");
      }
      pathAnalysisTdf = this.pathDir.resolve(ANALYSIS_TDF_FN);
      if (!Files.exists(pathAnalysisTdf)) {
        throw new IllegalArgumentException("Bruker data dir must contain an `analysis.tdf` file");
      }

    } catch (Exception e) {
      throw new IllegalArgumentException("Not a valid pathDir for Bruker data directory", e);
    }

    sql = new BrukerTdfSql(pathAnalysisTdf);

    setupJna();
    readBuf = new byte[2 << 20]; // about 2M bytes
    indexBuf = new int[readBuf.length / 4 + 2048];
    errorBuf = new byte[2 << 10];
  }

  @Override
  public void close() {
    if (sql != null) {
      try {
        sql.close();
      } catch (Exception e) {
        log.error("Could not release sql db connection", e);
      }
    }
    if (timsHandle != 0) {
      try {
        Timsdata.getInstance().tims_close(timsHandle);
      } catch (Exception e) {
        log.error("Error closing file by TIMS lib", e);
      }
    }
  }

  private void setupJna() {
    timsHandle = Timsdata.getInstance().tims_open(pathDir.toString(), 0);
    if (timsHandle == 0) {
      throw new IllegalStateException("Timsdata could not open the file");
    }
  }

  /**
   * That's how you know which scan numbers (IDs) exist in the file. Scan numbers are not guaranteed
   * to be consecutive integers.
   */
  public List<FrameInfo> readFrameInfos() {
    List<Frame> frames;
    try {
      frames = sql.sqlReadAllFrames();
    } catch (SQLException e) {
      throw new IllegalStateException("Could not read Frames information", e);
    }
    List<FrameInfo> frameInfos = frames.stream()
        .map(f -> new FrameInfo(f.getId(), f.getTime(), f.getScanMode(), f.getMsMsType()))
        .collect(Collectors.toList());
    return frameInfos;
  }

  /**
   * Reads the 2D IM spectrum as a peaklist, i.e. each data point gets assigned its own IM value.
   */
  private Peaklist2D nativeReadRawFrame(Frame f) throws BrukerException {
    if (timsHandle == 0) {
      throw new IllegalStateException(
          "TDF file handle was zero, meaning the .d directory was not opened by the native bruker lib.");
    }

    final long frameId = f.getId();

    long bytesNeededForQuery;
    int N = (int) f.getNumScans(); // num scans to read
    synchronized (readLock) {
      boolean grewBuffer;
      do {
        grewBuffer = false;
        bytesNeededForQuery = Timsdata.getInstance()
            .tims_read_scans_v2(timsHandle, frameId, 0, N, readBuf, readBuf.length);
        if (bytesNeededForQuery == 0 && f.getNumPeaks() == 0) {
          // safety net, not sure what happens in this case
          log.warn("tims_read_scans_v2() returned 0, which normally means an error has occurred. "
              + "However, frame {} contained zero peaks according to analysis.tdf. "
              + "Please, report to developers.", frameId);
        } else if (bytesNeededForQuery == 0) {
          // means Timsdata reading error
          throw new BrukerException(
              "Error in call to tims_read_scans_v2(): " + nativeGetLastError());
        }
        if (bytesNeededForQuery > readBuf.length) {
          log.debug("Growing read buffer");
          long delta = bytesNeededForQuery - readBuf.length;
          if (Integer.MAX_VALUE - readBuf.length - 256 < delta) {
            throw new BrukerException("Not enough space to grow the read buffer.");
          }
          // grow the read buffer
          readBuf = new byte[readBuf.length + (int) delta];
          grewBuffer = true;
        }
      } while (grewBuffer);
    }

    if (bytesNeededForQuery > Integer.MAX_VALUE) {
      throw new BrukerException("The byte array returned from Bruker's library was too long "
          + "to wrap a bytebuffer around it.");
    }
//    IntBuffer src = ByteBuffer.wrap(readBuf).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
    IntBuffer src = ByteBuffer.wrap(readBuf, 0, (int) bytesNeededForQuery)
        .order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
    if (indexBuf.length < src.remaining()) {
      log.debug("Growing index buffer");
      int delta = src.remaining() - indexBuf.length;
      if (delta > Integer.MAX_VALUE - indexBuf.length - 256) {
        throw new BrukerException("Not enough space to grow the index buffer.");
      }
      indexBuf = new int[indexBuf.length + delta];
    }
    final int indexBufLimit = src.remaining();
    src.get(indexBuf, 0, indexBufLimit);

    int calcNumPoints = 0;
    for (int i = 0; i < N; i++) {
      calcNumPoints += indexBuf[i];
    }
    if (calcNumPoints != f.getNumPeaks()) {
      log.warn("Scan id: {}. Read number of data points ({}) differs from the value "
          + "in analysis.tdf ({})", frameId, calcNumPoints, f.getNumPeaks());
    }

    int expectedLen = N + calcNumPoints * 2;
    if (indexBufLimit != expectedLen) {
      log.warn("Scan id: {}. 'indexBufLimit' ({}) did not match the expected result "
          + "(N + calcNumPoints * 2 = {})", frameId, indexBufLimit, expectedLen);
    }

    double[] scanNums = new double[N];
    for (int i = 0; i < N; i++) {
//      scanNums[i] = N - 1 - i; // that reversal was in bruker's example
      scanNums[i] = i; // that reversal was in bruker's example
    }

    double[] oneOverK0;
    oneOverK0 = convertImScanNumTo1k0(frameId, scanNums);


//    long error = Timsdata.getInstance()
//        .tims_scannum_to_oneoverk0(timsHandle, frameId, scanNums, oneOverK0, N);
//    if (0 == error) {
//      throw new BrukerException("Error converting scan numbers to 1/k0: " + nativeGetLastError());
//    }

//    System.out.print("Scan Nums\t");
//    for (int i = 0; i < scanNums.length; i++) {
//      System.out.printf("%.5f\t", scanNums[i]);
//    }
//    System.out.println();
//    System.out.print("1/k0\t");
//    for (int i = 0; i < oneOverK0.length; i++) {
//      System.out.printf("%.5f\t", oneOverK0[i]);
//    }
//    System.out.println();

    double[] mzsIndexes = new double[calcNumPoints];
    double[] abs = new double[calcNumPoints];
    double[] ims = new double[calcNumPoints];

    int scanPtr = N;
    int ourPtr = 0;
    for (int iScan = 0; iScan < N; iScan++) {
      final int scanSz = indexBuf[iScan];
      copyArray(indexBuf, scanPtr, mzsIndexes, ourPtr, scanSz);
      scanPtr += scanSz;
      copyArray(indexBuf, scanPtr, abs, ourPtr, scanSz);
      scanPtr += scanSz;
//      double im = iScan; // im as scan number
      double im = oneOverK0[iScan]; // im as 1/k0
      Arrays.fill(ims, ourPtr, ourPtr + scanSz, im);
      ourPtr += scanSz;
    }

    double[] mzs = new double[calcNumPoints];
    long error = Timsdata.getInstance().tims_index_to_mz(timsHandle, frameId, mzsIndexes, mzs, mzs.length);
    if (0 == error) {
      throw new BrukerException(
          "Error converting mz indexes to mz values: " + nativeGetLastError());
    }

    return new Peaklist2D(mzs, abs, ims);
  }

  private void copyArray(int[] src, int srcOffset, double[] dest, int destOffset, int len) {
    for (int i = 0; i < len; i++) {
      dest[destOffset + i] = src[srcOffset + i];
    }
  }

  /**
   * This method only reads the bare spectrum. If it's an MS2 PASEF scan and there are IM sub-ranges
   * for each precursor, it still reads and reports the whole 2D spectrum.
   */
  public Peaklist2D readFrameRaw(long frameId) throws BrukerException {

    Frame frame;
    try {
      frame = sql.sqlReadFrame(frameId);
    } catch (SQLException e) {
      throw new BrukerException(e);
    }

    Peaklist2D spectrum = nativeReadRawFrame(frame);
    return spectrum;
  }

  public Frame readFrameInfoRaw(long frameId) throws BrukerException {
    Frame frame;
    try {
      return sql.sqlReadFrame(frameId);
    } catch (SQLException e) {
      throw new BrukerException(e);
    }
  }

  private List<CallbackVals> nativeReadPasefFrame(long frameId) throws BrukerException {
    if (timsHandle == 0) {
      throw new IllegalStateException(
          "TDF file handle was zero, meaning the .d directory was not opened by the native bruker lib.");
    }
    final List<CallbackVals> storage = Collections.synchronizedList(new ArrayList<>());
    Timsdata.msms_spectrum_functor callback = (precursor_id, num_peaks, mz_values, area_values) -> {
      ConvertedCallbackVals c = convertCallbackVals(num_peaks, mz_values, area_values);
      storage.add(new CallbackVals(precursor_id, num_peaks, c.mzArray, c.intensityArray));
    };
    long error = Timsdata.getInstance()
        .tims_read_pasef_msms_for_frame(timsHandle, frameId, callback);
    if (0 == error) {
      throw new BrukerException(
          "Error reading PASEF spectra with tims_read_pasef_msms_for_frame(): "
              + nativeGetLastError());
    }
    return storage;
  }

  private void checkTimsHandle() {
    if (timsHandle == 0) {
      throw new IllegalStateException(
          "TDF file handle was zero, meaning the .d directory was not opened by the native bruker lib.");
    }
  }

  private List<CallbackVals> nativeReadPasefFrame(List<PasefFrameMsMsInfo> infos)
      throws BrukerException {
    checkTimsHandle();

    List<Long> frameIds = infos.stream().map(PasefFrameMsMsInfo::getFrame).distinct()
        .collect(Collectors.toList());
    if (frameIds.size() > 1) {
      throw new IllegalArgumentException("All PASEF precursors must be from the same FrameId");
    }
    Long frameId = frameIds.get(0);

    final List<CallbackVals> storage = Collections.synchronizedList(new ArrayList<>());

    // The callback function definition
    Timsdata.msms_spectrum_functor callback = (precursor_id, num_peaks, mz_values, area_values) -> {
      ConvertedCallbackVals c = convertCallbackVals(num_peaks, mz_values, area_values);
      storage.add(new CallbackVals(precursor_id, num_peaks, c.mzArray, c.intensityArray));
    };

    long[] precursorIds = infos.stream().mapToLong(PasefFrameMsMsInfo::getPrecursor).toArray();

//    log.debug("Reading pasef MSMS frame #{} for precursor IDs: {}", frameId, precursorIds);
    long error = Timsdata.getInstance()
        .tims_read_pasef_msms(timsHandle, precursorIds, precursorIds.length, callback);
    if (0 == error) {
      throw new BrukerException(
          "Error reading PASEF spectra with tims_read_pasef_msms_for_frame(): "
              + nativeGetLastError());
    }
    return storage;
  }

  public List<PasefMs2Data> readFramePasef(FrameInfo f) throws BrukerException {
    try {
      List<CallbackVals> vals;
      if (f.getMsLevel() == 1) {
        vals = nativeReadPasefFrame(f.getFrameId());
      } else {
        List<PasefFrameMsMsInfo> infos = sql.sqlPasefFrameMsMsInfos(f);
        vals = nativeReadPasefFrame(infos);
      }
      List<PasefMs2Data> result = vals.isEmpty() ? Collections.emptyList() : new ArrayList<>(vals.size());
      for (CallbackVals val : vals) {
        double[] mzs = Arrays.copyOfRange(val.mz_values, 0, val.mz_values.length);
        double[] abs = new double[val.area_values.length];
        for (int i = 0; i < val.area_values.length; i++) {
          abs[i] = val.area_values[i];
        }
        result.add(new PasefMs2Data(val.precursor_id, new Peaklist1D(mzs, abs)));
      }
      return result;
    } catch (SQLException e) {
      throw new BrukerException(e);
    }
  }

  public double[] convertImScanNumTo1k0(long frameId, double[] scanNums) throws BrukerException {
    checkTimsHandle();
    final int N = scanNums.length;
    double[] oneOverK0 = new double[N];
    long error = Timsdata.getInstance()
        .tims_scannum_to_oneoverk0(timsHandle, frameId, scanNums, oneOverK0, N);
    if (0 == error) {
      throw new BrukerException("Error converting scan numbers to 1/k0: " + nativeGetLastError());
    }
    return  oneOverK0;
  }

  public double[] convertIm1k0ToScanNum(long frameId, double[] oneOverK0) throws BrukerException {
    checkTimsHandle();
    final int N = oneOverK0.length;
    double[] scanNums = new double[N];
    long error = Timsdata.getInstance()
        .tims_oneoverk0_to_scannum(timsHandle, frameId, oneOverK0, scanNums, N);
    if (0 == error) {
      throw new BrukerException("Error converting scan numbers to 1/k0: " + nativeGetLastError());
    }
    return  scanNums;
  }

  private String nativeGetLastError() {
    long len = Timsdata.getInstance().tims_get_last_error_string(errorBuf, errorBuf.length);
    return new String(errorBuf, StandardCharsets.UTF_8);
  }


  private static class CallbackVals {

    final long precursor_id;
    final long num_peaks;
    final double[] mz_values;
    final float[] area_values;

    private CallbackVals(long precursor_id, long num_peaks, double[] mz_values,
        float[] area_values) {
      this.precursor_id = precursor_id;
      this.num_peaks = num_peaks;
      this.mz_values = mz_values;
      this.area_values = area_values;
    }
  }

  private class ConvertedCallbackVals {
    final double[] mzArray;
    final float[] intensityArray;

    private ConvertedCallbackVals(double[] mzArray, float[] intensityArray) {
      this.mzArray = mzArray;
      this.intensityArray = intensityArray;
    }
  }

  private ConvertedCallbackVals convertCallbackVals(long num_peaks, Pointer mz_values, Pointer area_values) {
    if (num_peaks > Integer.MAX_VALUE - 32) {
      throw new IllegalStateException("Number of peaks too large to fit in a java array.");
    }
    double[] mzArray;
    float[] intensityArray;
    if (mz_values == null) {
      log.debug("Native callback got null for mz-values");
      mzArray = new double[0];
    } else {
      mzArray = mz_values.getDoubleArray(0, (int)num_peaks);
    }
    if (area_values == null) {
      log.debug("Native callback got null for area-values");
      intensityArray = new float[0];
    } else {
      intensityArray = area_values.getFloatArray(0, (int)num_peaks);
    }
    return new ConvertedCallbackVals(mzArray, intensityArray);
  }
}
