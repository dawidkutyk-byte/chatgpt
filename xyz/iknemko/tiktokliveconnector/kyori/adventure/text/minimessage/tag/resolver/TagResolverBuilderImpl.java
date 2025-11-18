/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.TagInternals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.EmptyTagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.MapTagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.MappableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.SequentialTagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.TagInternals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.EmptyTagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.MapTagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.MappableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.SequentialTagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

final class TagResolverBuilderImpl
implements TagResolver.Builder {
    static final Collector<TagResolver, TagResolver.Builder, TagResolver> COLLECTOR = Collector.of(TagResolver::builder, TagResolver.Builder::resolver, (left, right) -> TagResolver.builder().resolvers(new TagResolver[]{left.build(), right.build()}), TagResolver.Builder::build, new Collector.Characteristics[0]);
    private final List<TagResolver> resolvers;
    private final Map<String, Tag> replacements = new HashMap<String, Tag>();

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TagResolver.Builder resolvers(TagResolver ... resolvers) {
        return this.resolvers(resolvers, true);
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TagResolver.Builder resolvers(@NotNull Iterable<? extends TagResolver> resolvers) {
        boolean popped = false;
        Iterator<? extends TagResolver> iterator = Objects.requireNonNull(resolvers, "resolvers").iterator();
        while (iterator.hasNext()) {
            TagResolver resolver = iterator.next();
            popped = this.single(resolver, popped);
        }
        return this;
    }

    private void popMap() {
        if (this.replacements.isEmpty()) return;
        this.resolvers.add((TagResolver)new MapTagResolver(new HashMap<String, Tag>(this.replacements)));
        this.replacements.clear();
    }

    @NotNull
    public TagResolver build() {
        this.popMap();
        if (this.resolvers.size() == 0) {
            return EmptyTagResolver.INSTANCE;
        }
        if (this.resolvers.size() == 1) {
            return this.resolvers.get(0);
        }
        TagResolver[] resolvers = this.resolvers.toArray(new TagResolver[0]);
        Collections.reverse(Arrays.asList(resolvers));
        return new SequentialTagResolver(resolvers);
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TagResolver.Builder tag(@NotNull String name, @NotNull Tag tag) {
        TagInternals.assertValidTagName((String)Objects.requireNonNull(name, "name"));
        this.replacements.put(name, Objects.requireNonNull(tag, "tag"));
        return this;
    }

    TagResolverBuilderImpl() {
        this.resolvers = new ArrayList<TagResolver>();
    }

    private // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TagResolver.Builder resolvers(@NotNull @NotNull TagResolver @NotNull [] resolvers, boolean forwards) {
        boolean popped = false;
        Objects.requireNonNull(resolvers, "resolvers");
        if (forwards) {
            TagResolver[] tagResolverArray = resolvers;
            int n = tagResolverArray.length;
            int n2 = 0;
            while (n2 < n) {
                TagResolver resolver = tagResolverArray[n2];
                popped = this.single(resolver, popped);
                ++n2;
            }
            return this;
        }
        int i = resolvers.length - 1;
        while (i >= 0) {
            popped = this.single(resolvers[i], popped);
            --i;
        }
        return this;
    }

    private boolean consumePotentialMappable(TagResolver resolver) {
        if (!(resolver instanceof MappableResolver)) return false;
        return ((MappableResolver)resolver).contributeToMap(this.replacements);
    }

    private boolean single(TagResolver resolver, boolean popped) {
        if (resolver instanceof SequentialTagResolver) {
            this.resolvers(((SequentialTagResolver)resolver).resolvers, false);
            return false;
        }
        if (this.consumePotentialMappable(resolver)) return false;
        if (!popped) {
            this.popMap();
        }
        this.resolvers.add(Objects.requireNonNull(resolver, "resolvers[?]"));
        return true;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TagResolver.Builder resolver(@NotNull TagResolver resolver) {
        if (resolver instanceof SequentialTagResolver) {
            this.resolvers(((SequentialTagResolver)resolver).resolvers, false);
        } else {
            if (this.consumePotentialMappable(resolver)) return this;
            this.popMap();
            this.resolvers.add(Objects.requireNonNull(resolver, "resolver"));
        }
        return this;
    }
}
