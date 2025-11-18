/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.PatternReplacementResult
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig$Condition
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.IntFunction2
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.PatternReplacementResult;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.IntFunction2;

public static interface TextReplacementConfig.Builder
extends AbstractBuilder<TextReplacementConfig>,
Buildable.Builder<TextReplacementConfig> {
    @Contract(value="_ -> this")
    @NotNull
    public TextReplacementConfig.Builder replacement(@NotNull BiFunction<MatchResult, // Could not load outer class - annotation placement on inner may be incorrect
    TextComponent.Builder, @Nullable ComponentLike> var1);

    @NotNull
    @Contract(value="_ -> this")
    default public TextReplacementConfig.Builder times(int times) {
        return this.condition((IntFunction2<PatternReplacementResult>)((IntFunction2)(index, replaced) -> replaced < times ? PatternReplacementResult.REPLACE : PatternReplacementResult.STOP));
    }

    @Contract(value="_ -> this")
    @NotNull
    public TextReplacementConfig.Builder replaceInsideHoverEvents(boolean var1);

    @NotNull
    default public TextReplacementConfig.Builder once() {
        return this.times(1);
    }

    @NotNull
    @Contract(value="_ -> this")
    public TextReplacementConfig.Builder condition(@NotNull TextReplacementConfig.Condition var1);

    @Contract(value="_ -> this")
    @NotNull
    default public TextReplacementConfig.Builder condition(@NotNull IntFunction2<PatternReplacementResult> condition) {
        return this.condition((result, matchCount, replaced) -> (PatternReplacementResult)condition.apply(matchCount, replaced));
    }

    @NotNull
    @Contract(value="_ -> this")
    public TextReplacementConfig.Builder match(@NotNull Pattern var1);

    @NotNull
    @Contract(value="_ -> this")
    default public TextReplacementConfig.Builder match(@NotNull @RegExp String pattern) {
        return this.match(Pattern.compile(pattern));
    }

    @Contract(value="_ -> this")
    default public TextReplacementConfig.Builder matchLiteral(String literal) {
        return this.match(Pattern.compile(literal, 16));
    }

    @NotNull
    @Contract(value="_ -> this")
    default public TextReplacementConfig.Builder replacement(@NotNull String replacement) {
        Objects.requireNonNull(replacement, "replacement");
        return this.replacement((TextComponent.Builder builder) -> builder.content(replacement));
    }

    @Contract(value="_ -> this")
    @NotNull
    default public TextReplacementConfig.Builder replacement(@Nullable ComponentLike replacement) {
        @Nullable Component baked = ComponentLike.unbox((ComponentLike)replacement);
        return this.replacement((MatchResult result, TextComponent.Builder input) -> baked);
    }

    @NotNull
    @Contract(value="_ -> this")
    default public TextReplacementConfig.Builder replacement(@NotNull Function<// Could not load outer class - annotation placement on inner may be incorrect
    TextComponent.Builder, @Nullable ComponentLike> replacement) {
        Objects.requireNonNull(replacement, "replacement");
        return this.replacement((MatchResult result, TextComponent.Builder input) -> (ComponentLike)replacement.apply((TextComponent.Builder)input));
    }
}
