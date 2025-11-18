/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag$ActionHandler
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag;

static final class HoverTag.ShowText
implements HoverTag.ActionHandler<Component> {
    private static final HoverTag.ShowText INSTANCE = new HoverTag.ShowText();

    private HoverTag.ShowText() {
    }

    @NotNull
    public Component parse(@NotNull ArgumentQueue args, @NotNull Context ctx) throws ParsingException {
        return ctx.deserialize(args.popOr("show_text action requires a message").value());
    }

    static /* synthetic */ HoverTag.ShowText access$000() {
        return INSTANCE;
    }

    public void emit(Component event, TokenEmitter emit) {
        emit.argument(event);
    }
}
