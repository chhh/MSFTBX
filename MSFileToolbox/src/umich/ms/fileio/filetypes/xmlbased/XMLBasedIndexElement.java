/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.fileio.filetypes.xmlbased;

import umich.ms.datatypes.index.IndexElement;

/**
 *
 * @author Dmitry Avtonomov
 */
public interface XMLBasedIndexElement extends IndexElement {
    OffsetLength getOffsetLength();

    /**
     * This mutator is needed to facilitate "fixing" of index after it was created.
     * @param offlen
     */
    void setOffsetLength(OffsetLength offlen);
}
