/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

class TypeAdapter.1
extends TypeAdapter<T> {
    @Override
    public T read(JsonReader reader) throws IOException {
        if (reader.peek() != JsonToken.NULL) return TypeAdapter.this.read(reader);
        reader.nextNull();
        return null;
    }

    TypeAdapter.1() {
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            TypeAdapter.this.write(out, value);
        }
    }
}
