/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig$Condition
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfigImpl$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementRenderer$State
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.function.BiFunction;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfigImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementRenderer;

final class TextReplacementConfigImpl
implements TextReplacementConfig {
    private final TextReplacementConfig.Condition continuer;
    private final boolean replaceInsideHoverEvents;
    private final BiFunction<MatchResult, // Could not load outer class - annotation placement on inner may be incorrect
    TextComponent.Builder, @Nullable ComponentLike> replacement;
    private final Pattern matchPattern;

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"matchPattern", (Object)this.matchPattern), ExaminableProperty.of((String)"replacement", this.replacement), ExaminableProperty.of((String)"continuer", (Object)this.continuer));
    }

    static /* synthetic */ TextReplacementConfig.Condition access$200(TextReplacementConfigImpl x0) {
        return x0.continuer;
    }

    static /* synthetic */ Pattern access$000(TextReplacementConfigImpl x0) {
        return x0.matchPattern;
    }

    static /* synthetic */ BiFunction access$100(TextReplacementConfigImpl x0) {
        return x0.replacement;
    }

    TextReplacementRenderer.State createState() {
        return new TextReplacementRenderer.State(this.matchPattern, this.replacement, this.continuer, this.replaceInsideHoverEvents);
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public Pattern matchPattern() {
        return this.matchPattern;
    }

    TextReplacementConfigImpl(Builder builder) {
        this.matchPattern = builder.matchPattern;
        this.replacement = builder.replacement;
        this.continuer = builder.continuer;
        this.replaceInsideHoverEvents = builder.replaceInsideHoverEvents;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextReplacementConfig.Builder toBuilder() {
        return new Builder(this);
    }
}
