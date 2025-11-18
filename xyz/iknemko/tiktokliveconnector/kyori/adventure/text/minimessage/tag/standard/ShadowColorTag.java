/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.NamedTextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.ShadowColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleGetter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ColorTagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.RGBLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.NamedTextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.ShadowColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleGetter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ColorTagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.RGBLike;

final class ShadowColorTag {
    private static final String SHADOW_NONE = "!shadow";
    private static final float DEFAULT_ALPHA = 0.25f;
    private static final String SHADOW_COLOR = "shadow";
    static final TagResolver RESOLVER = TagResolver.resolver((TagResolver[])new TagResolver[]{SerializableResolver.claimingStyle((String)"shadow", ShadowColorTag::create, (StyleClaim)StyleClaim.claim((String)"shadow", StyleGetter::shadowColor, ShadowColorTag::emit)), TagResolver.resolver((String)"!shadow", (Tag)Tag.styling((StyleBuilderApplicable[])new StyleBuilderApplicable[]{ShadowColor.none()}))});

    static void emit(@NotNull ShadowColor color, @NotNull TokenEmitter emitter) {
        if (ShadowColor.none().equals(color)) {
            emitter.tag(SHADOW_NONE);
            return;
        }
        emitter.tag(SHADOW_COLOR);
        @Nullable NamedTextColor possibleMatch = NamedTextColor.namedColor((int)TextColor.color((RGBLike)color).value());
        if (possibleMatch != null) {
            emitter.argument((String)NamedTextColor.NAMES.key((Object)possibleMatch)).argument(Float.toString((float)color.alpha() / 255.0f));
        } else {
            emitter.argument(color.asHexString());
        }
    }

    private ShadowColorTag() {
    }

    static Tag create(@NotNull ArgumentQueue args, @NotNull Context ctx) throws ParsingException {
        ShadowColor color;
        String colorString = args.popOr("Expected to find a color parameter: #RRGGBBAA").lowerValue();
        if (colorString.startsWith("#") && colorString.length() == 9) {
            color = ShadowColor.fromHexString((String)colorString);
            if (color != null) return Tag.styling((StyleBuilderApplicable[])new StyleBuilderApplicable[]{color});
            throw ctx.newException(String.format("Unable to parse a shadow color from '%s'. Please use #RRGGBBAA formatting.", colorString));
        }
        TextColor text = ColorTagResolver.resolveColor((String)colorString, (Context)ctx);
        float alpha = args.hasNext() ? (float)args.pop().asDouble().orElseThrow(() -> ctx.newException("Number was expected to be a double")) : 0.25f;
        color = ShadowColor.shadowColor((RGBLike)text, (int)((int)(alpha * 255.0f)));
        return Tag.styling((StyleBuilderApplicable[])new StyleBuilderApplicable[]{color});
    }
}
