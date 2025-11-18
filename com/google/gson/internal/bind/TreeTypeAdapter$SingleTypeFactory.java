/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.reflect.TypeToken;

private static final class TreeTypeAdapter.SingleTypeFactory
implements TypeAdapterFactory {
    private final TypeToken<?> exactType;
    private final boolean matchRawType;
    private final JsonDeserializer<?> deserializer;
    private final JsonSerializer<?> serializer;
    private final Class<?> hierarchyType;

    TreeTypeAdapter.SingleTypeFactory(Object typeAdapter, TypeToken<?> exactType, boolean matchRawType, Class<?> hierarchyType) {
        this.serializer = typeAdapter instanceof JsonSerializer ? (JsonSerializer)typeAdapter : null;
        this.deserializer = typeAdapter instanceof JsonDeserializer ? (JsonDeserializer)typeAdapter : null;
        $Gson$Preconditions.checkArgument(this.serializer != null || this.deserializer != null);
        this.exactType = exactType;
        this.matchRawType = matchRawType;
        this.hierarchyType = hierarchyType;
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        boolean matches = this.exactType != null ? this.exactType.equals(type) || this.matchRawType && this.exactType.getType() == type.getRawType() : this.hierarchyType.isAssignableFrom(type.getRawType());
        return matches ? new TreeTypeAdapter(this.serializer, this.deserializer, gson, type, this) : null;
    }
}
