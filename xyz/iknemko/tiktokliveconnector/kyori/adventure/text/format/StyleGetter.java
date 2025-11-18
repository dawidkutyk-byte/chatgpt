/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.ShadowColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.EnumMap;
import java.util.Map;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.ShadowColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;

@ApiStatus.NonExtendable
public interface StyleGetter {
    default public @Unmodifiable @NotNull Map<TextDecoration, // Could not load outer class - annotation placement on inner may be incorrect
    TextDecoration.State> decorations() {
        EnumMap<TextDecoration, TextDecoration.State> decorations = new EnumMap<TextDecoration, TextDecoration.State>(TextDecoration.class);
        int i = 0;
        int length = DecorationMap.DECORATIONS.length;
        while (i < length) {
            TextDecoration decoration = DecorationMap.DECORATIONS[i];
            TextDecoration.State value = this.decoration(decoration);
            decorations.put(decoration, value);
            ++i;
        }
        return decorations;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State decoration(@NotNull TextDecoration var1);

    @Nullable
    public ShadowColor shadowColor();

    @Nullable
    public ClickEvent clickEvent();

    default public boolean hasDecoration(@NotNull TextDecoration decoration) {
        return this.decoration(decoration) == TextDecoration.State.TRUE;
    }

    @Nullable
    public HoverEvent<?> hoverEvent();

    @Nullable
    public Key font();

    @Nullable
    public String insertion();

    @Nullable
    public TextColor color();
}
