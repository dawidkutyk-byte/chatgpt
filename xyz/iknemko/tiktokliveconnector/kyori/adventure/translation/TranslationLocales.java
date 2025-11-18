/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventureProperties
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.translation;

import java.util.Locale;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventureProperties;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator;

final class TranslationLocales {
    private static final Supplier<Locale> GLOBAL;

    static Locale global() {
        return GLOBAL.get();
    }

    static {
        @Nullable String property = (String)AdventureProperties.DEFAULT_TRANSLATION_LOCALE.value();
        if (property == null || property.isEmpty()) {
            GLOBAL = () -> Locale.US;
        } else if (property.equals("system")) {
            GLOBAL = Locale::getDefault;
        } else {
            Locale locale = Translator.parseLocale((String)property);
            GLOBAL = () -> locale;
        }
    }

    private TranslationLocales() {
    }
}
