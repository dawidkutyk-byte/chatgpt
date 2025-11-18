/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.MappableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver$WithoutArguments
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver;

import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.MappableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

final class MapTagResolver
implements TagResolver.WithoutArguments,
MappableResolver {
    private final Map<String, ? extends Tag> tagMap;

    @Nullable
    public Tag resolve(@NotNull String name) {
        return this.tagMap.get(name);
    }

    public boolean contributeToMap(@NotNull Map<String, Tag> map) {
        map.putAll(this.tagMap);
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.tagMap);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MapTagResolver)) {
            return false;
        }
        MapTagResolver that = (MapTagResolver)other;
        return Objects.equals(this.tagMap, that.tagMap);
    }

    MapTagResolver(@NotNull Map<String, ? extends Tag> placeholderMap) {
        this.tagMap = placeholderMap;
    }
}
