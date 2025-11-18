/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.internal.ObjectConstructor;
import java.lang.reflect.Type;

class ConstructorConstructor.1
implements ObjectConstructor<T> {
    final /* synthetic */ Type val$type;
    final /* synthetic */ InstanceCreator val$typeCreator;

    ConstructorConstructor.1(InstanceCreator instanceCreator, Type type) {
        this.val$typeCreator = instanceCreator;
        this.val$type = type;
    }

    @Override
    public T construct() {
        return this.val$typeCreator.createInstance(this.val$type);
    }
}
