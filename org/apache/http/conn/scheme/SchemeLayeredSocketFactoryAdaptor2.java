/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ConnectTimeoutException
 *  org.apache.http.conn.scheme.LayeredSchemeSocketFactory
 *  org.apache.http.conn.scheme.SchemeLayeredSocketFactory
 *  org.apache.http.params.HttpParams
 */
package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.LayeredSchemeSocketFactory;
import org.apache.http.conn.scheme.SchemeLayeredSocketFactory;
import org.apache.http.params.HttpParams;

@Deprecated
class SchemeLayeredSocketFactoryAdaptor2
implements SchemeLayeredSocketFactory {
    private final LayeredSchemeSocketFactory factory;

    public Socket createLayeredSocket(Socket socket, String target, int port, HttpParams params) throws IOException, UnknownHostException {
        return this.factory.createLayeredSocket(socket, target, port, true);
    }

    public Socket createSocket(HttpParams params) throws IOException {
        return this.factory.createSocket(params);
    }

    public boolean isSecure(Socket sock) throws IllegalArgumentException {
        return this.factory.isSecure(sock);
    }

    SchemeLayeredSocketFactoryAdaptor2(LayeredSchemeSocketFactory factory) {
        this.factory = factory;
    }

    public Socket connectSocket(Socket sock, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpParams params) throws UnknownHostException, IOException, ConnectTimeoutException {
        return this.factory.connectSocket(sock, remoteAddress, localAddress, params);
    }
}
