/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.internal.bind.TreeTypeAdapter;
import java.lang.reflect.Type;

private final class TreeTypeAdapter.GsonContextImpl
implements JsonSerializationContext,
JsonDeserializationContext {
    @Override
    public JsonElement serialize(Object src) {
        return TreeTypeAdapter.access$100((TreeTypeAdapter)TreeTypeAdapter.this).toJsonTree(src);
    }

    public <R> R deserialize(JsonElement json, Type typeOfT) throws JsonParseException {
        return (R)TreeTypeAdapter.access$100((TreeTypeAdapter)TreeTypeAdapter.this).fromJson(json, typeOfT);
    }

    private TreeTypeAdapter.GsonContextImpl() {
    }

    @Override
    public JsonElement serialize(Object src, Type typeOfSrc) {
        return TreeTypeAdapter.access$100((TreeTypeAdapter)TreeTypeAdapter.this).toJsonTree(src, typeOfSrc);
    }
}
