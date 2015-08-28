package umich.ms.fileio.filetypes.xmlbased;


import java.util.concurrent.Callable;

/**
 * @author Dmitry Avtonomov
 * @param <T> type of elements in the index.
 */
public interface IndexBuilder<T extends XMLBasedIndexElement> extends Callable<IndexBuilderResult<T>> {
    IndexBuilderResult<T> buildIndex(long offsetInFile, long offsetInBuffer) throws Exception;
}
