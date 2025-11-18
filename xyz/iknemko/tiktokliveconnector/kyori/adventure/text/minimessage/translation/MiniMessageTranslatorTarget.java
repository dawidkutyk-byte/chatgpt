/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer;

final class MiniMessageTranslatorTarget
implements VirtualComponentRenderer<Void> {
    private final Pointered pointered;

    public @UnknownNullability ComponentLike apply(@NotNull Void context) {
        return null;
    }

    @NotNull
    Pointered pointered() {
        return this.pointered;
    }

    MiniMessageTranslatorTarget(@NotNull Pointered pointered) {
        this.pointered = pointered;
    }
}
