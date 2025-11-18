/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.TagInternals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.TagInternals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern;

final class MiniMessageTranslatorArgument<T>
implements VirtualComponentRenderer<Void> {
    @NotNull
    private final T data;
    @NotNull
    private final String name;

    @NotNull
    public String name() {
        return this.name;
    }

    MiniMessageTranslatorArgument(@TagPattern @NotNull String name, @NotNull T data) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(data, "data");
        TagInternals.assertValidTagName((String)name);
        this.name = name;
        this.data = data;
    }

    public @UnknownNullability ComponentLike apply(@NotNull Void context) {
        if (!(this.data instanceof ComponentLike)) return null;
        return (ComponentLike)this.data;
    }

    @NotNull
    public T data() {
        return this.data;
    }
}
