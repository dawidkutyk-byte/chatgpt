/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  F8Jq
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.reflect.TypeToken;

public final class JsonAdapterAnnotationTypeAdapterFactory
implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    TypeAdapter<?> getTypeAdapter(ConstructorConstructor constructorConstructor, Gson gson, TypeToken<?> type, F8Jq annotation) {
        TypeAdapter<?> typeAdapter;
        Object instance = constructorConstructor.get(TypeToken.get(annotation.value())).construct();
        if (instance instanceof TypeAdapter) {
            typeAdapter = (TypeAdapter<?>)instance;
        } else if (instance instanceof TypeAdapterFactory) {
            typeAdapter = ((TypeAdapterFactory)instance).create(gson, type);
        } else {
            if (!(instance instanceof JsonSerializer)) {
                if (!(instance instanceof JsonDeserializer)) throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference.");
            }
            JsonSerializer serializer = instance instanceof JsonSerializer ? (JsonSerializer)instance : null;
            JsonDeserializer deserializer = instance instanceof JsonDeserializer ? (JsonDeserializer)instance : null;
            typeAdapter = new TreeTypeAdapter(serializer, deserializer, gson, type, null);
        }
        if (typeAdapter == null) return typeAdapter;
        if (!annotation.nullSafe()) return typeAdapter;
        typeAdapter = typeAdapter.nullSafe();
        return typeAdapter;
    }

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor) {
        this.constructorConstructor = constructorConstructor;
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> targetType) {
        Class<T> rawType = targetType.getRawType();
        F8Jq annotation = rawType.getAnnotation(F8Jq.class);
        if (annotation != null) return this.getTypeAdapter(this.constructorConstructor, gson, targetType, annotation);
        return null;
    }
}
