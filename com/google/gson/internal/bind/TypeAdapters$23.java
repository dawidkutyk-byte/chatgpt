/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.net.InetAddress;

static final class TypeAdapters.23
extends TypeAdapter<InetAddress> {
    @Override
    public void write(JsonWriter out, InetAddress value) throws IOException {
        out.value(value == null ? null : value.getHostAddress());
    }

    @Override
    public InetAddress read(JsonReader in) throws IOException {
        if (in.peek() != JsonToken.NULL) return InetAddress.getByName(in.nextString());
        in.nextNull();
        return null;
    }

    TypeAdapters.23() {
    }
}
