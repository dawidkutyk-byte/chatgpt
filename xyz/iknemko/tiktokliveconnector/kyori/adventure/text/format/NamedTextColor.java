/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.HSVLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.HSVLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index;

public final class NamedTextColor
implements TextColor {
    private static final int DARK_BLUE_VALUE = 170;
    public static final NamedTextColor RED;
    public static final NamedTextColor BLACK;
    public static final NamedTextColor DARK_BLUE;
    private static final int RED_VALUE = 0xFF5555;
    private static final int DARK_GREEN_VALUE = 43520;
    private static final List<NamedTextColor> VALUES;
    public static final NamedTextColor DARK_PURPLE;
    public static final NamedTextColor BLUE;
    private static final int BLACK_VALUE = 0;
    private static final int DARK_RED_VALUE = 0xAA0000;
    public static final NamedTextColor DARK_GRAY;
    private static final int GREEN_VALUE = 0x55FF55;
    public static final NamedTextColor GOLD;
    public static final NamedTextColor WHITE;
    public static final NamedTextColor YELLOW;
    public static final Index<String, NamedTextColor> NAMES;
    private static final int DARK_GRAY_VALUE = 0x555555;
    public static final NamedTextColor AQUA;
    public static final NamedTextColor DARK_RED;
    private static final int WHITE_VALUE = 0xFFFFFF;
    private final HSVLike hsv;
    public static final NamedTextColor GRAY;
    private final int value;
    public static final NamedTextColor GREEN;
    private static final int GRAY_VALUE = 0xAAAAAA;
    private static final int LIGHT_PURPLE_VALUE = 0xFF55FF;
    private static final int DARK_AQUA_VALUE = 43690;
    private static final int DARK_PURPLE_VALUE = 0xAA00AA;
    public static final NamedTextColor DARK_AQUA;
    private final String name;
    public static final NamedTextColor DARK_GREEN;
    private static final int GOLD_VALUE = 0xFFAA00;
    private static final int YELLOW_VALUE = 0xFFFF55;
    private static final int BLUE_VALUE = 0x5555FF;
    private static final int AQUA_VALUE = 0x55FFFF;
    public static final NamedTextColor LIGHT_PURPLE;

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.concat(Stream.of(ExaminableProperty.of((String)"name", (String)this.name)), super.examinableProperties());
    }

    public int value() {
        return this.value;
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @Nullable
    public static NamedTextColor ofExact(int value) {
        switch (value) {
            case 0: {
                return BLACK;
            }
            case 170: {
                return DARK_BLUE;
            }
            case 43520: {
                return DARK_GREEN;
            }
            case 43690: {
                return DARK_AQUA;
            }
            case 0xAA0000: {
                return DARK_RED;
            }
            case 0xAA00AA: {
                return DARK_PURPLE;
            }
            case 0xFFAA00: {
                return GOLD;
            }
            case 0xAAAAAA: {
                return GRAY;
            }
            case 0x555555: {
                return DARK_GRAY;
            }
            case 0x5555FF: {
                return BLUE;
            }
            case 0x55FF55: {
                return GREEN;
            }
            case 0x55FFFF: {
                return AQUA;
            }
            case 0xFF5555: {
                return RED;
            }
            case 0xFF55FF: {
                return LIGHT_PURPLE;
            }
            case 0xFFFF55: {
                return YELLOW;
            }
            case 0xFFFFFF: {
                return WHITE;
            }
        }
        return null;
    }

    static {
        BLACK = new NamedTextColor("black", 0);
        DARK_BLUE = new NamedTextColor("dark_blue", 170);
        DARK_GREEN = new NamedTextColor("dark_green", 43520);
        DARK_AQUA = new NamedTextColor("dark_aqua", 43690);
        DARK_RED = new NamedTextColor("dark_red", 0xAA0000);
        DARK_PURPLE = new NamedTextColor("dark_purple", 0xAA00AA);
        GOLD = new NamedTextColor("gold", 0xFFAA00);
        GRAY = new NamedTextColor("gray", 0xAAAAAA);
        DARK_GRAY = new NamedTextColor("dark_gray", 0x555555);
        BLUE = new NamedTextColor("blue", 0x5555FF);
        GREEN = new NamedTextColor("green", 0x55FF55);
        AQUA = new NamedTextColor("aqua", 0x55FFFF);
        RED = new NamedTextColor("red", 0xFF5555);
        LIGHT_PURPLE = new NamedTextColor("light_purple", 0xFF55FF);
        YELLOW = new NamedTextColor("yellow", 0xFFFF55);
        WHITE = new NamedTextColor("white", 0xFFFFFF);
        VALUES = Collections.unmodifiableList(Arrays.asList(BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE));
        NAMES = Index.create(constant -> constant.name, VALUES);
    }

    @NotNull
    public String toString() {
        return this.name;
    }

    @Nullable
    public static NamedTextColor namedColor(int value) {
        switch (value) {
            case 0: {
                return BLACK;
            }
            case 170: {
                return DARK_BLUE;
            }
            case 43520: {
                return DARK_GREEN;
            }
            case 43690: {
                return DARK_AQUA;
            }
            case 0xAA0000: {
                return DARK_RED;
            }
            case 0xAA00AA: {
                return DARK_PURPLE;
            }
            case 0xFFAA00: {
                return GOLD;
            }
            case 0xAAAAAA: {
                return GRAY;
            }
            case 0x555555: {
                return DARK_GRAY;
            }
            case 0x5555FF: {
                return BLUE;
            }
            case 0x55FF55: {
                return GREEN;
            }
            case 0x55FFFF: {
                return AQUA;
            }
            case 0xFF5555: {
                return RED;
            }
            case 0xFF55FF: {
                return LIGHT_PURPLE;
            }
            case 0xFFFF55: {
                return YELLOW;
            }
            case 0xFFFFFF: {
                return WHITE;
            }
        }
        return null;
    }

    @NotNull
    public static NamedTextColor nearestTo(@NotNull TextColor any) {
        if (!(any instanceof NamedTextColor)) return (NamedTextColor)TextColor.nearestColorTo(VALUES, (TextColor)any);
        return (NamedTextColor)any;
    }

    private NamedTextColor(String name, int value) {
        this.name = name;
        this.value = value;
        this.hsv = HSVLike.fromRGB((int)this.red(), (int)this.green(), (int)this.blue());
    }

    @NotNull
    public HSVLike asHSV() {
        return this.hsv;
    }
}
