/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.internal.bind.TypeAdapters$36
 */
package com.google.gson.internal.bind;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.BitSet;

static final class TypeAdapters.2
extends TypeAdapter<BitSet> {
    @Override
    public void write(JsonWriter out, BitSet src) throws IOException {
        if (src == null) {
            out.nullValue();
            return;
        }
        out.beginArray();
        int i = 0;
        while (true) {
            if (i >= src.length()) {
                out.endArray();
                return;
            }
            int value = src.get(i) ? 1 : 0;
            out.value(value);
            ++i;
        }
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public BitSet read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        bitset = new BitSet();
        in.beginArray();
        i = 0;
        tokenType = in.peek();
        block7: while (true) {
            if (tokenType == JsonToken.END_ARRAY) {
                in.endArray();
                return bitset;
            }
            switch (TypeAdapters.36.$SwitchMap$com$google$gson$stream$JsonToken[tokenType.ordinal()]) {
                case 1: {
                    set = in.nextInt() != 0;
                    ** GOTO lbl26
                }
                case 2: {
                    set = in.nextBoolean();
                    ** GOTO lbl26
                }
                case 3: {
                    stringValue = in.nextString();
                    try {
                        set = Integer.parseInt(stringValue) != 0;
                    }
                    catch (NumberFormatException e) {
                        throw new JsonSyntaxException("Error: Expecting: bitset number value (1, 0), Found: " + stringValue);
                    }
lbl26:
                    // 3 sources

                    if (set) {
                        bitset.set(i);
                    }
                    ++i;
                    tokenType = in.peek();
                    continue block7;
                }
            }
            break;
        }
        throw new JsonSyntaxException("Invalid bitset value type: " + (Object)tokenType);
    }

    TypeAdapters.2() {
    }
}
