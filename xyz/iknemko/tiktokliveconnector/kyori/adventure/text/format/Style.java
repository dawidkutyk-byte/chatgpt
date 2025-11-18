/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Merge
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Merge$Strategy
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleGetter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleSetter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleGetter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleSetter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

/*
 * Exception performing whole class analysis ignored.
 */
@ApiStatus.NonExtendable
public interface Style
extends Buildable<Style, Builder>,
Examinable,
StyleGetter,
StyleSetter<Style> {
    public static final Key DEFAULT_FONT = Key.key((String)"default");

    @NotNull
    default public Style merge(@NotNull Style that, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Style.Merge.Strategy strategy) {
        return this.merge(that, strategy, Merge.all());
    }

    @NotNull
    public Style insertion(@Nullable String var1);

    @NotNull
    public static Style style(@NotNull Consumer<Builder> consumer) {
        return (Style)AbstractBuilder.configureAndBuild((AbstractBuilder)Style.style(), consumer);
    }

    @NotNull
    public static Builder style() {
        return new StyleImpl.BuilderImpl();
    }

    @NotNull
    default public Style edit(@NotNull Consumer<Builder> consumer) {
        return this.edit(consumer, Merge.Strategy.ALWAYS);
    }

    @Nullable
    public TextColor color();

    @NotNull
    public static Style style(@Nullable TextColor color, TextDecoration ... decorations) {
        Builder builder = Style.style();
        builder.color(color);
        builder.decorate(decorations);
        return builder.build();
    }

    @NotNull
    public Style decoration(@NotNull TextDecoration var1, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State var2);

    @NotNull
    public static Style style(@NotNull Iterable<? extends StyleBuilderApplicable> applicables) {
        Builder builder = Style.style();
        Iterator<? extends StyleBuilderApplicable> iterator = applicables.iterator();
        while (iterator.hasNext()) {
            StyleBuilderApplicable applicable = iterator.next();
            applicable.styleApply(builder);
        }
        return builder.build();
    }

    @Nullable
    public Key font();

    @NotNull
    default public Style merge(@NotNull Style that, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Style.Merge.Strategy strategy, Merge ... merges) {
        return this.merge(that, strategy, Merge.merges((Merge[])merges));
    }

    @NotNull
    public static Style empty() {
        return StyleImpl.EMPTY;
    }

    @NotNull
    public static Style style(@Nullable TextColor color) {
        return Style.empty().color(color);
    }

    @Nullable
    public String insertion();

    @NotNull
    public static Style style(StyleBuilderApplicable ... applicables) {
        int length = applicables.length;
        if (length == 0) {
            return Style.empty();
        }
        Builder builder = Style.style();
        int i = 0;
        while (i < length) {
            StyleBuilderApplicable applicable = applicables[i];
            if (applicable != null) {
                applicable.styleApply(builder);
            }
            ++i;
        }
        return builder.build();
    }

    @NotNull
    public Style clickEvent(@Nullable ClickEvent var1);

    @NotNull
    default public Style merge(@NotNull Style that, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Style.Merge.Strategy strategy, @NotNull Merge merge) {
        return this.merge(that, strategy, Collections.singleton(merge));
    }

    default public @Unmodifiable @NotNull Map<TextDecoration, // Could not load outer class - annotation placement on inner may be incorrect
    TextDecoration.State> decorations() {
        return super.decorations();
    }

    @NotNull
    public Builder toBuilder();

    @NotNull
    default public Style decoration(@NotNull TextDecoration decoration, boolean flag) {
        return (Style)super.decoration(decoration, flag);
    }

    @NotNull
    public Style font(@Nullable Key var1);

    @NotNull
    public static Style style(@Nullable TextColor color, Set<TextDecoration> decorations) {
        Builder builder = Style.style();
        builder.color(color);
        if (decorations.isEmpty()) return builder.build();
        Iterator<TextDecoration> iterator = decorations.iterator();
        while (iterator.hasNext()) {
            TextDecoration decoration = iterator.next();
            builder.decoration(decoration, true);
        }
        return builder.build();
    }

    @NotNull
    public Style colorIfAbsent(@Nullable TextColor var1);

    @NotNull
    default public Style edit(@NotNull Consumer<Builder> consumer, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Style.Merge.Strategy strategy) {
        return Style.style((Builder style) -> {
            if (strategy == Merge.Strategy.ALWAYS) {
                style.merge(this, strategy);
            }
            consumer.accept((Builder)style);
            if (strategy != Merge.Strategy.IF_ABSENT_ON_TARGET) return;
            style.merge(this, strategy);
        });
    }

    public boolean isEmpty();

    @NotNull
    default public Style merge(@NotNull Style that) {
        return this.merge(that, Merge.all());
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State decoration(@NotNull TextDecoration var1);

    @NotNull
    public Style decorationIfAbsent(@NotNull TextDecoration var1, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State var2);

    @NotNull
    default public Style merge(@NotNull Style that, @NotNull Set<Merge> merges) {
        return this.merge(that, Merge.Strategy.ALWAYS, merges);
    }

    default public boolean hasDecoration(@NotNull TextDecoration decoration) {
        return super.hasDecoration(decoration);
    }

    @NotNull
    public Style merge(@NotNull Style var1, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Style.Merge.Strategy var2, @NotNull Set<Merge> var3);

    @NotNull
    default public Style decorate(@NotNull TextDecoration decoration) {
        return (Style)super.decorate(decoration);
    }

    @Nullable
    public HoverEvent<?> hoverEvent();

    @NotNull
    public Style decorations(@NotNull Map<TextDecoration, TextDecoration.State> var1);

    @Nullable
    public ClickEvent clickEvent();

    @NotNull
    public Style unmerge(@NotNull Style var1);

    @NotNull
    public static Style style(@NotNull TextDecoration decoration) {
        return Style.style().decoration(decoration, true).build();
    }

    @NotNull
    default public Style merge(@NotNull Style that, Merge ... merges) {
        return this.merge(that, Merge.merges((Merge[])merges));
    }

    @NotNull
    public Style color(@Nullable TextColor var1);

    @NotNull
    public Style hoverEvent(@Nullable HoverEventSource<?> var1);

    @NotNull
    default public Style merge(@NotNull Style that, @NotNull Merge merge) {
        return this.merge(that, Collections.singleton(merge));
    }
}
