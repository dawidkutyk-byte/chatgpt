/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.FlattenerListener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.NamedTextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextFormat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl$Cereal
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl$DecodedFormat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl$FormatCodeType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl$Reset
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyFormat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.FlattenerListener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.NamedTextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextFormat;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyFormat;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services;

final class LegacyComponentSerializerImpl
implements LegacyComponentSerializer {
    private static final String LEGACY_CHARS;
    private final boolean hexColours;
    private static final char LEGACY_BUNGEE_HEX_CHAR = 'x';
    private final boolean useTerriblyStupidHexFormat;
    private final ComponentFlattener flattener;
    static final Consumer<LegacyComponentSerializer.Builder> BUILDER;
    private static final TextDecoration[] DECORATIONS;
    private static final List<TextFormat> FORMATS;
    @Nullable
    private final TextReplacementConfig urlReplacementConfig;
    static final Pattern URL_SCHEME_PATTERN;
    static final Pattern DEFAULT_URL_PATTERN;
    private static final Optional<LegacyComponentSerializer.Provider> SERVICE;
    private final char character;
    private final char hexCharacter;

    LegacyComponentSerializerImpl(char character, char hexCharacter, @Nullable TextReplacementConfig urlReplacementConfig, boolean hexColours, boolean useTerriblyStupidHexFormat, ComponentFlattener flattener) {
        this.character = character;
        this.hexCharacter = hexCharacter;
        this.urlReplacementConfig = urlReplacementConfig;
        this.hexColours = hexColours;
        this.useTerriblyStupidHexFormat = useTerriblyStupidHexFormat;
        this.flattener = flattener;
    }

    static {
        DEFAULT_URL_PATTERN = Pattern.compile("(?:(https?)://)?([-\\w_.]+\\.\\w{2,})(/\\S*)?");
        URL_SCHEME_PATTERN = Pattern.compile("^[a-z][a-z0-9+\\-.]*:");
        DECORATIONS = TextDecoration.values();
        LinkedHashMap<Object, String> formats = new LinkedHashMap<Object, String>(22);
        formats.put(NamedTextColor.BLACK, "0");
        formats.put(NamedTextColor.DARK_BLUE, "1");
        formats.put(NamedTextColor.DARK_GREEN, "2");
        formats.put(NamedTextColor.DARK_AQUA, "3");
        formats.put(NamedTextColor.DARK_RED, "4");
        formats.put(NamedTextColor.DARK_PURPLE, "5");
        formats.put(NamedTextColor.GOLD, "6");
        formats.put(NamedTextColor.GRAY, "7");
        formats.put(NamedTextColor.DARK_GRAY, "8");
        formats.put(NamedTextColor.BLUE, "9");
        formats.put(NamedTextColor.GREEN, "a");
        formats.put(NamedTextColor.AQUA, "b");
        formats.put(NamedTextColor.RED, "c");
        formats.put(NamedTextColor.LIGHT_PURPLE, "d");
        formats.put(NamedTextColor.YELLOW, "e");
        formats.put(NamedTextColor.WHITE, "f");
        formats.put(TextDecoration.OBFUSCATED, "k");
        formats.put(TextDecoration.BOLD, "l");
        formats.put(TextDecoration.STRIKETHROUGH, "m");
        formats.put(TextDecoration.UNDERLINED, "n");
        formats.put(TextDecoration.ITALIC, "o");
        formats.put(Reset.INSTANCE, "r");
        FORMATS = Collections.unmodifiableList(new ArrayList(formats.keySet()));
        LEGACY_CHARS = String.join((CharSequence)"", formats.values());
        if (FORMATS.size() != LEGACY_CHARS.length()) {
            throw new IllegalStateException("FORMATS length differs from LEGACY_CHARS length");
        }
        SERVICE = Services.service(LegacyComponentSerializer.Provider.class);
        BUILDER = SERVICE.map(LegacyComponentSerializer.Provider::legacy).orElseGet(() -> builder -> {});
    }

    static /* synthetic */ boolean access$1100(LegacyComponentSerializerImpl x0) {
        return x0.useTerriblyStupidHexFormat;
    }

    private TextComponent extractUrl(TextComponent component) {
        if (this.urlReplacementConfig == null) {
            return component;
        }
        Component newComponent = component.replaceText(this.urlReplacementConfig);
        if (!(newComponent instanceof TextComponent)) return (TextComponent)((TextComponent.Builder)Component.text().append(newComponent)).build();
        return (TextComponent)newComponent;
    }

    @Nullable
    private FormatCodeType determineFormatType(char legacy, String input, int pos) {
        if (pos >= 14) {
            int expectedCharacterPosition = pos - 14;
            int expectedIndicatorPosition = pos - 13;
            if (input.charAt(expectedCharacterPosition) == this.character && input.charAt(expectedIndicatorPosition) == 'x') {
                return FormatCodeType.BUNGEECORD_UNUSUAL_HEX;
            }
        }
        if (legacy == this.hexCharacter && input.length() - pos >= 6) {
            return FormatCodeType.KYORI_HEX;
        }
        if (LEGACY_CHARS.indexOf(legacy) == -1) return null;
        return FormatCodeType.MOJANG_LEGACY;
    }

    @NotNull
    public String serialize(@NotNull Component component) {
        Cereal state = new Cereal(this, null);
        this.flattener.flatten(component, (FlattenerListener)state);
        return state.toString();
    }

    static /* synthetic */ boolean access$1000(LegacyComponentSerializerImpl x0) {
        return x0.hexColours;
    }

    @Nullable
    private static TextColor tryParseHexColor(String hexDigits) {
        try {
            int color = Integer.parseInt(hexDigits, 16);
            return TextColor.color((int)color);
        }
        catch (NumberFormatException ex) {
            return null;
        }
    }

    static /* synthetic */ String access$300(LegacyComponentSerializerImpl x0, TextFormat x1) {
        return x0.toLegacyCode(x1);
    }

    static /* synthetic */ char access$800(LegacyComponentSerializerImpl x0) {
        return x0.hexCharacter;
    }

    static /* synthetic */ char access$400(LegacyComponentSerializerImpl x0) {
        return x0.character;
    }

    @Nullable
    static LegacyFormat legacyFormat(char character) {
        int index = LEGACY_CHARS.indexOf(character);
        if (index == -1) return null;
        TextFormat format = FORMATS.get(index);
        if (format instanceof NamedTextColor) {
            return new LegacyFormat((NamedTextColor)format);
        }
        if (format instanceof TextDecoration) {
            return new LegacyFormat((TextDecoration)format);
        }
        if (!(format instanceof Reset)) return null;
        return LegacyFormat.RESET;
    }

    private String toLegacyCode(TextFormat format) {
        if (LegacyComponentSerializerImpl.isHexTextColor(format)) {
            TextColor color = (TextColor)format;
            if (this.hexColours) {
                String hex = String.format("%06x", color.value());
                if (!this.useTerriblyStupidHexFormat) return this.hexCharacter + hex;
                StringBuilder legacy = new StringBuilder(String.valueOf('x'));
                int i = 0;
                int length = hex.length();
                while (i < length) {
                    legacy.append(this.character).append(hex.charAt(i));
                    ++i;
                }
                return legacy.toString();
            }
            format = NamedTextColor.nearestTo((TextColor)color);
        }
        int index = FORMATS.indexOf(format);
        return Character.toString(LEGACY_CHARS.charAt(index));
    }

    static /* synthetic */ TextDecoration[] access$500() {
        return DECORATIONS;
    }

    @NotNull
    public TextComponent deserialize(@NotNull String input) {
        String remaining;
        int next = input.lastIndexOf(this.character, input.length() - 2);
        if (next == -1) {
            return this.extractUrl(Component.text((String)input));
        }
        ArrayList<TextComponent> parts = new ArrayList<TextComponent>();
        TextComponent.Builder current = null;
        boolean reset = false;
        int pos = input.length();
        do {
            DecodedFormat decoded;
            if ((decoded = this.decodeTextFormat(input.charAt(next + 1), input, next + 2)) == null) continue;
            int from = next + (decoded.encodedFormat == FormatCodeType.KYORI_HEX ? 8 : 2);
            if (from != pos) {
                if (current != null) {
                    if (reset) {
                        parts.add((TextComponent)current.build());
                        reset = false;
                        current = Component.text();
                    } else {
                        current = (TextComponent.Builder)Component.text().append((Component)current.build());
                    }
                } else {
                    current = Component.text();
                }
                current.content(input.substring(from, pos));
            } else if (current == null) {
                current = Component.text();
            }
            if (!reset) {
                reset = LegacyComponentSerializerImpl.applyFormat(current, decoded.format);
            }
            if (decoded.encodedFormat == FormatCodeType.BUNGEECORD_UNUSUAL_HEX) {
                next -= 12;
            }
            pos = next;
        } while ((next = input.lastIndexOf(this.character, next - 1)) != -1);
        if (current != null) {
            parts.add((TextComponent)current.build());
        }
        String string = remaining = pos > 0 ? input.substring(0, pos) : "";
        if (parts.size() == 1 && remaining.isEmpty()) {
            return this.extractUrl((TextComponent)parts.get(0));
        }
        Collections.reverse(parts);
        return this.extractUrl((TextComponent)((TextComponent.Builder)Component.text().content(remaining).append(parts)).build());
    }

    static /* synthetic */ Optional access$000() {
        return SERVICE;
    }

    static /* synthetic */ ComponentFlattener access$1200(LegacyComponentSerializerImpl x0) {
        return x0.flattener;
    }

    private static boolean isHexTextColor(TextFormat format) {
        return format instanceof TextColor && !(format instanceof NamedTextColor);
    }

    @NotNull
    public LegacyComponentSerializer.Builder toBuilder() {
        return new BuilderImpl(this);
    }

    private static boolean applyFormat(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextComponent.Builder builder, @NotNull TextFormat format) {
        if (format instanceof TextColor) {
            builder.colorIfAbsent((TextColor)format);
            return true;
        }
        if (format instanceof TextDecoration) {
            builder.decoration((TextDecoration)format, TextDecoration.State.TRUE);
            return false;
        }
        if (!(format instanceof Reset)) throw new IllegalArgumentException(String.format("unknown format '%s'", format.getClass()));
        return true;
    }

    static /* synthetic */ TextReplacementConfig access$900(LegacyComponentSerializerImpl x0) {
        return x0.urlReplacementConfig;
    }

    @Nullable
    private DecodedFormat decodeTextFormat(char legacy, String input, int pos) {
        FormatCodeType foundFormat = this.determineFormatType(legacy, input, pos);
        if (foundFormat == null) {
            return null;
        }
        if (foundFormat == FormatCodeType.KYORI_HEX) {
            @Nullable TextColor parsed = LegacyComponentSerializerImpl.tryParseHexColor(input.substring(pos, pos + 6));
            if (parsed == null) return null;
            return new DecodedFormat(foundFormat, (TextFormat)parsed, null);
        }
        if (foundFormat == FormatCodeType.MOJANG_LEGACY) {
            return new DecodedFormat(foundFormat, FORMATS.get(LEGACY_CHARS.indexOf(legacy)), null);
        }
        if (foundFormat != FormatCodeType.BUNGEECORD_UNUSUAL_HEX) return null;
        StringBuilder foundHex = new StringBuilder(6);
        int i = pos - 1;
        while (true) {
            if (i < pos - 11) {
                @Nullable TextColor parsed = LegacyComponentSerializerImpl.tryParseHexColor(foundHex.reverse().toString());
                if (parsed == null) return null;
                return new DecodedFormat(foundFormat, (TextFormat)parsed, null);
            }
            foundHex.append(input.charAt(i));
            i -= 2;
        }
    }
}
