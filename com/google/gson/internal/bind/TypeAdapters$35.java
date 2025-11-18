/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

static final class TypeAdapters.35
implements TypeAdapterFactory {
    final /* synthetic */ TypeAdapter val$typeAdapter;
    final /* synthetic */ Class val$clazz;

    TypeAdapters.35(Class clazz, TypeAdapter typeAdapter) {
        this.val$clazz = clazz;
        this.val$typeAdapter = typeAdapter;
    }

    public String toString() {
        return "Factory[typeHierarchy=" + this.val$clazz.getName() + ",adapter=" + this.val$typeAdapter + "]";
    }

    public <T2> TypeAdapter<T2> create(Gson gson, TypeToken<T2> typeToken) {
        Class<T2> requestedType = typeToken.getRawType();
        if (this.val$clazz.isAssignableFrom(requestedType)) return new /* Unavailable Anonymous Inner Class!! */;
        return null;
    }
}
