/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

static final class Gson.5
extends TypeAdapter<AtomicLong> {
    final /* synthetic */ TypeAdapter val$longAdapter;

    Gson.5(TypeAdapter typeAdapter) {
        this.val$longAdapter = typeAdapter;
    }

    @Override
    public AtomicLong read(JsonReader in) throws IOException {
        Number value = (Number)this.val$longAdapter.read(in);
        return new AtomicLong(value.longValue());
    }

    @Override
    public void write(JsonWriter out, AtomicLong value) throws IOException {
        this.val$longAdapter.write(out, value.get());
    }
}
