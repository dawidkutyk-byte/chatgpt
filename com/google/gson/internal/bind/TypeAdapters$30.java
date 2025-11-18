/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;

static final class TypeAdapters.30
implements TypeAdapterFactory {
    TypeAdapters.30() {
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<T> rawType = typeToken.getRawType();
        if (!Enum.class.isAssignableFrom(rawType)) return null;
        if (rawType == Enum.class) {
            return null;
        }
        if (rawType.isEnum()) return new TypeAdapters.EnumTypeAdapter<T>(rawType);
        rawType = rawType.getSuperclass();
        return new TypeAdapters.EnumTypeAdapter<T>(rawType);
    }
}
