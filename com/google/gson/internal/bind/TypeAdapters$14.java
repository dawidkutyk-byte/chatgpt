/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.internal.bind.TypeAdapters$36
 */
package com.google.gson.internal.bind;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

static final class TypeAdapters.14
extends TypeAdapter<Number> {
    TypeAdapters.14() {
    }

    @Override
    public void write(JsonWriter out, Number value) throws IOException {
        out.value(value);
    }

    @Override
    public Number read(JsonReader in) throws IOException {
        JsonToken jsonToken = in.peek();
        switch (TypeAdapters.36.$SwitchMap$com$google$gson$stream$JsonToken[jsonToken.ordinal()]) {
            case 4: {
                in.nextNull();
                return null;
            }
            case 1: {
                return new LazilyParsedNumber(in.nextString());
            }
        }
        throw new JsonSyntaxException("Expecting number, got: " + (Object)((Object)jsonToken));
    }
}
