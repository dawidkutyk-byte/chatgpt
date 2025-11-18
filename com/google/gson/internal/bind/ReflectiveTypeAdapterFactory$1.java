/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.TypeAdapterRuntimeTypeWrapper;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;

class ReflectiveTypeAdapterFactory.1
extends ReflectiveTypeAdapterFactory.BoundField {
    final /* synthetic */ Gson val$context;
    final /* synthetic */ TypeToken val$fieldType;
    final /* synthetic */ boolean val$isPrimitive;
    final /* synthetic */ boolean val$jsonAdapterPresent;
    final /* synthetic */ Field val$field;
    final /* synthetic */ TypeAdapter val$typeAdapter;

    @Override
    void write(JsonWriter writer, Object value) throws IllegalAccessException, IOException {
        Object fieldValue = this.val$field.get(value);
        TypeAdapter t = this.val$jsonAdapterPresent ? this.val$typeAdapter : new TypeAdapterRuntimeTypeWrapper(this.val$context, this.val$typeAdapter, this.val$fieldType.getType());
        t.write(writer, fieldValue);
    }

    ReflectiveTypeAdapterFactory.1(String name, boolean serialized, boolean deserialized, Field field, boolean bl, TypeAdapter typeAdapter, Gson gson, TypeToken typeToken, boolean bl2) {
        this.val$field = field;
        this.val$jsonAdapterPresent = bl;
        this.val$typeAdapter = typeAdapter;
        this.val$context = gson;
        this.val$fieldType = typeToken;
        this.val$isPrimitive = bl2;
        super(name, serialized, deserialized);
    }

    public boolean writeField(Object value) throws IOException, IllegalAccessException {
        Object fieldValue = this.val$field.get(value);
        if (this.serialized) return fieldValue != value;
        return false;
    }

    void read(JsonReader reader, Object value) throws IOException, IllegalAccessException {
        Object fieldValue = this.val$typeAdapter.read(reader);
        if (fieldValue == null) {
            if (this.val$isPrimitive) return;
        }
        this.val$field.set(value, fieldValue);
    }
}
