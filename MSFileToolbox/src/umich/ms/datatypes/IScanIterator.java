package umich.ms.datatypes;

import umich.ms.datatypes.scan.IScan;

import java.util.Iterator;

public interface IScanIterator extends Iterable<IScan> {

    default Iterable<IScan> iterate() {
        throw new UnsupportedOperationException();
    }

    default boolean supportsIterate() {
        return false;
    }
}
