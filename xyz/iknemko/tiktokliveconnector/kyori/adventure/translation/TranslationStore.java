/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.ComponentTranslationStore
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.MessageFormatTranslationStore
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.translation;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.ComponentTranslationStore;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.MessageFormatTranslationStore;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator;

public interface TranslationStore<T>
extends Translator {
    public void registerAll(@NotNull Locale var1, @NotNull Map<String, T> var2);

    public void defaultLocale(@NotNull Locale var1);

    public boolean contains(@NotNull String var1);

    public boolean contains(@NotNull String var1, @NotNull Locale var2);

    public void registerAll(@NotNull Locale var1, @NotNull Set<String> var2, Function<String, T> var3);

    public void unregister(@NotNull String var1);

    public static // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TranslationStore.StringBased<MessageFormat> messageFormat(@NotNull Key name) {
        return new MessageFormatTranslationStore(Objects.requireNonNull(name, "name"));
    }

    public void register(@NotNull String var1, @NotNull Locale var2, T var3);

    @NotNull
    public static TranslationStore<Component> component(@NotNull Key name) {
        return new ComponentTranslationStore(Objects.requireNonNull(name, "name"));
    }

    default public boolean canTranslate(@NotNull String key, @NotNull Locale locale) {
        return super.canTranslate(key, locale);
    }
}
