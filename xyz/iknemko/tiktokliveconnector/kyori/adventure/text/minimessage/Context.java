/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

@ApiStatus.NonExtendable
public interface Context {
    @NotNull
    public Component deserialize(@NotNull String var1, @NotNull TagResolver var2);

    @NotNull
    public ParsingException newException(@NotNull String var1);

    public boolean emitVirtuals();

    @NotNull
    public ParsingException newException(@NotNull String var1, @Nullable Throwable var2, @NotNull ArgumentQueue var3);

    @NotNull
    public ParsingException newException(@NotNull String var1, @NotNull ArgumentQueue var2);

    @NotNull
    public Pointered targetOrThrow();

    @Nullable
    public Pointered target();

    @NotNull
    public Component deserialize(@NotNull String var1, TagResolver ... var2);

    @NotNull
    public <T extends Pointered> T targetAsType(@NotNull Class<T> var1);

    @NotNull
    public Component deserialize(@NotNull String var1);
}
