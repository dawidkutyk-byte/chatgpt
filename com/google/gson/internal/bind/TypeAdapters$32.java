/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

static final class TypeAdapters.32
implements TypeAdapterFactory {
    final /* synthetic */ Class val$type;
    final /* synthetic */ TypeAdapter val$typeAdapter;

    TypeAdapters.32(Class clazz, TypeAdapter typeAdapter) {
        this.val$type = clazz;
        this.val$typeAdapter = typeAdapter;
    }

    public String toString() {
        return "Factory[type=" + this.val$type.getName() + ",adapter=" + this.val$typeAdapter + "]";
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        return typeToken.getRawType() == this.val$type ? this.val$typeAdapter : null;
    }
}
