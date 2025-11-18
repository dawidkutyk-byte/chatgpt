/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.LongSerializationPolicy;

static final class LongSerializationPolicy.2
extends LongSerializationPolicy {
    @Override
    public JsonElement serialize(Long value) {
        return new JsonPrimitive(String.valueOf(value));
    }
}
