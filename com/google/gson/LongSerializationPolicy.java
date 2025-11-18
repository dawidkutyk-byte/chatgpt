/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;

public enum LongSerializationPolicy {
    DEFAULT{

        @Override
        public JsonElement serialize(Long value) {
            if (value != null) return new JsonPrimitive(value);
            return JsonNull.INSTANCE;
        }
    }
    ,
    STRING{

        @Override
        public JsonElement serialize(Long value) {
            if (value != null) return new JsonPrimitive(value.toString());
            return JsonNull.INSTANCE;
        }
    };


    public abstract JsonElement serialize(Long var1);
}
