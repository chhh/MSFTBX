package umich.ms.fileio.filetypes.mzml;

import java.util.Arrays;

public class OpenList<T> {
  public T[] items;
  public int lo;
  public int hi;

  public int capacityLeft() {
    return hi - items.length;
  }

  public void capacityAddForExtra(int extraItems) {
    int toAllocate = extraItems - capacityLeft();
    if (toAllocate > 0) {
      items = Arrays.copyOf(items, items.length + toAllocate);
    }
  }

  public void reset() {
    lo = 0;
    hi = 0;
  }
}
