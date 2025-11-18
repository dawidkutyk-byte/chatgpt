/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$Action
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag$ActionHandler
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag$ShowEntity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag$ShowItem
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag$ShowText
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag;

/*
 * Exception performing whole class analysis ignored.
 */
final class HoverTag {
    static final TagResolver RESOLVER = SerializableResolver.claimingStyle((String)"hover", HoverTag::create, (StyleClaim)StyleClaim.claim((String)"hover", Style::hoverEvent, HoverTag::emit));
    private static final String HOVER = "hover";

    @Nullable
    static <V> ActionHandler<V> actionHandler(HoverEvent.Action<V> action) {
        ShowText ret = null;
        if (action == HoverEvent.Action.SHOW_TEXT) {
            ret = ShowText.access$000();
        } else if (action == HoverEvent.Action.SHOW_ITEM) {
            ret = ShowItem.access$100();
        } else {
            if (action != HoverEvent.Action.SHOW_ENTITY) return ret;
            ret = ShowEntity.INSTANCE;
        }
        return ret;
    }

    static Tag create(ArgumentQueue args, Context ctx) throws ParsingException {
        String actionName = args.popOr("Hover event requires an action as its first argument").value();
        HoverEvent.Action action = (HoverEvent.Action)HoverEvent.Action.NAMES.value((Object)actionName);
        ActionHandler value = HoverTag.actionHandler(action);
        if (value != null) return Tag.styling((StyleBuilderApplicable[])new StyleBuilderApplicable[]{HoverEvent.hoverEvent((HoverEvent.Action)action, (Object)value.parse(args, ctx))});
        throw ctx.newException("Don't know how to turn '" + args + "' into a hover event", args);
    }

    static void emit(HoverEvent<?> event, TokenEmitter emitter) {
        ActionHandler handler = HoverTag.actionHandler(event.action());
        emitter.tag("hover").argument((String)HoverEvent.Action.NAMES.key((Object)event.action()));
        handler.emit(event.value(), emitter);
    }

    private HoverTag() {
    }

    @NotNull
    static String compactAsString(@NotNull Key key) {
        if (!key.namespace().equals("minecraft")) return key.asString();
        return key.value();
    }
}
