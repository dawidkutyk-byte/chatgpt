/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public final class Placeholder {
    public static // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TagResolver.Single component(@TagPattern @NotNull String key, @NotNull ComponentLike value) {
        return TagResolver.resolver((String)key, (Tag)Tag.selfClosingInserting((ComponentLike)value));
    }

    private Placeholder() {
    }

    public static // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TagResolver.Single unparsed(@TagPattern @NotNull String key, @NotNull String value) {
        Objects.requireNonNull(value, "value");
        return Placeholder.component(key, (ComponentLike)Component.text((String)value));
    }

    public static // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TagResolver.Single styling(@TagPattern @NotNull String key, StyleBuilderApplicable ... style) {
        return TagResolver.resolver((String)key, (Tag)Tag.styling((StyleBuilderApplicable[])style));
    }

    public static // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TagResolver.Single parsed(@TagPattern @NotNull String key, @NotNull String value) {
        return TagResolver.resolver((String)key, (Tag)Tag.preProcessParsed((String)value));
    }
}
