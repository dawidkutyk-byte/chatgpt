/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.internal.ObjectConstructor;
import java.lang.reflect.Type;

class ConstructorConstructor.2
implements ObjectConstructor<T> {
    final /* synthetic */ Type val$type;
    final /* synthetic */ InstanceCreator val$rawTypeCreator;

    ConstructorConstructor.2(InstanceCreator instanceCreator, Type type) {
        this.val$rawTypeCreator = instanceCreator;
        this.val$type = type;
    }

    @Override
    public T construct() {
        return this.val$rawTypeCreator.createInstance(this.val$type);
    }
}
