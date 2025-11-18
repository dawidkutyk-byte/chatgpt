/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  fr.mrmicky.fastboard.FastBoardBase
 *  fr.mrmicky.fastboard.FastBoardBase$TeamMode
 *  fr.mrmicky.fastboard.FastBoardBase$VersionType
 *  fr.mrmicky.fastboard.FastReflection
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Player
 */
package fr.mrmicky.fastboard;

import fr.mrmicky.fastboard.FastBoardBase;
import fr.mrmicky.fastboard.FastReflection;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Array;
import java.util.Objects;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FastBoard
extends FastBoardBase<String> {
    private static final MethodHandle MESSAGE_FROM_STRING;
    private static final Object EMPTY_MESSAGE;

    public FastBoard(Player player) {
        super(player);
    }

    protected void sendLineChange(int score) throws Throwable {
        String prefix;
        int maxLength = this.hasLinesMaxLength() ? 16 : 1024;
        String line = (String)this.getLineByScore(score);
        String suffix = "";
        if (line == null || line.isEmpty()) {
            prefix = COLOR_CODES[score] + ChatColor.RESET;
        } else if (line.length() <= maxLength) {
            prefix = line;
        } else {
            int index = line.charAt(maxLength - 1) == '\u00a7' ? maxLength - 1 : maxLength;
            prefix = line.substring(0, index);
            String suffixTmp = line.substring(index);
            ChatColor chatColor = null;
            if (suffixTmp.length() >= 2 && suffixTmp.charAt(0) == '\u00a7') {
                chatColor = ChatColor.getByChar((char)suffixTmp.charAt(1));
            }
            String color = ChatColor.getLastColors((String)prefix);
            boolean addColor = chatColor == null || chatColor.isFormat();
            suffix = (addColor ? (color.isEmpty() ? ChatColor.RESET.toString() : color) : "") + suffixTmp;
        }
        if (prefix.length() > maxLength || suffix.length() > maxLength) {
            prefix = prefix.substring(0, Math.min(maxLength, prefix.length()));
            suffix = suffix.substring(0, Math.min(maxLength, suffix.length()));
        }
        this.sendTeamPacket(score, FastBoardBase.TeamMode.UPDATE, prefix, suffix);
    }

    public void updateLines(String ... lines) {
        Objects.requireNonNull(lines, "lines");
        if (!FastBoardBase.VersionType.V1_13.isHigherOrEqual()) {
            int lineCount = 0;
            String[] stringArray = lines;
            int n = stringArray.length;
            for (int i = 0; i < n; ++lineCount, ++i) {
                String s = stringArray[i];
                if (s == null || s.length() <= 30) continue;
                throw new IllegalArgumentException("Line " + lineCount + " is longer than 30 chars");
            }
        }
        super.updateLines((Object[])lines);
    }

    protected String emptyLine() {
        return "";
    }

    protected Object toMinecraftComponent(String line) throws Throwable {
        if (line == null) return EMPTY_MESSAGE;
        if (!line.isEmpty()) return Array.get(MESSAGE_FROM_STRING.invoke(line), 0);
        return EMPTY_MESSAGE;
    }

    public void updateTitle(String title) {
        Objects.requireNonNull(title, "title");
        if (!FastBoardBase.VersionType.V1_13.isHigherOrEqual() && title.length() > 32) {
            throw new IllegalArgumentException("Title is longer than 32 chars");
        }
        super.updateTitle((Object)title);
    }

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            Class craftChatMessageClass = FastReflection.obcClass((String)"util.CraftChatMessage");
            MESSAGE_FROM_STRING = lookup.unreflect(craftChatMessageClass.getMethod("fromString", String.class));
            EMPTY_MESSAGE = Array.get(MESSAGE_FROM_STRING.invoke(""), 0);
        }
        catch (Throwable t) {
            throw new ExceptionInInitializerError(t);
        }
    }

    protected String serializeLine(String value) {
        return value;
    }

    protected boolean hasLinesMaxLength() {
        return !FastBoardBase.VersionType.V1_13.isHigherOrEqual();
    }
}
