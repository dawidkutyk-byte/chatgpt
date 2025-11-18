/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

static final class TypeAdapters.19
extends TypeAdapter<StringBuilder> {
    TypeAdapters.19() {
    }

    @Override
    public StringBuilder read(JsonReader in) throws IOException {
        if (in.peek() != JsonToken.NULL) return new StringBuilder(in.nextString());
        in.nextNull();
        return null;
    }

    @Override
    public void write(JsonWriter out, StringBuilder value) throws IOException {
        out.value(value == null ? null : value.toString());
    }
}
