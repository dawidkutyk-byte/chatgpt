/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponentImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgumentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgumentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

final class TranslatableComponentImpl
extends AbstractComponent
implements TranslatableComponent {
    private final String key;
    @Nullable
    private final String fallback;
    private final List<TranslationArgument> args;

    @NotNull
    public TranslatableComponent children(@NotNull List<? extends ComponentLike> children) {
        return TranslatableComponentImpl.create(children, this.style, this.key, this.fallback, this.args);
    }

    @Nullable
    public String fallback() {
        return this.fallback;
    }

    TranslatableComponentImpl(@NotNull List<Component> children, @NotNull Style style, @NotNull String key, @Nullable String fallback, @NotNull List<TranslationArgument> args) {
        super(children, style);
        this.key = key;
        this.fallback = fallback;
        this.args = args;
    }

    @NotNull
    public TranslatableComponent fallback(@Nullable String fallback) {
        return TranslatableComponentImpl.create(this.children, this.style, this.key, fallback, this.args);
    }

    static TranslatableComponent create(@NotNull List<Component> children, @NotNull Style style, @NotNull String key, @Nullable String fallback, @NotNull @NotNull ComponentLike @NotNull [] args) {
        Objects.requireNonNull(args, "args");
        return TranslatableComponentImpl.create(children, style, key, fallback, Arrays.asList(args));
    }

    @NotNull
    public String key() {
        return this.key;
    }

    @NotNull
    public TranslatableComponent arguments(ComponentLike ... args) {
        return TranslatableComponentImpl.create(this.children, this.style, this.key, this.fallback, args);
    }

    static TranslatableComponent create(@NotNull List<? extends ComponentLike> children, @NotNull Style style, @NotNull String key, @Nullable String fallback, @NotNull List<? extends ComponentLike> args) {
        return new TranslatableComponentImpl(ComponentLike.asComponents(children, (Predicate)IS_NOT_EMPTY), Objects.requireNonNull(style, "style"), Objects.requireNonNull(key, "key"), fallback, TranslatableComponentImpl.asArguments(args));
    }

    @NotNull
    public List<TranslationArgument> arguments() {
        return this.args;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TranslatableComponent)) {
            return false;
        }
        if (super.equals(other)) TranslatableComponent that;
        return Objects.equals(this.key, (that = (TranslatableComponent)other).key()) && Objects.equals(this.fallback, that.fallback()) && Objects.equals(this.args, that.arguments());
        return false;
    }

    @NotNull
    public TranslatableComponent style(@NotNull Style style) {
        return TranslatableComponentImpl.create(this.children, style, this.key, this.fallback, this.args);
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    static List<TranslationArgument> asArguments(@NotNull List<? extends ComponentLike> likes) {
        if (likes.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<TranslationArgument> ret = new ArrayList<TranslationArgument>(likes.size());
        int i = 0;
        while (i < likes.size()) {
            ComponentLike like = likes.get(i);
            if (like == null) {
                throw new NullPointerException("likes[" + i + "]");
            }
            if (like instanceof TranslationArgument) {
                ret.add((TranslationArgument)like);
            } else if (like instanceof TranslationArgumentLike) {
                ret.add(Objects.requireNonNull(((TranslationArgumentLike)like).asTranslationArgument(), "likes[" + i + "].asTranslationArgument()"));
            } else {
                ret.add(TranslationArgument.component((ComponentLike)like));
            }
            ++i;
        }
        return Collections.unmodifiableList(ret);
    }

    @Deprecated
    @NotNull
    public List<Component> args() {
        return ComponentLike.asComponents(this.args);
    }

    @NotNull
    public TranslatableComponent arguments(@NotNull List<? extends ComponentLike> args) {
        return TranslatableComponentImpl.create(this.children, this.style, this.key, this.fallback, args);
    }

    @NotNull
    public TranslatableComponent.Builder toBuilder() {
        return new BuilderImpl((TranslatableComponent)this);
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.key.hashCode();
        result = 31 * result + Objects.hashCode(this.fallback);
        result = 31 * result + this.args.hashCode();
        return result;
    }

    @NotNull
    public TranslatableComponent key(@NotNull String key) {
        if (!Objects.equals(this.key, key)) return TranslatableComponentImpl.create(this.children, this.style, key, this.fallback, this.args);
        return this;
    }
}
