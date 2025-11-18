/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.UnsafeAllocator;
import java.lang.reflect.Type;

class ConstructorConstructor.14
implements ObjectConstructor<T> {
    final /* synthetic */ Class val$rawType;
    private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();
    final /* synthetic */ Type val$type;

    @Override
    public T construct() {
        try {
            Object newInstance = this.unsafeAllocator.newInstance(this.val$rawType);
            return newInstance;
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to invoke no-args constructor for " + this.val$type + ". Register an InstanceCreator with Gson for this type may fix this problem.", e);
        }
    }

    ConstructorConstructor.14(Class clazz, Type type) {
        this.val$rawType = clazz;
        this.val$type = type;
    }
}
