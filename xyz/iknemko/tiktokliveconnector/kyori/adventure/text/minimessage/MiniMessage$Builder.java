/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public static interface MiniMessage.Builder
extends AbstractBuilder<MiniMessage> {
    @NotNull
    public MiniMessage.Builder tags(@NotNull TagResolver var1);

    @NotNull
    public MiniMessage build();

    @NotNull
    public MiniMessage.Builder debug(@Nullable Consumer<String> var1);

    @NotNull
    public MiniMessage.Builder postProcessor(@NotNull UnaryOperator<Component> var1);

    @NotNull
    public MiniMessage.Builder strict(boolean var1);

    @NotNull
    public MiniMessage.Builder preProcessor(@NotNull UnaryOperator<String> var1);

    @NotNull
    public MiniMessage.Builder emitVirtuals(boolean var1);

    @NotNull
    public MiniMessage.Builder editTags(@NotNull Consumer<TagResolver.Builder> var1);
}
