/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

static abstract class ReflectiveTypeAdapterFactory.BoundField {
    final boolean deserialized;
    final boolean serialized;
    final String name;

    protected ReflectiveTypeAdapterFactory.BoundField(String name, boolean serialized, boolean deserialized) {
        this.name = name;
        this.serialized = serialized;
        this.deserialized = deserialized;
    }

    abstract void read(JsonReader var1, Object var2) throws IllegalAccessException, IOException;

    abstract void write(JsonWriter var1, Object var2) throws IllegalAccessException, IOException;

    abstract boolean writeField(Object var1) throws IOException, IllegalAccessException;
}
