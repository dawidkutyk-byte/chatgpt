/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

static final class TypeAdapters.1
extends TypeAdapter<Class> {
    @Override
    public Class read(JsonReader in) throws IOException {
        if (in.peek() != JsonToken.NULL) throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        in.nextNull();
        return null;
    }

    TypeAdapters.1() {
    }

    @Override
    public void write(JsonWriter out, Class value) throws IOException {
        if (value != null) throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + value.getName() + ". Forgot to register a type adapter?");
        out.nullValue();
    }
}
