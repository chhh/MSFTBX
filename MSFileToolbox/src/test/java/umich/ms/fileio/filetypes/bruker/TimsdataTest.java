package umich.ms.fileio.filetypes.bruker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimsdataTest {
  private static final Logger log = LoggerFactory.getLogger(TimsdataTest.class);

  @Test
  public void getLoadedLibName() throws Exception {
    String loadedLibName = Timsdata.getLoadedLibName();
    Assert.assertEquals("Loaded lib name", Timsdata.BRUKER_LIB_NAME, loadedLibName);
  }
}
