/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpInetConnection
 *  org.apache.http.impl.AbstractHttpClientConnection
 *  org.apache.http.impl.io.SocketInputBuffer
 *  org.apache.http.impl.io.SocketOutputBuffer
 *  org.apache.http.io.SessionInputBuffer
 *  org.apache.http.io.SessionOutputBuffer
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 */
package org.apache.http.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import org.apache.http.HttpInetConnection;
import org.apache.http.impl.AbstractHttpClientConnection;
import org.apache.http.impl.io.SocketInputBuffer;
import org.apache.http.impl.io.SocketOutputBuffer;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

@Deprecated
public class SocketHttpClientConnection
extends AbstractHttpClientConnection
implements HttpInetConnection {
    private volatile boolean open;
    private volatile Socket socket = null;

    public String toString() {
        if (this.socket == null) return super.toString();
        StringBuilder buffer = new StringBuilder();
        SocketAddress remoteAddress = this.socket.getRemoteSocketAddress();
        SocketAddress localAddress = this.socket.getLocalSocketAddress();
        if (remoteAddress == null) return buffer.toString();
        if (localAddress == null) return buffer.toString();
        SocketHttpClientConnection.formatAddress(buffer, localAddress);
        buffer.append("<->");
        SocketHttpClientConnection.formatAddress(buffer, remoteAddress);
        return buffer.toString();
    }

    public InetAddress getLocalAddress() {
        if (this.socket == null) return null;
        return this.socket.getLocalAddress();
    }

    public int getLocalPort() {
        if (this.socket == null) return -1;
        return this.socket.getLocalPort();
    }

    private static void formatAddress(StringBuilder buffer, SocketAddress socketAddress) {
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress addr = (InetSocketAddress)socketAddress;
            buffer.append(addr.getAddress() != null ? addr.getAddress().getHostAddress() : addr.getAddress()).append(':').append(addr.getPort());
        } else {
            buffer.append(socketAddress);
        }
    }

    public void shutdown() throws IOException {
        this.open = false;
        Socket tmpsocket = this.socket;
        if (tmpsocket == null) return;
        tmpsocket.close();
    }

    public void setSocketTimeout(int timeout) {
        this.assertOpen();
        if (this.socket == null) return;
        try {
            this.socket.setSoTimeout(timeout);
        }
        catch (SocketException socketException) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void close() throws IOException {
        if (!this.open) {
            return;
        }
        this.open = false;
        Socket sock = this.socket;
        try {
            this.doFlush();
            try {
                try {
                    sock.shutdownOutput();
                }
                catch (IOException ignore) {
                    // empty catch block
                }
                try {
                    sock.shutdownInput();
                }
                catch (IOException ignore) {}
            }
            catch (UnsupportedOperationException unsupportedOperationException) {
                // empty catch block
            }
        }
        finally {
            sock.close();
        }
    }

    public InetAddress getRemoteAddress() {
        if (this.socket == null) return null;
        return this.socket.getInetAddress();
    }

    public int getSocketTimeout() {
        if (this.socket == null) return -1;
        try {
            return this.socket.getSoTimeout();
        }
        catch (SocketException ignore) {
            return -1;
        }
    }

    protected void bind(Socket socket, HttpParams params) throws IOException {
        Args.notNull((Object)socket, (String)"Socket");
        Args.notNull((Object)params, (String)"HTTP parameters");
        this.socket = socket;
        int bufferSize = params.getIntParameter("http.socket.buffer-size", -1);
        this.init(this.createSessionInputBuffer(socket, bufferSize, params), this.createSessionOutputBuffer(socket, bufferSize, params), params);
        this.open = true;
    }

    protected Socket getSocket() {
        return this.socket;
    }

    public boolean isOpen() {
        return this.open;
    }

    protected SessionOutputBuffer createSessionOutputBuffer(Socket socket, int bufferSize, HttpParams params) throws IOException {
        return new SocketOutputBuffer(socket, bufferSize, params);
    }

    protected void assertNotOpen() {
        Asserts.check((!this.open ? 1 : 0) != 0, (String)"Connection is already open");
    }

    public int getRemotePort() {
        if (this.socket == null) return -1;
        return this.socket.getPort();
    }

    protected void assertOpen() {
        Asserts.check((boolean)this.open, (String)"Connection is not open");
    }

    protected SessionInputBuffer createSessionInputBuffer(Socket socket, int bufferSize, HttpParams params) throws IOException {
        return new SocketInputBuffer(socket, bufferSize, params);
    }
}
