/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslationStoreImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslator
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation;

import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslationStoreImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslator;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

/*
 * Exception performing whole class analysis ignored.
 */
private final class MiniMessageTranslationStoreImpl.Translator
extends MiniMessageTranslator {
    @NotNull
    public Key name() {
        return MiniMessageTranslationStoreImpl.this.name();
    }

    private MiniMessageTranslationStoreImpl.Translator(MiniMessage miniMessage) {
        super(miniMessage);
    }

    @NotNull
    public TriState hasAnyTranslations() {
        return MiniMessageTranslationStoreImpl.this.hasAnyTranslations();
    }

    @Nullable
    protected String getMiniMessageString(@NotNull String key, @NotNull Locale locale) {
        return (String)MiniMessageTranslationStoreImpl.access$100((MiniMessageTranslationStoreImpl)MiniMessageTranslationStoreImpl.this, (String)key, (Locale)locale);
    }
}
