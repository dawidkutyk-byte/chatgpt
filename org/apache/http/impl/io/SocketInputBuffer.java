/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.io.AbstractSessionInputBuffer
 *  org.apache.http.io.EofSensor
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.io;

import java.io.IOException;
import java.net.Socket;
import org.apache.http.impl.io.AbstractSessionInputBuffer;
import org.apache.http.io.EofSensor;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

@Deprecated
public class SocketInputBuffer
extends AbstractSessionInputBuffer
implements EofSensor {
    private final Socket socket;
    private boolean eof;

    public boolean isEof() {
        return this.eof;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isDataAvailable(int timeout) throws IOException {
        boolean result = this.hasBufferedData();
        if (result) return result;
        int oldtimeout = this.socket.getSoTimeout();
        try {
            this.socket.setSoTimeout(timeout);
            this.fillBuffer();
            result = this.hasBufferedData();
        }
        finally {
            this.socket.setSoTimeout(oldtimeout);
        }
        return result;
    }

    public SocketInputBuffer(Socket socket, int bufferSize, HttpParams params) throws IOException {
        Args.notNull((Object)socket, (String)"Socket");
        this.socket = socket;
        this.eof = false;
        int n = bufferSize;
        if (n < 0) {
            n = socket.getReceiveBufferSize();
        }
        if (n < 1024) {
            n = 1024;
        }
        this.init(socket.getInputStream(), n, params);
    }

    protected int fillBuffer() throws IOException {
        int i = super.fillBuffer();
        this.eof = i == -1;
        return i;
    }
}
