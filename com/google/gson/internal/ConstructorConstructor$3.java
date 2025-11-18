/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.ObjectConstructor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class ConstructorConstructor.3
implements ObjectConstructor<T> {
    final /* synthetic */ Constructor val$constructor;

    @Override
    public T construct() {
        try {
            Object[] args = null;
            return this.val$constructor.newInstance(args);
        }
        catch (InstantiationException e) {
            throw new RuntimeException("Failed to invoke " + this.val$constructor + " with no args", e);
        }
        catch (InvocationTargetException e) {
            throw new RuntimeException("Failed to invoke " + this.val$constructor + " with no args", e.getTargetException());
        }
        catch (IllegalAccessException e) {
            throw new AssertionError((Object)e);
        }
    }

    ConstructorConstructor.3(Constructor constructor) {
        this.val$constructor = constructor;
    }
}
