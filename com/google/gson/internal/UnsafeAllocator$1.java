/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.UnsafeAllocator;
import java.lang.reflect.Method;

static final class UnsafeAllocator.1
extends UnsafeAllocator {
    final /* synthetic */ Method val$allocateInstance;
    final /* synthetic */ Object val$unsafe;

    @Override
    public <T> T newInstance(Class<T> c) throws Exception {
        UnsafeAllocator.assertInstantiable(c);
        return (T)this.val$allocateInstance.invoke(this.val$unsafe, c);
    }

    UnsafeAllocator.1(Method method, Object object) {
        this.val$allocateInstance = method;
        this.val$unsafe = object;
    }
}
