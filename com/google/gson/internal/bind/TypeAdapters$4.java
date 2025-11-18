/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

static final class TypeAdapters.4
extends TypeAdapter<Boolean> {
    @Override
    public Boolean read(JsonReader in) throws IOException {
        if (in.peek() != JsonToken.NULL) return Boolean.valueOf(in.nextString());
        in.nextNull();
        return null;
    }

    @Override
    public void write(JsonWriter out, Boolean value) throws IOException {
        out.value(value == null ? "null" : value.toString());
    }

    TypeAdapters.4() {
    }
}
