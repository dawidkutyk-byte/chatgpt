/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.internal.LinkedHashTreeMap$LinkedTreeMapIterator
 */
package com.google.gson.internal;

import com.google.gson.internal.LinkedHashTreeMap;

class LinkedHashTreeMap.KeySet.1
extends LinkedHashTreeMap.LinkedTreeMapIterator<K> {
    public K next() {
        return this.nextNode().key;
    }

    LinkedHashTreeMap.KeySet.1() {
        super(KeySet.this.this$0);
    }
}
