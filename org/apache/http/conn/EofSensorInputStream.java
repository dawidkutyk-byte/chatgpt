/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ConnectionReleaseTrigger
 *  org.apache.http.conn.EofSensorWatcher
 *  org.apache.http.util.Args
 */
package org.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.conn.EofSensorWatcher;
import org.apache.http.util.Args;

public class EofSensorInputStream
extends InputStream
implements ConnectionReleaseTrigger {
    protected InputStream wrappedStream;
    private final EofSensorWatcher eofWatcher;
    private boolean selfClosed;

    boolean isSelfClosed() {
        return this.selfClosed;
    }

    @Override
    public int read() throws IOException {
        int readLen = -1;
        if (!this.isReadAllowed()) return readLen;
        try {
            readLen = this.wrappedStream.read();
            this.checkEOF(readLen);
        }
        catch (IOException ex) {
            this.checkAbort();
            throw ex;
        }
        return readLen;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int readLen = -1;
        if (!this.isReadAllowed()) return readLen;
        try {
            readLen = this.wrappedStream.read(b, off, len);
            this.checkEOF(readLen);
        }
        catch (IOException ex) {
            this.checkAbort();
            throw ex;
        }
        return readLen;
    }

    public void abortConnection() throws IOException {
        this.selfClosed = true;
        this.checkAbort();
    }

    protected boolean isReadAllowed() throws IOException {
        if (!this.selfClosed) return this.wrappedStream != null;
        throw new IOException("Attempted read on closed stream.");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void checkEOF(int eof) throws IOException {
        InputStream toCheckStream = this.wrappedStream;
        if (toCheckStream == null) return;
        if (eof >= 0) return;
        try {
            boolean scws = true;
            if (this.eofWatcher != null) {
                scws = this.eofWatcher.eofDetected(toCheckStream);
            }
            if (!scws) return;
            toCheckStream.close();
        }
        finally {
            this.wrappedStream = null;
        }
    }

    public void releaseConnection() throws IOException {
        this.close();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void checkAbort() throws IOException {
        InputStream toAbortStream = this.wrappedStream;
        if (toAbortStream == null) return;
        try {
            boolean scws = true;
            if (this.eofWatcher != null) {
                scws = this.eofWatcher.streamAbort(toAbortStream);
            }
            if (!scws) return;
            toAbortStream.close();
        }
        finally {
            this.wrappedStream = null;
        }
    }

    @Override
    public int available() throws IOException {
        int a = 0;
        if (!this.isReadAllowed()) return a;
        try {
            a = this.wrappedStream.available();
        }
        catch (IOException ex) {
            this.checkAbort();
            throw ex;
        }
        return a;
    }

    InputStream getWrappedStream() {
        return this.wrappedStream;
    }

    public EofSensorInputStream(InputStream in, EofSensorWatcher watcher) {
        Args.notNull((Object)in, (String)"Wrapped stream");
        this.wrappedStream = in;
        this.selfClosed = false;
        this.eofWatcher = watcher;
    }

    @Override
    public void close() throws IOException {
        this.selfClosed = true;
        this.checkClose();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void checkClose() throws IOException {
        InputStream toCloseStream = this.wrappedStream;
        if (toCloseStream == null) return;
        try {
            boolean scws = true;
            if (this.eofWatcher != null) {
                scws = this.eofWatcher.streamClosed(toCloseStream);
            }
            if (!scws) return;
            toCloseStream.close();
        }
        finally {
            this.wrappedStream = null;
        }
    }
}
