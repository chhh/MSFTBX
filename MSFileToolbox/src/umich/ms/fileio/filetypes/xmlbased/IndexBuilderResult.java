package umich.ms.fileio.filetypes.xmlbased;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Avtonomov
 * @param <T> type of index elements
 */
public class IndexBuilderResult <T extends XMLBasedIndexElement> {
    private List<T> indexElements;
    private List<T> unfinishedIndexElements;

    public IndexBuilderResult() {
        this.indexElements = new ArrayList<>(100);
        this.unfinishedIndexElements = new ArrayList<>(1);
    }

    public List<T> getIndexElements() {
        return indexElements;
    }

    public List<T> getUnfinishedIndexElements() {
        return unfinishedIndexElements;
    }

    public boolean addIndexElement(T indexElement) {
        return indexElements.add(indexElement);
    }

    public boolean addUnfinishedIndexElement(T indexElement) {
        return unfinishedIndexElements.add(indexElement);
    }
}
