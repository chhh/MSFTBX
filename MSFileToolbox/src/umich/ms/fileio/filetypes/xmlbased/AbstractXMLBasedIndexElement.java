/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.fileio.filetypes.xmlbased;

import umich.ms.datatypes.index.impl.IndexElementDefault;

/**
 *
 * @author Dmitry Avtonomov
 */
public class AbstractXMLBasedIndexElement extends IndexElementDefault implements XMLBasedIndexElement {
    /** The offset and length of scan entry in the XML file. */
    protected OffsetLength offlen;

    public AbstractXMLBasedIndexElement(int num, int numRaw, OffsetLength offlen) {
        super(num, numRaw);
        this.offlen = offlen;
    }

    @Override
    public OffsetLength getOffsetLength() {
        return offlen;
    }

    /**
     * Use carefully, the only intended use for this mutator is to extend the already existing offset-length pairs
     * if the original length was not extending all the way up to the next "scan" tag.
     * @param offlen
     */
    @Override
    public void setOffsetLength(OffsetLength offlen) {
        this.offlen = offlen;
    }
}
