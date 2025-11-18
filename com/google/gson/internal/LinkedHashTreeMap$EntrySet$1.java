/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.internal.LinkedHashTreeMap$LinkedTreeMapIterator
 */
package com.google.gson.internal;

import com.google.gson.internal.LinkedHashTreeMap;
import java.util.Map;

class LinkedHashTreeMap.EntrySet.1
extends LinkedHashTreeMap.LinkedTreeMapIterator<Map.Entry<K, V>> {
    public Map.Entry<K, V> next() {
        return this.nextNode();
    }

    LinkedHashTreeMap.EntrySet.1() {
        super(EntrySet.this.this$0);
    }
}
