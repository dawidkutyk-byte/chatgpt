/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ConnectTimeoutException
 *  org.apache.http.conn.scheme.SchemeSocketFactory
 *  org.apache.http.conn.scheme.SocketFactory
 *  org.apache.http.params.HttpParams
 */
package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpParams;

@Deprecated
class SchemeSocketFactoryAdaptor
implements SchemeSocketFactory {
    private final SocketFactory factory;

    SchemeSocketFactoryAdaptor(SocketFactory factory) {
        this.factory = factory;
    }

    public Socket connectSocket(Socket sock, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpParams params) throws UnknownHostException, ConnectTimeoutException, IOException {
        String host = remoteAddress.getHostName();
        int port = remoteAddress.getPort();
        InetAddress local = null;
        int localPort = 0;
        if (localAddress == null) return this.factory.connectSocket(sock, host, port, local, localPort, params);
        local = localAddress.getAddress();
        localPort = localAddress.getPort();
        return this.factory.connectSocket(sock, host, port, local, localPort, params);
    }

    public int hashCode() {
        return this.factory.hashCode();
    }

    public boolean isSecure(Socket sock) throws IllegalArgumentException {
        return this.factory.isSecure(sock);
    }

    public Socket createSocket(HttpParams params) throws IOException {
        return this.factory.createSocket();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this != obj) return obj instanceof SchemeSocketFactoryAdaptor ? this.factory.equals(((SchemeSocketFactoryAdaptor)obj).factory) : this.factory.equals(obj);
        return true;
    }

    public SocketFactory getFactory() {
        return this.factory;
    }
}
