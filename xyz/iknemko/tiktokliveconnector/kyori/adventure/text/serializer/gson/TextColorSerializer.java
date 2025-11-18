/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.NamedTextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.NamedTextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;

final class TextColorSerializer
extends TypeAdapter<TextColor> {
    static final TypeAdapter<TextColor> INSTANCE = new TextColorSerializer(false).nullSafe();
    private final boolean downsampleColor;
    static final TypeAdapter<TextColor> DOWNSAMPLE_COLOR = new TextColorSerializer(true).nullSafe();

    @Override
    @Nullable
    public TextColor read(JsonReader in) throws IOException {
        @Nullable TextColor color = TextColorSerializer.fromString(in.nextString());
        if (color != null) return this.downsampleColor ? NamedTextColor.nearestTo((TextColor)color) : color;
        return null;
    }

    private TextColorSerializer(boolean downsampleColor) {
        this.downsampleColor = downsampleColor;
    }

    @Override
    public void write(JsonWriter out, TextColor value) throws IOException {
        if (value instanceof NamedTextColor) {
            out.value((String)NamedTextColor.NAMES.key((Object)((NamedTextColor)value)));
        } else if (this.downsampleColor) {
            out.value((String)NamedTextColor.NAMES.key((Object)NamedTextColor.nearestTo((TextColor)value)));
        } else {
            out.value(TextColorSerializer.asUpperCaseHexString(value));
        }
    }

    @Nullable
    static TextColor fromString(@NotNull String value) {
        if (!value.startsWith("#")) return (TextColor)NamedTextColor.NAMES.value((Object)value);
        return TextColor.fromHexString((String)value);
    }

    private static String asUpperCaseHexString(TextColor color) {
        return String.format(Locale.ROOT, "%c%06X", Character.valueOf('#'), color.value());
    }
}
