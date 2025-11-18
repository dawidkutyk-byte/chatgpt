/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.internal.LinkedHashTreeMap$Node
 */
package com.google.gson.internal;

import com.google.gson.internal.LinkedHashTreeMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

final class LinkedHashTreeMap.EntrySet
extends AbstractSet<Map.Entry<K, V>> {
    @Override
    public boolean contains(Object o) {
        return o instanceof Map.Entry && LinkedHashTreeMap.this.findByEntry((Map.Entry)o) != null;
    }

    LinkedHashTreeMap.EntrySet() {
    }

    @Override
    public void clear() {
        LinkedHashTreeMap.this.clear();
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof Map.Entry)) {
            return false;
        }
        LinkedHashTreeMap.Node node = LinkedHashTreeMap.this.findByEntry((Map.Entry)o);
        if (node == null) {
            return false;
        }
        LinkedHashTreeMap.this.removeInternal(node, true);
        return true;
    }

    @Override
    public int size() {
        return LinkedHashTreeMap.this.size;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new /* Unavailable Anonymous Inner Class!! */;
    }
}
