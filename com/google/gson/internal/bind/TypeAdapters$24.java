/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.UUID;

static final class TypeAdapters.24
extends TypeAdapter<UUID> {
    @Override
    public UUID read(JsonReader in) throws IOException {
        if (in.peek() != JsonToken.NULL) return UUID.fromString(in.nextString());
        in.nextNull();
        return null;
    }

    @Override
    public void write(JsonWriter out, UUID value) throws IOException {
        out.value(value == null ? null : value.toString());
    }

    TypeAdapters.24() {
    }
}
