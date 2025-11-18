/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.PatternReplacementResult
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig$Condition
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfigImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.PatternReplacementResult;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfigImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static final class TextReplacementConfigImpl.Builder
implements TextReplacementConfig.Builder {
    @Nullable
    Pattern matchPattern;
    boolean replaceInsideHoverEvents = true;
    @Nullable
    @Nullable BiFunction<MatchResult, // Could not load outer class - annotation placement on inner may be incorrect
    TextComponent.Builder, @Nullable ComponentLike> replacement;
    TextReplacementConfig.Condition continuer = (matchResult, index, replacement) -> PatternReplacementResult.REPLACE;

    @NotNull
    public TextReplacementConfigImpl.Builder condition(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextReplacementConfig.Condition condition) {
        this.continuer = Objects.requireNonNull(condition, "continuation");
        return this;
    }

    TextReplacementConfigImpl.Builder() {
    }

    TextReplacementConfigImpl.Builder(TextReplacementConfigImpl instance) {
        this.matchPattern = TextReplacementConfigImpl.access$000((TextReplacementConfigImpl)instance);
        this.replacement = TextReplacementConfigImpl.access$100((TextReplacementConfigImpl)instance);
        this.continuer = TextReplacementConfigImpl.access$200((TextReplacementConfigImpl)instance);
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextReplacementConfig.Builder replaceInsideHoverEvents(boolean replace) {
        this.replaceInsideHoverEvents = replace;
        return this;
    }

    @NotNull
    public TextReplacementConfig build() {
        if (this.matchPattern == null) {
            throw new IllegalStateException("A pattern must be provided to match against");
        }
        if (this.replacement != null) return new TextReplacementConfigImpl(this);
        throw new IllegalStateException("A replacement action must be provided");
    }

    @NotNull
    public TextReplacementConfigImpl.Builder match(@NotNull Pattern pattern) {
        this.matchPattern = Objects.requireNonNull(pattern, "pattern");
        return this;
    }

    @NotNull
    public TextReplacementConfigImpl.Builder replacement(@NotNull BiFunction<MatchResult, // Could not load outer class - annotation placement on inner may be incorrect
    TextComponent.Builder, @Nullable ComponentLike> replacement) {
        this.replacement = Objects.requireNonNull(replacement, "replacement");
        return this;
    }
}
