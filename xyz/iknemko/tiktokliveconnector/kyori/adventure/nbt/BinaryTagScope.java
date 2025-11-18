/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.IOException;

interface BinaryTagScope
extends AutoCloseable {
    @Override
    public void close() throws IOException;
}
