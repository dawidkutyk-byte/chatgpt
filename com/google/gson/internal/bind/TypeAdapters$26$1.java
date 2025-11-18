/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

class TypeAdapters.1
extends TypeAdapter<Timestamp> {
    final /* synthetic */ TypeAdapter val$dateTypeAdapter;

    TypeAdapters.1(TypeAdapter typeAdapter) {
        this.val$dateTypeAdapter = typeAdapter;
    }

    @Override
    public Timestamp read(JsonReader in) throws IOException {
        Date date = (Date)this.val$dateTypeAdapter.read(in);
        return date != null ? new Timestamp(date.getTime()) : null;
    }

    @Override
    public void write(JsonWriter out, Timestamp value) throws IOException {
        this.val$dateTypeAdapter.write(out, value);
    }
}
