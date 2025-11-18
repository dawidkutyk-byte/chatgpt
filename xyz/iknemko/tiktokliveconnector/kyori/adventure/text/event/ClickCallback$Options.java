/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback$Options$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallbackOptionsImpl$BuilderImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.time.Duration;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallbackOptionsImpl;

@ApiStatus.NonExtendable
public static interface ClickCallback.Options
extends Examinable {
    @NotNull
    public Duration lifetime();

    @NotNull
    public static Builder builder() {
        return new ClickCallbackOptionsImpl.BuilderImpl();
    }

    public int uses();

    @NotNull
    public static Builder builder(@NotNull ClickCallback.Options existing) {
        return new ClickCallbackOptionsImpl.BuilderImpl(existing);
    }
}
