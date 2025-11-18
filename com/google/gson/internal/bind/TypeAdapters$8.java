/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

static final class TypeAdapters.8
extends TypeAdapter<AtomicInteger> {
    @Override
    public void write(JsonWriter out, AtomicInteger value) throws IOException {
        out.value(value.get());
    }

    TypeAdapters.8() {
    }

    @Override
    public AtomicInteger read(JsonReader in) throws IOException {
        try {
            return new AtomicInteger(in.nextInt());
        }
        catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }
}
