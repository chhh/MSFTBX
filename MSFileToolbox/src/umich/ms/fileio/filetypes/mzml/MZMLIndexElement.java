/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.fileio.filetypes.mzml;

import umich.ms.fileio.filetypes.xmlbased.AbstractXMLBasedIndexElement;
import umich.ms.fileio.filetypes.xmlbased.OffsetLength;

/**
 *
 * @author Dmitry Avtonomov
 */
public class MZMLIndexElement extends AbstractXMLBasedIndexElement {
    protected final String id;

    public MZMLIndexElement(int num, int numRaw, String id, OffsetLength offlen) {
        super(num, numRaw, offlen);
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
