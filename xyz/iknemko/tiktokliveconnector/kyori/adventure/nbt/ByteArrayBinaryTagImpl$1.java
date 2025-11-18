/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.Iterator;

class ByteArrayBinaryTagImpl.1
implements Iterator<Byte> {
    private int index;

    @Override
    public Byte next() {
        return ByteArrayBinaryTagImpl.this.value[this.index++];
    }

    ByteArrayBinaryTagImpl.1() {
    }

    @Override
    public boolean hasNext() {
        return this.index < ByteArrayBinaryTagImpl.this.value.length - 1;
    }
}
