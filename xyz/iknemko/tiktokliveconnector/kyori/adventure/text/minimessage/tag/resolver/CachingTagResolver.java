/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.ClaimConsumer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.MappableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver$WithoutArguments
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.ClaimConsumer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.MappableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

final class CachingTagResolver
implements SerializableResolver,
MappableResolver,
TagResolver.WithoutArguments {
    private static final Tag NULL_REPLACEMENT = () -> {
        throw new UnsupportedOperationException("no-op null tag");
    };
    private final Map<String, Tag> cache = new HashMap<String, Tag>();
    private final TagResolver.WithoutArguments resolver;

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CachingTagResolver)) {
            return false;
        }
        CachingTagResolver that = (CachingTagResolver)other;
        return Objects.equals(this.resolver, that.resolver);
    }

    private Tag query(@NotNull String key) {
        return this.cache.computeIfAbsent(key, k -> {
            @Nullable Tag result = this.resolver.resolve(k);
            return result == null ? NULL_REPLACEMENT : result;
        });
    }

    CachingTagResolver(TagResolver.WithoutArguments resolver) {
        this.resolver = resolver;
    }

    @Nullable
    public Tag resolve(@NotNull String name) {
        Tag potentialValue = this.query(name);
        return potentialValue == NULL_REPLACEMENT ? null : potentialValue;
    }

    public int hashCode() {
        return Objects.hash(this.resolver);
    }

    public boolean contributeToMap(@NotNull Map<String, Tag> map) {
        if (!(this.resolver instanceof MappableResolver)) return false;
        return ((MappableResolver)this.resolver).contributeToMap(map);
    }

    public boolean has(@NotNull String name) {
        return this.query(name) != NULL_REPLACEMENT;
    }

    public void handle(@NotNull Component serializable, @NotNull ClaimConsumer consumer) {
        if (!(this.resolver instanceof SerializableResolver)) return;
        ((SerializableResolver)this.resolver).handle(serializable, consumer);
    }
}
