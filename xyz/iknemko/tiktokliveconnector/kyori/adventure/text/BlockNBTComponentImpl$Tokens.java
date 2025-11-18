/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$WorldPos$Coordinate
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$WorldPos$Coordinate$Type
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.regex.Pattern;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;

static final class BlockNBTComponentImpl.Tokens {
    static final Pattern WORLD_PATTERN;
    static final String LOCAL_SYMBOL = "^";
    static final Pattern LOCAL_PATTERN;
    static final String RELATIVE_SYMBOL = "~";
    static final String ABSOLUTE_SYMBOL = "";

    static String serializeLocal(double value) {
        return LOCAL_SYMBOL + value;
    }

    static {
        LOCAL_PATTERN = Pattern.compile("^\\^(-?\\d+(\\.\\d+)?) \\^(-?\\d+(\\.\\d+)?) \\^(-?\\d+(\\.\\d+)?)$");
        WORLD_PATTERN = Pattern.compile("^(~?)(-?\\d+) (~?)(-?\\d+) (~?)(-?\\d+)$");
    }

    static String serializeCoordinate(BlockNBTComponent.WorldPos.Coordinate coordinate) {
        return (coordinate.type() == BlockNBTComponent.WorldPos.Coordinate.Type.RELATIVE ? RELATIVE_SYMBOL : ABSOLUTE_SYMBOL) + coordinate.value();
    }

    static BlockNBTComponent.WorldPos.Coordinate deserializeCoordinate(String prefix, String value) {
        int i = Integer.parseInt(value);
        if (prefix.equals(ABSOLUTE_SYMBOL)) {
            return BlockNBTComponent.WorldPos.Coordinate.absolute((int)i);
        }
        if (!prefix.equals(RELATIVE_SYMBOL)) throw new AssertionError();
        return BlockNBTComponent.WorldPos.Coordinate.relative((int)i);
    }

    private BlockNBTComponentImpl.Tokens() {
    }
}
