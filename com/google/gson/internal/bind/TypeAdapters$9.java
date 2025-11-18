/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

static final class TypeAdapters.9
extends TypeAdapter<AtomicBoolean> {
    TypeAdapters.9() {
    }

    @Override
    public AtomicBoolean read(JsonReader in) throws IOException {
        return new AtomicBoolean(in.nextBoolean());
    }

    @Override
    public void write(JsonWriter out, AtomicBoolean value) throws IOException {
        out.value(value.get());
    }
}
