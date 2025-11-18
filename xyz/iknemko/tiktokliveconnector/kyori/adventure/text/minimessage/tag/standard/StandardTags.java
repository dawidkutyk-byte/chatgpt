/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ClickTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ColorTagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.DecorationTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.FontTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.GradientTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.InsertionTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.KeybindTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.NbtTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.NewlineTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.PrideTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.RainbowTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ResetTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ScoreTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.SelectorTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ShadowColorTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.TransitionTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.TranslatableFallbackTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.TranslatableTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ClickTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ColorTagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.DecorationTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.FontTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.GradientTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.InsertionTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.KeybindTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.NbtTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.NewlineTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.PrideTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.RainbowTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ResetTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ScoreTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.SelectorTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ShadowColorTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.TransitionTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.TranslatableFallbackTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.TranslatableTag;

public final class StandardTags {
    private static final TagResolver ALL = TagResolver.builder().resolvers(new TagResolver[]{HoverTag.RESOLVER, ClickTag.RESOLVER, ColorTagResolver.INSTANCE, KeybindTag.RESOLVER, TranslatableTag.RESOLVER, TranslatableFallbackTag.RESOLVER, InsertionTag.RESOLVER, FontTag.RESOLVER, DecorationTag.RESOLVER, GradientTag.RESOLVER, RainbowTag.RESOLVER, ResetTag.RESOLVER, NewlineTag.RESOLVER, TransitionTag.RESOLVER, SelectorTag.RESOLVER, ScoreTag.RESOLVER, NbtTag.RESOLVER, PrideTag.RESOLVER, ShadowColorTag.RESOLVER}).build();

    private StandardTags() {
    }

    @NotNull
    public static TagResolver nbt() {
        return NbtTag.RESOLVER;
    }

    @NotNull
    public static TagResolver hoverEvent() {
        return HoverTag.RESOLVER;
    }

    @NotNull
    public static TagResolver translatableFallback() {
        return TranslatableFallbackTag.RESOLVER;
    }

    @NotNull
    public static TagResolver insertion() {
        return InsertionTag.RESOLVER;
    }

    @NotNull
    public static TagResolver gradient() {
        return GradientTag.RESOLVER;
    }

    @NotNull
    public static TagResolver clickEvent() {
        return ClickTag.RESOLVER;
    }

    @NotNull
    public static TagResolver decorations() {
        return DecorationTag.RESOLVER;
    }

    public static TagResolver transition() {
        return TransitionTag.RESOLVER;
    }

    @NotNull
    public static TagResolver translatable() {
        return TranslatableTag.RESOLVER;
    }

    @NotNull
    public static TagResolver pride() {
        return PrideTag.RESOLVER;
    }

    @NotNull
    public static TagResolver selector() {
        return SelectorTag.RESOLVER;
    }

    @NotNull
    public static TagResolver shadowColor() {
        return ShadowColorTag.RESOLVER;
    }

    @NotNull
    public static TagResolver color() {
        return ColorTagResolver.INSTANCE;
    }

    @NotNull
    public static TagResolver newline() {
        return NewlineTag.RESOLVER;
    }

    @NotNull
    public static TagResolver keybind() {
        return KeybindTag.RESOLVER;
    }

    @NotNull
    public static TagResolver score() {
        return ScoreTag.RESOLVER;
    }

    @NotNull
    public static TagResolver reset() {
        return ResetTag.RESOLVER;
    }

    static Set<String> names(String ... names) {
        return new HashSet<String>(Arrays.asList(names));
    }

    @NotNull
    public static TagResolver defaults() {
        return ALL;
    }

    @NotNull
    public static TagResolver rainbow() {
        return RainbowTag.RESOLVER;
    }

    @NotNull
    public static TagResolver decorations(@NotNull TextDecoration decoration) {
        return Objects.requireNonNull((TagResolver)DecorationTag.RESOLVERS.get(decoration), "No resolver found for decoration (this should not be possible?)");
    }

    @NotNull
    public static TagResolver font() {
        return FontTag.RESOLVER;
    }
}
