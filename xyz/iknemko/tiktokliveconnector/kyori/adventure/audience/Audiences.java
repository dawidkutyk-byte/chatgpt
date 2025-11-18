/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.ForwardingAudience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackCallback
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.audience;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.ForwardingAudience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackCallback;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;

public final class Audiences {
    static final Collector<? super Audience, ?, ForwardingAudience> COLLECTOR = Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new), audiences -> Audience.audience(Collections.unmodifiableCollection(audiences)));

    @NotNull
    static ResourcePackCallback unwrapCallback(Audience forwarding, Audience dest, @NotNull ResourcePackCallback cb) {
        if (cb != ResourcePackCallback.noOp()) return (uuid, status, audience) -> cb.packEventReceived(uuid, status, audience == dest ? forwarding : audience);
        return cb;
    }

    @NotNull
    public static Consumer<? super Audience> sendingMessage(@NotNull ComponentLike message) {
        return audience -> audience.sendMessage(message);
    }

    private Audiences() {
    }
}
