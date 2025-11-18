/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.TranslationStore
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.translation;

import java.nio.file.Path;
import java.util.Locale;
import java.util.ResourceBundle;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.TranslationStore;

public static interface TranslationStore.StringBased<T>
extends TranslationStore<T> {
    public void registerAll(@NotNull Locale var1, @NotNull Path var2, boolean var3);

    public void registerAll(@NotNull Locale var1, @NotNull ResourceBundle var2, boolean var3);
}
