/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent;

public interface SelectorComponent
extends BuildableComponent<SelectorComponent, Builder>,
ScopedComponent<SelectorComponent> {
    @NotNull
    public SelectorComponent separator(@Nullable ComponentLike var1);

    @Nullable
    public Component separator();

    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.concat(Stream.of(ExaminableProperty.of((String)"pattern", (String)this.pattern()), ExaminableProperty.of((String)"separator", (Object)this.separator())), super.examinableProperties());
    }

    @NotNull
    public String pattern();

    @NotNull
    @Contract(pure=true)
    public SelectorComponent pattern(@NotNull String var1);
}
