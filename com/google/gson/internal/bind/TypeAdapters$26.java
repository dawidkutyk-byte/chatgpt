/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.sql.Timestamp;
import java.util.Date;

static final class TypeAdapters.26
implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (typeToken.getRawType() != Timestamp.class) {
            return null;
        }
        TypeAdapter<Date> dateTypeAdapter = gson.getAdapter(Date.class);
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    TypeAdapters.26() {
    }
}
