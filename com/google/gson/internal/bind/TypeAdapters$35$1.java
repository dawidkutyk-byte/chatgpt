/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

class TypeAdapters.1
extends TypeAdapter<T1> {
    final /* synthetic */ Class val$requestedType;

    TypeAdapters.1(Class clazz) {
        this.val$requestedType = clazz;
    }

    @Override
    public void write(JsonWriter out, T1 value) throws IOException {
        val$typeAdapter.write(out, value);
    }

    @Override
    public T1 read(JsonReader in) throws IOException {
        Object result = val$typeAdapter.read(in);
        if (result == null) return result;
        if (this.val$requestedType.isInstance(result)) return result;
        throw new JsonSyntaxException("Expected a " + this.val$requestedType.getName() + " but was " + result.getClass().getName());
    }
}
