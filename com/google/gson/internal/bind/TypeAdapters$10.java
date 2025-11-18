/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerArray;

static final class TypeAdapters.10
extends TypeAdapter<AtomicIntegerArray> {
    @Override
    public void write(JsonWriter out, AtomicIntegerArray value) throws IOException {
        out.beginArray();
        int i = 0;
        int length = value.length();
        while (true) {
            if (i >= length) {
                out.endArray();
                return;
            }
            out.value(value.get(i));
            ++i;
        }
    }

    @Override
    public AtomicIntegerArray read(JsonReader in) throws IOException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        in.beginArray();
        while (in.hasNext()) {
            try {
                int integer = in.nextInt();
                list.add(integer);
            }
            catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }
        in.endArray();
        int length = list.size();
        AtomicIntegerArray array = new AtomicIntegerArray(length);
        int i = 0;
        while (i < length) {
            array.set(i, (Integer)list.get(i));
            ++i;
        }
        return array;
    }

    TypeAdapters.10() {
    }
}
