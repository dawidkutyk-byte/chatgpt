/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

class Excluder.1
extends TypeAdapter<T> {
    final /* synthetic */ Gson val$gson;
    final /* synthetic */ boolean val$skipDeserialize;
    private TypeAdapter<T> delegate;
    final /* synthetic */ TypeToken val$type;
    final /* synthetic */ boolean val$skipSerialize;

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        if (this.val$skipSerialize) {
            out.nullValue();
            return;
        }
        this.delegate().write(out, value);
    }

    private TypeAdapter<T> delegate() {
        TypeAdapter d = this.delegate;
        return d != null ? d : (this.delegate = this.val$gson.getDelegateAdapter(Excluder.this, this.val$type));
    }

    @Override
    public T read(JsonReader in) throws IOException {
        if (!this.val$skipDeserialize) return this.delegate().read(in);
        in.skipValue();
        return null;
    }

    Excluder.1(boolean bl, boolean bl2, Gson gson, TypeToken typeToken) {
        this.val$skipDeserialize = bl;
        this.val$skipSerialize = bl2;
        this.val$gson = gson;
        this.val$type = typeToken;
    }
}
