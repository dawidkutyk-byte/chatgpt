/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ArrayBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ArrayBinaryTag;

abstract class ArrayBinaryTagImpl
extends AbstractBinaryTag
implements ArrayBinaryTag {
    ArrayBinaryTagImpl() {
    }

    static void checkIndex(int index, int length) {
        if (index < 0) throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        if (index < length) return;
        throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
}
