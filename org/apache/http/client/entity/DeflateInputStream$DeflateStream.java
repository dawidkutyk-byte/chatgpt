/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

static class DeflateInputStream.DeflateStream
extends InflaterInputStream {
    private boolean closed = false;

    public DeflateInputStream.DeflateStream(InputStream in, Inflater inflater) {
        super(in, inflater);
    }

    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.inf.end();
        super.close();
    }
}
