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

class Gson.3
extends TypeAdapter<Number> {
    @Override
    public Float read(JsonReader in) throws IOException {
        if (in.peek() != JsonToken.NULL) return Float.valueOf((float)in.nextDouble());
        in.nextNull();
        return null;
    }

    Gson.3() {
    }

    @Override
    public void write(JsonWriter out, Number value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        float floatValue = value.floatValue();
        Gson.checkValidFloatingPoint(floatValue);
        out.value(value);
    }
}
