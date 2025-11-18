/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

static final class ArrayTypeAdapter.1
implements TypeAdapterFactory {
    ArrayTypeAdapter.1() {
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        if (!(type instanceof GenericArrayType)) {
            if (!(type instanceof Class)) return null;
            if (!((Class)type).isArray()) {
                return null;
            }
        }
        Type componentType = $Gson$Types.getArrayComponentType(type);
        TypeAdapter<?> componentTypeAdapter = gson.getAdapter(TypeToken.get(componentType));
        return new ArrayTypeAdapter(gson, componentTypeAdapter, $Gson$Types.getRawType(componentType));
    }
}
