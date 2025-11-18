/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.internal.ObjectConstructor;

class ConstructorConstructor.13
implements ObjectConstructor<T> {
    ConstructorConstructor.13() {
    }

    @Override
    public T construct() {
        return new LinkedTreeMap();
    }
}
