package com.lovelz.lzlearncenter.memory.design;

import android.util.SparseArray;

/**
 *
 * 对象池模式
 *
 * @author lovelz
 * @date on 2018/11/5.
 */
public abstract class ObjectPool<T> {

    private SparseArray<T> freePool;
    private SparseArray<T> lentPool;
    private int maxCapacity;

    public ObjectPool(int maxCapacity) {
        this(maxCapacity / 2, maxCapacity);
    }

    public ObjectPool(int initialCapacity, int maxCapacity) {
        initialize(initialCapacity);
        this.maxCapacity = maxCapacity;
    }

    private void initialize(int initialCapacity) {
        freePool = new SparseArray<>();
        lentPool = new SparseArray<>();
        for (int i = 0; i < initialCapacity; i++) {
            freePool.put(i, create());
        }
    }

    public T acquire() {
        T t = null;
        synchronized (freePool) {
            int freeSize = freePool.size();
            for (int i = 0; i < freeSize; i++) {
                int key = freePool.keyAt(i);
                t = freePool.get(key);
                if (t != null) {
                    this.lentPool.put(key, t);
                    this.freePool.remove(key);
                    return t;
                }
            }

            if (t == null && (lentPool.size() + freeSize < maxCapacity)) {
                t = create();
                lentPool.put(lentPool.size() + freeSize, t);
            }
        }

        return t;
    }

    public void release(T t) {
        if (t == null) {
            return;
        }

        int key = lentPool.indexOfValue(t);
        restore(t);
        freePool.put(key, t);
        lentPool.remove(key);
    }


    protected abstract T create();

    protected void restore(T t) {

    }
}
