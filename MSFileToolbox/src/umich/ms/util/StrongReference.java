/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.util;

import java.lang.ref.WeakReference;

/**
 * Use when you need to be able to switch between Weak and Strong referencing.
 * @author Dmitry Avtonomov
 * @param <T>
 */
public class StrongReference<T> extends WeakReference<T>{
    private T referent;

    public StrongReference(T referent) {
        super(null);
        this.referent = referent;
    }

    @Override
    public boolean enqueue() {
        return false;
    }

    @Override
    public boolean isEnqueued() {
        return false;
    }

    @Override
    public void clear() {
        referent = null;
    }

    @Override
    public T get() {
        return referent;
    }
}
