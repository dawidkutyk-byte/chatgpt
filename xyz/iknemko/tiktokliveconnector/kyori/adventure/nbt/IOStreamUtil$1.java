/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.IOException;
import java.io.InputStream;

static class IOStreamUtil.1
extends InputStream {
    final /* synthetic */ InputStream val$stream;

    @Override
    public int read(byte[] b) throws IOException {
        return this.val$stream.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return this.val$stream.read(b, off, len);
    }

    IOStreamUtil.1(InputStream inputStream) {
        this.val$stream = inputStream;
    }

    @Override
    public int read() throws IOException {
        return this.val$stream.read();
    }
}
