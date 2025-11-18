/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventureProperties
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.LegacyFormattingDetected
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponentImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Nag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventureProperties;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.LegacyFormattingDetected;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Nag;

class TextComponentImpl
extends AbstractComponent
implements TextComponent {
    private final String content;
    @VisibleForTesting
    static final char SECTION_CHAR = '\u00a7';
    static final TextComponent EMPTY;
    static final TextComponent SPACE;
    private static final boolean WARN_WHEN_LEGACY_FORMATTING_DETECTED;
    static final TextComponent NEWLINE;

    TextComponent create0(@NotNull List<? extends ComponentLike> children, @NotNull Style style, @NotNull String content) {
        return TextComponentImpl.create(children, style, content);
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public TextComponent.Builder toBuilder() {
        return new BuilderImpl((TextComponent)this);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextComponentImpl)) {
            return false;
        }
        if (!super.equals(other)) {
            return false;
        }
        TextComponentImpl that = (TextComponentImpl)((Object)other);
        return Objects.equals(this.content, that.content);
    }

    @VisibleForTesting
    @Nullable
    final LegacyFormattingDetected warnWhenLegacyFormattingDetected() {
        if (this.content.indexOf(167) == -1) return null;
        return new LegacyFormattingDetected((Component)this);
    }

    @NotNull
    public String content() {
        return this.content;
    }

    @NotNull
    public TextComponent children(@NotNull List<? extends ComponentLike> children) {
        return this.create0(children, this.style, this.content);
    }

    static TextComponent create(@NotNull List<? extends ComponentLike> children, @NotNull Style style, @NotNull String content) {
        List filteredChildren = ComponentLike.asComponents(children, (Predicate)IS_NOT_EMPTY);
        if (!filteredChildren.isEmpty()) return new TextComponentImpl(filteredChildren, Objects.requireNonNull(style, "style"), Objects.requireNonNull(content, "content"));
        if (!style.isEmpty()) return new TextComponentImpl(filteredChildren, Objects.requireNonNull(style, "style"), Objects.requireNonNull(content, "content"));
        if (!content.isEmpty()) return new TextComponentImpl(filteredChildren, Objects.requireNonNull(style, "style"), Objects.requireNonNull(content, "content"));
        return Component.empty();
    }

    static {
        WARN_WHEN_LEGACY_FORMATTING_DETECTED = Boolean.TRUE.equals(AdventureProperties.TEXT_WARN_WHEN_LEGACY_FORMATTING_DETECTED.value());
        EMPTY = TextComponentImpl.createDirect("");
        NEWLINE = TextComponentImpl.createDirect("\n");
        SPACE = TextComponentImpl.createDirect(" ");
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.content.hashCode();
        return result;
    }

    @NotNull
    private static TextComponent createDirect(@NotNull String content) {
        return new TextComponentImpl(Collections.<Component>emptyList(), Style.empty(), content);
    }

    @NotNull
    public TextComponent content(@NotNull String content) {
        if (!Objects.equals(this.content, content)) return this.create0(this.children, this.style, content);
        return this;
    }

    @NotNull
    public TextComponent style(@NotNull Style style) {
        return this.create0(this.children, style, this.content);
    }

    TextComponentImpl(@NotNull List<Component> children, @NotNull Style style, @NotNull String content) {
        super(children, style);
        this.content = content;
        if (!WARN_WHEN_LEGACY_FORMATTING_DETECTED) return;
        LegacyFormattingDetected nag = this.warnWhenLegacyFormattingDetected();
        if (nag == null) return;
        Nag.print((Nag)nag);
    }
}
