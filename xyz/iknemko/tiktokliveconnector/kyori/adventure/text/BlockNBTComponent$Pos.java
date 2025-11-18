/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$LocalPos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$WorldPos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$WorldPos$Coordinate
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl$Tokens
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.regex.Matcher;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl;

/*
 * Exception performing whole class analysis ignored.
 */
public static interface BlockNBTComponent.Pos
extends Examinable {
    @NotNull
    public String asString();

    @NotNull
    public static BlockNBTComponent.Pos fromString(@NotNull String input) throws IllegalArgumentException {
        Matcher localMatch = BlockNBTComponentImpl.Tokens.LOCAL_PATTERN.matcher(input);
        if (localMatch.matches()) {
            return BlockNBTComponent.LocalPos.localPos((double)Double.parseDouble(localMatch.group(1)), (double)Double.parseDouble(localMatch.group(3)), (double)Double.parseDouble(localMatch.group(5)));
        }
        Matcher worldMatch = BlockNBTComponentImpl.Tokens.WORLD_PATTERN.matcher(input);
        if (!worldMatch.matches()) throw new IllegalArgumentException("Cannot convert position specification '" + input + "' into a position");
        return BlockNBTComponent.WorldPos.worldPos((BlockNBTComponent.WorldPos.Coordinate)BlockNBTComponentImpl.Tokens.deserializeCoordinate((String)worldMatch.group(1), (String)worldMatch.group(2)), (BlockNBTComponent.WorldPos.Coordinate)BlockNBTComponentImpl.Tokens.deserializeCoordinate((String)worldMatch.group(3), (String)worldMatch.group(4)), (BlockNBTComponent.WorldPos.Coordinate)BlockNBTComponentImpl.Tokens.deserializeCoordinate((String)worldMatch.group(5), (String)worldMatch.group(6)));
    }
}
