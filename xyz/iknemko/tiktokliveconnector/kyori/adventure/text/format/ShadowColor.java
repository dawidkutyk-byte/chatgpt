/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.ShadowColorImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ARGBLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.RGBLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.ShadowColorImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ARGBLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.RGBLike;

public interface ShadowColor
extends ARGBLike,
StyleBuilderApplicable {
    default public @Range(from=0L, to=255L) int red() {
        return this.value() >> 16 & 0xFF;
    }

    @Contract(pure=true)
    @NotNull
    public static ShadowColor shadowColor(int argb) {
        if (argb != 0) return new ShadowColorImpl(argb);
        return ShadowColor.none();
    }

    default public @Range(from=0L, to=255L) int alpha() {
        return this.value() >> 24 & 0xFF;
    }

    @NotNull
    public static ShadowColor shadowColor(@NotNull ARGBLike argb) {
        if (!(argb instanceof ShadowColor)) return ShadowColor.shadowColor(argb.red(), argb.green(), argb.blue(), argb.alpha());
        return (ShadowColor)argb;
    }

    default public void styleApply(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Style.Builder style) {
        style.shadowColor((ARGBLike)this);
    }

    @Contract(pure=true)
    @NotNull
    public static ShadowColor shadowColor(@Range(from=0L, to=255L) int red, @Range(from=0L, to=255L) int green, @Range(from=0L, to=255L) int blue, @Range(from=0L, to=255L) int alpha) {
        int value = (alpha & 0xFF) << 24 | (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
        if (value != 0) return new ShadowColorImpl(value);
        return ShadowColor.none();
    }

    public int value();

    @Contract(pure=true)
    @NotNull
    public static ShadowColor shadowColor(@NotNull RGBLike rgb, @Range(from=0L, to=255L) int alpha) {
        return ShadowColor.shadowColor(rgb.red(), rgb.green(), rgb.blue(), alpha);
    }

    @NotNull
    default public String asHexString() {
        int argb = this.value();
        int a = argb >> 24 & 0xFF;
        int r = argb >> 16 & 0xFF;
        int g = argb >> 8 & 0xFF;
        int b = argb & 0xFF;
        return String.format("#%02X%02X%02X%02X", r, g, b, a);
    }

    @NotNull
    public static ShadowColor none() {
        return ShadowColorImpl.NONE;
    }

    @NotNull
    public static ShadowColor lerp(float t, @NotNull ARGBLike a, @NotNull ARGBLike b) {
        float clampedT = Math.min(1.0f, Math.max(0.0f, t));
        int ar = a.red();
        int br = b.red();
        int ag = a.green();
        int bg = b.green();
        int ab = a.blue();
        int bb = b.blue();
        int aa = a.alpha();
        int ba = b.alpha();
        return ShadowColor.shadowColor(Math.round((float)ar + clampedT * (float)(br - ar)), Math.round((float)ag + clampedT * (float)(bg - ag)), Math.round((float)ab + clampedT * (float)(bb - ab)), Math.round((float)aa + clampedT * (float)(ba - aa)));
    }

    default public @Range(from=0L, to=255L) int blue() {
        return this.value() & 0xFF;
    }

    default public @Range(from=0L, to=255L) int green() {
        return this.value() >> 8 & 0xFF;
    }

    @Nullable
    @Contract(pure=true)
    public static ShadowColor fromHexString(@Pattern(value="#[0-9a-fA-F]{8}") @NotNull String hex) {
        if (hex.length() != 9) {
            return null;
        }
        if (!hex.startsWith("#")) {
            return null;
        }
        try {
            int r = Integer.parseInt(hex.substring(1, 3), 16);
            int g = Integer.parseInt(hex.substring(3, 5), 16);
            int b = Integer.parseInt(hex.substring(5, 7), 16);
            int a = Integer.parseInt(hex.substring(7, 9), 16);
            return new ShadowColorImpl(a << 24 | r << 16 | g << 8 | b);
        }
        catch (NumberFormatException ignored) {
            return null;
        }
    }
}
