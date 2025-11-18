/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.ObjectConstructor;
import java.util.ArrayDeque;

class ConstructorConstructor.7
implements ObjectConstructor<T> {
    ConstructorConstructor.7() {
    }

    @Override
    public T construct() {
        return new ArrayDeque();
    }
}
