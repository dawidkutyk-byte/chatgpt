/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translatable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translatable;

public interface TranslatableComponent
extends BuildableComponent<TranslatableComponent, Builder>,
ScopedComponent<TranslatableComponent> {
    @NotNull
    public String key();

    @NotNull
    @Contract(pure=true)
    default public TranslatableComponent key(@NotNull Translatable translatable) {
        return this.key(Objects.requireNonNull(translatable, "translatable").translationKey());
    }

    @Deprecated
    @NotNull
    @Contract(pure=true)
    default public TranslatableComponent args(ComponentLike ... args) {
        return this.arguments(args);
    }

    @Deprecated
    @Contract(pure=true)
    @NotNull
    default public TranslatableComponent args(@NotNull List<? extends ComponentLike> args) {
        return this.arguments(args);
    }

    @Nullable
    public String fallback();

    @NotNull
    @Contract(pure=true)
    public TranslatableComponent arguments(@NotNull List<? extends ComponentLike> var1);

    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.concat(Stream.of(ExaminableProperty.of((String)"key", (String)this.key()), ExaminableProperty.of((String)"arguments", this.arguments()), ExaminableProperty.of((String)"fallback", (String)this.fallback())), super.examinableProperties());
    }

    @Contract(pure=true)
    @NotNull
    public TranslatableComponent arguments(ComponentLike ... var1);

    @Deprecated
    @NotNull
    public List<Component> args();

    @NotNull
    @Contract(pure=true)
    public TranslatableComponent fallback(@Nullable String var1);

    @NotNull
    public List<TranslationArgument> arguments();

    @NotNull
    @Contract(pure=true)
    public TranslatableComponent key(@NotNull String var1);
}
