/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgumentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslatorArgument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslatorTarget
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgumentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslatorArgument;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslatorTarget;

public final class Argument {
    private Argument() {
    }

    @NotNull
    public static ComponentLike target(@NotNull Pointered target) {
        return Component.virtual(Void.class, (VirtualComponentRenderer)new MiniMessageTranslatorTarget(Objects.requireNonNull(target, "target")));
    }

    @NotNull
    public static ComponentLike string(@TagPattern @NotNull String name, @NotNull String value) {
        return Argument.argument(name, TranslationArgument.component((ComponentLike)Component.text((String)value)));
    }

    @NotNull
    public static ComponentLike numeric(@TagPattern @NotNull String name, @NotNull Number value) {
        return Argument.argument(name, TranslationArgument.numeric((Number)value));
    }

    @NotNull
    public static ComponentLike tagResolver(@NotNull TagResolver tagResolver) {
        return Component.virtual(Void.class, (VirtualComponentRenderer)new MiniMessageTranslatorArgument("unused", (Object)Objects.requireNonNull(tagResolver, "tagResolver")));
    }

    @NotNull
    public static ComponentLike tag(@TagPattern @NotNull String name, @NotNull Tag tag) {
        return Component.virtual(Void.class, (VirtualComponentRenderer)new MiniMessageTranslatorArgument(name, (Object)Objects.requireNonNull(tag, "tag")));
    }

    @NotNull
    public static ComponentLike component(@TagPattern @NotNull String name, @NotNull ComponentLike value) {
        return Argument.argument(name, TranslationArgument.component((ComponentLike)value));
    }

    @Deprecated
    @NotNull
    public static ComponentLike numeric(@TagPattern @NotNull String name, @NotNull String value) {
        return Argument.string(name, value);
    }

    @NotNull
    public static ComponentLike argument(@TagPattern @NotNull String name, @NotNull TranslationArgument argument) {
        return Component.virtual(Void.class, (VirtualComponentRenderer)new MiniMessageTranslatorArgument(name, (Object)Objects.requireNonNull(argument, "argument")));
    }

    @NotNull
    public static ComponentLike bool(@TagPattern @NotNull String name, boolean value) {
        return Argument.argument(name, TranslationArgument.bool((boolean)value));
    }

    @NotNull
    public static ComponentLike tagResolver(@NotNull Iterable<TagResolver> resolvers) {
        return Argument.tagResolver(TagResolver.resolver(resolvers));
    }

    @NotNull
    public static ComponentLike argument(@TagPattern @NotNull String name, @NotNull TranslationArgumentLike argument) {
        return Argument.argument(name, Objects.requireNonNull(argument, "argument").asTranslationArgument());
    }

    @NotNull
    public static ComponentLike tagResolver(TagResolver ... resolvers) {
        return Argument.tagResolver(TagResolver.resolver((TagResolver[])resolvers));
    }
}
