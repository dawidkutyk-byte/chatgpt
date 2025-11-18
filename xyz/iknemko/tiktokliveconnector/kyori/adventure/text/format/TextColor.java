/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.NamedTextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColorImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextFormat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.HSVLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.RGBLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.NamedTextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColorImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextFormat;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.HSVLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.RGBLike;

public interface TextColor
extends Comparable<TextColor>,
Examinable,
RGBLike,
StyleBuilderApplicable,
TextFormat {
    public static final String HEX_PREFIX = "#";
    public static final char HEX_CHARACTER = '#';

    @NotNull
    public static TextColor color(@Range(from=0L, to=255L) int r, @Range(from=0L, to=255L) int g, @Range(from=0L, to=255L) int b) {
        return TextColor.color((r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF);
    }

    @NotNull
    public static TextColor color(@NotNull RGBLike rgb) {
        if (!(rgb instanceof TextColor)) return TextColor.color(rgb.red(), rgb.green(), rgb.blue());
        return (TextColor)rgb;
    }

    default public @Range(from=0L, to=255L) int green() {
        return this.value() >> 8 & 0xFF;
    }

    default public @Range(from=0L, to=255L) int blue() {
        return this.value() & 0xFF;
    }

    @NotNull
    public static TextColor color(float r, float g, float b) {
        return TextColor.color((int)(r * 255.0f), (int)(g * 255.0f), (int)(b * 255.0f));
    }

    @NotNull
    public static <C extends TextColor> C nearestColorTo(@NotNull List<C> values, @NotNull TextColor any) {
        Objects.requireNonNull(any, "color");
        float matchedDistance = Float.MAX_VALUE;
        TextColor match = (TextColor)values.get(0);
        int i = 0;
        int length = values.size();
        while (i < length) {
            TextColor potential = (TextColor)values.get(i);
            float distance = TextColorImpl.distance((HSVLike)any.asHSV(), (HSVLike)potential.asHSV());
            if (distance < matchedDistance) {
                match = potential;
                matchedDistance = distance;
            }
            if (distance == 0.0f) {
                return (C)match;
            }
            ++i;
        }
        return (C)match;
    }

    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (String)this.asHexString()));
    }

    default public void styleApply(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Style.Builder style) {
        style.color(this);
    }

    default public @Range(from=0L, to=255L) int red() {
        return this.value() >> 16 & 0xFF;
    }

    public int value();

    @Override
    default public int compareTo(TextColor that) {
        return Integer.compare(this.value(), that.value());
    }

    @NotNull
    public static TextColor lerp(float t, @NotNull RGBLike a, @NotNull RGBLike b) {
        float clampedT = Math.min(1.0f, Math.max(0.0f, t));
        int ar = a.red();
        int br = b.red();
        int ag = a.green();
        int bg = b.green();
        int ab = a.blue();
        int bb = b.blue();
        return TextColor.color(Math.round((float)ar + clampedT * (float)(br - ar)), Math.round((float)ag + clampedT * (float)(bg - ag)), Math.round((float)ab + clampedT * (float)(bb - ab)));
    }

    @NotNull
    public static TextColor color(@NotNull HSVLike hsv) {
        float s = hsv.s();
        float v = hsv.v();
        if (s == 0.0f) {
            return TextColor.color(v, v, v);
        }
        float h = hsv.h() * 6.0f;
        int i = (int)Math.floor(h);
        float f = h - (float)i;
        float p = v * (1.0f - s);
        float q = v * (1.0f - s * f);
        float t = v * (1.0f - s * (1.0f - f));
        if (i == 0) {
            return TextColor.color(v, t, p);
        }
        if (i == 1) {
            return TextColor.color(q, v, p);
        }
        if (i == 2) {
            return TextColor.color(p, v, t);
        }
        if (i == 3) {
            return TextColor.color(p, q, v);
        }
        if (i != 4) return TextColor.color(v, p, q);
        return TextColor.color(t, p, v);
    }

    @NotNull
    default public String asHexString() {
        StringBuilder result = new StringBuilder();
        result.append(HEX_PREFIX);
        String hex = Integer.toHexString(this.value());
        int i = 0;
        while (true) {
            if (i >= 6 - hex.length()) {
                result.append(hex);
                return result.toString();
            }
            result.append('0');
            ++i;
        }
    }

    @NotNull
    public static TextColor color(int value) {
        int truncatedValue = value & 0xFFFFFF;
        NamedTextColor named = NamedTextColor.namedColor((int)truncatedValue);
        return named != null ? named : new TextColorImpl(truncatedValue);
    }

    @Nullable
    public static TextColor fromHexString(@NotNull String string) {
        if (!string.startsWith(HEX_PREFIX)) return null;
        try {
            int hex = Integer.parseInt(string.substring(1), 16);
            return TextColor.color(hex);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    @Nullable
    public static TextColor fromCSSHexString(@NotNull String string) {
        int hex;
        if (!string.startsWith(HEX_PREFIX)) return null;
        String hexString = string.substring(1);
        if (hexString.length() != 3 && hexString.length() != 6) {
            return null;
        }
        try {
            hex = Integer.parseInt(hexString, 16);
        }
        catch (NumberFormatException e) {
            return null;
        }
        if (hexString.length() == 6) {
            return TextColor.color(hex);
        }
        int red = (hex & 0xF00) >> 8 | (hex & 0xF00) >> 4;
        int green = (hex & 0xF0) >> 4 | hex & 0xF0;
        int blue = (hex & 0xF) << 4 | hex & 0xF;
        return TextColor.color(red, green, blue);
    }
}
