/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback$Options$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallbackOptionsImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallbackOptionsImpl;

static final class ClickCallbackOptionsImpl.BuilderImpl
implements ClickCallback.Options.Builder {
    private static final int DEFAULT_USES = 1;
    private int uses;
    private Duration lifetime;

    @NotNull
    public ClickCallback.Options.Builder lifetime(@NotNull TemporalAmount lifetime) {
        this.lifetime = lifetime instanceof Duration ? (Duration)lifetime : Duration.from(Objects.requireNonNull(lifetime, "lifetime"));
        return this;
    }

    ClickCallbackOptionsImpl.BuilderImpl(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull ClickCallback.Options existing) {
        this.uses = existing.uses();
        this.lifetime = existing.lifetime();
    }

    @NotNull
    public ClickCallback.Options.Builder uses(int uses) {
        this.uses = uses;
        return this;
    }

    ClickCallbackOptionsImpl.BuilderImpl() {
        this.uses = 1;
        this.lifetime = ClickCallback.DEFAULT_LIFETIME;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull ClickCallback.Options build() {
        return new ClickCallbackOptionsImpl(this.uses, this.lifetime);
    }
}
