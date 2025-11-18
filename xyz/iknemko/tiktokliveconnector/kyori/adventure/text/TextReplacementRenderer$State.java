/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig$Condition
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.function.BiFunction;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig;

static final class TextReplacementRenderer.State {
    boolean firstMatch = true;
    final BiFunction<MatchResult, // Could not load outer class - annotation placement on inner may be incorrect
    TextComponent.Builder, @Nullable ComponentLike> replacement;
    final boolean replaceInsideHoverEvents;
    int replaceCount = 0;
    final TextReplacementConfig.Condition continuer;
    int matchCount = 0;
    boolean running = true;
    final Pattern pattern;

    TextReplacementRenderer.State(@NotNull Pattern pattern, @NotNull BiFunction<MatchResult, // Could not load outer class - annotation placement on inner may be incorrect
    TextComponent.Builder, @Nullable ComponentLike> replacement, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull TextReplacementConfig.Condition continuer, boolean replaceInsideHoverEvents) {
        this.pattern = pattern;
        this.replacement = replacement;
        this.continuer = continuer;
        this.replaceInsideHoverEvents = replaceInsideHoverEvents;
    }
}
