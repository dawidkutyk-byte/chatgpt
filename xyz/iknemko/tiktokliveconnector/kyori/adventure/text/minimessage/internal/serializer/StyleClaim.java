/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaimImpl
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
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.StyleClaimImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter;

public interface StyleClaim<V> {
    @Nullable
    public Emitable apply(@NotNull Style var1);

    @NotNull
    public static <T> StyleClaim<T> claim(@NotNull String claimKey, @NotNull Function<Style, @Nullable T> lens, @NotNull Predicate<T> filter, @NotNull BiConsumer<T, TokenEmitter> emitable) {
        return new StyleClaimImpl(Objects.requireNonNull(claimKey, "claimKey"), Objects.requireNonNull(lens, "lens"), Objects.requireNonNull(filter, "filter"), Objects.requireNonNull(emitable, "emitable"));
    }

    @NotNull
    public static <T> StyleClaim<T> claim(@NotNull String claimKey, @NotNull Function<Style, @Nullable T> lens, @NotNull BiConsumer<T, TokenEmitter> emitable) {
        return StyleClaim.claim(claimKey, lens, $ -> true, emitable);
    }

    @NotNull
    public String claimKey();
}
