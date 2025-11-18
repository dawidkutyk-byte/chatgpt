/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.LinkedTreeMap;
import java.util.AbstractSet;
import java.util.Iterator;

final class LinkedTreeMap.KeySet
extends AbstractSet<K> {
    @Override
    public Iterator<K> iterator() {
        return new LinkedTreeMap.LinkedTreeMapIterator<K>(){

            @Override
            public K next() {
                return this.nextNode().key;
            }
        };
    }

    @Override
    public boolean contains(Object o) {
        return LinkedTreeMap.this.containsKey(o);
    }

    @Override
    public void clear() {
        LinkedTreeMap.this.clear();
    }

    @Override
    public int size() {
        return LinkedTreeMap.this.size;
    }

    LinkedTreeMap.KeySet() {
    }

    @Override
    public boolean remove(Object key) {
        return LinkedTreeMap.this.removeInternalByKey(key) != null;
    }
}
