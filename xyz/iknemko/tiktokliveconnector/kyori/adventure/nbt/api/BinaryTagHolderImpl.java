/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api.BinaryTagHolder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api.BinaryTagHolder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec;

final class BinaryTagHolderImpl
implements BinaryTagHolder {
    private final String string;

    BinaryTagHolderImpl(String string) {
        this.string = Objects.requireNonNull(string, "string");
    }

    public String toString() {
        return this.string;
    }

    public boolean equals(Object that) {
        if (that instanceof BinaryTagHolderImpl) return this.string.equals(((BinaryTagHolderImpl)that).string);
        return false;
    }

    @NotNull
    public String string() {
        return this.string;
    }

    public int hashCode() {
        return 31 * this.string.hashCode();
    }

    @NotNull
    public <T, DX extends Exception> T get(@NotNull Codec<T, String, DX, ?> codec) throws DX {
        return (T)codec.decode((Object)this.string);
    }
}
