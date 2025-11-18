/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.AbstractTranslationStore$StringBased
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.TranslationRegistry
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.translation;

import java.text.MessageFormat;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.AbstractTranslationStore;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.TranslationRegistry;

final class MessageFormatTranslationStore
extends AbstractTranslationStore.StringBased<MessageFormat>
implements TranslationRegistry {
    @Nullable
    public MessageFormat translate(@NotNull String key, @NotNull Locale locale) {
        return (MessageFormat)this.translationValue(key, locale);
    }

    @NotNull
    protected MessageFormat parse(@NotNull String string, @NotNull Locale locale) {
        return new MessageFormat(string, locale);
    }

    MessageFormatTranslationStore(Key name) {
        super(name);
    }
}
