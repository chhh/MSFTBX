package umich.ms.fileio.filetypes.mzml;

import java.util.Arrays;
import java.util.StringJoiner;

public class Trace {
  double[] mzs;
  float[] abs;
  int[] scanNums;
  int countNonZeros;
  int zeroAbStretch;
  double abSum;
  double mzSum;
  double mzSumWeighted;
  double mzAvg;
  double mzAvgWeighted;
  int ptr = -1;

  public Trace(int initSize) {
    mzs = new double[initSize];
    abs = new float[initSize];
    scanNums = new int[initSize];
  }

  public final void add(double mz, float ab, int scanNum) {
    ensureCapacity(10);
    ptr += 1;
    mzs[ptr] = mz;
    abs[ptr] = ab;
    scanNums[ptr] = scanNum;

    if (ab > 0) {
      countNonZeros += 1;
      mzSum += mz;
    }
    abSum += ab;
    mzSumWeighted += mz * ab;
    mzAvg = mzSum / countNonZeros;
    mzAvgWeighted = mzSumWeighted / abSum;
  }

  public int size() {
    return ptr + 1;
  }

  public final void ensureCapacity(int extendBy) {
    if (mzs.length == ptr + 1) {
      int newLen = mzs.length + extendBy;
      mzs = Arrays.copyOf(mzs, newLen);
      abs = Arrays.copyOf(abs, newLen);
      scanNums = Arrays.copyOf(scanNums, newLen);
    }
  }
  public final void reset() {
    ptr = -1;
    abSum = 0;
    mzSum = 0;
    mzAvg = 0;
    mzAvgWeighted = 0;
  }

  public void restart(double mz, float ab, int scanNum) {
    reset();
    add(mz, ab, scanNum);
  }

  private String makeId(double mz, int scanNum) {
    return String.format("%.5f@%d", mz, scanNum);
  }

  @Override
  public String toString() {
    if (ptr < 0) {
      return Trace.class.getSimpleName() + "[empty, capacity: " + mzs.length + "]";
    }
    return new StringJoiner(", ", Trace.class.getSimpleName() + "[", "]")
        .add(String.format("mz: %.4f", mzs[0]))
        .add(String.format("@#: %d", scanNums[0]))
        .add(String.format("len: %d", ptr + 1))
        .add(String.format("capacity: %d", mzs.length))
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Trace trace = (Trace) o;

    if (Double.compare(mzs[0], trace.mzs[0]) != 0) return false;
    return scanNums[0] == trace.scanNums[0];
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(mzs[0]);
    result = (int) (temp ^ (temp >>> 32));
    result = 31 * result + scanNums[0];
    return result;
  }
}
