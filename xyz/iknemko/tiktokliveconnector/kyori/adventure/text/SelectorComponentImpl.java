/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponentImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

final class SelectorComponentImpl
extends AbstractComponent
implements SelectorComponent {
    @Nullable
    private final Component separator;
    private final String pattern;

    @Nullable
    public Component separator() {
        return this.separator;
    }

    @NotNull
    public SelectorComponent separator(@Nullable ComponentLike separator) {
        return SelectorComponentImpl.create(this.children, this.style, this.pattern, separator);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SelectorComponent)) {
            return false;
        }
        if (super.equals(other)) SelectorComponent that;
        return Objects.equals(this.pattern, (that = (SelectorComponent)other).pattern()) && Objects.equals(this.separator, that.separator());
        return false;
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    SelectorComponentImpl(@NotNull List<Component> children, @NotNull Style style, @NotNull String pattern, @Nullable Component separator) {
        super(children, style);
        this.pattern = pattern;
        this.separator = separator;
    }

    @NotNull
    public SelectorComponent children(@NotNull List<? extends ComponentLike> children) {
        return SelectorComponentImpl.create(children, this.style, this.pattern, (ComponentLike)this.separator);
    }

    @NotNull
    public SelectorComponent pattern(@NotNull String pattern) {
        if (!Objects.equals(this.pattern, pattern)) return SelectorComponentImpl.create(this.children, this.style, pattern, (ComponentLike)this.separator);
        return this;
    }

    @NotNull
    public String pattern() {
        return this.pattern;
    }

    static SelectorComponent create(@NotNull List<? extends ComponentLike> children, @NotNull Style style, @NotNull String pattern, @Nullable ComponentLike separator) {
        return new SelectorComponentImpl(ComponentLike.asComponents(children, (Predicate)IS_NOT_EMPTY), Objects.requireNonNull(style, "style"), Objects.requireNonNull(pattern, "pattern"), ComponentLike.unbox((ComponentLike)separator));
    }

    @NotNull
    public SelectorComponent style(@NotNull Style style) {
        return SelectorComponentImpl.create(this.children, style, this.pattern, (ComponentLike)this.separator);
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.pattern.hashCode();
        result = 31 * result + Objects.hashCode(this.separator);
        return result;
    }

    @NotNull
    public SelectorComponent.Builder toBuilder() {
        return new BuilderImpl((SelectorComponent)this);
    }
}
