/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

final class DecorationTag {
    private static final String ST = "st";
    private static final String B = "b";
    private static final String U = "u";
    static final Map<TextDecoration, TagResolver> RESOLVERS = Stream.of(DecorationTag.resolvers(TextDecoration.OBFUSCATED, "obf", new String[0]), DecorationTag.resolvers(TextDecoration.BOLD, "b", new String[0]), DecorationTag.resolvers(TextDecoration.STRIKETHROUGH, "st", new String[0]), DecorationTag.resolvers(TextDecoration.UNDERLINED, "u", new String[0]), DecorationTag.resolvers(TextDecoration.ITALIC, "em", "i")).collect(Collectors.toMap(Map.Entry::getKey, ent -> (TagResolver)((Stream)ent.getValue()).collect(TagResolver.toTagResolver()), (l, r) -> TagResolver.builder().resolver(l).resolver(r).build(), LinkedHashMap::new));
    public static final String REVERT = "!";
    private static final String I = "i";
    private static final String EM = "em";
    static final TagResolver RESOLVER = TagResolver.resolver(RESOLVERS.values());
    private static final String OBF = "obf";

    static Tag create(TextDecoration toApply, ArgumentQueue args, Context ctx) {
        boolean flag = !args.hasNext() || !args.pop().isFalse();
        return Tag.styling((StyleBuilderApplicable[])new StyleBuilderApplicable[]{toApply.withState(flag)});
    }

    @NotNull
    static StyleClaim<TextDecoration.State> claim(@NotNull TextDecoration decoration, @NotNull BiConsumer<TextDecoration.State, TokenEmitter> emitable) {
        Objects.requireNonNull(decoration, "decoration");
        return StyleClaim.claim((String)("decoration_" + (String)TextDecoration.NAMES.key((Object)decoration)), style -> style.decoration(decoration), state -> state != TextDecoration.State.NOT_SET, emitable);
    }

    static Tag createNegated(TextDecoration toApply) {
        return Tag.styling((StyleBuilderApplicable[])new StyleBuilderApplicable[]{toApply.withState(false)});
    }

    static void emit(@NotNull String longName, @NotNull String shortName, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State state, @NotNull TokenEmitter emitter) {
        if (state == TextDecoration.State.FALSE) {
            emitter.tag(REVERT + longName);
        } else {
            emitter.tag(longName);
        }
    }

    private DecorationTag() {
    }

    static Map.Entry<TextDecoration, Stream<TagResolver>> resolvers(TextDecoration decoration, @Nullable String shortName, String ... secondaryAliases) {
        String canonicalName = (String)TextDecoration.NAMES.key((Object)decoration);
        HashSet<String> names = new HashSet<String>();
        names.add(canonicalName);
        if (shortName != null) {
            names.add(shortName);
        }
        Collections.addAll(names, secondaryAliases);
        return new AbstractMap.SimpleImmutableEntry<TextDecoration, Stream<TagResolver>>(decoration, Stream.concat(Stream.of(SerializableResolver.claimingStyle(names, (args, ctx) -> DecorationTag.create(decoration, args, ctx), DecorationTag.claim(decoration, (state, emitter) -> DecorationTag.emit(canonicalName, shortName == null ? canonicalName : shortName, state, emitter)))), names.stream().map(name -> TagResolver.resolver((String)(REVERT + name), (Tag)DecorationTag.createNegated(decoration)))));
    }
}
