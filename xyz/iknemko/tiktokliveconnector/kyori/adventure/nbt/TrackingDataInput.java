/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagScope
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagScope$NoOp
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.DataInput;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagScope;

final class TrackingDataInput
implements BinaryTagScope,
DataInput {
    private final DataInput input;
    private final long maxLength;
    private int depth;
    private static final int MAX_DEPTH = 512;
    private long counter;

    private void ensureMaxLength(long expected) throws IOException {
        if (this.maxLength <= 0L) return;
        if (this.counter + expected <= this.maxLength) return;
        throw new IOException("The read NBT was longer than the maximum allowed size of " + this.maxLength + " bytes!");
    }

    @Override
    public double readDouble() throws IOException {
        this.counter += 8L;
        return this.input.readDouble();
    }

    public static BinaryTagScope enter(DataInput input, long expectedSize) throws IOException {
        if (!(input instanceof TrackingDataInput)) return BinaryTagScope.NoOp.INSTANCE;
        return ((TrackingDataInput)input).enter(expectedSize);
    }

    public void close() throws IOException {
        this.exit();
    }

    @Override
    public int skipBytes(int n) throws IOException {
        return this.input.skipBytes(n);
    }

    @Override
    public float readFloat() throws IOException {
        this.counter += 4L;
        return this.input.readFloat();
    }

    @Override
    public char readChar() throws IOException {
        this.counter += 2L;
        return this.input.readChar();
    }

    @Override
    @NotNull
    public String readUTF() throws IOException {
        String result = this.input.readUTF();
        this.counter += (long)result.length() * 2L + 2L;
        return result;
    }

    @Override
    public void readFully(byte @NotNull [] array) throws IOException {
        this.counter += (long)array.length;
        this.input.readFully(array);
    }

    @Override
    public boolean readBoolean() throws IOException {
        ++this.counter;
        return this.input.readBoolean();
    }

    public DataInput input() {
        return this.input;
    }

    TrackingDataInput(DataInput input, long maxLength) {
        this.input = input;
        this.maxLength = maxLength;
    }

    @Override
    public void readFully(byte @NotNull [] array, int off, int len) throws IOException {
        this.counter += (long)len;
        this.input.readFully(array, off, len);
    }

    @Override
    public int readUnsignedByte() throws IOException {
        ++this.counter;
        return this.input.readUnsignedByte();
    }

    @Override
    public int readUnsignedShort() throws IOException {
        this.counter += 2L;
        return this.input.readUnsignedShort();
    }

    @Override
    @Nullable
    public String readLine() throws IOException {
        @Nullable String result = this.input.readLine();
        if (result == null) return result;
        this.counter += (long)(result.length() + 1);
        return result;
    }

    public static BinaryTagScope enter(DataInput input) throws IOException {
        if (!(input instanceof TrackingDataInput)) return BinaryTagScope.NoOp.INSTANCE;
        return ((TrackingDataInput)input).enter();
    }

    @Override
    public int readInt() throws IOException {
        this.counter += 4L;
        return this.input.readInt();
    }

    public TrackingDataInput enter(long expectedSize) throws IOException {
        if (this.depth++ > 512) {
            throw new IOException("NBT read exceeded maximum depth of 512");
        }
        this.ensureMaxLength(expectedSize);
        return this;
    }

    public void exit() throws IOException {
        --this.depth;
        this.ensureMaxLength(0L);
    }

    @Override
    public short readShort() throws IOException {
        this.counter += 2L;
        return this.input.readShort();
    }

    @Override
    public byte readByte() throws IOException {
        ++this.counter;
        return this.input.readByte();
    }

    public TrackingDataInput enter() throws IOException {
        return this.enter(0L);
    }

    @Override
    public long readLong() throws IOException {
        this.counter += 8L;
        return this.input.readLong();
    }
}
