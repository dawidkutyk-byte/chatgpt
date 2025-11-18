/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagScope
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagScope;

public static final class BinaryTagScope.NoOp
implements BinaryTagScope {
    static final BinaryTagScope.NoOp INSTANCE = new BinaryTagScope.NoOp();

    private BinaryTagScope.NoOp() {
    }

    public void close() {
    }
}
