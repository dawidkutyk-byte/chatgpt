/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.internal.bind.TimeTypeAdapter
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.TimeTypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.sql.Time;

static final class TimeTypeAdapter.1
implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        return typeToken.getRawType() == Time.class ? new TimeTypeAdapter() : null;
    }

    TimeTypeAdapter.1() {
    }
}
