/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.InvalidKeyException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowEntity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag$ActionHandler
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.InvalidKeyException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag;

/*
 * Exception performing whole class analysis ignored.
 */
static final class HoverTag.ShowEntity
implements HoverTag.ActionHandler<HoverEvent.ShowEntity> {
    static final HoverTag.ShowEntity INSTANCE = new HoverTag.ShowEntity();

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowEntity parse(@NotNull ArgumentQueue args, @NotNull Context ctx) throws ParsingException {
        try {
            Key key = Key.key((String)args.popOr("Show entity needs a type argument").value());
            UUID id = UUID.fromString(args.popOr("Show entity needs an entity UUID").value());
            if (!args.hasNext()) return HoverEvent.ShowEntity.showEntity((Key)key, (UUID)id);
            Component name = ctx.deserialize(args.pop().value());
            return HoverEvent.ShowEntity.showEntity((Key)key, (UUID)id, (Component)name);
        }
        catch (IllegalArgumentException | InvalidKeyException ex) {
            throw ctx.newException("Exception parsing show_entity hover", ex, args);
        }
    }

    private HoverTag.ShowEntity() {
    }

    public void emit(HoverEvent.ShowEntity event, TokenEmitter emit) {
        emit.argument(HoverTag.compactAsString((Key)event.type())).argument(event.id().toString());
        if (event.name() == null) return;
        emit.argument(event.name());
    }
}
