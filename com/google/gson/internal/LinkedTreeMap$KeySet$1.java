/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.LinkedTreeMap;

class LinkedTreeMap.KeySet.1
extends LinkedTreeMap.LinkedTreeMapIterator<K> {
    @Override
    public K next() {
        return this.nextNode().key;
    }

    LinkedTreeMap.KeySet.1() {
        super(KeySet.this.this$0);
    }
}
