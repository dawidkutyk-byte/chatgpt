/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslationStore
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslationStoreImpl$Translator
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.AbstractTranslationStore$StringBased
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslationStore;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslationStoreImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.AbstractTranslationStore;

final class MiniMessageTranslationStoreImpl
extends AbstractTranslationStore.StringBased<String>
implements MiniMessageTranslationStore {
    private final Translator translator;

    MiniMessageTranslationStoreImpl(@NotNull Key name, @NotNull MiniMessage miniMessage) {
        super(name);
        this.translator = new Translator(this, Objects.requireNonNull(miniMessage, "miniMessage"), null);
    }

    @Nullable
    public MessageFormat translate(@NotNull String key, @NotNull Locale locale) {
        return null;
    }

    @NotNull
    protected String parse(@NotNull String string, @NotNull Locale locale) {
        return string;
    }

    static /* synthetic */ Object access$100(MiniMessageTranslationStoreImpl x0, String x1, Locale x2) {
        return x0.translationValue(x1, x2);
    }

    @Nullable
    public Component translate(@NotNull TranslatableComponent component, @NotNull Locale locale) {
        return this.translator.translate(component, locale);
    }
}
