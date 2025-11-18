/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.ObjectConstructor;
import java.util.concurrent.ConcurrentHashMap;

class ConstructorConstructor.10
implements ObjectConstructor<T> {
    @Override
    public T construct() {
        return new ConcurrentHashMap();
    }

    ConstructorConstructor.10() {
    }
}
