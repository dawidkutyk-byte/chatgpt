/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.ObjectConstructor;
import java.util.concurrent.ConcurrentSkipListMap;

class ConstructorConstructor.9
implements ObjectConstructor<T> {
    @Override
    public T construct() {
        return new ConcurrentSkipListMap();
    }

    ConstructorConstructor.9() {
    }
}
