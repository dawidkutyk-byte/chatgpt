/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

private static final class CraftBukkitFacet.AbstractBook.TrustedByteArrayOutputStream
extends ByteArrayOutputStream {
    public InputStream toInputStream() {
        return new ByteArrayInputStream(this.buf, 0, this.count);
    }

    private CraftBukkitFacet.AbstractBook.TrustedByteArrayOutputStream() {
    }
}
