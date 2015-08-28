/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.util;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dmitriya
 */
class CircularList<E> extends LinkedList<E> {
    Iterator<E> circularIter = null;

    E nextItem() {
        if (circularIter == null) {
            circularIter = iterator();
        } else if (!circularIter.hasNext()) {
            circularIter = iterator();
        }
        return circularIter.next();
    }

    void destroyIterationCircle() {
        circularIter = null;
    }

}
