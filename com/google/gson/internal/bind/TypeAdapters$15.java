/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

static final class TypeAdapters.15
extends TypeAdapter<Character> {
    @Override
    public void write(JsonWriter out, Character value) throws IOException {
        out.value(value == null ? null : String.valueOf(value));
    }

    @Override
    public Character read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        String str = in.nextString();
        if (str.length() == 1) return Character.valueOf(str.charAt(0));
        throw new JsonSyntaxException("Expecting character, got: " + str);
    }

    TypeAdapters.15() {
    }
}
