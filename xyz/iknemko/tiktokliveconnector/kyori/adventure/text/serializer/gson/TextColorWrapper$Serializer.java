/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TextColorSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TextColorWrapper
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TextColorSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TextColorWrapper;

static final class TextColorWrapper.Serializer
extends TypeAdapter<TextColorWrapper> {
    static final TextColorWrapper.Serializer INSTANCE = new TextColorWrapper.Serializer();

    @Override
    public void write(JsonWriter out, TextColorWrapper value) {
        throw new JsonSyntaxException("Cannot write TextColorWrapper instances");
    }

    @Override
    public TextColorWrapper read(JsonReader in) throws IOException {
        String input = in.nextString();
        TextColor color = TextColorSerializer.fromString((String)input);
        TextDecoration decoration = (TextDecoration)TextDecoration.NAMES.value((Object)input);
        boolean reset = decoration == null && input.equals("reset");
        if (color != null) return new TextColorWrapper(color, decoration, reset);
        if (decoration != null) return new TextColorWrapper(color, decoration, reset);
        if (reset) return new TextColorWrapper(color, decoration, reset);
        throw new JsonParseException("Don't know how to parse " + input + " at " + in.getPath());
    }

    private TextColorWrapper.Serializer() {
    }
}
