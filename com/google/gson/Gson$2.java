/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

class Gson.2
extends TypeAdapter<Number> {
    @Override
    public void write(JsonWriter out, Number value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        double doubleValue = value.doubleValue();
        Gson.checkValidFloatingPoint(doubleValue);
        out.value(value);
    }

    @Override
    public Double read(JsonReader in) throws IOException {
        if (in.peek() != JsonToken.NULL) return in.nextDouble();
        in.nextNull();
        return null;
    }

    Gson.2() {
    }
}
