/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.EndBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.EndBinaryTag;

final class EndBinaryTagImpl
extends AbstractBinaryTag
implements EndBinaryTag {
    static final EndBinaryTagImpl INSTANCE = new EndBinaryTagImpl();

    public boolean equals(Object that) {
        return this == that;
    }

    EndBinaryTagImpl() {
    }

    public int hashCode() {
        return 0;
    }
}
