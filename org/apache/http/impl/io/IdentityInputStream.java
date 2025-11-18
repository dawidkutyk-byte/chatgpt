/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.io.BufferInfo
 *  org.apache.http.io.SessionInputBuffer
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.Args;

public class IdentityInputStream
extends InputStream {
    private boolean closed = false;
    private final SessionInputBuffer in;

    @Override
    public int read() throws IOException {
        return this.closed ? -1 : this.in.read();
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return this.closed ? -1 : this.in.read(b, off, len);
    }

    @Override
    public void close() throws IOException {
        this.closed = true;
    }

    @Override
    public int available() throws IOException {
        if (!(this.in instanceof BufferInfo)) return 0;
        return ((BufferInfo)this.in).length();
    }

    public IdentityInputStream(SessionInputBuffer in) {
        this.in = (SessionInputBuffer)Args.notNull((Object)in, (String)"Session input buffer");
    }
}
