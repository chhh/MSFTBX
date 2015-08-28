/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.fileio.filetypes.mzxml;

import umich.ms.fileio.filetypes.xmlbased.AbstractXMLBasedIndexElement;
import umich.ms.fileio.filetypes.xmlbased.OffsetLength;

/**
 *
 * @author Dmitry Avtonomov
 */
public class MZXMLIndexElement extends AbstractXMLBasedIndexElement {

    public MZXMLIndexElement(int num, int numRaw, OffsetLength offlen) {
        super(num, numRaw, offlen);
    }


    @Override
    public String getId() {
        return String.valueOf(numRaw);
    }
    
}
