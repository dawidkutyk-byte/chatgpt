/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.LinkedTreeMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

class LinkedTreeMap.EntrySet
extends AbstractSet<Map.Entry<K, V>> {
    LinkedTreeMap.EntrySet() {
    }

    @Override
    public void clear() {
        LinkedTreeMap.this.clear();
    }

    @Override
    public boolean contains(Object o) {
        return o instanceof Map.Entry && LinkedTreeMap.this.findByEntry((Map.Entry)o) != null;
    }

    @Override
    public int size() {
        return LinkedTreeMap.this.size;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof Map.Entry)) {
            return false;
        }
        LinkedTreeMap.Node node = LinkedTreeMap.this.findByEntry((Map.Entry)o);
        if (node == null) {
            return false;
        }
        LinkedTreeMap.this.removeInternal(node, true);
        return true;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new LinkedTreeMap.LinkedTreeMapIterator<Map.Entry<K, V>>(){

            @Override
            public Map.Entry<K, V> next() {
                return this.nextNode();
            }
        };
    }
}
