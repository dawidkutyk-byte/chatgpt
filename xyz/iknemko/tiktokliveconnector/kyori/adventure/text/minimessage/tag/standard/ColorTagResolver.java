/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.NamedTextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver$Single
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.NamedTextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

final class ColorTagResolver
implements SerializableResolver.Single,
TagResolver {
    private static final StyleClaim<TextColor> STYLE;
    static final TagResolver INSTANCE;
    private static final String COLOR_2 = "colour";
    private static final String COLOR = "color";
    private static final Map<String, TextColor> COLOR_ALIASES;
    private static final String COLOR_3 = "c";

    @Nullable
    public StyleClaim<?> claimStyle() {
        return STYLE;
    }

    private static boolean isColorOrAbbreviation(String name) {
        return name.equals(COLOR) || name.equals(COLOR_2) || name.equals(COLOR_3);
    }

    public boolean has(@NotNull String name) {
        return ColorTagResolver.isColorOrAbbreviation(name) || NamedTextColor.NAMES.value((Object)name) != null || COLOR_ALIASES.containsKey(name) || TextColor.fromHexString((String)name) != null;
    }

    @NotNull
    static TextColor resolveColor(@NotNull String colorName, @NotNull Context ctx) throws ParsingException {
        TextColor color = ColorTagResolver.resolveColorOrNull(colorName);
        if (color != null) return color;
        throw ctx.newException(String.format("Unable to parse a color from '%s'. Please use named colours or hex (#RRGGBB) colors.", colorName));
    }

    static {
        INSTANCE = new ColorTagResolver();
        STYLE = StyleClaim.claim((String)COLOR, Style::color, (color, emitter) -> {
            if (color instanceof NamedTextColor) {
                emitter.tag((String)NamedTextColor.NAMES.key((Object)((NamedTextColor)color)));
            } else {
                emitter.tag(color.asHexString());
            }
        });
        COLOR_ALIASES = new HashMap<String, TextColor>();
        COLOR_ALIASES.put("dark_grey", (TextColor)NamedTextColor.DARK_GRAY);
        COLOR_ALIASES.put("grey", (TextColor)NamedTextColor.GRAY);
    }

    ColorTagResolver() {
    }

    @Nullable
    static TextColor resolveColorOrNull(String colorName) {
        TextColor color = COLOR_ALIASES.containsKey(colorName) ? COLOR_ALIASES.get(colorName) : (colorName.charAt(0) == '#' ? TextColor.fromHexString((String)colorName) : (TextColor)NamedTextColor.NAMES.value((Object)colorName));
        return color;
    }

    @Nullable
    public Tag resolve(@NotNull String name, @NotNull ArgumentQueue args, @NotNull Context ctx) throws ParsingException {
        if (!this.has(name)) {
            return null;
        }
        String colorName = ColorTagResolver.isColorOrAbbreviation(name) ? args.popOr("Expected to find a color parameter: <name>|#RRGGBB").lowerValue() : name;
        TextColor color = ColorTagResolver.resolveColor(colorName, ctx);
        return Tag.styling((StyleBuilderApplicable[])new StyleBuilderApplicable[]{color});
    }
}
