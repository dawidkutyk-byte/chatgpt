/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver$Single
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer;

import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

class ComponentClaimingResolverImpl
implements TagResolver,
SerializableResolver.Single {
    @NotNull
    private final Set<String> names;
    @NotNull
    private final BiFunction<ArgumentQueue, Context, Tag> handler;
    @NotNull
    private final Function<Component, @Nullable Emitable> componentClaim;

    @Nullable
    public Emitable claimComponent(@NotNull Component component) {
        return this.componentClaim.apply(component);
    }

    public boolean has(@NotNull String name) {
        return this.names.contains(name);
    }

    @Nullable
    public Tag resolve(@NotNull String name, @NotNull ArgumentQueue arguments, @NotNull Context ctx) throws ParsingException {
        if (this.names.contains(name)) return this.handler.apply(arguments, ctx);
        return null;
    }

    ComponentClaimingResolverImpl(Set<String> names, BiFunction<ArgumentQueue, Context, Tag> handler, Function<Component, @Nullable Emitable> componentClaim) {
        this.names = names;
        this.handler = handler;
        this.componentClaim = componentClaim;
    }
}
