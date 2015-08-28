/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.datatypes.index.impl;

import umich.ms.datatypes.index.IndexElement;

/**
 *
 * @author Dmitry Avtonomov
 */
public class IndexElementDefault implements IndexElement {

    /**
     * Internal scan number.
     */
    protected final int num;
    /**
     * Raw scan number from raw file.
     */
    protected final int numRaw;

    public IndexElementDefault(int num, int numRaw) {
        this.num = num;
        this.numRaw = numRaw;
    }

    @Override
    public int getNumber() {
        return num;
    }

    @Override
    public int getRawNumber() {
        return numRaw;
    }

    @Override
    public String getId() {
        return String.valueOf(numRaw);
    }

    @Override
    public String toString() {
        return "Num: " + num + " RawNum: " + numRaw + " ID: " + getId();
    }
}
