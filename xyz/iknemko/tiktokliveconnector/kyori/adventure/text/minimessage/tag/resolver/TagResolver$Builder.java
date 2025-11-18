/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver$WithoutArguments
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiFunction;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

/*
 * Exception performing whole class analysis ignored.
 */
public static interface TagResolver.Builder {
    @NotNull
    default public TagResolver.Builder tag(@NotNull Set<String> names, @NotNull BiFunction<ArgumentQueue, Context, Tag> handler) {
        return this.resolver(TagResolver.resolver(names, handler));
    }

    @NotNull
    public TagResolver build();

    @NotNull
    public TagResolver.Builder resolvers(TagResolver ... var1);

    @NotNull
    default public TagResolver.Builder caching(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TagResolver.WithoutArguments dynamic) {
        return this.resolver(TagResolver.caching((TagResolver.WithoutArguments)dynamic));
    }

    @NotNull
    public TagResolver.Builder tag(@TagPattern @NotNull String var1, @NotNull Tag var2);

    @NotNull
    public TagResolver.Builder resolvers(@NotNull Iterable<? extends TagResolver> var1);

    @NotNull
    public TagResolver.Builder resolver(@NotNull TagResolver var1);

    @NotNull
    default public TagResolver.Builder tag(@TagPattern @NotNull String name, @NotNull BiFunction<ArgumentQueue, Context, Tag> handler) {
        return this.tag(Collections.singleton(name), handler);
    }
}
