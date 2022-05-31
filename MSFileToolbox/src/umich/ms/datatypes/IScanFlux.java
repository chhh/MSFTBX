package umich.ms.datatypes;

import reactor.core.publisher.Flux;
import umich.ms.datatypes.scan.IScan;
import umich.ms.fileio.exceptions.FileParsingException;

public interface IScanFlux {
  default boolean supportsScanFlux() {
    return false;
  }
  default Flux<IScan> flux() throws FileParsingException {
    throw new UnsupportedOperationException("Not supported. Call .supportsScanFlux() to " +
        "check if the operation is supported by a source.");
  }
}
