/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.UnsafeAllocator;
import java.lang.reflect.Method;

static final class UnsafeAllocator.3
extends UnsafeAllocator {
    final /* synthetic */ Method val$newInstance;

    @Override
    public <T> T newInstance(Class<T> c) throws Exception {
        UnsafeAllocator.assertInstantiable(c);
        return (T)this.val$newInstance.invoke(null, c, Object.class);
    }

    UnsafeAllocator.3(Method method) {
        this.val$newInstance = method;
    }
}
