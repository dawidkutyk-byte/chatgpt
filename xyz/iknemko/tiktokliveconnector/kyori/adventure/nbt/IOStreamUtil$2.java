/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.IOException;
import java.io.OutputStream;

static class IOStreamUtil.2
extends OutputStream {
    final /* synthetic */ OutputStream val$stream;

    IOStreamUtil.2(OutputStream outputStream) {
        this.val$stream = outputStream;
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.val$stream.write(b);
    }

    @Override
    public void write(int b) throws IOException {
        this.val$stream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.val$stream.write(b, off, len);
    }
}
