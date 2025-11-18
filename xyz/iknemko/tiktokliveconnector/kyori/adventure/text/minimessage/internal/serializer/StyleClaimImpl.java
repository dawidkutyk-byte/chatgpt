/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaim;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter;

class StyleClaimImpl<V>
implements StyleClaim<V> {
    private final Predicate<V> filter;
    private final String claimKey;
    private final BiConsumer<V, TokenEmitter> emitable;
    private final Function<Style, V> lens;

    StyleClaimImpl(String claimKey, Function<Style, @Nullable V> lens, Predicate<V> filter, BiConsumer<V, TokenEmitter> emitable) {
        this.claimKey = claimKey;
        this.lens = lens;
        this.filter = filter;
        this.emitable = emitable;
    }

    @Nullable
    public Emitable apply(@NotNull Style style) {
        V element = this.lens.apply(style);
        if (element == null) return null;
        if (this.filter.test(element)) return emitter -> this.emitable.accept(element, emitter);
        return null;
    }

    public int hashCode() {
        return Objects.hash(this.claimKey);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StyleClaimImpl)) {
            return false;
        }
        StyleClaimImpl that = (StyleClaimImpl)other;
        return Objects.equals(this.claimKey, that.claimKey);
    }

    @NotNull
    public String claimKey() {
        return this.claimKey;
    }
}
