/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ARGBLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ARGBLike;

@ApiStatus.NonExtendable
public interface StyleSetter<T extends StyleSetter<?>> {
    @NotNull
    default public T decoration(@NotNull TextDecoration decoration, boolean flag) {
        return this.decoration(decoration, TextDecoration.State.byBoolean((boolean)flag));
    }

    @NotNull
    public T font(@Nullable Key var1);

    @NotNull
    public T shadowColorIfAbsent(@Nullable ARGBLike var1);

    @NotNull
    default public T decorate(@NotNull TextDecoration decoration) {
        return this.decoration(decoration, TextDecoration.State.TRUE);
    }

    @NotNull
    public T colorIfAbsent(@Nullable TextColor var1);

    @NotNull
    public T clickEvent(@Nullable ClickEvent var1);

    @NotNull
    default public T decorate(TextDecoration ... decorations) {
        EnumMap<TextDecoration, TextDecoration.State> map = new EnumMap<TextDecoration, TextDecoration.State>(TextDecoration.class);
        int i = 0;
        int length = decorations.length;
        while (i < length) {
            map.put(decorations[i], TextDecoration.State.TRUE);
            ++i;
        }
        return this.decorations(map);
    }

    @NotNull
    public T shadowColor(@Nullable ARGBLike var1);

    @NotNull
    public T decoration(@NotNull TextDecoration var1, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State var2);

    @NotNull
    public T decorationIfAbsent(@NotNull TextDecoration var1, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State var2);

    @NotNull
    public T decorations(@NotNull Map<TextDecoration, TextDecoration.State> var1);

    @NotNull
    default public T decorations(@NotNull Set<TextDecoration> decorations, boolean flag) {
        return this.decorations(decorations.stream().collect(Collectors.toMap(Function.identity(), decoration -> TextDecoration.State.byBoolean((boolean)flag))));
    }

    @NotNull
    public T color(@Nullable TextColor var1);

    @NotNull
    public T insertion(@Nullable String var1);

    @NotNull
    public T hoverEvent(@Nullable HoverEventSource<?> var1);
}
