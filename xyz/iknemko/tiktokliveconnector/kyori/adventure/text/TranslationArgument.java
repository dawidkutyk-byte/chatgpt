/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgumentImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgumentLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Objects;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgumentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgumentLike;

@ApiStatus.NonExtendable
public interface TranslationArgument
extends TranslationArgumentLike,
Examinable {
    @NotNull
    public static TranslationArgument numeric(@NotNull Number value) {
        return new TranslationArgumentImpl((Object)Objects.requireNonNull(value, "value"));
    }

    @NotNull
    public static TranslationArgument component(@NotNull ComponentLike value) {
        if (!(value instanceof TranslationArgumentLike)) return new TranslationArgumentImpl((Object)Objects.requireNonNull(Objects.requireNonNull(value, "value").asComponent(), "value.asComponent()"));
        return ((TranslationArgumentLike)value).asTranslationArgument();
    }

    @NotNull
    public Object value();

    @NotNull
    public static TranslationArgument bool(boolean value) {
        return new TranslationArgumentImpl((Object)value);
    }

    @NotNull
    default public TranslationArgument asTranslationArgument() {
        return this;
    }
}
