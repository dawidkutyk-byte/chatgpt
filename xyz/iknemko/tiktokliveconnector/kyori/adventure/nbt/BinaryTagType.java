/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType$Impl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType$Impl$Numeric
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType$Reader
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType$Writer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag;

public abstract class BinaryTagType<T extends BinaryTag>
implements Predicate<BinaryTagType<? extends BinaryTag>> {
    private static final List<BinaryTagType<? extends BinaryTag>> TYPES = new ArrayList<BinaryTagType<? extends BinaryTag>>();

    public abstract void write(@NotNull T var1, @NotNull DataOutput var2) throws IOException;

    @NotNull
    static <T extends NumberBinaryTag> BinaryTagType<T> registerNumeric(Class<T> type, byte id, Reader<T> reader, Writer<T> writer) {
        return BinaryTagType.register(new Impl.Numeric(type, id, reader, writer));
    }

    static <T extends BinaryTag> void writeUntyped(BinaryTagType<? extends BinaryTag> type, T tag, DataOutput output) throws IOException {
        type.write(tag, output);
    }

    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    static BinaryTagType<? extends BinaryTag> of(byte id) {
        return BinaryTagType.binaryTagType(id);
    }

    @NotNull
    static BinaryTagType<? extends BinaryTag> binaryTagType(byte id) {
        int i = 0;
        while (i < TYPES.size()) {
            BinaryTagType<? extends BinaryTag> type = TYPES.get(i);
            if (type.id() == id) {
                return type;
            }
            ++i;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    @NotNull
    public abstract T read(@NotNull DataInput var1) throws IOException;

    @Override
    public boolean test(BinaryTagType<? extends BinaryTag> that) {
        return this == that || this.numeric() && that.numeric();
    }

    public abstract byte id();

    private static <T extends BinaryTag, Y extends BinaryTagType<T>> Y register(Y type) {
        TYPES.add(type);
        return type;
    }

    @NotNull
    static <T extends BinaryTag> BinaryTagType<T> register(Class<T> type, byte id, Reader<T> reader, @Nullable Writer<T> writer) {
        return BinaryTagType.register(new Impl(type, id, reader, writer));
    }

    abstract boolean numeric();
}
