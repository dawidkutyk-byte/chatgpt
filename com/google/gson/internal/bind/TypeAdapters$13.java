/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

static final class TypeAdapters.13
extends TypeAdapter<Number> {
    @Override
    public void write(JsonWriter out, Number value) throws IOException {
        out.value(value);
    }

    TypeAdapters.13() {
    }

    @Override
    public Number read(JsonReader in) throws IOException {
        if (in.peek() != JsonToken.NULL) return in.nextDouble();
        in.nextNull();
        return null;
    }
}
