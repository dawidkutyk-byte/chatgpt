/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

static final class TypeAdapters.33
implements TypeAdapterFactory {
    final /* synthetic */ Class val$boxed;
    final /* synthetic */ Class val$unboxed;
    final /* synthetic */ TypeAdapter val$typeAdapter;

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<T> rawType = typeToken.getRawType();
        return rawType == this.val$unboxed || rawType == this.val$boxed ? this.val$typeAdapter : null;
    }

    public String toString() {
        return "Factory[type=" + this.val$boxed.getName() + "+" + this.val$unboxed.getName() + ",adapter=" + this.val$typeAdapter + "]";
    }

    TypeAdapters.33(Class clazz, Class clazz2, TypeAdapter typeAdapter) {
        this.val$unboxed = clazz;
        this.val$boxed = clazz2;
        this.val$typeAdapter = typeAdapter;
    }
}
