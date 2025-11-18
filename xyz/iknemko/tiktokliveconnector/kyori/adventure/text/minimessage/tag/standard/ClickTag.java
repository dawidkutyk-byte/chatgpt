/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Action
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.QuotingOverride
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.QuotingOverride;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

final class ClickTag {
    private static final String CLICK = "click";
    static final TagResolver RESOLVER = SerializableResolver.claimingStyle((String)"click", ClickTag::create, (StyleClaim)StyleClaim.claim((String)"click", Style::clickEvent, (event, emitter) -> emitter.tag(CLICK).argument((String)ClickEvent.Action.NAMES.key((Object)event.action())).argument(event.value(), QuotingOverride.QUOTED)));

    private ClickTag() {
    }

    static Tag create(ArgumentQueue args, Context ctx) throws ParsingException {
        String actionName = args.popOr(() -> "A click tag requires an action of one of " + ClickEvent.Action.NAMES.keys()).lowerValue();
        // Could not load outer class - annotation placement on inner may be incorrect
        @Nullable ClickEvent.Action action = (ClickEvent.Action)ClickEvent.Action.NAMES.value((Object)actionName);
        if (action == null) {
            throw ctx.newException("Unknown click event action '" + actionName + "'", args);
        }
        String value = args.popOr("Click event actions require a value").value();
        return Tag.styling((StyleBuilderApplicable[])new StyleBuilderApplicable[]{ClickEvent.clickEvent((ClickEvent.Action)action, (String)value)});
    }
}
