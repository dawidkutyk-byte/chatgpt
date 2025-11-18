/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponentImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

/*
 * Exception performing whole class analysis ignored.
 */
static final class TranslatableComponentImpl.BuilderImpl
extends AbstractComponentBuilder<TranslatableComponent, TranslatableComponent.Builder>
implements TranslatableComponent.Builder {
    @Nullable
    private String key;
    @Nullable
    private String fallback;
    private List<TranslationArgument> args = Collections.emptyList();

    TranslatableComponentImpl.BuilderImpl() {
    }

    @NotNull
    public TranslatableComponent.Builder arguments(@NotNull List<? extends ComponentLike> args) {
        this.args = TranslatableComponentImpl.asArguments(Objects.requireNonNull(args, "args"));
        return this;
    }

    TranslatableComponentImpl.BuilderImpl(@NotNull TranslatableComponent component) {
        super((BuildableComponent)component);
        this.key = component.key();
        this.args = component.arguments();
        this.fallback = component.fallback();
    }

    @NotNull
    public TranslatableComponent.Builder arguments(ComponentLike ... args) {
        Objects.requireNonNull(args, "args");
        if (args.length != 0) return this.arguments(Arrays.asList(args));
        return this.arguments(Collections.emptyList());
    }

    @NotNull
    public TranslatableComponent.Builder key(@NotNull String key) {
        this.key = key;
        return this;
    }

    @NotNull
    public TranslatableComponent build() {
        if (this.key != null) return TranslatableComponentImpl.create((List)this.children, (Style)this.buildStyle(), (String)this.key, (String)this.fallback, this.args);
        throw new IllegalStateException("key must be set");
    }

    @NotNull
    public TranslatableComponent.Builder fallback(@Nullable String fallback) {
        this.fallback = fallback;
        return this;
    }
}
