/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.net.URL;

static final class TypeAdapters.21
extends TypeAdapter<URL> {
    TypeAdapters.21() {
    }

    @Override
    public URL read(JsonReader in) throws IOException {
        if (in.peek() != JsonToken.NULL) String nextString;
        return "null".equals(nextString = in.nextString()) ? null : new URL(nextString);
        in.nextNull();
        return null;
    }

    @Override
    public void write(JsonWriter out, URL value) throws IOException {
        out.value(value == null ? null : value.toExternalForm());
    }
}
