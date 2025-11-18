/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback$Options
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallbackOptionsImpl$BuilderImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.time.Duration;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallbackOptionsImpl;

final class ClickCallbackOptionsImpl
implements ClickCallback.Options {
    private final int uses;
    static final ClickCallback.Options DEFAULT = new BuilderImpl().build();
    private final Duration lifetime;

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"uses", (int)this.uses), ExaminableProperty.of((String)"expiration", (Object)this.lifetime));
    }

    ClickCallbackOptionsImpl(int uses, Duration lifetime) {
        this.uses = uses;
        this.lifetime = lifetime;
    }

    public int uses() {
        return this.uses;
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public Duration lifetime() {
        return this.lifetime;
    }
}
