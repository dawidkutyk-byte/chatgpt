/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.ObjectConstructor;
import java.util.LinkedHashSet;

class ConstructorConstructor.6
implements ObjectConstructor<T> {
    ConstructorConstructor.6() {
    }

    @Override
    public T construct() {
        return new LinkedHashSet();
    }
}
