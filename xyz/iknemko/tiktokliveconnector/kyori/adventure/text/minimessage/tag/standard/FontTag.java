/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.InvalidKeyException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.InvalidKeyException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

final class FontTag {
    static final String FONT = "font";
    static final TagResolver RESOLVER = SerializableResolver.claimingStyle((String)"font", FontTag::create, (StyleClaim)StyleClaim.claim((String)"font", Style::font, FontTag::emit));

    private FontTag() {
    }

    static Tag create(ArgumentQueue args, Context ctx) throws ParsingException {
        Key font;
        String valueOrNamespace = args.popOr("A font tag must have either arguments of either <value> or <namespace:value>").value();
        try {
            if (!args.hasNext()) {
                font = Key.key((String)valueOrNamespace);
            } else {
                String fontKey = args.pop().value();
                font = Key.key((String)valueOrNamespace, (String)fontKey);
            }
        }
        catch (InvalidKeyException ex) {
            throw ctx.newException(ex.getMessage(), args);
        }
        return Tag.styling(builder -> builder.font(font));
    }

    static void emit(Key font, TokenEmitter emitter) {
        emitter.tag(FONT);
        if (font.namespace().equals("minecraft")) {
            emitter.argument(font.value());
        } else {
            emitter.arguments(new String[]{font.namespace(), font.value()});
        }
    }
}
