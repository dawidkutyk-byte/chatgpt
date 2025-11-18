/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;

class LongArrayBinaryTagImpl.1
implements PrimitiveIterator.OfLong {
    private int index;

    @Override
    public long nextLong() {
        if (this.hasNext()) return LongArrayBinaryTagImpl.this.value[this.index++];
        throw new NoSuchElementException();
    }

    LongArrayBinaryTagImpl.1() {
    }

    @Override
    public boolean hasNext() {
        return this.index < LongArrayBinaryTagImpl.this.value.length - 1;
    }
}
