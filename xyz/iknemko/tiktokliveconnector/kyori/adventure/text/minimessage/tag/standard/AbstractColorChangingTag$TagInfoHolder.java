/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter;

static final class AbstractColorChangingTag.TagInfoHolder
implements VirtualComponentRenderer<Void>,
Emitable {
    private final Component originalComp;
    private final Consumer<TokenEmitter> output;

    @Nullable
    public Component substitute() {
        return this.originalComp;
    }

    public void emit(@NotNull TokenEmitter emitter) {
        this.output.accept(emitter);
    }

    @NotNull
    public String fallbackString() {
        return "";
    }

    AbstractColorChangingTag.TagInfoHolder(Consumer<TokenEmitter> output, Component originalComp) {
        this.output = output;
        this.originalComp = originalComp;
    }

    public @UnknownNullability ComponentLike apply(@NotNull Void context) {
        return this.originalComp;
    }
}
