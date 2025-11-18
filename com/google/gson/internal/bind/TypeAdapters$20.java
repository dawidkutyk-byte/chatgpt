/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

static final class TypeAdapters.20
extends TypeAdapter<StringBuffer> {
    TypeAdapters.20() {
    }

    @Override
    public StringBuffer read(JsonReader in) throws IOException {
        if (in.peek() != JsonToken.NULL) return new StringBuffer(in.nextString());
        in.nextNull();
        return null;
    }

    @Override
    public void write(JsonWriter out, StringBuffer value) throws IOException {
        out.value(value == null ? null : value.toString());
    }
}
