/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

static final class TypeAdapters.34
implements TypeAdapterFactory {
    final /* synthetic */ Class val$sub;
    final /* synthetic */ TypeAdapter val$typeAdapter;
    final /* synthetic */ Class val$base;

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<T> rawType = typeToken.getRawType();
        return rawType == this.val$base || rawType == this.val$sub ? this.val$typeAdapter : null;
    }

    TypeAdapters.34(Class clazz, Class clazz2, TypeAdapter typeAdapter) {
        this.val$base = clazz;
        this.val$sub = clazz2;
        this.val$typeAdapter = typeAdapter;
    }

    public String toString() {
        return "Factory[type=" + this.val$base.getName() + "+" + this.val$sub.getName() + ",adapter=" + this.val$typeAdapter + "]";
    }
}
