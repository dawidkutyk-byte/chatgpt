/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.JsonIOException;
import com.google.gson.internal.ObjectConstructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;

class ConstructorConstructor.5
implements ObjectConstructor<T> {
    final /* synthetic */ Type val$type;

    @Override
    public T construct() {
        if (!(this.val$type instanceof ParameterizedType)) throw new JsonIOException("Invalid EnumSet type: " + this.val$type.toString());
        Type elementType = ((ParameterizedType)this.val$type).getActualTypeArguments()[0];
        if (!(elementType instanceof Class)) throw new JsonIOException("Invalid EnumSet type: " + this.val$type.toString());
        return EnumSet.noneOf((Class)elementType);
    }

    ConstructorConstructor.5(Type type) {
        this.val$type = type;
    }
}
