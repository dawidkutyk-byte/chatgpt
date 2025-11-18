/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.DataUtil
 *  org.jsoup.helper.Validate
 */
package org.jsoup.internal;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.Validate;

@Deprecated
public final class ConstrainableInputStream
extends BufferedInputStream {
    private final int maxSize;
    private int remaining;
    private long timeout = 0L;
    private long startTime;
    private final boolean capped;
    private boolean interrupted;

    private boolean expired() {
        long now = System.nanoTime();
        long dur = now - this.startTime;
        if (this.timeout != 0L) return dur > this.timeout;
        return false;
    }

    private ConstrainableInputStream(InputStream in, int bufferSize, int maxSize) {
        super(in, bufferSize);
        Validate.isTrue((maxSize >= 0 ? 1 : 0) != 0);
        this.maxSize = maxSize;
        this.remaining = maxSize;
        this.capped = maxSize != 0;
        this.startTime = System.nanoTime();
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.interrupted) return -1;
        if (this.capped && this.remaining <= 0) {
            return -1;
        }
        if (Thread.currentThread().isInterrupted()) {
            this.interrupted = true;
            return -1;
        }
        if (this.expired()) {
            throw new SocketTimeoutException("Read timeout");
        }
        if (this.capped && len > this.remaining) {
            len = this.remaining;
        }
        try {
            int read = super.read(b, off, len);
            this.remaining -= read;
            return read;
        }
        catch (SocketTimeoutException e) {
            return 0;
        }
    }

    public ConstrainableInputStream timeout(long startTimeNanos, long timeoutMillis) {
        this.startTime = startTimeNanos;
        this.timeout = timeoutMillis * 1000000L;
        return this;
    }

    public static ConstrainableInputStream wrap(InputStream in, int bufferSize, int maxSize) {
        return in instanceof ConstrainableInputStream ? (ConstrainableInputStream)in : new ConstrainableInputStream(in, bufferSize, maxSize);
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        this.remaining = this.maxSize - this.markpos;
    }

    public ByteBuffer readToByteBuffer(int max) throws IOException {
        return DataUtil.readToByteBuffer((InputStream)this, (int)max);
    }
}
