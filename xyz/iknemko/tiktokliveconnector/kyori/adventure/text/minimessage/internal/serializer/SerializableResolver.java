/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.TagInternals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.ClaimConsumer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.ComponentClaimingResolverImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaimingResolverImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.TagInternals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.ClaimConsumer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.ComponentClaimingResolverImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaimingResolverImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public interface SerializableResolver {
    @NotNull
    public static TagResolver claimingComponent(@NotNull String name, @NotNull BiFunction<ArgumentQueue, Context, Tag> handler, @NotNull Function<Component, @Nullable Emitable> componentClaim) {
        return SerializableResolver.claimingComponent(Collections.singleton(name), handler, componentClaim);
    }

    @NotNull
    public static TagResolver claimingComponent(@NotNull Set<String> names, @NotNull BiFunction<ArgumentQueue, Context, Tag> handler, @NotNull Function<Component, @Nullable Emitable> componentClaim) {
        HashSet<String> ownNames = new HashSet<String>(names);
        Iterator iterator = ownNames.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                Objects.requireNonNull(handler, "handler");
                return new ComponentClaimingResolverImpl(ownNames, handler, componentClaim);
            }
            String name = (String)iterator.next();
            TagInternals.assertValidTagName((String)name);
        }
    }

    @NotNull
    public static TagResolver claimingStyle(@NotNull String name, @NotNull BiFunction<ArgumentQueue, Context, Tag> handler, @NotNull StyleClaim<?> styleClaim) {
        return SerializableResolver.claimingStyle(Collections.singleton(name), handler, styleClaim);
    }

    @NotNull
    public static TagResolver claimingStyle(@NotNull Set<String> names, @NotNull BiFunction<ArgumentQueue, Context, Tag> handler, @NotNull StyleClaim<?> styleClaim) {
        HashSet<String> ownNames = new HashSet<String>(names);
        Iterator iterator = ownNames.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                Objects.requireNonNull(handler, "handler");
                return new StyleClaimingResolverImpl(ownNames, handler, styleClaim);
            }
            String name = (String)iterator.next();
            TagInternals.assertValidTagName((String)name);
        }
    }

    public void handle(@NotNull Component var1, @NotNull ClaimConsumer var2);
}
