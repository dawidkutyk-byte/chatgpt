/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent;

public interface ScoreComponent
extends BuildableComponent<ScoreComponent, Builder>,
ScopedComponent<ScoreComponent> {
    @Deprecated
    @Contract(pure=true)
    @NotNull
    public ScoreComponent value(@Nullable String var1);

    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.concat(Stream.of(ExaminableProperty.of((String)"name", (String)this.name()), ExaminableProperty.of((String)"objective", (String)this.objective()), ExaminableProperty.of((String)"value", (String)this.value())), super.examinableProperties());
    }

    @NotNull
    @Contract(pure=true)
    public ScoreComponent name(@NotNull String var1);

    @NotNull
    public String name();

    @Contract(pure=true)
    @NotNull
    public ScoreComponent objective(@NotNull String var1);

    @Deprecated
    @Nullable
    public String value();

    @NotNull
    public String objective();
}
