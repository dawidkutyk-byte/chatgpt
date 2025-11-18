/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver$WithoutArguments
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

@ApiStatus.NonExtendable
public static interface TagResolver.Single
extends TagResolver.WithoutArguments {
    @Nullable
    default public Tag resolve(@TagPattern @NotNull String name) {
        if (!this.has(name)) return null;
        return this.tag();
    }

    @NotNull
    public Tag tag();

    @NotNull
    public String key();

    default public boolean has(@NotNull String name) {
        return name.equalsIgnoreCase(this.key());
    }
}
