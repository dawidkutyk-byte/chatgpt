/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback$Options
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.time.temporal.TemporalAmount;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback;

@ApiStatus.NonExtendable
public static interface ClickCallback.Options.Builder
extends AbstractBuilder<ClickCallback.Options> {
    @NotNull
    public ClickCallback.Options.Builder uses(int var1);

    @NotNull
    public ClickCallback.Options.Builder lifetime(@NotNull TemporalAmount var1);
}
