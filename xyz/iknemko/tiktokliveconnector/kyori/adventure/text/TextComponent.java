/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;

public interface TextComponent
extends BuildableComponent<TextComponent, Builder>,
ScopedComponent<TextComponent> {
    @Contract(pure=true)
    @NotNull
    public TextComponent content(@NotNull String var1);

    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.concat(Stream.of(ExaminableProperty.of((String)"content", (String)this.content())), super.examinableProperties());
    }

    @NotNull
    public String content();

    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static TextComponent ofChildren(ComponentLike ... components) {
        return Component.textOfChildren((ComponentLike[])components);
    }
}
