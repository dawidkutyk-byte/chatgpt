/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponentImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

/*
 * Exception performing whole class analysis ignored.
 */
static final class ScoreComponentImpl.BuilderImpl
extends AbstractComponentBuilder<ScoreComponent, ScoreComponent.Builder>
implements ScoreComponent.Builder {
    @Nullable
    private String name;
    @Nullable
    private String value;
    @Nullable
    private String objective;

    @NotNull
    public ScoreComponent build() {
        if (this.name == null) {
            throw new IllegalStateException("name must be set");
        }
        if (this.objective != null) return ScoreComponentImpl.create((List)this.children, (Style)this.buildStyle(), (String)this.name, (String)this.objective, (String)this.value);
        throw new IllegalStateException("objective must be set");
    }

    ScoreComponentImpl.BuilderImpl(@NotNull ScoreComponent component) {
        super((BuildableComponent)component);
        this.name = component.name();
        this.objective = component.objective();
        this.value = component.value();
    }

    ScoreComponentImpl.BuilderImpl() {
    }

    @NotNull
    public ScoreComponent.Builder name(@NotNull String name) {
        this.name = Objects.requireNonNull(name, "name");
        return this;
    }

    @Deprecated
    @NotNull
    public ScoreComponent.Builder value(@Nullable String value) {
        this.value = value;
        return this;
    }

    @NotNull
    public ScoreComponent.Builder objective(@NotNull String objective) {
        this.objective = Objects.requireNonNull(objective, "objective");
        return this;
    }
}
