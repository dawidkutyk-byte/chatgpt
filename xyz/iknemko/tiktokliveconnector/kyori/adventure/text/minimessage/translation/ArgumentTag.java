/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

final class ArgumentTag
implements TagResolver {
    private static final String NAME_1 = "arg";
    private static final String NAME = "argument";
    private final List<Tag> arguments;
    private final TagResolver tagResolver;

    ArgumentTag(@NotNull List<Tag> arguments, @NotNull TagResolver tagResolver) {
        this.arguments = arguments;
        this.tagResolver = tagResolver;
    }

    @Nullable
    public Tag resolve(@NotNull String name, @NotNull ArgumentQueue arguments, @NotNull Context ctx) throws ParsingException {
        int index;
        if (!name.equals(NAME)) {
            if (!name.equals(NAME_1)) return this.tagResolver.resolve(name, arguments, ctx);
        }
        if ((index = arguments.popOr("No argument number provided").asInt().orElseThrow(() -> ctx.newException("Invalid argument number", arguments))) < 0) throw ctx.newException("Invalid argument number", arguments);
        if (index < this.arguments.size()) return this.arguments.get(index);
        throw ctx.newException("Invalid argument number", arguments);
    }

    public boolean has(@NotNull String name) {
        return name.equals(NAME) || name.equals(NAME_1) || this.tagResolver.has(name);
    }
}
