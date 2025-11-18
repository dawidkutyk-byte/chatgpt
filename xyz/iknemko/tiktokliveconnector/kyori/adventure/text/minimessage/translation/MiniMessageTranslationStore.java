/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslationStoreImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.TranslationStore$StringBased
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslationStoreImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.TranslationStore;

@ApiStatus.NonExtendable
public interface MiniMessageTranslationStore
extends TranslationStore.StringBased<String> {
    @NotNull
    public static MiniMessageTranslationStore create(@NotNull Key name) {
        return MiniMessageTranslationStore.create(name, MiniMessage.miniMessage());
    }

    @NotNull
    public static MiniMessageTranslationStore create(@NotNull Key name, @NotNull MiniMessage miniMessage) {
        return new MiniMessageTranslationStoreImpl(name, miniMessage);
    }
}
