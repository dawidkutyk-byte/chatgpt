/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.ClaimConsumer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.ClaimConsumer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim;

public static interface SerializableResolver.Single
extends SerializableResolver {
    default public void handle(@NotNull Component serializable, @NotNull ClaimConsumer consumer) {
        Emitable applied;
        @Nullable StyleClaim<?> style = this.claimStyle();
        if (style != null && !consumer.styleClaimed(style.claimKey()) && (applied = style.apply(serializable.style())) != null) {
            consumer.style(style.claimKey(), applied);
        }
        if (consumer.componentClaimed()) return;
        @Nullable Emitable component = this.claimComponent(serializable);
        if (component == null) return;
        consumer.component(component);
    }

    @Nullable
    default public Emitable claimComponent(@NotNull Component component) {
        return null;
    }

    @Nullable
    default public StyleClaim<?> claimStyle() {
        return null;
    }
}
