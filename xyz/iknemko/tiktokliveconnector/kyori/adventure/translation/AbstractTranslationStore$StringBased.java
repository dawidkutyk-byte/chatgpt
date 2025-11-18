/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.AbstractTranslationStore
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.TranslationStore$StringBased
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.translation;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.AbstractTranslationStore;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.TranslationStore;

public static abstract class AbstractTranslationStore.StringBased<T>
extends AbstractTranslationStore<T>
implements TranslationStore.StringBased<T> {
    private static final Pattern SINGLE_QUOTE_PATTERN = Pattern.compile("'");

    public final void registerAll(@NotNull Locale locale, @NotNull Path path, boolean escapeSingleQuotes) {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);){
            this.registerAll(locale, new PropertyResourceBundle(reader), escapeSingleQuotes);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    protected AbstractTranslationStore.StringBased(@NotNull Key name) {
        super(name);
    }

    public final void registerAll(@NotNull Locale locale, @NotNull ResourceBundle bundle, boolean escapeSingleQuotes) {
        this.registerAll(locale, bundle.keySet(), key -> {
            String format = bundle.getString((String)key);
            return this.parse(escapeSingleQuotes ? SINGLE_QUOTE_PATTERN.matcher(format).replaceAll("''") : format, locale);
        });
    }

    @NotNull
    protected abstract T parse(@NotNull String var1, @NotNull Locale var2);
}
