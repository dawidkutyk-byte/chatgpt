/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.TagInternals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.CachingTagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.EmptyTagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.SingleResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolverBuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.StandardTags
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.TagInternals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.CachingTagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.EmptyTagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.SingleResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolverBuilderImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.StandardTags;

public interface TagResolver {
    @NotNull
    public static TagResolver empty() {
        return EmptyTagResolver.INSTANCE;
    }

    @NotNull
    public static TagResolver caching(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TagResolver.WithoutArguments resolver) {
        if (!(resolver instanceof CachingTagResolver)) return new CachingTagResolver(Objects.requireNonNull(resolver, "resolver"));
        return resolver;
    }

    public static // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TagResolver.Single resolver(@TagPattern @NotNull String name, @NotNull Tag tag) {
        TagInternals.assertValidTagName((String)name);
        return new SingleResolver(name, Objects.requireNonNull(tag, "tag"));
    }

    @NotNull
    public static TagResolver resolver(@TagPattern @NotNull String name, @NotNull BiFunction<ArgumentQueue, Context, Tag> handler) {
        return TagResolver.resolver(Collections.singleton(name), handler);
    }

    @NotNull
    public static TagResolver resolver(@NotNull Set<String> names, @NotNull BiFunction<ArgumentQueue, Context, Tag> handler) {
        HashSet<String> ownNames = new HashSet<String>(names);
        Iterator iterator = ownNames.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                Objects.requireNonNull(handler, "handler");
                return new /* Unavailable Anonymous Inner Class!! */;
            }
            String name = (String)iterator.next();
            TagInternals.assertValidTagName((String)name);
        }
    }

    @NotNull
    public static TagResolver resolver(@NotNull Iterable<? extends TagResolver> resolvers) {
        if (!(resolvers instanceof Collection)) return TagResolver.builder().resolvers(resolvers).build();
        int size = ((Collection)resolvers).size();
        if (size == 0) {
            return TagResolver.empty();
        }
        if (size != 1) return TagResolver.builder().resolvers(resolvers).build();
        return Objects.requireNonNull(resolvers.iterator().next(), "resolvers must not contain null elements");
    }

    @NotNull
    public static Collector<TagResolver, ?, TagResolver> toTagResolver() {
        return TagResolverBuilderImpl.COLLECTOR;
    }

    @NotNull
    public static Builder builder() {
        return new TagResolverBuilderImpl();
    }

    public boolean has(@NotNull String var1);

    @NotNull
    public static TagResolver resolver(TagResolver ... resolvers) {
        if (Objects.requireNonNull(resolvers, "resolvers").length != 1) return TagResolver.builder().resolvers(resolvers).build();
        return Objects.requireNonNull(resolvers[0], "resolvers must not contain null elements");
    }

    @NotNull
    public static TagResolver standard() {
        return StandardTags.defaults();
    }

    @Nullable
    public Tag resolve(@TagPattern @NotNull String var1, @NotNull ArgumentQueue var2, @NotNull Context var3) throws ParsingException;
}
