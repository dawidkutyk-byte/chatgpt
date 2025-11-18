/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.MonkeyBars
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.Set;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.MonkeyBars;

public static enum Style.Merge {
    COLOR,
    SHADOW_COLOR,
    DECORATIONS,
    EVENTS,
    INSERTION,
    FONT;

    static final Set<Style.Merge> COLOR_AND_DECORATIONS;
    static final Set<Style.Merge> ALL;

    public static @Unmodifiable @NotNull Set<Style.Merge> merges(Style.Merge ... merges) {
        return MonkeyBars.enumSet(Style.Merge.class, (Enum[])merges);
    }

    static boolean hasAll(@NotNull Set<Style.Merge> merges) {
        return merges.size() == ALL.size();
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static @Unmodifiable @NotNull Set<Style.Merge> of(Style.Merge ... merges) {
        return MonkeyBars.enumSet(Style.Merge.class, (Enum[])merges);
    }

    static {
        ALL = Style.Merge.merges(Style.Merge.values());
        COLOR_AND_DECORATIONS = Style.Merge.merges(COLOR, DECORATIONS);
    }

    @NotNull
    public static @Unmodifiable Set<Style.Merge> all() {
        return ALL;
    }

    public static @Unmodifiable @NotNull Set<Style.Merge> colorAndDecorations() {
        return COLOR_AND_DECORATIONS;
    }
}
