/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTagImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.Collections;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTagImpl;

final class ListBinaryTag0 {
    private static final String WRAPPER_KEY = "";

    static CompoundBinaryTag box(BinaryTag tag) {
        if (!(tag instanceof CompoundBinaryTag)) return new CompoundBinaryTagImpl(Collections.singletonMap(WRAPPER_KEY, tag));
        return (CompoundBinaryTag)tag;
    }

    private ListBinaryTag0() {
    }

    static BinaryTag unbox(CompoundBinaryTag compound) {
        if (compound.size() != 1) return compound;
        BinaryTag potentialValue = compound.get(WRAPPER_KEY);
        if (potentialValue == null) return compound;
        return potentialValue;
    }
}
