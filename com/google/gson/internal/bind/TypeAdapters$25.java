/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Currency;

static final class TypeAdapters.25
extends TypeAdapter<Currency> {
    @Override
    public Currency read(JsonReader in) throws IOException {
        return Currency.getInstance(in.nextString());
    }

    TypeAdapters.25() {
    }

    @Override
    public void write(JsonWriter out, Currency value) throws IOException {
        out.value(value.getCurrencyCode());
    }
}
