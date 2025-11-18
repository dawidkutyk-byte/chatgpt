/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import java.util.AbstractSet;
import java.util.Iterator;

final class LinkedHashTreeMap.KeySet
extends AbstractSet<K> {
    @Override
    public Iterator<K> iterator() {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @Override
    public void clear() {
        LinkedHashTreeMap.this.clear();
    }

    @Override
    public boolean remove(Object key) {
        return LinkedHashTreeMap.this.removeInternalByKey(key) != null;
    }

    LinkedHashTreeMap.KeySet() {
    }

    @Override
    public boolean contains(Object o) {
        return LinkedHashTreeMap.this.containsKey(o);
    }

    @Override
    public int size() {
        return LinkedHashTreeMap.this.size;
    }
}
