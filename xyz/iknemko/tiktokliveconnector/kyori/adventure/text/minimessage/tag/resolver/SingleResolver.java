/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.MappableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver$Single
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver;

import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.MappableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

final class SingleResolver
implements TagResolver.Single,
MappableResolver {
    private final Tag tag;
    private final String key;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        SingleResolver that = (SingleResolver)other;
        return Objects.equals(this.key, that.key) && Objects.equals(this.tag, that.tag);
    }

    public int hashCode() {
        return Objects.hash(this.key, this.tag);
    }

    @NotNull
    public Tag tag() {
        return this.tag;
    }

    @NotNull
    public String key() {
        return this.key;
    }

    public boolean contributeToMap(@NotNull Map<String, Tag> map) {
        map.put(this.key, this.tag);
        return true;
    }

    SingleResolver(String key, Tag tag) {
        this.key = key;
        this.tag = tag;
    }
}
