/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;

class IntArrayBinaryTagImpl.1
implements PrimitiveIterator.OfInt {
    private int index;

    IntArrayBinaryTagImpl.1() {
    }

    @Override
    public int nextInt() {
        if (this.hasNext()) return IntArrayBinaryTagImpl.this.value[this.index++];
        throw new NoSuchElementException();
    }

    @Override
    public boolean hasNext() {
        return this.index < IntArrayBinaryTagImpl.this.value.length - 1;
    }
}
