/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.AudienceProvider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform;

import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.AudienceProvider;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer;

public static interface AudienceProvider.Builder<P extends AudienceProvider, B extends AudienceProvider.Builder<P, B>> {
    @NotNull
    public B componentRenderer(@NotNull ComponentRenderer<Pointered> var1);

    @NotNull
    public P build();

    @NotNull
    public B partition(@NotNull Function<Pointered, ?> var1);

    @NotNull
    default public <T> B componentRenderer(@NotNull Function<Pointered, T> partition, @NotNull ComponentRenderer<T> componentRenderer) {
        return this.partition(partition).componentRenderer((ComponentRenderer<Pointered>)componentRenderer.mapContext(partition));
    }
}
