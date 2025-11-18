/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.translation;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

public interface Translator {
    @Nullable
    public MessageFormat translate(@NotNull String var1, @NotNull Locale var2);

    @Nullable
    default public Component translate(@NotNull TranslatableComponent component, @NotNull Locale locale) {
        return null;
    }

    @NotNull
    default public TriState hasAnyTranslations() {
        return TriState.NOT_SET;
    }

    @Nullable
    public static Locale parseLocale(@NotNull String string) {
        String[] segments = string.split("_", 3);
        int length = segments.length;
        if (length == 1) {
            return new Locale(string);
        }
        if (length == 2) {
            return new Locale(segments[0], segments[1]);
        }
        if (length != 3) return null;
        return new Locale(segments[0], segments[1], segments[2]);
    }

    @NotNull
    public Key name();

    default public boolean canTranslate(@NotNull String key, @NotNull Locale locale) {
        Component translatedValue = this.translate(Component.translatable((String)Objects.requireNonNull(key, "key")), Objects.requireNonNull(locale, "locale"));
        if (translatedValue == null) return this.translate(key, locale) != null;
        return true;
    }
}
