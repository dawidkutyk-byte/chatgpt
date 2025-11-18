/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.UnsafeAllocator;

static final class UnsafeAllocator.4
extends UnsafeAllocator {
    @Override
    public <T> T newInstance(Class<T> c) {
        throw new UnsupportedOperationException("Cannot allocate " + c);
    }

    UnsafeAllocator.4() {
    }
}
