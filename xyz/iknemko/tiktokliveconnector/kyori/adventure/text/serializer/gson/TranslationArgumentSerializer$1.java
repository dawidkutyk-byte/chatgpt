/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.stream.JsonToken;

static class TranslationArgumentSerializer.1 {
    static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

    static {
        $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];
        try {
            TranslationArgumentSerializer.1.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TranslationArgumentSerializer.1.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
