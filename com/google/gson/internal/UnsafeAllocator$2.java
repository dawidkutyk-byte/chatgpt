/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.UnsafeAllocator;
import java.lang.reflect.Method;

static final class UnsafeAllocator.2
extends UnsafeAllocator {
    final /* synthetic */ int val$constructorId;
    final /* synthetic */ Method val$newInstance;

    UnsafeAllocator.2(Method method, int n) {
        this.val$newInstance = method;
        this.val$constructorId = n;
    }

    @Override
    public <T> T newInstance(Class<T> c) throws Exception {
        UnsafeAllocator.assertInstantiable(c);
        return (T)this.val$newInstance.invoke(null, c, this.val$constructorId);
    }
}
