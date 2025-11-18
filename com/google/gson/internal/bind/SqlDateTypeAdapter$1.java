/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.internal.bind.SqlDateTypeAdapter
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.sql.Date;

static final class SqlDateTypeAdapter.1
implements TypeAdapterFactory {
    SqlDateTypeAdapter.1() {
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        return typeToken.getRawType() == Date.class ? new SqlDateTypeAdapter() : null;
    }
}
