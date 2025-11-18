/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponentImpl$BuilderImpl
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
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

final class ScoreComponentImpl
extends AbstractComponent
implements ScoreComponent {
    private final String objective;
    private final String name;
    @Deprecated
    @Nullable
    private final String value;

    @NotNull
    public ScoreComponent name(@NotNull String name) {
        if (!Objects.equals(this.name, name)) return ScoreComponentImpl.create(this.children, this.style, name, this.objective, this.value);
        return this;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.name.hashCode();
        result = 31 * result + this.objective.hashCode();
        result = 31 * result + Objects.hashCode(this.value);
        return result;
    }

    @NotNull
    public ScoreComponent children(@NotNull List<? extends ComponentLike> children) {
        return ScoreComponentImpl.create(children, this.style, this.name, this.objective, this.value);
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @Deprecated
    @NotNull
    public ScoreComponent value(@Nullable String value) {
        if (!Objects.equals(this.value, value)) return ScoreComponentImpl.create(this.children, this.style, this.name, this.objective, value);
        return this;
    }

    @NotNull
    public ScoreComponent style(@NotNull Style style) {
        return ScoreComponentImpl.create(this.children, style, this.name, this.objective, this.value);
    }

    @NotNull
    public ScoreComponent objective(@NotNull String objective) {
        if (!Objects.equals(this.objective, objective)) return ScoreComponentImpl.create(this.children, this.style, this.name, objective, this.value);
        return this;
    }

    @NotNull
    public ScoreComponent.Builder toBuilder() {
        return new BuilderImpl((ScoreComponent)this);
    }

    static ScoreComponent create(@NotNull List<? extends ComponentLike> children, @NotNull Style style, @NotNull String name, @NotNull String objective, @Nullable String value) {
        return new ScoreComponentImpl(ComponentLike.asComponents(children, (Predicate)IS_NOT_EMPTY), Objects.requireNonNull(style, "style"), Objects.requireNonNull(name, "name"), Objects.requireNonNull(objective, "objective"), value);
    }

    @NotNull
    public String objective() {
        return this.objective;
    }

    @NotNull
    public String name() {
        return this.name;
    }

    ScoreComponentImpl(@NotNull List<Component> children, @NotNull Style style, @NotNull String name, @NotNull String objective, @Nullable String value) {
        super(children, style);
        this.name = name;
        this.objective = objective;
        this.value = value;
    }

    @Deprecated
    @Nullable
    public String value() {
        return this.value;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScoreComponent)) {
            return false;
        }
        if (super.equals(other)) ScoreComponent that;
        return Objects.equals(this.name, (that = (ScoreComponent)other).name()) && Objects.equals(this.objective, that.objective()) && Objects.equals(this.value, that.value());
        return false;
    }
}
