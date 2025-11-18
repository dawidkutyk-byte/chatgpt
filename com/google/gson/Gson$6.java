/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLongArray;

static final class Gson.6
extends TypeAdapter<AtomicLongArray> {
    final /* synthetic */ TypeAdapter val$longAdapter;

    Gson.6(TypeAdapter typeAdapter) {
        this.val$longAdapter = typeAdapter;
    }

    @Override
    public void write(JsonWriter out, AtomicLongArray value) throws IOException {
        out.beginArray();
        int i = 0;
        int length = value.length();
        while (true) {
            if (i >= length) {
                out.endArray();
                return;
            }
            this.val$longAdapter.write(out, value.get(i));
            ++i;
        }
    }

    @Override
    public AtomicLongArray read(JsonReader in) throws IOException {
        ArrayList<Long> list = new ArrayList<Long>();
        in.beginArray();
        while (in.hasNext()) {
            long value = ((Number)this.val$longAdapter.read(in)).longValue();
            list.add(value);
        }
        in.endArray();
        int length = list.size();
        AtomicLongArray array = new AtomicLongArray(length);
        int i = 0;
        while (i < length) {
            array.set(i, (Long)list.get(i));
            ++i;
        }
        return array;
    }
}
