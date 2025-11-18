/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map;

class LinkedTreeMap.EntrySet.1
extends LinkedTreeMap.LinkedTreeMapIterator<Map.Entry<K, V>> {
    LinkedTreeMap.EntrySet.1() {
        super(EntrySet.this.this$0);
    }

    @Override
    public Map.Entry<K, V> next() {
        return this.nextNode();
    }
}
